package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Competition implements Serializable {
    protected String nom;
    protected ArrayList<Match> list_matchs;
    protected ArrayList<Arbitre> list_arbitres;
    protected ArrayList<Equipe> list_equipes;
    protected ArrayList<Joueur> list_joueurs;
    protected int nombre_tour;

    public Competition(String nom) {
        this.nom = nom;
        this.list_matchs = new ArrayList<Match>();
        this.list_arbitres = new ArrayList<Arbitre>();
        this.list_equipes = new ArrayList<Equipe>();
        this.list_joueurs = new ArrayList<Joueur>();
        this.nombre_tour = 0;
    }

    public String get_nom() {
        return this.nom;
    }

    public int get_tour_actuel() {
        return -1;
    }

    public int get_nombre_tour() {
        return this.nombre_tour;
    }

    public ArrayList<Match> get_list_matchs() {
        return this.list_matchs;
    }

    public ArrayList<Arbitre> get_list_arbitres() {
        return this.list_arbitres;
    }

    public ArrayList<Equipe> get_list_equipes() {
        return this.list_equipes;
    }

    public ArrayList<Joueur> get_list_joueurs() {
        return this.list_joueurs;
    }

    public void add_arbitre(Arbitre arbitre) {
        this.list_arbitres.add(arbitre);
    }

    public void add_equipe(Equipe equipe) {
        this.list_equipes.add(equipe);
        equipe.set_competition(this);
    }

    public void add_joueur(Joueur joueur) {
        this.list_joueurs.add(joueur);
    }

    public void supprimer_arbitre(Arbitre arbitre) {
        this.list_arbitres.remove(arbitre);
    }

    public void supprimer_equipe(Equipe equipe) {
        this.list_equipes.remove(equipe);
    }

    public boolean has_equipe(Equipe equipe) {
        for (Equipe actual : this.list_equipes) {
            if (actual.get_id().equals(equipe.get_id())) return true;
        }
        return false;
    }

    public boolean has_arbitre(Arbitre arbitre) {
        for (Arbitre actual : this.list_arbitres) {
            if (actual.get_id().equals(arbitre.get_id())) return true;
        }
        return false;
    }

    public ArrayList<Match> get_list_match_of_equipe(Equipe equipe) {
        ArrayList<Match> list_match_of_equipe = new ArrayList<>();
        for (Match match : this.list_matchs) {
            if (match.has_equipe(equipe)) list_match_of_equipe.add(match);
        }
        return list_match_of_equipe;
    }

    public ArrayList<Match> get_list_match_of_arbitre(Arbitre arbitre) {
        ArrayList<Match> list_match_of_arbitre = new ArrayList<>();
        for (Match match : this.list_matchs) {
            if (match.has_arbitre(arbitre)) list_match_of_arbitre.add(match);
        }
        return list_match_of_arbitre;
    }

    public ArrayList<Match> get_list_match_par_tour(int tour) {
        ArrayList<Match> list_match_of_tour = new ArrayList<>();
        for (Match match : this.list_matchs) {
            if (match.get_tour() == tour) list_match_of_tour.add(match);
        }
        System.out.println(list_match_of_tour.size());
        return list_match_of_tour;
    }
}