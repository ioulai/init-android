package fr.eni.ecole.androkado;

import android.service.autofill.TextValueSanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.manager.ArticleManager;

public class ArticleDetailActivity extends AppCompatActivity {

    private final static String TAG = "TAG_ARTICLE";
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.article = ArticleManager.getFirst();

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
                Log.i(TAG, "ToggleButton click");
                ArticleDetailActivity.this.article.setAcheter(((ToggleButton)v).isChecked());

            }
        });
    }

    /**
     *
     * @param view
     */
    public void onClickWeb(View view) {
        String web = "Aucun lien actif";
        if(this.article != null && this.article.getUrl() != null){
            web = this.article.getUrl();
        }

        Toast.makeText(ArticleDetailActivity.this, web, Toast.LENGTH_LONG).show();
    }
}
