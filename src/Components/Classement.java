package Components;

import Classes.Equipe;
import Classes.Match;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Classement extends ScrollPane {

    public Classement(ArrayList<Equipe> listEquipe) {
        GridPane classementGrid = new GridPane();

        this.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                classementGrid.setPrefWidth(bounds.getWidth());
                classementGrid.setPrefHeight(bounds.getHeight());
            }
        });
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        classementGrid.setPadding(new Insets(20));
        classementGrid.setHgap(25);
        classementGrid.setVgap(15);

        Label index = new Label("#");
        classementGrid.add(index, 0, 0);
        Label nom = new Label("Nom de l'équipe");
        classementGrid.add(nom, 1, 0);
        Label matchsJoues = new Label("MJ");
        classementGrid.add(matchsJoues, 2, 0);
        Label victoire = new Label("V");
        classementGrid.add(victoire, 3, 0);
        Label nul = new Label("N");
        classementGrid.add(nul, 4, 0);
        Label defaite = new Label("D");
        classementGrid.add(defaite, 5, 0);
        Label butsPour = new Label("BP");
        classementGrid.add(butsPour, 6, 0);
        Label butsContre = new Label("BC");
        classementGrid.add(butsContre, 7, 0);
        Label points = new Label("PTS");
        classementGrid.add(points, 8, 0);
        int i = 1;


        for (Equipe equipe : listEquipe) {
            index = new Label(String.valueOf(i));
            classementGrid.add(index, 0, i);
            nom = new Label(equipe.get_nom());
            classementGrid.add(nom, 1, i);
            matchsJoues = new Label(String.valueOf(equipe.get_nombre_joues()));
            classementGrid.add(matchsJoues, 2, i);
            victoire = new Label(String.valueOf(equipe.get_nombre_victoire()));
            classementGrid.add(victoire, 3, i);
            nul = new Label(String.valueOf(equipe.get_nombre_nul()));
            classementGrid.add(nul, 4, i);
            defaite = new Label(String.valueOf(equipe.get_nombre_defaite()));
            classementGrid.add(defaite, 5, i);
            butsPour = new Label(String.valueOf(equipe.get_nombre_but()));
            classementGrid.add(butsPour, 6, i);
            butsContre = new Label(String.valueOf(equipe.get_nombre_but_pris()));
            classementGrid.add(butsContre, 7, i);

            int equipePoints = equipe.get_nombre_victoire() * 3 + equipe.get_nombre_nul();
            points = new Label(String.valueOf(equipePoints));
            classementGrid.add(points, 8, i);
//            HBox lineEquipe = new HBox(10);
//            Label nom = new Label(equipe.get_nom());
//            Label victoire = new Label(String.valueOf(0));
//            ajouter_node(lineEquipe, nom);
//            ajouter_node(lineEquipe, victoire);
//
//            ajouter_node(classementGrid, lineEquipe);
//            classementGrid.add(lineEquipe, 0, i);
//
            i++;
        }

        this.setContent(classementGrid);
    }



    private void ajouter_node(GridPane host, Node node) {
        host.getChildren().add(node);
    }

    private void ajouter_node(HBox host, Node node) {
        host.getChildren().add(node);
    }


}
