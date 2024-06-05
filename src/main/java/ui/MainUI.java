package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainUI {

        @FXML
        private Button btnDevelopTeam;

        @FXML
        private Button btnExit;

        @FXML
        private Button btnLogin;

        @FXML
        void handleDevelopTeam(ActionEvent event) {
                new DevTeamUI().run();
        }

        @FXML
        void handleExit(ActionEvent event) {
                Platform.exit();
        }

        @FXML
        void handleLogin(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("authenticationUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stageAuthenticationUI = new Stage();
                stageAuthenticationUI.initModality(Modality.APPLICATION_MODAL);
                stageAuthenticationUI.setScene(scene);
                stageAuthenticationUI.show();
                AuthenticationUI authenticationUI = loader.getController();
                authenticationUI.associateParent(this);

            } catch (IOException e) {
//           e.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR, "'authentication' UI not open");
                a.showAndWait();
            }
        }
}