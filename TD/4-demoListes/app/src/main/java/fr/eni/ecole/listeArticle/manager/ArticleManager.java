package fr.eni.ecole.listeArticle.manager;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.listeArticle.bo.Article;

public class ArticleManager {

    public static List<Article> getList(){
        List<Article> lst = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            lst.add(new Article(i+1, "article"+i+1));
        }

        return lst;
    }
}
