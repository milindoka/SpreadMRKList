package in.peecee.spreadMRKList;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SpreadMRKListView extends javax.swing.JFrame {

    private static JLabel lblPrinter;
    private JButton btnPrint;      
    private JButton btnCansel;
    private JButton btnSetPrinter;
    private JButton btnSearch;
    private JTextField search;
    private JButton btnSave;
    private JButton btnLoad;
  
    private JTable table;
    private JScrollPane scrollPane;
    public DefaultTableModel model;
    
    public static void Show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void Show(int msg) {JOptionPane.showMessageDialog(null, msg);}
        
/*   public static void main(String[] args) { 
	
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				SpreadMRKListView window = new SpreadMRKListView();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}             */                                               
   String Order[]={"Sr.No","Roll No" , "ENG","MAR","TAM","HIN","ITE","MAT","PHY","CHE",
	               "BIO","SEP","ECO","BKE","OCM"};
   String Row[]={"T1","T2","U1","U2"};
   
   String OrderSc[]={ "Sr.No","Roll No","ENG","MAR","TAM","HIN","ITE","MAT"} ;     //,"PHY","CHE","BIO",
		                                                                          // "CS1", "CS2", "EL1", "EL2"};

   String OrderCom[]={"Sr.No","Roll No","ENG","MAR","TAM","HIN","ITE","MAT",
	                                    "SEP","ECO","BKE","OCM"};

          
    public SpreadMRKListView(){

        setTitle("Attandance Sheet Maker");           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1260,700);
//        setSize(553,660);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);           
                 
     ///////     E A S T       P A N E L  -  FRO TABLE     ////
        
        JPanel northPanel = new JPanel();
        
        Object[][] data = 	{ {" "} }; /////define data types    
        
        model = new DefaultTableModel(data, OrderSc);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        getContentPane().add( new JScrollPane(table), BorderLayout.CENTER);
        table.setRowHeight(20);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(25);
        table.getColumnModel().getColumn(2).setMinWidth(125);
        table.getColumnModel().getColumn(3).setMinWidth(125);
        table.getColumnModel().getColumn(4).setMinWidth(125);
        table.getColumnModel().getColumn(5).setMinWidth(125);
        table.getColumnModel().getColumn(6).setMinWidth(125);
        table.getColumnModel().getColumn(7).setMinWidth(125);
       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
           
        northPanel.add(scrollPane);
        getContentPane().add(northPanel, BorderLayout.NORTH);        
        
//        ResizeTable(table,35);
         

  JPanel southPanel = new JPanel();  
  southPanel.setLayout(new GridBagLayout());
    
  btnSave = new JButton("Save");    
  btnSave.setToolTipText("Save");
  btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
  southPanel.add(btnSave);
  
  btnLoad = new JButton("Load");
  btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 14));
//  btnLoad.setPreferredSize(new Dimension(115, 25));
  southPanel.add(btnLoad);    

  btnSearch = new JButton("Search");        
  btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
//  btnSearch.setMinimumSize(new Dimension(50,100));
  southPanel.add(btnSearch);
  
  search = new JTextField();
  search.setFont(new Font("Times New Roman", Font.BOLD, 14));
  search.setColumns(8);
  southPanel.add(search);

  btnSetPrinter = new JButton("Set Printer");
  btnSetPrinter.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent arg0) {
  		
  		
  	}
  });
  btnSetPrinter.setFont(new Font("Times New Roman", Font.BOLD, 14));
//  btnSetPrinter.setPreferredSize(new Dimension(115, 25));
  southPanel.add(btnSetPrinter);
  
  lblPrinter = new JLabel("Printer Name");
  lblPrinter.setFont(new Font("Times New Roman", Font.BOLD, 14));
  southPanel.add(lblPrinter);
  
  btnPrint = new JButton("Print");
  btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
//  btnPrint.setPreferredSize(new Dimension(115, 25));
  southPanel.add(btnPrint);
  
  btnCansel = new JButton("Cancel");
  btnCansel.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent arg0) {
  		System.exit(0);
  	}
  });
  btnCansel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//  btnCansel.setPreferredSize(new Dimension(115, 25));
  southPanel.add(btnCansel);
  
  getContentPane().add(southPanel, BorderLayout.SOUTH);
  
 }
    
    public JButton getCanselButton(){
        return btnCansel;
    }
    
    public JButton getPrintButton(){
        return btnPrint;
    }
    
    public JButton getSaveButton(){
        return btnSave;
    }
    
    public JButton getLoadButton(){
        return btnLoad;
    }
    
    public JButton getSearchButton(){
        return btnSearch;
    }
    
    public JButton getSetPrinterButton(){
        return btnSetPrinter;
    }
    
    public JTable getTable(){
        return table;
    }
    
   public static void setPrinterLabel(String text){
    	lblPrinter.setText(" "+text+" ");
    }              
    
 
}