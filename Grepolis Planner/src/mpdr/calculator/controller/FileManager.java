package mpdr.calculator.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mpdr.calculator.window.main.table.Plan;
import java.io.*;
import java.util.ArrayList;

class FileManager {

	static void save(ObservableList<String> nameList, ObservableMap<String, Plan> planMap, ArrayList<String> delete) {
		File saveDirectory = new File(System.getProperty("user.dir"), "files");

		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		for (String file : delete) {
			File forDeletion = new File(saveDirectory, file + ".pln");
			forDeletion.delete();
		}

		for (String name : nameList) {
			File saveFile = new File(saveDirectory, name + ".pln");
			try {
				FileOutputStream fout = new FileOutputStream(saveFile);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				planMap.get(name).writeExternal(oout);
				oout.close();
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static void load() {
		File saveDirectory = new File(System.getProperty("user.dir"), "files");
		if (saveDirectory.exists()) {
			File[] files = saveDirectory.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".pln");
				}
			});

			if (files != null) {
				for (File file : files) {
					try {
						FileInputStream fin = new FileInputStream(file);
						ObjectInputStream oin = new ObjectInputStream(fin);
						Handler.loadPlan(oin, file);
						oin.close();
						fin.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
