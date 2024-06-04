package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Human Resources Manager User Interface.
 */
public class GSmUI implements Runnable {
    public GSmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a green space", new RegisterGreenSpaceUI())); // Specific UI for registering skills
        options.add(new MenuItem("Add a new entry in the to-do-list", new AddToDoListUI()));
        options.add(new MenuItem("Add a new entry in the agenda", new AddAgendaUI()));
        options.add(new MenuItem("List YOUR Green Spaces", new ListGreenSpaceUI()));
        options.add(new MenuItem("Assign team to entry", new AssignTeamToEntryUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GS MANAGER MENU -------------------------");

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
