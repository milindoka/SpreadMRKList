import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Freeze3 extends javax.swing.JFrame {
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
    private JTextField textField3;
    private JTextField textField4;
    private JTable tableBlox;
    
    private int fixedRows = 1;    //number of columns to be frozen
    
//    private String[] columnNames = {""}; 
//    private Object[][] data = {{" "}};
//    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    
	public String GetData (JTable table, int row_index, int col_index) {  
		return (String) table.getModel().getValueAt(row_index, col_index); 
		}
	public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
  
    public Freeze3() {
 
    	setTitle("S U P E R V I S I O N  C H A R T FOR J U N I O R C O L L E G E");
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane);
      
        setPreferredSize(new Dimension(1360,350));   
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        new JPanel(new GridBagLayout());
      
        Object[][] data = new String[7][40];
        Object[] headers =new String[26];
        tableModel = new DefaultTableModel(data, headers);
        jTable.setModel(tableModel);
      
        jTable.setAutoCreateColumnsFromModel( false );
        
        for (int i=0; i < jTable.getRowCount(); i++){
          if(i == 0){jTable.getColumnModel().getColumn(0).setMinWidth(150);}
            jTable.getColumnModel().getColumn(i).setMinWidth(50);
            jTable.setRowHeight(20);
//            jTable.setTableHeader(null);
        }
        
        
        
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
/*        jTable.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                //  Keep freezeTable in sync with the jTable
                if ("selectionModel".equals(e.getPropertyName())) {
                    freezeTable.setSelectionModel(jTable.getSelectionModel());
                }

                if ("dataModel".equals(e.getPropertyName())) {
                    freezeTable.setModel(jTable.getModel());
                }
            }
        });               */
      
       // for(int i=0;i<100;i++)
       // tableModel.addRow(new Object[]{"","","","",""});
        
        freezeTable = new javax.swing.JTable();
        freezeTable.setAutoCreateColumnsFromModel(false);
        freezeTable.setModel(tableModel);
        freezeTable.setSelectionModel(jTable.getSelectionModel());
        freezeTable.setFocusable(false);
      
        for (int i = 0; i < fixedRows; i++) {
            TableColumnModel colModel = jTable.getColumnModel();
            TableColumn column = colModel.getColumn(0);
            colModel.removeColumn(column);
            freezeTable.getColumnModel().addColumn(column);
            freezeTable.setRowHeight(20);
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
        
        textField0 = new JTextField();
        textField0.setColumns(2);
        JLabel Label0 = new JLabel("No.of Days of Exam");
      	northPanel.add(Label0);
        northPanel.add(textField0);
        show(textField0);
        String NOE = textField0.getText();
        
        textField1 = new JTextField();
        textField1.setColumns(2);
        JLabel Label1 = new JLabel("No.of Supevisors");
      	northPanel.add(Label1);
        northPanel.add(textField1);
        
        JButton buttoncreate = new JButton("Create Chart");
        buttoncreate.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub					
			}		
        });
        northPanel.add(buttoncreate);                    
        
        textField2 = new JTextField();
        textField2.setColumns(8);
        JLabel Label2 = new JLabel("EXAM TYPE - FYJC");
      	northPanel.add(Label2);
        northPanel.add(textField2);
        
        textField3 = new JTextField();
        textField3.setColumns(8);
        JLabel Label3 = new JLabel("EXAM TYPE - SYJC");
      	northPanel.add(Label3);
        northPanel.add(textField3);
    		
      	add(northPanel, BorderLayout.NORTH);
        

	///////    S O U T H   P A N E L  /////		
        JPanel southPanel = new JPanel();
        JButton buttonDistri = new JButton("Distribute");
        buttonDistri.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub					
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
        JButton buttonMastTT = new JButton("Mast.TT");
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
                new Freeze3().setVisible(true);
            }
        });
    }
}
