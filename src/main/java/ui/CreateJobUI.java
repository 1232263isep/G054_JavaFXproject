package ui;

import controller.JobController;
import model.Job;
import ui.utils.Utils;


public class CreateJobUI implements Runnable {
    private final JobController controller = new JobController();
    private String jobName;

    @Override
    public void run() {
        System.out.println("\n\n--- Create Job ---\n");
        requestData();

        if (submitData()) {
            System.out.println("Job successfully created!");
        } else {
            System.out.println("Failed to create job. The job may already exist.");
        }
    }

    private void requestData() {
        this.jobName = Utils.readLineFromConsole("Enter Job Name: ").toLowerCase();
    }

    private boolean submitData() {
        Job job = controller.createJob(jobName);
        return job != null;
    }
}