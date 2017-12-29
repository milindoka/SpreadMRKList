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

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;
import javax.swing.JTable;

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
	
	
	public void PrintCurrent(){
	 try {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		pjob.setJobName("Current Marks Card - Print");
		pjob.setCopies(1);
		pjob.setPrintable(new Printable() {
		public int print(Graphics pg, PageFormat pf, int pageNum) {
		int totalpages = 0;
		  if (pageNum > totalpages) // we only print one page
		     return Printable.NO_SUCH_PAGE; // ie., end of job
		     Font newFont;		          
		     newFont = new Font("Liberation Serif", Font.PLAIN, 13);
//		     FontMetrics metrics = pg.getFontMetrics(newFont);
		     int LtMrg = 40;       // Left Top x, Left Top y and Left Margin
		     int BtMrg = 750;
		        		          		          		          
		for(int i = 0; i < 8; i++){	
			pg.drawRect(80, 300+i*20, 80, 20);        // Printing LEFT Two columns grid
		}
					  				          
		for(int j = 0; j < 2; j++){
		    pg.drawRect(230 + j*87, 460, 88, 20);        // Printing Bottom Rectangles					
		    pg.drawString("RESULT", 250, 475);
		}		          		          
				  
		for(int j = 0; j < 11; j++){
		  for(int i = 0; i < 8; i++){	
			  pg.drawRect(160 + j*35, 300+i*20, 35, 20);        // Printing Body of Marks Sheet
			  pg.drawString("Examination", 82, 315);
			  pg.drawString("Unit Test I", 90, 335);
			  pg.drawString("Terminal I", 90, 355);
			  pg.drawString("Unit Test II", 90, 375);
			  pg.drawString("Terminal II", 90, 395);
			  pg.drawString("Aaggregate", 85, 415);
			  pg.drawString("Average", 95, 435);
			  pg.drawString("Grace", 103, 455);
			  
			  pg.drawString("Max", 165, 315);
			  pg.drawString("Min", 201, 315);
			  pg.drawString("SUB", 235, 315);
			  pg.drawString("SUB", 270, 315);
			  pg.drawString("SUB", 305, 315);
			  pg.drawString("SUB", 340, 315);
			  pg.drawString("SUB", 375, 315);
			  pg.drawString("SUB", 410, 315);
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
			  
			  for(int k = 0; k < 4; k++)
			  {
				  pg.drawString("-----", 200, 335+k*20);			
			  }

			  for(int k = 0; k < 2; k++)
			  {
				  pg.drawString("-----", 165, 415+k*20);			
			  }
			  pg.drawString("-----", 200, 455);
			  
		  }
		}
//		int y = 585;                                        // Right margin indent.
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
}