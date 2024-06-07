package ui;

import controller.EntryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Collaborator;
import model.Entry;
import model.repository.AuthenticationRepository;
import model.repository.CollaboratorRepository;
import model.repository.Repositories;
import ui.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ConsultEntriesUI implements Runnable{

    private final EntryController entryController;
    private final CollaboratorRepository collaboratorRepository;
    private final AuthenticationRepository authenticationRepository;
    private Date startDate;
    private Date endDate;
    @FXML
    private Button btnConfirm;

    @FXML
    private DatePicker dateEndDate;

    @FXML
    private DatePicker dateStartingDate;

    @FXML
    private Label lblEnd;

    @FXML
    private Label lblStarting;

    @FXML
    private TextArea txtOut;

    public ConsultEntriesUI() {
        Repositories repositories = Repositories.getInstance();
        this.entryController = new EntryController();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    }

    @FXML
    void handleConfirm(ActionEvent event) {
        String email = authenticationRepository.getCurrentUserSession().getUserId().toString();
        boolean flag=true;
        Collaborator collaborator = collaboratorRepository.findCollaboratorByEmail(email);
        if (collaborator == null) {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Collaborator not found");
            alert.showAndWait();
            flag=false;
        }
        try {
            LocalDate sd = dateStartingDate.getValue();
            Calendar c = Calendar.getInstance();
            c.set(sd.getYear(), sd.getMonthValue() - 1, sd.getDayOfMonth());
            this.startDate = c.getTime();
        } catch (NullPointerException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select a valid date");
            alert.showAndWait();
            flag=false;
        }
        try {
            LocalDate ed = dateEndDate.getValue();
            Calendar c1 = Calendar.getInstance();
            c1.set(ed.getYear(), ed.getMonthValue() - 1, ed.getDayOfMonth());
            this.endDate = c1.getTime();
        } catch (NullPointerException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select a valid date");
            alert.showAndWait();
            flag=false;
        }
        if (this.endDate==null || this.startDate==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Select valid dates");
            alert.showAndWait();
            flag=false;
        }
        if (flag=true){
            String out="Entries assigned to " + collaborator.getName() + " between " + startDate + " and " + endDate + ":";
            try {
                List<Entry> entries = entryController.getEntriesForCollaboratorBetweenDates(collaborator, startDate, endDate);
                if (entries.size()==0){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"You don't have tasks in this period");
                    alert.showAndWait();
                }
                else {
                    for (Entry entry : entries) {
                        out = out + '\n' + entry.getTask().getDescription() + " - " + entry.getDate();
                    }
                }
                txtOut.setText(out);
            }catch (Exception e){
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"You don't have tasks in this period");
                alert.showAndWait();
            }
        }
    }

    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultEntriesUI.fxml"));
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
}
