package mpdr.calculator.window.main.table;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import mpdr.calculator.utility.MathMachine;

public class AttackingCity implements Externalizable {
	private SimpleStringProperty name;
	private SimpleStringProperty destinationName;
	private SimpleStringProperty departureDate;
	private SimpleStringProperty arrivalDate;

	AttackingCity(ObjectInput in) {
		try {
			this.readExternal(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	AttackingCity(String name, String destinationName, String arrivalDate, String[] travelTime) {
		this.name = new SimpleStringProperty(name);
		this.destinationName = new SimpleStringProperty(destinationName);
		this.arrivalDate = new SimpleStringProperty(arrivalDate);

		Date dDate = MathMachine.getAttackDate(arrivalDate, travelTime);

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		String departureDateString = format.format(dDate);

		this.departureDate = new SimpleStringProperty(departureDateString);
	}

	public String getName() {
		return name.get();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(this.name.get());
		out.writeUTF(this.destinationName.get());
		out.writeUTF(this.departureDate.get());
		out.writeUTF(this.arrivalDate.get());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = new SimpleStringProperty(in.readUTF());
		this.destinationName = new SimpleStringProperty(in.readUTF());
		this.departureDate = new SimpleStringProperty(in.readUTF());
		this.arrivalDate = new SimpleStringProperty(in.readUTF());
	}

	public String getDestinationName() {
		return destinationName.get();
	}

	public String getDepartureDate() {
		return departureDate.get();
	}

	public String getArrivalDate() {
		return arrivalDate.get();
	}
}
