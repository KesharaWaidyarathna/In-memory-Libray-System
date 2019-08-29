package Controllers;

import Classes.LibraryDB;
import Classes.ManageMembersTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class manageMembers {
    public TableView<ManageMembersTM> tblManageMembers;
    public JFXTextField txtMemberId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public AnchorPane anpManageMembers;
    public JFXTextField txtContactNo;
    static int memberID=1;
    public JFXButton btnAdd;


    public void initialize(){

        tblManageMembers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ManageMembersTM>() {
            @Override
            public void changed(ObservableValue<? extends ManageMembersTM> observable, ManageMembersTM oldValue, ManageMembersTM newValue) {

                ManageMembersTM selectedRow = tblManageMembers.getSelectionModel().getSelectedItem();
                if(selectedRow==null){
                    btnAdd.setText("Add");

                }
                btnAdd.setText("Update");
                txtMemberId.setText(selectedRow.getMemberId());
                txtName.setText(selectedRow.getMemberName());
                txtAddress.setText(selectedRow.getMemberAddress());
                txtContactNo.setText(selectedRow.getContactNo());
            }
        });

        tblManageMembers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblManageMembers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberName"));
        tblManageMembers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        tblManageMembers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        ObservableList<ManageMembersTM> members=FXCollections.observableList(LibraryDB.members);
        tblManageMembers.setItems(members);
    }

    public void btnNew_Action(ActionEvent actionEvent) {
        memberID+=1;
        String id = "";
        if (memberID < 10){
            id = "M00" + memberID;
        }else if (memberID < 100){
            id = "M0" + memberID;
        }else{
            id = "M" + memberID;
        }
        txtMemberId.setText(id);
        txtMemberId.setDisable(true);
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        btnAdd.setText("Add");
    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/Fxml/MaindashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage= (Stage) this.anpManageMembers.getScene().getWindow();
        primaryStage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    public void btnAdd_Action(ActionEvent actionEvent) {
        if(btnAdd.getText().equals("Add")) {

            ObservableList<ManageMembersTM> members = tblManageMembers.getItems();

            members.add(new ManageMembersTM(
                    txtMemberId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText()));
        }
        else {
            ManageMembersTM selectedItem = tblManageMembers.getSelectionModel().getSelectedItem();

            selectedItem.setMemberName(txtName.getText());
            selectedItem.setMemberAddress(txtAddress.getText());
            selectedItem.setContactNo(txtContactNo.getText());

            if(selectedItem.getMemberId().equals(txtMemberId.getText())){

                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Update Suessfull",ButtonType.OK);
                alert.show();
            }
        }

        tblManageMembers.refresh();
    }

    public void btnDelete_Action(ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES) {
            ManageMembersTM selectedItem = tblManageMembers.getSelectionModel().getSelectedItem();
            tblManageMembers.getItems().remove(selectedItem);
        }
    }
}
