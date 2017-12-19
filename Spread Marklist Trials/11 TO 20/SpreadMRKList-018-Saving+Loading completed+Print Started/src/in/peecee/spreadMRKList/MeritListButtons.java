package in.peecee.spreadMRKList;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MeritListButtons {

	JFrame frame = new JFrame();
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	public void showMLButtons(){
		frame.validate();                
	    frame.setTitle("Score Card/Spread Sheet");
	    frame.setSize(450, 175);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
		
	}
	public MeritListButtons() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel MLLabel = new JLabel("CLICK TO ..... ");
		MLLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_MLLabel = new GridBagConstraints();
		gbc_MLLabel.gridwidth = 3;
		gbc_MLLabel.insets = new Insets(10, 0, 5, 5);
		gbc_MLLabel.gridx = 2;
		gbc_MLLabel.gridy = 1;
		frame.getContentPane().add(MLLabel, gbc_MLLabel);
		
		JButton MLScPrintButton = new JButton("Merit List Science / Commerce");       //  MLCom = Merit List Science
		MLScPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_MLScPrintButton = new GridBagConstraints();
		gbc_MLScPrintButton.anchor = GridBagConstraints.EAST;
		gbc_MLScPrintButton.insets = new Insets(10, 20, 5, 5);
		gbc_MLScPrintButton.gridx = 2;
		gbc_MLScPrintButton.gridy = 3;
		frame.getContentPane().add(MLScPrintButton, gbc_MLScPrintButton);
		
/*		JButton MLComPrintButton = new JButton("Merit List Commerce");    //  MLCom = Merit List Commerce 
		MLComPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_MLComPrintButton = new GridBagConstraints();
		gbc_MLComPrintButton.insets = new Insets(10, 10, 5, 5);
		gbc_MLComPrintButton.gridx = 4;
		gbc_MLComPrintButton.gridy = 3;
		frame.getContentPane().add(MLComPrintButton, gbc_MLComPrintButton);                       */
		
		JButton STPrintButton = new JButton("Subject Topper");        //  ST = Subject Topper
		STPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_STPrintButton = new GridBagConstraints();
		gbc_STPrintButton.anchor = GridBagConstraints.WEST;
		gbc_STPrintButton.insets = new Insets(10, 30, 5, 5);
		gbc_STPrintButton.gridx = 4;
		gbc_STPrintButton.gridy = 3;
		frame.getContentPane().add(STPrintButton, gbc_STPrintButton);
		
		final JButton cancellButton = new JButton("Cancell");
		cancellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window win = SwingUtilities.getWindowAncestor(cancellButton);
			      if (win != null) {
			         win.dispose();
			}
		  }
			      
        });
		cancellButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_cancellButton = new GridBagConstraints();
		gbc_cancellButton.insets = new Insets(10, 30, 0, 5);
		gbc_cancellButton.gridx = 2;
		gbc_cancellButton.gridy = 5;
		frame.getContentPane().add(cancellButton, gbc_cancellButton);
		// TODO Auto-generated constructor stub
						
	}

}


