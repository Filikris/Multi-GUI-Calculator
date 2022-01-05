package lux.task.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import lux.tasks.calculator.core.CalculationException;
import lux.tasks.calculator.core.CalculatorCore;
import lux.tasks.calculator.core.Operation;

public class CalculatorPanel extends Composite {
	private static Operation [] operations = CalculatorCore.getInstance().getSupportedOperations();
	private Text num1Field;
	private Combo operationWithNums;
	private Text num2Field;
	private Button flyCalculator;
	private Button calculate;
	private Label textResult;
	private Text resultField;
	private HistoryPanel historyPanel;
	private CalculatorCore core = CalculatorCore.getInstance();
	private SelectionListener selectionListener = new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
        	calculate();
        }
    };
    private VerifyListener verifyListener = new VerifyListener() {
		public void verifyText(VerifyEvent e) {

			  boolean doit = true;

			  if (!(Character.isDigit(e.character) || e.character == SWT.DEL || e.character == SWT.BS)) {
			    doit = false;
			  	}

			  if (!doit) {
			    try {
			    	Integer.parseInt(e.text);
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
	
	public CalculatorPanel (Composite parent, int style, HistoryPanel historyPanel) {
		super(parent, style);
		this.historyPanel = historyPanel;
		
		FillLayout fillLayout= new FillLayout(SWT.VERTICAL);
        fillLayout.marginHeight= 40;
        fillLayout.marginWidth= 40;
        fillLayout.spacing=20;
		
        this.setLayout(fillLayout);
        
        String[] actions = new String[operations.length];      
        for(int i = 0; i < actions.length; i++) {
        	actions [i] = operations[i].getName();
        }
	
		Composite compositeLineWithNums = new Composite(this, SWT.NONE); 
        compositeLineWithNums.setLayout(new FillLayout());
		num1Field = new Text(compositeLineWithNums, SWT.BORDER);
		operationWithNums = new Combo(compositeLineWithNums, SWT.READ_ONLY);
		operationWithNums.setItems(actions);
		operationWithNums.select(0);
		num2Field = new Text(compositeLineWithNums, SWT.BORDER);
		
		Composite compositeOptionsCalculate = new Composite(this, SWT.NONE);
        compositeOptionsCalculate.setLayout(new FillLayout());
		flyCalculator = new Button(compositeOptionsCalculate,SWT.CHECK);
		flyCalculator.setText("Calculate on the fly");
		calculate = new Button(compositeOptionsCalculate, SWT.NONE);
		calculate.setText("Calculate");
		calculate.setEnabled(false);
		
		Composite compositeResult = new Composite(this, SWT.NONE);
        compositeResult.setLayout(new FillLayout());
		textResult = new Label(compositeResult, SWT.NONE);
		textResult.setText("Result: ");
		resultField = new Text(compositeResult, SWT.BORDER);
		
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
    		double result = core.executeOperation(operations[operationWithNums.getSelectionIndex()], nums);
    		resultField.setText("" + result);
    		
    		historyPanel.addItem(num1Field.getText(), operationWithNums.getText(),
    				num2Field.getText(), resultField.getText());
    		
    	} catch (NumberFormatException e) {
    		resultField.setText("Invalid input");
    	} catch(CalculationException e) {
    		resultField.setText(e.getMessage());
    	}
    }
    
    public boolean isValid(Text text) {    	
    	return !(text.getText().trim().isEmpty());
    }  	 
}
