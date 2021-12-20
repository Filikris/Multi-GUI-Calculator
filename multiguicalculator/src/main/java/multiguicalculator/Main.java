package multiguicalculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class Main {
	private static final String[] actions = new String[]{"+", "-", "/", "*"};
	
	public static void main (String[] args){
			
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT Calculator");
		TabFolder panels = new TabFolder(shell, SWT.NONE);
		TabItem panelCalculator = new TabItem(panels, SWT.NONE);
		panelCalculator.setText("Calculator");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3; 
		Text num1Field = new Text(panels, SWT.BORDER);
		Combo operationWithNums = new Combo(panels, SWT.READ_ONLY);
		operationWithNums.setItems(actions);
		Text num2Field = new Text(panels, SWT.BORDER);
		Button flyCalculator = new Button(panels,SWT.CHECK);
		flyCalculator.setText("Calculate on the fly");
		Button calculate = new Button(panels, SWT.NONE);
		calculate.setText("Calculate");
		Label textResult = new Label(panels, SWT.NONE);
		textResult.setText("Result: ");
		Text resultField = new Text(panels, SWT.BORDER);
		panels.setLayout(gridLayout);
		
		TabItem panelHistory = new TabItem(panels, SWT.NONE);
		panelHistory.setText("History");
		
		
		shell.setLayout(gridLayout);        
		shell.pack();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
			display.sleep();
			}
		}
		display.dispose();
	}
}
