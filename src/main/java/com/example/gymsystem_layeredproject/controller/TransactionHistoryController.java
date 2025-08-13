package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.SupplementTransactionBOImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import org.example.gymsystem.Model.SupplementTransactionModel;
import  com.example.gymsystem_layeredproject.dto.SupplementTransactionHistoryDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TransactionHistoryController {

    @FXML
    private TableView<SupplementTransactionHistoryDTO> tableTransactions;
    @FXML
    private TableColumn<SupplementTransactionHistoryDTO, String> colTxnId;
    @FXML
    private TableColumn<SupplementTransactionHistoryDTO, String> colMemberId;
    @FXML
    private TableColumn<SupplementTransactionHistoryDTO, LocalDate> colDate;
    @FXML
    private TableColumn<SupplementTransactionHistoryDTO, Double> colTotal;

    SupplementTransactionBOImpl supplementTransactionBO = (SupplementTransactionBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLEMENTTRANSACTION);
    @FXML
    public void initialize() {
        colTxnId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        try {
            List<SupplementTransactionHistoryDTO> list = supplementTransactionBO.getAllTransactions();
            tableTransactions.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load transactions").show();
        }
    }
}
