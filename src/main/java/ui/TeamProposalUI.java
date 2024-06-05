package ui;

import controller.TeamProposalController;
import model.Team;

import ui.utils.Utils;

import java.util.List;

public class TeamProposalUI implements Runnable{
    private final TeamProposalController controller;
    public TeamProposalUI() {
        controller = new TeamProposalController();
    }



    public void run() {
        int maxSize = Utils.readIntegerFromConsole("Enter maximum team size: ");
        int minSize = Utils.readIntegerFromConsole("Enter minimum team size: ");

        new ListSkillsUI();

        String skillsInput = Utils.readLineFromConsole("Enter required skills (comma separated): ");

        List<String> skillNames = List.of(skillsInput.split(",\\s*"));
        try {
            Team team = controller.generateTeamProposal(maxSize, minSize, skillNames);
            System.out.println("Generated Team:");
            team.getCollaborators().forEach(collaborator ->
                    System.out.println(" - " + collaborator.getName()));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
