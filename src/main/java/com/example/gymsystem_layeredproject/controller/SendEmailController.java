package com.example.gymsystem_layeredproject.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailController {
    public TextArea txtMessage;
    public TextField txtSubject;
    public TextField txtTo;

    public void setRecipientEmail(String email) {
        txtTo.setText(email);
    }


    public void btnSendOnAction(ActionEvent actionEvent) {
        String To = txtTo.getText();
        String Subject = txtSubject.getText();
        String message = txtMessage.getText();
        if (To == null || Subject == null || message == null) {
            return;
        }
        String from = "fghhfghvelonix@gmail.com";
        String password = "tctk rqqx jhnr kfju";



        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        try {

            Message mimeMessage  = new MimeMessage(session);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
            mimeMessage.setSubject(Subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            new Alert(Alert.AlertType.INFORMATION, "Mail sent successfully").show();



        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
            e.printStackTrace();
        }
    }
    }

