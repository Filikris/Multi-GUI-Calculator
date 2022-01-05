package lux.task.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class CalculatorWindowSWT {
	private Shell shell;
	
	public CalculatorWindowSWT() {}
	
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		createContents();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	private void createContents() {
		shell.setText("SWT Calculator");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));
		
		TabFolder panels = new TabFolder(shell, SWT.BORDER);
		
		HistoryPanel historyPanel = new HistoryPanel(panels,SWT.NONE);
		TabItem tabCalculator = new TabItem(panels, SWT.NONE);	
		tabCalculator.setText("Calculator");
		tabCalculator.setControl(new CalculatorPanel(panels, SWT.NONE, historyPanel));
		
		TabItem tabHistory = new TabItem(panels, SWT.NONE);
		tabHistory.setText("History");   
		tabHistory.setControl(historyPanel);
	}

}
