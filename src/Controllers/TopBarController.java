package Controllers;

import Classes.Competition;
import Models.CompetitionModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarController {

    @FXML private Button Competition;
    @FXML private Button Equipes;
    @FXML private Button Joueurs;
    @FXML private Button Arbitres;
    @FXML private Button Enregistrer;
    @FXML private Button Menu;


    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/mainscreen.fxml"));
        primaryStage.setScene(new Scene(root));
    }

}
