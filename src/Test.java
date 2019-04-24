

import Classes.*;
import Components.NumberTextField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Test extends Application {

    Stage window;
    Equipe selectedTeam;

    public static void main(String[] args) {
        Application.launch(Test.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("MyTournament");
        window.getIcons().add(new Image(getClass().getResourceAsStream("logoMT.png")));

        showFirstScreen();
        window.show();
    }

    public void showFirstScreen() {
        VBox contentMenu = new VBox(8);
        Scene scene = new Scene(contentMenu, 1024, 768);
        contentMenu.setId("pane");
        scene.getStylesheets().addAll(this.getClass().getResource("Views/Stylesheets/style.css").toExternalForm());

        int sizeButton = 180;
        Button createChampionnat = new Button();
        Button createTournament = new Button();
        Button openCompetition = new Button();
        createChampionnat.setText("Créer un Championnat");
        createChampionnat.setPrefWidth(sizeButton);
        createTournament.setText("Créer un Tournoi");
        createTournament.setPrefWidth(sizeButton);
        openCompetition.setText("Ouvrir une Compétition");
        openCompetition.setPrefWidth(sizeButton);

        createChampionnat.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                showCreateCompetition();
            }
        });
        contentMenu.setAlignment(Pos.CENTER);
        contentMenu.getChildren().add(createChampionnat);
        contentMenu.getChildren().add(createTournament);
        contentMenu.getChildren().add(openCompetition);
        window.setScene(scene);
    }

    public void showCreateCompetition() {

        if (selectedTeam == null) {
            selectedTeam = new Equipe("");
        }

        VBox contentMenu = new VBox(10);
        Scene scene = new Scene(contentMenu, 1024, 768);
        contentMenu.setId("paneplus");
        contentMenu.setAlignment(Pos.CENTER);
        scene.getStylesheets().addAll(this.getClass().getResource("Views/Stylesheets/style.css").toExternalForm());

        HBox menu = new HBox(10);
        menu.getStyleClass().add("box-bordered-border");
        Button equipeButton = new Button();
        equipeButton.setText("Equipes");
        equipeButton.setPrefWidth(150);
        Button arbitreButton = new Button();
        arbitreButton.setText("Arbitres");
        arbitreButton.setPrefWidth(150);
        Button backButton = new Button();
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                showFirstScreen();
            }
        });
        backButton.setText("Retour au menu");
        backButton.setPrefWidth(150);
        menu.getChildren().add(equipeButton);
        menu.getChildren().add(arbitreButton);
        menu.getChildren().add(backButton);

        HBox equipe = new HBox(10);
        equipe.getStyleClass().add("box-bordered-border");
        Button createButton = new Button();
        Button listButton = new Button();
        createButton.setText("Créer");
        createButton.setPrefWidth(150);
        listButton.setText("Liste");
        listButton.setPrefWidth(150);
        equipe.getChildren().add(createButton);
        equipe.getChildren().add(listButton);


        StackPane changeBox = new StackPane();
        changeBox.getStyleClass().add("box-bordered-border");
        changeBox.getStyleClass().add("box-full");

        HBox createTeam = new HBox(20);
        VBox leftCreateTeam = new VBox(24);

        StackPane teamNameBox = new StackPane();
        teamNameBox.getStyleClass().add("box-bordered-border");
        Label teamNameLabel = new Label("Nom de l'équipe");
        teamNameLabel.getStyleClass().add("box-bordered-title");
        TextField teamName = new TextField (selectedTeam.get_nom());
        teamName.setPromptText("Nom de l'équipe");

        leftCreateTeam.getChildren().add(teamNameBox);
        teamNameBox.getChildren().add(teamNameLabel);
        teamNameBox.getChildren().add(teamName);

        // --------

        VBox entraineurBox = new VBox(12);
        entraineurBox.getStyleClass().add("low-box-bordered-border");

        Label entraineurLabel = new Label("Entraineur");
        entraineurLabel.getStyleClass().add("low-box-bordered-title");
        entraineurBox.getChildren().add(entraineurLabel);

        StackPane entraineurNomBox = new StackPane();
        entraineurNomBox.getStyleClass().add("box-bordered-border");
        Label entraineurNomLabel = new Label("Nom de l'entraineur");
        entraineurNomLabel.getStyleClass().add("box-bordered-title");
        TextField entraineurNom = new TextField ();
        entraineurNom.setPromptText("Nom de l'entraineur");
        entraineurBox.getChildren().add(entraineurNomBox);
        entraineurNomBox.getChildren().add(entraineurNomLabel);
        entraineurNomBox.getChildren().add(entraineurNom);

        StackPane entraineurPrenomBox = new StackPane();
        entraineurPrenomBox.getStyleClass().add("box-bordered-border");
        Label entraineurPrenomLabel = new Label("Prénom de l'entraineur");
        entraineurPrenomLabel.getStyleClass().add("box-bordered-title");
        TextField entraineurPrenom = new TextField ();
        entraineurPrenom.setPromptText("Prénom de l'entraineur");
        entraineurBox.getChildren().add(entraineurPrenomBox);
        entraineurPrenomBox.getChildren().add(entraineurPrenomLabel);
        entraineurPrenomBox.getChildren().add(entraineurPrenom);

        StackPane entraineurAgeBox = new StackPane();
        entraineurAgeBox.getStyleClass().add("box-bordered-border");
        Label entraineurAgeLabel = new Label("Age de l'entraineur");
        entraineurAgeLabel.getStyleClass().add("box-bordered-title");
        TextField entraineurAge = new NumberTextField();
        entraineurAge.setPromptText("Age");
        entraineurBox.getChildren().add(entraineurAgeBox);
        entraineurAgeBox.getChildren().add(entraineurAgeLabel);
        entraineurAgeBox.getChildren().add(entraineurAge);

        StackPane entraineurTailleBox = new StackPane();
        entraineurTailleBox.getStyleClass().add("box-bordered-border");
        Label entraineurTailleLabel = new Label("Taille (cm) de l'entraineur");
        entraineurTailleLabel.getStyleClass().add("box-bordered-title");
        TextField entraineurTaille = new NumberTextField();
        entraineurTaille.setPromptText("Taille");
        entraineurBox.getChildren().add(entraineurTailleBox);
        entraineurTailleBox.getChildren().add(entraineurTailleLabel);
        entraineurTailleBox.getChildren().add(entraineurTaille);

        StackPane entraineurNationBox = new StackPane();
        entraineurNationBox.getStyleClass().add("box-bordered-border");
        Label entraineurNationLabel = new Label("Nation de l'entraineur");
        entraineurNationLabel.getStyleClass().add("box-bordered-title");
        TextField entraineurNation = new NumberTextField();
        entraineurNation.setPromptText("Nation de l'entraineur");
        entraineurBox.getChildren().add(entraineurNationBox);
        entraineurNationBox.getChildren().add(entraineurNationLabel);
        entraineurNationBox.getChildren().add(entraineurNation);

        leftCreateTeam.getChildren().add(entraineurBox);

        FlowPane centerCreateTeam = new FlowPane();
        centerCreateTeam.setPadding(new Insets(5, 5, 5, 5));
        ScrollPane scrollCenterCreateTeam = new ScrollPane();
        centerCreateTeam.getStyleClass().add("bg-gray");
        scrollCenterCreateTeam.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Horizontal scrollCenterCreateTeam bar
        scrollCenterCreateTeam.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scrollCenterCreateTeam bar
        scrollCenterCreateTeam.setContent(centerCreateTeam);
        scrollCenterCreateTeam.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                centerCreateTeam.setPrefWidth(bounds.getWidth());
                centerCreateTeam.setPrefHeight(bounds.getHeight());
            }
        });
        centerCreateTeam.setPrefWidth(500);
        centerCreateTeam.setVgap(8);
        centerCreateTeam.setHgap(8);

        ArrayList<Joueur> listJoueurs = selectedTeam.get_joueurs();
        for (Joueur joueur : listJoueurs) {
            VBox infoJoueur = new VBox();
            infoJoueur.getStyleClass().add("bg-blue");
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
            centerCreateTeam.getChildren().add(infoJoueur);
        }



        VBox rightCreateTeam = new VBox();

        VBox joueurBox = new VBox(12);
        joueurBox.getStyleClass().add("low-box-bordered-border");

        Label joueurLabel = new Label("Joueur");
        joueurLabel.getStyleClass().add("low-box-bordered-title");
        joueurBox.getChildren().add(joueurLabel);

        StackPane joueurNomBox = new StackPane();
        joueurNomBox.getStyleClass().add("box-bordered-border");
        Label joueurNomLabel = new Label("Nom du joueur");
        joueurNomLabel.getStyleClass().add("box-bordered-title");
        TextField joueurNom = new TextField ();
        joueurNom.setPromptText("Nom du joueur");
        joueurBox.getChildren().add(joueurNomBox);
        joueurNomBox.getChildren().add(joueurNomLabel);
        joueurNomBox.getChildren().add(joueurNom);

        StackPane joueurPrenomBox = new StackPane();
        joueurPrenomBox.getStyleClass().add("box-bordered-border");
        Label joueurPrenomLabel = new Label("Prénom du joueur");
        joueurPrenomLabel.getStyleClass().add("box-bordered-title");
        TextField joueurPrenom = new TextField ();
        joueurPrenom.setPromptText("Prénom du joueur");
        joueurBox.getChildren().add(joueurPrenomBox);
        joueurPrenomBox.getChildren().add(joueurPrenomLabel);
        joueurPrenomBox.getChildren().add(joueurPrenom);

        StackPane joueurAgeBox = new StackPane();
        joueurAgeBox.getStyleClass().add("box-bordered-border");
        Label joueurAgeLabel = new Label("Age du joueur");
        joueurAgeLabel.getStyleClass().add("box-bordered-title");
        TextField joueurAge = new NumberTextField();
        joueurAge.setPromptText("Age");
        joueurBox.getChildren().add(joueurAgeBox);
        joueurAgeBox.getChildren().add(joueurAgeLabel);
        joueurAgeBox.getChildren().add(joueurAge);

        StackPane joueurTailleBox = new StackPane();
        joueurTailleBox.getStyleClass().add("box-bordered-border");
        Label joueurTailleLabel = new Label("Taille (cm) du joueur");
        joueurTailleLabel.getStyleClass().add("box-bordered-title");
        TextField joueurTaille = new NumberTextField();
        joueurTaille.setPromptText("Taille");
        joueurBox.getChildren().add(joueurTailleBox);
        joueurTailleBox.getChildren().add(joueurTailleLabel);
        joueurTailleBox.getChildren().add(joueurTaille);

        StackPane joueurNationBox = new StackPane();
        joueurNationBox.getStyleClass().add("box-bordered-border");
        Label joueurNationLabel = new Label("Nation du joueur");
        joueurNationLabel.getStyleClass().add("box-bordered-title");
        TextField joueurNation = new TextField();
        joueurNation.setPromptText("Nation du joueur");
        joueurBox.getChildren().add(joueurNationBox);
        joueurNationBox.getChildren().add(joueurNationLabel);
        joueurNationBox.getChildren().add(joueurNation);

        StackPane joueurPosteBox = new StackPane();
        joueurPosteBox.getStyleClass().add("box-bordered-border");
        Label joueurPosteLabel = new Label("Poste du joueur");
        joueurPosteLabel.getStyleClass().add("box-bordered-title");
        TextField joueurPoste = new TextField();
        joueurPoste.setPromptText("Poste du joueur");
        joueurBox.getChildren().add(joueurPosteBox);
        joueurPosteBox.getChildren().add(joueurPosteLabel);
        joueurPosteBox.getChildren().add(joueurPoste);

        StackPane joueurStatutBox = new StackPane();
        joueurStatutBox.getStyleClass().add("box-bordered-border");
        Label joueurStatutLabel = new Label("Statut du joueur");
        joueurStatutLabel.getStyleClass().add("box-bordered-title");
        TextField joueurStatut = new TextField();
        joueurStatut.setPromptText("Statut du joueur");
        joueurBox.getChildren().add(joueurStatutBox);
        joueurStatutBox.getChildren().add(joueurStatutLabel);
        joueurStatutBox.getChildren().add(joueurStatut);

        Button joueurSaveButton = new Button("Enregistrer");
        joueurSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                selectedTeam.ajouter_joueur(new Joueur("Kilian", "EsKa", 19, 175, "France", Poste.Milieu));
                selectedTeam.ajouter_joueur(new Joueur("Kilian", "EsKa EsKaEsKa EsKa", 19, 175, "France", Poste.Milieu));
                showCreateCompetition();
            }
        });
        joueurSaveButton.setTranslateX(55);
        joueurBox.getChildren().add(joueurSaveButton);


        rightCreateTeam.getChildren().add(joueurBox);


        leftCreateTeam.setAlignment(Pos.CENTER_LEFT);
        centerCreateTeam.setAlignment(Pos.CENTER);
        rightCreateTeam.setAlignment(Pos.CENTER_RIGHT);
        createTeam.getChildren().add(leftCreateTeam);
        createTeam.getChildren().add(centerCreateTeam);
        createTeam.getChildren().add(scrollCenterCreateTeam
        );
        createTeam.getChildren().add(rightCreateTeam);

        changeBox.getChildren().add(createTeam);

        contentMenu.getChildren().add(menu);
        contentMenu.getChildren().add(equipe);
        contentMenu.getChildren().add(changeBox);

        window.setScene(scene);
    }


}