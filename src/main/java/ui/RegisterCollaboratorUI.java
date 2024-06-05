package ui;

import controller.RegisterCollaboratorController;
import model.Job;
import ui.utils.Utils;

import java.util.Date;
import java.util.List;


public class RegisterCollaboratorUI implements Runnable {
    private final RegisterCollaboratorController controller;
    private String name;
    private Date birthdate;
    private Date admissionDate;
    private String street;
    private String city;
    private String zipcode;
    private int phoneNumber;
    private String email;
    private int taxNumber;
    private String IDDocType;
    private String IDNumber;
    private Job job;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        requestCollaboratorData();
        requestJob();
        submitData();
    }


    private void requestCollaboratorData() {
        Date today=new Date();
        name = null;
        while (name==null){
            String[] arr=null;
            try {
                name=Utils.readLineFromConsole("Enter the name of the collaborator (letters and spaces only): ");
                arr=name.split(" ");
            } catch (Exception e){
                name=null;
                System.out.println("Invalid format for the name, retry");
                continue;
            }
            if (arr.length>6){
                name=null;
                System.out.println("The name can't be longer than 6 words");
                continue;
            }
        }
        birthdate=null;
        while (birthdate==null){
            try {
                birthdate = new Date(Utils.readLineFromConsole("Enter the birthdate of the collaborator (yyyy/mm/dd): "));
            } catch (Exception e) {
                System.out.println("Invalid birthdate format, retry");
                continue;
            }
            if (today.getYear()-birthdate.getYear()<18){
                birthdate=null;
                System.out.println("The collaborator is a minor, impossible to register a collaborator with less than 18 years");
                continue;
            }
        }
        admissionDate=null;
        while (admissionDate==null){
            try {
                admissionDate = new Date(Utils.readLineFromConsole("Enter the admission date of the collaborator (yyyy/mm/dd): "));
            } catch (Exception e) {
                System.out.println("Invalid admission date format, retry");
                continue;
            }
            if ( today.compareTo(admissionDate)<0) {
                admissionDate = null;
                System.out.println("Impossible register a collaborator that is not alredy admitted");
                continue;
            }
        }
        street = Utils.readLineFromConsole("Enter the street of the collaborator: ");
        city = Utils.readLineFromConsole("Enter the city of the collaborator: ");
        zipcode = Utils.readLineFromConsole("Enter the zipcode of the collaborator: ");
        phoneNumber=0;
        while (phoneNumber==0) {
            try {
                phoneNumber = Integer.parseInt(Utils.readLineFromConsole("Enter the phone number of the collaborator (9 numbers only): "));
            } catch (Exception e) {
                phoneNumber = 0;
                System.out.println("Invalid phone number format, retry");
                continue;
            }
            if (phoneNumber<100000000 || phoneNumber>999999999){
                phoneNumber=0;
                System.out.println("Phone number is formed by 9 numbers, the format is wrong");
                continue;
            }
        }
        email=null;
        while (email==null) {
            email = Utils.readLineFromConsole("Enter the email of the collaborator (prefix@domain): ");
            String[] fields=email.split("@");
            if (fields.length!=2) {
                email = null;
                System.out.println("Wrong format, mail has to be like this: prefix@domain");
                continue;
            }
        }
        taxNumber=0;
        while (taxNumber==0){
            try {
                taxNumber = Integer.parseInt(Utils.readLineFromConsole("Enter the taxpayer number of the collaborator"));
            } catch (Exception e){
                taxNumber=0;
                System.out.println("Invalid format for the taxpayer number, it has to be made by 9 numbers");
                continue;
            }
            if (taxNumber<100000000 || taxNumber>999999999){
                taxNumber=0;
                System.out.println("Taxpayer number is formed by 9 numbers, the format is wrong");
                continue;
            }
        }

        System.out.println("1 - ID card");
        System.out.println("2 - Passport");
        String answer=null;
        while (answer==null){
            answer=Utils.readLineFromConsole("Choose a type of ID document:");
            if (answer.equals("1")) {
                IDDocType = "ID card";
                IDNumber = null;
                while (IDNumber == null) {
                    IDNumber = Utils.readLineFromConsole("Enter the ID number of the collaborator (format: 12345678-1AA1): ");
                    String regex = "\\d{8}-\\d[A-Z]{2}\\d";
                    if (!IDNumber.matches(regex)) {
                        IDNumber = null;
                        System.out.println("Invalid ID format, the format is wrong");
                        continue;
                    }
                }
            }
            else if(answer.equals("2")) {
                IDDocType="Passport";
                IDNumber = null;
                while (IDNumber == null) {
                    IDNumber=Utils.readLineFromConsole("Enter the ID number of the collaborator (format: 12345678-1AA1): ");
                    String regex="[A-Z]\\d{6}";
                    if(!IDNumber.matches(regex)) {
                        IDNumber = null;
                        System.out.println("Invalid ID format, the format is wrong");
                        continue;
                    }
                }
            }
            else{
                answer=null;
                System.out.println("Invalid option selected, pick 1 or 2");
                continue;
            }
        }
    }

    private void requestJob() {

        List<Job> jobList = controller.getjobList();
        boolean flag = true;
        while (flag == true) {

            for (Job j : jobList)
                System.out.println(j);
            String jobName =Utils.readLineFromConsole("Select a job from the list: ").toLowerCase();
            job = controller.getJobByName(jobName);
            if (job == null) {
                System.out.println("Error, the job is not in the database");
                continue;
            }
            flag = false;
        }
    }

    private void submitData() {
        try {
            boolean result = controller.registerCollaborator(name, birthdate, admissionDate, street, city, zipcode, phoneNumber, email, IDDocType, IDNumber, job);
            if (result == true) {
                System.out.println("\nCollaborator successfully registered!");
            } else {
                System.out.println("\nCollaborator registration failed!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}