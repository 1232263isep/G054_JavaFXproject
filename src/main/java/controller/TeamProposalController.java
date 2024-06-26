package controller;

import controller.session.ApplicationSession;
import model.Collaborator;
import model.Entry;
import model.Notification;
import model.Skill;
import model.Team;
import model.repository.CollaboratorRepository;
import model.repository.EntryRepository;
import model.repository.NotificationRepository;
import model.repository.Repositories;
import model.repository.SkillRepository;
import model.repository.TeamRepository;

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

    public Team generateTeamProposalOld(int maxSize, int minSize, List<String> skillNames) {
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
    public boolean assignGeneratedTeamToEntry(Entry entry, Team team) {
        if (team != null && entry.assignTeam(team)) {
            notifyTeamMembers(team, "You have been assigned to a new task: " + entry.getTask().getDescription());
            System.out.println(team.toString());
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


    public List<Team> getAllTeams() {
        return teamRepository.getTeams();
    }
}
