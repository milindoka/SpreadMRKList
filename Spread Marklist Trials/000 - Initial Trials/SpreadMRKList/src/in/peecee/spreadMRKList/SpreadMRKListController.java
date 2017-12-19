package in.peecee.spreadMRKList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SpreadMRKListController {

	private SpreadMRKListModel SpreadMRKListModel;
	private SpreadMRKListView SpreadMRKListview;

	public SpreadMRKListController(SpreadMRKListModel model, SpreadMRKListView view){

	        this.SpreadMRKListModel = model;
	        this.SpreadMRKListview = view;   
		    System.out.println(model.getJarPath());       ///set JAR path in model variable path;

	    }

	public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void Show(int msg) {JOptionPane.showMessageDialog(null, msg);}

    public void SetData(Object obj, int row_index, int col_index){SpreadMRKListview.getTable().getModel().setValueAt(obj,row_index,col_index);  }
	private ActionListener saveListener, canselListener, loadListener, searchListener, setprinterListener, printListener;
	
	public void contol(){
		
		int n = 50;
		ResizeTable(SpreadMRKListview.getTable(),n);

		for(int i = 0; i < n ; i++){
			{
				String SrNo=String.format("%d",i+1);
		        SetData(SrNo,i,0);
		    }
		}

	    	canselListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	                BtnCancel();
	            }
	        }; 
	        saveListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSave();
	            }
	        }; 
	    	
	        loadListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnLoad();
	            }
	        };
	        
	        searchListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSearch();
	            }
	        };
	        
	        setprinterListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSetPrinter();
	            }
	        };
	        
	        printListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnPrint();
	            }
	        }; 

	    	SpreadMRKListview.getSaveButton().addActionListener(saveListener);
	    	SpreadMRKListview.getLoadButton().addActionListener(loadListener);
	    	SpreadMRKListview.getSearchButton().addActionListener(searchListener);
	    	SpreadMRKListview.getSetPrinterButton().addActionListener(setprinterListener);
	    	SpreadMRKListview.getPrintButton().addActionListener(printListener);
	    	SpreadMRKListview.getCanselButton().addActionListener(canselListener);

	 }	
	
	    private void BtnSave(){
	        System.exit(0);
	    }

	    private void BtnLoad(){
	        System.exit(0);
	    }

	    private void BtnSearch(){
	        System.exit(0);
	    }

	    private void BtnPrint(){
	        System.exit(0);
	    }

	    private void BtnSetPrinter(){
	        System.exit(0);

	    	SetPrinter sp=new SetPrinter();
	        String printername=sp.SelectPrinter();
//	        model.setPrinterName(printername);
//	        SpreadMRKListView.setPrinterLabel(printername);

	    }

	    private void BtnCancel(){
	        System.exit(0);
	    }

	    public void ResizeTable(JTable tablename,int numberofrows)
		   { DefaultTableModel model=(DefaultTableModel) tablename.getModel();
	      int totalrows=tablename.getRowCount();
	      int difference=numberofrows-totalrows;
	      if(difference>0)
	       {
	          for(int i=0;i<difference;i++) model.addRow(new Object[]{" "});
	       }  
	 if(difference<0)
		   { difference=-difference;
	      for(int i=0;i<difference;i++) model.removeRow(0);	      
		   }
		 }
}
