package fr.eni.ecole.androkado.dal;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.dal.impls.ArticleDAOimpl;
import fr.eni.ecole.androkado.dal.impls.ArticleDAOsql;

public abstract class DAOFactory {

    public static ArticleDAO getArticleDAO(){
        //return new ArticleDAOimpl();
        return new ArticleDAOsql(AndroKadoApplication.getAppContext());
    }

}
