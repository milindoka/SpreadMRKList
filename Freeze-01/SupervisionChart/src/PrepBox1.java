import javax.swing.*;

import java.awt.*;
// import javax.swing.JFrame;
//  import javax.swing.JLabel;


public class PrepBox1 {
	    
	    static JTextField subject = new JTextField(5);
	    static JTextField Blocks = new JTextField(5);
	    static JTextField NOS = new JTextField(5);
	    static JTextField ExamType = new JTextField(5);
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("SUP CHART");
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 4, 5, 10));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
//		frame.add(panel);
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcLabel1 = new GridBagConstraints();
		gbc.insets = new Insets (10, 10, 10, 10);
		
		JLabel Label1 = new JLabel ("Num Of Blocks");
		gbcLabel1.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(Label1, gbc);
		panel.add(Blocks);
		
		JLabel Label2 = new JLabel ("Num Of Supervisors");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(Label2, gbc);
		panel.add(NOS);
		
		JLabel Label3 = new JLabel ("Num Of Subjects");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(Label3, gbc);
		panel.add(subject);
		
//		JLabel Label4 = new JLabel ("EXam Type");
//		gbc.gridx = 0;
//		gbc.gridy = 6;
//		panel.add(Label4, gbc);
		panel.add(subject);
		
		JButton Button1 = new JButton("Continue");
		gbc.gridx = 0;
		gbc.gridy = 6;	
    	panel.add(Button1, gbc);
		

	}

}
