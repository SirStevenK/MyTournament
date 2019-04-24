package Classes;

import java.io.Serializable;
import java.util.UUID;

public class Personne implements Serializable {

    protected String id;
    protected String nom;
    protected String prenom;
    protected int age;
    protected int taille;
    protected String nation;
    protected Competition competition;

    Personne(String nom, String prenom, int age, int taille, String nation) {
        this.id = String.valueOf(UUID.randomUUID());
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.taille = taille;
        this.nation = nation;
        this.competition = null;
    }

    public String get_id() {
        return this.id;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public void set_prenom(String prenom) {
        this.prenom = prenom;
    }

    public void set_age(int age) {
        this.age = age;
    }

    public void set_taille(int taille) {
        this.taille = taille;
    }

    public void set_nation(String nation) {
        this.nation = nation;
    }

    public String get_nom() {
        return this.nom;
    }

    public String get_prenom() {
        return this.prenom;
    }

    public int get_age() {
        return this.age;
    }

    public int get_taille() {
        return this.taille;
    }

    public String get_nation() {
        return this.nation;
    }

    public void set_competition(Competition competition) {
        this.competition = competition;
    }
    public Competition get_competition() {
        return this.competition;
    }
}
