package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Equipe implements Serializable {
    private String id;
    private String nom;
    private Competition competition;
    private Entraineur entraineur;
    private ArrayList<Joueur> joueurs;

    public Equipe(String nom) {
        this.id = String.valueOf(UUID.randomUUID());
        this.nom = nom;
        this.joueurs = new ArrayList<Joueur>();
        this.entraineur = null;
        this.competition = null;
    }

    public String get_id() {
        return this.id;
    }

    public String get_nom() {
        return this.nom;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public Entraineur get_entraineur() {
        return this.entraineur;
    }

    public void set_entraineur(Entraineur entraineur) {
        this.entraineur = entraineur;
    }

    public boolean has_joueur(Joueur joueur) {
        for (Joueur actual : this.joueurs) {
            if (actual.get_id().equals(joueur.get_id())) return true;
        }
        return false;
    }

    public void ajouter_joueur(Joueur joueur) {
        this.joueurs.add(joueur);
        joueur.set_equipe(this);
    }

    public void supprimer_joueur(Joueur joueur) {
        this.joueurs.remove(joueur);
    }

    public ArrayList<Joueur> get_joueurs() {
        return joueurs;
    }

    public void set_competition(Competition competition) {
        this.competition = competition;
    }

    public ArrayList<Match> get_matchs() {
        return this.competition.get_list_match_of_equipe(this);
    }

    public int get_nombre_victoire() {
        int nombre_victoire = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            if (match.get_team_result(this) == Resultat.Victoire) nombre_victoire++;
        }
        return nombre_victoire;
    }

    public int get_nombre_nul() {
        int nombre_nul = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            if (match.get_team_result(this) == Resultat.Nul) nombre_nul++;
        }
        return nombre_nul;
    }

    public int get_nombre_defaite() {
        int nombre_defaite = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            if (match.get_team_result(this) == Resultat.Defaite) nombre_defaite++;
        }
        return nombre_defaite;
    }

    public int get_nombre_but() {
        int nombre_but = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_but += match.get_nb_but_of_equipe(this);
        }
        return nombre_but;
    }

    public int get_nombre_but_pris() {
        int nombre_but_pris = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_but_pris += match.get_nb_but_pris_of_equipe(this);
        }
        return nombre_but_pris;
    }

    public int get_nombre_joues() {
        int nombre_match = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            if (match.get_fait()) nombre_match++;
        }
        return nombre_match;
    }

    public int get_nombre_carton_jaune() {
        int nombre_carton_jaune = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_carton_jaune += match.get_nb_carton_jaune_of_equipe(this);
        }
        return nombre_carton_jaune;
    }

    public int get_nombre_carton_rouge() {
        int nombre_carton_rouge = 0;
        ArrayList<Match> list_matchs = this.get_matchs();
        for (Match match : list_matchs) {
            nombre_carton_rouge += match.get_nb_carton_rouge_of_equipe(this);
        }
        return nombre_carton_rouge;
    }
}