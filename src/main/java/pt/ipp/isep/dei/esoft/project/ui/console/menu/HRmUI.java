package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateSkillUI; // Assuming this UI exists for creating skills
import pt.ipp.isep.dei.esoft.project.ui.console.ListSkillsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Human Resources Manager User Interface.
 */
public class HRmUI implements Runnable {
    public HRmUI() {
    }

    SkillRepository skillRepository = new SkillRepository();  // Assume this is initialized correctly

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Skill", new CreateSkillUI())); // Specific UI for registering skills
        options.add(new MenuItem("View All Skills", new ListSkillsUI())); // Placeholder for skill viewing functionality
        options.add(new MenuItem("Register a job", new CreateJobUI()));
        options.add(new MenuItem("Register a collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign skills to  collaborator", new AssignSkillsUI()));
        options.add(new MenuItem("Generate a team", new TeamProposalUI()));



        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HR MANAGER MENU -------------------------");

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
