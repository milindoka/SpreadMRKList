package in.peecee.spreadMRKList;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class showstatistics {
	
    JFrame frame = new JFrame();
    private JTable table;
    private JTable table_1;
    private MeritListButtons MLButtons = new MeritListButtons();
    
    public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
   
    
    public void ShowStats(){
		 
	    frame.validate();                
	    frame.setTitle("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
	    frame.setSize(1000, 650);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
	}

   public void SetData(Object obj, int row_index, int col_index){table.getModel().setValueAt(obj,row_index,col_index);  }
   public void SetData1(Object obj, int row_index, int col_index){table_1.getModel().setValueAt(obj,row_index,col_index);  }
//   public Object GetData(JTable table, int row_index, int col_index) {  return SpreadMRKListview.getTable().getValueAt(row_index, col_index); }
//   public String GetData1(JTable table, int row_index, int col_index) {  return (String) SpreadMRKListview.getTable().getValueAt(row_index, col_index); }
   
   
   public showstatistics(){	   
   	   	 
   	GridBagLayout gridBagLayout = new GridBagLayout();
   	gridBagLayout.columnWidths = new int[]{0, 483, 0};
   	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   	gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
   	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
   	frame.getContentPane().setLayout(gridBagLayout);
   	
   	JLabel lblNewLabel = new JLabel("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
   	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
   	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
   	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
   	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
   	gbc_lblNewLabel.gridx = 1;
   	gbc_lblNewLabel.gridy = 0;
   	frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
   	
   	JLabel label = new JLabel("OVER ALL RANKINGS");
   	label.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_label = new GridBagConstraints();
   	gbc_label.insets = new Insets(0, 0, 5, 5);
   	gbc_label.gridx = 0;
   	gbc_label.gridy = 1;
   	frame.getContentPane().add(label, gbc_label);
   	
   	JScrollPane scrollPane = new JScrollPane();
   	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
   	gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane.gridwidth = 2;
   	gbc_scrollPane.gridx = 0;
   	gbc_scrollPane.gridy = 2;
   	frame.getContentPane().add(scrollPane, gbc_scrollPane);
   	
   	table = new JTable();
   	scrollPane.setViewportView(table);
   	table.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table.setModel(new DefaultTableModel(
   		new Object[][] {
   				{"Science", "1", null, null, null, null, null},
	    		{null, "2", null, null, null, null, null},
	    		{null, "3", null, null, null, null, null},
	    		{null, "4", null, null, null, null, null},
	    		{null, "5", null, null, null, null, null},
	    		{null, "6", null, null, null, null, null},
	    		{null, "7", null, null, null, null, null},
	    		{null, "8", null, null, null, null, null},
	    		{null, "9", null, null, null, null, null},
	    		{null, "10", null, null, null, null, null},
	    		{null, null, null, null, null, null, null},
	    		{"Commerce", "1", null, null, null, null, null},
	    		{null, "2", null, null, null, null, null},
	    		{null, "3", null, null, null, null, null},
	    		{null, "4", null, null, null, null, null},
	    		{null, "5", null, null, null, null, null},
	    		{null, "6", null, null, null, null, null},
	    		{null, "7", null, null, null, null, null},
	    		{null, "8", null, null, null, null, null},
	    		{null, "9", null, null, null, null, null},
	    		{null, "10", null, null, null, null, null},
	    		{null, null, null, null, null, null, null},
   		},
   		new String[] {
   				"Stream", "Rank", "Name", "Roll No.", "Division", "Score", "Percent"
   		}
   	));
    table.setRowHeight(25); 
    JTableHeader header1 = table.getTableHeader();
    header1.setPreferredSize(new Dimension(0,30));
    header1.setFont(new Font("Dialog", Font.BOLD,16));   
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    table.setFont(new Font("Times New Roman", Font.BOLD, 18));
    table.getColumnModel().getColumn(0).setPreferredWidth(100);       // Stream           
    table.getColumnModel().getColumn(1).setPreferredWidth(30);       // Rank
    table.getColumnModel().getColumn(2).setPreferredWidth(410);      // Name  
    table.getColumnModel().getColumn(3).setPreferredWidth(65);       // Roll Number
    table.getColumnModel().getColumn(4).setPreferredWidth(40);       // Division
    table.getColumnModel().getColumn(5).setPreferredWidth(50);       // Total Score
    table.getColumnModel().getColumn(6).setPreferredWidth(70);       // Percent
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
   	
   	JLabel lblSubjectToppers = new JLabel("SUBJECT TOPPERS");
   	lblSubjectToppers.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_lblSubjectToppers = new GridBagConstraints();
   	gbc_lblSubjectToppers.insets = new Insets(0, 0, 5, 5);
   	gbc_lblSubjectToppers.gridx = 0;
   	gbc_lblSubjectToppers.gridy = 4;
   	frame.getContentPane().add(lblSubjectToppers, gbc_lblSubjectToppers);
   	
   	JScrollPane scrollPane_1 = new JScrollPane();
   	GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
   	gbc_scrollPane_1.gridheight = 2;
   	gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane_1.gridwidth = 3;
   	gbc_scrollPane_1.gridx = 0;
   	gbc_scrollPane_1.gridy = 5;
   	frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
   	
   	table_1 = new JTable();
   	scrollPane_1.setViewportView(table_1);
   	table_1.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table_1.setModel(new DefaultTableModel(
   		new Object[][] {
   				{"  English (Com)", null, null, null, null},
	    		{"  Hindi (Com)", null, null, null, null},
	    		{"  Marathi (Com)", null, null, null, null},
	    		{"  Tamil (Com)", null, null, null, null},
	    		{"  Economics", null, null, null, null},
	    		{"  Accountancy - BK", null, null, null, null},	    		
	    		{"  Org. of Commerece", null, null, null, null},
	    		{"  Secreterial Practice", null, null, null, null},
	    		{"  Mathematics (Com)", null, null, null, null},
	    		{"  English (Sci)", null, null, null, null},
	    		{"  Hindi (Sci)", null, null, null, null},
	    		{"  Marathi (Sci)", null, null, null, null},
	    		{"  Tamil (Sci)", null, null, null, null},
	    		{"  Physics", null, null, null, null},
	    		{"  Chemistry", null, null, null, null},
	    		{"  Mathematics", null, null, null, null},
	    		{"  Biology", null, null, null, null},
	    		{"  Electronics", null, null, null, null},
	    		{"  Computer Science", null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},	},
   		new String[] {
   				"Subject", "Name", "Roll No.", "Division", "Score"
   		}
   	));
   	
    table_1.setRowHeight(25);    
    JTableHeader header2 = table_1.getTableHeader();
    header2.setPreferredSize(new Dimension(0,30));
    header2.setFont(new Font("Dialog", Font.BOLD,16));   
    table_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
    table_1.getColumnModel().getColumn(0).setPreferredWidth(170);      // SUBJECT           
    table_1.getColumnModel().getColumn(1).setPreferredWidth(400);      // NAME
    table_1.getColumnModel().getColumn(2).setPreferredWidth(70);       // ROLL NUMBER  
    table_1.getColumnModel().getColumn(3).setPreferredWidth(30);       // DIVISION
    table_1.getColumnModel().getColumn(4).setPreferredWidth(50);       // SCORE   
   	
   	JButton btnPrintStats = new JButton("PRINT");
   	btnPrintStats.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent arg0) {
//   		 okButtonAction();
//   			String EngMarks = GetData1(SpreadMRKListview.getTable(),5, 28).toString();
//			show(EngMarks);
   			MLButtons.showMLButtons();
   			
   		}
   	});
   	GridBagConstraints gbc_btnPrintStats = new GridBagConstraints();
   	gbc_btnPrintStats.insets = new Insets(0, 0, 5, 5);
   	gbc_btnPrintStats.gridx = 0;
   	gbc_btnPrintStats.gridy = 8;
   	frame.getContentPane().add(btnPrintStats, gbc_btnPrintStats);
   	
   	JButton btnOK = new JButton("OK");
   	btnOK.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent arg0) {
   			
   		 okButtonAction();
   		}
   	});
   	GridBagConstraints gbc_btnOK = new GridBagConstraints();
   	gbc_btnOK.insets = new Insets(0, 0, 5, 0);
   	gbc_btnOK.anchor = GridBagConstraints.WEST;
   	gbc_btnOK.gridx = 1;
   	gbc_btnOK.gridy = 8;
   	frame.getContentPane().add(btnOK, gbc_btnOK);
   	
   
	   
   }
   
   public JTable Table(){
       return table;
   }
   
   public JTable Table1(){
       return table_1;
   }
   
   // This button's action is simply to dispose of the JDialog.
   private void okButtonAction() {
      // win is here the JDialog that holds this JPanel, but it could be a JFrame or 
      // any other top-level container that is holding this JPanel
      Window win = SwingUtilities.getWindowAncestor(table);
      if (win != null) {
         win.dispose();
      }
   }

   
}
