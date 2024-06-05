package ui;

import javafx.scene.control.*;
import controller.authorization.AuthenticationController;
import ui.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;
    MainUI parentUI;
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;
    @FXML
    void handleConfirm(ActionEvent event) {
        run();
    }

    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    public void run() {
        boolean success = doLogin();
        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        //this.logout();
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HRmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GSmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_EMPLOYEE, new EmployeeUI()));
        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    private boolean doLogin() {

        System.out.println("\n\n--- LOGIN UI ---------------------------");

        int maxAttempts = 3;
        boolean success = false;
        maxAttempts--;
        String id = txtEmail.getText();
        String pwd = txtPassword.getText();

        success = ctrl.doLogin(id, pwd);
        if (!success) {
            System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid UserId and/or Password. \n Please try again.");
            alert.showAndWait();
        }
        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

    public void associateParent(MainUI mainUI) {this.parentUI=mainUI;
    }
}