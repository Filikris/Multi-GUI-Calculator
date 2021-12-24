package multiguicalculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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
	
	public TabCalculator (TabFolder folder) {
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
		
		tabCalculator.setControl(compositePanelCalculator);
	}

}
