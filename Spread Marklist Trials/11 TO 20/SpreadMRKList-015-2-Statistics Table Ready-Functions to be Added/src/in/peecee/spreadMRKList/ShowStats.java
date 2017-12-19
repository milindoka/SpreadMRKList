package in.peecee.spreadMRKList;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class ShowStats {
	
	    JFrame frame = new JFrame();
	    private JTable table;
	    private JTable table_1;
	    private SpreadMRKListView SpreadMRKListview;
	    
	    public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	    
	    public void SetData(Object obj, int row_index, int col_index){SpreadMRKListview.getTable().getModel().setValueAt(obj,row_index,col_index);  }
	    public Object GetData(JTable table, int row_index, int col_index) {  return SpreadMRKListview.getTable().getValueAt(row_index, col_index); }
	    public String GetData1(JTable table, int row_index, int col_index) {  return (String) SpreadMRKListview.getTable().getValueAt(row_index, col_index); }

	/**
	 * @wbp.parser.entryPoint
	 */
	public void ShowStats(){
		 
	    frame.validate();                
	    frame.setTitle("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
	    frame.setSize(1000, 700);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{0, 0, 613, 0};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    frame.getContentPane().setLayout(gridBagLayout);
	    
	    JLabel lblNewLabel = new JLabel("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
	    lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
//	    lblNewLabel.setForeground(Color.BLUE);
	    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	    gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
	    gbc_lblNewLabel.gridx = 2;
	    gbc_lblNewLabel.gridy = 0;
	    

	    frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("OVER ALL RANKINGS");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
	    gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1.gridx = 0;
	    gbc_lblNewLabel_1.gridy = 1;
	    frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	    
	    table = new JTable();
	    
		table = new JTable() 
		{
			 public Component prepareRenderer(                                      // To make column header and all rows grey
	                 TableCellRenderer renderer, int row, int col) {              
	             if (row == 0) { 
	                 return this.getTableHeader().getDefaultRenderer()
	                     .getTableCellRendererComponent(this,
	                     this.getValueAt(row, col), false, false, row++, col);
	              } 
	                else { return super.prepareRenderer(renderer, row, col); }
	         }
		};
	    
	    table.setBorder(new LineBorder(new Color(0, 0, 0)));
	    table.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    table.setModel(new DefaultTableModel(
	    	new Object[][] {
	    		{"Stream", "Rank", "Name", "Roll No.", "Division", "Score", "Percent"},
	    		{null, "First", null, null, null, null, null},
	    		{"Science", "Second", null, null, null, null, null},
	    		{null, "Third", null, null, null, null, null},
	    		{null, null, null, null, null, null, null},
	    		{null, "First", null, null, null, null, null},
	    		{"Commerce", "Second", null, null, null, null, null},
	    		{null, "Third", null, null, null, null, null},
	    	},
	    	new String[] {
	    		"New column", "New column", "New column", "New column", "New column", "New column", "New column"
	    	}
	    ));
	    
	    JTableHeader header1 = table.getTableHeader();
	    header1.setPreferredSize(new Dimension(0,30));
	    header1.setFont(new Font("Dialog", Font.BOLD,16));   
	    
	    table.getColumnModel().getColumn(0).setPreferredWidth(70);       // Stream           
	    table.getColumnModel().getColumn(1).setPreferredWidth(50);       // Rank
	    table.getColumnModel().getColumn(2).setPreferredWidth(400);      // Name  
	    table.getColumnModel().getColumn(3).setPreferredWidth(70);       // Roll Number
	    table.getColumnModel().getColumn(4).setPreferredWidth(30);       // Division
	    table.getColumnModel().getColumn(5).setPreferredWidth(50);       // Total Score
	    table.getColumnModel().getColumn(6).setPreferredWidth(70);       // Percent
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    
	    table.setRowHeight(25); 
	    GridBagConstraints gbc_table = new GridBagConstraints();
	    gbc_table.insets = new Insets(0, 0, 5, 0);
	    gbc_table.gridwidth = 3;
	    gbc_table.fill = GridBagConstraints.BOTH;
	    gbc_table.gridx = 0;
	    gbc_table.gridy = 2;
	    frame.getContentPane().add(table, gbc_table);
	    
	    JLabel lblNewLabel_2 = new JLabel("SUBJECT TOPPERS");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	    gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_2.gridx = 0;
	    gbc_lblNewLabel_2.gridy = 3;
	    frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	    gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
	    gbc_scrollPane.fill = GridBagConstraints.BOTH;
	    gbc_scrollPane.gridwidth = 3;
	    gbc_scrollPane.gridx = 0;
	    gbc_scrollPane.gridy = 4;
	    frame.getContentPane().add(scrollPane, gbc_scrollPane);
	    
	    table_1 = new JTable();
	    scrollPane.setViewportView(table_1);
	    table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
	    table_1.setModel(new DefaultTableModel(
	    	new Object[][] {
	    		{"English (Com)", null, null, null, null},
	    		{"Hindi (Com)", null, null, null, null},
	    		{"Marathi (Com)", null, null, null, null},
	    		{"Tamil (Com)", null, null, null, null},
	    		{"Economics", null, null, null, null},
	    		{"Accountancy - BK", null, null, null, null},	    		
	    		{"Org. of Commerece", null, null, null, null},
	    		{"Secreterial Practice", null, null, null, null},
	    		{"Mathematics (Com)", null, null, null, null},
	    		{"English (Sci)", null, null, null, null},
	    		{"Hindi (Sci)", null, null, null, null},
	    		{"Marathi (Sci)", null, null, null, null},
	    		{"Tamil (Sci)", null, null, null, null},
	    		{"Physics", null, null, null, null},
	    		{"Chemistry", null, null, null, null},
	    		{"Mathematics", null, null, null, null},
	    		{"Biology", null, null, null, null},
	    		{"Electronics", null, null, null, null},
	    		{"Computer Science", null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    	},
	    	new String[] {
	    		"Subject", "Name", "Roll No.", "Division", "Score"
	    	}
	    ));
//	    table_1.setRowHeight(25);
	    
	    table_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
	    table_1.getColumnModel().getColumn(0).setPreferredWidth(120);       // SUBJECT           
	    table_1.getColumnModel().getColumn(1).setPreferredWidth(400);      // NAME
	    table_1.getColumnModel().getColumn(2).setPreferredWidth(70);       // ROLL NUMBER  
	    table_1.getColumnModel().getColumn(3).setPreferredWidth(20);       // DIVISION
	    table_1.getColumnModel().getColumn(4).setPreferredWidth(50);       // SCORE   
	    
	    table_1.setRowHeight(25);   
//	    table_1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
//	    table_1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    
	    JTableHeader header2 = table_1.getTableHeader();
	    header2.setPreferredSize(new Dimension(0,30));
	    header2.setFont(new Font("Dialog", Font.BOLD,16));
	   

	    
	    JButton btnNewButton = new JButton("PRINT");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		okButtonAction();
	    		String EngMarks = GetData(SpreadMRKListview.getTable(),5, 28).toString();
				show(EngMarks);
	    		
	    	}
	    });
	    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	    gbc_btnNewButton.gridwidth = 2;
	    gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
	    gbc_btnNewButton.gridx = 0;
	    gbc_btnNewButton.gridy = 6;
	    frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	    
	    JButton btnNewButton_1 = new JButton("OK");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		System.exit(0);
	    		okButtonAction();
	    	}
	    });                    
	    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	    gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
	    gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
	    gbc_btnNewButton_1.gridx = 2;
	    gbc_btnNewButton_1.gridy = 6;
	    frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);   	   
		
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
