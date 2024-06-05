package controller;

import model.Collaborator;
import model.Entry;
import model.repository.CollaboratorRepository;
import model.repository.EntryRepository;
import model.repository.Repositories;

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
        return entryRepository.getEntriesForCollaborator(collaborator).stream()
                .filter(entry -> {
                    Date entryDate = entry.getDate();
                    return (entryDate.equals(startDate) || entryDate.after(startDate)) &&
                            (entryDate.equals(endDate) || entryDate.before(endDate));
                })
                .sorted((entry1, entry2) -> entry1.getDate().compareTo(entry2.getDate()))
                .collect(Collectors.toList());
    }
}
