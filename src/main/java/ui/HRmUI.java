package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.repository.SkillRepository;

/**
 * Human Resources Manager User Interface.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class HRmUI implements Runnable {
    AuthenticationUI parentUI;
    @FXML
    private Button btnAssignSkillsToCollaborator;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnGenerateTeam;

    @FXML
    private Button btnRegisterACollaborator;

    @FXML
    private Button btnRegisterAJob;

    @FXML
    private Button btnRegisterSkill;

    @FXML
    private Button btnViewAllSkills;

    public HRmUI() {

    }

    @FXML
    void HandleExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleAssignSkillsToCollaborator(ActionEvent event) {
        AssignSkillsUI assignSkillsUI = new AssignSkillsUI();
        assignSkillsUI.run();
    }

    @FXML
    void handleGenerateTeam(ActionEvent event) {
        TeamProposalUI proposalUI = new TeamProposalUI();
        proposalUI.run();
    }

    @FXML
    void handleRegisterACollaborator(ActionEvent event) {
        RegisterCollaboratorUI registerCollaboratorUI=new  RegisterCollaboratorUI();
        registerCollaboratorUI.run();
    }

    @FXML
    void handleRegisterAJob(ActionEvent event) {
        CreateJobUI createJobUI=new CreateJobUI();
        createJobUI.run();
    }

    @FXML
    void handleRegisterSkill(ActionEvent event) {
        CreateSkillUI createSkillUI=new  CreateSkillUI();
        createSkillUI.run();
    }

    @FXML
    void handleViewAllSkills(ActionEvent event) {
        ListSkillsUI listSkillsUI=new  ListSkillsUI();
        listSkillsUI.run();
    }
    SkillRepository skillRepository = new SkillRepository();  // Assume this is initialized correctly

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HRmUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            associateParent(this.parentUI);
        } catch (IOException e) {
//           e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "'authentication' UI not open");
            a.showAndWait();
        }
    }

    public void associateParent(AuthenticationUI authenticationUI) {this.parentUI=authenticationUI;
    }

    /**
     * MenuItem class to hold the options and actions.
     */
    private static class MenuItem {
        private final String option;
        private final Runnable action;

        public MenuItem(String option, Runnable action) {
            this.option = option;
            this.action = action;
        }

        public void run() {
            this.action.run();
        }

        @Override
        public String toString() {
            return option;
        }
    }
}
