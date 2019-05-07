package Controllers;

import Classes.*;
import Components.Calendrier;
import Models.CompetitionModel;
import Components.Classement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CompetitionController {
    CompetitionModel model;
    Competition competition;
    String state;
    int tour;
    Calendrier calendrier;
    Match match;
    @FXML private VBox classementBox;
    @FXML private VBox calendrierBox;
    @FXML private VBox matchBox;
    @FXML private Label journee;
    @FXML private Label nomE1;
    @FXML private Label nomE2;
    @FXML private Label scoreLabel;
    @FXML private ToggleGroup Event;
    @FXML private ChoiceBox equipeEvent;
    @FXML private ChoiceBox joueurEvent;

    @FXML
    public void initialize() throws IOException {
        model = new CompetitionModel();
        competition = model.getCompetition();

        Classement classement = new Classement(competition.get_list_equipes());
        classementBox.getChildren().add(classement);

        tour = 0;
        updateTourLabelText();
        calendrier = new Calendrier(competition.get_list_match_par_tour(tour), this);
        calendrierBox.getChildren().add(calendrier);

        equipeEvent.getSelectionModel().selectedIndexProperty().addListener((observableValue, prev, current) -> {
            if ((int) current != -1) changeTeamEvent((String) equipeEvent.getItems().get((Integer) current));
        });

        state = "Calendrier";
        show();
    }

    @FXML
    public void classementState() {
        state = "Classement";
        show();
    }

    @FXML
    public void calendrierState() {
        state = "Calendrier";
        show();
    }

    @FXML
    public void prevTour() {
        if (tour - 1 >= 0) {
            tour--;
            updateTourLabelText();
            calendrier.updateListMatch(competition.get_list_match_par_tour(tour));
        }
    }

    @FXML
    public void showMatch(Match match) {
        this.match = match;
        state = "Match";
        show();
    }

    @FXML
    public void nextTour() {
        if (tour + 1 < competition.get_nombre_tour()) {
            tour++;
            updateTourLabelText();
            calendrier.updateListMatch(competition.get_list_match_par_tour(tour));
        }
    }

    private void updateTourLabelText() {
        journee.setText("Journée n°" + String.valueOf(tour+1));
    }

    private void updateMatchBox() {
        Equipe equipe1 = match.get_equipe1();
        Equipe equipe2 = match.get_equipe2();
        nomE1.setText(equipe1.get_nom());
        nomE2.setText(equipe2.get_nom());
        int[] Score = match.get_score();
        String score = String.valueOf(Score[0]) + " - " + String.valueOf(Score[1]);
        scoreLabel.setText(score);

//        int nombre_item_to_remove = equipeEvent.getItems().size();
//        System.out.println(nombre_item_to_remove);

        equipeEvent.getItems().clear();
        equipeEvent.getItems().addAll(equipe1.get_nom(), equipe2.get_nom());
        equipeEvent.setValue(equipe1.get_nom());

        changeTeamEvent(equipe1.get_nom());
    }

    @FXML
    private void changeTeamEvent(String equipeName) {
        Equipe equipe1 = match.get_equipe1();
        Equipe equipe2 = match.get_equipe2();
        ArrayList<String> listName = new ArrayList<>();
        if (equipeName == equipe1.get_nom()) {
            for (Joueur joueur : equipe1.get_joueurs()) {
                listName.add(joueur.get_nom());
            }
        }
        else {
            for (Joueur joueur : equipe2.get_joueurs()) {
                listName.add(joueur.get_nom());
            }
        }
        int nombre_item_to_remove = joueurEvent.getItems().size();

        joueurEvent.getItems().clear();
        joueurEvent.getItems().addAll(listName);
        joueurEvent.setValue(listName.get(0));

    }

    @FXML
    private void test() {
        RadioButton selectedRadioButton = (RadioButton) Event.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        System.out.println(toogleGroupValue);
    }
    private void show() {
        hideAll();
        if (state.equals("Classement")) classementBox.setVisible(true);
        else if (state.equals("Calendrier")) calendrierBox.setVisible(true);
        else if (state.equals("Match")) {
            updateMatchBox();
            matchBox.setVisible(true);
        }
    }

    private void hideAll() {
        classementBox.setVisible(false);
        calendrierBox.setVisible(false);
        matchBox.setVisible(false);
    }

}
