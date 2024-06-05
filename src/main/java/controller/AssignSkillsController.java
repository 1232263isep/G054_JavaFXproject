package controller;

import model.Collaborator;
import model.Skill;
import model.repository.CollaboratorRepository;
import model.repository.Repositories;
import model.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignSkillsController {
    private Repositories repositories;
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    public AssignSkillsController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillRepository = repositories.getSkillRepository();
    }
    /**
     *
     * @return the list of collaborator
     */
    public List<Collaborator> getCollaboratorList(){
        return  collaboratorRepository.getCollaboratorList();
    }

    /**
     *
     *
     * @return the list of skills
     */
    public List<Skill> getSkillsList(){
        return skillRepository.getSkills();
    }

    /**
     *
     * @param skillsName array of strings with the skills' name
     * @return a list of skills with that name, if a skill is not in the repository than is not added
     */
    public List<Skill> getSkillsByName(List<String> skillsName) {
        List<Skill> result = new ArrayList<>();
        for (String skillName : skillsName) {
            Optional<Skill> skillOptional = Optional.ofNullable(getSkillByName(skillName));
            skillOptional.ifPresent(result::add); // This will add the skill if it is present
        }
        return result;
    }




    public Skill getSkillByName(String name) {
        return skillRepository.findSkillByName(name)
                .orElseThrow(() -> new IllegalArgumentException("No skill found with name: " + name));
    }
    /**
     *
     * *@param collaborator's attributes
     * @return true if the collaborator has been created, false in other case
     */
    public boolean assignSkillsToCollaborator(List<String> skill, String ID){
        List<Skill> skills =getSkillsByName(skill);
        return collaboratorRepository.addSkills(skills,ID);
    }
}

