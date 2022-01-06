package lux.task.calculator.swt;

import java.util.ArrayList;
import java.util.List;

import lux.tasks.calculator.core.CalculationException;
import lux.tasks.calculator.core.ICalculator;
import lux.tasks.calculator.core.Operation;

class CalculatorWraper implements ICalculator{
		private ICalculator calculator;
		private List <ICalculatorListener> listeners;
		
		public CalculatorWraper(ICalculator calculator) {
			this.calculator = calculator;
		}

		@Override
		public List<Operation> getSupportedOperations() {
			return calculator.getSupportedOperations();
		}

		@Override
		public double executeOperation(Operation operation, double[] arguments) throws CalculationException {
			double result = calculator.executeOperation(operation, arguments);
			if(listeners != null) {
				listeners.forEach(l -> l.operationExecuted(operation, arguments, result));
			}
			return result;
		}
		
		public void addListener(ICalculatorListener l) {
			if(listeners == null) {
				listeners = new ArrayList<>();
			}
			listeners.add(l);
		}
		
		public void removeListener(ICalculatorListener l) {
			if(listeners != null) {
				listeners.remove(l);
			}
		}
		
	}