/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agilejava;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jodielaurenson
 */
public class FilterTest {
    
    public FilterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Tests if records are added correctly
     */
    @Test
    public void testSingleRecord(){
        Filter filter = new Filter();
        Record records[] = new Record[1];
        records[0] = new Record();
        records[0].setData("AL", 24057);
        boolean empty = filter.isEmpty(records);
        assertFalse("Array is not empty",empty);
    }
    
    
    /**
     * Tests if method filters with a single record
     */
    @Test
    public void testRetrictToPriceInRange(){
        Filter filter = new Filter();
        Record records[] = new Record[1];
        records[0] = new Record();
        records[0].setData("AL", 24057);
        Record[] filteredRecords = filter.restrictToPrice(records, 22000.0, 28000.0);
        boolean empty = filter.isEmpty(filteredRecords);
        assertFalse("Array is not empty",empty);
    }
    
    /**
     * Tests if array is empty after filtering when nothing is in range
     */
    @Test
    public void testRetrictToPriceNotInRange(){
        Filter filter = new Filter();
        Record records[] = new Record[1];
        records[0] = new Record();
        records[0].setData("AL", 20000);
        Record[] filteredRecords = filter.restrictToPrice(records, 22000.0, 28000.0);
        assertEquals("Array is empty",filteredRecords.length, 0);
    }

    /**
     * Test of restrictToPrice method, of class AgileJava.
     */
    @Test
    public void testRestrictToPrice() {
        Filter filter = new Filter();
        Record records[] = new Record[5];
        records[0] = new Record();
        records[1] = new Record();
        records[2] = new Record();
        records[3] = new Record();
        records[4] = new Record();
        records[0].setData("AL", 24057);
        records[1].setData("FL", 20365);
        records[2].setData("KS", 25968);
        records[3].setData("KY", 28776);
        records[4].setData("FL", 25432);
        Record[] filteredRecords = filter.restrictToPrice(records, 22000.0, 28000.0);
        System.out.println("length: "+filteredRecords.length);
        assertEquals("Restricted values is correct",filteredRecords[0],records[0]);
        assertEquals("Length is correct",filteredRecords.length,3);
    }
    
}
