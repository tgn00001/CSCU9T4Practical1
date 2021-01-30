/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4practical1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author saemundur
 */
public class TrainingRecordTest {
    public TrainingRecordTest() {
    }
    
    private TrainingRecord instance;
    private static Entry a, a2, a_sprint, a_cycle, a_swim, b, c1, c2;
    
    @BeforeAll
    public static void setUpClass() {
    	a = new RunEntry("Alice", 1, 2, 2003, 0, 16, 7, 3);
    	a2 = new RunEntry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        a_sprint = new SprintEntry("Alice", 2, 2, 2003, 0, 15, 8, 300, 4, 2);
        a_cycle = new CycleEntry("Alice", 3, 2, 2003, 0, 14, 9, 15, "mountain", "moderate");
        a_swim = new SwimEntry("Alice", 4, 2, 2003, 0, 13, 6, 7, "pool");
        b = new RunEntry("Bob", 1, 2, 2003, 0, 14, 15, 3);
        c1 = new RunEntry("Claire", 7, 3, 2010, 0, 26, 20, 7);
        c2 = new RunEntry("Claire", 11, 3, 2010, 0, 24, 55, 7);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    	instance = new TrainingRecord();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addEntry method, of class TrainingRecord.
     * You might want to extend this test when you implement the other
     * Entry types
     */
    @Test
    public void testAddEntry() {
        System.out.println("addEntry");
        try {
			assertEquals(instance.getNumberOfEntries(),0);
			instance.addEntry(a);
			assertEquals(instance.getNumberOfEntries(),1);
			instance.addEntry(a_sprint);
			assertEquals(instance.getNumberOfEntries(),2);
			instance.addEntry(a_cycle);
			assertEquals(instance.getNumberOfEntries(),3);
			instance.addEntry(a_swim);
			assertEquals(instance.getNumberOfEntries(),4);
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
    
    /**
     * Test of addEntry with a repeat
     * Adding another entry for the same person on the same day should fail
     */
    @Test
    public void testAddEntryUnique() {
        System.out.println("addEntry -- fail");
        try {
			instance.addEntry(a);
			assertThrows(DuplicateElementException.class, () -> instance.addEntry(a2));
			assertEquals(instance.getNumberOfEntries(),1);
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }

    /**
     * Test of lookupEntry method, of class TrainingRecord.
     */
    @Test
    public void testLookupEntry() {
        System.out.println("lookupEntry");
        String expResult = "No entries found";
        try {
			instance.addEntry(a);
			instance.addEntry(b);
			instance.addEntry(c1);
			instance.addEntry(c2);
	        int d = 7;
	        int m = 3;
	        int y = 2010;
	        String result = instance.lookupEntry(d, m, y);
	        assertNotEquals(expResult, result, "expecting to find the entry");
	        result = instance.lookupEntry(1, 2, 1999);
	        assertEquals(expResult, result, "expecting to not find the entry");
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}

    }
    

    
    
    /**
     * Test of lookupEntries, of class TrainingRecord
     */
    @Test
    public void testLookupEntries() {
        System.out.println("lookupEntries");
        String expectResultsNone = "Sorry couldn't find anything for this date";
        String expectResults = "Alice ran 3.0 km in 0:16:7 on 1/2/2003\n" + 
                                "Bob ran 3.0 km in 0:14:15 on 1/2/2003\n";
        try {
			instance.addEntry(a);
			instance.addEntry(b);
			int d = 1;
			int m = 2;
			int y = 2003;

			String resultSuccess = instance.lookupEntries(d,m,y);
			String resultNone = instance.lookupEntries(d,m,1999);
			assertEquals(expectResultsNone,resultNone);
			assertEquals(expectResults,resultSuccess);
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
    
    
    /**
     * Test of doesEntryExist, of class TrainingRecord
     */
    @Test
    public void testDoesEntryExist() {
    	System.out.println("doesEntryExist");
    	try {
    		assertFalse(instance.doesEntryExist(a.getName(), a.getDay(), a.getMonth(), a.getYear()));
    		instance.addEntry(a);
    		assertTrue(instance.doesEntryExist(a.getName(), a.getDay(), a.getMonth(), a.getYear()));
    	} catch (DuplicateElementException e) {
    		System.out.println("TrainingRecord.addEntry() is broken!!!");
    	}
    }
    
    /**
     * Test of findAllEntriesForName, of class TrainingRecord
     */
    @Test
    public void testFindAllEntriesForName() {
        System.out.println("findAllEntriesForName");
    	try {
			assertEquals(instance.getNumberOfEntries(),0);
			String expResultAliceNoEntries = "No entries found for \"Alice\"";
			String expResultClaireNoEntries = "No entries found for \"Claire\"";
			String expResultBothNoEntries = "No entries found for \"c\"";
			assertEquals(expResultAliceNoEntries, instance.findAllEntriesForName("Alice"));
			assertEquals(expResultClaireNoEntries, instance.findAllEntriesForName("Claire"));
			assertEquals(expResultBothNoEntries, instance.findAllEntriesForName("c"));
			instance.addEntry(a);
			instance.addEntry(a_sprint);
			instance.addEntry(a_cycle);
			instance.addEntry(a_swim);
			instance.addEntry(c1);
			instance.addEntry(c2);
			String expResultAlice = a.getEntry() + a_sprint.getEntry() + a_cycle.getEntry() + a_swim.getEntry();
			String expResultClaire = c1.getEntry() + c2.getEntry();
			String expResultBoth = expResultAlice + expResultClaire;
			assertEquals(expResultAlice, instance.findAllEntriesForName("Alice"));
			assertEquals(expResultClaire, instance.findAllEntriesForName("Claire"));
			assertEquals(expResultBoth, instance.findAllEntriesForName("c"));
		
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
    
    /**
     * Test of getNumberOfEntries, of class TrainingRecord
     */
    @Test
    public void testGetNumberOfEntries() {
        System.out.println("getNumberOfEntries");
        assertEquals(instance.getNumberOfEntries(),0);
        try {
			instance.addEntry(a);
			assertEquals(instance.getNumberOfEntries(),1);
			instance.addEntry(b);
			assertEquals(instance.getNumberOfEntries(),2);
			instance.addEntry(c1);
			assertEquals(instance.getNumberOfEntries(),3);
			instance.addEntry(c2);
			assertEquals(instance.getNumberOfEntries(),4);
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
    
    
    /**
     * Test of clearAllEntries, of class TrainingRecord
     */
    @Test
    public void testClearAllEntries() {
    	 System.out.println("clearAllEntries");
         try {
 			instance.addEntry(a);
 			assertEquals(instance.getNumberOfEntries(),1);
 			instance.addEntry(a_sprint);
 			assertEquals(instance.getNumberOfEntries(),2);
 			instance.addEntry(a_cycle);
 			assertEquals(instance.getNumberOfEntries(),3);
 			instance.addEntry(a_swim);
 			assertEquals(instance.getNumberOfEntries(),4);
 			instance.clearAllEntries();
 			assertEquals(instance.getNumberOfEntries(),0);
 		} catch (DuplicateElementException e) {
 			System.out.println("TrainingRecord.addEntry() is broken!!!");
 		}
    }
    
    /**
     * Test of removeEntry, of class TrainingRecord
     */
    @Test
    public void testRemoveEntry() {
    	System.out.println("removeEntry");
        try {
			assertEquals(instance.getNumberOfEntries(),0);
			instance.addEntry(a);
			assertEquals(instance.getNumberOfEntries(),1);
			assertEquals("Removed entry for Alice on 1/2/2003", instance.removeEntry(a.getName(), a.getDay(), a.getMonth(), a.getYear()));
			assertEquals(instance.getNumberOfEntries(),0);
			assertEquals("No entry found for Alice on 1/2/2003", instance.removeEntry(a.getName(), a.getDay(), a.getMonth(), a.getYear()));
			assertEquals(instance.getNumberOfEntries(),0);
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
    
    /**
     * Test of isEmpty, of class TrainingRecord
     */
    @Test
    public void testIsEmpty() {
    	System.out.println("isEmpty");
        try {
			assertTrue(instance.isEmpty());
			instance.addEntry(a);
			assertFalse(instance.isEmpty());
			instance.removeEntry(a.getName(), a.getDay(), a.getMonth(), a.getYear());
			assertTrue(instance.isEmpty());
		} catch (DuplicateElementException e) {
			System.out.println("TrainingRecord.addEntry() is broken!!!");
		}
    }
}
