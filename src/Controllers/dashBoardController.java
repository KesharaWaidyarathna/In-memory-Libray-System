package Controllers;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class dashBoardController {

    public AnchorPane anpDashBoard;
    public ImageView lblManageMembers;
    public ImageView lblManageBooks;
    public ImageView lblIssueBook;
    public ImageView lblReturnbooks;
    public ImageView lblSearchBooks;
    public Label lblDescription;
    public Label lblMenu;


    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch(icon.getId()){
                case "lblManageMembers":
                    root = FXMLLoader.load(this.getClass().getResource("/Fxml/manageMembers.fxml"));
                    break;
                case "lblManageBooks":
                    root = FXMLLoader.load(this.getClass().getResource("/Fxml/manageBooks.fxml"));
                    break;
                case "lblIssueBook":
                    root = FXMLLoader.load(this.getClass().getResource("/Fxml/issueBooks.fxml"));
                    break;
                case "lblReturnbooks":
                    root = FXMLLoader.load(this.getClass().getResource("/Fxml/returnBooks.fxml"));
                    break;
                case "lblSearchBooks":
                   root = FXMLLoader.load(this.getClass().getResource("/Fxml/Search.fxml"));
                    break;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.anpDashBoard.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();



                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }


    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }


    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            switch(icon.getId()){
                case "lblManageMembers":
                    lblMenu.setText("Manage Members");
                    lblDescription.setText("Click to add, edit, delete, search or view Members");
                    break;
                case "lblManageBooks":
                    lblMenu.setText("Manage Books");
                    lblDescription.setText("Click to add, edit, delete, search or view Books");
                    break;
                case "lblIssueBook":
                    lblMenu.setText("Issue Books");
                    lblDescription.setText("Click here if you want to issue books");
                    break;
                case "lblReturnbooks":
                    lblMenu.setText("Return Books");
                    lblDescription.setText("Click if you want to Retun books");
                    break;
                case "lblSearchBooks":
                    lblMenu.setText("Search Books");
                    lblDescription.setText("Click if you want to search Books");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BLUEVIOLET);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

}
