package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.PaymentBOImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
//import org.example.gymsystem.Model.PaymentModel;
import com.example.gymsystem_layeredproject.dto.PaymentDTO;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddPaymentController implements Initializable {
    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMemberId;

    @FXML
    private ComboBox<String> txtMethod;

    @FXML
    private ComboBox<String> txtPlan;

    PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    void onSubmit(ActionEvent event) {
        String memberId = txtMemberId.getText();
        String plan = txtPlan.getValue();
        String method = txtMethod.getValue();
        String amountText = txtAmount.getText();

        if (memberId.isEmpty() || plan == null || method == null || amountText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
            return;
        }
        try {
            BigDecimal amount = new BigDecimal(amountText);
            String paymentId = paymentBO.generatePaymentId();
            LocalDate today = LocalDate.now();

            PaymentDTO dto = new PaymentDTO(paymentId, memberId, plan, method, today, amount);
            boolean isSaved = paymentBO.save(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment added successfully!").show();

                txtMemberId.clear();
                txtPlan.setValue(null);
                txtMethod.setValue(null);
                txtAmount.clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid amount").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPlan.setItems(FXCollections.observableArrayList("Basic", "Standard", "Premium"));
        txtMethod.setItems(FXCollections.observableArrayList("Cash", "Card", "Online"));
    }
}
