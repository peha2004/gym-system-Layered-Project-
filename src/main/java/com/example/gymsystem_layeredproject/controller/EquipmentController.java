package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.EquipmentBOImpl;
import com.example.gymsystem_layeredproject.dto.EquipmentDTO;
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

public class EquipmentController implements Initializable {
    @FXML
    private Button btnEqiupmentAdd;

    @FXML
    private Button btnEquipmentDelete;

    @FXML
    private Button btnEquipmentUpdate;

    @FXML
    private TableColumn<EquipmentDTO, String> columnEquipmentId;

    @FXML
    private TableColumn<EquipmentDTO, String> columnEquipmentName;

    @FXML
    private TableColumn<EquipmentDTO, String> columnEquipmentStatus;

    @FXML
    private TableView<EquipmentDTO> tableEquipment;

    @FXML
    private TextField txtEquipmentId;

    @FXML
    private TextField txtEquipmentName;

    @FXML
    private ComboBox<String> txtEquipmentStatus;

    private EquipmentDTO selectedEquipment;



EquipmentBOImpl equipmentBO = (EquipmentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.EQUIPMENT);

    public void btnEqiupmentAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtEquipmentId.getText();
        String name = txtEquipmentName.getText();
        String status = txtEquipmentStatus.getValue();

        if (id.isEmpty() || name.isEmpty() || status == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
            return;
        }

        EquipmentDTO dto = new EquipmentDTO(id, name, status);
        boolean isSaved = equipmentBO.save(dto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment added successfully!").show();
            clearFields();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to add equipment!").show();
        }
    }


    public void btnEquipmentUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectedEquipment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an equipment to update").show();
            return;
        }

        String id = txtEquipmentId.getText();
        String name = txtEquipmentName.getText();
        String status = txtEquipmentStatus.getValue();

        EquipmentDTO dto = new EquipmentDTO(id, name, status);
        boolean isUpdated = equipmentBO.update(dto);

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment updated successfully!").show();
            clearFields();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update equipment!").show();
        }
    }


    public void btnEquipmentDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectedEquipment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an equipment to delete").show();
            return;
        }

        boolean isDeleted = equipmentBO.delete(selectedEquipment.getEquipmentId());

        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment deleted successfully!").show();
            clearFields();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete equipment!").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnEquipmentId.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        columnEquipmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEquipmentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        txtEquipmentStatus.setItems(FXCollections.observableArrayList("Available", "In Use", "Under Maintenance"));
        txtEquipmentId.setEditable(false);
        try {
            txtEquipmentId.setText(equipmentBO.generateEquipmentId());
            loadTableData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        tableEquipment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEquipment = newSelection;
                txtEquipmentId.setText(newSelection.getEquipmentId());
                txtEquipmentName.setText(newSelection.getName());
                txtEquipmentStatus.setValue(newSelection.getStatus());
            }
        });
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<EquipmentDTO> list = FXCollections.observableArrayList(equipmentBO.getAllEquipment());
        tableEquipment.setItems(list);
    }
    private void clearFields() {
        txtEquipmentId.clear();
        txtEquipmentName.clear();
        txtEquipmentStatus.setValue(null);
        selectedEquipment = null;
    }

}
