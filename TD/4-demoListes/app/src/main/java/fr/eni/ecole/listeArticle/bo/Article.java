package fr.eni.ecole.listeArticle.bo;

public class Article {

    private int id;
    private String nomArticle;

    public Article() {
    }

    public Article(int id, String nomArticle) {
        this.id = id;
        this.nomArticle = nomArticle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

}
