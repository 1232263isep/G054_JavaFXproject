package ui;

import controller.AddAgendaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Entry;
import ui.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddAgendaUI implements Runnable {
     private AddAgendaController controller;
    private Entry entry;
    private Date startingDate;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnEntries;

    @FXML
    private ChoiceBox<Entry> choiceEntry;

    @FXML
    private DatePicker dateDate;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEntry;

    public AddAgendaUI() {
        controller= new AddAgendaController();
    }

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAgendaUI.fxml"));
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
        boolean flag=true;
        this.entry=choiceEntry.getSelectionModel().getSelectedItem();
        if (entry==null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Plese select an entry");
            a.showAndWait();
            flag=false;
        }
        LocalDate ld = dateDate.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
        Date date = c.getTime();
        Date today=new Date();
        if (today.compareTo(date) > 0) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Please select a future date");
            a.showAndWait();
            flag=false;
        }
        startingDate=date;
        if (flag) {
            submitData();
        }
    }

    @FXML
    void handleShowEntries(ActionEvent event) {
        List<Entry> toDoList=controller.getToDoList();
        ObservableList<Entry> toDoList1 = FXCollections.observableArrayList();
        toDoList1.addAll(toDoList);
        this.choiceEntry.setItems(toDoList1);
    }


   /* private void requestEntry(){

        int i=1;
        for (Entry e:toDoList){
            System.out.println(i+". "+e);
            i++;
        }
        int choice=-1;
        while (choice==-1) {
            try {
                choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the selected task"));
            } catch (Exception e) {
                choice=-1;
                System.out.println("Wrong format, please enter the number of the selected task");
                continue;
            }
            if(choice<=toDoList.size()){
                entry=toDoList.get(choice-1);
            }
            else {
                choice=-1;
                System.out.println("Wrong number entered, please retry");
                continue;
            }
        }
    }
    private void  requestStartingDate() {
        Date today=new Date();
        startingDate = null;
        while (startingDate == null) {
            try {
                startingDate = new Date(Utils.readLineFromConsole("Enter the starting date (yyyy/mm/dd): "));
            } catch (Exception e) {
                System.out.println("Invalid date format, retry");
                continue;
            }
            if (today.compareTo(startingDate) > 0) {
                startingDate = null;
                System.out.println("The can't be in the past, please retry");
                continue;
            }
        }
    }
*/
    private void submitData() {
        try {
            boolean result = controller.addAgenda(entry,startingDate);
            if (result == true) {
                System.out.println("\nEntry successfully registered in to-do-list!");
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Entry succesfully registered in Agenda");
                a.showAndWait();
            } else {
                System.out.println("\nEntry registration failed!");
                Alert a=new Alert(Alert.AlertType.ERROR,"Entry registration failed");
                a.showAndWait();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
