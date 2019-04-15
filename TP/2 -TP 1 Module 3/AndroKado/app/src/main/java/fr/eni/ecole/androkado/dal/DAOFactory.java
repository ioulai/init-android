package fr.eni.ecole.androkado.dal;

import fr.eni.ecole.androkado.dal.impls.ArticleDAOimpl;

public abstract class DAOFactory {

    public static ArticleDAO getArticleDAO(){
        return new ArticleDAOimpl();
    }

}
