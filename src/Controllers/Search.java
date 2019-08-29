package Controllers;

import Classes.IssueBooksTM;
import Classes.LibraryDB;
import Classes.ManageBooksTM;
import Classes.SearchTM;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class Search {
    public JFXTextField txtSearch;
    public TableView<SearchTM> tblSearch;
    public AnchorPane anpSearch;

    public void initialize(){

        tblSearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblSearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblSearch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("staues"));

        ObservableList<ManageBooksTM> books = FXCollections.observableArrayList(LibraryDB.books);
        ObservableList<IssueBooksTM> issueBooksTMS = FXCollections.observableArrayList(LibraryDB.issueBooks);
        ObservableList<SearchTM> tableadd = tblSearch.getItems();


        for (int i = 0; i <books.size() ; i++) {

            tableadd.add(new SearchTM(books.get(i).getBookId(),books.get(i).getName(),books.get(i).getAuthorName(),books.get(i).getStatues()));
        }
        tblSearch.setItems(tableadd);

        ObservableList<SearchTM>tabletemp=FXCollections.observableArrayList(tableadd);

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ObservableList<SearchTM>newtemp=FXCollections.observableArrayList();


                for (SearchTM searchTM : tabletemp) {
                    if(searchTM.getBookId().contains(newValue)||searchTM.getName().contains(newValue)||searchTM.getAuthor().contains(newValue)){
                        newtemp.add(searchTM);
                    }

                }

                tblSearch.setItems(newtemp);

            }
        });





    }



    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/Fxml/MaindashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.anpSearch.getScene().getWindow();
        primaryStage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }
}
