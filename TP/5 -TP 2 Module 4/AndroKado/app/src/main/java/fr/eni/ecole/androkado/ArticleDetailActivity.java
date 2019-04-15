package fr.eni.ecole.androkado;

import android.content.Intent;

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

import fr.eni.ecole.androkado.bo.Article;


public class ArticleDetailActivity extends AppCompatActivity {

    private final static String TAG = "TAG_ARTICLE";
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();

        this.article = i.getParcelableExtra("article");

        chargerArticle(article);

    }

    /**
     * Rempli le layout avec les données de l'article
     * @param article
     */
    private void chargerArticle(Article article){

        TextView nom = findViewById(R.id.txtNom);
        CheckBox active = findViewById(R.id.chkActiver);
        TextView prix = findViewById(R.id.txtPrix);
        TextView description = findViewById(R.id.txtDescription);
        RatingBar envie = findViewById(R.id.rtbEnvie);
        ToggleButton acheter = findViewById(R.id.btnAcheter);

        nom.setText(article.getTitre());
        active.setChecked(article.isActive());
        //Paramètre dans le fichier strings.xml pour "symbole_euro_prix"
        prix.setText( getString(R.string.symbole_euro_prix, String.valueOf( article.getPrix())));

        description.setText(article.getDescription());
        envie.setRating(article.getEnvie());
        acheter.setChecked(article.isAcheter());

        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i(TAG, "ToggleButton click");
                ArticleDetailActivity.this.article.setAcheter(((ToggleButton)v).isChecked());

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
                return true;
            case R.id.item_envoyer :
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
