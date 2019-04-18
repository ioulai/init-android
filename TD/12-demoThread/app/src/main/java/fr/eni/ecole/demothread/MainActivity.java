package fr.eni.ecole.demothread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private MonHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.pb_demo);
        pb.setProgress(0);

        this.handler = new MonHandler();

    }

    public void onClickHello(View view) {

        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();

    }

    public void onClickUI(View view) {

        for (int i =0; i <= 10; i++){
            pb.setProgress(i);
            //Pause 1seconde entre chaque boucle
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void clearProgress(){
        pb.setProgress(0);
    }

    private void progress(int i){
        pb.setProgress(i);
    }

    private void stopProgress(){
        Toast.makeText(MainActivity.this,
                "Fin de la tâche asynchrone",
                Toast.LENGTH_LONG).show();
    }

    public void onClickThread(View view) {
        //Ne pas oubliez le start()
        new Thread(new Runnable() {
            @Override
            public void run() {

                //Début
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);

                for(int i = 0; i <= 10; i++){
                    //progress(i);

                    //En cours de progression
                    Message msgEnCours = new Message();
                    msgEnCours.what = 2;
                    msgEnCours.arg1 = i;
                    handler.sendMessage(msgEnCours);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Fin du traitement
                Message msgFin = new Message();
                msgFin.what = 3;
                handler.sendMessage(msgFin);

            }
        }).start();

    }

    public void onClickAsync(View view) {

        Worker worker = new Worker();
        worker.execute("param1","param2");

    }

    //<Params,Progress,Return>
    class Worker extends AsyncTask<String,Integer,String>{

        //Traitement
        @Override
        protected String doInBackground(String... voids) {

            Log.i("TAG_ASYNC", voids[0]);

            for(int i = 0; i <= 10; i++){

                //Appelle le onProgressUpdate
                publishProgress(i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "fin";
        }

        //Appeler avant le doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //clearProgress();
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }

        //Lier à publishProgress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //progress(values[0]);
            Message msgEnCours = new Message();
            msgEnCours.what = 2;
            msgEnCours.arg1 = values[0];
            handler.sendMessage(msgEnCours);
        }

        //Appeler à la fin du doInBackground
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //clearProgress();

            Message msgFin = new Message();
            msgFin.what = 3;
            handler.sendMessage(msgFin);
        }
    }

    class MonHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1 : clearProgress();
                case 2 : progress(msg.arg1);
                case 3 : stopProgress();
            }

        }
    }

}
