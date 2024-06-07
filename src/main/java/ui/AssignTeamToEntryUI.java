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
    @FXML
    private ListView<Skill> ListViewSkills;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnView;

    @FXML
    private ChoiceBox<Entry> choiceEntry;

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
        try {
            this.maxsize = Integer.parseInt(txtMax.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Max size must be an integer");
            alert.showAndWait();
            flag=false;
        }
        try{
            this.minsize=Integer.parseInt(txtMin.getText())
;        } catch (NumberFormatException e){
            Alert a=new Alert(Alert.AlertType.ERROR,"Min size must be an integer");
            a.showAndWait();
            flag=false;
        }
        this.entry=this.choiceEntry.getSelectionModel().getSelectedItem();
        if (this.entry==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select an Entry");
            alert.showAndWait();
            flag=false;
        }
        this.skills=this.ListViewSkills.getSelectionModel().getSelectedItems();
        if (this.skills==null || this.skills.size()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select skills");
            alert.showAndWait();
            flag=false;
        }
        if (flag) {
            boolean success = controller.assignGeneratedTeamToEntry(this.entry, this.skills, this.maxsize, this.minsize);
            if (success) {
                System.out.println("Team successfully assigned to the entry.");
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Team successfully assigned to the entry");
                a.showAndWait();
            } else {
                System.out.println("Failed to assign the team to the entry. Please check the details and try again.");
                Alert a=new Alert(Alert.AlertType.ERROR, "Failed to assign the team to the entry");
                a.showAndWait();
            }
            this.txtMax.clear();
            this.txtMin.clear();
            this.choiceEntry.getSelectionModel().clearSelection();
            ObservableList<Entry> list = FXCollections.observableArrayList();
            this.choiceEntry.setItems(list);
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
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        List<Skill> allSkills = controller.getAllSkills();
        if (allSkills.isEmpty()) {
            Alert a=new Alert(Alert.AlertType.ERROR,"No skills registered yet");
            a.showAndWait();
        }
        skills.addAll(allSkills);
        this.ListViewSkills.setItems(skills);
        this.ListViewSkills.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

