package mpdr.calculator.window.main.table;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mpdr.calculator.control.MainController;

public class TableController {
	private static TableView<AttackingCity> editedTable;

	public static void loadTable(TableView<AttackingCity> table) {
		editedTable = table;

		TableColumn<AttackingCity, String> nameCol = new TableColumn<>("City Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.prefWidthProperty().bind(editedTable.widthProperty().divide(4));

		TableColumn<AttackingCity, String> destNameCol = new TableColumn<>("Destination");
		destNameCol.setCellValueFactory(new PropertyValueFactory<>("destinationName"));
		destNameCol.prefWidthProperty().bind(editedTable.widthProperty().divide(4));

		TableColumn<AttackingCity, String> departureDateCol = new TableColumn<>("Attack Date");
		departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
		departureDateCol.prefWidthProperty().bind(editedTable.widthProperty().divide(4));

		TableColumn<AttackingCity, String> arrivalDateCol = new TableColumn<>("Arrival Date");
		arrivalDateCol.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		arrivalDateCol.prefWidthProperty().bind(editedTable.widthProperty().divide(4));

		ArrayList<TableColumn<AttackingCity, String>> columns = new ArrayList<>();
		columns.add(nameCol);
		columns.add(destNameCol);
		columns.add(departureDateCol);
		columns.add(arrivalDateCol);

		editedTable.getColumns().addAll(columns);
		MainController.selectFirst();
	}

	public static void setTableContents(ObservableList<AttackingCity> list) {
		editedTable.setItems(list);
	}

	public static void loadTableWithEmpty() {
		ObservableList<AttackingCity> list = FXCollections.observableArrayList();
		setTableContents(list);
	}
}
