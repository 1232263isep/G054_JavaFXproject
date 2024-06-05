package ui;

import controller.SkillManagementController;
import model.Skill;
import ui.utils.Utils;


/**
 * Create Skill UI (console). This option allows HR managers to add new skills.
 */
public class CreateSkillUI implements Runnable {

    private final SkillManagementController controller;
    private String skillName;

    public CreateSkillUI() {
        controller = new SkillManagementController();
    }

    public void run() {
        System.out.println("\n\n--- Create Skill ------------------------");

        requestData();

        submitData();
    }

    private void requestData() {
        //Request the Skill Name from the console
        skillName = requestSkillName();
    }

    private String requestSkillName() {
        return Utils.readLineFromConsole("Enter Skill Name (letters and spaces only): ");
    }

//Validates the skill creation
    private void submitData() {
        try {
            Skill skill = controller.createSkill(skillName.toLowerCase());
            if (skill != null) {
                System.out.println("\nSkill successfully created!");
            } else {
                System.out.println("\nSkill creation failed!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}