package Controllers;

import Classes.LibraryDB;
import Classes.ManageBooksTM;
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

public class manageBooks {
    public TableView<ManageBooksTM> tblManageBooks;
    public JFXTextField txtBookId;
    public JFXTextField txtAuthor;
    public JFXTextField txtName;
    public AnchorPane anpManageBooks;
    public JFXButton btnAdd;
    static int bookId=0;
    public JFXTextField txtStatues;

    public void initialize(){


        tblManageBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblManageBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblManageBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tblManageBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("statues"));

        ObservableList<ManageBooksTM> manageBooksTMS = FXCollections.observableList(LibraryDB.books);
        tblManageBooks.setItems(manageBooksTMS);

        tblManageBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ManageBooksTM>() {
            @Override
            public void changed(ObservableValue<? extends ManageBooksTM> observable, ManageBooksTM oldValue, ManageBooksTM newValue) {

                ManageBooksTM selectedItem = tblManageBooks.getSelectionModel().getSelectedItem();

                if(selectedItem==null){
                    btnAdd.setText("Add");
                }
                btnAdd.setText("Update");
            }
        });
        txtBookId.setDisable(true);
        txtStatues.setText("Available");
        txtStatues.setDisable(true);
    }

    public void btnNew_Action(ActionEvent actionEvent) {
        bookId+=1;
        String id = "";
        if (bookId < 10){
            id = "B00" + bookId;
        }else if (bookId < 100){
            id = "B0" + bookId;
        }else{
            id = "B" + bookId;
        }
        txtBookId.setText(id);

        txtName.clear();
        txtName.requestFocus();
        txtAuthor.clear();
        btnAdd.setText("Add");
    }

    public void btnDelete_Action(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES) {
            ManageBooksTM selectedItem = tblManageBooks.getSelectionModel().getSelectedItem();
            tblManageBooks.getItems().remove(selectedItem);
        }
        Alert alert1 =new Alert(Alert.AlertType.INFORMATION,"Book delete successfully! ",ButtonType.OK);
        alert1.show();
    }

    public void btnAdd_Action(ActionEvent actionEvent) {
        String authorName=txtAuthor.getText();
        if(!authorName.matches("^[A-Za-z]+$")){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Can't enter digits for name",ButtonType.OK);
            alert.show();
            txtAuthor.requestFocus();
        }
        else {
            if (btnAdd.getText().equals("Add")) {

                ObservableList<ManageBooksTM> books = tblManageBooks.getItems();

                books.add(new ManageBooksTM(txtBookId.getText(), txtName.getText(), txtAuthor.getText(), txtStatues.getText()));
                Alert alert =new Alert(Alert.AlertType.INFORMATION,"New book add successfully! ",ButtonType.OK);
                alert.show();

            } else {
                ManageBooksTM selectedItem = tblManageBooks.getSelectionModel().getSelectedItem();

                selectedItem.setName(txtName.getText());
                selectedItem.setAuthorName(txtAuthor.getText());
                Alert alert =new Alert(Alert.AlertType.INFORMATION,"Book update successfully! ",ButtonType.OK);
                alert.show();
            }
        }
        tblManageBooks.refresh();
    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/Fxml/MaindashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage= (Stage) this.anpManageBooks.getScene().getWindow();
        primaryStage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }
}
