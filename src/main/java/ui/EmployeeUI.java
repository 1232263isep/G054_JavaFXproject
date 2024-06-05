package ui;

import model.repository.CollaboratorRepository;
import model.repository.Repositories;
import ui.*;
import ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee User Interface.
 */
public class EmployeeUI implements Runnable {

    private CollaboratorRepository employeeRepository;

    private Repositories repositories;


    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("View tasks", new ConsultEntriesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- EMPLOYEE MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
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
