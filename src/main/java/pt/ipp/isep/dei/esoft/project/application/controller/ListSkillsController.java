package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

public class ListSkillsController {

    private final SkillManagementController skillManagement = new SkillManagementController();


    public List<Skill> findAllSkills() {
        return skillManagement.findAllSkills();
    }
}