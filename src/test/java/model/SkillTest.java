package model;

import model.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SkillTest {
    private SkillRepository skillRepository;

    @BeforeEach
    void setUp() {
        skillRepository = new SkillRepository();  // Assuming SkillRepository is ready to use
    }

    @Test
    void testCreateSkillSuccessfully() {
        // Arrange
        String skillName = "Java Programming";

        // Act
        Skill newSkill = new Skill(skillName);
        boolean added = skillRepository.addSkill(newSkill);

        // Assert
        assertTrue(added, "Skill should be added successfully.");
        assertNotNull(newSkill, "The created skill should not be null.");
        assertEquals(skillName, newSkill.getName(), "The skill name should match the input.");
    }

    @Test
    void testListSkillsContainsNewlyAddedSkill() {
        // Arrange
        String skillName = "Project Management";
        Skill newSkill = new Skill(skillName);
        skillRepository.addSkill(newSkill);

        // Act
        List<Skill> skills = skillRepository.getSkills();

        // Assert
        assertTrue(skills.contains(newSkill), "The list of skills should contain the newly added skill.");
    }

    @Test
    void testSkillNotAddedWhenAlreadyExists() {
        // Arrange
        String skillName = "Public Speaking";
        Skill skill1 = new Skill(skillName);
        skillRepository.addSkill(skill1);

        // Act
        Skill skill2 = new Skill(skillName);
        boolean addedAgain = skillRepository.addSkill(skill2);

        // Assert
        assertFalse(addedAgain, "Skill should not be added again if it already exists.");
    }
}
