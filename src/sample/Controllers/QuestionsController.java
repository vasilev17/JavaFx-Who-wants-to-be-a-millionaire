package sample.Controllers;

import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class QuestionsController implements Initializable {
	@FXML private TableView<Question> questionTable;
	@FXML private TableColumn<Question,String> column;
	@FXML private Button add_btn,back_btn;
	@FXML private AnchorPane questions_pane,add_pane;
	@FXML private ImageView minimize_btn,close_btn;
	@FXML private MenuButton sphere, difficulty;
	@FXML private TextField question_fl, correct_fl, ans1_fl, ans2_fl, ans3_fl;




private double x = 0,y = 0;
	private Stage stage;

	Image cur = new Image("sample/Images/cursor_hand.png");




	private void menu_hover() {
		AudioClip note = new AudioClip(this.getClass().getResource("..//Sounds//menu_hover.wav").toString());
		note.setVolume(0.3);
		note.play();

	}


	private ResultSet resultSet = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		displayTableData();
	}

	private void displayTableData(){
		column.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
		try {
			questionTable.setItems(getQuestions());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void getItems(String diff) throws Exception {
		String query = "SELECT * FROM " + diff;
		Connection conn = this.connect();
		Statement stmt = conn.createStatement();
		resultSet = stmt.executeQuery(query);
	}


	private String getDIff(){
		switch (difficulty.getText()){
			case "Лесно": return "EasyQuestions";
			case "Средно": return "MediumQuestions";
			case "Трудно": return "HardQuestions";
		}
		return "Error occurred while getting difficulty";
	}



	private void addQuestion(String diff) throws SQLException {
		if (question_fl.getText().trim().equals("")||correct_fl.getText().trim().equals("")||ans1_fl.getText().trim().equals("")
				||ans2_fl.getText().trim().equals("")||ans3_fl.getText().trim().equals("")||sphere.getText().trim().equals("Сфера")
				||difficulty.getText().trim().equals("Трудност")||ans1_fl.getText().trim().equalsIgnoreCase(correct_fl.getText())
				||ans2_fl.getText().trim().equalsIgnoreCase(correct_fl.getText())||ans3_fl.getText().trim().equalsIgnoreCase(correct_fl.getText())
				||ans1_fl.getText().trim().equalsIgnoreCase(ans2_fl.getText())||ans1_fl.getText().trim().equalsIgnoreCase(ans3_fl.getText())
				||ans3_fl.getText().trim().equalsIgnoreCase(ans2_fl.getText())){

			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Внимание");
			alert.setHeaderText("Възникна грешка");
			alert.setContentText("Има празни полета!");
			alert.showAndWait();
		}else {
			String query = "INSERT INTO " + diff + " (Question,Correct,Ans1,Ans2,Ans3,Sphere) VALUES('" + question_fl.getText() + "', '"
					+ correct_fl.getText() + "', '" + ans1_fl.getText() + "', '" + ans2_fl.getText() + "', '" + ans3_fl.getText() + "', '" + sphere.getText() + "')";

			Connection conn = this.connect();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Поздравления");
			alert.setHeaderText("Въпросът беше добавен успешно!");
			alert.showAndWait();
			add_pane.setDisable(true);
			new FadeOut(add_pane).setSpeed(2.4).play();
			add_pane.setVisible(false);
			displayTableData();
			questionTable.setVisible(true);
			new FadeIn(questionTable).setSpeed(2.4).play();
			questionTable.setDisable(false);
			question_fl.setText("");
			correct_fl.setText("");
			ans1_fl.setText("");
			ans2_fl.setText("");
			ans3_fl.setText("");
			sphere.setText("Сфера");
			difficulty.setText("Трудност");
		}
	}



	public ObservableList<Question> getQuestions() throws Exception {
		ObservableList<Question> questions = FXCollections.observableArrayList();
		getItems("EasyQuestions");
		while (resultSet.next()) {
			questions.add(0, new Question(resultSet.getString(1)));
		}

		getItems("MediumQuestions");
		while (resultSet.next()) {
			questions.add(0, new Question(resultSet.getString(1)));
		}

		getItems("HardQuestions");
		while (resultSet.next()) {
			questions.add(0, new Question(resultSet.getString(1)));
		}
		return questions;
	}

	public void Add_Hovered(MouseEvent mouseEvent) {
		menu_hover();
		new Pulse(add_btn).setSpeed(1.2).play();
		add_btn.setCursor(new ImageCursor(cur));
	}

	public void Back_Hovered(MouseEvent mouseEvent) {
		menu_hover();
		new Pulse(back_btn).setSpeed(1.2).play();
		back_btn.setCursor(new ImageCursor(cur));
	}


	public void Add_Clicked(MouseEvent mouseEvent) throws SQLException {
		if (!questionTable.isDisable()) {
			new FadeOut(questionTable).setSpeed(2.4).play();
			questionTable.setDisable(true);
			add_pane.setVisible(true);
			add_pane.setDisable(false);
			new FadeIn(add_pane).setSpeed(2.4).play();
		}else addQuestion(getDIff());


	}

	public void Back_Clicked(MouseEvent mouseEvent) throws IOException {


		if (!questionTable.isDisable()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//Interfaces//Menu.fxml"));
			AnchorPane root = loader.load();
			questions_pane.getChildren().setAll(root);
		}else{
			displayTableData();
			questionTable.setDisable(false);
			questionTable.setVisible(true);
			new FadeIn(questionTable).setSpeed(2.4).play();
			add_pane.setVisible(false);
			add_pane.setDisable(true);
		}
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
		Stage stage = (Stage) questions_pane.getScene().getWindow();
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

	public void M1_option1_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(0).getText());
	}

	public void M1_option2_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(1).getText());
	}

	public void M1_option3_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(2).getText());
	}

	public void M1_option4_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(3).getText());
	}

	public void M1_option5_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(4).getText());
	}

	public void M1_option6_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(5).getText());
	}

	public void M1_option7_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(6).getText());
	}

	public void M1_option8_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(7).getText());
	}
	public void M1_option9_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(8).getText());
	}
	public void M1_option10_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(9).getText());
	}
	public void M1_option11_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(10).getText());
	}
	public void M1_option12_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(11).getText());
	}
	public void M1_option13_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(12).getText());
	}
	public void M1_option14_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(13).getText());
	}
	public void M1_option15_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(14).getText());
	}
	public void M1_option16_Clicked(ActionEvent actionEvent) {
		sphere.setText(sphere.getItems().get(15).getText());
	}




	public void sphere_1_Hovered(MouseEvent mouseEvent) {

	}




	public void M2_option11_Clicked(ActionEvent actionEvent) {
		difficulty.setText(difficulty.getItems().get(0).getText());
	}

	public void M2_option12_Clicked(ActionEvent actionEvent) {
		difficulty.setText(difficulty.getItems().get(1).getText());
	}

	public void M2_option13_Clicked(ActionEvent actionEvent) {
		difficulty.setText(difficulty.getItems().get(2).getText());
	}
}
