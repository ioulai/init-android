package fr.eni.ecole.androkado.bo;

public class Contact {
    private String id;
    private String nom;
    private String telephone;

    public Contact() {
    }

    /**
     *
     * @param id
     * @param nom
     * @param telephone
     */
    public Contact(String id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
