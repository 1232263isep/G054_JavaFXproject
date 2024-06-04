package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.Duration;
import java.util.List;

public class AddToDoListController {
    private Repositories repositories;
    private GreenSpaceRepository greenSpaceRepository;
    private EntryRepository entryRepository;
    private AuthenticationRepository authenticationRepository;
    private TaskRepository taskRepository;
    private String userName;

    public AddToDoListController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
        entryRepository = repositories.getToDoList();
        authenticationRepository = repositories.getAuthenticationRepository();
        taskRepository=repositories.getTaskRepository();
        userName=authenticationRepository.getCurrentUserSession().getUserId().toString();

    }

    /**
     *
     * @return the list of the green spaces created by the user
     */
    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceRepository.getGreenSpaceList(userName);
    }

    /**
     *
     * @param name of the task
     * @param description of the task
     * @param duration of the task
     * @param urgency of the task
     * @param greenSpaceName where the task will be performed
     * @return true if the entry has been added in the to-do-List, false if the entry was already in the to-do-list
     */
    public boolean addToDoList(String name, String description, Duration duration, String urgency, String greenSpaceName) {
        GreenSpace g=greenSpaceRepository.getGreenSpaceByName(greenSpaceName,userName);
        Task t=taskRepository.getTask(name,description);
        return entryRepository.addEntryToDoList(t,duration,urgency,g,userName);
    }

    /**
     *
     *
     * @return the list of tasks
     */
    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }
}

