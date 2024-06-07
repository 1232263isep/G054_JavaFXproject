package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceTest {
    private GreenSpace greenSpace;
    private GreenSpace greenSpace1;
    @Test
    void testEqualsSuccess() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        greenSpace=new GreenSpace(name,type,area,address);
        greenSpace1=new GreenSpace(name,type,area,address);
        boolean expected=true;
        //Act
        boolean result=greenSpace.equals(greenSpace1);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testEqualsFail() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        greenSpace=new GreenSpace(name,type,area,address);
        String name1="Jardim do Morro";
        greenSpace1=new GreenSpace(name1,type,area,address);
        boolean expected=false;
        //Act
        boolean result=greenSpace.equals(greenSpace1);
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void testEqualsNull() {
        //Arrange
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        greenSpace=new GreenSpace(name,type,area,address);
        greenSpace1=null;
        boolean expected=false;
        //Act
        boolean result=greenSpace.equals(greenSpace1);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testCloneSuccess() {
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        greenSpace=new GreenSpace(name,type,area,address);
        greenSpace1=greenSpace.clone();
        boolean expected=true;
        //Act
        boolean result=greenSpace.equals(greenSpace1);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testCloneFail() {
        String name="Jardim de Agua";
        String type="Garden";
        int area=35;
        String address="123 Main Street";
        greenSpace=new GreenSpace(name,type,area,address);
        greenSpace1=greenSpace.clone();
        String name1="Jardim do Morro";
        greenSpace=new GreenSpace(name1,type,area,address);
        boolean expected=false;
        //Act
        boolean result=greenSpace.equals(greenSpace1);
        //Assert
        assertEquals(expected,result);
    }
}