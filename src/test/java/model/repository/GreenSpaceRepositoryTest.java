package model.repository;

import model.GreenSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceRepositoryTest {
    private  GreenSpaceRepository greenSpaceRepository;
    @BeforeEach
    void setUp() {
        greenSpaceRepository = new GreenSpaceRepository();
    }
    @Test
    void testRegisterGreenSpaceSuccess() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String username="gsm@this.app";
        boolean expected=true;
        //Act
        boolean result=greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testRegisterGreenSpaceAlreadyExisting() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String type2="Medium Sized Park";
        int area2=34;
        String username="gsm@this.app";
        boolean expected=false;
        //Act
        greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        boolean result=greenSpaceRepository.registerGreenSpace(name, type2, area2, address, username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testGetGreenSpaceByNameSuccess() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String username="gsm@this.app";
        GreenSpace g1=new GreenSpace(name,type,area,address);
        greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        boolean expected=true;
        //Act
        GreenSpace g2=greenSpaceRepository.getGreenSpaceByName(name,username);
        //Assert
        boolean result=g1.equals(g2);
        assertEquals(expected,result);
    }
    @Test
    void testGetGreenSpaceByNameFail() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String username="gsm@this.app";
        greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        String name1="Jardim do Morro";
        GreenSpace g1=new GreenSpace(name1,type,area,address);
        boolean expected=false;
        //Act
        GreenSpace g2=greenSpaceRepository.getGreenSpaceByName(name1,username);
        //Assert
        boolean result=g1.equals(g2);
        assertEquals(expected,result);
    }

    @Test
    void testGetGreenSpaceListSuccess() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String username="gsm@this.app";
        String name1="Jardim do Morro";
        List<GreenSpace> l1= new ArrayList<GreenSpace>();
        l1.add(new GreenSpace(name,type,area,address));
        l1.add(new GreenSpace(name1,type,area,address));
        greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        greenSpaceRepository.registerGreenSpace(name1, type, area, address, username);
        boolean expected=true;
        //Act
        List<GreenSpace> l2=greenSpaceRepository.getGreenSpaceList(username);
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected,result);
    }
    @Test
    void testGetGreenSpaceListFail() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        String username="gsm@this.app";
        String name1="Jardim do Morro";
        List<GreenSpace> l1= new ArrayList<GreenSpace>();
        l1.add(new GreenSpace(name,type,area,address));
        l1.add(new GreenSpace(name1,type,area,address));
        greenSpaceRepository.registerGreenSpace(name, type, area, address, username);
        boolean expected=false;
        //Act
        List<GreenSpace> l2=greenSpaceRepository.getGreenSpaceList(username);
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected,result);
    }
    @Test
    void testGetGreenSpaceListEmpty() {
        //Arrange
        String username="gsm@this.app";
        List<GreenSpace> l1= new ArrayList<GreenSpace>();
        boolean expected=false;
        //Act
        List<GreenSpace> l2=greenSpaceRepository.getGreenSpaceList(username);
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected,result);
    }
}