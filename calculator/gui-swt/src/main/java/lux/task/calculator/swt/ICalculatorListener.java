package lux.task.calculator.swt;

import lux.tasks.calculator.core.Operation;

interface ICalculatorListener{
	void operationExecuted(Operation operation, double[] arguments, double result);
}