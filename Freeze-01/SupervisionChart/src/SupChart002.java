import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SupChart002 extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.JTable freezeTable;
    private JTextField textField0;
    private JTextField textField1;
    private JTextField textField2;
    
    private JTable tableBlox;
    
    private int fixedColumns = 4;    //number of columns to be frozen
    
    String column_header[] = {"Date", "Time", "FYJC","SYJC"};
    private JButton buttonAllot;
    
//    private String[] columnNames = {""}; 
//    private Object[][] data = {{" "}};
//    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    
	public String GetData (JTable table, int row_index, int col_index) {  
		return (String) table.getModel().getValueAt(row_index, col_index); 
		}
	public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
  
    public SupChart002() {

    	setTitle("S U P E R V I S I O N  C H A R T FOR J U N I O R C O L L E G E");
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane);
      
        setPreferredSize(new Dimension(1360,700));   
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        new JPanel(new GridBagLayout());
      
        Object[][] data = new String[23][40];
        Object[] headers =new String[50];
        tableModel = new DefaultTableModel(data, headers);
        jTable.setModel(tableModel);
      
        jTable.setAutoCreateColumnsFromModel( false );
        
        for (int i=0; i < jTable.getColumnCount(); i++){
        	if(i == 0){jTable.getColumnModel().getColumn(0).setMinWidth(50);}
        	if(i == 1){jTable.getColumnModel().getColumn(1).setMinWidth(90);}
            jTable.getColumnModel().getColumn(i).setMinWidth(50);
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
//            if(i == 1){freezeTable.getColumnModel().getColumn(1).setMinWidth(60);}
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
        buttonAllot.addActionListener(new ActionListener()
        {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub	

				JOptionPane JOP = new JOptionPane();
				String NemsOfSups = JOptionPane.showInputDialog("Enter the Names of Supervisors");	
//				String NemsOfSups = "MO,PC,NM,KT,JT";
				JOP.showMessageDialog(JOP, NemsOfSups);	
				   for (int i = 0; i < 5; i++){
					   String[] split = NemsOfSups.split(",");
					   for(String separated: split){
						   System.out.println(separated);
						   }
//					   jTable.getColumnModel().getColumn(i).setHeaderValue(splits);
				   }
				
				//				System.exit(0);;
			}		
        });
        northPanel.add(buttonAllot);
        
      	add(northPanel, BorderLayout.NORTH);        

	///////    S O U T H   P A N E L  /////		
        
      	JPanel southPanel = new JPanel();
        JButton buttonDistri = new JButton("Distribute");
        buttonDistri.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub	
				System.exit(0);;
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
  
    private void show(JTextField textField02) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupChart002().setVisible(true);
            }
        });
    }
	
/*	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	Date date = new Date();
	String TodaysDate = dateFormat.format(date);                // Gives Current Date

	for(int i=1; i <= 11; i++)                                 //  To initialize the table with Current Date
	     {	 	
	    	 SetData5(TodaysDate,0,i);
	    	 SetData5( DayOfWeek(TodaysDate),1,i);
	    	 TodaysDate = IncrementDate(TodaysDate);
	    	 if(DayOfWeek(TodaysDate).contains("Sun"))  TodaysDate=IncrementDate(TodaysDate); ///SKIP SUNDAY
	     }
		private void SetData5(String todaysDate2, int j, int i2) {
			// TODO Auto-generated method stub
			
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
		  }             */
		 
}
