module com.example.gymsystem_layeredproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires static lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;

    // Main package
    opens com.example.gymsystem_layeredproject to javafx.fxml;
    exports com.example.gymsystem_layeredproject;
    opens com.example.gymsystem_layeredproject.dto to javafx.base;


    // Required for FXML to access controllers
    opens com.example.gymsystem_layeredproject.controller to javafx.fxml;
    exports com.example.gymsystem_layeredproject.controller;

}
