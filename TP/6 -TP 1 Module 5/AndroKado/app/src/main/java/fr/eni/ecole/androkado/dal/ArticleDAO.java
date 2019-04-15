package fr.eni.ecole.androkado.dal;

import java.util.List;

import fr.eni.ecole.androkado.bo.Article;

public interface ArticleDAO {
    public Article getFirst();
	public List<Article> findAll();
}
