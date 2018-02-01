package mpdr.calculator.control;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mpdr.calculator.utility.Exporter;
import mpdr.calculator.window.edit.addattacker.AddAttackerWindow;
import mpdr.calculator.window.edit.addattacker.AddAttackerWindowController;
import mpdr.calculator.window.edit.editplan.EditPlanWindow;
import mpdr.calculator.window.edit.editplan.EditPlanWindowController;
import mpdr.calculator.window.main.MainWindowController;
import mpdr.calculator.window.main.menu.MainMenu;
import mpdr.calculator.window.main.table.AttackingCity;
import mpdr.calculator.window.main.table.Plan;
import mpdr.calculator.window.main.table.TableController;
import mpdr.calculator.window.newplan.NewPlanWindow;
import mpdr.calculator.window.tools.calculator.QuickCalculatorWindow;

public class MainController {

	static ObservableList<String> planNameList = FXCollections.observableArrayList(new ArrayList<>());
	static ObservableMap<String, Plan> planMap = FXCollections.observableMap(new HashMap<>());
	static ArrayList<String> markedForDeletion = new ArrayList<>();

	private static Plan selectedPlan;
	private static MainWindowController mainWindowControllerInstance;

	// Getters
	public static ObservableList<String> getPlanNameList() {
		return planNameList;
	}

	public static Plan getSelectedPlan() {
		return (selectedPlan != null) ? selectedPlan : new Plan("", new HashMap<>());
	}

	// Setters
	public static void setMainWindowController(MainWindowController controller) {
		mainWindowControllerInstance = controller;
	}

	// Data Transformation
	public static void selectFirst() {
		if (planNameList.size() > 0) {
			selectPlan(planNameList.get(0));
		}
	}

	public static void selectPlan(String name) {
		Plan plan = planMap.get(name);
		selectedPlan = plan;
		reloadTable(plan);
		mainWindowControllerInstance.putName(plan.getName());
		mainWindowControllerInstance.setPlanName();
	}

	public static boolean addPlan(String planName, HashMap<String, String> targetMap) {
		if (!planNameList.contains(planName)) {
			planNameList.add(planName);
			planMap.put(planName, new Plan(planName, targetMap));
			selectPlan(planName);
			MainMenu.reloadMenu();
			return true;
		} else {
			return false;
		}

	}

	public static void deleteSelectedPlan() {
		if (selectedPlan != null) {
			markedForDeletion.add(selectedPlan.getName());
			planNameList.remove(selectedPlan.getName());
			planMap.remove(selectedPlan.getName());
			if (planNameList.size() > 0) {
				selectPlan(planNameList.get(0));
			}
			MainMenu.reloadMenu();
		}
	}

	public static void modifyPlan(String name, HashMap<String, String> map) {
		planMap.get(name).setTargetMap(map);
		selectPlan(name);
	}

	public static boolean replaceMap(String oldName, String newName) {
		if (!planMap.keySet().contains(newName)) {
			Plan plan = planMap.get(oldName);
			markedForDeletion.add(oldName);
			planNameList.remove(oldName);
			planMap.remove(oldName);
			addPlan(newName, plan.getTargetMap());
			planMap.get(newName).setAttackingCitiesList(plan.getAttackingCitiesList());
			selectPlan(newName);
			return true;
		} else {
			return false;
		}
	}

	public static void deleteAttacker(AttackingCity city) {
		System.out.println(selectedPlan.getName());
		selectedPlan.getAttackingCitiesList().remove(city);
		reloadTable(selectedPlan);
	}

	// Window element control
	public static void reloadTable(Plan plan) {
		if (plan != null) {
			TableController.setTableContents(plan.getAttackingCitiesList());
		} else {
			TableController.loadTableWithEmpty();
		}
	}

	// Window initialization
	public static void createPlan() throws Exception {
		NewPlanWindow window = new NewPlanWindow();
		window.display();
	}

	public static void editPlan() throws Exception {
		if (selectedPlan != null) {
			EditPlanWindow window = new EditPlanWindow();
			EditPlanWindowController controller = new EditPlanWindowController(selectedPlan);
			window.display(controller);
		}
	}

	public static void addAttacker() throws Exception {
		if (selectedPlan != null) {
			if (!selectedPlan.getTargetMap().keySet().isEmpty()) {
				AddAttackerWindow window = new AddAttackerWindow();
				AddAttackerWindowController controller = new AddAttackerWindowController(selectedPlan);
				window.display(controller);
			}
		}
	}

	public static void openQuickCalculator() {
		QuickCalculatorWindow window = new QuickCalculatorWindow();
		try {
			window.dispaly();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Data Output
	public static void exportToExcel() {
		Exporter.export(getSelectedPlan());
	}
}
