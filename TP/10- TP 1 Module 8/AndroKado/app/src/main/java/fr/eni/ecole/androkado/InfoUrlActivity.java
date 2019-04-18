package fr.eni.ecole.androkado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;

public class InfoUrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);

        Intent i = getIntent();
        Article article = i.getParcelableExtra(AndroKadoApplication.INTENT_ARTICLE);

        TextView url = findViewById(R.id.txtUrlWeb);

        if(article != null) {
            url.setText(article.getUrl());
        }
        else{
            url.setText(R.string.article_not_found);
        }
    }
}
