package controller;

import model.Collaborator;
import model.Entry;
import model.repository.CollaboratorRepository;
import model.repository.EntryRepository;
import model.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EntryController {

    private final CollaboratorRepository collaboratorRepository;
    private final EntryRepository entryRepository;

    public EntryController() {
        Repositories repositories = Repositories.getInstance();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.entryRepository = repositories.getEntryRepository();
    }

    public List<Entry> getEntriesForCollaboratorBetweenDates(Collaborator collaborator, Date startDate, Date endDate) {
        List<Entry> entries=entryRepository.getEntriesForCollaborator(collaborator);
        List<Entry> result= new ArrayList<Entry>();
        for (Entry entry:entries){
            if (startDate.compareTo(entry.getDate())<=0 && endDate.compareTo(entry.getDate())>=0){
                result.add(entry);
            }
        }
        return result;
    }
}
