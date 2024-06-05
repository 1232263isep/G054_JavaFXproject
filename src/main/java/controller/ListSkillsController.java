package controller;


import model.Skill;

import java.util.List;

public class ListSkillsController {

    private final SkillManagementController skillManagement = new SkillManagementController();


    public List<Skill> findAllSkills() {
        return skillManagement.findAllSkills();
    }
}