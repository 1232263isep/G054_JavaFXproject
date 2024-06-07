package ui;

import controller.AddToDoListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GreenSpace;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddToDoListUI implements Runnable {
     private AddToDoListController controller;
    private String name;
    private String description;
    private Duration duration;
    private String urgency;
    private String greenSpaceName;

    @FXML
    private Button btnConfirm;

    @FXML
    private ChoiceBox<GreenSpace> choiceGreenSpace;

    @FXML
    private Label lblDescription;
    @FXML
    private Label lblNameGreenSpace;

    @FXML
    private Label lblNameTask;

    @FXML
    private Label lblUrgency;

    @FXML
    private CheckBox optHigh;
    @FXML
    private TextField txtHours;
    @FXML
    private TextField txtDays;

    @FXML
    private CheckBox optLow;

    @FXML
    private CheckBox optMedium;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtNameTask;

    public AddToDoListUI() {
        controller= new AddToDoListController();
    }

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddToDoListUI.fxml"));
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
    void initialize(){
        List<GreenSpace> greenSpaces=controller.getGreenSpaceList();
        ObservableList<GreenSpace> greenSpaces1 = FXCollections.observableArrayList();
        greenSpaces1.addAll(greenSpaces);
        this.choiceGreenSpace.setItems(greenSpaces1);
    }
    @FXML
    void handleConfirm(ActionEvent event) {
        boolean flag=true;
        this.name=txtNameTask.getText();
        if (this.name.equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please enter a task name");
            a.showAndWait();
            flag=false;
        }
        this.description=txtDescription.getText();
        if (this.description.equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please enter a description");
            a.showAndWait();
            flag=false;
        }
        String days=txtDays.getText();
        if (days.equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please enter a day");
            a.showAndWait();
            flag=false;
        }
        String hours=txtHours.getText();
        if (hours.equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please enter a hour");
            a.showAndWait();
            flag=false;
        }
        String durationStr = "P" + days + "DT" + hours + "H" ;
        try {
            duration = Duration.parse(durationStr);
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Invalid duration");
            a.showAndWait();
            flag=false;
        }
        if ((optHigh.isSelected() && optLow.isSelected()) || (optMedium.isSelected() && optHigh.isSelected()) || (optMedium.isSelected() && optLow.isSelected())){
            Alert a=new Alert(Alert.AlertType.ERROR, "Please enter just one level of urgency");
            a.showAndWait();
            flag=false;
        }
        else if (!optHigh.isSelected() && !optLow.isSelected() && !optMedium.isSelected()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Please enter an urgency");
            a.showAndWait();
            flag=false;
        }
        if (optLow.isSelected())
            this.urgency="low";
        else if (optHigh.isSelected()) {this.urgency="high";
        } else if (optMedium.isSelected()) {this.urgency="medium";
        }
        if (this.urgency==null){
            flag=false;
        }
        try {
            this.greenSpaceName = this.choiceGreenSpace.getSelectionModel().getSelectedItem().getName();
        } catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Error, please select a green space");
            alert.showAndWait();
            flag=false;
        }
        if (this.choiceGreenSpace.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please select a green space");
            a.showAndWait();
            flag=false;
        }
        if (flag) {
            submitData();
            this.txtDays.clear();
            this.txtDescription.clear();
            this.txtHours.clear();
            this.txtNameTask.clear();
            this.optMedium.setSelected(false);
            this.optHigh.setSelected(false);
            this.optLow.setSelected(false);
            this.choiceGreenSpace.getSelectionModel().clearSelection();
        }


    }

    private void submitData() {
        try {
            boolean result = controller.addToDoList(name,description,duration,urgency,greenSpaceName);
            if (result == true) {
                System.out.println("\nEntry successfully registered in to-do-list!");
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Entry successfully registered in to-do-list!");
                a.showAndWait();
            } else {
                System.out.println("\nEntry registration failed!");
                Alert a=new Alert(Alert.AlertType.ERROR,"Entry registration failed!");
                a.showAndWait();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }

    }
}
