package controller;

import model.Job;
import model.repository.JobRepository;
import model.repository.Repositories;

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



