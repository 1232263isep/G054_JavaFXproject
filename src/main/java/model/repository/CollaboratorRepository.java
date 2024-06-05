package model.repository;

import model.Collaborator;
import model.Job;
import model.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CollaboratorRepository {
    private List<Collaborator> collaboratorList;
    public CollaboratorRepository() {collaboratorList=new ArrayList<Collaborator>();}

    /**
     *
     * @return list of collaborator
     */
    public List<Collaborator> getCollaboratorList() {return List.copyOf(collaboratorList);}
    /**
     *
     * @param skills list of skills
     * @param ID number of the collaborator
     * @return true if at least one skill are added to collaborator, false if none
     */
    public boolean addSkills(List<Skill> skills, String ID) {
        for (Collaborator c: collaboratorList) {
            if (c.getIDNumber().equals(ID)){
                return c.addSkills(skills);
            }
        }
        return false;
    }

    /**
     * in input argument of the collaborator
     * @return true if the collaborator is added to the list, false if the collaborator already exist
     */
    public boolean registerCollaborator(String name, Date birthdate, Date admissionDate, String street, String city, String zipcode, int phoneNumber, String email, String IDDocType, String IDNumber, Job job) {
        Collaborator c=new Collaborator(name,birthdate,admissionDate,street,city,zipcode,phoneNumber,email,IDDocType,IDNumber,job);
        if (collaboratorList.contains(c)) {
            return false;
        }
        collaboratorList.add(c);
        return true;
    }
    public Collaborator findCollaboratorByEmail(String email) {
        for(Collaborator collaborator : collaboratorList){
            if(Objects.equals(collaborator.getEmail(), email)){
                return collaborator;
            }

        }

        System.out.println("No collaborator was found with this email.");
        return null;
    }
}