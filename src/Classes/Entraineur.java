package Classes;

import java.io.Serializable;

public class Entraineur extends Personne implements Serializable {
    private Equipe equipe;

    public Entraineur(String nom, String prenom, int age, int taille, String nation) {
        super(nom, prenom, age, taille, nation);
        this.equipe = null;
    }

    public Equipe get_equipe() {
        return this.equipe;
    }

    public void set_equipe(Equipe equipe) {
        this.equipe = equipe;
    }
}