package sample.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.ImageCursor;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("..//Interfaces//Menu.fxml"));
        primaryStage.setTitle("Стани богат");
        primaryStage.setScene(new Scene(root));
primaryStage.setMinHeight(690);
primaryStage.setMinWidth(1252);
        Image image = new Image("sample/Images/cursor_def.png");
        root.setCursor(new ImageCursor(image));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Image logo = new Image("sample/Images/logo.png");
        primaryStage.getIcons().add(logo);
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
