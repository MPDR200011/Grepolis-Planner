package mpdr.calculator.window.tools.calculator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mpdr.calculator.utility.MathMachine;

public class QuickCalculatorWindowController {

	public TextField aDay;
	public TextField aMonth;
	public TextField aYear;
	public TextField aHours;
	public TextField aMinutes;
	public TextField aSeconds;

	public TextField tDays;
	public TextField tHours;
	public TextField tMinutes;
	public TextField tSeconds;

	public Label resultLabel;

	public void doCalculation() {
		try {
			calculate();
		} catch (Exception e) {
			resultLabel.setText("Something went wrong, check inputs.");
		}
	}

	private void calculate() throws Exception {
		String arrivalDateString = aDay.getText() + "/" + aMonth.getText() + "/" + aYear.getText() + " "
				+ aHours.getText() + ":" + aMinutes.getText() + ":" + aSeconds.getText();

		String[] travelTimeArray = { tDays.getText(), tHours.getText(), tMinutes.getText(), tSeconds.getText() };

		Date attackDate = MathMachine.getAttackDate(arrivalDateString, travelTimeArray);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");

		String attackDateString = format.format(attackDate);

		resultLabel.setText(attackDateString);
	}

}
