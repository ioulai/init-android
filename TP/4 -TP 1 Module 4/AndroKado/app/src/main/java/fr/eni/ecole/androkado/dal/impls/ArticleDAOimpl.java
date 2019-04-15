package fr.eni.ecole.androkado.dal.impls;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.dal.ArticleDAO;

public class ArticleDAOimpl implements ArticleDAO {

    private List<Article> articles;

    public ArticleDAOimpl() {
        this.articles = new ArrayList<>();
        articles.add(new Article("Pain au chocolat", "Une viennoiserie au beurre et au chocolat", "https://fr.wikipedia.org/wiki/Pain_au_chocolat", 1.0, 4f, true,false));
        articles.add(new Article("Journal", "Le journal local du jour", "https://www.ouest-france.fr/", 1.1, 2f, true,true));
        articles.add(new Article("Café", "Un noir serré", "https://fr.wikipedia.org/wiki/Cafe", 1.4, 3.5f, true,false));
        articles.add(new Article("Vacance au Ski", "Détente, montagne et ski","https://fr.wikipedia.org/wiki/Ski", 950, 5, false,false));
        articles.add(new Article("Voiture", "Voiture","https://fr.wikipedia.org/wiki/Ski", 25000, 2, true,false));
        articles.add(new Article("Théatre", "Pièce","https://fr.wikipedia.org/wiki/Ski", 35.5, 2.5f, false,true));
        articles.add(new Article("Blouson", "en cuir","https://fr.wikipedia.org/wiki/Ski", 120, 3.5f, true,false));
        articles.add(new Article("Coiffeur", "Coupe d'été","https://fr.wikipedia.org/wiki/Ski", 25, 1, true,false));
        articles.add(new Article("Pain au chocolat", "Une viennoiserie au beurre et au chocolat", "https://fr.wikipedia.org/wiki/Pain_au_chocolat", 1.0, 4f, true,false));
        articles.add(new Article("Journal", "Le journal local du jour", "https://www.ouest-france.fr/", 1.1, 2f, true,true));
        articles.add(new Article("Café", "Un noir serré", "https://fr.wikipedia.org/wiki/Cafe", 1.4, 3.5f, true,false));
        articles.add(new Article("Vacance au Ski", "Détente, montagne et ski","https://fr.wikipedia.org/wiki/Ski", 950, 5, false,false));
        articles.add(new Article("Voiture", "Voiture","https://fr.wikipedia.org/wiki/Ski", 25000, 2, true,false));
        articles.add(new Article("Théatre", "Pièce","https://fr.wikipedia.org/wiki/Ski", 35.5, 2.5f, false,true));
        articles.add(new Article("Blouson", "en cuir","https://fr.wikipedia.org/wiki/Ski", 120, 3.5f, true,false));
        articles.add(new Article("Coiffeur", "Coupe d'été","https://fr.wikipedia.org/wiki/Ski", 25, 1, true,false));
        articles.add(new Article("Pain au chocolat", "Une viennoiserie au beurre et au chocolat", "https://fr.wikipedia.org/wiki/Pain_au_chocolat", 1.0, 4f, true,false));
        articles.add(new Article("Journal", "Le journal local du jour", "https://www.ouest-france.fr/", 1.1, 2f, true,true));
        articles.add(new Article("Café", "Un noir serré", "https://fr.wikipedia.org/wiki/Cafe", 1.4, 3.5f, true,false));
        articles.add(new Article("Vacance au Ski", "Détente, montagne et ski","https://fr.wikipedia.org/wiki/Ski", 950, 5, false,false));
        articles.add(new Article("Voiture", "Voiture","https://fr.wikipedia.org/wiki/Ski", 25000, 2, true,false));
        articles.add(new Article("Théatre", "Pièce","https://fr.wikipedia.org/wiki/Ski", 35.5, 2.5f, false,true));
        articles.add(new Article("Blouson", "en cuir","https://fr.wikipedia.org/wiki/Ski", 120, 3.5f, true,false));
        articles.add(new Article("Coiffeur", "Coupe d'été","https://fr.wikipedia.org/wiki/Ski", 25, 1, true,false));


    }

    @Override
    public Article getFirst() {
        return articles.get(0);
    }

    @Override
    public List<Article> findAll() {
        return this.articles;
    }
}
