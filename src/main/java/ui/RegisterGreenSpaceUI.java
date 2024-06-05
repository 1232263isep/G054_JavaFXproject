package ui;

import controller.RegisterGreenSpaceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;

public class RegisterGreenSpaceUI implements Runnable {
    private RegisterGreenSpaceController controller;
    private String type;
    private int area;
    private String name;
    private String address;
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblArea;

    @FXML
    private Label lblName;

    @FXML
    private Label lblType;

    @FXML
    private CheckBox optGarden;

    @FXML
    private CheckBox optLarge;

    @FXML
    private CheckBox optMedium;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtName;


    public RegisterGreenSpaceUI() {
        controller = new RegisterGreenSpaceController();
    }

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterGreenSpaceUI.fxml"));
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
        this.name=txtName.getText();
        if(this.name==null || this.name.equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please enter a valid name!");
            flag=false;
            alert.showAndWait();
        }
        try {
            this.area = Integer.parseInt(txtArea.getText());
        } catch (NumberFormatException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please enter a valid area!");
            flag=false;
            alert.showAndWait();
        }
        this.address=txtAddress.getText();
        if (this.address==null || this.address.equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please enter a valid address!");
            flag=false;
            alert.showAndWait();
        }
        if ((this.optGarden.isSelected() && this.optLarge.isSelected()) || (this.optMedium.isSelected() && this.optLarge.isSelected()) || (this.optMedium.isSelected() && this.optMedium.isSelected() && this.optGarden.isSelected())) {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please select just one type");
            flag=false;
            alert.showAndWait();
        }
        if (!this.optLarge.isSelected() && !this.optGarden.isSelected() && !this.optMedium.isSelected()){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please select a type of garden");
            alert.showAndWait();
            flag=false;
        }
        if (flag==true)
            submitData();
    }

    @FXML
    void handleGarden(ActionEvent event) {
        this.type="Garden";
    }

    @FXML
    void handleLarge(ActionEvent event) {
        this.type="Large Sized Park";
    }

    @FXML
    void handleMedium(ActionEvent event) {
        this.type="Medium Sized Park";
    }

    private void requestData() {
        name = Utils.readLineFromConsole("Enter the name of the Green Space: ").toLowerCase();
        type = null;
        while (type == null) {
            System.out.println("1) Garden");
            System.out.println("2) Medium Sized Park");
            System.out.println("3) Large Sized Park");
            int choice = 0;
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
                    type = "Garden";
                } else if (choice == 2) {
                    type = "Medium Sized Park";
                } else if (choice == 3) {
                    type = "Large Sized Park";
                }
            }
        }
        area = 0;
        while (area == 0) {
            try {
                area = Integer.parseInt(Utils.readLineFromConsole("Enter the area of the Green Space (hectares): "));
            } catch (Exception e) {
                System.out.println("Invalid area format, retry");
                continue;
            }
        }
        address = Utils.readLineFromConsole("Enter the address of the Green Space: ");
    }


    private void submitData() {
        try {
            boolean result = controller.registerGreenSpace(name,type,area,address);
            if (result == true) {
                System.out.println("\nGreen Space successfully registered!");
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Green Space successfully registered");
                alert.showAndWait();
            } else {
                System.out.println("\nGreen Space registration failed!");
                Alert alert=new Alert(Alert.AlertType.ERROR,"Green Space registration failed");
                alert.showAndWait();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}