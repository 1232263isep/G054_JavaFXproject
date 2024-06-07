package ui;

import controller.TeamProposalController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Entry;
import model.Skill;
import model.Team;
import ui.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AssignTeamToEntryUI implements Runnable {
    private final TeamProposalController controller;
    private int maxsize;
    private int minsize;
    private Entry entry;
    private List<Skill> skills;
    private Team team;
    @FXML
    private ListView<Skill> ListViewSkills;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnView;

    @FXML
    private ChoiceBox<Entry> choiceEntry;
    @FXML
    private ChoiceBox<Team> choiceTem;

    @FXML
    private Label lblEntry;

    @FXML
    private Label lblMax;

    @FXML
    private Label lblMin;

    @FXML
    private Label lblSkills;

    @FXML
    private TextField txtMax;

    @FXML
    private TextField txtMin;

    public AssignTeamToEntryUI() {
        controller = new TeamProposalController();
    }

    @FXML
    void handleConfirm(ActionEvent event) {
        boolean flag=true;
        this.entry=this.choiceEntry.getSelectionModel().getSelectedItem();
        if (this.entry==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select an Entry");
            alert.showAndWait();
            flag=false;
        }
        this.team=this.choiceTem.getSelectionModel().getSelectedItem();
        if (this.team==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select a Team");
            alert.showAndWait();
            flag=false;
        }
        if (flag) {
            boolean success = controller.assignGeneratedTeamToEntry(this.entry, this.team);
            if (success) {
                System.out.println("Team successfully assigned to the entry.");
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Team successfully assigned to the entry");
                a.showAndWait();
            } else {
                System.out.println("Failed to assign the team to the entry. Please check the details and try again.");
                Alert a=new Alert(Alert.AlertType.ERROR, "Failed to assign the team to the entry");
                a.showAndWait();
            }
            this.choiceEntry.getSelectionModel().clearSelection();
            ObservableList<Entry> list = FXCollections.observableArrayList();
            this.choiceEntry.setItems(list);
            this.choiceTem.getSelectionModel().clearSelection();
            ObservableList<Team> teams = FXCollections.observableArrayList();
            this.choiceTem.setItems(teams);
            this.handleViewprivate();
     }

    }


    private void handleViewprivate() {
        List<Entry> entries=controller.getAllEntries();
        if (entries.isEmpty()) {
            Alert a=new Alert(Alert.AlertType.ERROR,"No entries registered yet");
            a.showAndWait();
        }
        ObservableList<Entry> entries1 = FXCollections.observableArrayList();
        entries1.addAll(entries);
        this.choiceEntry.setItems(entries1);
        List<Team> teams=controller.getAllTeams();
        if (teams.isEmpty()) {
            Alert a=new Alert(Alert.AlertType.ERROR,"No teams registered yet");
            a.showAndWait();
        }
        ObservableList<Team> tems1 = FXCollections.observableArrayList();
        tems1.addAll(teams);
        this.choiceTem.setItems(tems1);
    }

    @FXML
    void handleView(ActionEvent event) {
        this.handleViewprivate();
    }

    @Override
    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignTeamToEntryUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
//           e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "'authentication' UI not open");
            a.showAndWait();
        }

    }
}

