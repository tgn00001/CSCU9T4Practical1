// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class TrainingRecordGUI extends JFrame implements ActionListener, DocumentListener {

	private JTextField name = new JTextField(30);
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hours = new JTextField(2);
	private JTextField mins = new JTextField(2);
	private JTextField secs = new JTextField(2);
	private JTextField reps = new JTextField(3);
	private JTextField dist = new JTextField(4);
	private JTextField rec = new JTextField(2);
	private JTextField where = new JTextField(10);
	private JTextField terrain = new JTextField(10);
	private JTextField tempo = new JTextField(10);
	private JLabel labtype = new JLabel("Entry type");
	private JLabel labn = new JLabel("Name");
	private JLabel labd = new JLabel("Day");
	private JLabel labm = new JLabel("Month");
	private JLabel laby = new JLabel("Year");
	private JLabel labh = new JLabel("Hours");
	private JLabel labmm = new JLabel("Mins");
	private JLabel labs = new JLabel("Secs");
	private JLabel labreps = new JLabel("Repetitions");
	private JLabel labdist = new JLabel("Distance (km)");
	private JLabel labrec = new JLabel("Recovery (mins)");
	private JLabel labwhere = new JLabel("Location");
	private JLabel labterrain = new JLabel("Terrain");
	private JLabel labtempo = new JLabel("Tempo");
	private JButton addR = new JButton("Add");
	private JButton lookUpByDate = new JButton("Look Up");
	private JButton findAllByDate = new JButton("Find All By Date");
	private JButton findAllByName = new JButton("Find All By Name");
	private JButton remove = new JButton("Remove");
	private JTextArea outputArea = new JTextArea(5, 50);
	private JScrollPane outputScrollPane = new JScrollPane(outputArea);
	
	private JPanel mainPanel = new JPanel();
	private Border border = new EmptyBorder(20, 20, 20, 20);
	private Box mainBox = new Box(BoxLayout.Y_AXIS);
	private JPanel topPanel = new JPanel();
	private JPanel detailsPanel = new JPanel(new GridLayout(8,3));
	private JPanel outputPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();	
	
	Font font =  new Font(Font.SANS_SERIF, Font.PLAIN, 30);
	
	private TrainingRecord myAthletes = new TrainingRecord();
	private String[] entryTypes = { "Run", "Sprint", "Cycle", "Swim" };
	private JComboBox<String> selection = new JComboBox<String>(entryTypes);
	private String selectedEntryType = "run";

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TrainingRecordGUI applic = new TrainingRecordGUI();
	} // main

	// set up the GUI
	public TrainingRecordGUI() {
		super("Training Record");
		
		add(mainPanel);
		mainPanel.setBorder(border);
		mainPanel.add(mainBox);
		mainBox.add(topPanel);
		mainBox.add(detailsPanel);
		mainBox.add(outputPanel);
		mainBox.add(buttonPanel);
		
		// entry type selection
		labtype.setFont(font);
		topPanel.add(labtype);
		selection.setFont(font);
		topPanel.add(selection);
		selection.addActionListener(this);
		
		// athlete name entry
		labn.setFont(font);
		name.setFont(font);
		topPanel.add(labn);
		topPanel.add(name);
		name.setEditable(true);
		name.getDocument().addDocumentListener(this);
		
		// date labels
		labd.setFont(font);
		labm.setFont(font);
		laby.setFont(font);
		detailsPanel.add(labd);
		detailsPanel.add(labm);
		detailsPanel.add(laby);
		
		//day entry
		day.setFont(font);
		detailsPanel.add(day);
		day.setEditable(true);
		day.getDocument().addDocumentListener(this);
		
		//month entry
		month.setFont(font);
		detailsPanel.add(month);
		month.setEditable(true);
		month.getDocument().addDocumentListener(this);
		
		// year entry
		year.setFont(font);
		detailsPanel.add(year);
		year.setEditable(true);
		year.getDocument().addDocumentListener(this);
		
		// time labels
		labh.setFont(font);
		detailsPanel.add(labh);
		labmm.setFont(font);
		detailsPanel.add(labmm);
		labs.setFont(font);
		detailsPanel.add(labs);
		
		// hour entry
		hours.setFont(font);
		detailsPanel.add(hours);
		hours.setEditable(true);
		hours.getDocument().addDocumentListener(this);
		
		// minute entry
		mins.setFont(font);
		detailsPanel.add(mins);
		mins.setEditable(true);
		mins.getDocument().addDocumentListener(this);
		
		// second entry
		secs.setFont(font);
		detailsPanel.add(secs);
		secs.setEditable(true);
		secs.getDocument().addDocumentListener(this);
		
		// row three labels
		labreps.setFont(font);
		detailsPanel.add(labreps);
		labdist.setFont(font);
		detailsPanel.add(labdist);
		labrec.setFont(font);
		detailsPanel.add(labrec);
		
		// sprint repetitions entry
		reps.setFont(font);
		detailsPanel.add(reps);
		reps.setEditable(false);
		reps.setFocusable(false);
		reps.getDocument().addDocumentListener(this);
		
		// distance entry
		dist.setFont(font);
		detailsPanel.add(dist);
		dist.setEditable(true);
		dist.getDocument().addDocumentListener(this);
		
		// sprint recovery time entry
		rec.setFont(font);
		detailsPanel.add(rec);
		rec.setEditable(false);
		rec.setFocusable(false);
		rec.getDocument().addDocumentListener(this);
		
		// row four labels
		labwhere.setFont(font);
		detailsPanel.add(labwhere);
		labterrain.setFont(font);
		detailsPanel.add(labterrain);
		labtempo.setFont(font);
		detailsPanel.add(labtempo);
		
		// swimming location entry
		where.setFont(font);
		detailsPanel.add(where);
		where.setEditable(false);
		where.setFocusable(false);
		where.getDocument().addDocumentListener(this);
		
		// cycle terrain entry
		terrain.setFont(font);
		detailsPanel.add(terrain);
		terrain.setEditable(false);
		terrain.setFocusable(false);
		terrain.getDocument().addDocumentListener(this);
		
		// cycle tempo entry
		tempo.setFont(font);
		detailsPanel.add(tempo);
		tempo.setEditable(false);
		tempo.setFocusable(false);
		tempo.getDocument().addDocumentListener(this);
		
		// text output
		outputArea.setFont(font);
		outputArea.setEditable(false);
		outputArea.setFocusable(false);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputPanel.add(outputScrollPane);
				
		// add entry button
		addR.setFont(font);
		buttonPanel.add(addR);
		addR.setEnabled(false);
		addR.addActionListener(this);
		
		// look up latest entry by date button
		lookUpByDate.setFont(font);
		buttonPanel.add(lookUpByDate);
		lookUpByDate.setEnabled(false);
		lookUpByDate.addActionListener(this);
		
		// find all entries by date button
		findAllByDate.setFont(font);
		buttonPanel.add(findAllByDate);
		findAllByDate.setEnabled(false);
		findAllByDate.addActionListener(this);
		
		// find all entries by name button
		findAllByName.setFont(font);
		buttonPanel.add(findAllByName);
		findAllByName.setEnabled(false);
		findAllByName.addActionListener(this);
		
		// remove entry button
		remove.setFont(font);
		buttonPanel.add(remove);
		remove.setEnabled(false);
		remove.addActionListener(this);
		
		// window settings
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		// clear the display
		blankDisplay();
	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		String message = "";
		if (event.getSource() == selection) {
			switchSelection(event);
		}
		if (event.getSource() == addR) {
			message = addEntry(selectedEntryType);
		}
		if (event.getSource() == lookUpByDate) {
			message = lookupEntry();
		}
		if (event.getSource() == findAllByDate) {
			message = findAllByDate();
		}
		if (event.getSource() == findAllByName) {
			message = findEntriesByName();
		}
		if (event.getSource() == remove) {
			message = removeEntry();
		}
		outputArea.setText(message);

		// only blank the display if there are no errors the user may wish to fix and
		// try again
		if (!Pattern.matches(".*ERROR.*", message)) {
			blankDisplay();
		}

	} // actionPerformed

	@Override
	public void insertUpdate(DocumentEvent e) {
		boolean hasName = name.getText().length() > 0;
		boolean hasDate = day.getText().length() > 0 && month.getText().length() > 0 && year.getText().length() > 0;
		boolean hasTime = hours.getText().length() > 0 && mins.getText().length() > 0 && secs.getText().length() > 0;
		boolean hasDist = dist.getText().length() > 0;
		boolean hasRunDetails = hasName && hasDate && hasTime && hasDist;
		boolean hasSprintDetails = hasRunDetails && reps.getText().length() > 0 && rec.getText().length() > 0;
		boolean hasCycleDetails = hasRunDetails && terrain.getText().length() > 0 && tempo.getText().length() > 0;
		boolean hasSwimDetails = hasRunDetails && where.getText().length() > 0;
		
		switch(selectedEntryType) {
			case "run":
				if(hasRunDetails)
					addR.setEnabled(true);
				break;
			case "sprint":
				if(hasSprintDetails)
					addR.setEnabled(true);
				break;
			case "cycle":
				if(hasCycleDetails)
					addR.setEnabled(true);
				break;
			case "swim":
				if(hasSwimDetails)
						addR.setEnabled(true);
				break;
		}
		if (!myAthletes.isEmpty()) {
			if (hasDate) {
				lookUpByDate.setEnabled(true);
				findAllByDate.setEnabled(true);
			}
			if (hasName) {
				findAllByName.setEnabled(true);
			}
			if (hasName && hasDate) {
				remove.setEnabled(true);
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		boolean hasName = name.getText().length() > 0;
		boolean hasDate = day.getText().length() > 0 && month.getText().length() > 0 && year.getText().length() > 0;
		boolean hasTime = hours.getText().length() > 0 && mins.getText().length() > 0 && secs.getText().length() > 0;
		boolean hasDist = dist.getText().length() > 0;
		boolean hasRunDetails = hasName && hasDate && hasTime && hasDist;
		boolean hasSprintDetails = hasRunDetails && reps.getText().length() > 0 && rec.getText().length() > 0;
		boolean hasCycleDetails = hasRunDetails && terrain.getText().length() > 0 && tempo.getText().length() > 0;
		boolean hasSwimDetails = hasRunDetails && where.getText().length() > 0;
		
		switch(selectedEntryType) {
			case "run":
				if(!hasRunDetails)
					addR.setEnabled(false);
				break;
			case "sprint":
				if(!hasSprintDetails)
					addR.setEnabled(false);
				break;
			case "cycle":
				if(!hasCycleDetails)
					addR.setEnabled(false);
				break;
			case "swim":
				if(!hasSwimDetails)
						addR.setEnabled(false);
				break;
		}
		if (!myAthletes.isEmpty()) {
			if (!hasDate) {
				lookUpByDate.setEnabled(false);
				findAllByDate.setEnabled(false);
			}
			if (!hasName) {
				findAllByName.setEnabled(false);
			}
			if (!hasName && !hasDate) {
				remove.setEnabled(false);
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}
	
	/**
	 * Switches the GUI elements on or off depending on the value selected in the "selection" combo box.
	 * 
	 * @param event the combo box selection event
	 */
	private void switchSelection(ActionEvent event) {
		JComboBox<String> selec = (JComboBox<String>) event.getSource();
		if (selec.getSelectedItem().equals("Run")) {
			reps.setEditable(false);
			reps.setFocusable(false);
			labdist.setText("Distance (km)");
			rec.setEditable(false);
			rec.setFocusable(false);
			where.setEditable(false);
			where.setFocusable(false);
			terrain.setEditable(false);
			terrain.setFocusable(false);
			tempo.setEditable(false);
			tempo.setFocusable(false);
			selectedEntryType = "run";
		} else if (selec.getSelectedItem().equals("Sprint")) {
			reps.setEditable(true);
			reps.setFocusable(true);
			labdist.setText("Distance (m)");
			rec.setEditable(true);
			rec.setFocusable(true);
			where.setEditable(false);
			where.setFocusable(false);
			terrain.setEditable(false);
			terrain.setFocusable(false);
			tempo.setEditable(false);
			tempo.setFocusable(false);
			selectedEntryType = "sprint";
		} else if (selec.getSelectedItem().equals("Cycle")) {
			reps.setEditable(false);
			reps.setFocusable(false);
			labdist.setText("Distance (km)");
			rec.setEditable(false);
			rec.setFocusable(false);
			where.setEditable(false);
			terrain.setEditable(true);
			terrain.setFocusable(true);
			tempo.setEditable(true);
			tempo.setFocusable(true);
			selectedEntryType = "cycle";
		} else {
			reps.setEditable(false);
			reps.setFocusable(false);
			labdist.setText("Distance (km)");
			rec.setEditable(false);
			rec.setFocusable(false);
			where.setEditable(true);
			where.setFocusable(true);
			terrain.setEditable(false);
			terrain.setFocusable(false);
			tempo.setEditable(false);
			tempo.setFocusable(false);
			selectedEntryType = "swim";
		}
	}

	/**
	 * Attempts to add an Entry based on the values input, validating all fields.
	 * 
	 * @param what the type of Entry selected
	 * @return A diagnostic message to show whether or not the operation was successful.
	 */
	public String addEntry(String what) {
		String message = "";
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
			time.set(Calendar.MONTH, m - 1);
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

			// if sprint entry, get and validate repetition and recovery values
			int repetitions = 0;
			int recovery = 0;
			if (what.equals("sprint")) {
				repetitions = java.lang.Integer.parseInt(reps.getText());
				recovery = java.lang.Integer.parseInt(rec.getText());
				if (repetitions < 0) {
					message += "ERROR: value for repetitions must be a positive number.\n";
					isInputValid = false;
				}
				if (recovery < 0) {
					message += "ERROR: value for recovery must be a positive number.\n";
					isInputValid = false;
				}
			}

			// if cycle entry, get and validate terrain and tempo values
			String cycleTerrain = "";
			String cycleTempo = "";
			if (what.equals("cycle")) {
				cycleTerrain = terrain.getText();
				cycleTempo = tempo.getText();
				if (cycleTerrain.equals("")) {
					message += "ERROR: please enter a terrain\n";
					isInputValid = false;
				}
				if (cycleTempo.equals("")) {
					message += "ERROR: please enter a tempo\n";
					isInputValid = false;
				}
			}

			// if swim entry, get and validate location value
			String swimLocation = "";
			if (what.equals("swim")) {
				swimLocation = where.getText();
				if (swimLocation.equals("")) {
					message += "ERROR: please enter a swimming location\n";
					isInputValid = false;
				}
			}

			// only add the entry if it is valid
			if (isInputValid) {
				Entry e;
				// check which entry constructor to use, then attempt to add entry
				switch (what) {
				case "sprint":
					e = new SprintEntry(n, d, m, y, h, mm, s, km, repetitions, recovery);
					break;
				case "cycle":
					e = new CycleEntry(n, d, m, y, h, mm, s, km, cycleTerrain, cycleTempo);
					break;
				case "swim":
					e = new SwimEntry(n, d, m, y, h, mm, s, km, swimLocation);
					break;
				default:
					e = new RunEntry(n, d, m, y, h, mm, s, km);
					break;
				}

				myAthletes.addEntry(e);
				message = "Record added successfully\n";
			}
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			message += "ERROR: Please make sure valid values are entered into all fields.\n";
		} catch (DuplicateElementException e) {
			message = "ERROR: cannot add entry twice.\n";
		}

		return message;
	}

	/**
	 * Looks for an Entry in the training record based on date.
	 * @return The Entry, or an error message if no entry was found (or if there was an exception).
	 */
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
	
	/**
	 * Looks for all Entries in the training record for the date entered.
	 * @return The Entries, or an error message if no entries were found (or if there was an exception).
	 */
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

	/**
	 * Looks for all Entries in the training record for the name entered.
	 * @return The Entries, or an error message if no entries were found.
	 */
	private String findEntriesByName() {
		return myAthletes.findAllEntriesForName(name.getText());
	}
	
	/**
	 * Removes the Entry for the named athlete on the given date.
	 * @return A confirmation message, or an error message if no entry was found (or if there was an exception).
	 */
	private String removeEntry() {
		String n = name.getText();
		if (n.equals("")) {
			return "ERROR: Please enter a name.";
		}
		int m;
		int d;
		int y;
		try {
			m = Integer.parseInt(month.getText());
			d = Integer.parseInt(day.getText());
			y = Integer.parseInt(year.getText());
			outputArea.setText("looking up record ...");
			String message = myAthletes.removeEntry(n, d, m, y);
			if (Pattern.matches("Removed.*", message) && myAthletes.getNumberOfEntries() == 0) {
				lookUpByDate.setEnabled(false);
				findAllByDate.setEnabled(false);
				findAllByName.setEnabled(false);
				remove.setEnabled(false);
			}
			return message;
		} catch (NumberFormatException e) {
			return "ERROR: No valid date entered.";
		}
	}
	
	/**
	 * Clears all text fields, resetting input.
	 */
	public void blankDisplay() {
		name.setText("");
		day.setText("");
		month.setText("");
		year.setText("");
		hours.setText("");
		mins.setText("");
		secs.setText("");
		reps.setText("");
		dist.setText("");
		rec.setText("");
		where.setText("");
		terrain.setText("");
		tempo.setText("");

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
