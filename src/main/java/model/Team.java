package model;

import java.util.List;

public class Team {
    private final String id;
    private final List<Collaborator> collaborators;

    public Team(String id, List<Collaborator> collaborators) {
        this.id = id;
        this.collaborators = collaborators;
    }

    public String getId() {
        return id;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }
}
