package Controllers;

import Classes.*;
import Components.NumberTextField;
import Models.CompetitionModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateCompetitionController {
    @FXML private ScrollPane scrollCenterCreateTeam;
    @FXML private FlowPane centerCreateTeam;

    @FXML private TextField nameEquipe;

    @FXML private TextField nameEntraineur;
    @FXML private TextField prenomEntraineur;
    @FXML private NumberTextField ageEntraineur;
    @FXML private NumberTextField tailleEntraineur;
    @FXML private ChoiceBox nationEntraineur;

    @FXML private TextField nameJoueur;
    @FXML private TextField prenomJoueur;
    @FXML private NumberTextField ageJoueur;
    @FXML private NumberTextField tailleJoueur;
    @FXML private ChoiceBox nationJoueur;
    @FXML private ChoiceBox posteJoueur;
    @FXML private HBox changeTeam;

    @FXML private TextField nameArbitre;
    @FXML private TextField prenomArbitre;
    @FXML private NumberTextField ageArbitre;
    @FXML private NumberTextField tailleArbitre;
    @FXML private ChoiceBox nationArbitre;

    @FXML private ScrollPane listTeams;
    @FXML private FlowPane contentListTeams;
    @FXML private Button buttonSupprimerJoueur;

    @FXML private ScrollPane listArbitres;
    @FXML private FlowPane contentListArbitres;
    @FXML private Button buttonSupprimerArbitre;

    @FXML private VBox changeArbitre;


    private Competition newCompetition;
    CompetitionModel model;
    Competition competition;
    String typeChange;  // Equipe ou Arbitre
    String typeAction; // Créer ou Voir Liste
    Equipe selectedTeam;
    Arbitre selectedArbitre;
    Joueur selectedJoueur;

    @FXML
    public void initialize() throws IOException {
        scrollCenterCreateTeam.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                centerCreateTeam.setPrefWidth(bounds.getWidth());
                centerCreateTeam.setPrefHeight(bounds.getHeight());
            }
        });

        listTeams.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                contentListTeams.setPrefWidth(bounds.getWidth());
                contentListTeams.setPrefHeight(bounds.getHeight());
            }
        });

        listArbitres.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                contentListArbitres.setPrefWidth(bounds.getWidth());
                contentListArbitres.setPrefHeight(bounds.getHeight());
            }
        });

        model = new CompetitionModel();
        typeChange = "Equipe";
        typeAction = "Creer";
        competition = model.getCompetition();

        show();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/mainscreen.fxml"));
        primaryStage.setScene(new Scene(root));
    }

    private void show() {
        hideContent();
        if (typeChange.equals("Equipe")) {
            if (typeAction.equals("Creer")) {
                changeTeam.setVisible(true);
                buttonSupprimerJoueur.setVisible(false);

                centerCreateTeam.getChildren().clear();
                if (selectedTeam == null) {
                    selectedTeam = new Equipe("");
                    selectedTeam.set_entraineur(new Entraineur("", "", 18, 100, "France"));
                }
                if (selectedJoueur == null) {
                    selectedJoueur = new Joueur("", "", 18, 100, "France", Poste.Gardien);
                }
                else {
                    if (selectedTeam.has_joueur(selectedJoueur)) buttonSupprimerJoueur.setVisible(true);
                }

                nameEquipe.setText(selectedTeam.get_nom());

                Entraineur entraineurTeam = selectedTeam.get_entraineur();
                nameEntraineur.setText(entraineurTeam.get_nom());
                prenomEntraineur.setText(entraineurTeam.get_prenom());
                ageEntraineur.setText(String.valueOf(entraineurTeam.get_age()));
                tailleEntraineur.setText(String.valueOf(entraineurTeam.get_taille()));
                nationEntraineur.setValue(String.valueOf(entraineurTeam.get_nation()));

                nameJoueur.setText(selectedJoueur.get_nom());
                prenomJoueur.setText(selectedJoueur.get_prenom());
                ageJoueur.setText(String.valueOf(selectedJoueur.get_age()));
                tailleJoueur.setText(String.valueOf(selectedJoueur.get_taille()));
                nationJoueur.setValue(selectedJoueur.get_nation());
                posteJoueur.setValue(String.valueOf(selectedJoueur.get_poste()));

                ArrayList<Joueur> listJoueurs = selectedTeam.get_joueurs();
                for (Joueur joueur : listJoueurs) {
                    VBox infoJoueur = new VBox();
                    if (selectedJoueur.get_id().equals(joueur.get_id())) infoJoueur.getStyleClass().add("bg-orange");
                    else infoJoueur.getStyleClass().add("bg-blue");
                    infoJoueur.getStyleClass().add("box-bordered-border");
                    Label infoJoueurNom = new Label(joueur.get_prenom() + " " + joueur.get_nom());
                    infoJoueurNom.getStyleClass().add("font-white");
                    infoJoueur.getChildren().add(infoJoueurNom);
                    Label infoJoueurAge = new Label(String.valueOf(joueur.get_age())  + " ans");
                    infoJoueur.getChildren().add(infoJoueurAge);
                    infoJoueurAge.getStyleClass().add("font-white");
                    Label infoJoueurPoste = new Label(String.valueOf(joueur.get_poste()));
                    infoJoueur.getChildren().add(infoJoueurPoste);
                    infoJoueurPoste.getStyleClass().add("font-white");

                    Button modifierJoueur = new Button("Modifier");
                    modifierJoueur.setOnAction(event -> {
                        selectedJoueur = joueur;
                        show();
                    });
                    infoJoueur.getChildren().add(modifierJoueur);

                    centerCreateTeam.getChildren().add(infoJoueur);
                }
            }
            else if (typeAction.equals("Liste")) {
                listTeams.setVisible(true);

                contentListTeams.getChildren().clear();

                ArrayList<Equipe> listEquipes = competition.get_list_equipes();
                for (Equipe equipe : listEquipes) {
                    VBox infoEquipe = new VBox();
                    infoEquipe.getStyleClass().add("bg-blue");
                    infoEquipe.getStyleClass().add("box-bordered-border");
                    Label infoEquipeNom = new Label(equipe.get_nom());
                    infoEquipeNom.getStyleClass().add("font-white");
                    infoEquipe.getChildren().add(infoEquipeNom);

                    Entraineur entraineur = equipe.get_entraineur();

                    Label infoEquipeEntraineur = new Label("Entrainée par " + entraineur.get_prenom() + " " + entraineur.get_nom() );
                    infoEquipeEntraineur.getStyleClass().add("font-white");
                    infoEquipe.getChildren().add(infoEquipeEntraineur);
                    Button modifierEquipe = new Button("Modifier");
                    modifierEquipe.setOnAction(event -> {
                        selectedTeam = equipe;
                        typeAction = "Creer";
                        show();
                    });
                    infoEquipe.getChildren().add(modifierEquipe);
                    contentListTeams.getChildren().add(infoEquipe);
                }
            }
        }
        if (typeChange.equals("Arbitre")) {
            if (typeAction.equals("Creer")) {
                changeArbitre.setVisible(true);
                buttonSupprimerArbitre.setVisible(false);

                if (selectedArbitre == null) {
                    selectedArbitre = new Arbitre("", "", 18, 100, "France");
                }
                else {
                    if (competition.has_arbitre(selectedArbitre)) buttonSupprimerArbitre.setVisible(true);
                }

                nameArbitre.setText(selectedArbitre.get_nom());
                prenomArbitre.setText(selectedArbitre.get_prenom());
                ageArbitre.setText(String.valueOf(selectedArbitre.get_age()));
                tailleArbitre.setText(String.valueOf(selectedArbitre.get_taille()));
                nationArbitre.setValue(selectedArbitre.get_nation());
            }
            else if (typeAction.equals("Liste")) {
                listArbitres.setVisible(true);
                contentListArbitres.getChildren().clear();

                ArrayList<Arbitre> listArbitres = competition.get_list_arbitres();
                for (Arbitre arbitre : listArbitres) {
                    VBox infoArbitre = new VBox();
                    infoArbitre.getStyleClass().add("bg-blue");
                    infoArbitre.getStyleClass().add("box-bordered-border");
                    Label infoArbitreNom = new Label(arbitre.get_nom());
                    infoArbitreNom.getStyleClass().add("font-white");
                    infoArbitre.getChildren().add(infoArbitreNom);

                    Button modifierArbitre = new Button("Modifier");
                    modifierArbitre.setOnAction(event -> {
                        selectedArbitre = arbitre;
                        typeAction = "Creer";
                        show();
                    });
                    infoArbitre.getChildren().add(modifierArbitre);
                    contentListArbitres.getChildren().add(infoArbitre);
                }
            }
        }
    }

    @FXML
    private void arbitreChange() {
        typeChange = "Arbitre";
        creerAction();
    }

    @FXML
    private void equipeChange() {
        typeChange = "Equipe";
        creerAction();
    }

    @FXML
    private void creerAction() {
        selectedTeam = null;
        selectedArbitre = null;
        typeAction = "Creer";
        show();
    }

    @FXML
    private void listeAction() {
        typeAction = "Liste";
        show();
    }

    private void hideContent() {
        changeTeam.setVisible(false);
        listTeams.setVisible(false);
        changeArbitre.setVisible(false);
        listArbitres.setVisible(false);

    }

    @FXML
    private void addPlayer() {
        selectedJoueur.set_nom(nameJoueur.getText());
        selectedJoueur.set_prenom(prenomJoueur.getText());
        selectedJoueur.set_age(Integer.valueOf(ageJoueur.getText()));
        selectedJoueur.set_taille(Integer.valueOf(tailleJoueur.getText()));
        selectedJoueur.set_nation((String) nationJoueur.getValue());
        String poste = (String) posteJoueur.getValue();
        System.out.println(poste);


        if (poste.equals("Gardien")) selectedJoueur.set_poste(Poste.Gardien);
        else if (poste.equals("Defenseur")) selectedJoueur.set_poste(Poste.Defenseur);
        else if (poste.equals("Milieu")) selectedJoueur.set_poste(Poste.Milieu);
        else if (poste.equals("Attaquant")) selectedJoueur.set_poste(Poste.Attaquant);


        if (!selectedTeam.has_joueur(selectedJoueur)) selectedTeam.ajouter_joueur(selectedJoueur);
        selectedJoueur = null;
        show();
    }

    @FXML
    private void removePlayer() {
        selectedTeam.supprimer_joueur(selectedJoueur);
        selectedJoueur = null;
        show();
    }

    @FXML
    private void addArbitre() {
        selectedArbitre.set_nom(nameArbitre.getText());
        selectedArbitre.set_prenom(prenomArbitre.getText());
        selectedArbitre.set_age(Integer.valueOf(ageArbitre.getText()));
        selectedArbitre.set_taille(Integer.valueOf(tailleArbitre.getText()));
        selectedArbitre.set_nation((String) nationArbitre.getValue());

        if (!competition.has_arbitre(selectedArbitre)) competition.add_arbitre(selectedArbitre);
        typeAction = "Liste";
        selectedArbitre = null;
        show();
    }

    @FXML
    private void removeArbitre() {
        competition.supprimer_arbitre(selectedArbitre);
        selectedArbitre = null;
        show();
    }

    @FXML
    private void enregistrerEquipe() {
        selectedTeam.set_nom(nameEquipe.getText());

        Entraineur entraineur = selectedTeam.get_entraineur();

        entraineur.set_nom(nameEntraineur.getText());
        entraineur.set_prenom(prenomEntraineur.getText());
        entraineur.set_age(Integer.valueOf(ageEntraineur.getText()));
        entraineur.set_taille(Integer.valueOf(tailleEntraineur.getText()));
        entraineur.set_nation((String) nationEntraineur.getValue());

        if (!competition.has_equipe(selectedTeam)) competition.add_equipe(selectedTeam);
        typeAction = "Liste";
        selectedTeam = null;
        show();
    }

    @FXML
    private void confirmerCompetition(ActionEvent event) throws IOException {
        if (model.getTypeCompetition().equals("class Classes.Championnat")) {
            Championnat championnat = (Championnat) competition;
            championnat.generate_matchs();
        }
        model.saveCompetition();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/competition.fxml"));
        primaryStage.setScene(new Scene(root));
    }

}
