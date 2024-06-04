package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AssignSkillsUI implements Runnable {
    private final AssignSkillsController controller;
    private String ID;
    private List<String> skillsName;

    public AssignSkillsUI () {
        controller= new AssignSkillsController();
        skillsName=new ArrayList<String>();
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        requestCollaborator();
        requestSkills();
        submitData();
    }

    private void requestCollaborator() {

        List<Collaborator> collaborators=controller.getCollaboratorList();
        List<String> collaboratorsID=new ArrayList<String>();
        for (Collaborator c:collaborators) {
            System.out.println(c);
            collaboratorsID.add(c.getIDNumber());
        }
        ID=null;
        while(ID==null){
            ID = Utils.readLineFromConsole("Select the ID number of the collaborator (write the exact ID): ");
            if (!collaboratorsID.contains(ID)){
                ID=null;
                System.out.println("The ID number selected is not in the collaborator list, retry");
                continue;
            }
        }
    }
    private void  requestSkills() {
        List<Skill> skills=controller.getSkillsList();
        List<String> check=new ArrayList<String>();
        boolean answer=false;
        while (answer==false) {
            for (Skill s:skills) {
                System.out.println(s);
                check.add(s.getName());
            }
            String skill=null;
            while (skill==null){
                skill=Utils.readLineFromConsole("Select the name of the skill to add (letters and spaces only): ").toLowerCase();
                if (!check.contains(skill)) {
                    skill=null;
                    System.out.println("The selected skill is not in the list, retry");
                    continue;
                }
                skillsName.add(skill);
            }
            String ans=Utils.readLineFromConsole("Do you want to continue to add skills?");
            if (ans.equals("yes")) {answer=false;}
            else {answer=true;}
        }
    }

    private void submitData() {
        try {
            boolean result = controller.assignSkillsToCollaborator(skillsName,ID);
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
