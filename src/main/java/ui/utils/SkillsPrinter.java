package ui.utils;

import controller.ListSkillsController;
import model.Skill;

import java.util.List;

/**
 * Utility class to print all skills in the repository.
 */
public class SkillsPrinter implements Runnable {
    private final ListSkillsController listSkillsController;

    public SkillsPrinter(ListSkillsController listSkillsController) {
        this.listSkillsController = listSkillsController;
    }

    @Override
    public void run() {
        try {
            List<Skill> skills = listSkillsController.findAllSkills();
            if (skills.isEmpty()) {
                System.out.println("No skills available.");
            } else {
                skills.forEach(skill -> System.out.println(skill.getName()));
            }
        } catch (SecurityException e) {
            System.out.println("Access denied: " + e.getMessage());
        }
    }
}
