package in.peecee.spreadMRKList;


//   import in.peecee.spreadMRKList.SpreadMRKListView.ColumnGroup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SpreadMRKListController {

	private SpreadMRKListModel Model;
	private SpreadMRKListView View;
	private SpreadMRKListSubMarks SubMarks = new SpreadMRKListSubMarks();
	private showstatistics Stats = new showstatistics();
	private ScoreCardButtons SCButtons = new ScoreCardButtons();
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> TotalMarksArray = new ArrayList<String>();
	public  ArrayList<String> subArray = new ArrayList<String>();
	public  ArrayList<String> subMarksArray = new ArrayList<String>();
	public  ArrayList<String> headerArray = new ArrayList<String>();
	public  ArrayList<String> StuDetailsArray = new ArrayList<String>();
	
	
	public void show(float percent) {JOptionPane.showMessageDialog(null, percent);}   ///for debugging
	public void show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
	public void Show(ArrayList<String> arrayList) {JOptionPane.showMessageDialog(null, arrayList);}   ///for debugging
	
	public SpreadMRKListController(SpreadMRKListModel model, SpreadMRKListView view){

	        this.Model = model;
	        this.View = view;   
		    System.out.println(model.getJarPath());       ///set JAR path in model variable path;
	    }
	
    String subject[] = { "ENG", "HIN", "MAR", "TAM", "ITE", "EL1", "CS1", "ECO", "BIO", "EL2", "CS2",
    		             "BKE", "PHY", "OCM", "CHE", "MAT", "SEP"  /*, "EVS", "PTE" */ };
    
    String subjectExamType[] = {"U1=ENG","U2=ENG","T1=ENG","T2=ENG","U1=HIN","U2=HIN","T1=HIN","T2=HIN","U1=MAR","U2=MAR","T1=MAR","T2=MAR",
    		                    "U1=TAM","U2=TAM","T1=TAM","T2=TAM","U1=PHY","U2=PHY","T1=PHY","T2=PHY","U1=CHE","U2=CHE","T1=CHE","T2=CHE",
    		                    "U1=BIO","U2=BIO","T1=BIO","T2=BIO","U1=BKE","U2=BKE","T1=BKE","T2=BKE","U1=ECO","U2=ECO","T1=ECO","T2=ECO",
    		                    "U1=OCM","U2=OCM","T1=OCM","T2=OCM","U1=MAT","U2=MAT","T1=MAT","T2=MAT","U1=SEP","U2=SEP","T1=SEP","T2=SEP",
    		                    "U1=CS1","U2=CS1","T1=CS1","T2=CS1","U1=CS2","U2=CS2","T1=CS2","T2=CS2","U1=EL1","U2=EL1","T1=EL1","T2=EL1",
    		                    "U1=EL2","U2=EL2","T1=EL2","T2=EL2","U1=IT1","U2=IT1","T1=IT1","T2=IT1","EVS", "PTE" };
			  
    String subjectOPP[] = { "ENGORA", "HINORA", "MARORA", "TAMORA", "PHYPRA", "CHEPRA", "BIOPRA", "BKEPRO", "ECOPRO", "OCMPRO", "MATPRA",
             "SEPPRO", "CS1PRA", "CS2PRA", "EL1PRA", "EL2PRA", "ITEPRA", "EVSPRO", "PTEPRA"};					  
	
    public void SetData1(JTable table,Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }
    public void SetData(Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }
    public Object GetData(JTable table, int row_index, int col_index) {  return View.getTable().getValueAt(row_index, col_index); }
    public String GetData1(JTable table, int row_index, int col_index) {  return (String) View.getTable().getValueAt(row_index, col_index); }
    public int GetData2(JTable table, int row_index, int col_index) {  return (int) View.getTable().getValueAt(row_index, col_index); }
    
    
    private ActionListener saveListener, loadListener, processListener, searchListener, ResultListener,
	                       setprinterListener, printListener, printAllListener, canselListener, UpdateListener ;
    
    int TotalMarklists=0;
	
	public void contol(){
		
		int n = 2000;
		ResizeTable(View.getTable(),n);

/*		for(int i = 0; i < n ; i++){
			{
				String SrNo=String.format("%d",i+1);
		        SetData(SrNo,i,0);
		    }
		}                               */
		
		 SetPrinter sp=new SetPrinter();
	        String printername=sp.LoadPreferences();
	        Model.setPrinterName(printername);
	        View.setPrinterLabel(printername);
	        
	        
	        View.getTable().addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub					
				}				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub	
					  String RowNo = null;
					  int column = 0;
					  int row = View.getTable().getSelectedRow();         // To get the row number of JTable 
					  String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
					  int keyCode = e.getKeyCode();
					    switch( keyCode ) { 
					        case KeyEvent.VK_UP:				           
					        SearchByRollNo(RollNo);
					        break;
					    
					        case KeyEvent.VK_DOWN:
					        SearchByRollNo(RollNo);
					        break;					       
					     }	
				}				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
								
				}
			});	           	
	        	        
	        View.getTable().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent evt) {
//	            	boolean mouseclicked = true;  
	            	String RollNo = null;
	            	int Rows = View.getTable().getRowCount();
	                int row = View.getTable().rowAtPoint(evt.getPoint());
	                         RollNo = GetData1(View.getTable(), row, 1 );
	                SearchByRollNo(RollNo);                  
	            }	                
	});	        
	        
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
	  
	ResultListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
		           btnResult();
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
	
    UpdateListener = new ActionListener() {
    	public void actionPerformed(ActionEvent actionEvent) {                  
            BtnUpdate();
        }
     }; 
	  
	setprinterListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSetPrinter();
	            }
	  };
	        
	printListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnPrintCurrent();
	            }
	  }; 
	  
	printAllListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {                  
			BtnPrintAllMarksCards();
        }
    }; 
	  

	View.getSaveButton().addActionListener(saveListener);
	View.getLoadButton().addActionListener(loadListener);
	View.getResultButton().addActionListener(ResultListener);
	View.getProcessButton().addActionListener(processListener);
	View.getSearchButton().addActionListener(searchListener);
	View.getSetPrinterButton().addActionListener(setprinterListener);
	View.getPrintButton().addActionListener(printListener);
	View.getPrintAllButton().addActionListener(printAllListener);
	View.getCanselButton().addActionListener(canselListener);
	View.getUpdateButton().addActionListener(UpdateListener);

	 }	
	
	public void displayAll(){
		String lines = null;
		lines = Model.strArray.get(4);
	}
	
	private void BtnSave(){
//	        System.exit(0);
			String fyle="";
			  JFileChooser choosertosave = new JFileChooser();
			  choosertosave.setMultiSelectionEnabled(true);
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("Result View", "rlt");
	          choosertosave.setFileFilter(filter);
	          choosertosave.setCurrentDirectory(new File("E:/Eclipse/Prahlad/Test Entries"));
	          choosertosave.setCurrentDirectory(new File("/home/prahallad/Test Entries"));
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
	              if (!fyle.endsWith(".rlt")) fyle+= ".rlt";
	              SaveToFile(fyle);                             //Save to File is called here
	              choosertosave.getSelectedFile().getName();
	           }  			        
	    }

	
	private void BtnLoad(){
//	        System.exit(0);
		Model.LoadData();
		process();
		
		
	    }

	private void btnResult(){
//		System.exit(0);
		Result();
	}
	
	public void process(){
	     ClearTable();
	     ResizeTable(View.getTable(),Model.strArray.size());
  	 boolean flagBKE = false, flagPHY = false;
  	 String plate[];
  	 String rollno, names, div;		   	
		 for(int i=1; i < Model.strArray.size(); i++)                      //  strArray.size()
	     	{ 
		   		 String SrNo=String.format("%d",i);
				 SetData(SrNo,i-1,0);
		   		 plate=Model.strArray.get(i).split("#");
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
	     	  
      }
		 
//		 displayAll();
//		SubMarks.ENGMarks();
//		Model.ENGMarks();
//	   Model.test();	 
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
       EVSMarks();
       PTEGrade();
       TotalScore(); 
 //      Result();
  }


	
	private void BtnProcess(){
//	    	System.exit(0);
		String EngMarks = GetData1(View.getTable(),5,28);
//		show(EngMarks);
		String Eng = GetData1(View.getTable(),7,28);
		Stats.SetData(EngMarks, 1,6);
//		show(Eng);
		Stats.SetData1(Eng, 0, 4);
		Stats.ShowStats();

		
	}                    
	    	    	    
	private void BtnSearch(){
//	        System.exit(0);
		String[] NAMES;
    	String plate[];
    	String subject = null;
    	String rollno, names;
    	boolean foundName = false, required = true , found = false;
    	int dialogButton = 0 ;
		String RollNo = View.Search().getText();
		String Name = View.Search().getText();		
		int Rows = View.getTable().getRowCount();
		if(Name.contentEquals("") || RollNo.contentEquals("") || Name == null || RollNo == null){
		  show("Enter a Valid Roll Number OR Name to search"); return;}
		  for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);	
			  
			if(RollNo.trim().equals(SameRoll.trim())){		//	show(rollno);		
			found = true;
			// this will automatically set the view of the scroll in the location of the value
			View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
            // this will automatically set the focus of the searched/selected row/value
			View.getTable().setRowSelectionInterval(row, row);	
				 for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
	
					 View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	             }					
			}		

			 for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
     		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
     		   	        th.repaint();      
     		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
			 
			 
			  if(plate[1].length()<=12){names = plate[1].substring(0, 0); }  // For table or .rlt file with out names
			  else names = plate[1].substring(0, 60);
		      NAMES = names.split("\\(");   // Split at Parenthesis  show(NAMES[0].toUpperCase());		    
		      if(NAMES[0].toUpperCase().contains(Name.toUpperCase())){ foundName = true; }			 
			  if(foundName && required){
			    	found = true;
			    	// this will automatically set the view of the scroll in the location of the value
					View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
		            // this will automatically set the focus of the searched/selected row/value
					View.getTable().setRowSelectionInterval(row, row);	
			    	
			    	for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
							
			    		View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
		            }
					dialogButton = JOptionPane.showConfirmDialog (null, "Found !!  Would you like to continue search","WARNING", JOptionPane.YES_NO_OPTION);
			    	if(dialogButton == JOptionPane.YES_OPTION){  required = true; /* Show(required);*/ } 				    	
			    }	
			  
			    if(foundName){
			    	 for (int j = 2; j < plate.length; j++){							 
				     	   subject = plate[j].substring(5, 8);
//				     	   rollno = plate[0].substring(0);
				     	if(names.contains(Name.toUpperCase())){
	     		       		    
				     		if(subject.contains("ENG")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Eng.text="ENG"; }
				     		    
				     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
				     				||subject.contains("ITE")||subject.contains("CS1")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.SL.text= subject; }
				     		   
				     		if(subject.contains("ECO")||subject.contains("BIO")
				     				||subject.contains("EL2")||subject.contains("CS2")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Sub1.text= subject; }
				     		  
				     		if(subject.contains("BKE")||subject.contains("PHY")){ 
//					     		    JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	th.repaint();      
					     		    View.Sub2.text= subject; }
				     		 
				     		if(subject.contains("OCM")||subject.contains("CHE")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Sub3.text= subject; }
				     		 
				     		if(subject.contains("MAT")||subject.contains("SEP")){ 
			     		     	    JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
			     		   	        th.repaint();      
			     		    	    View.Sub4.text= subject; }
			     		   
				     		  }
				     		   
				     	   }
			    	 if(dialogButton == JOptionPane.NO_OPTION){  required = false; break; }
			    	 foundName = false;
			    }			 		
         }
		  
		if(found == false){ show("No Such Roll Number OR Name Found"); }  
}		
	
	
   private void BtnPrintCurrent(){			

//	   SCButtons.showScoreButtons(); 
	   final ArrayList<String> subject;
	    int row = View.getTable().getSelectedRow();
	    if(row <= 0){show("No name or Roll Number is selected "); return;}
	    subMarksArray.removeAll(subMarksArray);   
	   	for(int k = 4; k < 30 ; k++){	   		
	   		subMarksArray.add((String) GetData1(View.getTable(),row,k));
	   	}
	   	
	   	StuDetailsArray.removeAll(StuDetailsArray);
	   	for(int k = 1; k < 4 ; k++){	   		
	   		StuDetailsArray.add((String) GetData1(View.getTable(),row,k));
	   	}
	   	
		  final String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
//		  ArrayList<String> subject;		  
		  subject = collheaderfinder(RollNo);
		  String EVS = GetData1(View.getTable(),row,28);
		  
	 try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
		  pjob.setJobName("Current Marks Card Print");
		  pjob.setCopies(1);
		  pjob.setPrintable(new Printable() {
		  public int print(Graphics pg, PageFormat pf, int pageNum) {
		  int totalpages = 0;
		  if (pageNum > totalpages) // we only print one page
		  return Printable.NO_SUCH_PAGE; // ie., end of job
		  Font newFont;		          
		  newFont = new Font("Liberation Serif", Font.PLAIN, 13);
		  int LtMrg = 40;       // Left Top x, Left Top y and Left Margin
		  int BtMrg = 750;	        		          		          		          
		  for(int i = 0; i < 8; i++){pg.drawRect(80, 300+i*20, 80, 20); }    // Printing LEFT Two columns grid			  				          
		  for(int j = 0; j < 2; j++){
			pg.drawRect(230 + j*87, 460, 88, 20);                            // Printing Bottom Rectangles					
			pg.drawString("RESULT", 250, 475);
		  }	
			for(int j = 0; j < 11; j++){
				  for(int i = 0; i < 8; i++){	
					  if(j < 10) { pg.drawRect(160 + j*35, 300+i*20, 35, 20);}        // Printing Body of Marks Sheets
					  if(j == 10){ pg.drawRect(510, 300+i*20, 65, 20); }	  
				  }   
				}   
		  
		  for(int i = 0; i < subject.size(); i++){ pg.drawString(subject.get(i), 235+i*35, 315);}	  //   Subject 		 					  
			  				  			  
		  pg.drawString("Examination", 82, 315);
		  pg.drawString("Unit Test I", 90, 335);
		  pg.drawString("Terminal I", 90, 355);
	      pg.drawString("Unit Test II", 90, 375);
		  pg.drawString("Terminal II", 90, 395);
		  pg.drawString("Aaggregate", 85, 415);
		  pg.drawString("Average", 95, 435);
		  pg.drawString("Grace", 103, 455);
		  pg.drawString("Max", 165, 315);
		  pg.drawString("Min", 200, 315);
		  pg.drawString("EVS", 445, 315);
		  pg.drawString("PTE", 480, 315);          
		  pg.drawString("Total", 512, 315);
		  pg.drawString("25", 170, 335);
		  pg.drawString("50", 170, 355);
		  pg.drawString("25", 170, 375);
		  pg.drawString("100", 165, 395);
		  pg.drawString("70", 205, 415);
		  pg.drawString("35", 205, 435);
		  pg.drawString("15", 170, 455);					  			  
			  			
		for(int k = 0; k < 4; k++) { pg.drawString("-----", 200, 335+k*20); }
		for(int k = 0; k < 2; k++) { pg.drawString("-----", 165, 415+k*20);	}
		pg.drawString("-----", 205, 455);			
		pg.drawString("( FOR OFFICE USE ONLY )", 230, LtMrg);
		pg.drawString("( FOR OFFICE USE ONLY )", 230, BtMrg);
		int k = 0;
		for(int i= 0; i < 6; i++){
		   for(int j = 0; j < 4; j++){
			 pg.drawString(subMarksArray.get(k), 240+i*35, 335+j*20);   //  All Marks
			 k++;
			}								
		}		
		
///// E X A M W I S E   S U M
		
		pg.drawString(subMarksArray.get(24), 450, 395);               //  EVS marks
		pg.drawString(subMarksArray.get(25), 485, 395);               //  PTE Grade
		int row = View.getTable().getSelectedRow();        
		pg.drawString(String.valueOf(SumU1Score()), 515, 335);        // Sum of all Unit 1 Exams
		pg.drawString(String.valueOf(SumT1Score()), 515, 355);        // Sum of all Term 1 Exams
		pg.drawString(String.valueOf(SumU2Score()), 515, 375);        // Sum of all Unit 2 Exams
		pg.drawString(String.valueOf(SumT2andEVSScore()), 515, 395);  // Sum of all Term 2 Exams
		
/////  A  G  G  R  E  G  A  T  E
		
        pg.drawString(String.valueOf(Sub1()), 240, 415);              // Sum of all marks English Subject
        pg.drawString(String.valueOf(Sub2()), 273, 415);              // Sum of all marks SL or Tech Subject
        pg.drawString(String.valueOf(Sub3()), 308, 415);              // Sum of all marks SL or Tech2 Subject
        pg.drawString(String.valueOf(Sub4()), 341, 415);              // Sum of all marks SL or Sub4 Subject
        pg.drawString(String.valueOf(Sub5()), 378, 415);              // Sum of all marks SL or Sub5 Subject
        pg.drawString(String.valueOf(Sub6()), 412, 415);              // Sum of all marks SL or Sub6 Subject
        pg.drawString(GetData1(View.getTable(),row,28), 450, 415);    // Marks of EVS Subject

/////  A  V  E  R  A  G  E 
        
		float SUB1 = Sub1(); int sub1 = (int) Math.ceil(SUB1/2); 
		pg.drawString(String.valueOf(sub1), 240, 435);

		float SUB2 = Sub2(); int sub2 = (int) Math.ceil(SUB2/2); 
		pg.drawString(String.valueOf(sub2), 275, 435);

		float SUB3 = Sub3(); int sub3 = (int) Math.ceil(SUB3/2); 
		pg.drawString(String.valueOf(sub3), 310, 435);

		float SUB4 = Sub4(); int sub4 = (int) Math.ceil(SUB4/2); 
		pg.drawString(String.valueOf(sub4), 345, 435);

		float SUB5 = Sub5(); int sub5 = (int) Math.ceil(SUB5/2); 
		pg.drawString(String.valueOf(sub5), 380, 435);

		float SUB6 = Sub6(); int sub6 = (int) Math.ceil(SUB6/2); 
		pg.drawString(String.valueOf(sub6), 415, 435);
		
        pg.drawString(GetData1(View.getTable(),row,28), 450, 435);    // Marks of EVS Subject
        
//		  Font newFont;		          
		  newFont = new Font("Liberation Serif", Font.PLAIN, 9);

		pg.drawString(String.valueOf(SumU1Score()+SumT1Score()+SumU2Score()
				                     +SumT2andEVSScore()+"/1250" ), 514, 415);  // Sum of all U1, T1, U2, T2

		pg.drawString(String.valueOf(sub1+sub2+sub3+sub4+sub5+sub6+
				      Integer.parseInt(GetData1(View.getTable(),row,28))+"/650" ), 514, 435);  // Sum of all Averages

	    				
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int YEAR = year-1;

		newFont = new Font("Liberation Serif", Font.PLAIN, 10);

		pg.drawString("Mark Sheet showing the number of marks Obtained by  ", 80, 200);
		pg.drawString(StuDetailsArray.get(2), 80, 220);   //  Name of student 
		pg.drawString("with Roll No. : "+ StuDetailsArray.get(0)+" of Division : "
		              +StuDetailsArray.get(1)+", in " + Streamfinder(RollNo)+ " stream", 80, 240);

		pg.drawString("The following table shows each head of passing at FYJC examintion conducted", 80, 260);
		pg.drawString("during the academic year " + YEAR +" - " + year, 80, 280);				
		pg.drawString("NOTE  :  This marksheet has been prepared as per the instruction of circular", 80, 520);
		pg.drawString("No 6987,dated 04/11/2009 issued by Secretary, Maharashtra State", 140, 540);
		pg.drawString("Board of Secondary and Higher Secondary Education,Pune 411004", 140, 560);
			
		return Printable.PAGE_EXISTS;
	  }
   });

		if (pjob.printDialog() == false) // choose printer
		return; 
				     
		HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
		pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
		pjob.print(pattribs); 
	}
		catch (PrinterException pe) {
		pe.printStackTrace();
	  }                                     			
   
 }
   
	public void BtnPrintAllMarksCards(){
		
		   final ArrayList<String> subject;
		    int row = 24;    
//		    if(row <= 0){show("No name or Roll Number is selected "); return;}
		    subMarksArray.removeAll(subMarksArray);   
		   	for(int k = 4; k < 30 ; k++){	   		
		   		subMarksArray.add((String) GetData1(View.getTable(),row,k));
		   	}
		   	
		   	StuDetailsArray.removeAll(StuDetailsArray);
		   	for(int k = 1; k < 4 ; k++){	   		
		   		StuDetailsArray.add((String) GetData1(View.getTable(),row,k));
		   	}
		   	
			  final String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
			  subject = collheaderfinder(RollNo);
			  String EVS = GetData1(View.getTable(),row,28);

		  try {
              final String[] TableItemC1 = {"Examination","Unit Test I","Terminal I","Unit test II","Terminaal II",
            		                "Aggregate","Average","Grace"};
              final String[] TableItemC2 = {"Max", "25", "50", "25", "100", "-----", "-----", "15"};
              final String[] TableItemC3 = {"Min", "-----", "-----", "-----", "-----", "70", "35", "-----"};
			  PrinterJob pjob = PrinterJob.getPrinterJob();
			  pjob.setCopies(1);			    
			  pjob.setJobName("All Marks Card - Print");
			  pjob.setPrintable(new Printable() {
			  public int print(Graphics pg, PageFormat pf, int pageNum) {
				int totalpages = 15;
				  if (pageNum < totalpages) // we only print one page
				   {
					pg.drawString("( FOR OFFICE USE ONLY )", 230, 40);
					pg.drawString("( FOR OFFICE USE ONLY )", 230, 750);
					pg.drawString(subMarksArray.get(24), 430, 395);               //  EVS marks
					pg.drawString(subMarksArray.get(25), 465, 395);               //  PTE Grade
					  pg.drawString("EVS", 425, 315);
					  pg.drawString("PTE", 460, 315);          
					pg.drawString("Total", 505, 315);
					pg.drawString("RESULT", 250, 475);
				for(int j =0; j < 12; j++){
					 for(int i = 0; i < 8; i++){
					   if(j == 0) { pg.drawRect(60, 300+i*20, 80, 20); }            // Printing LEFT columns grid
					   if(j == 11){ pg.drawRect(490, 300+i*20, 70, 20);}            // Printing RIGHT columns grid
					   if(j>0 &&j<11) {pg.drawRect(105 + j*35, 300+i*20, 35, 20);}  // Printing Body of Marks Sheets
					 }					
				}
				for(int j = 0; j < 2; j++){pg.drawRect(230 + j*87, 460, 88, 20);}   // Printing Bottom Rectangles							
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC1[i], 62, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC2[i], 145, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC3[i], 182, 315+i*20);}
				for(int i = 0; i < subject.size(); i++){ pg.drawString(subject.get(i), 214+i*35, 315);}	 // Subjects
				
				
				      return Printable.PAGE_EXISTS;
				    } 
				    else
					{
					 return Printable.NO_SUCH_PAGE;
					}
				    
		        }
			    
			    
		});

	        if (pjob.printDialog() == false) // choose printer
		    return; 
			      
			HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
		    pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
			pjob.print(pattribs); 
		  }
		    catch (PrinterException pe) {
		    pe.printStackTrace();
		  }                                     				
	   }	


	   	   //   Swapping Total Scores in decreasing order --- Insertion Sorting 
	   	   
/*	   	   int first = 0, second = 0, max = 0, temp = 0;
	   	   String Fst = null, sec = null, Marks = null,temp1 = null, temp2 = null ;
	   	   
	   	TotalMarksArray.removeAll(TotalMarksArray);   
	   	for(int k = 0; k < Rows ; k++){	   		
	   		TotalMarksArray.add((String) GetData1(View.getTable(),k,28));
	   	}
	   	
	   	   for(int i = 0; i < 10; i++)
	   	   {
	   		  Fst =  GetData(View.getTable(),i,28).toString().trim();
	   		  first = Integer.parseInt(Fst);
	   		    for(int j = i + 1; j < 11; j++)
	   		     {	   			  
		   	       sec = GetData(View.getTable(),j,28).toString().trim();		   		  
		   		   second = Integer.parseInt(sec);
                   String Temp = GetData1(View.getTable(),j,1);
		   		  if(first < second)
		   			    {     				         		   				        
//		   				   SetData(sec,i,28);
//		                   SetData(Fst,j,28);
		   			       temp = first;
		   			       first = second;
		   			       second = temp;		
//		   			       show("First is : " + first); 
		                   SetData(first,i,28);
//		                   show("Second is " + second);
		                   SetData(second,j,28);		                   		                   
		   			     } 
	   		     }
          }		
	   	   for(int i = 0; i < 10; i++){
	   	       temp1 = GetData(View.getTable(),i,28).toString();
	   	       show(temp1);
	   	         {
	   	            for(int j = 0; j < 10; j++){
	   	        	temp2 = TotalMarksArray.get(j);
	   	        	if(temp1.equals(temp2)) SetData(j+1,i,29);
	   	            }
	   	         }
	   	   }
	   	   
	   	   */	   	   	   	   
	 
	private void BtnSetPrinter(){
//	        System.exit(0);
			SetPrinter sp=new SetPrinter();
	        String printername=sp.SelectPrinter();
	        Model.setPrinterName(printername);
	        View.setPrinterLabel(printername);
	    }

	private void BtnCancel(){
	        System.exit(0);
	    }
	    
	private void BtnUpdate(){
		TotalScore();
	}
	
	public void TotalScore(){
	     String marks;
		 int Marks = 0, TotalMarks = 0;
		 for(int i = 0; i < Model.strArray.size()-1; i++){ 	 //   show(strArray.size());
			 for(int j = 4; j < 29; j++){
				marks = GetData1(View.getTable(),i,j);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);             //  show(Marks);
				TotalMarks = TotalMarks + Marks;             //  show(TotalMarks);			 
			  }
			    String TM = String.format("%d", TotalMarks);
			    SetData(TM,i,30);
			    TotalMarks = 0;			 
		 }							
	}

	public int SumU1Score(){
		int row = View.getTable().getSelectedRow();
	     String marks;
		 int Marks = 0, SumOfU1Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 4+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfU1Marks = SumOfU1Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfU1Marks;							
	}

	public int SumT1Score(){
		int row = View.getTable().getSelectedRow();
	     String marks;
		 int Marks = 0, SumOfT1Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 5+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfT1Marks = SumOfT1Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfT1Marks;							
	}

	public int SumU2Score(){
		int row = View.getTable().getSelectedRow();
	     String marks;
		 int Marks = 0, SumOfU2Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 6+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfU2Marks = SumOfU2Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfU2Marks;							
	}

	public int SumT2andEVSScore(){
		int row = View.getTable().getSelectedRow();
	     String marks;
		 int Marks = 0, SumOfT2Marks = 0, EVSandT2Sum = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 7+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);               //   show("Marks = "+Marks);
				SumOfT2Marks = SumOfT2Marks + Marks;           //  show(TotalMarks);			 
			  }
		        int evs = Integer.parseInt(GetData1(View.getTable(),row, 28)); 
			    EVSandT2Sum = SumOfT2Marks + evs;
		return EVSandT2Sum;							
	}

	
	public void  SaveToFile(String fylnem){
		
		 FileWriter fw=null;		
		 try {fw = new FileWriter(fylnem); }    catch (IOException e1){e1.printStackTrace();}
		 String newLine = System.getProperty("line.separator");
		 int rows = View.getTable().getRowCount();
		 String Roll=null, Name = null, Div = null, Marks = null, SerialNo = null, Names = null;
		 int roll =  0, serialNo = 0;
		 	 
		 try { fw.write(Model.strArray.get(0) + newLine); }  catch (IOException e1) {e1.printStackTrace();}
		 for(int i = 0; i < rows-1; i++){		      
			 Name = GetData(View.getTable(), i,3).toString().trim();
			 Name = Name.replaceAll("\\s{2}", "").toUpperCase(); 
			 Names = String.format("%-60s",Name.trim());
			 Div =  GetData(View.getTable(), i,2).toString().trim();
			 SerialNo = GetData1(View.getTable(),i,0).trim();
			 serialNo = Integer.parseInt(SerialNo); 
			 Roll = GetData1(View.getTable(),i,1).trim();
		     roll = Integer.parseInt(Roll); 
		     SubOfStudent(serialNo) ;			 
			 try { fw.write(Roll+"#"+Names); }  catch (IOException e1) {e1.printStackTrace();}
			 for(int k = 4; k < 28; k++){ 
			       Marks = GetData(View.getTable(), i,k).toString().trim();
			       if(Marks == null || Marks.isEmpty()){ continue; }			       
				   String Exam = View.getTable().getColumnName(k);
				 try { fw.write("#" + Div + "=" + Exam + "=" + subArray.get(k/4 -1) + ":" + Marks); }  catch (IOException e1) {e1.printStackTrace();}				 				 
		   } 
			 try { fw.write(newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 
		 }
			 		 
		 try {fw.close();} catch (IOException e1) {e1.printStackTrace();}  
	}
	
	public void SubOfStudent(int roll)
	{ 
		subArray.removeAll(subArray);		
		String SubFromPlate = null;
		String plate[];
		String subfromArray = null ;
	    plate=Model.strArray.get(roll).split("#");
		   for(int j = 0; j < subject.length ; j++)
		   {		                                           //  subject.length = 19 including EVS and PTE 
		     subfromArray= subject[j];
		       for(int k = 2; k < plate.length; k++)
		         {
        		     SubFromPlate = plate[k].substring(5, 8);         // SubFromPlate means Only Three Letter Subject CODE			               
			         if(subfromArray.contains(SubFromPlate))
			           { 		           
			             subArray.add(subfromArray); 
			             break;		        
			           }		        
		         }  
		    }		   		   
	} 		        		
	
	public void Result(){
		
		int Total = 0, TotalXcludeEVS = 0, AVG;
		double Percent;
		int ENG = 0, SLITEL1CS1 = 0, ECOBIOEL2CS2 = 0, SUB1 = 0, SUB2 = 0, SUB3 = 0, SUB4 = 0, EVS = 0 ;
		String eng = null, slitel1cs1 = null, ecobioel2cs2 = null, sub1 = null, sub2 = null, sub3 = null, 
			   sub4 = null, evs = null ;
		String Result1 = "Fail", Result2 = "Promoted", Result3 = "PENDING";		
		
		for(int i = 0; i < 8; i++){
			Show(Sub6());
//			Show(Sub2(i));
//			if( engmarks() <= 33) { SetData (Result1, i, 31) ; }
			
//			else {						
				String GTotal = GetData1(View.getTable(), i, 30);
				Total = Integer.parseInt(GTotal);		
				TotalXcludeEVS = Total - Integer.parseInt(GetData1(View.getTable(), i, 28));
				AVG = TotalXcludeEVS/2;
				AVG = TotalXcludeEVS/2 + Integer.parseInt(GetData1(View.getTable(), i, 28));
				Percent = ( (double) Math.ceil(AVG)/650) * 100;		
				String result = String.format("%.2f", Percent);
				SetData (Math.ceil(Sub6()/2), i, 31);
//			}
		
		}
	}                                  
	public int Sub1(){
		int row = View.getTable().getSelectedRow();
		int TotalOfEng = 0, engtotal = 0;	
			for(int j = 4; j < 8; j++ ){
			String EngTotal = GetData1(View.getTable(), row, j);
			
			if(EngTotal == null || EngTotal.isEmpty()){ EngTotal = "00"; }					 
			if(EngTotal.contentEquals("AB") || EngTotal.contentEquals("AB ")){ EngTotal = "00"; }					 
			
		    engtotal = 	Integer.parseInt(EngTotal);
		    TotalOfEng = TotalOfEng + engtotal;
		    }
		return TotalOfEng;		
	}
	
	public int Sub2(){
		int row = View.getTable().getSelectedRow();
		int TotalOfSLITEL1CS1 = 0, slitel1cs1 = 0;	
			for(int j = 8; j < 12; j++ ){
			String SlItEl1Cs1Total = GetData1(View.getTable(), row, j);
			
			if(SlItEl1Cs1Total == null || SlItEl1Cs1Total.isEmpty()){ SlItEl1Cs1Total = "00"; }					 
			if(SlItEl1Cs1Total.contentEquals("AB") || SlItEl1Cs1Total.contentEquals("AB ")){ SlItEl1Cs1Total = "00"; }					 
			
			slitel1cs1 = 	Integer.parseInt(SlItEl1Cs1Total);
		    TotalOfSLITEL1CS1 = TotalOfSLITEL1CS1 + slitel1cs1;
		    }
		return TotalOfSLITEL1CS1;		
	}
	
	public int Sub3(){
		int row = View.getTable().getSelectedRow();
		int TotalOfSLITEL2CS2 = 0, slitcl2cs2 = 0;	
			for(int j = 12; j < 16; j++ ){
			String SlItEl2Cs2Total = GetData1(View.getTable(), row, j);
			
			if(SlItEl2Cs2Total == null || SlItEl2Cs2Total.isEmpty()){ SlItEl2Cs2Total = "00"; }					 
			if(SlItEl2Cs2Total.contentEquals("AB") || SlItEl2Cs2Total.contentEquals("AB ")){ SlItEl2Cs2Total = "00"; }					 
						
			slitcl2cs2 = 	Integer.parseInt(SlItEl2Cs2Total);
		    TotalOfSLITEL2CS2 = TotalOfSLITEL2CS2 + slitcl2cs2;
		    }
		return TotalOfSLITEL2CS2;		
	}

	public int Sub4(){
		int row = View.getTable().getSelectedRow();
		int TotalOfSub4 = 0, sub4 = 0;	
			for(int j = 16; j < 20; j++ ){
			String Sub4Total = GetData1(View.getTable(), row, j);
			
			if(Sub4Total == null || Sub4Total.isEmpty()){ Sub4Total = "00"; }					 
			if(Sub4Total.contentEquals("AB") || Sub4Total.contentEquals("AB ")){ Sub4Total = "00"; }					 
			
			sub4 = 	Integer.parseInt(Sub4Total);
			TotalOfSub4 = TotalOfSub4 + sub4;
		    }
		return TotalOfSub4;		
	}

	public int Sub5(){
		int row = View.getTable().getSelectedRow();
		int TotalOfSub5 = 0, sub5 = 0;	
			for(int j = 20; j < 24; j++ ){
			String Sub5Total = GetData1(View.getTable(), row, j);
			
			if(Sub5Total == null || Sub5Total.isEmpty()){ Sub5Total = "00"; }					 
			if(Sub5Total.contentEquals("AB") || Sub5Total.contentEquals("AB ")){ Sub5Total = "00"; }					 

			sub5 = 	Integer.parseInt(Sub5Total);
			TotalOfSub5 = TotalOfSub5 + sub5;
		    }
		return TotalOfSub5;		
	}
	
	public int Sub6(){
		int row = View.getTable().getSelectedRow();
		int TotalOfSub6 = 0, sub6 = 0;	
			for(int j = 24; j < 28; j++ ){
			String Sub6Total = GetData1(View.getTable(), row, j);
			
			if(Sub6Total == null || Sub6Total.isEmpty()){ Sub6Total = "00"; }					 
			if(Sub6Total.contentEquals("AB") || Sub6Total.contentEquals("AB ")){ Sub6Total = "00"; }					 

			sub6 = 	Integer.parseInt(Sub6Total);
			TotalOfSub6 = TotalOfSub6 + sub6;
		    }
		return TotalOfSub6;		
	}

	
	public void ENGMarks(){
		  
		 String[] subwithmarks = null;
    	 String plate[];
		 String ENGmarks = null, ENGTotal = null, line;	
		 int EngT2Marks = 0, ENGTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
	     	  plate=Model.strArray.get(i).split("#");

	     	   for (int j = 2; j < plate.length; j++){	
	     		   line = plate[j];
		     	   ENGmarks = plate[j].substring(2, 8);     //   show( submarksU1);
		     	   subwithmarks = line.split(":");
		     		 if(ENGmarks.contains("U1=ENG")){
		     			ENGmarks = subwithmarks[1];	
		     			SetData(ENGmarks, i-1,4);
		     			}	                         
		     		 
		     		if(ENGmarks.contains("T1=ENG")){
		     		   ENGmarks = subwithmarks[1];
		     		   SetData(subwithmarks[1], i-1,5);
		     		   }	
		     		
		     		if(ENGmarks.contains("U2=ENG")){
		     			ENGmarks = subwithmarks[1];
		     			SetData(subwithmarks[1], i-1,6);
		     			}
		     		
		     		if(ENGmarks.contains("T2=ENG")){
		     			ENGmarks = subwithmarks[1];
		     			if(ENGmarks == null || ENGmarks.isEmpty()){ ENGmarks = "00"; }					 
						if(ENGmarks.contentEquals("AB") || ENGmarks.contentEquals("AB ")){ ENGmarks = "00"; }
		     			EngT2Marks = Integer.parseInt(ENGmarks); //  show(EngT2Marks);	
		     			ENGTotalT2 =  ENGTotalT2 + EngT2Marks;   
		     			ENGTotal = Integer.toString(ENGTotalT2);	
		     			SetData(ENGTotal, i-1,7); 
		     			}			     		
    	         }   		
	     	         ENGTotalT2 = 0;
        }		
     	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
    	 th.repaint();                                          
     	 View.Eng.text="ENG";                       
	}     
		
	public void SecLangMarks(){
		  
		 String[] subwithmarks = null;
	   	 String plate[];
		 String SLITmarks = null, MARTotal = null, ITETotal = null, HINTotal = null, TAMTotal = null, line;		   	
		 int MART2marks = 0, MARTotalT2 = 0, ITET2marks = 0, ITETotalT2 = 0, HINT2marks = 0, HINTotalT2 = 0,
		     TAMT2marks = 0, TAMTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		    line = plate[j];
	     		    subwithmarks = line.split(":");
			    	SLITmarks = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(SLITmarks.contains("U1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
		     		if(SLITmarks.contains("U1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	
		     		if(SLITmarks.contains("U1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}
		     		
		     		if(SLITmarks.contains("T1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("T1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	
		     		if(SLITmarks.contains("T1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		if(SLITmarks.contains("T1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		
		     		if(SLITmarks.contains("U2=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U2=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
		     		if(SLITmarks.contains("U2=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("U2=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						   MART2marks = Integer.parseInt(SLITmarks);  // show(SLITT2marks);	
						   MARTotalT2 =  MARTotalT2 + MART2marks; 
						   MARTotal = Integer.toString(MARTotalT2);
		     			   SetData(MARTotal, i-1, 11);                 		     			
		     			 }		     		
		     		 if(SLITmarks.contains("T2=ITE")){
			     		SLITmarks = subwithmarks[1];	
			     		if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						ITET2marks = Integer.parseInt(SLITmarks);    //      show(ITET2marks);
			     		ITETotalT2 = ITETotalT2 + ITET2marks; 
			     		ITETotal = Integer.toString(ITETotalT2);			     			     		
			     		SetData(ITETotal, i-1, 11);                 		     			
			     		}	
		     		if(SLITmarks.contains("T2=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						HINT2marks = Integer.parseInt(SLITmarks);    //      show(HINT2marks);
						HINTotalT2 = HINTotalT2 + HINT2marks; 
						HINTotal = Integer.toString(HINTotalT2);		     			
		     			SetData(HINTotal, i-1, 11);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						TAMT2marks = Integer.parseInt(SLITmarks);   //        show(TAMT2marks);
						TAMTotalT2 = TAMTotalT2 + TAMT2marks; 
						TAMTotal = Integer.toString(TAMTotalT2);			     			
		     			SetData(TAMTotal, i-1, 11);                 		     			
		     			}	
	     	   }
	     	        MARTotalT2 = 0; ITETotalT2 = 0; HINTotalT2 = 0; TAMTotalT2 = 0;
	     	}
   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	 th.repaint();                                          
 	 View.SL.text = "SL / ITE / EL1 / CS1";
   	    	 
	}
	
	public void VocationalMarks(){
		  
			 String[] subwithmarks = null;
		   	 String plate[];
			 String VocationalMarks = null, EL1Total = null, CS1Total = null, EL2Total = null, CS2Total = null, line;	
			 int EL1T2marks = 0, EL1TotalT2 = 0, CS1T2marks = 0, CS1TotalT2 = 0,
			     EL2T2marks = 0, EL2TotalT2 = 0, CS2T2marks = 0, CS2TotalT2 = 0;
			 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
		     	{
				   plate=Model.strArray.get(i).split("#");
				   for (int j = 2; j < plate.length; j++){	
					 
					  line = plate[j];
				      VocationalMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				      subwithmarks = line.split(":");
					   if(VocationalMarks.contains("U1=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							EL1T2marks = Integer.parseInt(VocationalMarks);  //         show(EL1T2marks);
							EL1TotalT2 = EL1TotalT2 + EL1T2marks; 
							EL1Total = Integer.toString(EL1TotalT2);	
							SetData(EL1Total, i-1, 11);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							CS1T2marks = Integer.parseInt(VocationalMarks);   //       show(CS1T2marks);
							CS1TotalT2 = CS1TotalT2 + CS1T2marks; 
							CS1Total = Integer.toString(CS1TotalT2);				     						     			
			     			SetData(CS1Total, i-1, 11);                 		     			
			     			}	 
			     		
			     		if(VocationalMarks.contains("U1=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							EL2T2marks = Integer.parseInt(VocationalMarks);   //       show(EL2T2marks);
							EL2TotalT2 = EL2TotalT2 + EL2T2marks; 
							EL2Total = Integer.toString(EL2TotalT2);				     						     						     			
			     			SetData(EL2Total, i-1, 15);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							CS2T2marks = Integer.parseInt(VocationalMarks);   //  show(CS2T2marks);
							CS2TotalT2 = CS2TotalT2 + CS2T2marks; 
							CS2Total = Integer.toString(CS2TotalT2);					     						     			
			     			SetData(CS2Total, i-1, 15);                 		     			
			     			}			     					     		
				   }
				   EL1TotalT2 = 0;	CS1TotalT2 = 0;	EL2TotalT2 = 0;	CS2TotalT2 = 0; 				 
		     }
			 
		   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
			 th.repaint();                                          
	}
	
	public void BIOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BIOMarks = null, BIOTotal = null, line;	
		 int BIOT2marks = 0, BIOTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		   
					  line = plate[j];
					  subwithmarks = line.split(":");
				      BIOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(BIOMarks.contains("U1=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(BIOMarks.contains("T1=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(BIOMarks.contains("U2=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(BIOMarks.contains("T2=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			if(BIOMarks == null || BIOMarks.isEmpty()){ BIOMarks = "00"; }					 
							if(BIOMarks.contentEquals("AB") || BIOMarks.contentEquals("AB ")){ BIOMarks = "00"; }
							BIOT2marks = Integer.parseInt(BIOMarks);        //    show(BIOT2marks);
							BIOTotalT2 = BIOTotalT2 + BIOT2marks; 
							BIOTotal = Integer.toString(BIOTotalT2);				     						     			
			     		    SetData(BIOTotal, i-1, 15);
	     	           }
	     	}
	     	  BIOTotalT2 = 0;
	    }		
	   	 JTableHeader th = View.getTable().getTableHeader();     
		 th.repaint();                                          
	}
	
	public void ECOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String ECOMarks = null, ECOTotal = null,line;	
		 int ECOT2marks = 0, ECOTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  ECOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(ECOMarks.contains("U1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 12);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 13);                 		     			
			     	 }	 
					if(ECOMarks.contains("U2=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 14);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T2=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	  if(ECOMarks == null || ECOMarks.isEmpty()){ ECOMarks = "00"; }					 
						if(ECOMarks.contentEquals("AB") || ECOMarks.contentEquals("AB ")){ ECOMarks = "00"; }
						ECOT2marks = Integer.parseInt(ECOMarks);       //   show(ECOT2marks);
						ECOTotalT2 = ECOTotalT2 + ECOT2marks; 
						ECOTotal = Integer.toString(ECOTotalT2);				     	   			     	   
			           SetData(ECOTotal, i-1, 15);
	         	     }
	     	    }
	     	  ECOTotalT2 = 0;
	     	}
	     	   JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
				 th.repaint();                                 
				 View.Sub1.text = "ECO / BIO / EL2 / CS2";
	}
	

	public void BKEMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BKEMarks = null, BKETotal = null, line;	
		 int BKET2marks = 0, BKETotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  BKEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(BKEMarks.contains("U1=BKE")){			     			
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 16);                 		     			
			     	 }	
			     	if(BKEMarks.contains("T1=BKE")){
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 17);                 		     			
			     	 }	 
					if(BKEMarks.contains("U2=BKE")){
  	     			   BKEMarks = subwithmarks[1];	
      	     		   SetData(subwithmarks[1], i-1, 18);                 		     			
			         }	
			     	if(BKEMarks.contains("T2=BKE")){
			     	   BKEMarks = subwithmarks[1];	
			     	if(BKEMarks == null || BKEMarks.isEmpty()){ BKEMarks = "00"; }					 
					if(BKEMarks.contentEquals("AB") || BKEMarks.contentEquals("AB ")){ BKEMarks = "00"; }
					   BKET2marks = Integer.parseInt(BKEMarks);         //   show(BKET2marks);
					   BKETotalT2 = BKETotalT2 + BKET2marks; 
					   BKETotal = Integer.toString(BKETotalT2);				     	   			     	   
			     	   SetData(BKETotal, i-1, 19);
	         	     }
	     	     }
	     	  BKETotalT2 = 0;
	       	}
	   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//		 View.Sub2.text = "BKE / PHY";
 	}
		 
    public void PHYMarks(){
				 String[] subwithmarks = null;
			   	 String plate[];
				 String PHYMarks = null,PHYTotal = null,line;
				 int PHYT2marks = 0, PHYTotalT2 = 0;
				 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
			     	{
					 plate=Model.strArray.get(i).split("#");
//			     	  show(plate);
			     	   for (int j = 2; j < plate.length; j++){				     		   
						 line = plate[j];
						 subwithmarks = line.split(":");
						 PHYMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
						   if(PHYMarks.contains("U1=PHY")){					     			
					     	  PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 16);                 		     			
					     	}	
					       if(PHYMarks.contains("T1=PHY")){
					          PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 17);                 		     			
					     	}	 
						   if(PHYMarks.contains("U2=PHY")){
					          PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 18);                 		     			
					     	}	
					       if(PHYMarks.contains("T2=PHY")){
					     	  PHYMarks = subwithmarks[1];	
					       if(PHYMarks == null || PHYMarks.isEmpty()){ PHYMarks = "00"; }					 
						   if(PHYMarks.contentEquals("AB") || PHYMarks.contentEquals("AB ")){ PHYMarks = "00"; }
							  PHYT2marks = Integer.parseInt(PHYMarks);     //       show(PHYT2marks);
							  PHYTotalT2 = PHYTotalT2 + PHYT2marks; 
							  PHYTotal = Integer.toString(PHYTotalT2);							     	  					     	  
					     	  SetData(PHYTotal, i-1, 19);
			         	    }
		     	      }
			     	  PHYTotalT2 = 0;
   	    }
				 
		 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
		 View.Sub2.text = "BKE / PHY";
	}

	public void OCMMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String OCMMarks = null,OCMTotal = null, line;	
		 int OCMT2marks = 0, OCMTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){		     		   
                 line = plate[j];
                 subwithmarks = line.split(":");
				 OCMMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				 if(OCMMarks.contains("U1=OCM")){			     		
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 20);                 		     			
			      }	
			     if(OCMMarks.contains("T1=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 21);                 		     			
			      }	 
				 if(OCMMarks.contains("U2=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 22);                 		     			
			      }	
			     if(OCMMarks.contains("T2=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     if(OCMMarks == null || OCMMarks.isEmpty()){ OCMMarks = "00"; }					 
				 if(OCMMarks.contentEquals("AB") || OCMMarks.contentEquals("AB ")){ OCMMarks = "00"; }
					OCMT2marks = Integer.parseInt(OCMMarks);        //    show(OCMT2marks);
					OCMTotalT2 = OCMTotalT2 + OCMT2marks; 
					OCMTotal = Integer.toString(OCMTotalT2);					     				     	
			     	SetData(OCMTotal, i-1, 23);
	         	 }
	   	     }
	     	  OCMTotalT2 = 0;
	 }
		 
	  JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	  th.repaint();                                          
  	}

	
	public void CHEMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String CHEMarks = null,CHETotal = null, line;	
		 int CHET2marks = 0, CHETotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	       {
			plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		   
				line = plate[j];
				subwithmarks = line.split(":");
				CHEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				  if(CHEMarks.contains("U1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 20);                 		     			
			   		}	
			      if(CHEMarks.contains("T1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 21);                 		     			
			     	}	 
				  if(CHEMarks.contains("U2=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 22);                 		     			
			        }	
			      if(CHEMarks.contains("T2=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			      if(CHEMarks == null || CHEMarks.isEmpty()){ CHEMarks = "00"; }					 
				  if(CHEMarks.contentEquals("AB") || CHEMarks.contentEquals("AB ")){ CHEMarks = "00"; }
				     CHET2marks = Integer.parseInt(CHEMarks);        //    show(CHET2marks);
					 CHETotalT2 = CHETotalT2 + CHET2marks; 
					 CHETotal = Integer.toString(CHETotalT2);		 			     	 			     	 
			     	 SetData(CHETotal, i-1, 23);
	         	   }
	     	   }
	     	        CHETotalT2 = 0;
	       	}
		 
	   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
		 View.Sub3.text = "OCM / CHE";
 	}  
	
	public void MATMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String MATMarks = null,MATTotal = null, line;
		 int MATT2marks = 0, MATTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){	   		   
			     line = plate[j];
			     subwithmarks = line.split(":");
				 MATMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			   if(MATMarks.contains("U1=MAT")){			        			
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 24);                 		     			
			     }	
			   if(MATMarks.contains("T1=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 25);                 		     			
			     }	 
			   if(MATMarks.contains("U2=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 26);                 		     			
			     }	
			   if(MATMarks.contains("T2=MAT")){
			      MATMarks = subwithmarks[1];
			   if(MATMarks == null || MATMarks.isEmpty()){ MATMarks = "00"; }					 
			   if(MATMarks.contentEquals("AB") || MATMarks.contentEquals("AB ")){ MATMarks = "00"; }
				  MATT2marks = Integer.parseInt(MATMarks);     //       show(MATT2marks);
				  MATTotalT2 = MATTotalT2 + MATT2marks; 
				  MATTotal = Integer.toString(MATTotalT2);				      			      			      
			      SetData(MATTotal, i-1, 27);
	         	 }
	       }
	     	  MATTotalT2 = 0;
	    }
	   
		 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          

 	}

	public void SEPMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String SEPMarks = null,SEPTotal = null, line;	
		 int SEPT2marks = 0, SEPTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  SEPMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			  if(SEPMarks.contains("U1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(subwithmarks[1], i-1, 24);                 		     			
			    }	
			  if(SEPMarks.contains("T1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(subwithmarks[1], i-1, 25);                 		     			
			    }	 
			 if(SEPMarks.contains("U2=SEP")){
			    SEPMarks = subwithmarks[1];	
			    SetData(subwithmarks[1], i-1, 26);                 		     			
			   }	
			 if(SEPMarks.contains("T2=SEP")){
			    SEPMarks = subwithmarks[1];	
			 if(SEPMarks == null || SEPMarks.isEmpty()){ SEPMarks = "00"; }					 
			 if(SEPMarks.contentEquals("AB") || SEPMarks.contentEquals("AB ")){ SEPMarks = "00"; }
				SEPT2marks = Integer.parseInt(SEPMarks);      //      show(SEPT2marks);
				SEPTotalT2 = SEPTotalT2 + SEPT2marks; 
				SEPTotal = Integer.toString(SEPTotalT2);	    			    			    			    
			    SetData(SEPTotal, i-1, 27);
	           }
	       }
	     	 SEPTotalT2 = 0;
	 }
		 
	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	 th.repaint();                                           
	 View.Sub4.text = "MAT / SEP";
}

	public void EVSMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String EVSMarks = null,line;	
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  EVSMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(EVSMarks.contains("T2=EVS")){
				 EVSMarks = subwithmarks[1];	
			 if(EVSMarks == null || EVSMarks.isEmpty()){ EVSMarks = "00"; }					 
			 if(EVSMarks.contentEquals("AB") || EVSMarks.contentEquals("AB ")){ EVSMarks = "00"; }
			 SetData(EVSMarks, i-1, 28);
	           }
	       }
	 }
}

	public void PTEGrade(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String PTEGrade = null,line;	
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  PTEGrade = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(PTEGrade.contains("T2=PTE")){
				 PTEGrade = subwithmarks[1];	
			 if(PTEGrade == null || PTEGrade.isEmpty()){ PTEGrade = "00"; }					 
			 if(PTEGrade.contentEquals("AB") || PTEGrade.contentEquals("AB ")){ PTEGrade = "00"; }
			    SetData(PTEGrade, i-1, 29);
	           }
	       }
	 }
}

	public ArrayList<String> collheaderfinder(String RollNo){
		
		String plate[];
      	String rollno;
      	boolean found = false;
//		String RollNo = View.Search().getText();
		int Rows = View.getTable().getRowCount();
		String subject = null;
				
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);		    

			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    
	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
    		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
    		   	        th.repaint();      
    		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
		 }
		 headerArray.removeAll(headerArray);
		 headerArray.add(View.Eng.text);headerArray.add(View.SL.text);headerArray.add(View.Sub1.text);
		 headerArray.add(View.Sub2.text);headerArray.add(View.Sub3.text);headerArray.add(View.Sub4.text);
		return headerArray;

		
	}
	
	public String Streamfinder(String Rollno){		
		String Science = "SCIENCE", Commerce = "COMMERCE";
		String plate[];
      	String rollno;
      	int Rows = View.getTable().getRowCount();
		String subject = null;
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
 			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(Rollno.trim().equals(rollno.trim())){    		    	     		    	     		  
	     		    if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		  }
	     	   }
		 }
 //       show(View.Sub2.text);        
		if(View.Sub2.text.equals("PHY")) {return Science;}
		else return Commerce;		
	}
	
	
	public void SearchByRollNo(String RollNo){
		
		String plate[];
      	String rollno;
      	boolean found = false;
//		String RollNo = View.Search().getText();
		int Rows = View.getTable().getRowCount();
		String subject = null;
				
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);		    
			  
			if(RollNo.trim().equals(SameRoll.trim())){		//	show(rollno);		
			found = true;
			// this will automatically set the view of the scroll in the location of the value
			View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
           // this will automatically set the focus of the searched/selected row/value
			View.getTable().setRowSelectionInterval(row, row);	
				 for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
	
					 View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	             }					
			}		

			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    
	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
    		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
    		   	        th.repaint();      
    		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
		 }
	}
	
	private class HighlightRenderer extends DefaultTableCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	table = View.getTable();
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
	   {  int Rows = View.getTable().getRowCount();
		  int cols = View.getTable().getColumnCount();
		 for (int i = 0; i < Rows; i++)
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
