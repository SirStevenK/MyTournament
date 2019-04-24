package Classes;

import java.io.Serializable;

public class Evenement implements Serializable {

    private TypeEvent event;
    private Joueur joueur;
    private int minute;

    Evenement(TypeEvent event, Joueur joueur, int minute) {
        this.event = event;
        this.joueur = joueur;
        this.minute = minute;
    }

    public TypeEvent get_type() {
        return this.event;
    }

    public Joueur get_joueur() {
        return this.joueur;
    }

    public int get_minute() {
        return this.minute;
    }

}
