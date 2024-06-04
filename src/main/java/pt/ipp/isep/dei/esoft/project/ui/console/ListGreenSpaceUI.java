package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class ListGreenSpaceUI implements Runnable {
    private final ListGreenSpaceController controller = new ListGreenSpaceController();

    public void run() {
        String sortingAlgorithm = requestSortingAlgorithm();
        List<GreenSpace> greenSpaces = controller.listGreenSpaces();
        displayGreenSpaces(greenSpaces);
    }

    private String requestSortingAlgorithm() {
        System.out.println("Select Sorting Algorithm:");
        System.out.println("1 - Default");
        System.out.println("2 - Alternative");
        int choice = Utils.readIntegerFromConsole("Enter your choice: ");
        switch (choice) {
            case 2:
                return "alternative";
            case 1:
            default:
                return "default";
        }
    }

    private void displayGreenSpaces(List<GreenSpace> greenSpaces) {
        System.out.println("\nList of Green Spaces managed by you (sorted by size in descending order):");
        for (GreenSpace greenSpace : greenSpaces) {
            System.out.println(" - " + greenSpace.getName() + " (Area: " + greenSpace.getArea() + ")");
        }
    }
}
