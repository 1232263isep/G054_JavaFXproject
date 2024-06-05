package ui;

import controller.TeamProposalController;
import model.Entry;
import model.Skill;
import ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AssignTeamToEntryUI implements Runnable {
    private final TeamProposalController controller;

    public AssignTeamToEntryUI() {
        controller = new TeamProposalController();
    }

    public void run() {
        List<Entry> entries = controller.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries available.");
            return;
        }

        int entryIndex = Utils.showAndSelectIndex(entries, "Select an entry to assign a team:");
        if (entryIndex < 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        Entry selectedEntry = entries.get(entryIndex);

        List<Skill> allSkills = controller.getAllSkills();
        if (allSkills.isEmpty()) {
            System.out.println("No skills available.");
            return;
        }

        List<String> selectedSkillNames = new ArrayList<>();
        while (true) {
            int skillIndex = Utils.showAndSelectIndex(allSkills, "Select required skills for the team (0 to finish):");
            if (skillIndex < 0) {
                break;
            }
            selectedSkillNames.add(allSkills.get(skillIndex).getName());
        }

        int maxSize = Utils.readIntegerFromConsole("Enter maximum team size: ");
        int minSize = Utils.readIntegerFromConsole("Enter minimum team size: ");

        boolean success = controller.assignGeneratedTeamToEntry(selectedEntry, selectedSkillNames, maxSize, minSize);
        if (success) {
            System.out.println("Team successfully assigned to the entry.");
        } else {
            System.out.println("Failed to assign the team to the entry. Please check the details and try again.");
        }
    }
}
