package sample.Controllers;

import javafx.beans.property.SimpleStringProperty;


public class Question {
private SimpleStringProperty question;

	 Question(String question) {
		this.question = new SimpleStringProperty(question);
	}

	public String getQuestion() {
		return question.get();
	}

	public SimpleStringProperty questionProperty() {
		return question;
	}

	public void setQuestion(String question) {
		this.question.set(question);
	}
}
