package in.peecee.spreadMRKList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class SpreadMRKListSubMarks {
	
	private SpreadMRKListView SpreadMRKListview;
	private SpreadMRKListController Controller;
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}       ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}         ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging
	
	public void SetData1(JTable table,Object obj, int row_index, int col_index){SpreadMRKListview.getTable().getModel().setValueAt(obj,row_index,col_index);  }
    public void SetData(Object obj, int row_index, int col_index){SpreadMRKListview.getTable().getModel().setValueAt(obj,row_index,col_index);  }
	
    public SpreadMRKListSubMarks() {	
		

    }
		 
	public void ENGMarks(){
		  
		 String[] subwithmarks = null;
   	 String plate[];
		 String ENGmarks = null, ENGTotal = null, line;	
		 int EngT2Marks = 0, ENGTotalT2 = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate = strArray.get(i).split("#");

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
    	 JTableHeader th = SpreadMRKListview.getTable().getTableHeader();          //  For header changing dynamically
   	 th.repaint();                                          
    	 SpreadMRKListview.Eng.text="ENG";                       
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
	
	}

