import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
 
public class KeyListen extends JFrame {
     
	  public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new KeyListen().setVisible(true);
	            }
	        });
	    }
	private JTable table;
    public KeyListen() {
        super("JTable Column Header Mouse Click Demo");     
        String[] columnNames = new String[] {"Title", "Author", "Published Date"};
        String[][] rowData = new String[][] {
            {"Spring in Action", "Craig Walls", "June 29th 2011"},          
        };                 
        table = new JTable(rowData, columnNames);       
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new TableHeaderMouseListener(table));
         
        add(new JScrollPane(table));
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 150);
        setLocationRelativeTo(null);       
    }     
      
    public class TableHeaderMouseListener extends MouseAdapter {
        
        private JTable table;
         
        public TableHeaderMouseListener(JTable table) {
            this.table = table;
        }
         
        public void mouseClicked(MouseEvent event) {
            Point point = event.getPoint();
            int column = table.columnAtPoint(point);
             
           String Name = JOptionPane.showInputDialog(table, "Column header #" + column + " is clicked");
           table.getColumnModel().getColumn(column).setHeaderValue(Name); 
             
            // do your real thing here...
        }
    }
    
}