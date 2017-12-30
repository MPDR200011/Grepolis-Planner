package mpdr.calculator.utility;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommonMethods {

	public static void setupHorizontalLayout(HBox box) {
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(5, 0, 5, 0));
		box.setSpacing(5);
	}

	public static void setupVerticalLayout(VBox box) {
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(5, 5, 5, 5));
	}
}
