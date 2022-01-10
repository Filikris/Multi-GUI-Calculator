package lux.task.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import lux.tasks.calculator.core.ICalculator;

public class CalculatorWindowSWT {
	private Shell shell;
	private ICalculator calculator;
	
	public CalculatorWindowSWT(Display display, ICalculator calculator) {
		shell = new Shell(display);
		this.calculator = calculator;
		createContents();
	}
	
	public void open() {
		shell.pack();
		shell.open();
	}
	
	public boolean isDisposed() {
		return shell.isDisposed();
	}
	
	private void createContents() {
		shell.setText("SWT Calculator");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));

		TabFolder panels = new TabFolder(shell, SWT.BORDER);

		CalculatorWraper calculatorWraper = new CalculatorWraper(calculator);		

		CalculatorPanel calculatorPanel = new CalculatorPanel(panels, SWT.NONE, calculatorWraper);
		TabItem tabCalculator = new TabItem(panels, SWT.NONE);	
		tabCalculator.setText("Calculator");
		tabCalculator.setControl(calculatorPanel);
		calculatorWraper.addListener(calculatorPanel);

		HistoryPanel historyPanel = new HistoryPanel(panels, SWT.NONE);
		TabItem tabHistory = new TabItem(panels, SWT.NONE);
		tabHistory.setText("History");   
		tabHistory.setControl(historyPanel);
		calculatorWraper.addListener(historyPanel);
		
	}
}

