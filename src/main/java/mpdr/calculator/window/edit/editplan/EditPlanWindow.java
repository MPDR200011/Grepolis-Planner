package mpdr.calculator.window.edit.editplan;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditPlanWindow {

	public void display(EditPlanWindowController controller) throws Exception {
		Stage editPlanStage = new Stage();
		editPlanStage.initModality(Modality.APPLICATION_MODAL);
		editPlanStage.setTitle("Edit Plan");
		editPlanStage.setMinWidth(200);
		editPlanStage.setMinHeight(200);
		editPlanStage.setResizable(false);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		loader.setController(controller);
		VBox mainLayout = loader.load();

		Scene editPlanScene = new Scene(mainLayout);
		editPlanStage.setScene(editPlanScene);
		editPlanStage.showAndWait();
	}

}
