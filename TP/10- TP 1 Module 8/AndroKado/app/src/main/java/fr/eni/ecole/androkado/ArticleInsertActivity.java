package fr.eni.ecole.androkado;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.ihmUtil.ArticleForm;
import fr.eni.ecole.androkado.manager.ArticleManager;

public class ArticleInsertActivity extends AppCompatActivity {

    private ArticleForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_insert);

        Article article = new Article();

        article.setPrix(
                getSharedPreferences(AndroKadoApplication.CONFIGURATION_PREF,MODE_PRIVATE)
                        .getFloat("defaultPrix", 50));

        this.form = new ArticleForm(ArticleInsertActivity.this, article);

        this.form.chargeArticle();


    }

    /**
     *
     * @param view
     */
    public void insertArticleClick(View view) {

        List<String> errors = new ArrayList<>();
        String error = null;

        if(this.form.checkForm(errors)){

            this.form.saveOrUpdate(new InsertHandler());

        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

            error = errors.stream()
                    .collect(Collectors.joining("\n"));

        }
        else{
            StringBuilder sb = new StringBuilder();
            for(String e : errors){
                sb.append(e);
                sb.append("\n");
            }

            error = sb.toString();
        }

        if(error != null){
            Toast.makeText(ArticleInsertActivity.this, error, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Pour les messages du Handler
     * @param ok
     */
    private void insertMsg(boolean ok){
        if(ok){
            Toast.makeText(ArticleInsertActivity.this,"Insertion nouvel article",Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();

        }
        else{
            Toast.makeText(ArticleInsertActivity.this, "Impossible de sauvegarder l'article !!", Toast.LENGTH_LONG).show();
        }
    }

    private class InsertHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i("TAG_AND", "msg : " + msg.what);
            switch (msg.what){
                case 1 :
                    ArticleInsertActivity.this.insertMsg(true);
                    break;
                case 2 :
                    ArticleInsertActivity.this.insertMsg(false);
                    break;
            }
        }
    }
}
