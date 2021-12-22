package multiguicalculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
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
		shell.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL)); 
		
		TabFolder panels = new TabFolder(shell, SWT.BORDER);
		TabItem panelCalculator = new TabItem(panels, SWT.BORDER);
		panelCalculator.setText("Calculator");
		
		FillLayout fillLayout= new FillLayout(SWT.VERTICAL);
        fillLayout.marginHeight= 40;
        fillLayout.marginWidth= 40;
        fillLayout.spacing=20;
		
		Composite compositePanelCalculator = new Composite(panels, SWT.BORDER);
        compositePanelCalculator.setLayout(fillLayout);
		
		Composite compositeLineWithNums = new Composite(compositePanelCalculator, SWT.NONE); 
        compositeLineWithNums.setLayout(new FillLayout());
		Text num1Field = new Text(compositeLineWithNums, SWT.BORDER);
		Combo operationWithNums = new Combo(compositeLineWithNums, SWT.READ_ONLY);
		operationWithNums.setItems(actions);
		Text num2Field = new Text(compositeLineWithNums, SWT.BORDER);
		
		Composite compositeOptionsCalculate = new Composite(compositePanelCalculator, SWT.NONE);
        compositeOptionsCalculate.setLayout(new FillLayout());
		Button flyCalculator = new Button(compositeOptionsCalculate,SWT.CHECK);
		flyCalculator.setText("Calculate on the fly");
		Button calculate = new Button(compositeOptionsCalculate, SWT.NONE);
		calculate.setText("Calculate");
		
		Composite compositeResult = new Composite(compositePanelCalculator, SWT.NONE);
        compositeResult.setLayout(new FillLayout());
		Label textResult = new Label(compositeResult, SWT.NONE);
		textResult.setText("Result: ");
		Text resultField = new Text(compositeResult, SWT.BORDER);
		
		panelCalculator.setControl(compositePanelCalculator);
			
		TabItem panelHistory = new TabItem(panels, SWT.NONE);
		panelHistory.setText("History");       

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
			display.sleep();
			}
		}
		display.dispose();
	}
}
