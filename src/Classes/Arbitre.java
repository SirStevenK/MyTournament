package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Arbitre extends Personne implements Serializable {
    private Competition competition;

    public Arbitre(String nom, String prenom, int age, int taille, String nation) {
        super(nom, prenom, age, taille, nation);
    }

    public ArrayList<Match> get_matchs() {
        return this.competition.get_list_match_of_arbitre(this);
    }

    public int get_nombre_carton_jaune() {
        int nombre_carton_jaune = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_carton_jaune += match.get_nb_carton_jaune();
        }
        return nombre_carton_jaune;
    }

    public int get_nombre_carton_rouge() {
        int nombre_carton_rouge = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_carton_rouge += match.get_nb_carton_rouge();
        }
        return nombre_carton_rouge;
    }
}