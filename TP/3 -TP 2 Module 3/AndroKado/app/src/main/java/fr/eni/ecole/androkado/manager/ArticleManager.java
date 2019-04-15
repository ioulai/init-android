package fr.eni.ecole.androkado.manager;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;
import fr.eni.ecole.androkado.dal.DAOFactory;

public abstract class ArticleManager {

    public static Article getFirst(){
        ArticleDAO dao = DAOFactory.getArticleDAO();
        return dao.getFirst();
    }

}
