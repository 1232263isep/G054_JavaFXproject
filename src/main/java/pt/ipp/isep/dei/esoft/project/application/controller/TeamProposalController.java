package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Notification;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.EntryRepository;
import pt.ipp.isep.dei.esoft.project.repository.NotificationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamProposalController {

    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final EntryRepository entryRepository;
    private final NotificationRepository notificationRepository;
    private final TeamRepository teamRepository;

    public TeamProposalController() {
        Repositories repositories = Repositories.getInstance();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.skillRepository = repositories.getSkillRepository();
        this.entryRepository = repositories.getEntryRepository();
        this.notificationRepository = repositories.getNotificationRepository();
        this.teamRepository = repositories.getTeamRepository();
    }

    public Team generateTeamProposal(int maxSize, int minSize, List<String> skillNames) {
        List<Skill> requiredSkills = skillNames.stream()
                .map(skillRepository::findSkillByName)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        List<Collaborator> potentialMembers = findPotentialTeamMembers(requiredSkills);

        if (potentialMembers.size() < minSize) {
            throw new IllegalArgumentException("Not enough collaborators with the required skills.");
        }

        List<Collaborator> teamMembers = potentialMembers.stream().limit(maxSize).collect(Collectors.toList());
        return new Team("GeneratedTeam", teamMembers);
    }

    public List<Collaborator> findPotentialTeamMembers(List<Skill> requiredSkills) {
        return collaboratorRepository.getCollaboratorList().stream()
                .filter(collaborator -> collaborator.getSkills().containsAll(requiredSkills))
                .collect(Collectors.toList());
    }

    public List<Skill> getAllSkills() {
        return collaboratorRepository.getCollaboratorList().stream()
                .flatMap(collaborator -> collaborator.getSkills().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getCollaboratorList();
    }

    public boolean assignGeneratedTeamToEntry(Entry entry, List<String> skillNames, int maxSize, int minSize) {
        Team team = generateTeamProposal(maxSize, minSize, skillNames);
        if (team != null && entry.assignTeam(team)) {
            teamRepository.addTeam(team);
            notifyTeamMembers(team, "You have been assigned to a new task: " + entry.getTask().getDescription());
            System.out.println(teamRepository.toString());
            return true;
        }
        return false;
    }

    private void notifyTeamMembers(Team team, String message) {
        List<Collaborator> collaborators = team.getCollaborators();
        for (Collaborator collaborator : collaborators) {
            Notification notification = new Notification(collaborator.getEmail(), message);
            notificationRepository.addNotification(notification);
        }
    }

    public List<Entry> getAllEntries() {
        String userName = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return entryRepository.getAllEntriesForUser(userName);
    }

    public boolean assignTeamToEntry(Entry entry, String teamId) {
        Optional<Team> optionalTeam = teamRepository.findTeamById(teamId);

        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            entry.assignTeam(team);
            notifyTeamMembers(team, "You have been assigned to a new task: " + entry.getTask().getDescription());
            return true;
        }
        return false;
    }
}
