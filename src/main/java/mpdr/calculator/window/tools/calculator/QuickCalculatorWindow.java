package mpdr.calculator.window.tools.calculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuickCalculatorWindow {

	public void dispaly() throws Exception {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Calculator");
		stage.setResizable(false);
		stage.setMaxWidth(500);
		stage.setMaxHeight(300);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.showAndWait();
	}
}
