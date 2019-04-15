package fr.eni.ecole.listeArticle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import fr.eni.ecole.listeArticle.R;
import fr.eni.ecole.listeArticle.adapter.ArticleRecyclerAdapter;
import fr.eni.ecole.listeArticle.bo.Article;
import fr.eni.ecole.listeArticle.manager.ArticleManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_main);

        ArticleRecyclerAdapter adapter
                = new ArticleRecyclerAdapter(ArticleManager.getList(),
                    new ArticleRecyclerAdapter.onClickItemListener() {
                        @Override
                        public void onClickItem(Article p) {
                            Toast.makeText(MainActivity.this,
                                     p.getNomArticle(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
        RecyclerView recycler = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);

        /*
        setContentView(R.layout.activity_main);

        ListView liste = findViewById(R.id.lstviewPersonnes);

        ArticleAdapter adapter = new ArticleAdapter(MainActivity.this,
                                R.layout.layout_item,
                                ArticleManager.getList());

        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article p = (Article) parent.getItemAtPosition(position);

                Toast.makeText(MainActivity.this,
                        p.getPrenom() + " " + p.getNomArticle(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
        */

    }
}
