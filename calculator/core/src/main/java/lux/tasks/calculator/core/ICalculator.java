package lux.tasks.calculator.core;

import java.util.List;

public interface ICalculator {

	List<Operation> getSupportedOperations();

	double executeOperation(Operation operation, double[] arguments) throws CalculationException;

}