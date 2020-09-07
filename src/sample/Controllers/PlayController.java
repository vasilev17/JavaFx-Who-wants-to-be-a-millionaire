package sample.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import animatefx.animation.Pulse;
import animatefx.animation.SlideInUp;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class PlayController implements Initializable {

    @FXML private Button ans_A, ans_B, ans_C, ans_D, continue_btn, correctAnswer, get_money, stay_btn;
    @FXML private TextField question_fl;
    @FXML private Circle joker_50, phone_joker, aud_joker;
    @FXML private ImageView volume_cross, volume_icon, opinion_imageView, joker2Cross, joker2, joker3, close_btn, minimize_btn, arrow, joker1Cross, quit_btn, textBubble, joker3Cross;
    @FXML private Text opinion_lb, continue_lb, sphere_field_1,sphere_field_2,sphere_field_3, people_1, people_2, people_3, percentage1, percentage2, percentage3, percentage4;
    @FXML private Label joker1, X, X1;
    @FXML private Pane lifelinePanel1, lifelinePanel2, lifelinePanel3, trans;
    @FXML private AnchorPane lifelines_Pane, play_pane, continue_pane, confirm_pane, chart_pane;
    @FXML private GridPane money_pane;
    @FXML private BorderPane opinion_bp;
    @FXML private BarChart crowd_chart;


    AudioClip men = new AudioClip(this.getClass().getResource("..//Sounds//menu_hover.wav").toString());
    AudioClip right = new AudioClip(this.getClass().getResource("..//Sounds//correctSelection.mp3").toString());



    private void menu_click(boolean play,double volume) {
        if (play) {
            men.setVolume(volume);
            men.play();
        }else
            men.setVolume(0);

    }


    private void correctSelection(boolean play,double volume) {

        if (play) {
            right.setVolume(volume);
            right.play();
        }else right.setVolume(0);
    }



    private void answer_marked(boolean play,double volume) {
        AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//Mark answer.mp3").toString());
        if (play) {
            note.setVolume(volume);
            note.play();
        }else note.setVolume(0);
    }

    private void music_Play(boolean indicator,String track) {
        AudioClip easyQ_Music = new AudioClip(this.getClass().getResource("..//Sounds//"+track).toString());
        if (indicator==true) {
            easyQ_Music.setVolume(0.8);
            easyQ_Music.setCycleCount(999999999);
            easyQ_Music.play();
        }else{
            easyQ_Music.stop();
        }
    }





    private void worng_marked() {

        AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//Wrong answer.mp3").toString());
        note.setVolume(1);
        note.play();
    }

private String checkTrack(){
        String track;
        if (answeredQuestions<5){
            track="Q1-5.mp3";
        }else if(answeredQuestions>=5&&answeredQuestions<10){
            track = "Q6-10.mp3";
        }else if (answeredQuestions>=10||answeredQuestions<15){
            track = "Q11-14.mp3";
        }else{
            track = "Q15.mp3";
        }
        return track;
}

    javafx.scene.image.Image cur = new Image("sample/Images/cursor_hand.png");
    javafx.scene.image.Image cur_def = new Image("sample/Images/cursor_def.png");
    private Stage stage;
    private double x = 0;
    private double y = 0;
    private int answeredQuestions = 0;
    private ResultSet resultSet = null;
    private Random rand = new Random();
    private int question = 14;
    private int correct = 0;
    private String moneyWon = "";


    Image woman = new Image("sample/Images/person3.png");


    ResultSet getResultSet() {
        return resultSet;
    }


    public void Ans_A_hovered(MouseEvent mouseEvent) {
        ans_A.setCursor(new ImageCursor(cur));
    }



    public void Ans_B_hovered(MouseEvent mouseEvent) {

        ans_B.setCursor(new ImageCursor(cur));

    }

    public void Ans_C_hovered(MouseEvent mouseEvent) {

        ans_C.setCursor(new ImageCursor(cur));
    }

    public void Ans_D_hovered(MouseEvent mouseEvent) {

        ans_D.setCursor(new ImageCursor(cur));
    }

    public void Question_hovered(MouseEvent mouseEvent) {
        question_fl.setCursor(new ImageCursor(cur_def));

    }

    public void joker_50_hovered(MouseEvent mouseEvent) {

        joker_50.setCursor(new ImageCursor(cur));
    }

    public void phone_joker_hovered(MouseEvent mouseEvent) {

        phone_joker.setCursor(new ImageCursor(cur));
    }

    public void people_joker_hovered(MouseEvent mouseEvent) {

        aud_joker.setCursor(new ImageCursor(cur));
    }

    public void joker1_hovered(MouseEvent mouseEvent) {

        joker1.setCursor(new ImageCursor(cur));
    }

    public void joker2_hovered(MouseEvent mouseEvent) {

        joker2.setCursor(new ImageCursor(cur));
    }

    public void joker3_hovered(MouseEvent mouseEvent) {

        joker3.setCursor(new ImageCursor(cur));
    }

    public void lifelines_clicked(MouseEvent mouseEvent) throws Exception {
            menu_click(true,0.3);
        if (!lifelines_Pane.isVisible()||lifelines_Pane.isDisable()) {
            lifelines_Pane.setDisable(false);
            lifelines_Pane.setVisible(true);
            lifelines_Pane.toFront();
            new FadeIn(lifelines_Pane).setSpeed(2.4).play();
        } else {
            new FadeOut(lifelines_Pane).setSpeed(2.4).play();
            lifelines_Pane.setDisable(true);
        }


    }

    public void Panel1_Hovered(MouseEvent mouseEvent) {

        lifelinePanel1.setStyle("-fx-background-color: orange");
        lifelinePanel1.setCursor(new ImageCursor(cur));

    }

    public void Panel2_Hovered(MouseEvent mouseEvent) {

        lifelinePanel2.setStyle("-fx-background-color: orange");
        lifelinePanel2.setCursor(new ImageCursor(cur));
    }

    public void Panel3_Hovered(MouseEvent mouseEvent) {

        lifelinePanel3.setStyle("-fx-background-color: orange");
        lifelinePanel3.setCursor(new ImageCursor(cur));
    }

    public void Panel3_UnHovered(MouseEvent mouseEvent) {

        lifelinePanel3.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-radius: 25;");
    }

    public void Panel2_UnHovered(MouseEvent mouseEvent) {

        lifelinePanel2.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-radius: 25;");
    }

    public void Panel1_UnHovered(MouseEvent mouseEvent) {

        lifelinePanel1.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-radius: 25;");

    }

    public void X_Hovered(MouseEvent mouseEvent) {
        X.setCursor(new ImageCursor(cur));
        X1.setCursor(new ImageCursor(cur));

    }

    public void X_clicked(MouseEvent mouseEvent) {
        new FadeOut(lifelines_Pane).setSpeed(2.4).play();
        lifelines_Pane.setDisable(true);

    }

    public void Line_Dragged(MouseEvent mouseEvent) {
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);
    }

    public void Line_Pressed(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    public void Minimize_Clicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) play_pane.getScene().getWindow();
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
        play_pane.setCursor(new ImageCursor(cur_def));
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    void setNames(String p1, String p2, String p3, String s1, String s2, String s3) {
        people_1.setText(p1);
        people_2.setText(p2);
        people_3.setText(p3);

        sphere_field_1.setText(s1);
        sphere_field_2.setText(s2);
        sphere_field_3.setText(s3);
    }


private void restore(){


    Platform.runLater(()-> ans_A.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18; -fx-border-color: orange"));
    Platform.runLater(()-> ans_B.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18; -fx-border-color: orange"));
    Platform.runLater(()-> ans_C.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18; -fx-border-color: orange"));
    Platform.runLater(()-> ans_D.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18; -fx-border-color: orange"));


    Platform.runLater(()-> ans_A.setDisable(false));
    Platform.runLater(()-> ans_B.setDisable(false));
    Platform.runLater(()-> ans_C.setDisable(false));
    Platform.runLater(()-> ans_D.setDisable(false));
    Platform.runLater(()-> chart_pane.setVisible(false));
    new FadeIn(ans_A).setSpeed(2).play();
    Platform.runLater(()-> ans_A.setDisable(false));
    new FadeIn(ans_B).setSpeed(2).play();
    Platform.runLater(()->  ans_B.setDisable(false));
    new FadeIn(ans_C).setSpeed(2).play();
    Platform.runLater(()->  ans_C.setDisable(false));
    new FadeIn(ans_D).setSpeed(2).play();
    Platform.runLater(()->  ans_D.setDisable(false));
    Platform.runLater(()->  textBubble.setVisible(false));
    Platform.runLater(()->  opinion_bp.setVisible(false));
    Platform.runLater(()->  opinion_lb.setVisible(false));

}

    void setQuestion(ResultSet resultSet) throws Exception {
        if (!volume_cross.isVisible())
        music_Play(true,checkTrack());

        if (answeredQuestions < 15 && answeredQuestions>0){
            money_pane.setRowIndex(arrow, question);
            new SlideInUp(arrow).setSpeed(1.2).play();
        }


            restore();

        resultSet.next();
        List<Integer> list = new ArrayList<>(4);
        List<Integer> answers = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            int random = rand.nextInt(4) + 2;
            list.add(random);
        }
        for (int j = 0; j < 4; j++) {
            while (answers.contains(list.get(j))) {
                list.set(j, rand.nextInt(4) + 2);

            }
            answers.add(list.get(j));
        }

        String Q=resultSet.getString(1);
        String A=resultSet.getString(answers.get(0));
        String B=resultSet.getString(answers.get(1));
        String C=resultSet.getString(answers.get(2));
        String D=resultSet.getString(answers.get(3));


          question_fl.setText(Q);


        Platform.runLater(()->   ans_A.setText(A));
        Platform.runLater(()->  ans_B.setText(B));
        Platform.runLater(()->  ans_C.setText(C));
        Platform.runLater(()->  ans_D.setText(D));

        question--;
        getAnswer();
    }



    private Connection connect() {
        String url = "jdbc:sqlite:Стани богат_db.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }




    void setDiff(String diff) throws Exception {
        String query = "SELECT * FROM " + diff + " WHERE id > 1 AND id >= (abs(random()) % (SELECT max(id) FROM " + diff + ")) LIMIT 5";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
    }

    Task<Void> taskEnd = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            TimeUnit.SECONDS.sleep(5);

            Platform.runLater(()-> correctAnswer.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18"));
            new Pulse(correctAnswer).setCycleCount(4).play();
            correctSelection(true,0.7);
            TimeUnit.SECONDS.sleep(5);


            calculateMoneyWon();
            Platform.runLater(()->  continue_lb.setText("Спечелихте!!!"));

            disable();

            return null;
        }
    };


    Task<Void> taskWrong = new Task<Void>() {
        @Override
        protected Void call() throws Exception {


            Thread.sleep(5000);
            worng_marked();
            getAnswer();
            Platform.runLater(()-> correctAnswer.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18"));
            new Pulse(correctAnswer).setCycleCount(4).play();
            calculateMoneyWon();
            getAnswer();
            Thread.sleep(5000);
            disable();
            new SlideInUp(continue_pane).play();

            return null;
        }
    };

private void disable(){
    Platform.runLater(()-> ans_A.setDisable(true));
    Platform.runLater(()-> joker1.setDisable(true));
    Platform.runLater(()-> ans_D.setDisable(true));
    Platform.runLater(()-> ans_C.setDisable(true));
    Platform.runLater(()-> joker2.setDisable(true));
    Platform.runLater(()-> joker3.setDisable(true));
    Platform.runLater(()-> opinion_bp.setVisible(false));
    Platform.runLater(()-> textBubble.setVisible(false));
    Platform.runLater(()-> opinion_lb.setVisible(false));
    Platform.runLater(()-> ans_B.setDisable(true));
    Platform.runLater(()-> chart_pane.setVisible(false));


    Platform.runLater(()->  trans.setVisible(true));
    Platform.runLater(()->  trans.toFront());
    Platform.runLater(()->  continue_pane.setVisible(true));
    Platform.runLater(()->  chart_pane.setVisible(false));
    Platform.runLater(()->  opinion_bp.setVisible(false));
    Platform.runLater(()->  textBubble.setVisible(false));
    Platform.runLater(()->  opinion_lb.setVisible(false));
    Platform.runLater(()->  continue_pane.toFront());

}


    Service serviceMedium = new Service() {
        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(5000);
                    correctSelection(true,0.7);
                    Platform.runLater(()-> correctAnswer.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18"));
                    new Pulse(correctAnswer).setCycleCount(4).play();
                    Thread.sleep(5000);
                    setDiff("MediumQuestions");
                    setQuestion(resultSet);
                    return null;
                }
            };
        }
    };



    Service serviceCorrect = new Service() {
        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Void call() throws Exception {
                    TimeUnit.SECONDS.sleep(5);
                    correctSelection(true,0.7);
                    correctAnswer.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18");
                    new Pulse(correctAnswer).setCycleCount(4).play();
                    TimeUnit.SECONDS.sleep(5);
                    setQuestion(resultSet);
                    return null;
                }
            };
        }
    };


    Service serviceHard = new Service() {
        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(5000);
                    correctSelection(true,0.7);
                    Platform.runLater(()-> correctAnswer.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18"));
                    new Pulse(correctAnswer).setCycleCount(4).play();
                    Thread.sleep(5000);
                    setDiff("HardQuestions");
                    setQuestion(resultSet);
                    return null;
                }
            };
        }
    };



    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private void choiceMade(Button ans) throws Exception {

        answer_marked(true,1);
        if (!volume_cross.isVisible())
        music_Play(false,checkTrack());
        ans.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight:900; -fx-font-size:18; -fx-border-color: black;");
        ans_A.setDisable(true);
        ans_B.setDisable(true);
        ans_C.setDisable(true);
        ans_D.setDisable(true);

        if (ans.getText().equalsIgnoreCase(resultSet.getString(2))) {
            answeredQuestions++;

            if (answeredQuestions == 5) {
                getAnswer();
                if (!serviceCorrect.isRunning()) {
                    serviceMedium.reset();
                    serviceMedium.start();
                }


            } else if (answeredQuestions == 10) {
                getAnswer();
                if (!serviceHard.isRunning()) {
                    serviceHard.reset();
                    serviceHard.start();
                }

            } else if (answeredQuestions == 15) {
                getAnswer();
                new Thread(taskEnd).start();

            }else{
                getAnswer();
                if (!serviceCorrect.isRunning()) {
                    serviceCorrect.reset();
                    serviceCorrect.start();
                }
            }
        } else {
            new Thread(taskWrong).start();
        }

    }


    public void Ans_A_Clicked(MouseEvent mouseEvent) throws Exception {
        choiceMade(ans_A);
    }


    public void Ans_B_Clicked(MouseEvent mouseEvent) throws Exception {
      choiceMade(ans_B);

    }

    public void Ans_C_Clicked(MouseEvent mouseEvent) throws Exception {
       choiceMade(ans_C);

    }

    public void Ans_D_Clicked(MouseEvent mouseEvent) throws Exception {
       choiceMade(ans_D);

    }


    void getAnswer() throws SQLException {
        if (ans_A.getText().equalsIgnoreCase(resultSet.getString(2))) {
            correct = 1;
            correctAnswer = ans_A;
        }
        if (ans_B.getText().equalsIgnoreCase(resultSet.getString(2))) {
            correct = 2;
            correctAnswer = ans_B;
        }
        if (ans_C.getText().equalsIgnoreCase(resultSet.getString(2))) {
            correct = 3;
            correctAnswer = ans_C;
        }
        if (ans_D.getText().equalsIgnoreCase(resultSet.getString(2))) {
            correct = 4;
            correctAnswer = ans_D;
        }

    }


    public void joker_1_Clicked(MouseEvent mouseEvent) throws SQLException, InterruptedException {
        menu_click(true,0.4);
        getAnswer();
        int random1 = rand.nextInt(4) + 1;
        int random2 = rand.nextInt(4) + 1;

        while (random2 == random1 || random1 == correct || random2 == correct) {
            random1 = rand.nextInt(4) + 1;
            random2 = rand.nextInt(4) + 1;
        }

        switch (random1) {
            case 1:
                if (!ans_A.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_A).setSpeed(2.4).play();
                    ans_A.setDisable(true);

                }
                break;
            case 2:
                if (!ans_B.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_B).setSpeed(2.4).play();
                    ans_B.setDisable(true);
                }
                break;
            case 3:
                if (!ans_C.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_C).setSpeed(2.4).play();
                    ans_C.setDisable(true);
                }
                break;
            case 4:
                if (!ans_D.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_D).setSpeed(2.4).play();
                    ans_D.setDisable(true);
                }
                break;
        }

        switch (random2) {
            case 1:
                if (!ans_A.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_A).setSpeed(2.4).play();
                    ans_A.setDisable(true);
                }
                break;
            case 2:
                if (!ans_B.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_B).setSpeed(2.4).play();
                    ans_B.setDisable(true);
                }
                break;
            case 3:
                if (!ans_C.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_C).setSpeed(2.4).play();
                    ans_C.setDisable(true);
                }
                break;
            case 4:
                if (!ans_D.getText().equalsIgnoreCase(resultSet.getString(2))) {
                    new FadeOut(ans_D).setSpeed(2.4).play();
                    ans_D.setDisable(true);
                }
                break;
        }
        joker1Cross.setVisible(true);
        joker1.setDisable(true);
    }


    public void ContinueBTN_Hovered(MouseEvent mouseEvent) {

        continue_btn.setCursor(new ImageCursor(cur));

    }

    public void continue_Clicked(MouseEvent mouseEvent) throws IOException {
        menu_click(true,0.4);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("..//Interfaces//EndScreen.fxml"));
        Parent root = (Parent) loader.load();
        EndScreenController ed = loader.getController();
        ed.setCheckMoney(moneyWon);

        play_pane.getChildren().setAll(root);

    }

    public void X1_Clicked(MouseEvent mouseEvent) {
        trans.setVisible(false);
        ans_A.setDisable(false);
        ans_B.setDisable(false);
        ans_C.setDisable(false);
        ans_D.setDisable(false);
        joker1.setDisable(false);
        joker2.setDisable(false);
        joker3.setDisable(false);
        new FadeOut(confirm_pane).setSpeed(2.4).play();
        confirm_pane.setDisable(true);
    }

    public void get_money_Hovered(MouseEvent mouseEvent) {

        get_money.setCursor(new ImageCursor(cur));
    }

    public void stay_btn_Hovered(MouseEvent mouseEvent) {

        stay_btn.setCursor(new ImageCursor(cur));
    }

    public void quit_Clicked(MouseEvent mouseEvent) {
        menu_click(true,0.4);
        trans.setVisible(true);
        trans.toFront();
        ans_A.setDisable(true);
        ans_B.setDisable(true);
        ans_C.setDisable(true);
        ans_D.setDisable(true);
        joker1.setDisable(true);
        joker2.setDisable(true);
        joker3.setDisable(true);
        confirm_pane.setVisible(true);
        confirm_pane.setDisable(false);
        new FadeIn(confirm_pane).setSpeed(2.4).play();
        confirm_pane.toFront();

    }

    public void quit_btn_Hovered(MouseEvent mouseEvent) {
        quit_btn.setCursor(new ImageCursor(cur));
    }


    private void calculateMoneyWhenSurrender() {
        switch (answeredQuestions) {
            case 1:
                moneyWon = "100";
                break;
            case 2:
                moneyWon = "200";
                break;
            case 3:
                moneyWon = "300";
                break;
            case 4:
                moneyWon = "400";
                break;
            case 5:
                moneyWon = "500";
                break;
            case 6:
                moneyWon = "1 000";
                break;
            case 7:
                moneyWon = "1 500";
                break;
            case 8:
                moneyWon = "2 000";
                break;
            case 9:
                moneyWon = "2 500";
                break;
            case 10:
                moneyWon = "3 000";
                break;
            case 11:
                moneyWon = "5 000";
                break;
            case 12:
                moneyWon = "10 000";
                break;
            case 13:
                moneyWon = "25 000";
                break;
            case 14:
                moneyWon = "50 000";
                break;
            case 15:
                moneyWon = "100 000";
                break;


        }
    }

    private void calculateMoneyWon() {
        System.out.println("Answered questions: " + answeredQuestions);
        if (answeredQuestions < 5) {
            moneyWon = "0";
        } else if (answeredQuestions > 4 && answeredQuestions < 10) {
            moneyWon = "500";
        } else if (answeredQuestions > 9 && answeredQuestions < 15) {
            moneyWon = "3 000";
        } else {
            moneyWon = "100 000";
        }
    }

    public void get_money_Clicked(MouseEvent mouseEvent) throws IOException {
        menu_click(true,0.4);
        if (!volume_cross.isVisible())
        music_Play(false,checkTrack());
        calculateMoneyWhenSurrender();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("..//Interfaces//EndScreen.fxml"));
        Parent root = (Parent) loader.load();
        EndScreenController ed = loader.getController();
        ed.setCheckMoney(moneyWon);

        play_pane.getChildren().setAll(root);
    }


    private void LifelineSelected() {
        menu_click(true,0.4);
        new FadeOut(lifelines_Pane).setSpeed(2.4).play();
    lifelines_Pane.setDisable(true);
        opinion_bp.setDisable(false);
        new FadeOut(chart_pane).setSpeed(2.4).play();
        chart_pane.setDisable(true);
        opinion_bp.setVisible(true);
        opinion_bp.toFront();
        new FadeIn(opinion_bp).setSpeed(2.4).play();
        textBubble.setVisible(true);
        new FadeIn(textBubble).setSpeed(2.4).play();
        textBubble.setDisable(false);
        textBubble.toFront();
        opinion_lb.setVisible(true);
        new FadeIn(opinion_lb).setSpeed(2.4).play();
        opinion_lb.setDisable(false);
        opinion_lb.toFront();

        joker2Cross.setVisible(true);
        new FadeIn(joker2Cross).setSpeed(2.4).play();
        joker2Cross.setDisable(false);
        joker2.setDisable(true);

    }


    private void wrongThought(int opinion) {
        switch (opinion) {
            case 1:
                if (!ans_A.isDisable())
                opinion_lb.setText("Мисля, че отговорът е " + ans_A.getText() + "!");
                else
                    opinion_lb.setText("Мисля, че отговорът е " + ans_B.getText() + "!");
                break;
            case 2:
                if (!ans_B.isDisable())
                opinion_lb.setText("Мисля, че отговорът е " + ans_B.getText() + "!");
                else
                    opinion_lb.setText("Мисля, че отговорът е " + ans_C.getText() + "!");
                break;
            case 3:
                if (!ans_C.isDisable())
                opinion_lb.setText("Мисля, че отговоът е " + ans_C.getText() + "!");
                else
                    opinion_lb.setText("Мисля, че отговорът е " + ans_D.getText() + "!");
                break;
            case 4:
                if (!ans_D.isDisable())
                opinion_lb.setText("Мисля, че отговоът е " + ans_D.getText() + "!");
                else
                    opinion_lb.setText("Мисля, че отговорът е " + ans_A.getText() + "!");
                break;
        }
    }


    public void Person1_selected(MouseEvent mouseEvent) throws SQLException {
        getAnswer();
        LifelineSelected();
        int opinion = 0;
        char[] arr = people_1.getText().toCharArray();
        if (arr[arr.length - 1] == 'a' || arr[arr.length - 1] == 'A' || arr[arr.length - 1] == 'а' || arr[arr.length - 1] == 'А')
            opinion_imageView.setImage(woman);

        if (sphere_field_1.getText().equalsIgnoreCase(resultSet.getString(6))) {
            if (answeredQuestions > 5 && answeredQuestions < 10) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Мисля, че отговорът е " + correctAnswer.getText() + "!");
                        break;
                    } else wrongThought(opinion);
                }
            } else if (answeredQuestions < 5) {
                opinion_lb.setText("Почти със сигурнсот мога да кажа, че отговорът е " + correctAnswer.getText() + "!");
            } else {
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }
        } else {
            if (answeredQuestions < 5) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else if (answeredQuestions>5 && answeredQuestions<10){
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else {
                opinion = rand.nextInt(3) + 1;
                if (opinion == correct) {
                    opinion_lb.setText("Не го знам със сигурност, но ако бях на твое място, щях да заложа на " + correctAnswer.getText() + "!");
                } else wrongThought(opinion);
            }
        }
    }

    public void Person2_selected(MouseEvent mouseEvent) throws SQLException {
        getAnswer();
        LifelineSelected();
        int opinion = 0;
        char[] arr = people_2.getText().toCharArray();
        if (arr[arr.length - 1] == 'a' || arr[arr.length - 1] == 'A' || arr[arr.length - 1] == 'а' || arr[arr.length - 1] == 'А')
            opinion_imageView.setImage(woman);

        if (sphere_field_2.getText().equalsIgnoreCase(resultSet.getString(6))) {
            if (answeredQuestions > 5 && answeredQuestions < 10) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Мисля, че отговорът е " + correctAnswer.getText() + "!");
                        break;
                    } else wrongThought(opinion);
                }
            } else if (answeredQuestions < 5) {
                opinion_lb.setText("Почти със сигурнсот мога да кажа, че отговорът е " + correctAnswer.getText() + "!");
            } else {
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }
        } else {
            if (answeredQuestions < 5) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else if (answeredQuestions>5 && answeredQuestions<10){
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else {
                opinion = rand.nextInt(3) + 1;
                if (opinion == correct) {
                    opinion_lb.setText("Не го знам със сигурност, но ако бях на твое място, щях да заложа на " + correctAnswer.getText() + "!");
                } else wrongThought(opinion);
            }
        }

    }


    public void Person3_selected(MouseEvent mouseEvent) throws SQLException {
        getAnswer();
        LifelineSelected();
        int opinion = 0;
        char[] arr = people_3.getText().toCharArray();
        if (arr[arr.length - 1] == 'a' || arr[arr.length - 1] == 'A' || arr[arr.length - 1] == 'а' || arr[arr.length - 1] == 'А')
            opinion_imageView.setImage(woman);

        if (sphere_field_3.getText().equalsIgnoreCase(resultSet.getString(6))) {
            if (answeredQuestions > 5 && answeredQuestions < 10) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Мисля, че отговорът е " + correctAnswer.getText() + "!");
                        break;
                    } else wrongThought(opinion);
                }
            } else if (answeredQuestions < 5) {
                opinion_lb.setText("Почти със сигурнсот мога да кажа, че отговорът е " + correctAnswer.getText() + "!");
            } else {
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }
        } else {
            if (answeredQuestions < 5) {
                for (int i = 0; i < 3; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else if (answeredQuestions>5 && answeredQuestions<10){
                for (int i = 0; i < 2; i++) {
                    opinion = rand.nextInt(3) + 1;
                    if (opinion == correct) {
                        opinion_lb.setText("Не мога да кажа със сигурност, но мисля че е " + correctAnswer.getText() + "!");
                    } else wrongThought(opinion);
                }
            }else {
                opinion = rand.nextInt(3) + 1;
                if (opinion == correct) {
                    opinion_lb.setText("Не го знам със сигурност, но ако бях на твое място, щях да заложа на " + correctAnswer.getText() + "!");
                } else wrongThought(opinion);
            }
        }


    }

    private void adaptCorrect(int to,int from){
        switch (correct){
            case 1:
                currentOpinion=rand.nextInt(to)+from;
                percentage1.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage-1);
                percentage2.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage);
                percentage3.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                percentage4.setText(String.valueOf(totalPercentage));
                break;
            case 2:
                currentOpinion=rand.nextInt(to)+from;
                percentage2.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage-1);
                percentage1.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage);
                percentage3.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                percentage4.setText(String.valueOf(totalPercentage));

                break;
            case 3:
                currentOpinion=rand.nextInt(to)+from;
                percentage3.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage-1);
                percentage1.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage);
                percentage2.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                percentage4.setText(String.valueOf(totalPercentage));

                break;
            case 4:
                currentOpinion=rand.nextInt(to)+from;
                percentage4.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage-1);
                percentage3.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                currentOpinion=rand.nextInt(totalPercentage);
                percentage2.setText(String.valueOf(currentOpinion));
                totalPercentage-=currentOpinion;

                percentage1.setText(String.valueOf(totalPercentage));

                break;

        }

    }

    private int crowdOpinion =0;
    private int totalPercentage =100;
    private int currentOpinion=0;

    private void addSigns(){
        percentage1.setText(percentage1.getText()+"%");
        percentage2.setText(percentage2.getText()+"%");
        percentage3.setText(percentage3.getText()+"%");
        percentage4.setText(percentage4.getText()+"%");


    }



    private  void checkIfJoker1Used(){
        if (ans_A.isDisable()) {
            percentage2.setText(String.valueOf(Integer.valueOf(percentage1.getText()) + Integer.valueOf(percentage2.getText())));
            percentage1.setText("0");
        }
        if (ans_B.isDisable()) {
            percentage3.setText(String.valueOf(Integer.valueOf(percentage2.getText()) + Integer.valueOf(percentage3.getText())));
            percentage2.setText("0");
        }
        if (ans_C.isDisable()) {
            percentage4.setText(String.valueOf(Integer.valueOf(percentage3.getText()) + Integer.valueOf(percentage4.getText())));
            percentage3.setText("0");
        }
        if (ans_D.isDisable()) {
            percentage1.setText(String.valueOf(Integer.valueOf(percentage4.getText()) + Integer.valueOf(percentage1.getText())));
            percentage4.setText("0");
        }


    }
    public void joker_3_Clicked(MouseEvent mouseEvent) throws SQLException {
        getAnswer();
        menu_click(true,0.4);
        joker3.setDisable(true);
        joker3Cross.setVisible(true);
        new FadeIn(joker3Cross).setSpeed(2.4).play();
        chart_pane.setVisible(true);
        new FadeIn(chart_pane).setSpeed(2.4).play();

        new FadeOut(opinion_bp).setSpeed(2.4).play();
        new FadeOut(textBubble).setSpeed(2.4).play();
        new FadeOut(opinion_lb).setSpeed(2.4).play();

        chart_pane.toFront();
        XYChart.Series set1 = new XYChart.Series();
        set1.setName("Отговори на публиката");
       if (answeredQuestions<5){
           adaptCorrect(18,75);

       }else if(answeredQuestions>=5 && answeredQuestions<10){
           for (int i=0;i<2;i++) {
               crowdOpinion = rand.nextInt(3) + 1;
               if (crowdOpinion==correct){
                 adaptCorrect(25,50);

                   checkIfJoker1Used();
                       set1.getData().add(new XYChart.Data("A",Integer.parseInt(percentage1.getText())));
                   set1.getData().add(new XYChart.Data("B",Integer.parseInt(percentage2.getText())));
                   set1.getData().add(new XYChart.Data("C",Integer.parseInt(percentage3.getText())));
                   set1.getData().add(new XYChart.Data("D",Integer.parseInt(percentage4.getText())));
                   crowd_chart.getData().addAll(set1);
                   addSigns();
                   return;
               }
           }
               currentOpinion=rand.nextInt(25)+50;
               percentage4.setText(String.valueOf(currentOpinion));
               totalPercentage-=currentOpinion;

               currentOpinion=rand.nextInt(totalPercentage-1);
               percentage3.setText(String.valueOf(currentOpinion));
               totalPercentage-=currentOpinion;

               currentOpinion=rand.nextInt(totalPercentage);
               percentage2.setText(String.valueOf(currentOpinion));
               totalPercentage-=currentOpinion;

               percentage1.setText(String.valueOf(totalPercentage));

       }else{

               crowdOpinion = rand.nextInt(3) + 1;

               if (crowdOpinion==correct){
                   adaptCorrect(15,50);
                   checkIfJoker1Used();
                   set1.getData().add(new XYChart.Data("A",Integer.parseInt(percentage1.getText())));
                   set1.getData().add(new XYChart.Data("B",Integer.parseInt(percentage2.getText())));
                   set1.getData().add(new XYChart.Data("C",Integer.parseInt(percentage3.getText())));
                   set1.getData().add(new XYChart.Data("D",Integer.parseInt(percentage4.getText())));
                   crowd_chart.getData().addAll(set1);
                   addSigns();
                   return;
               }else{

                   currentOpinion=rand.nextInt(25)+50;
                   percentage2.setText(String.valueOf(currentOpinion));
                   totalPercentage-=currentOpinion;

                   currentOpinion=rand.nextInt(totalPercentage-1);
                   percentage3.setText(String.valueOf(currentOpinion));
                   totalPercentage-=currentOpinion;

                   currentOpinion=rand.nextInt(totalPercentage);
                   percentage4.setText(String.valueOf(currentOpinion));
                   totalPercentage-=currentOpinion;

                   percentage1.setText(String.valueOf(totalPercentage));
               }
           }

        checkIfJoker1Used();
        set1.getData().add(new XYChart.Data("A",Integer.parseInt(percentage1.getText())));
        set1.getData().add(new XYChart.Data("B",Integer.parseInt(percentage2.getText())));
        set1.getData().add(new XYChart.Data("C",Integer.parseInt(percentage3.getText())));
        set1.getData().add(new XYChart.Data("D",Integer.parseInt(percentage4.getText())));
        crowd_chart.getData().addAll(set1);
        addSigns();
    }


    public void volume_Clicked(MouseEvent mouseEvent) {
        music_Play(false,checkTrack());
        volume_cross.setVisible(true);
    }




    public void volume_Hovered(MouseEvent mouseEvent) {
        volume_icon.setCursor(new ImageCursor(cur));
    }

    public void volume_cross_Clicked(MouseEvent mouseEvent) {
        music_Play(true,checkTrack());
        volume_cross.setVisible(false);
    }

    public void volume_cross_Hovered(MouseEvent mouseEvent) {
        volume_cross.setCursor(new ImageCursor(cur));
    }
}
