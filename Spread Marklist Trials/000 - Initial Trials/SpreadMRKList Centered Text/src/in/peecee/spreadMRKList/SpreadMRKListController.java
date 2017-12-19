package in.peecee.spreadMRKList;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SpreadMRKListController {

	private SpreadMRKListModel SpreadMRKListModel;
	private SpreadMRKListView SpreadMRKListview;
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	
	public void show(float percent) {JOptionPane.showMessageDialog(null, percent);}   ///for debugging
	public void show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(ArrayList<String> arrayList) {JOptionPane.showMessageDialog(null, arrayList);}   ///for debugging	
	
	public SpreadMRKListController(SpreadMRKListModel model, SpreadMRKListView view){

	        this.SpreadMRKListModel = model;
	        this.SpreadMRKListview = view;   
		    System.out.println(model.getJarPath());       ///set JAR path in model variable path;
	    }
	
    String subject[] = { "ENG", "HIN", "MAR", "TAM", "PHY", "CHE", "BIO", "BKE", "ECO", "OCM", "MAT",
                      "SEP", "CS1", "CS2", "EL1", "EL2", "ITE", "EVS", "PTE" };
    
    String subjectExamType[] = {"U1=ENG","U2=ENG","T1=ENG","T2=ENG","U1=HIN","U2=HIN","T1=HIN","T2=HIN","U1=MAR","U2=MAR","T1=MAR","T2=MAR",
    		                    "U1=TAM","U2=TAM","T1=TAM","T2=TAM","U1=PHY","U2=PHY","T1=PHY","T2=PHY","U1=CHE","U2=CHE","T1=CHE","T2=CHE",
    		                    "U1=BIO","U2=BIO","T1=BIO","T2=BIO","U1=BKE","U2=BKE","T1=BKE","T2=BKE","U1=ECO","U2=ECO","T1=ECO","T2=ECO",
    		                    "U1=OCM","U2=OCM","T1=OCM","T2=OCM","U1=MAT","U2=MAT","T1=MAT","T2=MAT","U1=SEP","U2=SEP","T1=SEP","T2=SEP",
    		                    "U1=CS1","U2=CS1","T1=CS1","T2=CS1","U1=CS2","U2=CS2","T1=CS2","T2=CS2","U1=EL1","U2=EL1","T1=EL1","T2=EL1",
    		                    "U1=EL2","U2=EL2","T1=EL2","T2=EL2","U1=IT1","U2=IT1","T1=IT1","T2=IT1","EVS", "PTE" };
			  
    String subjectOPP[] = { "ENGORA", "HINORA", "MARORA", "TAMORA", "PHYPRA", "CHEPRA", "BIOPRA", "BKEPRO", "ECOPRO", "OCMPRO", "MATPRA",
             "SEPPRO", "CS1PRA", "CS2PRA", "EL1PRA", "EL2PRA", "ITEPRA", "EVSPRO", "PTEPRA"};					  
	

    public void SetData(Object obj, int row_index, int col_index){SpreadMRKListview.getTable().getModel().setValueAt(obj,row_index,col_index);  }
	
    private ActionListener saveListener, loadListener, processListener, searchListener,
	                       setprinterListener, printListener, canselListener ;
    
    int TotalMarklists=0;
	
	public void contol(){
		
		int n = 1500;
		ResizeTable(SpreadMRKListview.getTable(),n);

		for(int i = 0; i < n ; i++){
			{
				String SrNo=String.format("%d",i+1);
		        SetData(SrNo,i,0);
		    }
		}
		
		 SetPrinter sp=new SetPrinter();
	        String printername=sp.LoadPreferences();
	        SpreadMRKListModel.setPrinterName(printername);
	        SpreadMRKListview.setPrinterLabel(printername);

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
	        
	processListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnProcess();
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
	SpreadMRKListview.getProcessButton().addActionListener(processListener);
	SpreadMRKListview.getSearchButton().addActionListener(searchListener);
	SpreadMRKListview.getSetPrinterButton().addActionListener(setprinterListener);
	SpreadMRKListview.getPrintButton().addActionListener(printListener);
	SpreadMRKListview.getCanselButton().addActionListener(canselListener);

	 }	
	
	private void BtnSave(){
//	        System.exit(0);
			String fyle="";
			  JFileChooser choosertosave = new JFileChooser();
			  choosertosave.setMultiSelectionEnabled(true);
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("BatchCreater", "bch");
	          choosertosave.setFileFilter(filter);
	          choosertosave.setCurrentDirectory(new File("E:/Eclipse/Prahlad/Creat Batches"));
	          choosertosave.setCurrentDirectory(new File("/home/prahlad/Blank Entries"));
	          choosertosave.setCurrentDirectory(new File("/home/siws/Blank Entries"));   
	          int option = choosertosave.showSaveDialog(choosertosave);

	          if (option == JFileChooser.APPROVE_OPTION)
	           {
	              File[] sf = choosertosave.getSelectedFiles();
	              String filelist = "nothing";
	              if (sf.length > 0) filelist = sf[0].getName();
	              for (int i = 1; i < sf.length; i++) 
	                {
	                  filelist += ", " + sf[i].getName();
	                }
	              fyle=sf[0].getPath();             
	              if (!fyle.endsWith(".bch")) fyle+= ".bch";
//	              SaveToFile(fyle);                             //Save to File is called here
	           }  		
	        
	    }

	private void BtnLoad(){
//	        System.exit(0);	    	
	    	{  
				String fyle = "";			
				JFileChooser chooser = new JFileChooser();
		        chooser.setMultiSelectionEnabled(true);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Result View", "rlt");
		        chooser.setFileFilter(filter);
		        chooser.setCurrentDirectory(new File("E:/Eclipse/Prahlad/Test Entries"));
		        chooser.setCurrentDirectory(new File("/home/prahlad/Blank Entries"));
		        chooser.setCurrentDirectory(new File("/home/siws/Blank Entries"));   
		       
		        int option = chooser.showOpenDialog(SpreadMRKListview.getLoadButton());
		        
		        if (option == JFileChooser.APPROVE_OPTION)
		         {
		            File[] sf = chooser.getSelectedFiles();
		            String filelist = "nothing";
		            if (sf.length > 0) filelist = sf[0].getName();
		            for (int i = 1; i < sf.length; i++) 
		            {
		            filelist += ", " + sf[i].getName();
		             }
		          fyle=sf[0].getPath();
		         if (!fyle.endsWith(".rlt")) fyle+= ".rlt";			
		         ReadFromDisk(fyle);
	        }  
		  } 
	    }

	private void BtnProcess(){
//	    	System.exit(0);
	    	 boolean flagBKE = false, flagPHY = false;
	    	 String plate[];
	    	 String[] submarks = null;
	    	 String[] subwithmarks = null;
			 String rollno, names, div, Stream = null,  marks, temp1, Subject = null, line;		   	
			 int subjectMarks = 0, Total = 0;
			 			 			 			 
	    	 for(int i=1; i < strArray.size(); i++)                      //  strArray.size()
		     	{ plate=strArray.get(i).split("#");
		     	  rollno = plate[0].substring(0);
		     	  names = plate[1].substring(0, 60);
//		     	  str = str.replaceAll("\\s+"," ");                // REGEX replacing method - Removes all white spaces
		     	  names = names.replaceAll("\\s{2}", "");          // Keeps one white space and removes extra white spaces
		     	  div = plate[2].substring(0,1);		     	  
		     	   for (int j = 2; j < plate.length; j++){	
			     		    line = plate[j];
					    	submarks = line.split(":");         // To split the text from marks
					    	marks = submarks[1];                //  Fetching marks which is at index 1					    	
					    	if(marks.contentEquals("AB")){ marks = "00"; }
					    	subjectMarks =Integer.parseInt(marks);
					    	Total = Total + subjectMarks;

		     	   }       		
		     		SetData(rollno,i-1,1);
		     		SetData(div, i-1, 2);
		     		SetData(names,i-1,3);
	        }     	    	 

	    	 TotalScore();  
	         ENGMarks();
	    }
	    
	public void TotalScore(){
			 String plate[];
			 String rollno, name, div, Stream = null, temp1, Subject = null, SubjectOPP = null, marks, line;
			 String[] submarks = null;		   	
			 int subjectMarks = 0, Total = 0;
			 
			 for(int i=1; i < strArray.size(); i++)                      //  strArray.size()
		     	{
				   plate=strArray.get(i).split("#"); 
//				   show(plate);
				    for(int j = 2; j < plate.length; j++) 
				      { line = plate[j];
				    	submarks = line.split(":");
				    	marks = submarks[1];
				    	if(marks.contentEquals("AB")){ marks = "00"; }
				    	subjectMarks =Integer.parseInt(marks);
				    	Total = Total + subjectMarks;
//				    	show("Total Score = " + Total);			
				      }
				    SetData(Total,i-1,28);
				    Total = 0;
		     	}							
		}
	    	    
	private void BtnSearch(){
	        System.exit(0);
	    }

	private void BtnPrint(){
//	        System.exit(0);
//		    U1Marks();
		
		 boolean flagBKE = false, flagPHY = false;
    	 String[] subwithmarks = null;
    	 String plate[];
		 String rollno, names, div, Stream = null, temp1, submarksU1 = null, line;		   	
		 int subjectMarks = 0, Total = 0;
    	 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	  rollno = plate[0].substring(0);
	     	  div = plate[2].substring(0,1);
	     	  names = plate[1].substring(0, 60);
	     	   for (int j = 2; j < plate.length; j++)
	     		   
	     	   { line = plate[j];
	     		 submarksU1 = plate[j].substring(2, 8);   //     show( submarksU1); 
	     		 if(submarksU1.contains("U1=ENG")){
	     			subwithmarks = line.split(":");
	     			submarksU1 = subwithmarks[1];		     			 
	     			}	
	     	   }          	     	   
	     	   
	     	   SetData(subwithmarks[1], i-1,4);

        }		
		
 //    	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
 //   	 TableColumnModel cm = th.getColumnModel();
   // 	 TableColumn tc = cm.getColumn(2);
 //   	 tc.setHeaderValue( "ENG" );
    	 SpreadMRKListview.ENG.text = "ENG";
 //   	 th.repaint();                             
			
     }

	private void BtnSetPrinter(){
//	        System.exit(0);

			SetPrinter sp=new SetPrinter();
	        String printername=sp.SelectPrinter();
	        SpreadMRKListModel.setPrinterName(printername);
	        SpreadMRKListview.setPrinterLabel(printername);
	    }

	private void BtnCancel(){
	        System.exit(0);
	    }
	    
	public  void ReadFromDisk(String fnem)
	    {   strArray.removeAll(strArray);
	    	BufferedReader reader=null;
			try { 
				reader = new BufferedReader(new FileReader(fnem));
			} catch (FileNotFoundException e1) 
			{		
				e1.printStackTrace();
			}
	 				
			String line = null;
	    	try { while ((line = reader.readLine()) != null) 
				{			 
				 strArray.add(line);
				}
			} catch (IOException e) { e.printStackTrace(); }    	
	     }

	public void ENGMarks(){
		  
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
    	 String[] subwithmarks = null;
    	 String plate[];
		 String rollno, names, div, Stream = null, marks, temp1, submarksU1 = null,  submarksENG = null ,line;		   	
		 int subjectMarks = 0, Total = 0;
		 
    	 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	  rollno = plate[0].substring(0);
	     	  div = plate[2].substring(0,1);
	     	  names = plate[1].substring(0, 60);
	     	   for (int j = 2; j < plate.length; j++){	
	     		    line = plate[j];
			    	submarks = line.split(":");         // To split the text from marks
			    	marks = submarks[1];                //  Fetching marks which is at index 1					    	
//			    	if(marks.contentEquals("AB")){ marks = "00"; }
//			    	subjectMarks =Integer.parseInt(marks);
//			    	Total = Total + subjectMarks;
		     		 submarksENG = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(submarksENG.contains("U1=ENG")){
		     			subwithmarks = line.split(":");
		     			submarksENG = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1,4);
//		     			show( subwithmarks); 
		     			}	                         
		     		 
		     		if(submarksENG.contains("T1=ENG")){
		     			subwithmarks = line.split(":");
		     			submarksENG = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,5);
//		     			show( subwithmarks); 
		     			}	
		     		
		     		if(submarksENG.contains("U2=ENG")){
		     			subwithmarks = line.split(":");
		     			submarksENG = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,6);
//		     			show( subwithmarks); 
		     			}
		     		
		     		if(submarksENG.contains("T2=ENG")){
		     			subwithmarks = line.split(":");
		     			submarksENG = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,7);
//		     			show( subwithmarks); 
		     			}			    	
    	   }   		
        }		
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
