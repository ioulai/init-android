package fr.eni.ecole.androkado.dal.impls;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;

public class ArticleDAOimpl implements ArticleDAO {

    private List<Article> articles;

    public ArticleDAOimpl() {
        this.articles = new ArrayList<>();
        articles.add(new Article("Pain au chocolat", "Une viennoiserie au beurre et au chocolat", "https://fr.wikipedia.org/wiki/Pain_au_chocolat", 0.95, 3.5f, true,false));
        articles.add(new Article("Journal", "Le journal local du jour", "https://www.ouest-france.fr/", 1.1, 2f, true,true));
        articles.add(new Article("Café", "Un noir serré", "https://fr.wikipedia.org/wiki/Cafe", 1.4, 3.5f, true,false));
        articles.add(new Article("Vacance au Ski", "Détente, montagne et ski","https://fr.wikipedia.org/wiki/Ski", 950, 5, false,false));
    }

    @Override
    public Article getFirst() {
        return articles.get(0);
    }
}
