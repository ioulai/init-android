package fr.eni.ecole.androkado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import fr.eni.ecole.androkado.adapters.ArticleAdapter;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.manager.ArticleManager;

public class ListeArticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        RecyclerView liste = findViewById(R.id.listArticles);

        LinearLayoutManager manager = new LinearLayoutManager(ListeArticlesActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        liste.setLayoutManager(manager);

        List<Article> articles = ArticleManager.findAll();

        ArticleAdapter adapter = new ArticleAdapter(articles, new ArticleAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View v, Article article) {
                Intent i = new Intent(ListeArticlesActivity.this, ArticleDetailActivity.class);
                i.putExtra("article", article);
                startActivity(i);
            }
        });

        liste.setAdapter(adapter);

    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_ajout :
                return true;
            case R.id.item_configuration :
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
