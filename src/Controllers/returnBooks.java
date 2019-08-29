package Controllers;

import Classes.IssueBooksTM;
import Classes.LibraryDB;
import Classes.ManageBooksTM;
import Classes.ReturnBooksTM;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.util.resources.LocaleData;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



public class returnBooks {
    public TableView<ReturnBooksTM> tblReturnBooks;
    public JFXDatePicker dpReturnDate;
    public Label lblTotal;
    public AnchorPane anpReturnBooks;
    public JFXTextField txtIssuedate;
    public JFXTextField txtBookid;
    public JFXComboBox<String> cmbIssueId;
    public JFXTextField txtTotalFines;
    public JFXTextField txtChages;


    public void initialize(){

        ObservableList<IssueBooksTM> issueBooksTMS = FXCollections.observableArrayList(LibraryDB.issueBooks);
        ObservableList issueId = cmbIssueId.getItems();

        for (int i = 0; i <issueBooksTMS.size() ; i++) {
            issueId.add(issueBooksTMS.get(i).getIssuId());
        }

        cmbIssueId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedItem = cmbIssueId.getSelectionModel().getSelectedItem();

                ObservableList<ManageBooksTM> manageBooksTMS = FXCollections.observableList(LibraryDB.books);

                if(selectedItem.equals(null)){
                    System.out.println(" 41");
                }


                for (int i = 0; i <issueBooksTMS.size() ; i++) {
                    if(selectedItem.equals(issueBooksTMS.get(i).getIssuId())){
                        txtBookid.setText(issueBooksTMS.get(i).getBookId());
                        txtIssuedate.setText(issueBooksTMS.get(i).getDate());
                    }


                }
            }
        });

        dpReturnDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                String selectedItem = cmbIssueId.getSelectionModel().getSelectedItem();

                for (int i = 0; i <issueBooksTMS.size() ; i++) {
                    if(issueBooksTMS.get(i).getIssuId().equals(selectedItem)){
                        LocalDate issued = LocalDate.parse(issueBooksTMS.get(i).getDate());
                        LocalDate retundate= LocalDate.parse(dpReturnDate.getValue().toString());

                        int differnt= (int) ChronoUnit.DAYS.between(issued,retundate);

                        if(differnt>15){
                            int fines=differnt*15;
                            txtTotalFines.setText(String.valueOf(fines));
                        }

                    }

                }



            }
        });

        tblReturnBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issueId"));
        tblReturnBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("totalFines"));
        tblReturnBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        ObservableList<ReturnBooksTM> returnBooksTMS = FXCollections.observableList(LibraryDB.returnBooks);
        tblReturnBooks.setItems(returnBooksTMS);






    }

    public void btnNew_Action(ActionEvent actionEvent) {
        cmbIssueId.getSelectionModel().clearSelection();
        txtBookid.clear();
        txtTotalFines.clear();
        dpReturnDate.getEditor().clear();

    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/Fxml/MaindashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.anpReturnBooks.getScene().getWindow();
        primaryStage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    public void btnReturn_Action(ActionEvent actionEvent) {
        String selectedItem = cmbIssueId.getSelectionModel().getSelectedItem();
        ObservableList<ReturnBooksTM> items = tblReturnBooks.getItems();
        ObservableList<ManageBooksTM> manageBooksTMS = FXCollections.observableList(LibraryDB.books);

        items.add(new ReturnBooksTM(selectedItem,Double.valueOf(txtTotalFines.getText()),dpReturnDate.getValue().toString()));

        for (int j = 0; j <manageBooksTMS.size() ; j++) {
            if(txtBookid.getText().equals(manageBooksTMS.get(j).getBookId())){

                manageBooksTMS.get(j).setStatues("Available");
            }

            ObservableList<IssueBooksTM> issuerow = FXCollections.observableList(LibraryDB.issueBooks);


            for (int k = 0; k <issuerow.size() ; k++) {
                if(selectedItem.equals(issuerow.get(k).getIssuId())){
                    issuerow.remove(k);
                }

            }


        }




    }

}
