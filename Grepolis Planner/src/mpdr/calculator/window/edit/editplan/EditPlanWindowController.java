package mpdr.calculator.window.edit.editplan;

import java.util.HashMap;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpdr.calculator.controller.MainController;
import mpdr.calculator.utility.MathMachine;
import mpdr.calculator.window.main.table.Plan;

public class EditPlanWindowController {

	public TextField planNameField;

	public TextField targetNameField;
	public TextField arrivalDayField;
	public TextField arrivalMonthField;
	public TextField arrivalYearField;
	public TextField arrivalHoursField;
	public TextField arrivalMinutesField;
	public TextField arrivalSecondsField;

	public HashMap<String, String> targetMap;

	public ObservableList<String> listViewItems;
	public ObservableList<String> targetNameList;

	public ListView<String> addedTargetsList;

	public Label alertLabel;
	public Label errorLabel;

	public Plan editedPlan;
	public String oldName;

	public EditPlanWindowController(Plan plan) {
		editedPlan = plan;

		oldName = plan.getName();

		targetMap = plan.getTargetMap();
		listViewItems = FXCollections.observableArrayList();
		targetNameList = FXCollections.observableArrayList();
	}

	public void initialize() {
		planNameField.setText(oldName);
		buildListView(editedPlan);
	}

	public void buildListView(Plan plan) {
		HashMap<String, String> map = plan.getTargetMap();
		for (String targetName : map.keySet()) {
			listViewItems.add(targetName + " " + "(Attack arrives at " + map.get(targetName) + ")");
			targetNameList.add(
					listViewItems.indexOf(targetName + " " + "(Attack arrives at " + map.get(targetName) + ")"),
					targetName);
		}
		addedTargetsList.setItems(listViewItems);
	}

	public void addTarget() {
		if (targetNameList == null) {
			targetMap = new HashMap<>();
			listViewItems = FXCollections.observableArrayList();
			targetNameList = FXCollections.observableArrayList();
		}

		int[] monthsWith31Days = { 1, 3, 5, 7, 8, 10, 12 };
		int[] monthsWith30Days = { 4, 6, 9, 11 };

		// Get target name
		String targetName = targetNameField.getText().trim();

		// Check if duplicate
		if (targetNameList.contains(targetName.trim())) {
			alertLabel.setText("Target with that name already exists");
			return;
		}

		// Check target name
		if (targetName.replaceAll("\\s+", "").length() == 0) {
			alertLabel.setText("Target name is empty");
		}

		// Get Date Data
		String aDay = arrivalDayField.getText();
		String aMonth = arrivalMonthField.getText();
		String aYear = arrivalYearField.getText();
		String aHours = arrivalHoursField.getText();
		String aMinutes = arrivalMinutesField.getText();
		String aSeconds = arrivalSecondsField.getText();

		// Remove Spaces from numbers
		aDay = aDay.replaceAll("\\s+", "");
		aMonth = aMonth.replaceAll("\\s+", "");
		aYear = aYear.replaceAll("\\s+", "");
		aHours = aHours.replaceAll("\\s+", "");
		aMinutes = aMinutes.replaceAll("\\s+", "");
		aSeconds = aSeconds.replaceAll("\\s+", "");

		if (aDay.equals(""))
			aDay = "00";
		if (aMonth.equals(""))
			aMonth = "00";
		if (aYear.equals(""))
			aYear = "00";
		if (aHours.equals(""))
			aHours = "00";
		if (aMinutes.equals(""))
			aMinutes = "00";
		if (aSeconds.equals(""))
			aSeconds = "00";

		// check if inputs are convertible to positive ints
		if (!MathMachine.isPositiveInteger(aDay)) {
			alertLabel.setText("Invalid Day");
			return;
		}
		if (!MathMachine.isPositiveInteger(aMonth)) {
			alertLabel.setText("Invalid Month");
			return;
		}
		if (!MathMachine.isPositiveInteger(aYear)) {
			alertLabel.setText("Invalid Years");
			return;
		}
		if (!MathMachine.isPositiveInteger(aHours)) {
			alertLabel.setText("Invalid Hours");
			return;
		}
		if (!MathMachine.isPositiveInteger(aMinutes)) {
			alertLabel.setText("Invalid Minutes");
			return;
		}
		if (!MathMachine.isPositiveInteger(aSeconds)) {
			alertLabel.setText("Invalid Seconds");
			return;
		}

		// Check if date inputs correspond to proper calendar rules
		int iAMonth = Integer.parseInt(aMonth);

		if (IntStream.of(monthsWith31Days).anyMatch(x -> x == iAMonth)) {
			if (Integer.parseInt(aDay) > 31 || Integer.parseInt(aDay) < 1) {
				alertLabel.setText("Invalid day number for month");
				return;
			}
		} else if (IntStream.of(monthsWith30Days).anyMatch(x -> x == iAMonth)) {
			if (Integer.parseInt(aDay) > 30 || Integer.parseInt(aDay) < 1) {
				alertLabel.setText("Invalid day number for month");
				return;
			}
		} else if (Integer.parseInt(aMonth) == 2) {
			if ((Integer.parseInt(aYear) % 4) == 0) {
				if (Integer.parseInt(aDay) > 29 || Integer.parseInt(aDay) < 1) {
					alertLabel.setText("Invalid February day");
					return;
				}
			} else if (Integer.parseInt(aDay) > 28 || Integer.parseInt(aDay) < 1) {
				alertLabel.setText("Invalid February day");
				return;
			}
		} else {
			alertLabel.setText("Invalid Month");
			return;
		}

		if (Integer.parseInt(aHours) > 24) {
			alertLabel.setText("Invalid Hours");
			return;
		}
		if (Integer.parseInt(aMinutes) > 59) {
			alertLabel.setText("Invalid Minutes");
			return;
		}
		if (Integer.parseInt(aSeconds) > 59) {
			alertLabel.setText("Invalid Seconds");
			return;
		}
		if (Integer.parseInt(aYear) < 1970) {
			alertLabel.setText("Year is too early");
			return;
		}

		String arrivalDateString = aDay + "/" + aMonth + "/" + aYear + " " + aHours + ":" + aMinutes + ":" + aSeconds;

		targetMap.put(targetName, arrivalDateString);
		listViewItems.add(targetName + " " + "(Attack arrives at " + arrivalDateString + ")");
		targetNameList.add(listViewItems.indexOf(targetName + " " + "(Attack arrives at " + arrivalDateString + ")"),
				targetName);
		addedTargetsList.setItems(listViewItems);

		targetNameField.setText("");

		arrivalDayField.setText("");
		arrivalMonthField.setText("");
		arrivalYearField.setText("");
		arrivalHoursField.setText("");
		arrivalMinutesField.setText("");
		arrivalSecondsField.setText("");
	}

	public void removeTarget() {
		if (addedTargetsList.getSelectionModel().getSelectedIndex() >= 0) {
			String selected = addedTargetsList.getSelectionModel().getSelectedItem();

			targetMap.remove(targetNameList.get(listViewItems.indexOf(selected)));
			targetNameList.remove(listViewItems.indexOf(selected));
			listViewItems.remove(selected);

			addedTargetsList.setItems(listViewItems);
		}
	}

	public void finish() {
		String oldPlanName = oldName;
		String newPlanName = planNameField.getText();

		if (newPlanName.replaceAll("\\s+", "").length() == 0)
			return;

		if (newPlanName.trim().equals(oldPlanName)) {
			MainController.modifyPlan(newPlanName.trim(), targetMap);
		} else {
			if (!MainController.replaceMap(oldPlanName, newPlanName.trim())) {
				errorLabel.setText("Plan name already exists");
				return;
			}
		}

		Stage stage = (Stage) planNameField.getScene().getWindow();
		stage.close();
	}
}
