package fr.eni.ecole.androkado.dal.impls;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.bo.DateTypeConverter;
import fr.eni.ecole.androkado.dal.ArticleDAO;
import fr.eni.ecole.androkado.dal.contracts.ArticleRoomContract;

@Database(entities = {Article.class},version = ArticleRoomContract.DATABASE_VERSION,
        exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract ArticleDAO getArticleDAO();
}
