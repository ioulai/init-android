package fr.eni.ecole.androkado.manager;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.content.SharedPreferences;

import java.util.List;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;
import fr.eni.ecole.androkado.dal.DAOFactory;
import fr.eni.ecole.androkado.dal.contracts.ArticleRoomContract;

public abstract class ArticleManager {

    public static Article getFirst(){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.getFirst();
    }

    public static List<Article> findAll(){
        String query = "SELECT * FROM ARTICLE";

        SharedPreferences pref = AndroKadoApplication.getAppContext()
                .getSharedPreferences(
                        AndroKadoApplication.CONFIGURATION_PREF,
                        AndroKadoApplication.getAppContext().MODE_PRIVATE
                );



        boolean tri = pref.getBoolean("tri", false);
        boolean active = pref.getBoolean("active", false);

        if(active){
            query += " WHERE " + ArticleRoomContract.COL_ACTIVE + " = 1";
        }

        if(tri){
            query += " ORDER BY " + ArticleRoomContract.COL_PRIX + " DESC ";
        }

        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.findAll(new SimpleSQLiteQuery(query));
    }

    public static boolean insert(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.insert(article) > 0;
    }

    public static boolean update(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.update(article) > 0;
    }

    public static boolean delete(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.delete(article) > 0;
    }
}
