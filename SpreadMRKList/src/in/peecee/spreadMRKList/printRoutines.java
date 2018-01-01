package in.peecee.spreadMRKList;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Calendar;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class printRoutines {
	SpreadMRKListController controller;
	private SpreadMRKListView View;
	public void SetData1(JTable table,Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }
	public String GetData1(JTable table, int row_index, int col_index) {  return (String) View.getTable().getValueAt(row_index, col_index); }
	public Object GetData(JTable table, int row_index, int col_index) {  return View.getTable().getValueAt(row_index, col_index); }
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
	
	 String Exams[] = {"U1", "T1", "U2", "T2"};
	 String subjects[] = { "ENG", "SL/ITE/EL1/CS1", "ECO/BIO/EL2/CS2", "BKE / PHY", "OCM / CHE", "MAT / SEP"};
	 String Names = null;
	 
    public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> subMarksArray = new ArrayList<String>();
	public  ArrayList<String> headerArray = new ArrayList<String>();
	public  ArrayList<String> StuDetailsArray = new ArrayList<String>();

	
	public printRoutines() {
		// TODO Auto-generated constructor stub
		

	}
	public void SpreadSheet(){		
	  try {
	    PrinterJob pjob = PrinterJob.getPrinterJob();
		pjob.setJobName("Spread Sheet Print");
		pjob.setCopies(1);
		pjob.setPrintable(new Printable() {
	  public int print(Graphics pg, PageFormat pf, int pageNum) {
		int totalpages = 0;
		if (pageNum > totalpages) // we only print one page
		return Printable.NO_SUCH_PAGE; // ie., end of job
		Font newFont;		          
		newFont = new Font("Liberation Serif", Font.PLAIN, 13);
//		FontMetrics metrics = pg.getFontMetrics(newFont);
		int LtMrg = 40;       
		int BtMrg = 820;		        

		for(int j = 0; j < 6; j++){		  			 
		pg.drawRect(50, 32+j*80, 17, 80);        // Printing Subject Headings
		}      
				          
		for(int j = 0; j < 29; j++){
		  for(int i = 0; i < 24; i++){	
		    pg.drawRect(67 + j*17, 32+i*20, 17, 20);        // Printing Rectangular grid ( Body of Table for Marks )
		    }
		}
						
		for(int j = 0; j < 30; j++){				   			 
		pg.drawRect(50 + j*17, 512, 17, 228);              // Printing Names
		} 								
							
		for(int j = 0; j < 30; j++){
		  for(int i = 0; i < 2; i++){	
			pg.drawRect(50 + j*17, 775-i*35, 17, 35);        // Printing Serial No and Roll Numbers
			}
		}		          		          
//		int y = 585;                                        // Right margin indent.
		pg.drawString("( FOR OFFICE USE ONLY )", 230, LtMrg);
		pg.drawString("( FOR OFFICE USE ONLY )", 230, BtMrg);	
		
		Graphics g2 = (Graphics2D) pg;
        Font font = new Font(null, Font.PLAIN, 9);    
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(270), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        g2.drawString("Sr. No.",62,807);
        g2.drawString("Roll No",62,774);
        g2.drawString("NAME",62,660);
        g2.drawString("ENG",62,480);
        g2.drawString("98",95,487);
        g2.drawString("100",95,510);
        int j = 1;
        for(int i = 0; i < 2; i++){
            g2.drawString(subjects[j],62,428 - i*80);
            j++;
        }

        j = 3;
        for(int i = 0; i < 3; i++){
            g2.drawString(subjects[j],62,255 - i*80);
            j++;
        }
        int k = 0;
        for(int i = 0; i < 6; i++){
          for(int m = 0; m < 4; m++){
              g2.drawString(Exams[m], 79, (507-m*20)-i*80);
//              k++;        	          	  
          }
        }
        
        for( int i = 0; i <28; i++){
        	g2.drawString(Integer.toString(i+1),96+i*17,807);
        	
//        	pg.drawString(Integer.toString(i+1),96+i*17,770);
        }

        for( int i = 0; i <25; i++){
            String Roll = GetData1(View.getTable(), i, 3);
        	pg.drawString(Roll, 96+i*17, 807);

        }

        g2.dispose();
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
	
	
	   private void BtnPrintCurrent(){			

//		   SCButtons.showScoreButtons(); 
		   final ArrayList<String> subject;
		    int row = View.getTable().getSelectedRow();
		    subMarksArray.removeAll(subMarksArray);   
		   	for(int k = 4; k < 30 ; k++){	   		
		   		subMarksArray.add((String) GetData1(View.getTable(),row,k));
		   	}
		   	
		   	StuDetailsArray.removeAll(StuDetailsArray);
		   	for(int k = 1; k < 4 ; k++){	   		
		   		StuDetailsArray.add((String) GetData1(View.getTable(),row,k));
		   	}
		   	
			  final String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
//			  ArrayList<String> subject;		  
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
				  
			for(int j = 0; j < 11; j++){
			  for(int i = 0; i < 8; i++){	
					  pg.drawRect(160 + j*35, 300+i*20, 35, 20);        // Printing Body of Marks Sheets
			  }   
			}   
				
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
			pg.drawString(subMarksArray.get(24), 450, 395);               //  EVS marks
			pg.drawString(subMarksArray.get(25), 485, 395);               //  PTE Grade
			int row = View.getTable().getSelectedRow();        
			pg.drawString(String.valueOf(SumU1Score()), 515, 335);        // Sum of all Unit 1 Exams
			pg.drawString(String.valueOf(SumT1Score()), 515, 355);        // Sum of all Term 1 Exams
			pg.drawString(String.valueOf(SumU2Score()), 515, 375);        // Sum of all Unit 2 Exams
			pg.drawString(String.valueOf(SumT2andEVSScore()), 515, 395);  // Sum of all Term 2 Exams
			
	        pg.drawString(String.valueOf(Sub1()), 240, 415);              // Sum of all marks English Subject
	        pg.drawString(String.valueOf(Sub2()), 275, 415);              // Sum of all marks SL or Tech Subject
	        pg.drawString(String.valueOf(Sub3()), 308, 415);              // Sum of all marks SL or Tech2 Subject
	        pg.drawString(String.valueOf(Sub4()), 341, 415);              // Sum of all marks SL or Sub4 Subject
	        pg.drawString(String.valueOf(Sub5()), 378, 415);              // Sum of all marks SL or Sub5 Subject
	        pg.drawString(String.valueOf(Sub6()), 412, 415);              // Sum of all marks SL or Sub6 Subject
	        pg.drawString(GetData1(View.getTable(),row,28), 450, 415);    // Marks of EVS Subject
	        
			pg.drawString(String.valueOf(SumU1Score()+SumT1Score()+SumU2Score()+SumT2andEVSScore() ), 514, 415);  // Sum of all
	        
		    				
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);

			pg.drawString("Mark Sheet showing the number of marks Obtained by  ", 80, 200);
			pg.drawString(StuDetailsArray.get(2), 80, 220);   //  Name of student 
			pg.drawString("with Roll No. : "+ StuDetailsArray.get(0)+" of Division : "
			              +StuDetailsArray.get(1)+", in " + Streamfinder(RollNo)+ " stream", 80, 240);

			pg.drawString("The following table shows each head of passing at FYJC examintion conducted", 80, 260);
			pg.drawString("during the academic year " + year, 80, 280);				
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
	
	
	public void PrintAllMarksCards(){
	  try {
		    PrinterJob pjob = PrinterJob.getPrinterJob();
		    pjob.setJobName("All Marks Card - Print");
		    pjob.setCopies(1);
		    pjob.setPrintable(new Printable() {
		    public int print(Graphics pg, PageFormat pf, int pageNum) {
		    int totalpages = 0;
		    if (pageNum > totalpages) // we only print one page
		    return Printable.NO_SUCH_PAGE; // ie., end of job
		    Font newFont;		          
		    newFont = new Font("Liberation Serif", Font.PLAIN, 13);
//		    FontMetrics metrics = pg.getFontMetrics(newFont);
		    int LtMrg = 40;       // Left Top x, Left Top y and Left Margin
		    int BtMrg = 820;
		          		          		          		          
		for(int i = 0; i < 8; i++){	
			pg.drawRect(80, 300+i*20, 80, 20);        // Printing LEFT Two columns grid
		}					  				          
		
		for(int j = 0; j < 2; j++){
		    pg.drawRect(230 + j*87, 460, 88, 20);        // Printing Bottom Rectangles					
		}		          		          
				  
		for(int j = 0; j < 11; j++){
			for(int i = 0; i < 8; i++){	
				pg.drawRect(160 + j*35, 300+i*20, 35, 20);        // Printing Body of Marks Sheet
			}
		}
//		          int y = 585;                                        // Right margin indent.
		pg.drawString("( FOR OFFICE USE ONLY )", 230, LtMrg);
		pg.drawString("( FOR OFFICE USE ONLY )", 230, BtMrg);
		          				          
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
	
	public ArrayList<String> collheaderfinder(String RollNo){
		
		String plate[];
      	String rollno;
      	boolean found = false;
//		String RollNo = View.Search().getText();
		int Rows = View.getTable().getRowCount();
		String subject = null;
				
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=strArray.get(row+1).split("#");
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
	public String Streamfinder(String Rollno){		
		String Science = "SCIENCE", Commerce = "COMMERCE";
		String plate[];
      	String rollno;
      	int Rows = View.getTable().getRowCount();
		String subject = null;
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=strArray.get(row+1).split("#");
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

	
}