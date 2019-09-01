package Controllers;

import Classes.IssueBooksTM;
import Classes.LibraryDB;
import Classes.ManageBooksTM;
import Classes.ManageMembersTM;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

public class issueBooks {
    static int issueId = 0;
    public AnchorPane anpIssueBooks;
    public JFXTextField txtIssueId;
    public TableView<IssueBooksTM> tblIssueBooks;
    public JFXTextField txtSataues;
    public JFXDatePicker dpDate;
    public JFXComboBox<String> cmbBookId;
    public JFXComboBox<String> cmbMemberId;

    public void initialize() {

        ObservableList<ManageBooksTM> manageBooksTMS = FXCollections.observableArrayList(LibraryDB.books);
        ObservableList<ManageMembersTM> mambersTMS = FXCollections.observableArrayList(LibraryDB.members);
        ObservableList<String> bookid = cmbBookId.getItems();
        ObservableList<String> memberid = cmbMemberId.getItems();

        for (int i = 0; i < manageBooksTMS.size(); i++) {

            bookid.add(manageBooksTMS.get(i).getBookId());

        }

        for (int i = 0; i < mambersTMS.size(); i++) {

            memberid.add(mambersTMS.get(i).getMemberId());

        }

        cmbMemberId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

            }
        });

        cmbBookId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

            }
        });

        tblIssueBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issuId"));
        tblIssueBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblIssueBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblIssueBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<IssueBooksTM> issueBooksTMS = FXCollections.observableList(LibraryDB.issueBooks);
        tblIssueBooks.setItems(issueBooksTMS);

        txtIssueId.setDisable(true);
        cmbBookId.requestFocus();

    }

    public void btnNew_Action(ActionEvent actionEvent) {
        issueId += 1;
        String id = "";
        if (issueId < 10) {
            id = "I00" + issueId;
        } else if (issueId < 100) {
            id = "I0" + issueId;
        } else {
            id = "I" + issueId;
        }
        txtIssueId.setText(id);
        cmbBookId.getSelectionModel().clearSelection();
        cmbMemberId.getSelectionModel().clearSelection();
    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/Fxml/MaindashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.anpIssueBooks.getScene().getWindow();
        primaryStage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    public void btnIssue_Action(ActionEvent actionEvent) {
        ObservableList<IssueBooksTM>issue=tblIssueBooks.getItems();
        String bookid = cmbBookId.getSelectionModel().getSelectedItem();
        String memberid = cmbMemberId.getSelectionModel().getSelectedItem();
        ObservableList<ManageBooksTM> manageBooksTMS = FXCollections.observableList(LibraryDB.books);

        for (int i = 0; i <issue.size(); i++) {

            if(bookid.equals(issue.get(i).getBookId())){
                Alert alert =new Alert(Alert.AlertType.ERROR,"Book allready Issued", ButtonType.OK);
                alert.show();
                return;
            }
        }
        issue.add(new IssueBooksTM(txtIssueId.getText(),bookid,memberid,dpDate.getValue().toString()));
        for (int i = 0; i <manageBooksTMS.size() ; i++) {
            if(bookid.equals(manageBooksTMS.get(i).getBookId())){
                manageBooksTMS.get(i).setStatues("Not Availabe");
            }
        }
        Alert alert =new Alert(Alert.AlertType.INFORMATION,"Book issued successfully! ",ButtonType.OK);
        alert.show();
    }
}
