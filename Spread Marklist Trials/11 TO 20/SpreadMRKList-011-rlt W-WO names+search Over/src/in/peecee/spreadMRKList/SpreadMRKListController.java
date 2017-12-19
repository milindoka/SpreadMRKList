package in.peecee.spreadMRKList;


import java.awt.Color;
import java.awt.Component;
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

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
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
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
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
    public Object GetData(JTable table, int row_index, int col_index) {  return SpreadMRKListview.getTable().getValueAt(row_index, col_index); }
    public String GetData1(JTable table, int row_index, int col_index) {  return (String) SpreadMRKListview.getTable().getValueAt(row_index, col_index); }

    
    private ActionListener saveListener, loadListener, processListener, searchListener,
	                       setprinterListener, printListener, canselListener ;
    
    int TotalMarklists=0;
	
	public void contol(){
		
		int n = 2000;
		ResizeTable(SpreadMRKListview.getTable(),n);

/*		for(int i = 0; i < n ; i++){
			{
				String SrNo=String.format("%d",i+1);
		        SetData(SrNo,i,0);
		    }
		}                               */
		
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
//	    	BtnProcess();
	    }

	private void BtnProcess(){
//	    	System.exit(0);
		     ClearTable();
		     ResizeTable(SpreadMRKListview.getTable(),strArray.size());
	    	 boolean flagBKE = false, flagPHY = false;
	    	 String plate[];
	    	 String[] submarks = null;
	    	 String[] subwithmarks = null;
			 String rollno, names, div, Stream = null,  marks, temp1, Subject = null, line;		   	
			 int subjectMarks = 0, Total = 0;
			 			 			 			 
	    	 for(int i=1; i < strArray.size(); i++)                      //  strArray.size()
		     	{ 
	    		 String SrNo=String.format("%d",i);
			     SetData(SrNo,i-1,0);
	    		 plate=strArray.get(i).split("#");
		     	  rollno = plate[0];
		     	  if(plate[1].length()<=12){ 
		     		  rollno = plate[0];
		     	      div = plate[2].substring(0,1);
		     	      SetData(rollno,i-1,1);
			     	  SetData(div, i-1, 2);
			     	  }
		     	 else {
			     	  names = plate[1].substring(0, 60);
	//		     	  str = str.replaceAll("\\s+"," ");                // REGEX replacing method - Removes all white spaces
			     	  names = names.replaceAll("\\s{2}", "").toUpperCase();          // Keeps one white space and removes extra white spaces
			     	  div = plate[2].substring(0,1);
			     	  SetData(rollno,i-1,1);
			     	  SetData(div, i-1, 2);
			     	  SetData(names,i-1,3);
		     	  }
//		     	  show(names);
//		     	  show(names.length());
//		     	   show(plate);            //  Full Plate
//		     	   show(plate[0]);         //  Roll No i.e.  0 th item of plate
//		     	   show(plate[1]);         //  Name i.e. 1st item of plate
//		     	   show(plate[2]);         //  2nd item of plate which is of the form A=U1=ENG:31
//		     	   show(plate[2].substring(0,1);); //  2nd item of plate from which DIV is extracted
		     	  
	        }     	    	 
	    	 
	         ENGMarks();
	         SecLangMarks();
	         VocationalMarks();
	         BIOMarks();
	         ECOMarks();
	         BKEMarks();
	         PHYMarks();
	         OCMMarks();
	         CHEMarks();
	         MATMarks();
	         SEPMarks();
	         TotalScore();  
	    }
	    
	public void TotalScore(){
	     String name, marks;
			 int Marks = 0, TotalMarks = 0;
			 for(int i = 0; i < strArray.size()-1; i++){ 	 //   show(strArray.size());
				 for(int j = 4; j < 28; j++){
					 marks = GetData1(SpreadMRKListview.getTable(),i,j);
//					 marks.trim();
					 if(marks == null || marks.isEmpty()){ marks = "00"; }					 
					 if(marks.contentEquals("AB")){ marks = "00"; }					 
					 Marks = Integer.parseInt(marks);
					 TotalMarks = TotalMarks + Marks;			 
//	               Show(Marks);
//	               Show(TotalMarks);
			 }
			    
			 SetData(TotalMarks,i,28);
			 TotalMarks = 0;
			 
		   }							
		}
	    	    
	private void BtnSearch(){
//	        System.exit(0);
		String[] NAMES;
    	String plate[];
    	String subject = null;
    	String rollno, names;
    	boolean foundName = false, required = true ;
    	int dialogButton = 0 ;
		String RollNo = SpreadMRKListview.Search().getText();
		String Name = SpreadMRKListview.Search().getText();		
		int rows = SpreadMRKListview.getTable().getRowCount();
		if(Name.contentEquals("") || RollNo.contentEquals("") || Name == null || RollNo == null){
		  show("Enter a Valid Roll Number OR Name to search");}
		  for (int row = 0; row < rows; row++) {				
			String SameRoll = GetData1(SpreadMRKListview.getTable(), row, 1);
			plate=strArray.get(row+1).split("#");
		    rollno = plate[0].substring(0);
		    
		    names = plate[1].substring(0, 60);
		    NAMES = names.split("\\(");   // Split at Parenthesis  show(NAMES[0].toUpperCase());
		    
		    if(NAMES[0].toUpperCase().contains(Name.toUpperCase())){ foundName = true; }
			
			if(RollNo.trim().equals(SameRoll.trim())){					
					
			// this will automatically set the view of the scroll in the location of the value
			SpreadMRKListview.getTable().scrollRectToVisible(SpreadMRKListview.getTable().getCellRect(row, 0, true));
					
            // this will automatically set the focus of the searched/selected row/value
			SpreadMRKListview.getTable().setRowSelectionInterval(row, row);	
				 for (int i = 0; i <= SpreadMRKListview.getTable().getColumnCount()-1; i++) {
	
					 SpreadMRKListview.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	             }					
			}				
			
			 for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    
	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	SpreadMRKListview.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	SpreadMRKListview.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	SpreadMRKListview.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    SpreadMRKListview.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	SpreadMRKListview.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
     		     	    JTableHeader th = SpreadMRKListview.getTable().getTableHeader();  //  For header changing dynamically
     		   	        th.repaint();      
     		    	    SpreadMRKListview.Sub4.text= subject; }     		   
	     		  }
	     	   }		
			 
			 
			 if(foundName && required){
			    	
			    	// this will automatically set the view of the scroll in the location of the value
					SpreadMRKListview.getTable().scrollRectToVisible(SpreadMRKListview.getTable().getCellRect(row, 0, true));
					
		            // this will automatically set the focus of the searched/selected row/value
					SpreadMRKListview.getTable().setRowSelectionInterval(row, row);	
			    	
			    	for (int i = 0; i <= SpreadMRKListview.getTable().getColumnCount()-1; i++) {
							
			    		SpreadMRKListview.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
		            }
					dialogButton = JOptionPane.showConfirmDialog (null, "Found !!  Would you like to continue search","WARNING", JOptionPane.YES_NO_OPTION);
			    	if(dialogButton == JOptionPane.YES_OPTION){  required = true; /* Show(required);*/ } 				    	
			    }				    
			    if(foundName){
			    	 for (int j = 2; j < plate.length; j++){							 
				     	   subject = plate[j].substring(5, 8);
//				     	   rollno = plate[0].substring(0);
				     	if(names.contains(Name.toUpperCase())){
//				     		    show(strArray.get(row+1).split("#"));
//				     		   show(strArray.get(5002).split("#"));
				     		       		    
				     		if(subject.contains("ENG")){ 
//				     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	SpreadMRKListview.Eng.text="ENG"; }
				     		    
				     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
				     				||subject.contains("ITE")||subject.contains("CS1")){ 
//				     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	SpreadMRKListview.SL.text= subject; }
				     		   
				     		if(subject.contains("ECO")||subject.contains("BIO")
				     				||subject.contains("EL2")||subject.contains("CS2")){ 
//				     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	SpreadMRKListview.Sub1.text= subject; }
				     		  
				     		if(subject.contains("BKE")||subject.contains("PHY")){ 
//					     		    JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	th.repaint();      
					     		    SpreadMRKListview.Sub2.text= subject; }
				     		 
				     		if(subject.contains("OCM")||subject.contains("CHE")){ 
//				     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	SpreadMRKListview.Sub3.text= subject; }
				     		 
				     		if(subject.contains("MAT")||subject.contains("SEP")){ 
			     		     	    JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
			     		   	        th.repaint();      
			     		    	    SpreadMRKListview.Sub4.text= subject; }
			     		   
				     		  }
				     		   
				     	   }
			    	 if(dialogButton == JOptionPane.NO_OPTION){  required = false; break; /* Show(required);*/ }
			    	foundName = false;
			    }

			 
			 
         }
}				
		private void BtnPrint(){	
		    	String plate[];
		    	String[] NAMES;
		    	String subject = null;
		    	boolean foundName = false, required = true ;
				String names;		   	
				int dialogButton = 0 ;
				String RollNo = SpreadMRKListview.Search().getText();
				String Name = SpreadMRKListview.Search().getText();		
				int rows = SpreadMRKListview.getTable().getRowCount();
				if(Name.contentEquals("") || RollNo.contentEquals("") || Name == null || RollNo == null){
				  show("Enter a Valid Roll Number OR Name to search");}
				if(Name.contentEquals("") || RollNo.contentEquals("") || Name == null || RollNo == null){
					  show("Enter a Valid Roll Number OR Name to search");}
				for (int row = 0; row < rows; row++) {				
					String SameRoll = GetData1(SpreadMRKListview.getTable(), row, 1);
					plate = strArray.get(row+1).split("#");
					
//					names = GetData1(SpreadMRKListview.getTable(), row, 2).substring(0, 60);
				    names = plate[1].substring(0, 60);
				    NAMES = names.split("\\(");   // Split at Parenthesis  show(NAMES[0].toUpperCase());
				    
				    if(NAMES[0].toUpperCase().contains(Name.toUpperCase())){ foundName = true; }
				    
				    if(foundName && required){
				    	
				    	// this will automatically set the view of the scroll in the location of the value
						SpreadMRKListview.getTable().scrollRectToVisible(SpreadMRKListview.getTable().getCellRect(row, 0, true));
						
			            // this will automatically set the focus of the searched/selected row/value
						SpreadMRKListview.getTable().setRowSelectionInterval(row, row);	
				    	
				    	for (int i = 0; i <= SpreadMRKListview.getTable().getColumnCount()-1; i++) {
								
				    		SpreadMRKListview.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
			            }
						dialogButton = JOptionPane.showConfirmDialog (null, "Found !!  Would you like to continue search","WARNING", JOptionPane.YES_NO_OPTION);
				    	if(dialogButton == JOptionPane.YES_OPTION){  required = true; /* Show(required);*/ } 				    	
				    }				    
				    if(foundName){
				    	 for (int j = 2; j < plate.length; j++){							 
					     	   subject = plate[j].substring(5, 8);
//					     	   rollno = plate[0].substring(0);
					     	if(names.contains(Name.toUpperCase())){
//					     		    show(strArray.get(row+1).split("#"));
//					     		   show(strArray.get(5002).split("#"));
					     		       		    
					     		if(subject.contains("ENG")){ 
//					     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	    th.repaint();      
					     		    	SpreadMRKListview.Eng.text="ENG"; }
					     		    
					     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
					     				||subject.contains("ITE")||subject.contains("CS1")){ 
//					     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	    th.repaint();      
					     		    	SpreadMRKListview.SL.text= subject; }
					     		   
					     		if(subject.contains("ECO")||subject.contains("BIO")
					     				||subject.contains("EL2")||subject.contains("CS2")){ 
//					     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	    th.repaint();      
					     		    	SpreadMRKListview.Sub1.text= subject; }
					     		  
					     		if(subject.contains("BKE")||subject.contains("PHY")){ 
//						     		    JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//						     		   	th.repaint();      
						     		    SpreadMRKListview.Sub2.text= subject; }
					     		 
					     		if(subject.contains("OCM")||subject.contains("CHE")){ 
//					     		     	JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	    th.repaint();      
					     		    	SpreadMRKListview.Sub3.text= subject; }
					     		 
					     		if(subject.contains("MAT")||subject.contains("SEP")){ 
				     		     	    JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
				     		   	        th.repaint();      
				     		    	    SpreadMRKListview.Sub4.text= subject; }
				     		   
					     		  }
					     		   
					     	   }
				    	 if(dialogButton == JOptionPane.NO_OPTION){  required = false; break; /* Show(required);*/ }
				    	foundName = false;
				    }
		}
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
		 String ENGmarks = null ,line;		   	
		 int subjectMarks = 0, Total = 0;
    	 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		    line = plate[j];
			    	submarks = line.split(":");         // To split the text from marks
			    	//			    	if(marks.contentEquals("AB")){ marks = "00"; }
//			    	subjectMarks =Integer.parseInt(marks);
//			    	Total = Total + subjectMarks;
		     		 ENGmarks = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(ENGmarks.contains("U1=ENG")){
		     			subwithmarks = line.split(":");
		     			ENGmarks = subwithmarks[1];	
		     			SetData(ENGmarks, i-1,4);
//		     			show( subwithmarks); 
		     			}	                         
		     		 
		     		if(ENGmarks.contains("T1=ENG")){
		     			subwithmarks = line.split(":");
		     			ENGmarks = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,5);
//		     			show( subwithmarks); 
		     			}	
		     		
		     		if(ENGmarks.contains("U2=ENG")){
		     			subwithmarks = line.split(":");
		     			ENGmarks = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,6);
//		     			show( subwithmarks); 
		     			}
		     		
		     		if(ENGmarks.contains("T2=ENG")){
		     			subwithmarks = line.split(":");
		     			ENGmarks = subwithmarks[1];
		     			 SetData(subwithmarks[1], i-1,7);
//		     			show( subwithmarks); 
		     			}			     		
    	   }   		
        }		
     	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
    	 th.repaint();                                          
     	 SpreadMRKListview.Eng.text="ENG";                       
	}     
	
	public void SecLangMarks(){
		  
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String SecLangITmarks = null, line;		   	
		 int Total = 0;
   	 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		    line = plate[j];
			    	submarks = line.split(":");         // To split the text from marks
			    	SecLangITmarks = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(SecLangITmarks.contains("U1=MAR")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	  		     		 		    
		     		 if(SecLangITmarks.contains("U1=ITE")){
			     			subwithmarks = line.split(":");
			     			SecLangITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
		     		if(SecLangITmarks.contains("U1=HIN")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	
		     		if(SecLangITmarks.contains("U1=TAM")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}
		     		
		     		if(SecLangITmarks.contains("T1=MAR")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	  		     		 		    
		     		 if(SecLangITmarks.contains("T1=ITE")){
			     			subwithmarks = line.split(":");
			     			SecLangITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	
		     		if(SecLangITmarks.contains("T1=HIN")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		if(SecLangITmarks.contains("T1=TAM")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		
		     		if(SecLangITmarks.contains("U2=MAR")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	  		     		 		    
		     		 if(SecLangITmarks.contains("U2=ITE")){
			     			subwithmarks = line.split(":");
			     			SecLangITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
		     		if(SecLangITmarks.contains("U2=HIN")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SecLangITmarks.contains("U2=TAM")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SecLangITmarks.contains("T2=MAR")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 11);                 		     			
		     			}	  		     		 		    
		     		 if(SecLangITmarks.contains("T2=ITE")){
			     			subwithmarks = line.split(":");
			     			SecLangITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 11);                 		     			
			     			}	
		     		if(SecLangITmarks.contains("T2=HIN")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 11);                 		     			
		     			}	
		     		if(SecLangITmarks.contains("T2=TAM")){
		     			subwithmarks = line.split(":");
		     			SecLangITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 11);                 		     			
		     			}	
	     	   }
	     	}
   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
	 th.repaint();                                          
 	 SpreadMRKListview.SL.text = "SL / ITE / EL1 / CS1";
   	    	 
	}
	
	public void VocationalMarks(){
		  
			 boolean flagBKE = false, flagPHY = false;
			 String[] submarks = null;
		   	 String[] subwithmarks = null;
		   	 String plate[];
			 String VocationalMarks = null ,line;		   	
			 int Total = 0;
			 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
		     	{
				   plate=strArray.get(i).split("#");
				   for (int j = 2; j < plate.length; j++){	
					 
					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      VocationalMarks = plate[j].substring(2, 8);     //   show( submarksU1); 

					   if(VocationalMarks.contains("U1=EL1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 11);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS1")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 11);                 		     			
			     			}	 
			     		

			     		if(VocationalMarks.contains("U1=EL2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 15);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS2")){
			     			subwithmarks = line.split(":");
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 15);                 		     			
			     			}			     					     		
				   }
				 				 				 
		     }
			 
		   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
			 th.repaint();                                          
//		 	 SpreadMRKListview.SL.text = "MAR"; 
	}
	
	public void BIOMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String BIOMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      BIOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(BIOMarks.contains("U1=BIO")){
			     			subwithmarks = line.split(":");
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(BIOMarks.contains("T1=BIO")){
			     			subwithmarks = line.split(":");
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(BIOMarks.contains("U2=BIO")){
			     			subwithmarks = line.split(":");
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(BIOMarks.contains("T2=BIO")){
			     			subwithmarks = line.split(":");
			     			BIOMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 15);
	     	   }
	     	}
	    }		
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub1.text = "ECO / BIO / EL2 / CS2";
	}
	
	public void ECOMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String ECOMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      ECOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(ECOMarks.contains("U1=ECO")){
			     			subwithmarks = line.split(":");
			     			ECOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(ECOMarks.contains("T1=ECO")){
			     			subwithmarks = line.split(":");
			     			ECOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(ECOMarks.contains("U2=ECO")){
			     			subwithmarks = line.split(":");
			     			ECOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(ECOMarks.contains("T2=ECO")){
			     			subwithmarks = line.split(":");
			     			ECOMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 15);
	         	   }
	     	   }
	     	}
	     	   JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
				 th.repaint();                                          
//			 	 SpreadMRKListview.SL.text = "MAR"; 
	     	
	}

	public void BKEMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String BKEMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      BKEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(BKEMarks.contains("U1=BKE")){
			     			subwithmarks = line.split(":");
			     			BKEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 16);                 		     			
			     			}	
			     		if(BKEMarks.contains("T1=BKE")){
			     			subwithmarks = line.split(":");
			     			BKEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 17);                 		     			
			     			}	 
					   if(BKEMarks.contains("U2=BKE")){
			     			subwithmarks = line.split(":");
			     			BKEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 18);                 		     			
			     			}	
			     		if(BKEMarks.contains("T2=BKE")){
			     			subwithmarks = line.split(":");
			     			BKEMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 19);
	         	   }
	     	   }
	       	}
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub2.text = "BKE / PHY";
 	}
		 
    public void PHYMarks(){
				 boolean flagBKE = false, flagPHY = false;
				 String[] submarks = null;
			   	 String[] subwithmarks = null;
			   	 String plate[];
				 String PHYMarks = null ,line;		   	
				 int Total = 0;
				 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
			     	{
					 plate=strArray.get(i).split("#");
//			     	  show(plate);
			     	   for (int j = 2; j < plate.length; j++){				     		   
							  line = plate[j];
						      submarks = line.split(":");         // To split the text from marks
						      PHYMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
							   if(PHYMarks.contains("U1=PHY")){
					     			subwithmarks = line.split(":");
					     			PHYMarks = subwithmarks[1];	
					     			SetData(subwithmarks[1], i-1, 16);                 		     			
					     			}	
					     		if(PHYMarks.contains("T1=PHY")){
					     			subwithmarks = line.split(":");
					     			PHYMarks = subwithmarks[1];	
					     			SetData(subwithmarks[1], i-1, 17);                 		     			
					     			}	 
							   if(PHYMarks.contains("U2=PHY")){
					     			subwithmarks = line.split(":");
					     			PHYMarks = subwithmarks[1];	
					     			SetData(subwithmarks[1], i-1, 18);                 		     			
					     			}	
					     		if(PHYMarks.contains("T2=PHY")){
					     			subwithmarks = line.split(":");
					     			PHYMarks = subwithmarks[1];	
					     		    SetData(subwithmarks[1], i-1, 19);
			         	   }
			     	   }

			     	}
			   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
				 th.repaint();                                          
//			 	 SpreadMRKListview.SL.text = "MAR"; 
				 SpreadMRKListview.Sub2.text = "BKE / PHY";
	}

	public void OCMMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String OCMMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      OCMMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(OCMMarks.contains("U1=OCM")){
			     			subwithmarks = line.split(":");
			     			OCMMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 20);                 		     			
			     			}	
			     		if(OCMMarks.contains("T1=OCM")){
			     			subwithmarks = line.split(":");
			     			OCMMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 21);                 		     			
			     			}	 
					   if(OCMMarks.contains("U2=OCM")){
			     			subwithmarks = line.split(":");
			     			OCMMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 22);                 		     			
			     			}	
			     		if(OCMMarks.contains("T2=OCM")){
			     			subwithmarks = line.split(":");
			     			OCMMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 23);
	         	   }
	     	   }
	       	}
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub3.text = "OCM / CHE";
  	}

	
	public void CHEMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String CHEMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      CHEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(CHEMarks.contains("U1=CHE")){
			     			subwithmarks = line.split(":");
			     			CHEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 20);                 		     			
			     			}	
			     		if(CHEMarks.contains("T1=CHE")){
			     			subwithmarks = line.split(":");
			     			CHEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 21);                 		     			
			     			}	 
					   if(CHEMarks.contains("U2=CHE")){
			     			subwithmarks = line.split(":");
			     			CHEMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 22);                 		     			
			     			}	
			     		if(CHEMarks.contains("T2=CHE")){
			     			subwithmarks = line.split(":");
			     			CHEMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 23);
	         	   }
	     	   }
	       	}
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub3.text = "OCM / CHE";
 	}  
	
	public void MATMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String MATMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      MATMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(MATMarks.contains("U1=MAT")){
			     			subwithmarks = line.split(":");
			     			MATMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 24);                 		     			
			     			}	
			     		if(MATMarks.contains("T1=MAT")){
			     			subwithmarks = line.split(":");
			     			MATMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 25);                 		     			
			     			}	 
					   if(MATMarks.contains("U2=MAT")){
			     			subwithmarks = line.split(":");
			     			MATMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 26);                 		     			
			     			}	
			     		if(MATMarks.contains("T2=MAT")){
			     			subwithmarks = line.split(":");
			     			MATMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 27);
	         	   }
	     	   }
	       	}
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub4.text = "MAT / SPA";
 	}

	public void SEPMarks(){
		 boolean flagBKE = false, flagPHY = false;
		 String[] submarks = null;
	   	 String[] subwithmarks = null;
	   	 String plate[];
		 String SEPMarks = null ,line;		   	
		 int Total = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		   

					  line = plate[j];
				      submarks = line.split(":");         // To split the text from marks
				      SEPMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(SEPMarks.contains("U1=SEP")){
			     			subwithmarks = line.split(":");
			     			SEPMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 24);                 		     			
			     			}	
			     		if(SEPMarks.contains("T1=SEP")){
			     			subwithmarks = line.split(":");
			     			SEPMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 25);                 		     			
			     			}	 
					   if(SEPMarks.contains("U2=SEP")){
			     			subwithmarks = line.split(":");
			     			SEPMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 26);                 		     			
			     			}	
			     		if(SEPMarks.contains("T2=SEP")){
			     			subwithmarks = line.split(":");
			     			SEPMarks = subwithmarks[1];	
			     		    SetData(subwithmarks[1], i-1, 27);
	         	   }
	     	   }
	       	}
	   	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//	 	 SpreadMRKListview.SL.text = "MAR"; 
		 SpreadMRKListview.Sub4.text = "MAT / SPA";
	}

	private class HighlightRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	table = SpreadMRKListview.getTable();
	        // everything as usual
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // added behavior
	        if(row == table.getSelectedRow()) {

	            // this will customize that kind of border that will be use to highlight a row
	            setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLUE));
	        }

	        return this;
	    }
	}
	
	 public void ClearTable()
	   {  int rows = SpreadMRKListview.getTable().getRowCount();
		  int cols = SpreadMRKListview.getTable().getColumnCount();
		 for (int i = 0; i < rows; i++)
		  {
		    for (int j = 1; j < cols; j++)
		      {
				SetData("",i,j);
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
