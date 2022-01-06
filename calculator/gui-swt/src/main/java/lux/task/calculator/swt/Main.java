package lux.task.calculator.swt;

public class Main {
	
	
	public static void main (String[] args){
		
		try {
			CalculatorWindowSWT window = new CalculatorWindowSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
