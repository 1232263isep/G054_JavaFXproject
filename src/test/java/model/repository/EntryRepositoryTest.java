package model.repository;

import model.Entry;
import model.GreenSpace;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryTest {
    private EntryRepository entryRepository;
    @BeforeEach
    void setUp() {
        entryRepository = new EntryRepository();
    }
    @Test
    void addEntryToDoListSuccess() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        boolean expected=true;
        //Act
        boolean result=entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addEntryToDoListAlreadyExisting() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        boolean expected=false;
        entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        //Act
        boolean result=entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addEntryToDoListDifferentEntry() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        Task task2=new Task("Cut trees","Cut trees higher than 8 meters");
        boolean expected=true;
        //Act
        boolean result=entryRepository.addEntryToDoList(task2,duration,urgency,greenSpace,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getToDoListSuccess() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        List<Entry> l1=new ArrayList<Entry>();
        l1.add(new Entry(task,duration,urgency,greenSpace));
        boolean expected=true;
        //Act
        List<Entry> l2=entryRepository.getToDoList(username);
        boolean result=l1.equals(l2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getToDoListFail() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        entryRepository.addEntryToDoList(task,duration,urgency,greenSpace,username);
        List<Entry> l1=new ArrayList<Entry>();
        Task task1=new Task("Cut trees","Cut trees higher than 8 meters");
        l1.add(new Entry(task1,duration,urgency,greenSpace));
        boolean expected=false;
        //Act
        List<Entry> l2=entryRepository.getToDoList(username);
        boolean result=l1.equals(l2);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getToDoListEmpty() {
        //Arrange
        String username="gsm@this.com";
        boolean expected=true;
        //Act
        List<Entry> l2=entryRepository.getToDoList(username);
        boolean result=(l2.isEmpty());
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addAgendaSuccess() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        Entry entry=new Entry(task,duration,urgency,greenSpace);
        Date start=new Date("2024/06/02");
        boolean expected=true;
        //Act
        boolean result=entryRepository.addAgenda(entry,start,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addAgendaAlreadyExisting() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        Entry entry=new Entry(task,duration,urgency,greenSpace);
        Date start=new Date("2024/06/02");
        entryRepository.addAgenda(entry,start,username);
        boolean expected=false;
        //Act
        boolean result=entryRepository.addAgenda(entry,start,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addAgendaAlreadyPlanned() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        Entry entry=new Entry(task,duration,urgency,greenSpace);
        Date start=new Date("2024/06/02");
        entry.schedule(new Date());
        boolean expected=false;
        //Act
        boolean result=entryRepository.addAgenda(entry,start,username);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addAgendaDifferentUsername() {
        //Arrange
        Task task=new Task("Cut trees","Cut trees higher than 5 meters");
        Duration duration=Duration.ofHours(5);
        String urgency="high";
        GreenSpace greenSpace=new GreenSpace("Jardim de agua", "Garden",23,"Rua santa catarina");
        String username="gsm@this.app";
        Entry entry=new Entry(task,duration,urgency,greenSpace);
        Date start=new Date("2024/06/02");
        entryRepository.addAgenda(entry,start,username);
        String username1="gsm1@this.app";
        boolean expected=false;
        //Act
        boolean result=entryRepository.addAgenda(entry,start,username1);
        //Assert
        assertEquals(expected,result);
    }
}