package com.example.gymsystem_layeredproject.controller;

import com.example.gymsystem_layeredproject.bo.BOFactory;
import com.example.gymsystem_layeredproject.bo.custom.impl.MemberBOImpl;
import com.example.gymsystem_layeredproject.bo.custom.impl.TrainerBOImpl;
import com.example.gymsystem_layeredproject.bo.custom.impl.WorkoutPlanBOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.gymsystem_layeredproject.dto.WorkoutDTO;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WorkoutPlanController implements Initializable {
    @FXML
    public TableColumn <WorkoutDTO, LocalDate> columnWorkOutEndDate;
    public TextField txtWorkoutPlanEndDate;
    public Button btnWorkOutDelete;
    public TableColumn columnWorkOutEndEmail;
    public TextField txtWorkoutPlanEmail;
    public Button btnEmail;
    @FXML
    private Button btnWorkOutAdd;

    @FXML
    private Button btnWorkOutClear;

    @FXML
    private Button btnWorkOutUpdate;

    @FXML
    private TableColumn<WorkoutDTO, Integer> columnWorkOutDuration;

    @FXML
    private TableColumn<WorkoutDTO, String> columnWorkOutMember;

    @FXML
    private TableColumn<WorkoutDTO, String> columnWorkOutPlanName;

    @FXML
    private TableColumn<WorkoutDTO, LocalDate> columnWorkOutStartDate;

    @FXML
    private TableColumn<WorkoutDTO, String> columnWorkOutTrainer;

    @FXML
    private TableView<WorkoutDTO> tableWorkOut;

    @FXML
    private TextField txtWorkoutPlanDuration;

    @FXML
    private ComboBox<String> txtWorkoutPlanMember;

    @FXML
    private ComboBox<String> txtWorkoutPlanName;

    @FXML
    private TextField txtWorkoutPlanStartdate;

    @FXML
    private ComboBox<String> txtWorkoutPlanTrainer;

    WorkoutPlanBOImpl workoutPlanBO = (WorkoutPlanBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.WORKOUTPLAN);
    MemberBOImpl memberBO = (MemberBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
    TrainerBOImpl trainerBO = (TrainerBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.TRAINER);

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    public void btnWorkOutAddOnAction(ActionEvent actionEvent) {
        try {
            String planName = txtWorkoutPlanName.getValue();
            int duration = Integer.parseInt(txtWorkoutPlanDuration.getText());
            String memberId = txtWorkoutPlanMember.getValue();
            String trainerId = txtWorkoutPlanTrainer.getValue();
            LocalDate startDate = LocalDate.parse(txtWorkoutPlanStartdate.getText());
            LocalDate endDate = startDate.plusDays(duration);
            String email = txtWorkoutPlanEmail.getText();

            if (!isValidEmail(email)) {
                new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
                return;
            }

            WorkoutDTO dto = new WorkoutDTO(planName, duration, memberId, trainerId, startDate , endDate, email);

            boolean success = workoutPlanBO.save(dto);
            if (success) {
                new Alert(Alert.AlertType.INFORMATION, "Workout Plan Added!").show();
                loadWorkoutPlans();
                clearForm();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add workout plan.").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please check your fields.").show();
            e.printStackTrace();
        }
    }

    public void btnWorkOutUpdateOnAction(ActionEvent actionEvent) {
        try {
            String planName = txtWorkoutPlanName.getValue();
            int duration = Integer.parseInt(txtWorkoutPlanDuration.getText());
            String memberId = txtWorkoutPlanMember.getValue();
            String trainerId = txtWorkoutPlanTrainer.getValue();
            LocalDate startDate = LocalDate.parse(txtWorkoutPlanStartdate.getText());
            LocalDate endDate = startDate.plusDays(duration);
            String email = txtWorkoutPlanEmail.getText();

            if (!isValidEmail(email)) {
                new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
                return;
            }

            WorkoutDTO dto = new WorkoutDTO(planName, duration, memberId, trainerId, startDate, endDate, email);

            boolean success = workoutPlanBO.update(dto);
            if (success) {
                new Alert(Alert.AlertType.INFORMATION, "Workout Plan Updated!").show();
                loadWorkoutPlans();
                clearForm();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update workout plan.").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please check your fields.").show();
            e.printStackTrace();
        }
    }

    public void btnWorkOutClearOnAction(ActionEvent actionEvent) {
        clearForm();
    }
    private void clearForm() {
        txtWorkoutPlanName.getSelectionModel().clearSelection();
        txtWorkoutPlanDuration.clear();
        txtWorkoutPlanMember.getSelectionModel().clearSelection();
        txtWorkoutPlanTrainer.getSelectionModel().clearSelection();
        txtWorkoutPlanStartdate.clear();
        txtWorkoutPlanEndDate.clear();
        txtWorkoutPlanEmail.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtWorkoutPlanName.setItems(FXCollections.observableArrayList("Standard", "Premium", "Beginner"));
        try {
            txtWorkoutPlanMember.setItems(FXCollections.observableArrayList(memberBO.getAllMemberIds()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            txtWorkoutPlanTrainer.setItems(FXCollections.observableArrayList(trainerBO.getAllIds()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        columnWorkOutPlanName.setCellValueFactory(new PropertyValueFactory<>("planName"));
        columnWorkOutDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        columnWorkOutMember.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        columnWorkOutTrainer.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        columnWorkOutStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnWorkOutEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        columnWorkOutEndEmail.setCellValueFactory(new PropertyValueFactory<>("email"));



        tableWorkOut.setOnMouseClicked(event -> {
            WorkoutDTO selected = tableWorkOut.getSelectionModel().getSelectedItem();
            if (selected != null) {
                txtWorkoutPlanName.setValue(selected.getPlanName());
                txtWorkoutPlanDuration.setText(String.valueOf(selected.getDuration()));
                txtWorkoutPlanMember.setValue(selected.getMemberId());
                txtWorkoutPlanTrainer.setValue(selected.getTrainerId());
                txtWorkoutPlanStartdate.setText(selected.getStartDate().toString());
                txtWorkoutPlanEndDate.setText(selected.getEndDate().toString());
                txtWorkoutPlanEmail.setText(selected.getEmail());
            }
        });

        try {
            loadWorkoutPlans();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadWorkoutPlans() throws SQLException, ClassNotFoundException {
        ObservableList<WorkoutDTO> plans = FXCollections.observableArrayList(workoutPlanBO.getAllPlans());
        tableWorkOut.setItems(plans);
    }

    public void btnWorkOutDeleteOnAction(ActionEvent actionEvent) {
        try {
            String planName = txtWorkoutPlanName.getValue();
            String memberId = txtWorkoutPlanMember.getValue();
            String trainerId = txtWorkoutPlanTrainer.getValue();
            LocalDate startDate = LocalDate.parse(txtWorkoutPlanStartdate.getText());
            String email = txtWorkoutPlanEmail.getText();

            boolean confirm = workoutPlanBO.delete(planName, memberId, trainerId, startDate, email);
            if (confirm) {
                new Alert(Alert.AlertType.INFORMATION, "Workout Plan Deleted!").show();
                loadWorkoutPlans();
                clearForm();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete workout plan.").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please check your fields.").show();
            e.printStackTrace();
        }
    }

    public void btnEmailOnAction(ActionEvent actionEvent) {
        WorkoutDTO selected = tableWorkOut.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a workout plan from the table.").show();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SendEmail.fxml"));
            Parent root = loader.load();


            SendEmailController controller = loader.getController();
            controller.setRecipientEmail(selected.getEmail());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Send Email");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
