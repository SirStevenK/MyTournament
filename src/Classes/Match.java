package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Match implements Serializable  {
    private int tour;
    private Arbitre arbitre;
    private Equipe equipe1;
    private Equipe equipe2;
    private boolean fait;
    private ArrayList<Evenement> list_events;

    Match(int tour, Arbitre arbitre, Equipe equipe1, Equipe equipe2) {
        this.tour = tour;
        this.arbitre = arbitre;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.fait = false;
        this.list_events = new ArrayList<Evenement>();
    }

    public int get_tour() {
        return this.tour;
    }

    public Arbitre get_arbitre() {
        return this.arbitre;
    }

    public Equipe get_equipe1() {
        return this.equipe1;
    }

    public void set_equipe1(Equipe equipe) {
        this.equipe1 = equipe;
    }

    public Equipe get_equipe2() {
        return this.equipe2;
    }

    public void set_equipe2(Equipe equipe) {
        this.equipe2 = equipe;
    }

    public void add_evenement(Evenement event) {
        this.list_events.add(event);
    }

    public void set_arbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public boolean get_fait() {
        return this.fait;
    }

    public void set_fait(boolean fait) {
        this.fait = fait;
    }

    public int[] get_score() {
        int score[] = {0, 0};
        if (this.fait) {
            for (Evenement event : this.list_events) {
                if (event.get_type() == TypeEvent.But) {
                    if (this.equipe1.has_joueur(event.get_joueur())) score[0]++;
                    else if (this.equipe2.has_joueur(event.get_joueur())) score[1]++;
                }
            }
        }
        return score;
    }

    public boolean has_equipe(Equipe equipe) {
        if (this.equipe1.get_id().equals(equipe.get_id()) || this.equipe2.get_id().equals(equipe.get_id())) return true;
        else return false;
    }

    public boolean has_arbitre(Arbitre arbitre) {
        if (this.arbitre == arbitre) return true;
        else return false;
    }

    public int get_vainqueur() {
        if (this.fait) {
            int score[] = this.get_score();
            if (score[0] == score[1]) return 0;
            else if (score[0] > score[1]) return 1;
            else return 2;
        }
        else return -1;
    }

    public Resultat get_team_result(Equipe equipe) {
        int result = get_vainqueur();
        if (this.equipe1.get_id().equals(equipe.get_id())) {
            if (result == 1) return Resultat.Victoire;
            else if (result == 2) return Resultat.Defaite;
            else if (result == 0) return Resultat.Nul;
            else return Resultat.NonJoue;
        }
        else if (this.equipe2.get_id().equals(equipe.get_id())) {
            if (result == 2) return Resultat.Victoire;
            else if (result == 1) return Resultat.Defaite;
            else if (result == 0) return Resultat.Nul;
            else return Resultat.NonJoue;
        }
        return null;
    }

    public int get_nb_carton_jaune_of_equipe(Equipe equipe) {
        int nombre_carton_jaune = 0;
        if (this.fait) {
            for (Evenement event : this.list_events) {
                if (event.get_type() == TypeEvent.CartonJaune) {
                    if (equipe.has_joueur(event.get_joueur())) nombre_carton_jaune++;
                }
            }
        }
        return nombre_carton_jaune;
    }

    public int get_nb_carton_rouge_of_equipe(Equipe equipe) {
        int nombre_carton_rouge = 0;
        if (this.fait) {
            for (Evenement event : this.list_events) {
                if (event.get_type() == TypeEvent.CartonRouge) {
                    if (equipe.has_joueur(event.get_joueur())) nombre_carton_rouge++;
                }
            }
        }
        return nombre_carton_rouge;
    }

    public int get_nb_carton_jaune() {
        return this.get_nb_carton_jaune_of_equipe(this.equipe1) + this.get_nb_carton_jaune_of_equipe(this.equipe2);
    }

    public int get_nb_carton_rouge() {
        return this.get_nb_carton_rouge_of_equipe(this.equipe1) + this.get_nb_carton_rouge_of_equipe(this.equipe2);
    }

    public int get_nb_but_of_equipe(Equipe equipe) {
        int score[] = get_score();
        if (equipe1.get_id().equals(equipe.get_id())) return score[0];
        else if (equipe2.get_id().equals(equipe.get_id())) return score[1];
        else return 0;
    }

    public int get_nb_but_pris_of_equipe(Equipe equipe) {
        int score[] = get_score();
        if (equipe1.get_id().equals(equipe.get_id())) return score[1];
        else if (equipe2.get_id().equals(equipe.get_id())) return score[0];
        else return 0;
    }

    @Override
    public String toString() {
        return equipe1.get_nom() + " contre " + equipe2.get_nom();
    }
}