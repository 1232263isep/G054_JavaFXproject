package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.repository.CollaboratorRepository;
import model.repository.Repositories;
import ui.*;
import ui.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Employee User Interface.
 */
public class EmployeeUI implements Runnable {

    private CollaboratorRepository employeeRepository;

    private Repositories repositories;

        @FXML
        private Button btnExit;

        @FXML
        private Button btnViewTasks;

        @FXML
        void handleExit(ActionEvent event) {
            Platform.exit();
        }

        @FXML
        void handleView(ActionEvent event) {
            ConsultEntriesUI consultEntriesUI=new ConsultEntriesUI();
            consultEntriesUI.run();
        }

    @Override
    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeUI.fxml"));
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
