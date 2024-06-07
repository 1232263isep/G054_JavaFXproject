package model;

import model.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class JobTest {
    private JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        jobRepository = new JobRepository();  // Assuming JobRepository is ready to use
    }

    @Test
    void testCreateJobSuccessfully() {
        // Arrange
        String jobName = "Software Developer";

        // Act
        Job newJob = new Job(jobName);
        Job addition = jobRepository.save(newJob);

        boolean added;

        if(addition == null){
             added= false;
        }
        else{
             added = true;
        }
        // Assert
        assertTrue(added, "Job should be added successfully.");
        assertNotNull(newJob, "The created job should not be null.");
        assertEquals(jobName, newJob.getName(), "The job name should match the input.");
    }

    @Test
    void testListJobsContainsNewlyAddedJob() {
        // Arrange
        String jobName = "Data Analyst";
        Job newJob = new Job(jobName);
        jobRepository.save(newJob);

        // Act
        List<Job> jobs = jobRepository.getJobList();

        // Assert
        assertTrue(jobs.contains(newJob), "The list of jobs should contain the newly added job.");
    }

    @Test
    void testJobNotAddedWhenAlreadyExists() {
        // Arrange
        String jobName = "Graphic Designer";
        Job job1 = new Job(jobName);
        jobRepository.save(job1);

        // Act
        Job job2 = new Job(jobName);
        Job jobAddedAgain = jobRepository.save(job2);
        boolean addedAgain;

        if(jobAddedAgain == null){
            addedAgain= false;
        }
        else{
            addedAgain = true;
        }

        // Assert
        assertFalse(addedAgain, "Job should not be added again if it already exists.");
    }
}