package mpdr.calculator.window.main.table;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Plan implements Externalizable {

    private String name;
    private ObservableList<AttackingCity> attackingCitiesList = FXCollections.observableArrayList();
    private HashMap<String, String> targetMap = new HashMap<>();

    public Plan(ObjectInput in) {
	try {
	    this.readExternal(in);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Plan(String name, HashMap<String, String> targetMap) {
	this.name = name;
	this.targetMap = targetMap;
    }

    public String getName() {
	return this.name;
    }

    public ObservableList<AttackingCity> getAttackingCitiesList() {
	return this.attackingCitiesList;
    }

    public void setAttackingCitiesList(ObservableList<AttackingCity> attackingCitiesList) {
	this.attackingCitiesList = attackingCitiesList;
    }

    public HashMap<String, String> getTargetMap() {
	return this.targetMap;
    }

    public void setTargetMap(HashMap<String, String> map) {
	this.targetMap = map;
    }

    public boolean addAttacker(String attackerName, String destinationName, String arrivalDate, String[] travelTime) {
	for (AttackingCity city : attackingCitiesList) {
	    if (city.getName().equals(attackerName))
		return false;
	}
	this.attackingCitiesList.add(new AttackingCity(attackerName, destinationName, arrivalDate, travelTime));
	return true;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	out.writeUTF(this.name);
	out.writeInt(attackingCitiesList.size());
	for (AttackingCity city : this.attackingCitiesList) {
	    city.writeExternal(out);
	}
	out.writeObject(this.targetMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	this.name = in.readUTF();
	int i = in.readInt();
	for (int j = 0; j < i; j++) {
	    this.attackingCitiesList.add(new AttackingCity(in));
	}
	this.targetMap = (HashMap<String, String>) in.readObject();
    }
}
