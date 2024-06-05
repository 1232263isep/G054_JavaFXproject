package model.repository;

import model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository {
    private final List<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    public boolean addTeam(Team team) {
        if (team == null || containsTeam(team.getId())) {
            return false;
        }
        teams.add(team);
        return true;
    }

    public Optional<Team> findTeamById(String teamId) {
        return teams.stream()
                .filter(team -> team.getId().equalsIgnoreCase(teamId))
                .findFirst();
    }

    public boolean containsTeam(String teamId) {
        return teams.stream().anyMatch(team -> team.getId().equalsIgnoreCase(teamId));
    }

    public List<Team> getTeams() {
        return List.copyOf(teams);
    }
}
