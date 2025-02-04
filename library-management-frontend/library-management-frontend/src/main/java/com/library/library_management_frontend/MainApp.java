package com.library.library_management_frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		primaryStage.setTitle("Library Management System");
		primaryStage.show();
	}
}
