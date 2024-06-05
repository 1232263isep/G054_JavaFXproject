package ui;

import controller.RegisterGreenSpaceController;

import ui.utils.Utils;

public class RegisterGreenSpaceUI implements Runnable {
    private RegisterGreenSpaceController controller;
    private String type;
    private int area;
    private String name;
    private String address;

    public RegisterGreenSpaceUI() {
        controller = new RegisterGreenSpaceController();
    }

    public void run() {
        System.out.println("\n\n--- Register Green Space ------------------------");
        requestData();
        submitData();
    }


    private void requestData() {
        name = Utils.readLineFromConsole("Enter the name of the Green Space: ").toLowerCase();
        type = null;
        while (type == null) {
            System.out.println("1) Garden");
            System.out.println("2) Medium Sized Park");
            System.out.println("3) Large Sized Park");
            int choice = 0;
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
                    type = "Garden";
                } else if (choice == 2) {
                    type = "Medium Sized Park";
                } else if (choice == 3) {
                    type = "Large Sized Park";
                }
            }
        }
        area = 0;
        while (area == 0) {
            try {
                area = Integer.parseInt(Utils.readLineFromConsole("Enter the area of the Green Space (hectares): "));
            } catch (Exception e) {
                System.out.println("Invalid area format, retry");
                continue;
            }
        }
        address = Utils.readLineFromConsole("Enter the address of the Green Space: ");
    }


    private void submitData() {
        try {
            boolean result = controller.registerGreenSpace(name,type,area,address);
            if (result == true) {
                System.out.println("\nGreen Space successfully registered!");
            } else {
                System.out.println("\nGreen Space registration failed!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}