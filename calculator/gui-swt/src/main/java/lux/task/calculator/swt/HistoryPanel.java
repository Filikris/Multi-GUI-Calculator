package lux.task.calculator.swt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class HistoryPanel extends Composite{
	private Table table;
	private static String[] titles = {"First number", "Type of operation", "Second number", "Result"};
    
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

    	for (String title : titles) {
    		TableColumn column = new TableColumn (table, SWT.NONE);
    		column.setText (title);
    		column.setWidth(200);
    	}
        
        Composite compositeButtons = new Composite(this, SWT.NONE);
        compositeButtons.setLayout(new FillLayout());
        Button save = new Button(compositeButtons, SWT.NONE);
        save.setText("Save");
        Button clear = new Button(compositeButtons, SWT.NONE);
        clear.setText("Clear");
        
        clear.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	table.removeAll();
            }
        });
        save.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	buttonSavePress();
            }
        });
	}
	
	public void addItem(String firstNum, String operation, String secondNum, String result) {
		TableItem item = new TableItem (table, SWT.NONE);
		item.setText(0, firstNum);
		item.setText(1, operation);
		item.setText(2, secondNum);
		item.setText(3, result);
	}
	
	private void buttonSavePress() {
		FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);		
		dialog.setFilterNames (new String [] {"CSV Files", "All Files (*.*)"});
		dialog.setFilterExtensions (new String [] {"*.csv", "*.*"}); 
		String filePath = dialog.open();
		if(filePath == null) {
			return;
		}
		
		File fileToSave = new File(filePath);
		System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		
		try (FileWriter fw = new FileWriter(fileToSave);
                BufferedWriter bw = new BufferedWriter(fw)) {
			
			for (String title : titles) {
				bw.write(title);
				bw.write("\t");
			}
			bw.newLine();
		
			for (TableItem item : table.getItems()) {
				bw.write(item.getText(0));
				bw.write("\t");
				bw.write(item.getText(1));
				bw.write("\t");
				bw.write(item.getText(2));
				bw.write("\t");
				bw.write(item.getText(3));
				bw.write("\t");
				bw.newLine();
			}
		} catch (IOException ex) {
			MessageBox msg = new MessageBox(this.getShell(),SWT.ICON_ERROR);
			msg.setMessage("Error");
			msg.open();
		}
	}

}
