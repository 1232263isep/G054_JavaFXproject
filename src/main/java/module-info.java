module org.example.javafxprojectpprog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires AuthLib;
    requires commons.lang3;
    requires java.logging;


    opens org.example.javafxprojectpprog to javafx.fxml;
    exports org.example.javafxprojectpprog;

    opens pt.ipp.isep.dei.esoft.project.ui.console.authorization to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.console.authorization;

    opens pt.ipp.isep.dei.esoft.project.ui.console.menu to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.console.menu;
}