package model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {
    @Test
    void testValidCollaboratorMissing() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(2000,2,3);
        Date admissionDate = null;
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act and assert
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        assertEquals(expresult,result);
    }
    @Test
    void testValidCollaboratorName() {
        //Arrange
        String name = "Ana Maria Josè Marco Carlos Perez Gomes";
        Date birthdate = new Date(2000,2,3);
        Date admissionDate = new Date(2023,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        //Assert
        assertEquals(expresult,result);
    }
    @Test
    void testValidCollaboratorBirthdate() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(120,2,3);
        Date admissionDate = new Date(123,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        //Assert
        assertEquals(expresult,result);
    }
    @Test
    void testValidCollaboratorPhone() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(2000,2,3);
        Date admissionDate = new Date(2023,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=1234567890;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        //Assert
        assertEquals(expresult,result);
    }
    @Test
    void testValidCollaboratorEmail() {
        //Arrange
        String name = "Ana";
        Date birthdate = new Date(2000,2,3);
        Date admissionDate = new Date(2023,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="testtest.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        //Assert
        assertEquals(expresult,result);
    }
    @Test
    void testValidCollaboratorIDNumber() {
        //arrange
        String name = "Ana";
        Date birthdate = new Date(2000,2,3);
        Date admissionDate = new Date(2023,4,12);
        String street="Main Street";
        String city="Porto";
        String zipcode="1234AB";
        int phoneNumber=123456789;
        String email="test@test.com";
        String IDDocType="ID card";
        String IDNumber="123456789-1AA1";
        Job job=new Job("designer");
        boolean result=true;
        //Act
        boolean expresult=false;
        try {
            Collaborator c = new Collaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
        } catch (Exception e) {
            result = false;
        }
        //Assert
        assertEquals(expresult,result);
    }
}