package in.peecee.spreadMRKList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;

public class statistics {
	
	JTable table1 = new JTable(); 
	JTable table2 = new JTable();
    JFrame frame = new JFrame();
    private final JButton btnNewButton = new JButton("New button");
    
    /**
     * @wbp.parser.entryPoint
     */
    public void ShowStatistics(){
    	
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JScrollPane scrollpane1 = new JScrollPane(table1);
    JScrollPane scrollpane2 = new JScrollPane(table2);    
    frame.getContentPane().add(scrollpane1, BorderLayout.CENTER);
    frame.getContentPane().add(scrollpane2, BorderLayout.SOUTH);
    frame.validate();                
    frame.setTitle("MERIT LIST - OVER ALL RANKING");
    frame.setSize(1260, 700);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    
    DefaultTableModel model1 = (DefaultTableModel)table1.getModel();
    
    model1.setDataVector(new Object[][]{ 
    		{"SCIENCE","FIRST"," "," "}, {" ","SECOND"," "," "}, {" ","THIRD"," "," "}, {" "," "," "," "},
    		{"COMMERCE","FIRST"," "," "}, {" ","SECOND"," "," "}, {" ","THIRD"," "," "}, {" "," "," "," "}},
    		
    new Object[]{"STREAM","RANK","Name","Roll No.","Div","Total Score","Percent"});
    
    JTableHeader header1 = table1.getTableHeader();
    header1.setPreferredSize(new Dimension(0,30));
    header1.setFont(new Font("Dialog", Font.BOLD,16));   
    
    table1.setFont(new Font("Times New Roman", Font.BOLD, 15));
    table1.getColumnModel().getColumn(0).setPreferredWidth(50);       // Stream           
    table1.getColumnModel().getColumn(1).setPreferredWidth(50);       // Rank
    table1.getColumnModel().getColumn(2).setPreferredWidth(400);      // Name  
    table1.getColumnModel().getColumn(3).setPreferredWidth(50);      // Roll Number
    table1.getColumnModel().getColumn(4).setPreferredWidth(20);      // Division
    table1.getColumnModel().getColumn(5).setPreferredWidth(80);      // Total Score
    table1.getColumnModel().getColumn(6).setPreferredWidth(50);      // Percent
        
    table1.setRowHeight(30);       
    
    scrollpane1.setColumnHeaderView(btnNewButton);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    table1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    table1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
    
 DefaultTableModel model2 = (DefaultTableModel)table2.getModel();
    
    model2.setDataVector(new Object[][]{ 
    		{"ENGLISH (COM)"," "," "," "," "}, {"MARATHI (COM)"," "," "," "," "},    {"HINDI (COM)"," "," "," "," "},
    		{"TAMIL(COM)"," "," "," "," "}, {"ECONOMICS",""," "," "," "},    {"ACCCOUNTANCY "," "," "," "," "},
    		{"ORG.OF COMMERCE "," "," "," "," "},{"MATHEMATICS (COM)"," "," "," "," "}, {"SECRETERIAL PRACTICE"," "," "," "," "},
            {"ENGLISH (SCI)",""," "," "," "}, {"MARATHI (SCI)"," "," "," "," "},    {"HINDI (SCI)"," "," "," "," "},
    		{"TAMIL(SCI)"," "," "," "," "}, {"PHYSICS",""," "," "," "}, {"CHEMISTRY"," "," "," "," "},
    		{"MATHEMATICS (SCI)",""," "," "," "}, {"BIOLOGY"," "," "," "," "} },
    		
    new Object[]{"SUBJECT","Name","Roll No.","Div","Score"});
    
    JTableHeader header2 = table2.getTableHeader();
    header2.setPreferredSize(new Dimension(0,30));
    header2.setFont(new Font("Dialog", Font.BOLD,16));
   
    
    table2.setFont(new Font("Times New Roman", Font.BOLD, 15));
    table2.getColumnModel().getColumn(0).setPreferredWidth(80);       // SUBJECT           
    table2.getColumnModel().getColumn(1).setPreferredWidth(400);      // NAME
    table2.getColumnModel().getColumn(2).setPreferredWidth(80);       // ROLL NUMBER  
    table2.getColumnModel().getColumn(3).setPreferredWidth(20);       // DIVISION
    table2.getColumnModel().getColumn(4).setPreferredWidth(50);       // SCORE   
    
    table2.setRowHeight(30);   
    table2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    table2.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

    
    
    }
            
}
