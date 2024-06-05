package ui;

import controller.AddAgendaController;
import model.Entry;
import ui.utils.Utils;
import java.util.Date;
import java.util.List;


public class AddAgendaUI implements Runnable {
     private AddAgendaController controller;
    private Entry entry;
    private Date startingDate;

    public AddAgendaUI() {
        controller= new AddAgendaController();
    }

    public void run() {
        System.out.println("\n\n--- Add a new entry in the to-do-list ------------------------");
        requestEntry();
        requestStartingDate();
        submitData();
    }
    private void requestEntry(){
        List<Entry> toDoList=controller.getToDoList();
        int i=1;
        for (Entry e:toDoList){
            System.out.println(i+". "+e);
            i++;
        }
        int choice=-1;
        while (choice==-1) {
            try {
                choice = Integer.parseInt(Utils.readLineFromConsole("Enter the number of the selected task"));
            } catch (Exception e) {
                choice=-1;
                System.out.println("Wrong format, please enter the number of the selected task");
                continue;
            }
            if(choice<=toDoList.size()){
                entry=toDoList.get(choice-1);
            }
            else {
                choice=-1;
                System.out.println("Wrong number entered, please retry");
                continue;
            }
        }
    }
    private void  requestStartingDate() {
        Date today=new Date();
        startingDate = null;
        while (startingDate == null) {
            try {
                startingDate = new Date(Utils.readLineFromConsole("Enter the starting date (yyyy/mm/dd): "));
            } catch (Exception e) {
                System.out.println("Invalid date format, retry");
                continue;
            }
            if (today.compareTo(startingDate) > 0) {
                startingDate = null;
                System.out.println("The can't be in the past, please retry");
                continue;
            }
        }
    }

    private void submitData() {
        try {
            boolean result = controller.addAgenda(entry,startingDate);
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
