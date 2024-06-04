package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {

    private final List<Skill> skills;

    public SkillRepository() {
        skills = new ArrayList<>();
    }

    /**
     * Adds a new Skill to the repository if it does not already exist.
     *
     * @param skill The skill to be added.
     * @return Optional containing the Skill if added, empty Optional if not added.
     */
    public boolean addSkill(Skill skill) {
        if (skill == null || containsSkill(skill.getName())) {
            return false;
        }
        skills.add(skill);
        return true;
    }

    /**
     * Adds a new Skill by name to the repository if it does not already exist.
     *
     * @param name The name of the skill to be added.
     * @return Optional containing the Skill if added, empty Optional if not added.
     */
    public Optional<Skill> addSkillByName(String name) {
        if (name == null || name.trim().isEmpty() || containsSkill(name)) {
            return Optional.empty();
        }
        Skill newSkill = new Skill(name);
        skills.add(newSkill);
        return Optional.of(newSkill);
    }

    /**
     * Finds a skill by name.
     *
     * @param name The name of the skill to search for.
     * @return An Optional containing the found skill if any, otherwise an empty Optional.
     */
    public Optional<Skill> findSkillByName(String name) {
        if (name == null) return Optional.empty();  // Return an empty Optional if the name is null

        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return Optional.of(skill);  // Return the skill if found
            }
        }
        return Optional.empty();  // Return an empty Optional if no skill is found
    }

    /**
     * Checks if a skill with the given name already exists in the repository using a traditional for loop.
     *
     * @param name The name of the skill to check.
     * @return true if the skill exists, false otherwise.
     */
    public boolean containsSkill(String name) {
        if (name == null) return false;  // Return false if the name is null

        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return true;  // Return true immediately if a match is found
            }
        }
        return false;  // Return false if no match is found after checking all skills
    }

    /**
     * Returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkills() {
        return List.copyOf(skills);
    }

    /**
     *
     * @param name of the skill
     * @return a copy of the skill if found, null in other case
     */
    public Skill getSkill(String name){
        Skill result=null;
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                result = skill;
            }
        }
        return result.clone();
    }
}
