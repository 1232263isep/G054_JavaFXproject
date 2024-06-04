package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> taskList;

    public TaskRepository() {
        taskList = new ArrayList<Task>();
    }

    /**
     *
     * @return the list of tasks
     */
    public List<Task> getTaskList() {return List.copyOf(taskList);}

    /**
     *
     * @param name the name of the task
     * @param description the description of the task
     * @return an object task with inputted datas
     */
    public Task getTask(String name, String description){
        for (Task task : taskList) {
            if (task.getName().equals(name) && task.getDescription().equals(description)){
                return task;
            }
        }
        Task newTask= new Task(name, description);
        taskList.add(newTask);
        return newTask;
    }
}