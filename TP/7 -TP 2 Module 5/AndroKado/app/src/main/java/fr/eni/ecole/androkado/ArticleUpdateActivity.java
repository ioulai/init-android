package fr.eni.ecole.androkado;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.ihmUtil.ArticleForm;

public class ArticleUpdateActivity extends AppCompatActivity {

    private ArticleForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_update);

        Intent i = getIntent();

        Article article = i.getParcelableExtra("article");

        this.form = new ArticleForm(ArticleUpdateActivity.this, article);

        this.form.chargeArticle();

    }

    /**
     *
     * @param view
     */
    public void updateArticleClick(View view) {

        List<String> errors = new ArrayList<>();
        String error = null;

        if(this.form.checkForm(errors)){

           if(this.form.saveOrUpdate()){
                //TODO : redirection détail ou liste
               Toast.makeText(ArticleUpdateActivity.this,"Mise à jour de l'article",Toast.LENGTH_LONG).show();
               Intent i = new Intent();
               i.putExtra("article", this.form.getArticle());
               setResult(RESULT_OK, i);
               finish();

           }
           else{
               error = "Impossible de sauvegarder l'article !!";
           }

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
            Toast.makeText(ArticleUpdateActivity.this, error, Toast.LENGTH_LONG).show();
        }

    }
}
