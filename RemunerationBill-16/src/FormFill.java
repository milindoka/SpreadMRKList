

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JCheckBox;

public class FormFill extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6184290585772556149L;

	BillPrint Bill;
	
	protected static final String Subject = null;
	private JFrame frame;
/*	private JTextField Name;
	private JTextField Address1;
	private JTextField Address2;
	private JTextField Address3;
	private JTextField Address4;        */
// 	String Addline,Addline1,Addline2,Addline3,Addline4;
	private JTextField ExaminerName;
	private JTextField subject;
	private JTextField year;
	private JTextField COE;                //  COE  =  Center Of Exam
	private JTextField IndexOfEC;           //  IndexOfEC  =  Index Number Of Exam Center  
	private JTextField NODE;               //  NODE  =  Number Of Days Of Examination  
	private JTextField NOSE;               //  NOSE  =  Number Of Students Examined
	private JTextField RemPerStu;          //  RemPerStu  =  Remuneration Per student
	private JTextField AddLine1;           //  AddLine1  =  Address Line 1
	private JTextField AddLine2;
	private JTextField AddLine3;
	private JTextField AddLine4;
	private JTextField CollNemOfExaminer;  //  College Name Of Examiner
	private JTextField ColIndCdOfExmner;   //  //  ColIndCdOfExmner  =  College Index Code Of Examiner
	private JTextField textField;
	private JTextField textField_1;
	private JTextField StartDate;
    private JTextField EndDate;
    private JTextField ExceptOn;
    
    ArrayList<String> strArray = new ArrayList<String>();
	
	public static void show(JTextField text) {JOptionPane.showMessageDialog(null, text);}
	public void Show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	
	/**
	 * Launch the application.
	 */
	public static void RemPlate(String[] args) {
		try {
			  FormFill dialog = new FormFill();
			  dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			  dialog.setVisible(true);
		    } catch (Exception e) { e.printStackTrace(); }
	}

	/**
	 * Create the dialog.
	 */
	public FormFill() {
		
		Bill = BillPrint.getInstance();
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 14));
		setTitle("Practical - Remuneration Bill");
		setBounds(100, 0, 750, 575);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 252, 104, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Practical - Remuneration Form");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Name of the Examiner");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		ExaminerName = new JTextField();
		GridBagConstraints gbc_ExaminerName = new GridBagConstraints();
		gbc_ExaminerName.insets = new Insets(0, 0, 5, 5);
		gbc_ExaminerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExaminerName.gridx = 1;
		gbc_ExaminerName.gridy = 2;
		getContentPane().add(ExaminerName, gbc_ExaminerName);
		ExaminerName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Start Date");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 2;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Subject");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		subject = new JTextField();
		GridBagConstraints gbc_subject = new GridBagConstraints();
		gbc_subject.fill = GridBagConstraints.HORIZONTAL;
		gbc_subject.insets = new Insets(0, 0, 5, 5);
		gbc_subject.gridx = 1;
		gbc_subject.gridy = 3;
		getContentPane().add(subject, gbc_subject);
		subject.setColumns(10);
		
		StartDate = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		getContentPane().add(StartDate, gbc_textField);
		StartDate.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Year");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		year = new JTextField();
		year.setColumns(10);
		GridBagConstraints gbc_year = new GridBagConstraints();
		gbc_year.fill = GridBagConstraints.HORIZONTAL;
		gbc_year.insets = new Insets(0, 0, 5, 5);
		gbc_year.gridx = 1;
		gbc_year.gridy = 4;
		getContentPane().add(year, gbc_year);
		
		JLabel lblNewLabel_5 = new JLabel("End Date");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 4;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Except on");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 6;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		ExceptOn = new JTextField();
		GridBagConstraints gbc_ExceptOn = new GridBagConstraints();
		gbc_ExceptOn.insets = new Insets(0, 0, 5, 0);
		gbc_ExceptOn.fill = GridBagConstraints.BOTH;
		gbc_ExceptOn.gridx = 2;
		gbc_ExceptOn.gridy = 7;
		getContentPane().add(ExceptOn, gbc_ExceptOn);
		ExceptOn.setColumns(10);
		
		JLabel lblCenterOfExamination = new JLabel("Center of Examination");
		lblCenterOfExamination.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterOfExamination = new GridBagConstraints();
		gbc_lblCenterOfExamination.anchor = GridBagConstraints.EAST;
		gbc_lblCenterOfExamination.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterOfExamination.gridx = 0;
		gbc_lblCenterOfExamination.gridy = 5;
		getContentPane().add(lblCenterOfExamination, gbc_lblCenterOfExamination);
		
		COE = new JTextField();                    //  COE  =  Center Of Exam
		COE.setColumns(10);
		GridBagConstraints gbc_COE = new GridBagConstraints();
		gbc_COE.insets = new Insets(0, 0, 5, 5);
		gbc_COE.fill = GridBagConstraints.HORIZONTAL;
		gbc_COE.gridx = 1;
		gbc_COE.gridy = 5;
		getContentPane().add(COE, gbc_COE);
		
		EndDate = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 5;
		getContentPane().add(EndDate, gbc_textField_1);
		EndDate.setColumns(10);
		
		JLabel lblCollegeIndexNumber = new JLabel("Index Number of Exam Center");
		lblCollegeIndexNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblCollegeIndexNumber = new GridBagConstraints();
		gbc_lblCollegeIndexNumber.anchor = GridBagConstraints.EAST;
		gbc_lblCollegeIndexNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCollegeIndexNumber.gridx = 0;
		gbc_lblCollegeIndexNumber.gridy = 6;
		getContentPane().add(lblCollegeIndexNumber, gbc_lblCollegeIndexNumber);
		
		IndexOfEC = new JTextField();
		IndexOfEC.setColumns(10);
		GridBagConstraints gbc_IndexOfEC = new GridBagConstraints();
		gbc_IndexOfEC.fill = GridBagConstraints.HORIZONTAL;
		gbc_IndexOfEC.insets = new Insets(0, 0, 5, 5);
		gbc_IndexOfEC.gridx = 1;
		gbc_IndexOfEC.gridy = 6;
		getContentPane().add(IndexOfEC, gbc_IndexOfEC);
		
		JLabel lblNoOfDays = new JLabel("No. Of days of Exam");
		lblNoOfDays.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNoOfDays = new GridBagConstraints();
		gbc_lblNoOfDays.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfDays.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfDays.gridx = 0;
		gbc_lblNoOfDays.gridy = 7;
		getContentPane().add(lblNoOfDays, gbc_lblNoOfDays);
		
		NODE = new JTextField();
		NODE.setColumns(10);
		GridBagConstraints gbc_NODE = new GridBagConstraints();
		gbc_NODE.fill = GridBagConstraints.HORIZONTAL;
		gbc_NODE.insets = new Insets(0, 0, 5, 5);
		gbc_NODE.gridx = 1;
		gbc_NODE.gridy = 7;
		getContentPane().add(NODE, gbc_NODE);
		
		JLabel lblNoOfStudents = new JLabel("No. Of Students Examined");
		lblNoOfStudents.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNoOfStudents = new GridBagConstraints();
		gbc_lblNoOfStudents.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfStudents.gridx = 0;
		gbc_lblNoOfStudents.gridy = 8;
		getContentPane().add(lblNoOfStudents, gbc_lblNoOfStudents);
		
		NOSE = new JTextField();
		NOSE.setColumns(10);
		GridBagConstraints gbc_NOSE = new GridBagConstraints();
		gbc_NOSE.fill = GridBagConstraints.HORIZONTAL;
		gbc_NOSE.insets = new Insets(0, 0, 5, 5);
		gbc_NOSE.gridx = 1;
		gbc_NOSE.gridy = 8;
		getContentPane().add(NOSE, gbc_NOSE);
		
		JLabel lblRemunerationPerStudent = new JLabel("Remuneration per Student");
		lblRemunerationPerStudent.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblRemunerationPerStudent = new GridBagConstraints();
		gbc_lblRemunerationPerStudent.anchor = GridBagConstraints.EAST;
		gbc_lblRemunerationPerStudent.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemunerationPerStudent.gridx = 0;
		gbc_lblRemunerationPerStudent.gridy = 9;
		getContentPane().add(lblRemunerationPerStudent, gbc_lblRemunerationPerStudent);
		
		RemPerStu = new JTextField();
		RemPerStu.setColumns(10);
		GridBagConstraints gbc_RemPerStu = new GridBagConstraints();
		gbc_RemPerStu.fill = GridBagConstraints.HORIZONTAL;
		gbc_RemPerStu.insets = new Insets(0, 0, 5, 5);
		gbc_RemPerStu.gridx = 1;
		gbc_RemPerStu.gridy = 9;
		getContentPane().add(RemPerStu, gbc_RemPerStu);
		
		JLabel lblPostalAddress = new JLabel("Postal Address");
		lblPostalAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblPostalAddress = new GridBagConstraints();
		gbc_lblPostalAddress.anchor = GridBagConstraints.EAST;
		gbc_lblPostalAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalAddress.gridx = 0;
		gbc_lblPostalAddress.gridy = 10;
		getContentPane().add(lblPostalAddress, gbc_lblPostalAddress);
		
		AddLine1 = new JTextField();
		AddLine1.setColumns(10);
		GridBagConstraints gbc_AddLine1 = new GridBagConstraints();
		gbc_AddLine1.insets = new Insets(0, 0, 5, 5);
		gbc_AddLine1.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddLine1.gridx = 1;
		gbc_AddLine1.gridy = 10;
		getContentPane().add(AddLine1, gbc_AddLine1);
		
		AddLine2 = new JTextField();
		AddLine2.setColumns(10);
		GridBagConstraints gbc_AddLine2 = new GridBagConstraints();
		gbc_AddLine2.insets = new Insets(0, 0, 5, 5);
		gbc_AddLine2.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddLine2.gridx = 1;
		gbc_AddLine2.gridy = 11;
		getContentPane().add(AddLine2, gbc_AddLine2);
		
		AddLine3 = new JTextField();
		AddLine3.setColumns(10);
		GridBagConstraints gbc_AddLine3 = new GridBagConstraints();
		gbc_AddLine3.insets = new Insets(0, 0, 5, 5);
		gbc_AddLine3.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddLine3.gridx = 1;
		gbc_AddLine3.gridy = 12;
		getContentPane().add(AddLine3, gbc_AddLine3);
		
		AddLine4 = new JTextField();
		AddLine4.setColumns(10);
		GridBagConstraints gbc_AddLine4 = new GridBagConstraints();
		gbc_AddLine4.insets = new Insets(0, 0, 5, 5);
		gbc_AddLine4.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddLine4.gridx = 1;
		gbc_AddLine4.gridy = 13;
		getContentPane().add(AddLine4, gbc_AddLine4);
		
		JLabel lblNameOfThe = new JLabel("Name of the Junior College");
		lblNameOfThe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblNameOfThe = new GridBagConstraints();
		gbc_lblNameOfThe.anchor = GridBagConstraints.EAST;
		gbc_lblNameOfThe.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOfThe.gridx = 0;
		gbc_lblNameOfThe.gridy = 14;
		getContentPane().add(lblNameOfThe, gbc_lblNameOfThe);
		
		JLabel lblWhereExaminerIs = new JLabel("where Examiner is Teachng");
		lblWhereExaminerIs.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblWhereExaminerIs = new GridBagConstraints();
		gbc_lblWhereExaminerIs.anchor = GridBagConstraints.EAST;
		gbc_lblWhereExaminerIs.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhereExaminerIs.gridx = 0;
		gbc_lblWhereExaminerIs.gridy = 15;
		getContentPane().add(lblWhereExaminerIs, gbc_lblWhereExaminerIs);
		
		CollNemOfExaminer = new JTextField();
		CollNemOfExaminer.setColumns(10);
		GridBagConstraints gbc_CollNemOfExaminer = new GridBagConstraints();
		gbc_CollNemOfExaminer.insets = new Insets(0, 0, 5, 5);
		gbc_CollNemOfExaminer.fill = GridBagConstraints.HORIZONTAL;
		gbc_CollNemOfExaminer.gridx = 1;
		gbc_CollNemOfExaminer.gridy = 15;
		getContentPane().add(CollNemOfExaminer, gbc_CollNemOfExaminer);
		
		JLabel lblCollegeIndexCode = new JLabel("College Index Code of Examiner");
		lblCollegeIndexCode.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblCollegeIndexCode = new GridBagConstraints();
		gbc_lblCollegeIndexCode.anchor = GridBagConstraints.EAST;
		gbc_lblCollegeIndexCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCollegeIndexCode.gridx = 0;
		gbc_lblCollegeIndexCode.gridy = 16;
		getContentPane().add(lblCollegeIndexCode, gbc_lblCollegeIndexCode);
		
		ColIndCdOfExmner = new JTextField();
		ColIndCdOfExmner.setColumns(10);
		GridBagConstraints gbc_ColIndCdOfExmner = new GridBagConstraints();
		gbc_ColIndCdOfExmner.fill = GridBagConstraints.HORIZONTAL;
		gbc_ColIndCdOfExmner.insets = new Insets(0, 0, 5, 5);
		gbc_ColIndCdOfExmner.gridx = 1;
		gbc_ColIndCdOfExmner.gridy = 16;
		getContentPane().add(ColIndCdOfExmner, gbc_ColIndCdOfExmner);		
		
		JLabel lblWhatToPrint = new JLabel("Which one you want to Print");
		lblWhatToPrint.setFont(new Font("Times New Roman", Font.BOLD, 17));
		GridBagConstraints gbc_lblWhatToPrint = new GridBagConstraints();
		gbc_lblWhatToPrint.anchor = GridBagConstraints.EAST;
		gbc_lblWhatToPrint.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhatToPrint.gridx = 0;
		gbc_lblWhatToPrint.gridy = 17;
		getContentPane().add(lblWhatToPrint, gbc_lblWhatToPrint);
		
///////  C H E C K B O  X E S     //////////
		
		final JCheckBox chckbxFirstPage = new JCheckBox("First Page of Remuneration Bill");
		chckbxFirstPage.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_chckbxFirstPage = new GridBagConstraints();
		gbc_chckbxFirstPage.anchor = GridBagConstraints.EAST;
		gbc_chckbxFirstPage.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFirstPage.gridx = 0;
		gbc_chckbxFirstPage.gridy = 18;
		getContentPane().add(chckbxFirstPage, gbc_chckbxFirstPage);
		
		final JCheckBox chckbxSecondPage = new JCheckBox("Second Page of Remuneration Bill");
		chckbxSecondPage.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_chckbxSecondPage = new GridBagConstraints();
		gbc_chckbxSecondPage.anchor = GridBagConstraints.WEST;
		gbc_chckbxSecondPage.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSecondPage.gridx = 1;
		gbc_chckbxSecondPage.gridy = 18;
		getContentPane().add(chckbxSecondPage, gbc_chckbxSecondPage);
		
		final JCheckBox chckbxRelievingOrder = new JCheckBox("Relieving Order");
		chckbxRelievingOrder.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_chckbxRelievingOrder = new GridBagConstraints();
		gbc_chckbxRelievingOrder.anchor = GridBagConstraints.WEST;
		gbc_chckbxRelievingOrder.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxRelievingOrder.gridx = 2;
		gbc_chckbxRelievingOrder.gridy = 18;
		getContentPane().add(chckbxRelievingOrder, gbc_chckbxRelievingOrder);
	
//////      B U T T O N S    /////
		
		final JButton btnLoad = new JButton("LOAD");
		btnLoad.setPreferredSize(new Dimension(100, 25));
		btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.anchor = GridBagConstraints.EAST;
		gbc_btnLoad.fill = GridBagConstraints.VERTICAL;
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 19;
		getContentPane().add(btnLoad, gbc_btnLoad);
				
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fyle = "";
				JFileChooser chooser = new JFileChooser();
		        chooser.setMultiSelectionEnabled(true);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        		"RemunerationBill", "rmb");
		        chooser.setFileFilter(filter);
//		        chooser.setCurrentDirectory(new File("E:/RemunertionBill"));
		        chooser.setCurrentDirectory(new File("/home/prahlad/Prahlad/RemBILL"));
		       
		        int option = chooser.showOpenDialog(btnLoad);
		        
		        if (option == JFileChooser.APPROVE_OPTION)
		          {
		            File[] sf = chooser.getSelectedFiles();
		            String filelist = "nothing";
		            if (sf.length > 0) filelist = sf[0].getName();
		            for (int i = 1; i < sf.length; i++) 
		            { filelist += ", " + sf[i].getName(); }
		            fyle=sf[0].getPath();
		            if (!fyle.endsWith(".rmb")) fyle+= ".rmb";
				    LoadFile(fyle);                                    //Load File is called here
				 }  
			  }	
				
			
		});

					
		final JButton btnSave = new JButton("SAVE");
		btnSave.setPreferredSize(new Dimension(100, 25));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.fill = GridBagConstraints.VERTICAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 19;
		getContentPane().add(btnSave, gbc_btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//	Saving Code here
				
				String fyle="";
  				JFileChooser chooser = new JFileChooser();
  	            chooser.setMultiSelectionEnabled(true);
  	            FileNameExtensionFilter filter = new FileNameExtensionFilter(
  	                    "RemunerationBill", "rmb");
  	            chooser.setFileFilter(filter);
//  	            chooser.setCurrentDirectory(new File("E:/RemunertionBill"));
	            chooser.setCurrentDirectory(new File("/home/prahlad/Prahlad/RemBILL"));
  	            int option = chooser.showSaveDialog(btnSave);

  	            if (option == JFileChooser.APPROVE_OPTION)
  	             {
  	                File[] sf = chooser.getSelectedFiles();
  	                String filelist = "nothing";
  	                if (sf.length > 0) filelist = sf[0].getName();
  	                for (int i = 1; i < sf.length; i++) 
  	                  {
  	                    filelist += ", " + sf[i].getName();
  	                  }
  	                fyle=sf[0].getPath();
  	                
  	                if (!fyle.endsWith(".rmb")) fyle+= ".rmb";

  	                SaveToFile(fyle);                             //Save to File is called here
  	             }  			 	              	  
            }	   
				
			
		});						
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setPreferredSize(new Dimension(100, 25));
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.fill = GridBagConstraints.VERTICAL;
		gbc_btnPrint.anchor = GridBagConstraints.EAST;
		gbc_btnPrint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrint.gridx = 0;
		gbc_btnPrint.gridy = 20;
		getContentPane().add(btnPrint, gbc_btnPrint);
		
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
	            Bill.setDetails(ExaminerName.getText(), subject.getText(), year.getText(), IndexOfEC.getText(), 
	            		        COE.getText(),NODE.getText(), NOSE.getText(), RemPerStu.getText(), 
	            		        CollNemOfExaminer.getText(), ColIndCdOfExmner.getText(),
	            		        AddLine1.getText(), AddLine2.getText(), AddLine3.getText(),AddLine4.getText(),
	            		        StartDate.getText(),  EndDate.getText(), ExceptOn.getText());

	            if(chckbxFirstPage.isSelected())      {  Bill.Print(); };
				if(chckbxSecondPage.isSelected())     { Bill.Print1(); };
				if(chckbxRelievingOrder.isSelected()) { Bill.Print2(); };
	            
//				Bill.Print();
				
/*				int result=JOptionPane.showOptionDialog(null, 
				        "Would You Like To Print Next Page ?", 
				        "Feedback", 
				        JOptionPane.OK_CANCEL_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, 
				        new String[]{"Yes I do", "No I don't"}, // this is the array
				        "default");
				if(result==JOptionPane.OK_OPTION) 	Bill.Print1();    */
										
/*				int result1=JOptionPane.showOptionDialog(null, 
				        "Would You Like To Print Relieving Order ?", 
				        "Feedback", 
				        JOptionPane.OK_CANCEL_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, 
				        new String[]{"Yes I do", "No I don't"}, // this is the array
				        "default");
				if(result1==JOptionPane.OK_OPTION) 	Bill.Print2();    */
				
			}
		});
		
		JButton btnCansel = new JButton("CANCEL");
		btnCansel.setPreferredSize(new Dimension(100, 25));
		btnCansel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_btnCansel = new GridBagConstraints();
		gbc_btnCansel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCansel.anchor = GridBagConstraints.EAST;
		gbc_btnCansel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCansel.gridx = 1;
		gbc_btnCansel.gridy = 20;
		getContentPane().add(btnCansel, gbc_btnCansel);
		
		btnCansel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
	
	protected void LoadFile(String fnem) {
		// TODO Auto-generated method stub
		BufferedReader reader = null;
        try { 	reader = new BufferedReader(new FileReader(fnem)); } 
        catch (FileNotFoundException e) { e.printStackTrace(); }
       
        strArray.clear();
        String line = null;                                                        
        try { while ((line = reader.readLine()) != null) { strArray.add(line); } } // Collecting all data in to strArray 
        catch (IOException e) {e.printStackTrace(); }
        int size = strArray.size(); 
        
        ExaminerName.setText(strArray.get(0));	 
        subject.setText(strArray.get(1));
        year.setText(strArray.get(2));
        COE.setText(strArray.get(3));
        IndexOfEC.setText(strArray.get(4));
        NODE.setText(strArray.get(5));
        NOSE.setText(strArray.get(6));
        RemPerStu.setText(strArray.get(7));
        AddLine1.setText(strArray.get(8));
        AddLine2.setText(strArray.get(9));
        AddLine3.setText(strArray.get(10));
        AddLine4.setText(strArray.get(11));
        CollNemOfExaminer.setText(strArray.get(12));
        ColIndCdOfExmner.setText(strArray.get(13));
        StartDate.setText(strArray.get(14));
        EndDate.setText(strArray.get(15));
        ExceptOn.setText(strArray.get(16));
        
	}
	
	
	protected void SaveToFile(String fnem) {
		// TODO Auto-generated method stub
	
	        FileWriter fw=null;		
			 try {fw = new FileWriter(fnem); }    catch (IOException e1){e1.printStackTrace();}
			 String newLine = System.getProperty("line.separator");
		
			 try { fw.write(ExaminerName.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(subject.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(year.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(COE.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(IndexOfEC.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(NODE.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(NOSE.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(RemPerStu.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(AddLine1.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(AddLine2.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(AddLine3.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(AddLine4.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(CollNemOfExaminer.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(ColIndCdOfExmner.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(StartDate.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(EndDate.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 try { fw.write(ExceptOn.getText()+newLine); }  catch (IOException e1) {e1.printStackTrace();}
//			 CollNemOfExaminer     ColIndCdOfExmner
			 
		
			 try {fw.close();} catch (IOException e1) {e1.printStackTrace();}           
	}
}
