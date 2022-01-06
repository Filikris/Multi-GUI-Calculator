package lux.task.calculator.swt;

import org.eclipse.swt.widgets.Display;

import lux.tasks.calculator.core.CalculatorCore;
import lux.tasks.calculator.core.ICalculator;

public class Main {
	
	
	public static void main (String[] args){
		
		try {
			ICalculator calculator = CalculatorCore.getInstance();
			Display display = Display.getDefault();
			CalculatorWindowSWT window = new CalculatorWindowSWT(display, calculator);
			window.open();
			while (!window.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			display.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
