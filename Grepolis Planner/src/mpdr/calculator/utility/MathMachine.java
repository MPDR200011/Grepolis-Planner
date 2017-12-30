package mpdr.calculator.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MathMachine {

	public static Date getAttackDate(String aDate, String[] tTime) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		Date arrivalDate;
		Date attackDate = null;

		int travelDays = tTime[0].equals("") ? 0 : Integer.parseInt(tTime[0]);
		int travelHours = tTime[1].equals("") ? 0 : Integer.parseInt(tTime[1]);
		int travelMins = tTime[2].equals("") ? 0 : Integer.parseInt(tTime[2]);
		int travelSec = tTime[3].equals("") ? 0 : Integer.parseInt(tTime[3]);

		int travelTimeUnix = (travelSec + travelMins * 60 + travelHours * 60 * 60 + travelDays * 24 * 60 * 60);

		try {
			arrivalDate = format.parse(aDate);
			attackDate = new Date((((arrivalDate.getTime() / 1000) - travelTimeUnix) * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return attackDate;
	}

	public static boolean isPositiveInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}

		if (str.charAt(0) == '-') {
			return false;
		}
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
}
