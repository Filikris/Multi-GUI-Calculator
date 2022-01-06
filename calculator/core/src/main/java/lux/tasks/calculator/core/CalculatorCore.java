package lux.tasks.calculator.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class CalculatorCore {

	private static final CalculatorCore INSTANCE = new CalculatorCore();

	public static CalculatorCore getInstance() {
		return INSTANCE;
	}

	private CalculatorCore() {}

	public List<Operation> getSupportedOperations() {
		return Arrays.asList( OPERATIONS );
	}

	public double executeOperation(Operation operation, double[] arguments) throws CalculationException {
		Objects.requireNonNull( operation );
		Objects.requireNonNull( arguments );
		if ( !(operation instanceof OperationDescriptor) ) {
			throw new CalculationException( "Unsupported operation: " + operation );
		}
		if ( arguments.length != operation.getArgumentsCount() ) {
			throw new IllegalArgumentException( "Wrong arguments count" );
		}
		return ((OperationDescriptor)operation).execute( arguments );
	}

	//===== Implemenation stuff
	
	private static final String[] NAMES = { "+", "-", "*", "/", "%" };
	private static final String[] DESCRIPTIONS = 
		{ "Adds together two values",
		  "Subtracts one value from another",
		  "Multiplies two values",
		  "Divides one value by another",
		  "Returns the division remainder" };
	private static final int[] ARG_COUNTS = { 2, 2, 2, 2, 2 };
	private static final OperationExecutor[] EXECUTORS = 
		{ CalculatorCore::addition,
		  CalculatorCore::subtraction,
		  CalculatorCore::multiplication,
		  CalculatorCore::division,
		  CalculatorCore::modulus }; 

	private static final Operation[] OPERATIONS = {
			new OperationDescriptor( 0 ),
			new OperationDescriptor( 1 ),
			new OperationDescriptor( 2 ),
			new OperationDescriptor( 3 ),
			new OperationDescriptor( 4 )
	};

	private static double addition( double[] arguments ) {
		return arguments[0] + arguments[1];
	}

	private static double subtraction( double[] arguments ) {
		return arguments[0] - arguments[1];
	}

	private static double multiplication( double[] arguments ) {
		return arguments[0] * arguments[1];
	}

	private static double division( double[] arguments ) {
		return arguments[0] / arguments[1];
	}

	private static double modulus( double[] arguments ) {
		return arguments[0] % arguments[1];
	}

	private static class OperationDescriptor implements Operation, OperationExecutor {
		private final int _index;

		public OperationDescriptor( int index ) {
			this._index = index;
		}

		public String getName() {
			return NAMES[ _index ];
		}

		public String getDescription() {
			return DESCRIPTIONS[ _index ];
		}

		public int getArgumentsCount() {
			return ARG_COUNTS[ _index ];
		}

		public double execute( double[] arguments ) throws CalculationException {
			return EXECUTORS[ _index ].execute( arguments );
		}
	}

	@FunctionalInterface
	interface OperationExecutor {
		double execute( double[] arguments ) throws CalculationException; 
	}
}
