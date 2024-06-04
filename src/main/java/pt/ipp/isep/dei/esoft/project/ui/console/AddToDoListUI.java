package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class AddToDoListUI implements Runnable {
     private AddToDoListController controller;
    private String name;
    private String description;
    private Duration duration;
    private String urgency;
    private String greenSpaceName;

    public AddToDoListUI() {
        controller= new AddToDoListController();
    }

    public void run() {
        System.out.println("\n\n--- Add a new entry in the to-do-list ------------------------");
        requestTask();
        requestEntryData();
        requestGreenSpace();
        submitData();
    }
    private void requestTask(){
        List<Task> taskList=controller.getTaskList();
        int i=1;
        for (Task t:taskList){
            System.out.println(i+". "+t);
            i++;
        }
        System.out.println("0. New Task");
        int choice=-1;
        while (choice==-1) {
            try {
                choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the selected task, or 0 to create a new task"));
            } catch (Exception e) {
                choice=-1;
                System.out.println("Wrong format, please enter the number of the selected task, or 0 to create a new task");
                continue;
            }
            if (choice==0){
                name=Utils.readLineFromConsole("Enter the name of the new task").toLowerCase();
                description=Utils.readLineFromConsole("Enter the description of the new task").toLowerCase();
            }
            else if(choice<=taskList.size()){
                name=taskList.get(choice-1).getName();
                description=taskList.get(choice-1).getDescription();
            }
            else {
                choice=-1;
                System.out.println("Wrong number entered, please retry");
                continue;
            }
        }
    }
    private void  requestEntryData() {
        duration=null;
        while (duration == null) {
            System.out.println("Please enter a valid duration: ");
            int days = -1;
            while (days == -1) {
                try {
                    days = Integer.parseInt(Utils.readLineFromConsole("Days: "));
                } catch (Exception e) {
                    days = -1;
                    System.out.println("Error, wrong format, please insert the number of days required");
                    continue;
                }
            }
            int hours = -1;
            while (hours == -1) {
                try {
                    hours = Integer.parseInt(Utils.readLineFromConsole("Hours: "));
                } catch (Exception e) {
                    hours = -1;
                    System.out.println("Error, wrong format, please insert the number of hours required");
                    continue;
                }
            }
            String durationStr = "P" + days + "DT" + hours + "H" ;
            try {
                duration = Duration.parse(durationStr);
            } catch (Exception e) {
                duration = null;
                System.out.println("Invalid duration, please try again.");
                continue;
            }
        }
        urgency=null;
        while (urgency==null){
            int choice=0;
            System.out.println("Please enter the level of urgency: ");
            System.out.println("1) Low");
            System.out.println("2) Medium");
            System.out.println("3) High");
            while (choice == 0) {
                try {
                    choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the choosen option: "));
                } catch (Exception e) {
                    choice = 0;
                    System.out.println("Invalid format, please select one option");
                    continue;
                }
                if (choice < 0 || choice > 3) {
                    choice = 0;
                    System.out.println("Number selected out of range, please retry");
                    continue;
                }
                if (choice == 1) {
                    urgency = "Low";
                } else if (choice == 2) {
                    urgency = "Medium";
                } else if (choice == 3) {
                    urgency = "High";
                }
            }
        }
    }


    private void requestGreenSpace() {
        List<GreenSpace> greenSpaces=controller.getGreenSpaceList();
        List<String> greenSpacesName=new ArrayList<String>();
        for (GreenSpace g:greenSpaces) {
            System.out.println(g);
            greenSpacesName.add(g.getName());
        }
        greenSpaceName=null;
        while(greenSpaceName==null){
            greenSpaceName = Utils.readLineFromConsole("Select the name of the Green Space: ").toLowerCase();
            if (!greenSpacesName.contains(greenSpaceName)){
                greenSpaceName=null;
                System.out.println("The name selected is not in the green space list, retry");
                continue;
            }
        }
    }

    private void submitData() {

        try {
            boolean result = controller.addToDoList(name,description,duration,urgency,greenSpaceName);
            if (result == true) {
                System.out.println("\nEntry successfully registered in to-do-list!");
            } else {
                System.out.println("\nEntry registration failed!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
