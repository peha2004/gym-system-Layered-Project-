package com.example.gymsystem_layeredproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private Label lblIncorrectid;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;



    public void loginbtn(ActionEvent actionEvent) {
       if (userName.getText().equals("admin")&& password.getText().equals("admin")){
           try {
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DashBord.fxml"));
               Parent dashboard = fxmlLoader.load();

               Stage dashboardStage = new Stage();
               dashboardStage.setScene(new Scene(dashboard));
               dashboardStage.setTitle("Dashboard");
               dashboardStage.setResizable(false);
               dashboardStage.show();


               ((Stage) userName.getScene().getWindow()).close();

           } catch (IOException e) {
               e.printStackTrace();
               new Alert(Alert.AlertType.ERROR, "Failed to load Dashboard.fxml").show();
           }

       }
    }
}
