package sample.Controllers;

import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.control.TextField;



public class LifelinePeopleController {

    @FXML private AnchorPane lifeline_pane;
    @FXML private Button continue_btn;
    @FXML private ImageView continue_icon, close_btn, minimize_btn;
    @FXML private TextField name_1, name_2, name_3;
    @FXML private MenuButton sphere_1, sphere_2, sphere_3;



    private void menu_hover() {
        AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//menu_hover.wav").toString());
        note.setVolume(0.3);
        note.play();
    }



    private Stage stage;
    private double X = 0;
    private double Y = 0;


    javafx.scene.image.Image cur = new Image("sample/Images/cursor_hand.png");
    javafx.scene.image.Image cur_def = new Image("sample/Images/cursor_def.png");


    public void Continue_Clicked(MouseEvent mouseEvent) throws Exception {
        if(name_1.getText().trim().isEmpty()||name_2.getText().trim().isEmpty()||name_3.getText().trim().isEmpty()||sphere_1.getText().equalsIgnoreCase("Силна сфера")||sphere_2.getText().equalsIgnoreCase("Силна сфера")||sphere_3.getText().equalsIgnoreCase("Силна сфера")){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание");
            alert.setHeaderText("Възникна грешка");
            alert.setContentText("Има празни полета!");
            alert.showAndWait();
        }else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("..//Interfaces//Play.fxml"));
        AnchorPane root = loader.load();
        PlayController playController = loader.getController();

        playController.setNames(name_1.getText(), name_2.getText(), name_3.getText(),sphere_1.getText(),sphere_2.getText(),sphere_3.getText());
        playController.setDiff("EasyQuestions");
        playController.setQuestion(playController.getResultSet());

        lifeline_pane.getChildren().setAll(root);


        }




    }


    public void Continue_Hovered(MouseEvent mouseEvent) {
       new ParallelAnimationFX(new Pulse(continue_btn).setSpeed(1.2),new Pulse(continue_icon).setSpeed(1.2)).play();

        menu_hover();
        continue_btn.setCursor(new ImageCursor(cur));
        continue_icon.setCursor(new ImageCursor(cur));

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

    public void Minimize_Clicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) lifeline_pane.getScene().getWindow();
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

    public void setMouseCursor(MouseEvent mouseEvent) {
        lifeline_pane.setCursor(new ImageCursor(cur_def));
    }

    public void sphere_1_Hovered(MouseEvent mouseEvent) {
        sphere_1.setCursor(new ImageCursor(cur));
    }

    public void sphere_2_Hovered(MouseEvent mouseEvent) {
        sphere_2.setCursor(new ImageCursor(cur));
    }

    public void sphere_3_Hovered(MouseEvent mouseEvent) {
        sphere_3.setCursor(new ImageCursor(cur));
    }





    public void M1_option1_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(0).getText());

    }

    public void M1_option2_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(1).getText());
    }
    public void M1_option3_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(2).getText());
    }

    public void M1_option4_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(3).getText());
    }
    public void M1_option5_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(4).getText());
    }

    public void M1_option6_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(5).getText());
    }
    public void M1_option7_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(6).getText());
    }

    public void M1_option8_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(7).getText());
    }
    public void M1_option9_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(8).getText());
    }

    public void M1_option10_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(9).getText());
    }
    public void M1_option11_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(10).getText());
    }

    public void M1_option12_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(11).getText());
    }
    public void M1_option13_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(12).getText());
    }

    public void M1_option14_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(13).getText());
    }
    public void M1_option15_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(14).getText());
    }

    public void M1_option16_Clicked(ActionEvent actionEvent) {
        sphere_1.setText(sphere_1.getItems().get(15).getText());
    }




    public void M2_option1_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(0).getText());

    }
    public void M2_option2_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(1).getText());

    }
    public void M2_option3_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(2).getText());

    }
    public void M2_option4_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(3).getText());

    }
    public void M2_option5_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(4).getText());

    }
    public void M2_option6_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(5).getText());

    }
    public void M2_option7_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(6).getText());

    }
    public void M2_option8_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(7).getText());

    }
    public void M2_option9_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(8).getText());

    }
    public void M2_option10_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(9).getText());

    }
    public void M2_option11_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(10).getText());

    }
    public void M2_option12_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(11).getText());

    }
    public void M2_option13_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(12).getText());

    }
    public void M2_option14_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(13).getText());

    }
    public void M2_option15_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(14).getText());

    }
    public void M2_option16_Clicked(ActionEvent actionEvent) {
        sphere_2.setText(sphere_2.getItems().get(15).getText());
    }





    public void M3_option1_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(0).getText());

    }
    public void M3_option2_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(1).getText());

    }
    public void M3_option3_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(2).getText());

    }
    public void M3_option4_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(3).getText());

    }
    public void M3_option5_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(4).getText());

    }
    public void M3_option6_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(5).getText());

    }
    public void M3_option7_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(6).getText());

    }
    public void M3_option8_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(7).getText());

    }
    public void M3_option9_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(8).getText());

    }
    public void M3_option10_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(9).getText());

    }
    public void M3_option11_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(10).getText());

    }
    public void M3_option12_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_3.getItems().get(11).getText());

    }
    public void M3_option13_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_2.getItems().get(12).getText());

    }
    public void M3_option14_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_2.getItems().get(13).getText());

    }
    public void M3_option15_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_2.getItems().get(14).getText());

    }
    public void M3_option16_Clicked(ActionEvent actionEvent) {
        sphere_3.setText(sphere_2.getItems().get(15).getText());
    }


}
