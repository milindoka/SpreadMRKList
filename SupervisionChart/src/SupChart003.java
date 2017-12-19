import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;



public class SupChart003 extends javax.swing.JFrame {
    /**
	 * 
	 */
	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupChart003().setVisible(true);
            }
        });
    }
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.JTable freezeTable;
    private JTextField textField0;
    private JTextField textField1;
    private JTextField textField2;    
    private int NumRows = 15;
    
    private int fixedColumns = 5;    //number of columns to be frozen
    
    String column_header[] = {"Date", "Day", "Time", "FYJC","SYJC"};
    private JButton buttonAllot;
    

	public String GetData (JTable table, int row_index, int col_index) {  
		return (String) table.getModel().getValueAt(row_index, col_index); 
		}
	
	public void SetData(Object obj, int row_index, int col_index) {  
		        jTable.getModel().setValueAt(obj,row_index,col_index);   }
	
	public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
  
	public SupChart003() {

    	setTitle("S U P E R V I S I O N  C H A R T FOR J U N I O R C O L L E G E");
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane);
        NumRows = 21;
        setPreferredSize(new Dimension(1360,160+(NumRows*25)));   
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        new JPanel(new GridBagLayout());
        
        Object[][] data = new String[NumRows][40];
        Object[] headers =new String[50];
        tableModel = new DefaultTableModel(data, headers);
        jTable.setModel(tableModel);
      
        jTable.setAutoCreateColumnsFromModel( true );
        
        for (int i=0; i < jTable.getColumnCount(); i++){
        	if(i == 0){jTable.getColumnModel().getColumn(0).setPreferredWidth(60);}
        	if(i == 1){jTable.getColumnModel().getColumn(1).setPreferredWidth(20);}
        	if(i == 2){jTable.getColumnModel().getColumn(2).setMinWidth(95);}
            jTable.getColumnModel().getColumn(i).setMinWidth(40);
            jTable.setRowHeight(25);
//            jTable.setTableHeader(null);
        }
               
   for (int k = 0; k < column_header.length; k++){
	   jTable.getColumnModel().getColumn(k).setHeaderValue(column_header[k]);
   }
        
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        freezeTable = new javax.swing.JTable();
        freezeTable.setAutoCreateColumnsFromModel(false);
        freezeTable.setModel(tableModel);
        freezeTable.setSelectionModel(jTable.getSelectionModel());
        freezeTable.setFocusable(false);
      
        for (int i = 0; i < fixedColumns; i++) {
            TableColumnModel colModel = jTable.getColumnModel();
            TableColumn column = colModel.getColumn(0);
            colModel.removeColumn(column);
            freezeTable.getColumnModel().addColumn(column);
            freezeTable.setRowHeight(25);
        }

        //  Add the fixed table to the scroll pane
        freezeTable.setPreferredScrollableViewportSize(freezeTable.getPreferredSize());
        jScrollPane.setRowHeaderView(freezeTable);
        jScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, freezeTable.getTableHeader());

        
        // Synchronize scrolling of the row header with the jTable
        jScrollPane.getRowHeader().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                //  Sync the scroll pane scrollbar with the row header
                JViewport viewport = (JViewport) e.getSource();
                jScrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
            }
        });                        
        pack();
      
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    	Date date = new Date();
    	String TodaysDate = dateFormat.format(date);                // Gives Current Date

    	for(int i=0; i < jTable.getRowCount(); i++)                                 //  To initialize the table with Current Date
    	     {	 	
    	    	 SetData(TodaysDate,i,0);
    	    	 SetData( DayOfWeek(TodaysDate),i,1);
    	    	 TodaysDate = IncrementDate(TodaysDate);
    	    	 if(DayOfWeek(TodaysDate).contains("Sun"))  TodaysDate=IncrementDate(TodaysDate); ///SKIP SUNDAY
    	    	 SetData("12:30 - 03:30",i,2);
    	    	 if (i == jTable.getRowCount()-2){ 
    	    		 SetData(" ",i,0);
        	    	 SetData( " ",i,1);
    	    		 SetData("Current Count",i,2);}
    	    	 if (i == jTable.getRowCount()-1){ 
    	    		 SetData(" ",i,0);
        	    	 SetData( " ",i,1);
    	    		 SetData("Final Count",i,2);}
    	     }
    	
    	JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.addMouseListener(new TableHeaderMouseListener(jTable));
        JTableHeader TableHeader = freezeTable.getTableHeader();
        TableHeader.addMouseListener(new TableHeaderMouseListener(freezeTable));
        
        
///////    N O R T H   P A N E L  /////        
        
        JPanel northPanel = new JPanel();
        textField1 = new JTextField();
        textField1.setColumns(8);
        JLabel Label1 = new JLabel("EXAM TYPE - FYJC");
      	northPanel.add(Label1);
        northPanel.add(textField1);
        
        textField2 = new JTextField();
        textField2.setColumns(8);
        JLabel Label2 = new JLabel("EXAM TYPE - SYJC");
      	northPanel.add(Label2);
        northPanel.add(textField2);
        buttonAllot = new JButton("Enter Names");
        northPanel.add(buttonAllot);
        buttonAllot.addActionListener(new ActionListener()
        {		
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane JOP = new JOptionPane();
				String NemsOfSups = JOptionPane.showInputDialog("Enter the Names of Supervisors");	
				JOptionPane.showMessageDialog(JOP, NemsOfSups);
				String[] splitNames = NemsOfSups.split(",");
				for (int i = 0; i < splitNames.length; i++)
				{JTableHeader th = jTable.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(i);
				tc.setHeaderValue( splitNames[i] );
				th.repaint();
					   
		//			jTable.getColumnModel().getColumn(i).setHeaderValue(splitNames[i].toUpperCase());
        //            jTable.repaint();
				   }
			}		
        });            
           
      	add(northPanel, BorderLayout.NORTH);        

	///////    S O U T H   P A N E L  /////		
        
      	JPanel southPanel = new JPanel();
        JButton buttonDistri = new JButton("Distribute");
        buttonDistri.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub	
				System.exit(0);
			}		
        });
        JButton buttonLoad = new JButton("Load");
        buttonDistri.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub					
			}		
        });
        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent arg0) {           
            
            }           
        });
        JButton buttonPrint = new JButton("Update");
        buttonPrint.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
               
            }
        });
        JButton buttonMastTT = new JButton("Master TT");
        buttonMastTT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {             
            	
            }
        });
        JButton buttonIndTT = new JButton("Ind.TT");
        buttonIndTT.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {
            	
            }
        });
        JButton buttonFYTT = new JButton("FYJC TT");
        buttonIndTT.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
             {
            	
             }
        });
        JButton buttonSYTT = new JButton("SYJC TT");
        buttonIndTT.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
             {
            	
             }
        });

        
        southPanel.add(buttonDistri);
        southPanel.add(buttonLoad);
        southPanel.add(buttonSave);
        southPanel.add(buttonPrint);
        southPanel.add(buttonMastTT);
        southPanel.add(buttonIndTT);
        southPanel.add(buttonFYTT);
        southPanel.add(buttonSYTT);
        
        add(southPanel, BorderLayout.SOUTH);
        
    }
 	
		 public  String DayOfWeek(String det)                                              //Format dd/MM/yy
    	 {  SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yy");
	        Date MyDate = null;
	try {  MyDate = newDateFormat.parse(det); } catch (ParseException e) { e.printStackTrace(); }
	   newDateFormat.applyPattern("EEE");
	 String dow = newDateFormat.format(MyDate);
	 return dow;
	 }

		  public  String IncrementDate(String det)                                             //Format dd/MM/yyyy
		  {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Calendar c = Calendar.getInstance();
			try { c.setTime(sdf.parse(det)); } catch (ParseException e) { e.printStackTrace();	}
			c.add(Calendar.DATE, 1);                                                   // number of days to add
			det = sdf.format(c.getTime());                                            // det is now the new date
			return det;
		  }       
		  
		  public class TableHeaderMouseListener extends MouseAdapter {
		        
		        private JTable jTable;
		         
		  public TableHeaderMouseListener(JTable table) {
		            this.jTable = table;
		        }  
		  public void mouseClicked(MouseEvent event) {
		         Point point = event.getPoint();
		         int column = jTable.columnAtPoint(point);
		          
		        String Name = JOptionPane.showInputDialog(jTable, "Column header #" + column + " is clicked");
		        jTable.getColumnModel().getColumn(column).setHeaderValue(Name); 
		          
		         // do your real thing here...
		     }
		  }
}
