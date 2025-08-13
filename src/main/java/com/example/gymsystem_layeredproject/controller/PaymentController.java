package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.PaymentBOImpl;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.dto.PaymentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {


    public Button btnPaymentReport;
    @FXML
    private Label lblTotalAmout;
    @FXML
    private Button btnAddPayment;

    @FXML
    private AnchorPane btnTotal;

    @FXML
    private TableColumn<PaymentDTO, BigDecimal> columnAmount;

    @FXML
    private TableColumn<PaymentDTO, LocalDate> columnDate;

    @FXML
    private TableColumn<PaymentDTO, String> columnMemberId;

    @FXML
    private TableColumn<PaymentDTO, String> columnPaymentId;

    @FXML
    private TableColumn<PaymentDTO, String> columnPaymentMethod;

    @FXML
    private TableColumn<PaymentDTO, String> columnPlanName;

    @FXML
    private TableView<PaymentDTO> tablePayment;

    PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    public void TotalOnAction(MouseEvent mouseEvent) {
    }

    public void btnAddPaymentOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addpayment.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Payment");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


              loadTableData();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        columnMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        columnPlanName.setCellValueFactory(new PropertyValueFactory<>("planName"));
        columnPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        try {
            loadTableData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<PaymentDTO> data = FXCollections.observableArrayList((java.util.Collection<? extends PaymentDTO>) paymentBO.getAllPayments());
        tablePayment.setItems(data);
        updateTotalAmount();
    }
    private void updateTotalAmount() {
        double total = 0.0;
        for (PaymentDTO dto : tablePayment.getItems()) {
            total += dto.getAmount().doubleValue();
        }
        lblTotalAmout.setText(String.format("%.2f", total));
    }

    public void btnPaymentReportOnAction(ActionEvent actionEvent) {
        try {
            JasperReport report = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/PaymentReport.jrxml")
            );
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_DATE", java.sql.Date.valueOf(LocalDate.now()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    report,
                    parameters,
                    connection
            );
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
