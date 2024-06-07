package model.repository;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {
    private TaskRepository repository;
    @BeforeEach
    void setUp() {
        repository = new TaskRepository();
    }
    @Test
    void getTaskNew() {
        //Arrange
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        Task task1=new Task(name,description);
        boolean expected=true;
        //Act
        Task task2=repository.getTask(name,description);
        //Assert
        boolean result=task1.equals(task2);
        assertEquals(expected, result);
    }
    @Test
    void getTaskAlreadyExisting() {
        //Arrange
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        Task task1=repository.getTask(name,description);
        boolean expected=true;
        //Act
        Task task2=repository.getTask(name,description);
        //Assert
        boolean result=task1.equals(task2);
        assertEquals(expected, result);
    }
    @Test
    void getTaskDifferentDescription() {
        //Arrange
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        repository.getTask(name,description);
        String description1="Cuts trees higher than 8 meters";
        Task task1=new Task(name,description1);
        boolean expected=false;
        //Act
        Task task2=repository.getTask(name,description);
        //Assert
        boolean result=task1.equals(task2);
        assertEquals(expected, result);
    }
    @Test
    void getTaskListSuccess() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        Task t1=repository.getTask(name,description);
        String description1="Cuts trees higher than 8 meters";
        Task t2=repository.getTask(name,description1);
        List<Task> l1=new ArrayList<Task>();
        l1.add(t1);
        l1.add(t2);
        boolean expected=true;
        //Act
        List<Task> l2=repository.getTaskList();
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected, result);
    }
    @Test
    void getTaskListFail() {
        String name="Cut trees";
        String description="Cuts trees higher than 5 meters";
        Task t1=repository.getTask(name,description);
        String description1="Cuts trees higher than 8 meters";
        Task t2=repository.getTask(name,description1);
        List<Task> l1=new ArrayList<Task>();
        l1.add(t1);
        boolean expected=false;
        //Act
        List<Task> l2=repository.getTaskList();
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected, result);
    }
    @Test
    void getTaskListEmpty() {
        List<Task> l1=new ArrayList<Task>();
        boolean expected=true;
        //Act
        List<Task> l2=repository.getTaskList();
        //Assert
        boolean result=l1.equals(l2);
        assertEquals(expected, result);
    }
}