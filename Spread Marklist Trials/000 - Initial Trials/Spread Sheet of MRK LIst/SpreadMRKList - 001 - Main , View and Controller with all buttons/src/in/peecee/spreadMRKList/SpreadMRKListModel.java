package in.peecee.spreadMRKList;

import java.io.File;

public class SpreadMRKListModel {
	
	private String PrinterName, path ;
	
	
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

	
	
	

}
