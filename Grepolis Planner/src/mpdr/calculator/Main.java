package mpdr.calculator;

import mpdr.calculator.controller.Handler;
import mpdr.calculator.window.main.MainWindow;

public class Main {
    public static void main(String[] args) {
	Handler.load();
	MainWindow.initialize(args);
    }
}
