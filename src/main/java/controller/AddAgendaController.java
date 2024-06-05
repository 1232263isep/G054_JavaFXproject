package controller;

import model.Entry;
import model.repository.AuthenticationRepository;
import model.repository.EntryRepository;
import model.repository.Repositories;

import java.util.Date;
import java.util.List;

public class AddAgendaController {
    private Repositories repositories;
    private EntryRepository entryRepository;
    private AuthenticationRepository authenticationRepository;
    private String userName;

    public AddAgendaController() {
        Repositories repositories = Repositories.getInstance();
        entryRepository = repositories.getToDoList();
        authenticationRepository = repositories.getAuthenticationRepository();
        userName=authenticationRepository.getCurrentUserSession().getUserId().toString();
    }

    /**
     *
     * @return the to-do-list in his green spaces, null if doesn't exist
     */
    public List<Entry> getToDoList() {return entryRepository.getToDoList(userName);
    }

    /**
     *
     * @param entry the entry to add in the agenda
     * @param startingDate the date of start of the task
     * @return true if the entry is added to the agenda, false if the entry was already in the agenda, has been already planned or username doesn't exist
     */
    public boolean addAgenda(Entry entry, Date startingDate) {return entryRepository.addAgenda(entry,startingDate,userName);
    }
}

