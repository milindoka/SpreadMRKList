
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;

public class BillPrint 
{
	////Basic 2 Functions For MVC Design---------------------------------------------------------------
	private static BillPrint instance = null;  /* ===== singleton details ===== */
	protected BillPrint() 
	{/* nothing needed, but this prevents a public no-arg  constructor from being created automatically */}
	public static BillPrint getInstance()
	{ if (instance==null)	{ instance = new BillPrint();} 	return instance;}
      //------------------------------------------------------------------------------------------------
    int StudCount;
	private String ExaminerName;
    private String Subject;
    private String Year;
    private String IndexNoOfJrCollege;
    private String CenterOfExam;
    private String TotRem;
    private String NumberOfDaysOFExam;
    private String NumOfStudentsExamined;
    private String RemPerStudent;
    private String CollNemOfExaminer;
    private String ColIndCdOfExmner;
    private String StartDate;
    private String EndDate;
    private String ExceptOn;
    
    private int NumOfStudents = 0;
    private float RemunerationPerStudent = 0.0f;
    private float TotalRemuneration = 0.0f;		
    String  Addline1, Addline2, Addline3, Addline4;  

    public void setDetails ( String ExaminerName, String Subject, String Year, String IndexNoOfJrCollege,
    	                     String CenterOfExam, String NumberOfDaysOFExam,
    	                     String NumOfStudentsExamined, String RemPerStudent, String CollNemOfExaminer,
    	                     String ColIndCdOfExmner, String Addline1, String Addline2, String Addline3,
    	                     String Addline4, String StartDate, String EndDate, String ExceptOn)
     {this.ExaminerName = ExaminerName; this.Subject = Subject; this.Year = Year;
      this.IndexNoOfJrCollege = IndexNoOfJrCollege; this.CenterOfExam = CenterOfExam;
      this.NumberOfDaysOFExam = NumberOfDaysOFExam;
      this.NumOfStudentsExamined = NumOfStudentsExamined; this.RemPerStudent = RemPerStudent;
      this.CollNemOfExaminer = CollNemOfExaminer; this.ColIndCdOfExmner = ColIndCdOfExmner;
      this.Addline1 = Addline1; this.Addline2 = Addline2; this.Addline3 = Addline3; this.Addline4 = Addline4;
      this.StartDate = StartDate; this.EndDate = EndDate; this.ExceptOn = ExceptOn;
     }
    
    public void Show(float msg) {JOptionPane.showMessageDialog(null, msg);}
    public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}
    public static void SHOW(String msg) {JOptionPane.showMessageDialog(null, msg);}
  	
    public void Print()
    {
    	 try {
    	      PrinterJob pjob = PrinterJob.getPrinterJob();
    	      pjob.setJobName("Remuneration Bill Printout");
    	      pjob.setCopies(1);
    	      pjob.setPrintable(new Printable() {
    	        public int print(Graphics pg, PageFormat pf, int pageNum) {
    	          if (pageNum > 0) // we only print one page
    	            return Printable.NO_SUCH_PAGE; // ie., end of job
    	          Font newFont;
    	          newFont = new Font("Liberation Serif", Font.BOLD, 12);
    	          FontMetrics metrics = pg.getFontMetrics(newFont);
    	          
//	   				pg.drawString("A", 72,  81);
//	   				pg.drawString("A", 514, 81);
//	   				pg.drawString("A", 72, 770);
//	   				pg.drawString("A", 514,770);	   		        	  
// 		          int NOTs =  NumberOfTeachers();
    	          
    	          int LtMrg = 60;       // Left Top x, Left Top y and Left Margin
    	          int RTx = 600; int RTy = 10;  int RtMrg = 34;       // Right Top x, Right Top y and Right Margin
    	          int RBL = RTx - RtMrg ;                             // Right Border Line
    	          int x = 60;                                         // Left margin indent.
                  int Center = (RBL - x)/2 + x ;
    	          
    	          pg.drawString("C-2/P2-13", RBL - LtMrg, 4*RTy);
//    	          pg.drawRect(0, 0, 612, 790);                        // Full Screen Dimension
    	          pg.drawRect(503, 28, 63, 15); if(TotalRemuneration < 50.00) TotalRemuneration = (float) 50.00;
    	          pg.drawRect(90, 584, 65, 65);                       // coordinates of left top corner and width and height
   
    	          String S1 = "MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION";
    	          String S2 = "MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI  400703";
    	          
    	          pg.setFont(newFont);
    
    	          pg.drawString(S1,  Center - (int) (metrics.stringWidth(S1))/2 , 60);
  
    	          pg.drawString(S2, Center - (int) (metrics.stringWidth(S2))/2 , 77 );
    	          
    	          newFont = new Font("Liberation Serif", Font.BOLD, 12);
    	          pg.setFont(newFont);
    	          String S3 = "H.S.C. PRACTICAL EXAMINATION FEBRUARY/OCTOBER";     // String width = 333
    	          
    	          int YrSp = (Center - (int) (metrics.stringWidth(S3))/2) + metrics.stringWidth(S3) ;
    	          
    	          pg.drawString(Year,YrSp + 10,95);
//    	          pg.drawString(Year,480,95);
//    	          show(metrics.stringWidth(S3));
//    	          show(metrics.stringWidth("H.S.C. PRACTICAL EXAMINATION FEBRUARY/OCTOBER"));
    	          String S4 = "BILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER";
    	          pg.drawString(S3, Center - (int) (metrics.stringWidth(S3))/2 , 95 );
 //   	          pg.drawString(S3, 125, 95);
 //   	          pg.drawString(S4, 158, 114);
    	          pg.drawString(S4, Center - (int) (metrics.stringWidth(S4))/2 , 114 );
  //  	          show(Center - (int) (metrics.stringWidth(S3))/2);
 //   	          pg.drawLine(RTx/2, 30, RTx/2, 180);
    
       	          newFont = new Font("Liberation Serif", Font.PLAIN, 13);
    	          pg.setFont(newFont);
    	      
    	          String address[] = {"The Divisional Secretary,","Maharashtra State Board of Secondary","& Higher Secondary Education",
    	        		              "Mumbai Divisional Board,", "Vashi, Navi Mumbai  400703"};
    	          int k = 140;
    	          for( int i = 0; i < address.length; i++)
    	          {
    	            pg.drawString( address[i], x, k);
    	            k = k+15;
    	          }
    	          
    	          pg.drawString("Name Shri/Smt/Miss", 60, 220);
//    	          int Strlen1 = metrics.stringWidth("Name Shri/Smt/Kum");
//    	          show(Strlen1);
 //   	          show(metrics.stringWidth("Name Shri/Smt/Kum"));
    	          pg.drawString(ExaminerName.toUpperCase(), x + metrics.stringWidth("Name Shri/Smt/Kum") + 3, 220);
    	          pg.drawLine(x + metrics.stringWidth("Name Shri/Smt/Kum") + 2, 222, RBL, 222);
 //   	          pg.drawLine(170, 222, RBL, 222);
    	              	   
    	          pg.drawString("Subject", x*6, 245);
    	          pg.drawString(Subject.toUpperCase(), x*6 + metrics.stringWidth("Subject") + 3, 245);
    	          pg.drawLine( x*6 + metrics.stringWidth("Subject") + 2, 247, RBL, 247); 
    	          
    	          pg.drawString("Practical Examination February/October", x, 265);
//    	          show(metrics.stringWidth("Practical Examination February/October"));
    	          int Str14 = metrics.stringWidth("Practical Examination February/October");
    	          pg.drawLine(x + Str14 + 5, 267, 2*x + Str14, 267); 
    	          pg.drawString(Year, x + Str14 + 10, 265);
 //   	          int type = 2*x - metrics.stringWidth("at the") + 2;
  //  	          show(type);
    	          pg.drawString("at the", x, 285);       	    
    	          pg.drawLine(x + metrics.stringWidth("at the")+ 4, 287, 320, 287);
    	          pg.drawString(CenterOfExam, (2*x - metrics.stringWidth("at the") + 15), 285);
    	          
    	          pg.drawString("Index No.of Jr.College ", (int) ((5.5)*x) + 10, 285);
    	          pg.drawLine((int) ((7.5)*x) + 15, 287, RBL, 287);
    	          pg.drawString(IndexNoOfJrCollege, 475, 285);
    	             	          
    	          pg.drawString(NumberOfDaysOFExam, 6*x, 362);
    	          pg.drawString(NumOfStudentsExamined, 6*x, 402);
                  pg.drawString(RemPerStudent, (int) ((1.25)*x), 425);
                  
                     
                  try{
                	  NumOfStudents = Integer.parseInt(NumOfStudentsExamined);
                  }
                  catch(NumberFormatException e){
                	  NumOfStudentsExamined = "";  
                  }
                  try{
                	  RemunerationPerStudent = Float.parseFloat(RemPerStudent);
                  }
                  catch(NumberFormatException e){
                	  RemPerStudent = "";
                  }

                  if(NumOfStudentsExamined.equals("") || RemPerStudent.equals("")){
                	  TotRem = "";
                  }

                  else{
	                  TotalRemuneration = (float)(NumOfStudents*RemunerationPerStudent);
	                  pg.drawString(String.valueOf(TotalRemuneration), 290, 425);
	                  if(TotalRemuneration < 50.0f)
                		  TotalRemuneration = 50.0f;
	                  TotRem = String.valueOf(TotalRemuneration);
	                  pg.drawString("Rs.", 440, 345);
	                  pg.drawString("/-", 465 + metrics.stringWidth(TotRem), 345);
	                  pg.drawString("Rs.", 440, 479);
	                  pg.drawString("/-", 465 + metrics.stringWidth(TotRem), 479);
	                  pg.drawString("x", 225, 425);
	                  pg.drawString("=", 270, 425); 
	                  pg.drawString(TotRem, 460, 345);
	    	          pg.drawString(TotRem, 460, 479);
	    	          pg.drawString("/-", 295 + metrics.stringWidth(TotRem), 425);
                  }
//    	          newFont = new Font("Liberation Serif", Font.PLAIN, 12);
//    	          pg.setFont(newFont);
     	          
    	          newFont = new Font("Liberation Serif", Font.PLAIN, 10);
    	          pg.setFont(newFont);
    	          pg.drawString("(In capital letters)", 72, 230);
    	          pg.drawString("(Place of examination)", 130, 300);
    	          pg.drawString("(Signature)", 420, 555);
    	          
//    	          newFont = new Font("Liberation Serif", Font.PLAIN, 10);
//    	          pg.setFont(newFont);
    	          pg.drawString("Received Payment", 80, 580);
    	          pg.drawString("Full Postal", 230, 580);
    	          pg.drawString("Residential Address", 210, 590);
    	          pg.drawString("Signature of payee", 80, 660);
    	          pg.drawString("( Name of Jr. College where teaching )", x, 680);
    	             	         
    	          int Str30 = metrics.stringWidth("( Name of Jr. College where teaching )");
    	          pg.drawLine(Str30 + (x/2) + 5, 683, RBL, 683);      //  Horizontal Line of after Address line 4
    	          pg.drawString(CollNemOfExaminer , Str30 + (x/2) + 7, 680);
    	         
    	          pg.drawString("Index No. of Jr. College", x, 700);
    	          int Str31 = metrics.stringWidth("Index No. of Jr. College");      //    show(Str31);
    	          pg.drawString(ColIndCdOfExmner , 3*x, 699);
    	          pg.drawLine((int) (Str31 + (0.75*x)), 701, 280, 701);      //  Horizontal Line last line
//  	          show((int) (Str31 + (0.75*x)));
    	          pg.drawLine(x, 310, RBL, 310);        //  Horizontal Line of calculation table   
    	          pg.drawLine(x, 330, RBL, 330);        //  Horizontal Line of calculation table
    	          pg.drawLine(x, 465, RBL, 465);        //  Horizontal Line of calculation table   
    	          pg.drawLine(x, 485, RBL, 485);        //  Horizontal Line of calculation table
    	          pg.drawLine(325, 540, RBL, 540);      //  Horizontal Line above signature
    	          pg.drawLine(400, 310, 400, 485);      //  Vertical Line of calculation table        
    	          
    	          pg.drawString(Addline1, 326,580);
    	          pg.drawLine(325, 582, RBL, 582);      //  Horizontal Line of Address line 1
    	          pg.drawString(Addline2, 326,600);
    	          pg.drawLine(325, 602, RBL, 602);      //  Horizontal Line of Address line 2
    	          pg.drawString(Addline3, 326,620);
    	          pg.drawLine(325, 622, RBL, 622);      //  Horizontal Line of Address line 3
    	          pg.drawString(Addline4, 326,640);
    	          pg.drawLine(325, 642, RBL, 642);      //  Horizontal Line of Address line 4
    	          
    	         
    	          
    	          newFont = new Font("Liberation Serif", Font.PLAIN, 14);
    	          pg.setFont(newFont);
    	          pg.drawString("Particulars", 190, 325);
    	          pg.drawString("Amount", 450, 325);
    	          pg.drawLine(360, 465, 360, 484);      //  Vertical Line of calculation table
    	          pg.drawString("Total", 365, 480);
    	          
     	          
    	          newFont = new Font("Liberation Serif", Font.PLAIN, 12);
    	          pg.setFont(newFont);
    	          pg.drawString("Amount due to me as an INTERNAL/EXTERNAL examiner at the", x, 345);
    	          int Str20 = metrics.stringWidth("Examination Centre. The number of Days of Examination");
  //  	          show(Str20);
    	          pg.drawString("Examination Centre. The number of Days of Examination", x, 365);  
    	          pg.drawLine(Str20 + (x/3), 367, 400, 367);
    	          pg.drawString("Actual Total No. of  Candidates examined", x, 385);
    	          pg.drawString("by me excluding absentees", 2*x, 405);     
    	          pg.drawLine(Str20 + (x/3), 407, 400, 407);
    	          pg.drawString("Rs.          /- per candidate .....", x, 425);
    	          pg.drawString(NumOfStudentsExamined, 205, 425);
    	          pg.drawString(RemPerStudent, 240, 425);
//    	          pg.drawString(TotRem, 290, 425);
//    	          pg.drawString(TotRem, 460, 345);
//    	          pg.drawString(TotRem, 460, 479);
    	              	                  
    	          pg.drawString("( Minimum of Rs. 50/- irrespective of the number of candidates )", x, 445);
    	          pg.drawString("I hereby undertake to refund any amount paid to me in excess of the amount due", x, 500);
    	          pg.drawString("certified that the Examiner has actually examined the No. of candidates mentioned above", x, 715);
    	          pg.drawString("counter signature of the Head of the institution with stamp", x, 730);
    	          
    	          newFont = new Font("Liberation Serif", Font.PLAIN, 8);
    	          pg.setFont(newFont);
    	          pg.drawString("(In words)", x, 462);
    	          pg.drawString("(In Figure)", 590 - metrics.stringWidth("(In Figure)"), 462);
    	          String revenue[] = {"On revenue stamp","when the amount","exceeds Rs.5000/-"};
    	          int p = 605;
    	          for( int i = 0; i < revenue.length; i++)
    	          {
    	            pg.drawString(revenue[i], x+35, p);
    	            p = p+15;
    	          }                                 
    	          return Printable.PAGE_EXISTS;
    	        }
    	      });

    	      if (pjob.printDialog() == false) // choose printer
    	      return; 
    	      HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
              pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
              // 210 x 297  A4 size paper
    	      
    	      pjob.print(pattribs); 
    	    } 
    	      catch (PrinterException pe) {
    	      pe.printStackTrace();
    	    }
    	  }	  

public void Print1()
{
	 try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
	      pjob.setJobName("Remuneration Bill Printout-1");
	      pjob.setCopies(1);
	      pjob.setPrintable(new Printable() {
	        public int print(Graphics pg, PageFormat pf, int pageNum) {
	          if (pageNum > 0) // we only print one page
	            return Printable.NO_SUCH_PAGE; // ie., end of job
	          Font newFont;
	          
	          newFont = new Font("Liberation Serif", Font.PLAIN, 13);
	          FontMetrics metrics = pg.getFontMetrics(newFont);
	          int LtMrg = 60;       // Left Top x, Left Top y and Left Margin
	          int x = 60;                                         // Left margin indent.
 	          int y = 566;                                        // Right margin indent.
	          
	          int Space = 20;                                     // Left margin indent.
//	          int y = 585;                                        // Right margin indent.
	          pg.drawString("( FOR OFFICE USE ONLY )", 230, LtMrg);	          
	          newFont = new Font("Liberation Serif", Font.BOLD, 12);
	          pg.setFont(newFont);
	          pg.drawString("For Practical Branch", x, LtMrg + Space); 
	          pg.drawLine(x, 82, 200, LtMrg + Space + 2);
	          newFont = new Font("Liberation Serif", Font.PLAIN, 13);
	          pg.setFont(newFont);
	          pg.drawString("Certified that the afore said details have been verified from the office records",
	          		         x, LtMrg + (2*Space));
	          pg.drawString("& found correct, the bill is released for payment.", x, LtMrg + (3*Space));
	          pg.drawString("1) Signature of the dealing clerk", x, LtMrg + (4*Space));  
	          int S1 = metrics.stringWidth("1) Signature of the dealing clerk");
	          
	          pg.drawLine(S1+65, 142, 408, 142);        //  Horizontal Line - Signature of clerk
	          pg.drawString("Date", x+350, 140);
	          pg.drawLine(445, 142, 566, 142);        //  Horizontal Line in front of date
	          pg.drawString("2) Head of the H.S.C. Brabch", x, LtMrg + (5*Space));
	          
	          pg.drawString("Date", x+350, 160);	             	          
	          pg.drawLine(250, 162, 408, LtMrg + (5*Space) + 2);        //  Horizontal Line - Head ofHSC Branch   
	          pg.drawLine(445, 162, 566, LtMrg + (5*Space) + 2);        //  Horizontal Line in front of date
	     	   
	          newFont = new Font("Liberation Serif", Font.BOLD, 12);
	          pg.setFont(newFont);
	          pg.drawString("For Accounts Branch", x, LtMrg + (6*Space) + 10);       // y coordinate  =  190
	          pg.drawLine(x, LtMrg + (6*Space) + 12, 200, LtMrg + (6*Space) + 12);
	          newFont = new Font("Liberation Serif", Font.PLAIN, 13);  
	          pg.setFont(newFont);
	          pg.drawString("Passed for payement of Rs.", x, 210);
	          pg.drawLine(240, 212, 287, 212);        //  Horizontal Line - Rs.
	          pg.drawString("Rupees ",x+230, 210);             
	          pg.drawLine(340, 212, 566, 212);        //  Horizontal Line - Rupees
	          pg.drawString("Signature of dealing clerk", x, 240);
	          pg.drawLine(228, 242, 300, 242); 
	          pg.drawString("Accountant", x, 270);
	          pg.drawLine(135, 272, 300, 272); 
	          
	          String Boardaddress[] = {"For Divisional Secretary,",
		        		               "Maharashtra State Board of Secondary",
		        		               "& Higher Secondary Education",
			                           "Mumbai Divisional Board,", 
			                           "Vashi, Navi Mumbai  400703"};
				  int k = 350, d = 0;
				  for( int i = 0; i < Boardaddress.length; i++)
				  {
					d = metrics.stringWidth(Boardaddress[i]);  
				    pg.drawString( Boardaddress[i], y - d, k);
				    k = k+20;
				  }
	          
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

public void Print2()
{
	 try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
	      pjob.setJobName("Relieving Order Printout");
	      pjob.setCopies(1);
	      pjob.setPrintable(new Printable() {
	        public int print(Graphics pg, PageFormat pf, int pageNum) {
	          if (pageNum > 0) // we only print one page
	            return Printable.NO_SUCH_PAGE; // ie., end of job
	          Font newFont;
	          newFont = new Font("Liberation Serif", Font.PLAIN, 14);
	          FontMetrics metrics = pg.getFontMetrics(newFont);
	          int RTx = 600; int RtMrg = 40;       // Right Top x, Right Top y and Right Margin  600 - 40  =  560
	          int RBL = RTx - RtMrg ;                             // Right Border Line   600 - 40  =  560
	          int x = 60;                                         // Left margin indent.
	          int Center = (RBL - x)/2 + x ;
	          String S1 = "C E R T I F I C A T E";
	          String S2 = "TO WHOMSOEVER IT MAY CONCERN"; 
	          String S3 = " has conducted Practical /"; 
	          String S4 = "Oral Examination in "; 
	          String S5 = "This is to certify that Mr/Mrs/Miss ";
	          String S6 = "at our college, from ";
	          
	          int y = 560;                                        // Right margin indent.
	          pg.drawString(S1,  Center - (int) (metrics.stringWidth(S1))/2 , 235);
	          pg.drawLine(245, 237, 365, 237);                    // Under Line for Certificate         
	          
	          pg.drawString(S2, Center - (int) (metrics.stringWidth(S2))/2 + 15 , 270);

	          pg.setFont(newFont);
	          newFont = new Font("Liberation Serif", Font.PLAIN, 13);         
	          int S51 = metrics.stringWidth(S5);
	          pg.drawString(S5, x, 305);
	          pg.drawString(ExaminerName.toUpperCase(), S51+65, 305);
	          pg.drawLine(S51+60, 308, 560, 308);
	          
	          int S31 = metrics.stringWidth(S3);
	          pg.drawString("from", x, 340);
	          pg.drawString(CollNemOfExaminer, x+40, 340);
	          pg.drawLine(95, 343, (y - S31), 343);
	          
	          int S41 = metrics.stringWidth(S4);
	          pg.drawString(S3, y - S31, 340);
	          pg.drawString(S4, x, 375);
	          pg.drawString(Subject, x+S41+5, 375);
	          pg.drawLine(x+S41+3, 378, x+S41+110, 378);
//	          Show(S41);
	          	          	                
	          pg.drawString( S6, S41+175, 375);
	          pg.drawString(StartDate, y-155, 375);
	          pg.drawLine(y-157, 378, y-89, 378);
//	          pg.drawLine(y-70, 343, y, 343);
//	          pg.drawString("to ", x, 375);
	          pg.drawString("to ", y-85, 375);
	          pg.drawString(EndDate, y-68, 375);
//	          pg.drawString("I", y, 375);
//	          pg.drawString(EndDate, x + metrics.stringWidth("to ") + 5, 375);
	          pg.drawLine(y-70, 378, y, 378);
//              pg.drawLine(x + metrics.stringWidth("to "), 378, x + metrics.stringWidth("to ")+80, 378);
	          pg.drawString("Except on ", 60, 410);
	          pg.drawString(ExceptOn, x + metrics.stringWidth("Except on ")+5, 410);
	          pg.drawString("He / She is relieved from duties.", 60, 445);
	          pg.drawString("Principal / Vice Principal", 60, 525);
	                    
	          newFont = new Font("Liberation Serif", Font.PLAIN, 9);
	          pg.setFont(newFont);
	          pg.drawString("(subject)", S41+90, 387);
	          pg.drawString("(College)", S41+120, 352);
	          
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