
package in.peecee.spreadMRKList;

import javax.swing.SwingUtilities;

public class SpreadMRKListmain
{
    public static void main(String[] args) {           
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {
            	SpreadMRKListModel model = new SpreadMRKListModel();
            	SpreadMRKListView view = new SpreadMRKListView();
            	showstatistics Stats = new showstatistics();
            	SpreadMRKListController controller = new SpreadMRKListController(model, view, Stats);
            	controller.contol();
            }
        });  
    }
}