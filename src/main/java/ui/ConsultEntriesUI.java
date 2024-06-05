package ui;

import controller.EntryController;
import model.Collaborator;
import model.Entry;
import model.repository.AuthenticationRepository;
import model.repository.CollaboratorRepository;
import model.repository.Repositories;
import ui.utils.Utils;

import java.util.Date;
import java.util.List;

public class ConsultEntriesUI implements Runnable{

    private final EntryController entryController;
    private final CollaboratorRepository collaboratorRepository;
    private final AuthenticationRepository authenticationRepository;

    public ConsultEntriesUI() {
        Repositories repositories = Repositories.getInstance();
        this.entryController = new EntryController();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    }

    public void run() {

        String email = authenticationRepository.getCurrentUserSession().getUserId().toString();

        Collaborator collaborator = collaboratorRepository.findCollaboratorByEmail(email);
        if (collaborator == null) {
            System.out.println("Collaborator not found.");
            return;
        }

        Date startDate = Utils.readDateFromConsole("Enter start date (yyyy-mm-dd): ");

        Date endDate = Utils.readDateFromConsole("Enter end date (yyyy-mm-dd): ");

        List<Entry> entries = entryController.getEntriesForCollaboratorBetweenDates(collaborator, startDate, endDate);

        System.out.println("Entries assigned to " + collaborator.getName() + " between " + startDate + " and " + endDate + ":");
        entries.forEach(entry -> System.out.println(entry.getTask().getDescription() + " - " + entry.getDate()));
    }
}
