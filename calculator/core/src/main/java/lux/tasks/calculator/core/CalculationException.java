package lux.tasks.calculator.core;

public class CalculationException extends Exception {
	private static final long serialVersionUID = 1L;

	public CalculationException(String message) {
		super(message);
	}

	public CalculationException(Throwable cause) {
		super(cause);
	}
}
