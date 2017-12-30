package mpdr.calculator.controller;

import mpdr.calculator.window.main.table.Plan;

import java.io.File;
import java.io.ObjectInput;

public class Handler {
	public static void save() {
		FileManager.save(MainController.planNameList, MainController.planMap, MainController.markedForDeletion);
	}

	public static void load() {
		FileManager.load();
	}

	static void loadPlan(ObjectInput in, File file) {
		String fileName = file.getName();
		String name = fileName.substring(0, fileName.length() - 4);
		MainController.planNameList.add(name);
		MainController.planMap.put(name, new Plan(in));
	}
}
