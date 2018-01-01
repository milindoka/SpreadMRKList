package in.peecee.spreadMRKList;

import java.awt.Color;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ScoreCardButtons {

	JFrame frame = new JFrame();
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	private printRoutines prnRoutines = new printRoutines();
	private JButton SSPrintButton;
	
	public void showScoreButtons(){
		frame.validate();                
	    frame.setTitle("Score Card/Spread Sheet");
	    frame.setSize(450, 175);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
		
	}
	
	public ScoreCardButtons() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("CLICK TO ..... ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(10, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JButton currentPrintButton = new JButton("Print Current Marks Card");
		currentPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				prnRoutines.PrintCurrent();	
				
			}
		});
		currentPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_currentPrintButton = new GridBagConstraints();
		gbc_currentPrintButton.anchor = GridBagConstraints.EAST;
		gbc_currentPrintButton.insets = new Insets(10, 20, 5, 5);
		gbc_currentPrintButton.gridx = 2;
		gbc_currentPrintButton.gridy = 3;
		frame.getContentPane().add(currentPrintButton, gbc_currentPrintButton);
		
		JButton allPrintButton = new JButton("Print All Marks Cards");
		allPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prnRoutines.PrintAllMarksCards();
				
			}
		});
		allPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_allPrintButton = new GridBagConstraints();
		gbc_allPrintButton.insets = new Insets(10, 10, 5, 5);
		gbc_allPrintButton.gridx = 4;
		gbc_allPrintButton.gridy = 3;
		frame.getContentPane().add(allPrintButton, gbc_allPrintButton);
		
		SSPrintButton = new JButton("Print Spread Sheet");
		SSPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		SSPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prnRoutines.SpreadSheet();
				
//				String temp = "I am Fine";
//				show(temp);
//				show("I am Fine, too");
				
			}		
	    });                      
		
		GridBagConstraints gbc_SSPrintButton = new GridBagConstraints();
		gbc_SSPrintButton.anchor = GridBagConstraints.WEST;
		gbc_SSPrintButton.insets = new Insets(10, 45, 5, 5);
		gbc_SSPrintButton.gridx = 2;
		gbc_SSPrintButton.gridy = 5;
		frame.getContentPane().add(SSPrintButton, gbc_SSPrintButton);
		
		final JButton cancellButton = new JButton("Cancell");
		cancellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window win = SwingUtilities.getWindowAncestor(cancellButton);
			      if (win != null) {
			         win.dispose();
			}
		  }
			      
        });
		cancellButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_cancellButton = new GridBagConstraints();
		gbc_cancellButton.insets = new Insets(10, 10, 0, 5);
		gbc_cancellButton.gridx = 4;
		gbc_cancellButton.gridy = 5;
		frame.getContentPane().add(cancellButton, gbc_cancellButton);
		// TODO Auto-generated constructor stub
						
	}
	
	public JButton getSSButton(){
        return SSPrintButton;
    }

}
