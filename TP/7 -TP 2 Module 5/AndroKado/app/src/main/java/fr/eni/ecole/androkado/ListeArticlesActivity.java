package fr.eni.ecole.androkado;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.androkado.adapters.ArticleAdapter;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.manager.ArticleManager;

public class ListeArticlesActivity extends AppCompatActivity {

    private final static int RESULT_CODE_INSERT = 10001;
    //private final static int RESULT_CODE_CONFIG = 10002;
    private List<Article> articles;
    private  ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        RecyclerView liste = findViewById(R.id.listArticles);

        LinearLayoutManager manager = new LinearLayoutManager(ListeArticlesActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        liste.setLayoutManager(manager);

        this.articles = new ArrayList<>();

        this.adapter = new ArticleAdapter(articles, new ArticleAdapter.CustomItemClickListener() {
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
    protected void onResume() {
        //Permet de recharger la liste à chaque affichage de l'activité
        //Pour le fait de changer acheter dans le détail
        //Peut être à remplacer par un startActivityForResult dans le détail
        super.onResume();
        updateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()){
            case R.id.item_ajout :
                i = new Intent(ListeArticlesActivity.this, ArticleInsertActivity.class);
                startActivityForResult(i, RESULT_CODE_INSERT);
                return true;
            case R.id.item_configuration :
				i = new Intent(ListeArticlesActivity.this,
                                        ConfigurationActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_CODE_INSERT :

                if(resultCode == RESULT_OK){
                    updateList();
                }

                break;

        }

    }

    private void updateList(){
        Log.i("TAG_AND", "update list");
        articles.clear();
        articles.addAll(ArticleManager.findAll());
        this.adapter.notifyDataSetChanged();
    }

}
