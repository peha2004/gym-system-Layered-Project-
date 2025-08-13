package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.SupplementBOImpl;
import com.example.gymsystem_layeredproject.bo.custom.impl.SupplementTransactionBOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import org.example.gymsystem.Model.SupplementModel;
//import org.example.gymsystem.Model.SupplementTransactionModel;

import com.example.gymsystem_layeredproject.dto.SupplementCartItemDTO;
import com.example.gymsystem_layeredproject.dto.SupplementDTO;
import com.example.gymsystem_layeredproject.dto.SupplementTransactionDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplementController {
    public Button btnAddSupplement;
    public TableColumn columQty;
    public Button btnDeleteSupplemet;
    public Button btnUpdateSupplemet;
    public Button btnHistory;


    @FXML
    private TableColumn<SupplementCartItemDTO, String> columCartMemberId;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnsaveTransaction;

    @FXML
    private ComboBox<String> cmboMemberId;

    @FXML
    private TableColumn<SupplementDTO, Integer> columAvailable;

    @FXML
    private TableColumn<SupplementCartItemDTO, String> columCartName;

    @FXML
    private TableColumn<SupplementCartItemDTO, Double> columCartPrice;

    @FXML
    private TableColumn<SupplementCartItemDTO, Integer> columCartQty;

    @FXML
    private TableColumn<SupplementCartItemDTO, Double> columCartTotal;

    @FXML
    private TableColumn<SupplementDTO, String> columId;

    @FXML
    private TableColumn<SupplementDTO, String> columName;

    @FXML
    private TableColumn<SupplementDTO, Double> columPrice;

    @FXML
    private TableView<SupplementCartItemDTO> tableCart;

    @FXML
    private TableView<SupplementDTO> tableSppliments;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSelectedSupplement;

    @FXML
    private Label txtTotalAmount;

    @FXML
    private Label txtTransactionDate;

    @FXML
    private TextField txtTransactionId;

    SupplementBOImpl supplementBO = (SupplementBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLEMENT);
    SupplementTransactionBOImpl supplementTransactionBO = (SupplementTransactionBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLEMENTTRANSACTION);

    private ObservableList<SupplementDTO> supplementList = FXCollections.observableArrayList();
    private ObservableList<SupplementCartItemDTO> cartList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {

            supplementList.addAll(supplementBO.getAllSupplements());
            tableSppliments.setItems(supplementList);

            cmboMemberId.getItems().addAll("M001", "M002", "M003");

            txtTransactionDate.setText(LocalDate.now().toString());

            try {
                txtTransactionId.setText(supplementTransactionBO.generateNextTransactionId());
                txtTransactionId.setEditable(false);
            } catch (SQLException e) {
                showAlert("Failed to generate transaction ID.");
            }


            columId.setCellValueFactory(new PropertyValueFactory<>("supplementId"));
            columName.setCellValueFactory(new PropertyValueFactory<>("supplemetName"));
            columPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            columQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            columCartMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            columCartName.setCellValueFactory(new PropertyValueFactory<>("supplementName"));
            columCartQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            columCartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            columCartTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

            tableCart.setItems(cartList);


            tableSppliments.setOnMouseClicked(e -> {
                SupplementDTO selected = tableSppliments.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    txtSelectedSupplement.setText(selected.getSupplemetName());
                }
            });

        } catch (SQLException e) {
            showAlert("Error loading data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnsaveTransactionOnAction(ActionEvent actionEvent) {
        String transactionId = txtTransactionId.getText();
        String memberId = cmboMemberId.getValue();
        LocalDate date = LocalDate.parse(txtTransactionDate.getText());

        if (transactionId.isEmpty() || memberId == null || cartList.isEmpty()) {
            showAlert("Please fill all fields and ensure cart is not empty.");
            return;
        }

        double totalAmount = cartList.stream().mapToDouble(SupplementCartItemDTO::getTotal).sum();

        SupplementTransactionDTO transaction = new SupplementTransactionDTO(
                transactionId, memberId, date, totalAmount, new ArrayList<>(cartList));

        try {
            boolean success = supplementTransactionBO.saveTransaction(transaction);
            if (success) {
                showAlert("Transaction saved successfully!");
                clearForm();
                refreshSupplements();
            }
        } catch (SQLException e) {
            showAlert("Failed to save transaction: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        String supplementName = txtSelectedSupplement.getText();
        String quantityText = txtQuantity.getText();
        String memberId = cmboMemberId.getValue();

        if (supplementName.isEmpty() || quantityText.isEmpty() || memberId == null) {
            showAlert("Please select a supplement, enter quantity, and select a member.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid positive number for quantity.");
            return;
        }

        SupplementDTO selected = supplementList.stream()
                .filter(s -> s.getSupplemetName().equals(supplementName))
                .findFirst()
                .orElse(null);

        if (selected == null) {
            showAlert("Supplement not found.");
            return;
        }

        int alreadyInCartQty = cartList.stream()
                .filter(item -> item.getSupplementId().equals(selected.getSupplementId()))
                .mapToInt(SupplementCartItemDTO::getQuantity)
                .sum();

        int availableQty = selected.getQuantity() - alreadyInCartQty;

        if (quantity > availableQty) {
            showAlert("Not enough stock available. Available after cart: " + availableQty);
            return;
        }

        SupplementCartItemDTO cartItem = new SupplementCartItemDTO(
                memberId,
                selected.getSupplementId(),
                selected.getSupplemetName(),
                quantity,
                selected.getPrice());

        cartList.add(cartItem);
        updateTotal();
    }
private void updateTotal() {
    double total = cartList.stream().mapToDouble(SupplementCartItemDTO::getTotal).sum();
    txtTotalAmount.setText(String.format("%.2f", total));
}
private void clearForm() {
    txtTransactionId.clear();
    txtSelectedSupplement.clear();
    txtQuantity.clear();
    cartList.clear();
    txtTotalAmount.setText("0.00");
}
private void refreshSupplements() throws SQLException, ClassNotFoundException {
    supplementList.clear();
    supplementList.addAll(supplementBO.getAllSupplements());
    tableSppliments.refresh();
}
private void showAlert(String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText(msg);
    alert.showAndWait();
}

    public void btnAddSupplementOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addsupplement.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Supplement");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


            loadTableData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTableData() {
        try {
            supplementList.clear();
            supplementList.addAll(supplementBO.getAllSupplements());
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Failed to reload data: " + e.getMessage());
        }
    }

    public void btnDeleteSupplemetOnAction(ActionEvent actionEvent) {
        SupplementCartItemDTO selectedItem = tableCart.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert("Please select an item from the cart to delete.");
            return;
        }

        cartList.remove(selectedItem);
        updateTotal();
    }


    public void btnUpdateSupplemetOnAction(ActionEvent actionEvent) {
        SupplementDTO selected = tableSppliments.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Please select a supplement to update.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update Quantity");
        dialog.setHeaderText("Update Quantity for " + selected.getSupplemetName());
        dialog.setContentText("Enter new quantity:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int newQty = Integer.parseInt(input);
                if (newQty < 0) {
                    showAlert("Quantity cannot be negative.");
                    return;
                }

                boolean success = supplementBO.updateSupplementQuantity(selected.getSupplementId(), newQty);
                if (success) {
                    selected.setQuantity(newQty);
                    tableSppliments.refresh();
                    showAlert("Quantity updated successfully.");
                } else {
                    showAlert("Failed to update quantity.");
                }

            } catch (NumberFormatException e) {
                showAlert("Please enter a valid number.");
            } catch (SQLException | ClassNotFoundException e) {
                showAlert("Error: " + e.getMessage());
            }
        });
    }

    public void btnHistoryOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TransactionHistory.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Transaction History");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



