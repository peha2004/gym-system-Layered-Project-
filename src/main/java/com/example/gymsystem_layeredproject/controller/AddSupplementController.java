package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.SupplementBOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
//import org.example.gymsystem.Model.SupplementModel;
import com.example.gymsystem_layeredproject.dto.SupplementDTO;

import java.sql.SQLException;

public class AddSupplementController {
    @FXML
    private TextField txtSupplementId;

    @FXML
    private TextField txtSupplementName;

    @FXML
    private TextField txtSupplementPrice;

    SupplementBOImpl supplementBO = (SupplementBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLEMENT);
    @FXML
    private TextField txtSupplementQty;
    public void onSubmit(ActionEvent actionEvent) {
        String id = txtSupplementId.getText();
        String name = txtSupplementName.getText();
        String priceText = txtSupplementPrice.getText();
        String qtyText = txtSupplementQty.getText();

        if (id.isEmpty() || name.isEmpty() || priceText.isEmpty() || qtyText.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        double price;
        int qty;

        try {
            price = Double.parseDouble(priceText);
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException e) {
            showAlert("Invalid price or quantity.");
            return;
        }

        SupplementDTO supplement = new SupplementDTO(id, name, price, qty);

        try {
            boolean success = supplementBO.addSupplement(supplement);
            if (success) {
                showAlert("Supplement added successfully!");
                clearForm();
            } else {
                showAlert("Failed to add supplement.");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearForm() {
        txtSupplementId.clear();
        txtSupplementName.clear();
        txtSupplementPrice.clear();
        txtSupplementQty.clear();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    @FXML
    public void initialize() {
        try {
            txtSupplementId.setText(supplementBO.generateNextSupplementId());
            txtSupplementId.setEditable(false);
        } catch (SQLException e) {
            showAlert("Error generating ID: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    }

