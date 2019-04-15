package fr.eni.ecole.androkado.bo;

public class Article {
    private String titre;
    private String description;
    private String url;
    private double prix = 0;
    private float envie = 0;
    private boolean active = true;
    private boolean acheter = false;

    public Article() {

    }

    /**
     *
     * @param titre
     * @param description
     * @param url
     * @param prix
     * @param envie
     * @param active
     * @param acheter
     */
    public Article(String titre, String description, String url, double prix, float envie, boolean active, boolean acheter) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.envie = envie;
        this.active = active;
        this.acheter = acheter;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public float getEnvie() {
        return envie;
    }

    public void setEnvie(float envie) {
        this.envie = envie;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAcheter() {
        return acheter;
    }

    public void setAcheter(boolean acheter) {
        this.acheter = acheter;
    }

}
