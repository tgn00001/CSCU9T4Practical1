// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

@SuppressWarnings("serial")
public class TrainingRecordGUI extends JFrame implements ActionListener {

	private JTextField name = new JTextField(30);
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hours = new JTextField(2);
	private JTextField mins = new JTextField(2);
	private JTextField secs = new JTextField(2);
	private JTextField dist = new JTextField(4);
	private JLabel labn = new JLabel(" Name:");
	private JLabel labd = new JLabel(" Day:");
	private JLabel labm = new JLabel(" Month:");
	private JLabel laby = new JLabel(" Year:");
	private JLabel labh = new JLabel(" Hours:");
	private JLabel labmm = new JLabel(" Mins:");
	private JLabel labs = new JLabel(" Secs:");
	private JLabel labdist = new JLabel(" Distance (km):");
	private JButton addR = new JButton("Add");
	private JButton lookUpByDate = new JButton("Look Up");
	private JButton findAllByDate = new JButton("Find All By Date");

	private TrainingRecord myAthletes = new TrainingRecord();

	private JTextArea outputArea = new JTextArea(5, 50);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TrainingRecordGUI applic = new TrainingRecordGUI();
	} // main

	// set up the GUI
	public TrainingRecordGUI() {
		super("Training Record");
		setLayout(new FlowLayout());
		add(labn);
		add(name);
		name.setEditable(true);
		add(labd);
		add(day);
		day.setEditable(true);
		add(labm);
		add(month);
		month.setEditable(true);
		add(laby);
		add(year);
		year.setEditable(true);
		add(labh);
		add(hours);
		hours.setEditable(true);
		add(labmm);
		add(mins);
		mins.setEditable(true);
		add(labs);
		add(secs);
		secs.setEditable(true);
		add(labdist);
		add(dist);
		dist.setEditable(true);
		add(addR);
		addR.addActionListener(this);
		add(lookUpByDate);
		lookUpByDate.addActionListener(this);
		add(findAllByDate);
		findAllByDate.addActionListener(this);
		add(outputArea);
		outputArea.setEditable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(720, 200);
		setVisible(true);
		blankDisplay();

		// To save typing in new entries while testing, uncomment
		// the following lines (or add your own test cases)

	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		String message = "";
		if (event.getSource() == addR) {
				message = addEntry("generic");
		}
		if (event.getSource() == lookUpByDate) {
			message = lookupEntry();
		}
		if (event.getSource() == findAllByDate) {
			message = findAllByDate();
		}
		outputArea.setText(message);
		
		// only blank the display if there are no errors the user may wish to fix and try again
		if (!Pattern.matches(".*[ERROR].*", message)) {
			blankDisplay();
		}
		
	} // actionPerformed

	public String addEntry(String what) {
		String message = "";
		
		System.out.println("Adding " + what + " entry to the records");
		
		boolean isInputValid = true;
		
		// get name and validate it
		String n = name.getText();
		
		if (n.equals("")) {
			message += "ERROR: please enter a name\n";
			isInputValid = false;
		}
		
		try {
			// get date values
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			
			// get time values
			int h = Integer.parseInt(hours.getText());
			int mm = Integer.parseInt(mins.getText());
			int s = Integer.parseInt(secs.getText());
			
			GregorianCalendar time = new GregorianCalendar();
			time.setLenient(false);
			time.set(Calendar.YEAR, y);
			time.set(Calendar.MONTH, m-1);
			time.set(Calendar.DAY_OF_MONTH, d);
			time.set(Calendar.HOUR_OF_DAY, h);
			time.set(Calendar.MINUTE, mm);
			time.set(Calendar.SECOND, s);
			
			// get & validate distance value
			float km = java.lang.Float.parseFloat(dist.getText());
			if (km < 0) {
				message += "ERROR: value for distance must be a positive number.\n";
				isInputValid = false;
			}
			
			// only add the entry if it is valid
			if (isInputValid) {
				// only add entry if no run exists in the training record for this athlete on this day
				if (myAthletes.lookupEntry(d, m, y).equals("No entries found") || !myAthletes.doesEntryExist(n, d, m, y)) {
					Entry e = new RunEntry(n, d, m, y, h, mm, s, km);
					myAthletes.addEntry(e);
					message = "Record added successfully\n";
				}
				else {
					message = "ERROR: cannot add run twice.\n";
				}
			}
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			message += "ERROR: Please make sure valid values are entered into all fields.\n";
		}

		return message;
	}
	
	public String lookupEntry() {
		int m;
		int d;
		int y;
		try {
			m = Integer.parseInt(month.getText());
			d = Integer.parseInt(day.getText());
			y = Integer.parseInt(year.getText());
			outputArea.setText("looking up record ...");
			String message = myAthletes.lookupEntry(d, m, y);
			return message;
		} catch (NumberFormatException e) {
			return "ERROR: No valid date entered.";
		}
	}

	private String findAllByDate() {
		try {
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			outputArea.setText("looking up records ...");
			String message = myAthletes.lookupEntries(d, m, y);
			return message;
		} catch (NumberFormatException e) {
			return "ERROR: No valid date entered.";
		}
	}

	public void blankDisplay() {
		name.setText("");
		day.setText("");
		month.setText("");
		year.setText("");
		hours.setText("");
		mins.setText("");
		secs.setText("");
		dist.setText("");

	}// blankDisplay
	
	// Fills the input fields on the display for testing purposes only
	public void fillDisplay(Entry ent) {
		name.setText(ent.getName());
		day.setText(String.valueOf(ent.getDay()));
		month.setText(String.valueOf(ent.getMonth()));
		year.setText(String.valueOf(ent.getYear()));
		hours.setText(String.valueOf(ent.getHour()));
		mins.setText(String.valueOf(ent.getMin()));
		secs.setText(String.valueOf(ent.getSec()));
		dist.setText(String.valueOf(ent.getDistance()));
	}

} // TrainingRecordGUI
