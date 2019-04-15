package fr.eni.ecole.androkado.bo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Article implements Parcelable {

    private long id;
    private String nom;
    private String description;
    private String url;
    private double prix = 0;
    private float envie = 0;
    private boolean active = true;
    private boolean acheter = false;
    private Date dateCreation;

    public Article() {

    }

    /**
     *
     * @param nom
     * @param description
     * @param url
     * @param prix
     * @param envie
     * @param active
     * @param acheter
     */
    public Article(String nom, String description, String url, double prix, float envie, boolean active, boolean acheter, Date dateCreation) {
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.envie = envie;
        this.active = active;
        this.acheter = acheter;
        this.dateCreation = dateCreation;
    }

    /**
     *
     * @param id
     * @param nom
     * @param description
     * @param url
     * @param prix
     * @param envie
     * @param active
     * @param acheter
     */
    public Article(long id, String nom, String description, String url, double prix, float envie, boolean active, boolean acheter, Date dateCreation) {
        this(nom, description,url, prix,  envie, active, acheter, dateCreation);
        this.id = id;
    }


    protected Article(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        description = in.readString();
        url = in.readString();
        prix = in.readDouble();
        envie = in.readFloat();
        active = in.readByte() != 0;
        acheter = in.readByte() != 0;

        long date = in.readLong();
        dateCreation = date != -1 ? new Date(date) : null;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String titre) {
        this.nom = titre;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeDouble(prix);
        dest.writeFloat(envie);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeByte((byte) (acheter ? 1 : 0));
        dest.writeLong(dateCreation != null ? dateCreation.getTime() : -1);

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", prix=" + prix +
                ", envie=" + envie +
                ", active=" + active +
                ", acheter=" + acheter +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
