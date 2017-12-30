package mpdr.calculator.window.main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import mpdr.calculator.controller.MainController;
import mpdr.calculator.window.main.menu.MainMenu;
import mpdr.calculator.window.main.table.AttackingCity;
import mpdr.calculator.window.main.table.TableController;

public class MainWindowController {

	public MenuBar menu;

	public TableView<AttackingCity> table;

	public Label planName;
	public String name;

	public Button addAttackerButton;

	public void initialize() {
		MainController.setMainWindowController(this);

		MainMenu.loadMenu(menu);

		planName.setText("Plan: " + MainController.getSelectedPlan().getName());

		TableController.loadTable(table);
	}

	public void putName(String planName) {
		name = planName;
	}

	public void setPlanName() {
		planName.setText("Plan: " + name);
	}

	public void addAttackerAction() throws Exception {
		MainController.addAttacker();
	}

	public void removeAttackerAction() {
		AttackingCity city = table.getSelectionModel().getSelectedItem();

		if (city != null) {
			MainController.deleteAttacker(city);
		}
	}

}
