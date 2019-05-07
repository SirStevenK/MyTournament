package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Championnat extends Competition implements Serializable {
    public Championnat(String nom) {
        super(nom);
    }

    public ArrayList<Equipe> get_classement() {
        ArrayList<Equipe> classement = new ArrayList<>();
        return classement;
    }

    public void generate_matchs() {
        int nombre_equipe = this.list_equipes.size();
        this.nombre_tour = nombre_equipe - 1;
        ArrayList<Match> list_matchs = new ArrayList<>();
        int round = 0;

        for (int i = 0; i < nombre_equipe - 1; i++) {
            ArrayList<Integer> tab = new ArrayList<>();
            tab.add(0);
            for (int j = i + 1; j < i + nombre_equipe; j++) {
                tab.add(j % (nombre_equipe - 1) + 1);
            }
            for (int k = 0; k < nombre_equipe / 2; k++) {
                tab.add(k % (nombre_equipe - 1) + 1);
                Match match;
                if (tab.get(k) == 0 && round % 2 == 1) {
                    match = new Match(round, null, this.list_equipes.get(tab.get(k)), this.list_equipes.get(tab.get(nombre_equipe - 1 - k)));
                }
                else {
                    match = new Match(round, null, this.list_equipes.get(tab.get(nombre_equipe - 1 - k)), this.list_equipes.get(tab.get(k)));
                }
                this.list_matchs.add(match);
            }
            round++;
        }
    }
}