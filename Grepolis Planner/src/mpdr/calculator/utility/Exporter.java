package mpdr.calculator.utility;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;
import mpdr.calculator.window.main.table.AttackingCity;
import mpdr.calculator.window.main.table.Plan;

public class Exporter {
	public static void export(Plan plan) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(plan.getName());

		spreadsheet.setColumnWidth(0, 6000);
		spreadsheet.setColumnWidth(1, 6000);
		spreadsheet.setColumnWidth(2, 6000);
		spreadsheet.setColumnWidth(3, 6000);

		XSSFRow titleRow = spreadsheet.createRow(0);

		Cell attackerName = titleRow.createCell(0);
		attackerName.setCellValue("Attacker Name");

		Cell targetName = titleRow.createCell(1);
		targetName.setCellValue("Target Name");

		Cell attackDate = titleRow.createCell(2);
		attackDate.setCellValue("Attack Date");

		Cell arrivalDate = titleRow.createCell(3);
		arrivalDate.setCellValue("Arrival Date");

		ObservableList<AttackingCity> dataList = plan.getAttackingCitiesList();
		int rowIndex = 0;

		for (AttackingCity city : dataList) {
			XSSFRow row = spreadsheet.createRow(++rowIndex);
			Cell cell0 = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);
			Cell cell3 = row.createCell(3);

			cell0.setCellValue(city.getName());
			cell1.setCellValue(city.getDestinationName());
			cell2.setCellValue(city.getDepartureDate());
			cell3.setCellValue(city.getArrivalDate());
		}

		try {
			File save = new File(System.getProperty("user.dir"), plan.getName() + ".xls");
			FileOutputStream out = new FileOutputStream(save);
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
