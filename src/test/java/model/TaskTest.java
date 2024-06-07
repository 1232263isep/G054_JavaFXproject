package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task t1;
    private Task t2;
    @Test
    void testEqualsSuccess() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        t1=new Task(name,description);
        t2=new Task(name,description);
        boolean expected=true;
        //Act
        boolean actual=t1.equals(t2);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void testEqualsDifferentDescription() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        t1=new Task(name,description);
        String description1="Cuts trees higher than 8 meters";
        t2=new Task(name,description1);
        boolean expected=false;
        //Act
        boolean actual=t1.equals(t2);
        //Assert
        assertEquals(expected, actual);
    }@Test
    void testEqualsfail() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        t1=new Task(name,description);
        String description1="Cuts trees higher than 8 meters";
        t2=new Task(name,description1);
        boolean expected=false;
        //Act
        boolean actual=t1.equals(t2);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void testEqualsDifferentName() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        t1=new Task(name,description);
        String name1="Cut grass";
        t2=new Task(name1,description);
        boolean expected=false;
        //Act
        boolean actual=t1.equals(t2);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void testCloneSuccess() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        t1=new Task(name,description);
        boolean expected=true;
        //Act
        t2=t1.clone();
        //Assert
        boolean actual=t1.equals(t2);
        assertEquals(expected, actual);
    }
    @Test
    void testCloneFail() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        String name1="Cut grass";
        t1=new Task(name,description);
        boolean expected=false;
        //Act
        t2=t1.clone();
        t1=new Task(name1,description);
        //Assert
        boolean actual=t1.equals(t2);
        assertEquals(expected, actual);
    }
}