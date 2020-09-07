package sample.Controllers;

import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class MenuController {

        @FXML private Label quit_lb, add_lb, play_lb;
        @FXML private Separator sep_up, sep_down;
        @FXML private AnchorPane Menu_pane;
        @FXML private ImageView minimize_btn, close_btn;


        private Stage stage;
        private double X = 0;
        private double Y = 0;


private void menu_hover() {
    AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//menu_hover.wav").toString());
    note.setVolume(0.3);
    note.play();

}

    Image cur = new Image("sample/Images/cursor_hand.png");
    Image cur_def = new Image("sample/Images/cursor_def.png");



    public void Quit_Clicked(MouseEvent mouseEvent) {

        System.exit(1);

    }

    public void Quit_Hovered(MouseEvent mouseEvent) {
        menu_hover();
quit_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
       sep_down.setStyle("-fx-background-color:DodgerBlue");
        new Pulse(quit_lb).setSpeed(1.2).play();
        quit_lb.setCursor(new ImageCursor(cur));
    }

    public void Quit_UnHovered(MouseEvent mouseEvent) {

        quit_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        sep_down.setStyle("-fx-background-color:white");


    }


    public void Add_Clicked(MouseEvent mouseEvent) throws  IOException  {

        Parent root = FXMLLoader.load(getClass().getResource("..//Interfaces//Add Questions.fxml"));
       Menu_pane.getChildren().removeAll();
       Menu_pane.getChildren().setAll(root);

    }

    public void Add_Hovered(MouseEvent mouseEvent) {
        menu_hover();
        add_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
        sep_up.setStyle("-fx-background-color:DodgerBlue");
        sep_down.setStyle("-fx-background-color:DodgerBlue");
        add_lb.setCursor(new ImageCursor(cur));
        new Pulse(add_lb).setSpeed(1.2).play();
    }

    public void Add_UnHovered(MouseEvent mouseEvent) {
        add_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        sep_up.setStyle("-fx-background-color:white");
        sep_down.setStyle("-fx-background-color:white");

    }

    public void Play_Hovered(MouseEvent mouseEvent) {
        menu_hover();
        play_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
        sep_up.setStyle("-fx-background-color:DodgerBlue");
        play_lb.setCursor(new ImageCursor(cur));
	    new Pulse(play_lb).setSpeed(1.2).play();



    }

    public void Play_UnHovered(MouseEvent mouseEvent) {
        play_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        sep_up.setStyle("-fx-background-color:white");

    }

    public void Play_Clicked(MouseEvent mouseEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("..//Interfaces//Lifeline_People.fxml"));
        AnchorPane root = loader.load();
        Menu_pane.getChildren().setAll(root);
    }

    public void Close_Clicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void Minimize_Clicked(MouseEvent mouseEvent) {
    Stage stage = (Stage) Menu_pane.getScene().getWindow();
    stage.setIconified(true);
    }

    public void Minimize_Hovered(MouseEvent mouseEvent) {
        minimize_btn.setCursor(new ImageCursor(cur));
    }

    public void Close_Hovered(MouseEvent mouseEvent) {
        close_btn.setCursor(new ImageCursor(cur));
    }


    public void Line_Dragged(MouseEvent mouseEvent) {
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() -X);
        stage.setY(mouseEvent.getScreenY() -Y);
    }

    public void Line_Pressed(MouseEvent mouseEvent) {
        X = mouseEvent.getSceneX();
        Y = mouseEvent.getSceneY();
    }

    public void pane_hovered(MouseEvent mouseEvent) {
        Menu_pane.setCursor(new ImageCursor(cur_def));
    }
}
