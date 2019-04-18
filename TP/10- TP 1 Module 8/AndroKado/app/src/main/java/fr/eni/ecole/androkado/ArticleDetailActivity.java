package fr.eni.ecole.androkado;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.manager.ArticleManager;


public class ArticleDetailActivity extends AppCompatActivity {

    private final static String TAG = "TAG_ARTICLE";
    private Article article;
    private View mLayout;
    private final static int RESULT_UPDATE = 1201;
    private final static int PERMISSION_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();

        mLayout = findViewById(R.id.layout_detail);

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();

        this.article = i.getParcelableExtra(AndroKadoApplication.INTENT_ARTICLE);

        chargerArticle();

    }

    /**
     * Rempli le layout avec les données de l'article
     *
     */
    private void chargerArticle(){

        TextView nom = findViewById(R.id.txtNom);
        CheckBox active = findViewById(R.id.chkActiver);
        TextView prix = findViewById(R.id.txtPrix);
        TextView description = findViewById(R.id.txtDescription);
        RatingBar envie = findViewById(R.id.rtbEnvie);
        ToggleButton acheter = findViewById(R.id.btnAcheter);

        nom.setText(article.getNom());
        active.setChecked(article.isActive());
        //Paramètre dans le fichier strings.xml pour "symbole_euro_prix"
        prix.setText( getString(R.string.symbole_euro_prix, String.valueOf( article.getPrix())));

        description.setText(article.getDescription());
        envie.setRating(article.getEnvie());
        acheter.setChecked(article.isAcheter());

        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArticleDetailActivity.this.article.setAcheter(((ToggleButton)v).isChecked());
                Log.i(TAG, "ToggleButton click" + article);

                //Simple thread pour update
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArticleManager.update(article);
                    }
                }).start();


            }
        });
    }

    /**
     *
     * @param view View
     */
    public void onClickWeb(View view) {
        Intent i = new Intent(ArticleDetailActivity.this, InfoUrlActivity.class);
        //Nécessite l'Interface Parcelable
        i.putExtra("article", this.article);
        startActivity(i);
    }

    /**
     * Permet d'associer un fichier de ressource menu au layout de l'activité
     * @param menu
     * @return
     */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    /**
     * Traitement des menus
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
            case R.id.item_modifier :
                Intent i = new Intent(ArticleDetailActivity.this, ArticleUpdateActivity.class);
                i.putExtra(AndroKadoApplication.INTENT_ARTICLE, article);
                startActivityForResult(i, RESULT_UPDATE);
                return true;
            case R.id.item_envoyer :
				if(ContextCompat.checkSelfPermission(ArticleDetailActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED){

                    if(ActivityCompat.shouldShowRequestPermissionRationale(ArticleDetailActivity.this,
                            Manifest.permission.READ_CONTACTS)){
                        Snackbar.make(mLayout,
                                R.string.msg_demande_permission_snackbar,
                                Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(ArticleDetailActivity.this,
                                        new String[] {Manifest.permission.READ_CONTACTS},
                                                PERMISSION_READ_CONTACTS);
                            }
                        }).show();
                    }
                    else{
                        ActivityCompat.requestPermissions(ArticleDetailActivity.this,
                                new String[] {Manifest.permission.READ_CONTACTS}, PERMISSION_READ_CONTACTS);
                    }

                }
                else{
                    //startActivity
                    startContactActivity();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Retour de la demande de permission
     * @param requestCode int
     * @param permissions String[]
     * @param grantResults int[]
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_READ_CONTACTS :
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startContactActivity();
                }
                else{
                    Toast.makeText(ArticleDetailActivity.this,getString(R.string.alert_permission_contact), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /**
     * Démarre l'activité des contacts
     */
    private void startContactActivity(){
        Intent i = new Intent(ArticleDetailActivity.this, ContactActivity.class);
        i.putExtra("article", this.article);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_UPDATE){
            if(resultCode == RESULT_OK){
                if(data != null) {
                    this.article = data.getParcelableExtra(AndroKadoApplication.INTENT_ARTICLE);
                    chargerArticle();
                }
            }
        }
    }

}
