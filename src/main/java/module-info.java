module org.example.javafxprojectpprog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires AuthLib;
    requires commons.lang3;
    requires java.logging;


    opens ui to javafx.fxml;
    exports ui;

//    opens pt.ipp.isep.dei.esoft.project.ui.console.authorization to javafx.fxml;
  //  exports pt.ipp.isep.dei.esoft.project.ui.console.authorization;

   // opens pt.ipp.isep.dei.esoft.project.ui.console.menu to javafx.fxml;
    //exports pt.ipp.isep.dei.esoft.project.ui.console.menu;
 //   exports pt.ipp.isep.dei.esoft.project.ui.gui;
  //  opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
}