package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Human Resources Manager User Interface.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GSmUI implements Runnable {
    AuthenticationUI authenticationUI;
    @FXML
    private Button btnExit;

    @FXML
    private Button btnListGreenSpaces;

    @FXML
    private Button btnRegisterGreenSpace;

    @FXML
    private Button btnaddEntryToDoList;

    @FXML
    private Button btnaddEntryagenda;

    @FXML
    private Button btnassignTeamGreenSpace;
    public GSmUI() {
    }

    @FXML
    void handleAddEntryToDoList(ActionEvent event) {
        AddToDoListUI addToDoListUI = new AddToDoListUI();
        addToDoListUI.run();
    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleListGreenSpaces(ActionEvent event) {
        ListGreenSpaceUI listGreenSpaceUI=new ListGreenSpaceUI();
        listGreenSpaceUI.run();
    }

    @FXML
    void handleRegisterGreenSpace(ActionEvent event) {
        RegisterGreenSpaceUI registerGreenSpaceUI=new RegisterGreenSpaceUI();
        registerGreenSpaceUI.run();
    }

    @FXML
    void handleaddEntryagenda(ActionEvent event) {
        AddAgendaUI addAgendaUI=new AddAgendaUI();
        addAgendaUI.run();
    }

    @FXML
    void handleassignTeamGreenSpace(ActionEvent event) {
        AssignTeamToEntryUI assignTeamToEntryUI=new AssignTeamToEntryUI();
        assignTeamToEntryUI.run();
    }

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GSmUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            associateParent(this.authenticationUI);
        } catch (IOException e) {
//           e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "'authentication' UI not open");
            a.showAndWait();
        }
    }

    public void associateParent(AuthenticationUI authenticationUI) {this.authenticationUI=authenticationUI;
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
