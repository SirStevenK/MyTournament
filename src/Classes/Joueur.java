package Classes;

import java.io.Serializable;

public class Joueur extends Personne implements Serializable {
    private Poste poste;
    private Statut statut;
    private Equipe equipe;

    public Joueur(String nom, String prenom, int age, int taille, String nation, Poste poste) {
        super(nom, prenom, age, taille, nation);
        this.poste = poste;
        this.statut = null;
        this.equipe = null;
    }

    public Statut get_statut() {
        return this.statut;
    }

    public void set_statut(Statut statut) {
        this.statut = statut;
    }

    public Poste get_poste() {
        return this.poste;
    }

    public void set_poste(Poste poste) {
        this.poste = poste;
    }

    public Equipe get_equipe() {
        return this.equipe;
    }

    public void set_equipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String toString() {
        return this.get_nom() + " " + this.get_prenom();
    }

}