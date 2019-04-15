package fr.eni.ecole.androkado.dal;

import java.util.List;

import fr.eni.ecole.androkado.bo.Article;

public interface ArticleDAO {

    public Article getFirst();
    public List<Article> findAll();
    public boolean insert(Article article);
    public boolean update(Article article);
    public boolean delete(Article article);

}
