import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Freeze extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.JTable freezeTable;
    private int fixedColumns = 2;//number of colums to be freezed
  
    public Freeze() {
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane);
      
        setPreferredSize(new Dimension(1360,600));   
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      
        Object[][] data = new String[25][40];
        Object[] headers =new String[26];
        tableModel = new DefaultTableModel(data, headers);
        jTable.setModel(tableModel);
      
        jTable.setAutoCreateColumnsFromModel( false );
        for (int i=0; i<jTable.getColumnCount(); i++){
            jTable.getColumnModel().getColumn(i).setMinWidth(50);
        }
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                //  Keep freezeTable in sync with the jTable
                if ("selectionModel".equals(e.getPropertyName())) {
                    freezeTable.setSelectionModel(jTable.getSelectionModel());
                }

                if ("dataModel".equals(e.getPropertyName())) {
                    freezeTable.setModel(jTable.getModel());
                }
            }
        });
      
       // for(int i=0;i<100;i++)
       // tableModel.addRow(new Object[]{"","","","",""});
        
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
    }
  
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Freeze().setVisible(true);
            }
        });
    }
}
