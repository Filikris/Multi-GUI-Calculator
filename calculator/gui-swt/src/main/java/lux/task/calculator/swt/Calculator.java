package lux.task.calculator.swt;

public class Calculator {
	public double operation(double num1, String operation, double num2) {
        double result = 0;

        if (operation.equals("+"))
            result = num1 + num2;
        else if (operation.equals("-"))
            result = num1 - num2;
        else if (operation.equals("*"))
            result = num1 * num2;
        else if (operation.equals("/"))
            result = num1 / num2;

        return result;
    }
}
