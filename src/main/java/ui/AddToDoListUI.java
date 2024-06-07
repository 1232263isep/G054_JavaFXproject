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
import model.Task;
import ui.utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
    private Button btnShowGreenSpaces;
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
    void handleShowGreenSpace(ActionEvent event) {
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
        this.greenSpaceName=this.choiceGreenSpace.getSelectionModel().getSelectedItem().getName();
        if (this.choiceGreenSpace.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please select a green space");
            a.showAndWait();
            flag=false;
        }
        if (flag) {
            submitData();
        }
        this.txtDays.clear();
        this.txtDescription.clear();
        this.txtDuration.clear();
        this.txtHours.clear();
        this.txtNameTask.clear();
    }


    private void requestTask(){
        List<Task> taskList=controller.getTaskList();
        int i=1;
        for (Task t:taskList){
            System.out.println(i+". "+t);
            i++;
        }
        System.out.println("0. New Task");
        int choice=-1;
        while (choice==-1) {
            try {
                choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the selected task, or 0 to create a new task"));
            } catch (Exception e) {
                choice=-1;
                System.out.println("Wrong format, please enter the number of the selected task, or 0 to create a new task");
                continue;
            }
            if (choice==0){
                name=Utils.readLineFromConsole("Enter the name of the new task").toLowerCase();
                description=Utils.readLineFromConsole("Enter the description of the new task").toLowerCase();
            }
            else if(choice<=taskList.size()){
                name=taskList.get(choice-1).getName();
                description=taskList.get(choice-1).getDescription();
            }
            else {
                choice=-1;
                System.out.println("Wrong number entered, please retry");
                continue;
            }
        }
    }
    private void  requestEntryData() {
        duration=null;
        while (duration == null) {
            System.out.println("Please enter a valid duration: ");
            int days = -1;
            while (days == -1) {
                try {
                    days = Integer.parseInt(Utils.readLineFromConsole("Days: "));
                } catch (Exception e) {
                    days = -1;
                    System.out.println("Error, wrong format, please insert the number of days required");
                    continue;
                }
            }
            int hours = -1;
            while (hours == -1) {
                try {
                    hours = Integer.parseInt(Utils.readLineFromConsole("Hours: "));
                } catch (Exception e) {
                    hours = -1;
                    System.out.println("Error, wrong format, please insert the number of hours required");
                    continue;
                }
            }

        }
        urgency=null;
        while (urgency==null){
            int choice=0;
            System.out.println("Please enter the level of urgency: ");
            System.out.println("1) Low");
            System.out.println("2) Medium");
            System.out.println("3) High");
            while (choice == 0) {
                try {
                    choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the choosen option: "));
                } catch (Exception e) {
                    choice = 0;
                    System.out.println("Invalid format, please select one option");
                    continue;
                }
                if (choice < 0 || choice > 3) {
                    choice = 0;
                    System.out.println("Number selected out of range, please retry");
                    continue;
                }
                if (choice == 1) {
                    urgency = "Low";
                } else if (choice == 2) {
                    urgency = "Medium";
                } else if (choice == 3) {
                    urgency = "High";
                }
            }
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
