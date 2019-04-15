package fr.eni.ecole.androkado.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

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

    protected Article(Parcel in) {
        titre = in.readString();
        description = in.readString();
        url = in.readString();
        prix = in.readDouble();
        envie = in.readFloat();
        active = in.readByte() != 0;
        acheter = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeDouble(prix);
        dest.writeFloat(envie);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeByte((byte) (acheter ? 1 : 0));
    }
}
