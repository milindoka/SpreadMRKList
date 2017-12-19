package in.peecee.spreadMRKList;

import java.io.File;

import javax.swing.JTable;

public class SpreadMRKListModel {
	
	private int x;
	private String PrinterName, path, search ;
	JTable table;
		   
    public SpreadMRKListModel(){
        x = 0;
    }    
	
	public SpreadMRKListModel(int x)  ///// Model Constructor
    {
        this.x = x;
    }
	  
	public void incX(){
	        x++;
	    }
	  		  
	public int getX(){
	        return x;
	    }
	    	  
	public String getJarPath()
	    {
	    	File f = new File(System.getProperty("java.class.path"));
	     	File dir = f.getAbsoluteFile().getParentFile();
	        path=dir.toString();
	     	return  path;
	    }
	
	public void setPrinterName(String printername)
    {
       this.PrinterName = printername;
    }
	  
	public String getSearch(){
	     	return  search;
	    }
		 
	public JTable getTable(){
	     	return  table;
	    }	
}
