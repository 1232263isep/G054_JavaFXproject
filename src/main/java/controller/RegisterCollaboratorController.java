package controller;

import model.Job;
import model.repository.CollaboratorRepository;
import model.repository.JobRepository;
import model.repository.Repositories;

import java.util.Date;
import java.util.List;

public class RegisterCollaboratorController {
    private Repositories repositories;
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;

    public RegisterCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        jobRepository = repositories.getJobRepository();
    }

    /**
     *
     * @return the list of jobs
     */
    public List<Job> getjobList(){
        return  jobRepository.getJobList();
    }

    /**
     *
     * @param name of the job
     * @return the job
     */
    public Job getJobByName(String name){
        return jobRepository.getJobByName(name);
    }

    /**
     *
     * *@param collaborator's attributes
     * @return true if the collaborator has been created, false in other case
     */
    public boolean registerCollaborator(String name, Date birthdate, Date admissionDate, String street, String city, String zipcode, int phoneNumber, String email, String IDDocType, String IDNumber, Job job){
        return collaboratorRepository.registerCollaborator(name,birthdate,admissionDate,street,city,zipcode,phoneNumber,email,IDDocType,IDNumber,job);
    }
}


