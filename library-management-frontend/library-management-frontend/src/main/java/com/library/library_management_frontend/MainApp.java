package com.library.library_management_frontend;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Library Management System");
		primaryStage.show();
	}
}

