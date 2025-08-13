    package com.example.gymsystem_layeredproject.controller;

    import com.example.gymsystem_layeredproject.bo.BOFactory;
    import com.example.gymsystem_layeredproject.bo.custom.impl.MemberBOImpl;
    import com.example.gymsystem_layeredproject.dto.MemberDTO;
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


    public class MemberController implements Initializable {
        private final String namePattern = "^[A-Za-z ]+$";
        private final String phonePattern = "^\\d{10}$";

      MemberBOImpl memberBO = (MemberBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
        @FXML
        private Button btnMemberAdd;

        @FXML
        private Button btnMemberDelete;

        @FXML
        private Button btnMemberUpdate;

        @FXML
        private TableColumn<MemberDTO, String> columnContact;

        @FXML
        private TableColumn<MemberDTO, String> columnGender;

        @FXML
        private TableColumn<MemberDTO, String> columnMemberId;

        @FXML
        private TableColumn<MemberDTO, String> columnName;

        @FXML
        private TableView<MemberDTO> tableMember;

        @FXML
        private TextField txtMemberContact;

        @FXML
        private ComboBox<String> txtMemberGender;

        @FXML
        private TextField txtMemberId;

        @FXML
        private TextField txtMemberName;


        private void resetPage() throws SQLException, ClassNotFoundException {
            txtMemberId.setText(memberBO.generateMemberId());
            txtMemberName.clear();
            txtMemberContact.clear();
            txtMemberGender.setValue(null);


        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)   {
            try {
                loadTableData();
            } catch  (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Initialization Error", "Could not load member data or generate ID.");
            }
             try {
                txtMemberId.setText(memberBO.generateMemberId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            txtMemberGender.getItems().addAll("Male", "Female", "Other");



            columnMemberId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            columnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tableMember.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            });

            tableMember.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    txtMemberId.setText(newSelection.getId());
                    txtMemberName.setText(newSelection.getName());
                    txtMemberGender.setValue(newSelection.getGender());
                    txtMemberContact.setText(newSelection.getContact());

                }
            });

        }

        public void btnMemberAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            String id = txtMemberId.getText();
            String name = txtMemberName.getText();
            String contact = txtMemberContact.getText();
            String gender = txtMemberGender.getValue();

            if (!name.matches(namePattern)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Name", "Name should only contain letters and spaces.");
                return;
            }

            if (!contact.matches(phonePattern)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Phone", "Phone number must be exactly 10 digits.");
                return;
            }

            if (gender == null || gender.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Gender", "Please select a gender.");
                return;
            }

            MemberDTO dto = new MemberDTO(id, name, gender, contact);

            if (memberBO.save(dto)) {
                new Alert(Alert.AlertType.INFORMATION, " Member saved!").show();

                txtMemberId.setText(memberBO.generateMemberId());

            } else {
                new Alert(Alert.AlertType.ERROR, " Failed to save Member").show();
            }
        }

        public void btnMemberUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String id = txtMemberId.getText();
            String name = txtMemberName.getText();
            String contact = txtMemberContact.getText();
            String gender = txtMemberGender.getValue();

            if (!name.matches(namePattern)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Name", "Name should only contain letters and spaces.");
                return;
            }

            if (!contact.matches(phonePattern)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Phone", "Phone number must be exactly 10 digits.");
                return;
            }

            if (gender == null || gender.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Gender", "Please select a gender.");
                return;
            }
            MemberDTO dto = new MemberDTO(id, name, gender, contact);

            if (memberBO.update(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Member updated!").show();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Member").show();
            }

        }

        public void btnMemberDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            MemberDTO selected = tableMember.getSelectionModel().getSelectedItem();

            if (selected == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a member to delete").show();
                return;
            }

            if (memberBO.delete(selected.getId())) {
                new Alert(Alert.AlertType.INFORMATION, "Member deleted!").show();
                loadTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Member").show();
            }
        }

        private void loadTableData() throws SQLException, ClassNotFoundException {
         //   try {
                ObservableList<MemberDTO> memberList = FXCollections.observableArrayList((java.util.Collection<? extends MemberDTO>) memberBO.getAllMembers());
                tableMember.setItems(memberList);
//            } catch (SQLException | ClassNotFoundException e) {
//                e.printStackTrace();
//                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load member data.");
//            }
        }
        private void showAlert(Alert.AlertType type, String title, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
