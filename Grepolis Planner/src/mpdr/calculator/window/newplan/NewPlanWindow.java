package mpdr.calculator.window.newplan;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewPlanWindow {

	private Stage newPlanStage;

	public void display() throws Exception {
		newPlanStage = new Stage();
		newPlanStage.initModality(Modality.APPLICATION_MODAL);
		newPlanStage.setTitle("New Plan");
		newPlanStage.setMinWidth(200);
		newPlanStage.setMinHeight(200);
		newPlanStage.setResizable(false);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		Parent mainLayout = loader.load();

		Scene newPlanScene = new Scene(mainLayout);
		newPlanStage.setScene(newPlanScene);
		newPlanStage.showAndWait();
	}

	public void close() {
		newPlanStage.close();
	}
}
