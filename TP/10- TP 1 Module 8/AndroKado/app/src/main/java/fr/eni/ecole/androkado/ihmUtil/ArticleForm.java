package fr.eni.ecole.androkado.ihmUtil;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import fr.eni.ecole.androkado.R;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.manager.ArticleManager;

public class ArticleForm {

    private Context context;
    private Article article;
    private EditText nom;
    private EditText description;
    private EditText url;
    private EditText prix;
    private RatingBar envie;
    private CheckBox active;
    private boolean isCheck = false;

    /**
     *
     * @param context Context
     * @param article Article
     */
    public ArticleForm(Context context, Article article){
        this.context = context;
        this.article = article;
        Activity activity = ((Activity) context);
        nom = activity.findViewById(R.id.edtNom);
        description = activity.findViewById(R.id.edtDescription);
        url = activity.findViewById(R.id.edtUrl);
        prix = activity.findViewById(R.id.edtPrix);
        envie = activity.findViewById(R.id.rtbFormEnvie);
        active = activity.findViewById(R.id.chckFormActive);

    }

    /**
     *
     *
     */
    public void chargeArticle(){

        nom.setText(article.getNom());
        description.setText(article.getDescription());
        url.setText(article.getUrl());
        prix.setText(String.valueOf(article.getPrix()));
        envie.setRating(article.getEnvie());
        active.setChecked(article.isActive());

    }

    /**
     *
     * @param errors List<String>
     * @return boolean
     */
    public boolean checkForm(List<String> errors){
        isCheck = false;

        String no = nom.getText().toString();
        String descr = description.getText().toString();
        double p = Double.parseDouble(prix.getText().toString());

        if(no.trim().length() == 0){
            errors.add("Le nom de l'article ne peut être vide");
        }

        if(descr.trim().length() == 0){
            errors.add("La description de l'article ne peut être vide");
        }

        if(p < 0){
            errors.add("Le prix de l'article ne peut être vide");
        }

        isCheck = errors.size() == 0;

        return isCheck;
    }

    /**
     * InvalidParameterException
     * @return Article
     */
    public Article getArticle(){

        if(!this.isCheck){
            throw new InvalidParameterException(context.getString(R.string.exception_article_form_get));
        }

        this.article.setNom(nom.getText().toString());
        this.article.setDescription(description.getText().toString());
        this.article.setUrl(url.getText().toString());
        this.article.setPrix(Double.parseDouble(prix.getText().toString()));
        this.article.setEnvie(envie.getRating());
        this.article.setActive(active.isChecked());

        return this.article;
    }

    /**
     * Save or Update Article from FORM
     * @return boolean
     */
    public boolean saveOrUpdate(){
        boolean r = false;
        try {
            Article a = this.getArticle();

            if(a.getId() > 0){
                r = ArticleManager.update(a);
            }
            else{
                a.setDateCreation(new Date());
                r = ArticleManager.insert(a);
            }
        }
        catch (InvalidParameterException e){
            Log.e("ERROR_ANDROKADO", e.getMessage());
        }

        return r;
    }


    public void saveOrUpdate(Handler handler){

        try {
            Article a = this.getArticle();
            Log.i("TAG_AND", "saveOrUpdate : " + a.toString());
            InsertOrUpdateTask task = new InsertOrUpdateTask();
            task.setHandler(handler);
            task.execute(a);

        }
        catch (InvalidParameterException e) {
            Log.e("ERROR_ANDROKADO", e.getMessage());
        }
    }

    //Demande a être static en cas de risque de fuite mémoire si le parent est détruit avant la fin de l'execution
    private static class InsertOrUpdateTask
            extends AsyncTask<Article, Void, Boolean> {

        private Handler handler;

        @Override
        protected Boolean doInBackground(Article... articles) {

            boolean r;

            Article a = articles[0];
            Log.i("TAG_AND", a.toString());
            if(a.getId() > 0){
                r = ArticleManager.update(a);
            }
            else{
                r = ArticleManager.insert(a);
            }

            return r;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(this.handler != null) {
                Message msg = new Message();

                if (aBoolean) {
                    msg.what = 1;
                } else {
                    msg.what = 2;
                }

                this.handler.sendMessage(msg);
            }
        }

        //Peut être remplacer par un constructeur
        public void setHandler(Handler handler){
            this.handler = handler;
        }
    }

}
