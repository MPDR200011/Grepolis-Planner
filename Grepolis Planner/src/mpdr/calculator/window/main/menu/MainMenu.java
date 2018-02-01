package mpdr.calculator.window.main.menu;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import mpdr.calculator.control.MainController;

public class MainMenu {

	private static MenuBar menuBar;

	private static Menu planMenu;
	private static Menu choosePlan;
	private static MenuItem createPlan;
	private static MenuItem editPlan;
	private static MenuItem deletePlan;

	private static Menu toolsMenu;
	private static MenuItem exportPlan;
	private static MenuItem quickCalculator;

	public static void loadMenu(MenuBar menu) {
		menuBar = menu;

		// Plan Menu
		planMenu = new Menu("Plan");
		loadPlanList();
		createPlan = new MenuItem("Create Plan");
		createPlan.setOnAction(e -> {
			try {
				createPlan();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		editPlan = new MenuItem("Edit Plan");
		editPlan.setOnAction(e -> {
			try {
				editPlan();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		deletePlan = new MenuItem("Delete Plan");
		deletePlan.setOnAction(e -> deletePlan());

		planMenu.getItems().addAll(new SeparatorMenuItem(), createPlan, editPlan, deletePlan);

		// Tools Menu
		toolsMenu = new Menu("Tools");
		exportPlan = new MenuItem("Export plan to Excel File");
		exportPlan.setOnAction(e -> export());
		quickCalculator = new MenuItem("Quick Date Calculator");
		quickCalculator.setOnAction(e -> openQuickCalculator());

		toolsMenu.getItems().addAll(exportPlan, quickCalculator);

		menuBar.getMenus().addAll(planMenu, toolsMenu);
	}

	private static void loadPlanList() {
		ObservableList<String> list = MainController.getPlanNameList();
		choosePlan = null;
		choosePlan = new Menu("Choose Plan");
		if (list.size() > 0) {
			for (String name : list) {
				MenuItem item = new MenuItem(name);
				item.setOnAction(e -> MainController.selectPlan(name));
				choosePlan.getItems().add(item);
			}
		}
		if (planMenu.getItems().size() > 0) {
			planMenu.getItems().remove(0);
		}
		planMenu.getItems().add(0, choosePlan);
	}

	private static void createPlan() throws Exception {
		MainController.createPlan();
	}

	private static void deletePlan() {
		MainController.deleteSelectedPlan();
	}

	private static void editPlan() throws Exception {
		MainController.editPlan();
	}

	public static void reloadMenu() {
		loadPlanList();
	}

	public static void export() {
		MainController.exportToExcel();
	}

	public static void openQuickCalculator() {
		MainController.openQuickCalculator();
	}
}
