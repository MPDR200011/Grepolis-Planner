package mpdr.calculator.window.edit.addattacker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddAttackerWindow {

	public void display(AddAttackerWindowController controller) throws Exception {
		Stage addAttackerStage = new Stage();
		addAttackerStage.initModality(Modality.APPLICATION_MODAL);
		addAttackerStage.setTitle("Add Attacker");
		addAttackerStage.setMinWidth(200);
		addAttackerStage.setResizable(false);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		loader.setController(controller);
		VBox mainLayout = loader.load();

		Scene scene = new Scene(mainLayout, 252, 175);
		addAttackerStage.setScene(scene);
		addAttackerStage.show();
	}
}
