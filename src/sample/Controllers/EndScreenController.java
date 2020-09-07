package sample.Controllers;

import animatefx.animation.Pulse;
import javafx.fxml.*;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import java.io.IOException;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class EndScreenController implements Initializable {

    @FXML private AnchorPane end_pane;
    @FXML private ImageView minimize_btn, close_btn;
    @FXML private Label play_lb, quit_lb, main_menu_lb;
    @FXML private Line line_up, line_down;
    @FXML private Text money_check;


    private void menu_hover() {
        AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//menu_hover.wav").toString());
        note.setVolume(0.3);
        note.play();
    }

    private double X=0;
    private double Y=0;
    private Stage stage;
    private String money="";
    Image cur = new Image("sample/Images/cursor_hand.png");

    public void Line_Dragged(MouseEvent mouseEvent) {
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() -X);
        stage.setY(mouseEvent.getScreenY() -Y);
    }

    public void Line_Pressed(MouseEvent mouseEvent) {
        X = mouseEvent.getSceneX();
        Y = mouseEvent.getSceneY();
    }

    public void Minimize_Clicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) end_pane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void Minimize_Hovered(MouseEvent mouseEvent) {
    minimize_btn.setCursor(new ImageCursor(cur));
    }

    public void Close_Clicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void Close_Hovered(MouseEvent mouseEvent) {
        close_btn.setCursor(new ImageCursor(cur));
    }




    public void Main_Menu_Clicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent pane = loader.load(getClass().getResource("..//Interfaces//Menu.fxml"));
        Scene scene1 = new Scene(pane);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public void Main_Menu_Hovered(MouseEvent mouseEvent) {
        menu_hover();
        main_menu_lb.setCursor(new ImageCursor(cur));
        main_menu_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
        line_down.setStroke(Color.DODGERBLUE);
        line_up.setStroke(Color.DODGERBLUE);
        new Pulse(main_menu_lb).setSpeed(1.2).play();
    }

    public void Play_Clicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent pane = loader.load(getClass().getResource("..//Interfaces//Lifeline_People.fxml"));
        Scene scene1 = new Scene(pane);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public void Play_Hovered(MouseEvent mouseEvent) {
        menu_hover();
        play_lb.setCursor(new ImageCursor(cur));
        play_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
        line_up.setStroke(Color.DODGERBLUE);
        new Pulse(play_lb).setSpeed(1.2).play();
    }

    public void Quit_Clicked(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void Quit_Hovered(MouseEvent mouseEvent) {
        menu_hover();
        quit_lb.setCursor(new ImageCursor(cur));
        quit_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#1E90FF"));
        line_down.setStroke(Color.DODGERBLUE);
        new Pulse(quit_lb).setSpeed(1.2).play();
    }

    public void Quit_UnHovered(MouseEvent mouseEvent) {

        quit_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        line_down.setStroke(Color.WHITE);

    }


    public void Play_UnHovered(MouseEvent mouseEvent) {
        play_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        line_up.setStroke(Color.WHITE);
    }

    public void Main_Menu_UnHovered(MouseEvent mouseEvent) {
        main_menu_lb.setTextFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        line_up.setStroke(Color.WHITE);
        line_down.setStroke(Color.WHITE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        money_check.setText("0");
    }

    void setCheckMoney(String checkMoney){
        money_check.setText(checkMoney);

    }


}
