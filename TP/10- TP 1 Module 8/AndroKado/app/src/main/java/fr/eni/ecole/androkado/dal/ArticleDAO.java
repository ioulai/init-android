package fr.eni.ecole.androkado.dal;

import java.util.List;

import fr.eni.ecole.androkado.bo.Article;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Update;

@Dao
public interface ArticleDAO {

	
	@Query("SELECT * FROM ARTICLE LIMIT 1")
    public Article getFirst();

    @Query("SELECT * FROM ARTICLE")
    public List<Article> findAll();

    @RawQuery
    public List<Article> findAll(SupportSQLiteQuery query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Article article);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public int update(Article article);

    @Delete
    public int delete(Article article);

}
