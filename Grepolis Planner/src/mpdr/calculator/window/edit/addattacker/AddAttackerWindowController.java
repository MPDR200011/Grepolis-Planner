package mpdr.calculator.window.edit.addattacker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpdr.calculator.controller.MainController;
import mpdr.calculator.window.main.table.Plan;

public class AddAttackerWindowController {

    public Plan editedPlan;

    public TextField attackerNameField;

    public ComboBox<String> targetList;

    public TextField tDays;
    public TextField tHours;
    public TextField tMins;
    public TextField tSecs;

    public Label alertLabel;

    public ObservableList<String> targetNameList;

    public AddAttackerWindowController(Plan plan) {
	editedPlan = plan;

	targetNameList = (editedPlan == null) ? FXCollections.observableArrayList()
		: FXCollections.observableArrayList(plan.getTargetMap().keySet());
    }

    public void initialize() {
	targetList.setItems(targetNameList);
	targetList.setMinWidth(70);
    }

    public void addAttacker() {
	String name = attackerNameField.getText();
	String destName = targetList.getSelectionModel().getSelectedItem();
	String arrivalDate = editedPlan.getTargetMap().get(destName);
	String days = tDays.getText();
	String hours = tHours.getText();
	String mins = tMins.getText();
	String secs = tSecs.getText();

	String tTime[] = { days, hours, mins, secs };

	if (name.replaceAll("\\s+", "").length() == 0) {
	    alertLabel.setText("Attacker name is empty");
	    return;
	}

	if (destName == null) {
	    alertLabel.setText("You didn't select a target");
	    return;
	}

	if (days.replaceAll("\\s+", "").length() == 0)
	    days = "0";
	if (hours.replaceAll("\\s+", "").length() == 0)
	    hours = "0";
	if (mins.replaceAll("\\s+", "").length() == 0)
	    mins = "0";
	if (secs.replaceAll("\\s+", "").length() == 0)
	    secs = "0";

	if (!editedPlan.addAttacker(name, destName, arrivalDate, tTime)) {
	    alertLabel.setText("Attacker name already exists");
	    return;
	}

	MainController.reloadTable(editedPlan);

	Stage stage = (Stage) attackerNameField.getScene().getWindow();

	stage.close();
    }
}
