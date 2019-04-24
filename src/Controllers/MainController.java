package Controllers;

import Models.CompetitionModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    CompetitionModel model;

    @FXML
    public void initialize() throws IOException {
        model = new CompetitionModel();
    }

    @FXML
    private void createChampionnat(ActionEvent event) throws Exception {
        model.createCompetition("C");
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/createCompetition.fxml"));
        primaryStage.setScene(new Scene(root));
    }

    @FXML
    private void openCompetition(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/competition.fxml"));
        primaryStage.setScene(new Scene(root));
    }
}
