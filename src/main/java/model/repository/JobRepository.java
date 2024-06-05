package model.repository;

import model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {
    private List<Job> jobList;

    public JobRepository() {
        this.jobList = new ArrayList<>();
    }

    public List<Job> getJobList() {
        return List.copyOf(this.jobList);
    }

    /**
     * Saves a job to the repository.
     * If a job with the same name already exists, it is not added.
     *
     * @param job the job to save
     * @return the saved job, or null if the job was not saved due to a duplicate name
     */
    public Job save(Job job) {
        if (job == null || exists(job.getName())) {
            return null; // Guard clause for null input and existing job
        }
        this.jobList.add(job);
        return job.clone(); // Return a clone to preserve encapsulation
    }

    public Job addJobByName(String name) {
        if (name == null || name.isEmpty() || exists(name)) {
            return null;
        }
        Job newJob = new Job(name);
        jobList.add(newJob);
        return newJob;
    }
    /**
     * Checks if a job with the given name already exists in the repository.
     *
     * @param name the name of the job to check
     * @return true if a job with the same name exists, false otherwise
     */
    private boolean exists(String name) {
        return jobList.stream().anyMatch(job -> job.getName().equals(name));
    }

    /**
     * Retrieves a job by name.
     *
     * @param name the name of the job
     * @return the job, or null if not found
     */
    public Job getJobByName(String name) {
        return jobList.stream()
                .filter(job -> job.getName().equals(name))
                .findFirst()
                .map(Job::clone) // Clone the job to avoid external mutations
                .orElse(null);
    }
}
