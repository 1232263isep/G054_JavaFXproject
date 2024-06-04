package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;


/**
 * User interface for listing all skills.
 */
public class ListSkillsUI implements Runnable {

    private final ListSkillsController skillsController = new ListSkillsController();



    @Override
    public void run() {
        System.out.println("\n--- List All Skills ---\n");
        displaySkills();
    }

    private void displaySkills() {
        try {
            List<Skill> skills = skillsController.findAllSkills();
            if (skills.isEmpty()) {
                System.out.println("No skills are currently registered in the system.");
            } else {
                System.out.println("Registered Skills:");
                skills.forEach(skill -> System.out.println(" - " + skill.getName()));
            }
        } catch (SecurityException e) {
            System.out.println("Access denied: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while fetching skills: " + e.getMessage());
        }
    }
}