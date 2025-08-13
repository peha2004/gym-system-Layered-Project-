package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.TrainerBOImpl;
import com.example.gymsystem_layeredproject.dto.TrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrainerController implements Initializable {

    private final String namePattern = "^[A-Za-z ]+$";
    private final String phonePattern = "^\\d{10}$";
    private final String specializationPattern = "^[A-Za-z ]+$";
    @FXML
    private TableView<TrainerDTO> TrainerTable;

    @FXML
    private Button btnTrainerAdd;

    @FXML
    private Button btnTrainerDelete;

    @FXML
    private Button btnTrainerUpdate;

    @FXML
    private TableColumn<TrainerDTO, String> columnContact;

    @FXML
    private TableColumn<TrainerDTO, String> columnName;

    @FXML
    private TableColumn<TrainerDTO, String> columnSpecialization;

    @FXML
    private TableColumn<TrainerDTO, String> columnTrainarId;

    @FXML
    private TextField txtTrainerContact;

    @FXML
    private TextField txtTrainerId;

    @FXML
    private TextField txtTrainerName;

    @FXML
    private TextField txtTrainerSpecialization;

    TrainerBOImpl trainerBO = (TrainerBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.TRAINER);

    public void btnTrainerAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtTrainerId.getText();
        String name = txtTrainerName.getText();
        String contact = txtTrainerContact.getText();
        String specialization = txtTrainerSpecialization.getText();

        if (!name.matches(namePattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Name", "Trainer name should contain only letters and spaces.");
            return;
        }

        if (!contact.matches(phonePattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Phone Number", "Phone number must be exactly 10 digits.");
            return;
        }

        if (!specialization.matches(specializationPattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Specialization", "Specialization should contain only letters and spaces.");
            return;
        }

        TrainerDTO dto = new TrainerDTO(id, name, contact, specialization);
        if (trainerBO.save(dto)) {
            new Alert(Alert.AlertType.INFORMATION, "Trainer added successfully").show();
            txtTrainerId.setText(trainerBO.generateNewId());
            loadTableData();
            clearFields();

        }else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Trainer").show();
        }


    }

    public void btnTrainerUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtTrainerId.getText();
        String name = txtTrainerName.getText();
        String contact = txtTrainerContact.getText();
        String specialization = txtTrainerSpecialization.getText();

        if (!name.matches(namePattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Name", "Trainer name should contain only letters and spaces.");
            return;
        }

        if (!contact.matches(phonePattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Phone Number", "Phone number must be exactly 10 digits.");
            return;
        }

        if (!specialization.matches(specializationPattern)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Specialization", "Specialization should contain only letters and spaces.");
            return;
        }

        TrainerDTO trainerDTO = new TrainerDTO(id, name, contact, specialization);
        if (trainerBO.update(trainerDTO)) {
            new Alert(Alert.AlertType.INFORMATION, "Trainer updated successfully").show();
            loadTableData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update Trainer").show();
        }
    }

    public void btnTrainerDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String trainerId = txtTrainerId.getText();
        if (trainerId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a trainer to delete.").show();
            return;
        }

        if (trainerBO.delete(trainerId)) {
            new Alert(Alert.AlertType.INFORMATION, "Trainer deleted successfully").show();
            loadTableData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete Trainer").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTableData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            txtTrainerId.setText(trainerBO.generateNewId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtTrainerName.setText(txtTrainerName.getText());
       txtTrainerContact.setText(txtTrainerContact.getText());
        txtTrainerSpecialization.setText(txtTrainerSpecialization.getText());

        columnTrainarId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
        columnContact.setCellValueFactory(new PropertyValueFactory<>("trainerPhone"));
        columnSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        TrainerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

        });
        TrainerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtTrainerId.setText(newSelection.getTrainerId());
                txtTrainerName.setText(newSelection.getTrainerName());
                txtTrainerContact.setText(newSelection.getTrainerPhone());
                txtTrainerSpecialization.setText(newSelection.getSpecialization());

            }
        });


    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<TrainerDTO> trainerList = FXCollections.observableArrayList((java.util.Collection<? extends TrainerDTO>) trainerBO.getAll());
        TrainerTable.setItems(trainerList);
    }
    private void clearFields() throws SQLException, ClassNotFoundException {
        txtTrainerId.setText(trainerBO.generateNewId());
        txtTrainerName.clear();
        txtTrainerContact.clear();
        txtTrainerSpecialization.clear();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
