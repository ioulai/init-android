package fr.eni.ecole.androkado.dal;

import android.arch.persistence.room.Room;

import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.dal.contracts.ArticleRoomContract;
import fr.eni.ecole.androkado.dal.impls.DatabaseRoom;

public abstract class DAOFactory {

    public static ArticleDAO getArticleDAO(){
        //return new ArticleDAOimpl();
        //return new ArticleDAOsql(AndroKadoApplication.getAppContext());
		  DatabaseRoom db = Room.databaseBuilder(
		          AndroKadoApplication.getAppContext(),
                DatabaseRoom.class, ArticleRoomContract.DATABASE_NAME).build();

        return db.getArticleDAO();
    }

}
