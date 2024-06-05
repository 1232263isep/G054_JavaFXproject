package ui;

import controller.ListGreenSpaceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GreenSpace;
import ui.utils.Utils;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ListGreenSpaceUI implements Runnable {
    private final ListGreenSpaceController controller = new ListGreenSpaceController();
    private String sortingAlgorithm;
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblSelectAlgorithm;

    @FXML
    private CheckBox optDefault;

    @FXML
    private CheckBox optAlternative;

    @FXML
    private Label lblOut;

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListGreenSpaceUI.fxml"));
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

    @FXML
    void handleConfirm(ActionEvent event) {
        if (this.optDefault.isSelected() && this.optAlternative.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select just one sorting method");
            alert.showAndWait();
        }
        else if (!this.optDefault.isSelected() && !this.optAlternative.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select one sorting method");
            alert.showAndWait();
        }
        else{
            if (this.optDefault.isSelected()){
                String sortingAlgorithm="default";}
            else {
                String sortingAlgorithm="alternative";
            }
            List<GreenSpace> greenSpaces = controller.listGreenSpaces();
            displayGreenSpaces(greenSpaces);
        }
    }

    private String requestSortingAlgorithm() {
        System.out.println("Select Sorting Algorithm:");
        System.out.println("1 - Default");
        System.out.println("2 - Alternative");
        int choice = Utils.readIntegerFromConsole("Enter your choice: ");
        switch (choice) {
            case 2:
                return "alternative";
            case 1:
            default:
                return "default";
        }
    }

    private void displayGreenSpaces(List<GreenSpace> greenSpaces) {
        String greenspces="";
        for (GreenSpace greenSpace : greenSpaces) {
            greenspces=greenspces+'\n'+greenSpace;
        }
        lblOut.setText(greenspces);
    }
}
