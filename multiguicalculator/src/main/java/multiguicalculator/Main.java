package multiguicalculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;


public class Main {
	
	
	public static void main (String[] args){
			
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT Calculator");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL)); 
		
		TabFolder panels = new TabFolder(shell, SWT.BORDER);
		TabCalculator tabCalculator = new TabCalculator(panels);
		
		
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
