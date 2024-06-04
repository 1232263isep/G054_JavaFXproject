package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.ListSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

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
