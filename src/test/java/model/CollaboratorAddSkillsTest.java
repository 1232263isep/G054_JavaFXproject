package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorAddSkillsTest {
    @Test
    void addSkillSame() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(100,2,3);
        Date admissionDate = new Date(123,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="12345678-1AA1";
        Job job=new Job("designer");
        Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        List<Skill> skills=new ArrayList<Skill>();
        Skill skill=new Skill("brave");
        skills.add(skill);
        skills.add(skill);
        List<Skill> test=new ArrayList<Skill>();
        test.add(skill);
        boolean expected=true;
        //Act
        c.addSkills(skills);
        //ASsert
        boolean result=false;
        if (c.getSkills().equals(test))
            result=true;
        assertEquals(expected,result);
    }
    @Test
    void addSkillDouble() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(100,2,3);
        Date admissionDate = new Date(123,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        List<Skill> skills=new ArrayList<Skill>();

        //Act
        skills.add(new Skill("brave"));
        boolean expected=false;
        c.addSkills(skills);
        skills.add(new Skill("brave"));
        boolean result=c.addSkills(skills);
        //ASsert
        assertEquals(expected,result);
    }
}