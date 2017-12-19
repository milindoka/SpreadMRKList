package in.peecee.spreadMRKList;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

import javax.swing.plaf.basic.*;

//import jp.gr.java_conf.tame.swing.table.*;

/**
 * @version 1.0 11/09/98
 */
public class SpreadMRKListView extends javax.swing.JFrame {
	
	    private JLabel lblPrinter;
	    private JButton btnPrint;      
	    private JButton btnCansel;
	    private JButton btnSetPrinter;
	    private JButton btnSearch;
	    private JTextField search;
	    private JButton btnSave;
	    private JButton btnLoad;	  
	    private JButton btnProcess;
	    private JTable table;
	    private JScrollPane scrollPane;

	SpreadMRKListView() {
	
      setTitle("Attandance Sheet Maker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize( 1260, 700 );
      setResizable(false);
      setLocationRelativeTo(null);
      setVisible(true);           

    DefaultTableModel model = new DefaultTableModel();
    model.setDataVector(new Object[][]{
      {"","","","","","","","","","","","","","","","","","","","","","","","","","","","",""}},
//      {"A","B","C","D","E","F","H","","","","","","","",""},
//      {"x","y","z","p","q","r","j","","","","","","","",""},
//      {"X","Y","Z","P","Q","R","K","","","","","","","",""}},
      
    new Object[]{"Sr.No","Roll No","Div","Name","U1","T1","U2","T2","U1","T1","U2","T2","U1","T1","U2","T2",
    		                        "U1","T1","U2","T2","U1","T1","U2","T2","U1","T1","U2","T2","Total"});

      table = new JTable( model ) {
      protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
      }
    };
    
//    int n = 1500;
//	ResizeTable(table,n);
    
    TableColumnModel cm = table.getColumnModel();
//    ColumnGroup RollNo = new ColumnGroup("Roll No");
//    RollNo.add(cm.getColumn(1));
    table.setRowHeight(25);
    table.setFont(new Font("Times New Roman", Font.BOLD, 12));
//    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
//    table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    table.getColumnModel().getColumn(0).setPreferredWidth(100);              //  serial Numbers
    table.getColumnModel().getColumn(1).setPreferredWidth(125);              //  Roll Numbers
    table.getColumnModel().getColumn(2).setPreferredWidth(50);               //  Division
    table.getColumnModel().getColumn(3).setPreferredWidth(800);              //  Names of students
    
    ColumnGroup Eng = new ColumnGroup("ENG");
    Eng.add(cm.getColumn(4));
    Eng.add(cm.getColumn(5));
    Eng.add(cm.getColumn(6));
    Eng.add(cm.getColumn(7));
    
    ColumnGroup SL = new ColumnGroup("Sec Lang");
    SL.add(cm.getColumn(8));
    SL.add(cm.getColumn(9));
    SL.add(cm.getColumn(10));
    SL.add(cm.getColumn(11));
    
    ColumnGroup Sub1 = new ColumnGroup("SUB1");
    Sub1.add(cm.getColumn(12));
    Sub1.add(cm.getColumn(13));
    Sub1.add(cm.getColumn(14));
    Sub1.add(cm.getColumn(15));
    
    
    ColumnGroup Sub2 = new ColumnGroup("SUB2");
    Sub2.add(cm.getColumn(16));
    Sub2.add(cm.getColumn(17));
    Sub2.add(cm.getColumn(18));
    Sub2.add(cm.getColumn(19));
    
    
    ColumnGroup Sub3 = new ColumnGroup("SUB3");
    Sub3.add(cm.getColumn(20));
    Sub3.add(cm.getColumn(21));
    Sub3.add(cm.getColumn(22));
    Sub3.add(cm.getColumn(23));
    
    
    ColumnGroup Sub4 = new ColumnGroup("SUB4");
    Sub4.add(cm.getColumn(24));
    Sub4.add(cm.getColumn(25));
    Sub4.add(cm.getColumn(26));
    Sub4.add(cm.getColumn(27));
    
    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
//    header.addColumnGroup(RollNo);
    header.addColumnGroup(Eng);
    header.addColumnGroup(SL);
    header.addColumnGroup(Sub1);
    header.addColumnGroup(Sub2);
    header.addColumnGroup(Sub3);
    header.addColumnGroup(Sub4);
    
    JScrollPane scroll = new JScrollPane( table );
    getContentPane().add( scroll );

    header.revalidate(); 
    
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

    btnProcess = new JButton("Process");
    btnProcess.setFont(new Font("Times New Roman", Font.BOLD, 14));
  //  btnLoad.setPreferredSize(new Dimension(115, 25));
    southPanel.add(btnProcess);    
    
    
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
    
    lblPrinter = new JLabel("Printer Name ");
    lblPrinter.setFont(new Font("Times New Roman", Font.BOLD, 14));
    southPanel.add(lblPrinter);
    
    btnPrint = new JButton("Print");
    btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
  //  btnPrint.setPreferredSize(new Dimension(115, 25));
    southPanel.add(btnPrint);
    
    btnCansel = new JButton("Cansel");
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
	  
	public JButton getSaveButton(){
	        return btnSave;
	}
	    	    
	public JButton getLoadButton(){
	        return btnLoad;
	}
	
	public JButton getProcessButton(){
        return btnProcess;
    }
	
	public JButton getPrintButton(){
        return btnPrint;
    }
	
	public JButton getSetPrinterButton(){
        return btnSetPrinter;
    }

	public JButton getSearchButton(){
        return btnSearch;
    }
		
	 public JTable getTable(){
	        return table;
	    }
	 
	 public JTextField Search(){
	        return search;
	    }
	    
	   public void setPrinterLabel(String text){
	    	lblPrinter.setText("   "+text);
	    }
	   
	    public JButton getCanselButton(){
	        return btnCansel;
	    }
	    
  public class ColumnGroup {
	    protected TableCellRenderer renderer;
	    protected Vector v;
	    protected String text;
	    protected int margin=0;
	    public ColumnGroup(String text) {this(null,text);}

	    public ColumnGroup(TableCellRenderer renderer,String text) {
	      if (renderer == null) {
	        this.renderer = new DefaultTableCellRenderer() {
	  	public Component getTableCellRendererComponent(JTable table, Object value,
	                           boolean isSelected, boolean hasFocus, int row, int column) {
	  	  JTableHeader header = table.getTableHeader();
	  	  if (header != null) {
	  	    setForeground(header.getForeground());
	  	    setBackground(header.getBackground());
	  	    setFont(header.getFont());
	  	  }
	            setHorizontalAlignment(JLabel.CENTER);
	            setText((value == null) ? "" : value.toString());
	  	  setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	  	  return this;
	          }
	        };
	      } else {
	        this.renderer = renderer;
	      }
	      this.text = text;
	      v = new Vector();
	    }

	    /**
	     * @param obj    TableColumn or ColumnGroup
	     */
	    public void add(Object obj) {
	      if (obj == null) { return; }
	      v.addElement(obj);
	    }
	    
	    /**
	     * @param c    TableColumn
	     * @param v    ColumnGroups
	     */
	    public Vector getColumnGroups(TableColumn c, Vector g) {
	      g.addElement(this);
	      if (v.contains(c)) return g;
	      Enumeration en = v.elements();
	      while (en.hasMoreElements()) {
	        Object obj = en.nextElement();
	        if (obj instanceof ColumnGroup) {
	          Vector groups =
	            (Vector)((ColumnGroup)obj).getColumnGroups(c,(Vector)g.clone());
	          if (groups != null) return groups;
	        }
	      }
	      return null;
	    }

	    public TableCellRenderer getHeaderRenderer() {
	      return renderer;
	    }

	    public void setHeaderRenderer(TableCellRenderer renderer) {
	      if (renderer != null) {
	        this.renderer = renderer;
	      }
	    }

	    public Object getHeaderValue() {
	      return text;
	    }

	    public Dimension getSize(JTable table) {
	      Component comp = renderer.getTableCellRendererComponent(
	          table, getHeaderValue(), false, false,-1, -1);
	      int height = comp.getPreferredSize().height;
	      int width  = 0;
	      Enumeration en = v.elements();
	      while (en.hasMoreElements()) {
	        Object obj = en.nextElement();
	        if (obj instanceof TableColumn) {
	          TableColumn aColumn = (TableColumn)obj;
	          width += aColumn.getWidth();
	        } else {
	          width += ((ColumnGroup)obj).getSize(table).width;
	        }
	      }
	      return new Dimension(width, height);
	    }

	    public void setColumnMargin(int margin) {
	    }
  }

  public class GroupableTableHeader extends JTableHeader {
	  private static final String uiClassID = "GroupableTableHeaderUI";
	  protected Vector columnGroups = null;

	  public GroupableTableHeader(TableColumnModel model) {
	    super(model);
	    setReorderingAllowed(false);
	  }

	  public void setReorderingAllowed(boolean b) {
	    reorderingAllowed = false;
	  }

	  public void addColumnGroup(ColumnGroup g) {
	    if (columnGroups == null) {
	      columnGroups = new Vector();
	    }
	    columnGroups.addElement(g);
	  }

	  public Enumeration getColumnGroups(TableColumn col) {
	    if (columnGroups == null) return null;
	    Enumeration en = columnGroups.elements();
	    while (en.hasMoreElements()) {
	      ColumnGroup cGroup = (ColumnGroup)en.nextElement();
	      Vector v_ret = (Vector)cGroup.getColumnGroups(col,new Vector());
	      if (v_ret != null) {
		return v_ret.elements();
	      }
	    }
	    return null;
	  }

	  @Override
	public void updateUI() {
	    setUI(new GroupableTableHeaderUI());

	      TableCellRenderer tablecellrenderer = getDefaultRenderer();
	      if(tablecellrenderer instanceof Component)
	          SwingUtilities.updateComponentTreeUI((Component)tablecellrenderer);

	}

	  public void setColumnMargin() {
	    if (columnGroups == null) return;
	    int columnMargin = getColumnModel().getColumnMargin();
	    Enumeration en = columnGroups.elements();
	    while (en.hasMoreElements()) {
	      ColumnGroup cGroup = (ColumnGroup)en.nextElement();
	      cGroup.setColumnMargin(0/*columnMargin*/);
	    }
	  }

	}

  public class GroupableTableHeaderUI extends BasicTableHeaderUI {

	  public void paint(Graphics g, JComponent c) {
	    Rectangle clipBounds = g.getClipBounds();
	    if (header.getColumnModel() == null) return;
	    ((GroupableTableHeader)header).setColumnMargin();
	    int column = 0;
	    Dimension size = header.getSize();
	    Rectangle cellRect  = new Rectangle(0, 0, size.width, size.height);
	    Hashtable h = new Hashtable();
	    int columnMargin = header.getColumnModel().getColumnMargin();

	    Enumeration enumeration = header.getColumnModel().getColumns();
	    while (enumeration.hasMoreElements()) {
	      cellRect.height = size.height;
	      cellRect.y      = 0;
	      TableColumn aColumn = (TableColumn)enumeration.nextElement();
	      Enumeration cGroups = ((GroupableTableHeader)header).getColumnGroups(aColumn);
	      if (cGroups != null) {
	        int groupHeight = 0;
	        while (cGroups.hasMoreElements()) {
	          ColumnGroup cGroup = (ColumnGroup)cGroups.nextElement();
	          Rectangle groupRect = (Rectangle)h.get(cGroup);
	          if (groupRect == null) {
	            groupRect = new Rectangle(cellRect);
	            Dimension d = cGroup.getSize(header.getTable());
	            groupRect.width  = d.width;
	            groupRect.height = d.height;
	            h.put(cGroup, groupRect);
	          }
	          paintCell(g, groupRect, cGroup);
	          groupHeight += groupRect.height;
	          cellRect.height = size.height - groupHeight;
	          cellRect.y      = groupHeight;
	        }
	      }
	      cellRect.width = aColumn.getWidth();
	      if (cellRect.intersects(clipBounds)) {
	        paintCell(g, cellRect, column);
	      }
	      cellRect.x += cellRect.width;
	      column++;
	    }
	  }

	  private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
	    TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
	    TableCellRenderer renderer = getRenderer(columnIndex);
	    Component component = renderer.getTableCellRendererComponent(
	      header.getTable(), aColumn.getHeaderValue(),false, false, -1, columnIndex);
	    rendererPane.add(component);
	    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
					cellRect.width, cellRect.height, true);
	  }

	  private void paintCell(Graphics g, Rectangle cellRect,ColumnGroup cGroup) {
	    TableCellRenderer renderer = cGroup.getHeaderRenderer();
	    Component component = renderer.getTableCellRendererComponent(
	      header.getTable(), cGroup.getHeaderValue(),false, false, -1, -1);
	    rendererPane.add(component);
	    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
					cellRect.width, cellRect.height, true);
	  }

	  private int getHeaderHeight() {
	    int height = 0;
	    TableColumnModel columnModel = header.getColumnModel();
	    for(int column = 0; column < columnModel.getColumnCount(); column++) {
	      TableColumn aColumn = columnModel.getColumn(column);
	      TableCellRenderer renderer = getRenderer(column);
	      Component comp = renderer.getTableCellRendererComponent(
	        header.getTable(), aColumn.getHeaderValue(), false, false,-1, column);
	      int cHeight = comp.getPreferredSize().height;
	      Enumeration en = ((GroupableTableHeader)header).getColumnGroups(aColumn);
	      if (en != null) {
	        while (en.hasMoreElements()) {
	          ColumnGroup cGroup = (ColumnGroup)en.nextElement();
	          cHeight += cGroup.getSize(header.getTable()).height;
	        }
	      }
	      height = Math.max(height, cHeight);
	    }
	    return height;
	  }

	  private TableCellRenderer getRenderer(int column){
		  TableColumnModel columnModel = header.getColumnModel();
		  TableCellRenderer renderer = null;
	      if(column<0 && column<columnModel.getColumnCount()) {
	    	  renderer = columnModel.getColumn(column).getHeaderRenderer();
	      }
	      if(renderer==null){
	    	  renderer = header.getDefaultRenderer();
	      }
	      return renderer;
	  }

	  private Dimension createHeaderSize(long width) {
	    TableColumnModel columnModel = header.getColumnModel();
	    width += columnModel.getColumnMargin() * columnModel.getColumnCount();
	    if (width > Integer.MAX_VALUE) {
	      width = Integer.MAX_VALUE;
	    }
	    return new Dimension((int)width, getHeaderHeight());
	  }

	  public Dimension getPreferredSize(JComponent c) {
	    long width = 0;
	    Enumeration enumeration = header.getColumnModel().getColumns();
	    while (enumeration.hasMoreElements()) {
	      TableColumn aColumn = (TableColumn)enumeration.nextElement();
	      width = width + aColumn.getPreferredWidth();
	    }
	    return createHeaderSize(width);
	  }
	}

  public void ResizeTable(JTable tablename,int numberofrows)
	   { DefaultTableModel model=(DefaultTableModel) tablename.getModel();
    int totalrows=tablename.getRowCount();
    int difference=numberofrows-totalrows;
    if(difference>0)
       {
        for(int i=0;i<difference;i++) model.addRow(new Object[]{" "});
       }  
    if(difference<0)
	   { difference=-difference;
    for(int i=0;i<difference;i++) model.removeRow(0);
    
	   }
		 
	 }
  
}
