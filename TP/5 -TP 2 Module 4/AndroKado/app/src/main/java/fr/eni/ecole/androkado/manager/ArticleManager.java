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
}
