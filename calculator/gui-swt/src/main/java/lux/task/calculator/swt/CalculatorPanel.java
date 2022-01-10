package lux.task.calculator.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import lux.tasks.calculator.core.CalculationException;
import lux.tasks.calculator.core.ICalculator;
import lux.tasks.calculator.core.Operation;

public class CalculatorPanel extends Composite implements ICalculatorListener{
	private List<Operation> operations;
	private Text num1Field;
	private Combo operationWithNums;
	private Text num2Field;
	private Button flyCalculator;
	private Button calculate;
	private Label textResult;
	private Text resultField;
	private ICalculator core;
	private SelectionListener selectionListener = new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
        	calculate();
        }
    };
    private VerifyListener verifyListener = new VerifyListener() {
		public void verifyText(VerifyEvent e) {

			  boolean doit = true;

			  if (!(Character.isDigit(e.character) || e.character == SWT.DEL || e.character == SWT.BS 
					  || e.character == '.')) {
			    doit = false;
			  	}

			  if (!doit) {
			    try {
			    	Double.parseDouble(e.text);
			    	doit = true;
			    	} catch (NumberFormatException ex) {
			    	doit = false;
			    	}
			  	}

			  e.doit = doit;
			  if (!e.doit) {
			    Display.getCurrent().beep();
			  }
			}
    	};
    	
    private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			if(flyCalculator.getSelection()) {
                calculate();
			} else {
				calculate.setEnabled(isValid(num1Field) && isValid(num2Field));
			}
	    }
	};
	
	public CalculatorPanel (Composite parent, int style, ICalculator calculator) {
		super(parent, style);

		core = calculator;
		operations = calculator.getSupportedOperations();				

        String[] actions = new String[operations.size()];      
        for(int i = 0; i < actions.length; i++) {
        	actions [i] = operations.get(i).getName();
        }

		this.setLayout(new GridLayout(3, false));
    	this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        num1Field = new Text(this, SWT.SINGLE | SWT.BORDER);
		num1Field.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		operationWithNums = new Combo(this, SWT.READ_ONLY);
		operationWithNums.setItems(actions);
		operationWithNums.select(0);
		operationWithNums.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		num2Field = new Text(this, SWT.SINGLE | SWT.BORDER);
		num2Field.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		flyCalculator = new Button(this,SWT.CHECK);
		flyCalculator.setText("Calculate on the fly");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	    gridData.horizontalSpan = 2;
	    flyCalculator.setLayoutData(gridData);
	    
		calculate = new Button(this, SWT.NONE);
		calculate.setText("Calculate");
		calculate.setEnabled(false);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
	    calculate.setLayoutData(gridData);

		textResult = new Label(this, SWT.RIGHT);
		textResult.setText("Result: ");
		textResult.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		resultField = new Text(this, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	    gridData.horizontalSpan = 2;
	    resultField.setLayoutData(gridData);
		
		calculate.addSelectionListener(selectionListener);
		
		flyCalculator.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	if(flyCalculator.getSelection()) {
	                calculate.setEnabled(false);
	                operationWithNums.addSelectionListener(selectionListener);
	                calculate();
	            } else {
	            	calculate.setEnabled(isValid(num1Field) && isValid(num2Field));
	                operationWithNums.removeSelectionListener(selectionListener);
	            }
	        }
        });
		
		num1Field.addModifyListener(modifyListener);
		num2Field.addModifyListener(modifyListener);
				
		num1Field.addVerifyListener(verifyListener);
		num2Field.addVerifyListener(verifyListener);
		
	}
	
    private void calculate(){
    	try {
    		double nums [] = {Double.parseDouble(num1Field.getText()), 
    				Double.parseDouble(num2Field.getText())};
    		core.executeOperation(operations.get(operationWithNums.getSelectionIndex()), nums);
    	} catch (NumberFormatException e) {
    		resultField.setText("Invalid input");
    	} catch(CalculationException e) {
    		resultField.setText(e.getMessage());
    	}
    }
    
    public boolean isValid(Text text) {    	
    	return !(text.getText().trim().isEmpty());
    }

	@Override
	public void operationExecuted(Operation operation, double[] arguments, double result) {
		resultField.setText("" + result);
		
	}  	 
}
