package mpdr.calculator.window.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mpdr.calculator.controller.Handler;

public class MainWindow extends Application {

	public static void initialize(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Calculator");
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			Handler.save();
			primaryStage.close();
		});

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		Parent root = loader.load();

		Scene mainScene = new Scene(root, 500, 500);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}