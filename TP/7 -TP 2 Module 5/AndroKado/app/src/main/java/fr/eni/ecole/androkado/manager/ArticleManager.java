package fr.eni.ecole.androkado.manager;

import java.util.List;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;
import fr.eni.ecole.androkado.dal.DAOFactory;

public abstract class ArticleManager {

    public static Article getFirst(){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.getFirst();
    }

    public static List<Article> findAll(){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.findAll();
    }

    public static boolean insert(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.insert(article);
    }

    public static boolean update(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.update(article);
    }

    public static boolean delete(Article article){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.delete(article);
    }
}
