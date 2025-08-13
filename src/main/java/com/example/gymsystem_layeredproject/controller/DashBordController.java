package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {
    public AnchorPane ancMainContainer;
    public AnchorPane ancHomeHide1;
    public AnchorPane ancHomeHide2;
    public Button btnHome;
    public Button btnTrainers;
    public Button btnMembers;
    public Button btnEquipments;
    public Button btnPayment;
    public Button btnWorkoutPlan;
    public Label lblMembers;
    public Label lblTrainers;
    public Label lblGymEquipments;
    public Label lblTotalPayments;
    public AreaChart <String,Number>incomeChart;
    public Button btnLogOut;
    public Button btnSupplement;

    public void labelOnAction(MouseEvent mouseEvent) {
    }

    public void btnTrainersOnAction(ActionEvent actionEvent) {
     ancHomeHide1.setVisible(false);
     ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
     navigateTo("/view/trainer.fxml");

    }

    public void btnMembersOnAction(ActionEvent actionEvent) {
        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        navigateTo("/view/Member.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTotalMembers();
        loadTotalTrainers();
        loadTotalEquipments();
        loadTotalPayments();
        loadIncomeChart();


    }

    private void navigateTo(String path) {

        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        ancMainContainer.getChildren().clear();

        try {
            ancMainContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancMainContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainContainer.heightProperty());

            ancMainContainer.getChildren().add(anchorPane);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        ancMainContainer.getChildren().clear();
        ancHomeHide1.setVisible(true);
        ancHomeHide2.setVisible(true);


    }

    public void btnEquipmentsOnAction(ActionEvent actionEvent) {
        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        navigateTo("/view/Equipment.fxml");

    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        navigateTo("/view/payment.fxml");

    }

    public void btnWorkoutPlanOnAction(ActionEvent actionEvent) {
        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        navigateTo("/view/workoutplan.fxml");

    }
    public void btnSupplementOnAction(ActionEvent actionEvent) {
        ancHomeHide1.setVisible(false);
        ancHomeHide2.setVisible(false);
        ancMainContainer.setVisible(true);
        navigateTo("/view/Supplement.fxml");

    }
    private void loadTotalPayments() {
        String sql = "SELECT SUM(amount) AS total FROM Payment";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                double total = rs.getDouble("total");
                lblTotalPayments.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadTotalMembers() {
        String sql = "SELECT COUNT(*) AS total FROM Members";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("total");
                lblMembers.setText(String.valueOf(count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadTotalTrainers() {
        String sql = "SELECT COUNT(*) AS total FROM Trainer";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("total");
                lblTrainers.setText(String.valueOf(count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTotalEquipments() {
        String sql = "SELECT COUNT(*) AS total FROM Equipment";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("total");
                lblGymEquipments.setText(String.valueOf(count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadIncomeChart() {
        String sql = "SELECT DATE(date) AS day, SUM(amount) AS total " +
                "FROM Payment GROUP BY day ORDER BY day";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Daily Income");

            while (rs.next()) {
                String day = rs.getString("day");
                double total = rs.getDouble("total");
                series.getData().add(new XYChart.Data<>(day, total));
            }

            incomeChart.getData().clear();
            incomeChart.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        try {

            AnchorPane loginPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/registration.fxml")));

            Stage stage = (Stage) ancMainContainer.getScene().getWindow();
            Scene scene = new Scene(loginPane);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/view/registration.fxml")).toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Login - PrimeFit");
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load login page!").show();
            e.printStackTrace();
        }
    }

}

