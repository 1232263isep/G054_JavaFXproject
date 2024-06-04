package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.application.Platform;
import org.example.javafxprojectpprog.MainUI;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateSkillUI; // Assuming this UI exists for creating skills
import pt.ipp.isep.dei.esoft.project.ui.console.ListSkillsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Human Resources Manager User Interface.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;






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
        new AssignSkillsUI();
    }

    @FXML
    void handleGenerateTeam(ActionEvent event) {
        new TeamProposalUI();
    }

    @FXML
    void handleRegisterACollaborator(ActionEvent event) {
        new RegisterCollaboratorUI();
    }

    @FXML
    void handleRegisterAJob(ActionEvent event) {
        new CreateJobUI();
    }

    @FXML
    void handleRegisterSkill(ActionEvent event) {
        new CreateSkillUI();
    }

    @FXML
    void handleViewAllSkills(ActionEvent event) {
        new ListSkillsUI();
    }
    SkillRepository skillRepository = new SkillRepository();  // Assume this is initialized correctly

    public void run() {

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
