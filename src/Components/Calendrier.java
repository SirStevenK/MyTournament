package Components;

import Classes.Equipe;
import Classes.Match;
import Controllers.CompetitionController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Calendrier extends ScrollPane {
    private ArrayList<Match> listMatch;
    private GridPane calendrierGrid;
    private CompetitionController controller;

    public Calendrier(ArrayList<Match> ListMatch, CompetitionController Controller) {
        listMatch = ListMatch;
        controller = Controller;

        calendrierGrid = new GridPane();

        this.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                calendrierGrid.setPrefWidth(bounds.getWidth());
                calendrierGrid.setPrefHeight(bounds.getHeight());
            }
        });
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        calendrierGrid.setPadding(new Insets(20));
        calendrierGrid.setHgap(15);
        calendrierGrid.setVgap(15);
//        int i = 1;

        showMatch();
        this.setContent(calendrierGrid);
    }

    public void showMatch() {
        calendrierGrid.getChildren().clear();
        Label nomE1 = new Label("Equipe 1");
        calendrierGrid.add(nomE1, 0, 0);
        Label res = new Label("Résultat");
        calendrierGrid.add(res, 1, 0);
        Label nomE2 = new Label("Equipe 2");
        calendrierGrid.add(nomE2, 2, 0);
        int i = 1;
        for (Match match : listMatch) {
            Equipe equipe1 = match.get_equipe1();
            Equipe equipe2 = match.get_equipe2();
            nomE1 = new Label(equipe1.get_nom());
            calendrierGrid.add(nomE1, 0, i);
            Label versus = new Label("VS");
            GridPane.setHalignment(versus, HPos.CENTER);
            calendrierGrid.add(versus, 1, i);
            nomE2 = new Label(equipe2.get_nom());
            calendrierGrid.add(nomE2, 2, i);
            GridPane.setHalignment(nomE2, HPos.RIGHT);
            Button voirPlusButton = new Button("Voir Plus");
            voirPlusButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    controller.showMatch(match);
                }
            });
            calendrierGrid.add(voirPlusButton, 3, i);
            i++;
        }
    }

    public void updateListMatch(ArrayList<Match> ListMatch) {
        listMatch = ListMatch;
        showMatch();
    }


}
