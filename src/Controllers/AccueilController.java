package Controllers;

import Classes.Competition;
import Classes.Match;
import Models.CompetitionModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class AccueilController {
    CompetitionModel model;
    Competition competition;

    @FXML
    public void initialize() throws IOException {
        model = new CompetitionModel();
        competition = model.getCompetition();

        for (Match match : competition.get_list_matchs()) {
            System.out.println(match.toString());
        }

    }

}
