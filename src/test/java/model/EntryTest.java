package model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {
    private Entry entry;
    private Entry entry2;

    @Test
    void testEqualsSuccesful() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        entry2=new Entry(task,duration,urgency,greenSpace);
        boolean expected=true;
        //Act
        boolean result=entry.equals(entry2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testEqualsFail() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        Task task1=new Task("Cut trees","Cut trees higher than 8 meters");
        entry2=new Entry(task1,duration,urgency,greenSpace);
        boolean expected=false;
        //Act
        boolean result=entry.equals(entry2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testCloneSuccess() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        entry2=entry.clone();
        boolean expected=true;
        //Act
        boolean result=entry.equals(entry2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void testCloneFail() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        entry2=entry.clone();
        Task task1=new Task("Cut trees","Cut trees higher than 8 meters");
        entry=new Entry(task1,duration,urgency,greenSpace);
        boolean expected=false;
        //Act
        boolean result=entry.equals(entry2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void scheduleSuccess() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        boolean expected=true;
        //Act
        boolean result=entry.schedule(new Date());
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void schedulAlredyScheduled() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        entry.schedule(new Date());
        boolean expected=false;
        //Act
        boolean result=entry.schedule(new Date());
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void scheduleStatusModified() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        entry=new Entry(task,duration,urgency,greenSpace);
        entry.setStatus("Passed");
        boolean expected=false;
        //Act
        boolean result=entry.schedule(new Date());
        //Assert
        assertEquals(expected,result);
    }
}