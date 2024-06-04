package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class JobController {
    private Repositories repositories;
    private final JobRepository jobRepository;

    public JobController() {
        Repositories repositories = Repositories.getInstance();
        jobRepository=repositories.getJobRepository();
    }

    public Job createJob(String name) {
        return jobRepository.addJobByName(name);
    }

    public List<Job> findAllJobs() {
        return jobRepository.getJobList();
    }
}



