package controller;

import model.Skill;
import model.repository.SkillRepository;
import model.repository.Repositories;

import java.util.List;

public class SkillManagementController {

    private SkillRepository skillRepository;

    public SkillManagementController() {
        getSkillRepository();
    }

    // Constructor with repository parameter for easier testing
    public SkillManagementController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    public Skill createSkill(String name) {
        if (validateSkillName(name)) {
            Skill newSkill = new Skill(name);
            if (getSkillRepository().addSkill(newSkill)) {
                return newSkill;
            } else {
                throw new IllegalArgumentException("Skill could not be added. It might already exist.");
            }
        } else {
            throw new IllegalArgumentException("Invalid skill name. Only letters and spaces are allowed.");
        }
    }

    private boolean validateSkillName(String name) {
        return name != null && name.matches("[a-zA-Z ]+");
    }

    public Skill registerNewSkill(String name) throws IllegalArgumentException {
//should include authorisation
        if (!isValidSkillName(name)) {
            throw new IllegalArgumentException("Skill name must only contain letters and spaces.");
        }

        Skill newSkill = new Skill(name);
        skillRepository.addSkill(newSkill);
        return newSkill;
    }

    public List<Skill> findAllSkills() {
        return skillRepository.getSkills();
    }

    private boolean isValidSkillName(String name) {
        // Basic validation to ensure the skill name only contains letters and spaces
        return name != null && name.matches("[a-zA-Z ]+");
    }

}

