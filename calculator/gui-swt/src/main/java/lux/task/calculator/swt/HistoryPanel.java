package lux.task.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class HistoryPanel extends Composite{
	private Button save;
	private Button clean;
	private Table table;
	private SelectionListener selectionListener = new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
        	table.removeAll();;
        }
    };
	
	public HistoryPanel (Composite parent, int style) {
		super (parent, style);
		
		FillLayout fillLayout= new FillLayout(SWT.VERTICAL);
        fillLayout.marginHeight= 40;
        fillLayout.marginWidth= 40;
        fillLayout.spacing=20;
		
        this.setLayout(fillLayout);
        
        Composite compositeTable = new Composite(this,SWT.NONE);
        compositeTable.setLayout(new GridLayout());
        table = new Table (compositeTable, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    	table.setLinesVisible (true);
    	table.setHeaderVisible (true);
    	GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    	data.heightHint = 200;
    	table.setLayoutData(data);
    	String[] titles = {"First number", "Type of operation", "Second number", "Result"};
    	for (String title : titles) {
    		TableColumn column = new TableColumn (table, SWT.NONE);
    		column.setText (title);
    		column.setWidth(200);
    	}
        
        Composite compositeButtons = new Composite(this, SWT.NONE);
        compositeButtons.setLayout(new FillLayout());
        save = new Button(compositeButtons, SWT.NONE);
        save.setText("Save");
        clean = new Button(compositeButtons, SWT.NONE);
        clean.setText("Clean");
        
        clean.addSelectionListener(selectionListener);
	}
	
	public void addItem(String firstNum, String operation, String secondNum, String result) {
		TableItem item = new TableItem (table, SWT.NONE);
		item.setText(0, firstNum);
		item.setText(1, operation);
		item.setText(2, secondNum);
		item.setText(3, result);
	}

}
