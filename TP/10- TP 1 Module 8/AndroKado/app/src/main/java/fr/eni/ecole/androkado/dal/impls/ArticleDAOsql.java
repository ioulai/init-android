package fr.eni.ecole.androkado.dal.impls;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;
import fr.eni.ecole.androkado.dal.contracts.ArticleContract;
import fr.eni.ecole.androkado.dal.helper.AndroKadoHelper;

public class ArticleDAOsql implements ArticleDAO {

    private AndroKadoHelper helper;
    private Context context;

    public ArticleDAOsql(Context context)
    {
        this.helper = new AndroKadoHelper(context);

        this.context = context;
    }


    @Override
    public Article getFirst() {
        return null;
    }

    @Override
    public List<Article> findAll() {
        List<Article> lst = new ArrayList<>();
        SharedPreferences pref = context.getSharedPreferences(AndroKadoApplication.CONFIGURATION_PREF, Context.MODE_PRIVATE);

        boolean tri = pref.getBoolean("tri", false);
        boolean active = pref.getBoolean("active", false);

        String where = null;

        if(active){
            where = ArticleContract.COL_ACTIVE + " = 1";
        }

        String order = null;

        if(tri){
            order = ArticleContract.COL_PRIX + " DESC ";
        }

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(
                ArticleContract.TABLE_NAME,
                null,
                where,
                null,
                null,
                null,
                order,
                null
        );

        while(c.moveToNext()){
            lst.add(this.getArticle(c));
        }

        c.close();

        db.close();

        return lst;
    }

    @Override
    public List<Article> findAll(SupportSQLiteQuery query) {
        return null;
    }

    @Override
    public long insert(Article article) {

        ContentValues values = this.getContents(article);

        Log.i("ANDROKADO", values.toString());
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(ArticleContract.TABLE_NAME,null,values);

        article.setId(id);

        db.close();

        return id;
    }

    @Override
    public int update(Article article) {
        ContentValues values = this.getContents(article);
        String selection = ArticleContract.COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(article.getId()) };
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.update(ArticleContract.TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return count ;
    }

    @Override
    public int delete(Article article) {

        String selection = ArticleContract.COL_ID + " = ?";

        String[] selectionArgs = { String.valueOf(article.getId()) };
        SQLiteDatabase db = helper.getWritableDatabase();
        int deletedRows = db.delete(ArticleContract.TABLE_NAME, selection, selectionArgs);
        db.close();
        return deletedRows ;
    }

    /**
     *
     * @param c
     * @return Article
     */
    private Article getArticle(Cursor c){

        long date = c.getLong(c.getColumnIndex(ArticleContract.COL_DATE_CREATION));

        Article article = new Article(
                c.getInt(c.getColumnIndex(ArticleContract.COL_ID)),
                c.getString(c.getColumnIndex(ArticleContract.COL_NOM)),
                c.getString(c.getColumnIndex(ArticleContract.COL_DESCRIPTION)),
                c.getString(c.getColumnIndex(ArticleContract.COL_URL)),
                c.getDouble(c.getColumnIndex(ArticleContract.COL_PRIX)),
                c.getFloat(c.getColumnIndex(ArticleContract.COL_ENVIE)),
                c.getInt(c.getColumnIndex(ArticleContract.COL_ACTIVE)) == 1,
                c.getInt(c.getColumnIndex(ArticleContract.COL_ACHETER)) == 1,
                date != -1 ? new Date(date) : null
        );

        return article;
    }

    /**
     *
     * @param article
     * @return
     */
    private ContentValues getContents(Article article){
        ContentValues values = new ContentValues();

        values.put(ArticleContract.COL_NOM, article.getNom());
        values.put(ArticleContract.COL_DESCRIPTION, article.getDescription());
        values.put(ArticleContract.COL_URL, article.getUrl());
        values.put(ArticleContract.COL_PRIX, article.getPrix());
        values.put(ArticleContract.COL_ENVIE, article.getEnvie());
        values.put(ArticleContract.COL_ACTIVE, article.isActive() ? 1 : 0);
        values.put(ArticleContract.COL_ACHETER, article.isAcheter() ? 1 : 0);

        values.put(ArticleContract.COL_DATE_CREATION, article.getDateCreation() != null ? article.getDateCreation().getTime() : -1);

        return values;
    }
}
