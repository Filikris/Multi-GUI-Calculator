package com.task.calculator.swt;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class TabCalculator {
	private static final String[] actions = new String[]{"+", "-", "/", "*"};
	private Calculator calculator = new Calculator();
	private Text num1Field;
	private Combo operationWithNums;
	private Text num2Field;
	private Button flyCalculator;
	private Button calculate;
	private Label textResult;
	private Text resultField;
	private TabItem tabCalculator;
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

	
	public TabCalculator (final TabFolder folder) {
		tabCalculator = new TabItem(folder, SWT.BORDER);
		tabCalculator.setText("Calculator");
		FillLayout fillLayout= new FillLayout(SWT.VERTICAL);
        fillLayout.marginHeight= 40;
        fillLayout.marginWidth= 40;
        fillLayout.spacing=20;
		
		Composite compositePanelCalculator = new Composite(folder, SWT.BORDER);
        compositePanelCalculator.setLayout(fillLayout);
	
		Composite compositeLineWithNums = new Composite(compositePanelCalculator, SWT.NONE); 
        compositeLineWithNums.setLayout(new FillLayout());
		num1Field = new Text(compositeLineWithNums, SWT.BORDER);
		operationWithNums = new Combo(compositeLineWithNums, SWT.READ_ONLY);
		operationWithNums.setItems(actions);
		operationWithNums.select(0);
		num2Field = new Text(compositeLineWithNums, SWT.BORDER);
		
		Composite compositeOptionsCalculate = new Composite(compositePanelCalculator, SWT.NONE);
        compositeOptionsCalculate.setLayout(new FillLayout());
		flyCalculator = new Button(compositeOptionsCalculate,SWT.CHECK);
		flyCalculator.setText("Calculate on the fly");
		calculate = new Button(compositeOptionsCalculate, SWT.NONE);
		calculate.setText("Calculate");
		
		Composite compositeResult = new Composite(compositePanelCalculator, SWT.NONE);
        compositeResult.setLayout(new FillLayout());
		textResult = new Label(compositeResult, SWT.NONE);
		textResult.setText("Result: ");
		resultField = new Text(compositeResult, SWT.BORDER);
		
		tabCalculator.setControl(compositePanelCalculator);
		
		calculate.addSelectionListener(selectionListener);
		
		flyCalculator.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	if(flyCalculator.getSelection()) {
	                calculate.setEnabled(false);
	                operationWithNums.addSelectionListener(selectionListener);
	                calculate();
	            } else {
	                calculate.setEnabled(true);
	                operationWithNums.removeSelectionListener(selectionListener);
	            }
	        }
        });
		
		num1Field.addVerifyListener(verifyListener);
		num2Field.addVerifyListener(verifyListener);
	}
	
    private void calculate(){
        double result = calculator.operation(Double.parseDouble(num1Field.getText()),
                actions[operationWithNums.getSelectionIndex()], Double.parseDouble(num2Field.getText()));
        resultField.setText("" + result);
    }
    
    

}
