//******************************************************************************************************** 
// CLASS: View (View.java) 
// 
// COURSE AND PROJECT INFO 
// CSE205 Object Oriented Programming and Data Structures, Spring 2017 
// Project Number: project-03 
// 
// AUTHOR 
// Jessica Thomas (jdthom22@asu.edu) 
//********************************************************************************************************
package Project3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
* The View class implements the GUI.
*/
@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener 
{

	 public static final int FRAME_WIDTH  = 500;
	 public static final int FRAME_HEIGHT = 250;
	
	 // Declare instance variables
	 Main mMain;
	 private JTextField mSearchText;
	 private JTextField[] mHomeworkText;
	 private JTextField[] mExamText;
	 private Student mStudent;
	
	 /**
	  * View()
	  *
	  * The View constructor creates the GUI interface and makes the frame visible at the end.
	  */
	 public View(Main pMain) 
	 {
	
	     // Save a reference to the Main object pMain in mMain.
	     this.mMain = pMain;
	
	     // PSEUDOCODE:
	     // Create a JPanel named panelSearch which uses the FlowLayout.
	     // Add a JLabel "Student Name: " to panelSearch
	     // Create mSearchText and make the field 25 cols wide
	     // Add mSearchText to the panel
	     // Create mSearchButton
	     // Make this View the action listener for the button
	     // Add the button to the panel
	     JPanel panelSearch = new JPanel(new FlowLayout());
	     panelSearch.add(new JLabel("Student name: "));
	     mSearchText = new JTextField(25);
	     panelSearch.add(mSearchText);
	     JButton mSearchButton = new JButton("Search");
	     mSearchButton.addActionListener(this);
	     panelSearch.add(mSearchButton);
	
	     // PSEUDOCODE:
	     // Create a JPanel named panelHomework which uses the FlowLayout.
	     // Add a JLabel "Homework: " to the panel
	     // Create mHomeworkText which is an array of CourseConstants.NUM_HOMEWORKS JTextFields
	     // For i = 0 to CourseConstants.NUM_HOMEWORKS - 1 Do
	     //     Create textfield mHomeworkText[i] displaying 5 cols
	     //     Add mHomeworkText[i] to the panel
	     // End For
	     JPanel panelHomework = new JPanel();
	     panelHomework.setLayout(new FlowLayout());
	     panelHomework.add(new JLabel("Homework: "));
	     mHomeworkText = new JTextField[CourseConstants.NUM_HOMEWORKS];
	     for(int i = 0; i <= CourseConstants.NUM_HOMEWORKS-1; i++)
	     {
	    	 mHomeworkText[i] = new JTextField(5);
	    	 panelHomework.add(mHomeworkText[i]);
	     }
	
	     // Create the exam panel which contains the "Exam: " label and the two exam text fields. The pseudocode is omitted
	     // because this code is very similar to the code that creates the panelHomework panel.
	     JPanel panelExam = new JPanel();
	     panelExam.setLayout(new FlowLayout());
	     panelExam.add(new JLabel("Exam: "));
	     mExamText = new JTextField[CourseConstants.NUM_EXAMS];
	     for(int i = 0; i <= CourseConstants.NUM_EXAMS-1; i++)
	     {
	    	 mExamText[i] = new JTextField(5);
	    	 panelExam.add(mExamText[i]);
	     }
	
	     // PSEUDOCODE:
	     // Create a JPanel named panelButtons using FlowLayout.
	     // Create the Clear button mClearButton.
	     // Make this View the action listener for mClearButton.
	     // Add the  Clear button to the panel.
	     // Repeat the three above statements for the Save button.
	     // Repeat the three above statements for the Exit button.
	     JPanel panelButtons = new JPanel(new FlowLayout());
	     JButton mClearButton = new JButton("Clear");
	     mClearButton.addActionListener(this);
	     panelButtons.add(mClearButton);
	     JButton mSaveButton = new JButton("Save");
	     mSaveButton.addActionListener(this);
	     panelButtons.add(mSaveButton);
	     JButton mExitButton = new JButton("Exit");
	     mExitButton.addActionListener(this);
	     panelButtons.add(mExitButton);
	     
	
	     // PSEUDOCODE:
	     // Create a JPanel named panelMain using a vertical BoxLayout.
	     // Add panelSearch to panelMain.
	     // Add panelHomework to panelMain.
	     // Add panelExam to panelMain.
	     // Add panelButtons to panelMain.
	     JPanel panelMain = new JPanel();
	     panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
	     panelMain.add(panelSearch);
	     panelMain.add(panelHomework);
	     panelMain.add(panelExam);
	     panelMain.add(panelButtons);
	
	     // Initialize the remainder of the frame, add the main panel to the frame, and make the frame visible.
	     setTitle("Gradebookulator");
	     setSize(FRAME_WIDTH, FRAME_HEIGHT);
	     setResizable(false);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     add(panelMain);
	     setVisible(true);
	 }
	
	 /**
	  * actionPerformed()
	  *
	  * Called when one of the JButtons is clicked. Detects which button was clicked and handles it.
	  */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		/**
		  * PSEUDOCOODE:
		  * If the source of the event was the Search button Then
		  *     lastName = retrieve the text from the mSearchText text field.
		  *     If lastName is the empty string Then
		  *         Call messageBox() to display "Please enter the student's last name."
		  *     Else
		  *         student = Call mMain.search(lastName)
		  *         If student is null Then
		  *             Call messageBox() to display "Student not found. Try again."
		  *         Else
		  *             Call displayStudent(student)
		  *         End if
		  *     End If
		  * Else if the source of the event was the Save button Then
		  *     If mStudent is not null Then Call saveStudent(mStudent)
		  * Else if the source of the event was the Clear button Then
		  *     Call clear()
		  * Else if the source of the event was the Exit button Then
		  *     If mStudent is not null Then Call saveStudent(mStudent)
		  *     Call mMain.exit() to terminate the application
		  * End If
		  */
		if(arg0.getActionCommand().equals("Search"))
		{
			String lastName = mSearchText.getText();
			
			if(lastName.equals(""))
			{
				messageBox( "Please enter the student's last name.");
			}
			else
			{
				mStudent = mMain.search(lastName);
				if(mStudent == null)
				{
					messageBox("Student not found. Try again.");
				}
				else
				{
					displayStudent(mStudent);
				}
			}
		}
		else if(arg0.getActionCommand().equals("Save"))
		{
			String lastName = mSearchText.getText();
			
			mStudent = mMain.search(lastName);
			if(mStudent != null)
			{
				save(mStudent);
			}
		}
		else if(arg0.getActionCommand().equals("Clear"))
		{
			clear();
		}
		else if(arg0.getActionCommand().equals("Exit"))
		{			
			mMain.exit();
			System.exit(0);
		}
	}
	
	 /**
	  * clear()
	  *
	  * Called when the Clear button is clicked. Clears all of the text fields by setting the contents to the empty string.
	  * After clear() returns, no student information is being edited or displayed.
	  *
	  * PSEUDOCODE:
	  * Set the mSearchText text field to ""
	  * Set each of the homework text fields to ""
	  * Set each of the exam text fields to ""
	  * Set the mStudent reference to null
	  */
	public void clear()
	{
		mSearchText.setText("");
		for(JTextField index : mHomeworkText)
		{
			index.setText("");
		}
		for(JTextField index : mExamText)
		{
			index.setText("");
		}
		mStudent = null;
	}
	
	 /**
	  * displayStudent()
	  *
	  * Displays the homework and exam scores for a student in the mHomeworkText and mExamText text fields.
	  *
	  * PSEUDOCODE:
	  * For i = 0 to CourseConstants.NUM_HOMEWORKS - 1 Do
	  *     int hw = pStudent.getHomework(i)
	  *     String hwstr = convert hw to a String (Hint: Integer.toString())
	  *     mHomeworkText[i].setText(hwstr)
	  * End For
	  * Write another for loop similar to the one above to place the exams scores into the text fields
	  */
	public void displayStudent(Student pStudent)
	{		
		for(int i = 0; i < CourseConstants.NUM_HOMEWORKS; i++)
		{
			int hw = pStudent.getHomework(i);
			String hwstr = Integer.toString(hw);
			mHomeworkText[i].setText(hwstr);
		}
		for(int i = 0; i < CourseConstants.NUM_EXAMS; i++)
		{
			int exam = pStudent.getExam(i);
			String examstr = Integer.toString(exam);
			mExamText[i].setText(examstr);
		}
	}
	 
	 /**
	  * messageBox()
	  *
	  * Displays a message box containing some text.
	  */
	 public void messageBox(String pMessage) 
	 {
	     JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
	 }
	
	 /**
	  * saveStudent()
	  *
	  * Retrieves the homework and exam scores for pStudent from the text fields and writes the results to the Student record
	  * in the Roster.
	  *
	  * PSEUDOCODE:
	  * For i = 0 to CourseConstants.NUM_HOMEWORKS - 1 Do
	  *     String hwstr = mHomeworkText[i].getText()
	  *     int hw = convert hwstr to an int (Hint: Integer.parseInt())
	  *     Call pStudent.setHomework(i, hw)
	  * End For
	  * Write another for loop similar to the one above to save the exam scores
	  */
	 public void save(Student pStudent)
	 {
		 for(int i = 0; i < CourseConstants.NUM_HOMEWORKS; i++)
			{
				String hwstr = mHomeworkText[i].getText();
				int hw = Integer.parseInt(hwstr);
				pStudent.setHomework(i, hw);
			}
			for(int i = 0; i < CourseConstants.NUM_EXAMS; i++)
			{
				String examstr = mExamText[i].getText();
				int exam = Integer.parseInt(examstr);
				pStudent.setExam(i, exam);
			}
	 }

}
