package org.intercom.tests;

import java.util.ArrayList;

import org.intercom.test.ArrayFlattener;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * 
 * @author joedian
 *
 */
public class ArrayFlattenerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArrayFlattenerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArrayFlattenerTest.class );
    }

    /**
     * This test aims to test the functionality of the ArrayFlattenerClass
     * It uses base and valid cases to test the correct functionality of the flattener method
     */
    public void testApp()
    {
    	//test with simple two dimensional array 
    	int[][] testArray = {{5,2,6,5}, {8,1},  {4, 3, 31, 4 ,5 ,35, 35}};
    	Object[] myRes = ArrayFlattener.flattenArray(testArray, new ArrayList<Integer>());
        assertNotNull( myRes );
        assertEquals(myRes.length, new int[]{5,2,6,5,8,1,4,3,31,4,5,35,35}.length);
        
      //test with simple two dimensional array 
        int[][] testArray2 = {{8}, {8,1},  {4, 3}};        
        Object[] myRes2 = ArrayFlattener.flattenArray(testArray2, new ArrayList<Integer>());
        assertNotNull( myRes2 );
        assertEquals(myRes2.length , new int[]{8,8,1,4,3}.length);
        assertEquals(myRes2[2], new int[]{8,8,1,4,3}[2]);
        
      //test with simple three dimensional object array 
        Object[] testArray3 = new Object[] {
                new Object[] { new int[] { 1, 2 }, 
                               new int[] { 3, 4 }},
                new int[] { 5, 6 } 
        };        
        Object[] myRes3 = ArrayFlattener.flattenArray(testArray3, new ArrayList<Integer>());
        assertNotNull( myRes3 );
        assertEquals(myRes3.length, new int[]{1,2,3,4,5,6}.length);
        
      //test with simple empty array
        Object[] testArray4 = {};
        Object[] myRes4 = ArrayFlattener.flattenArray(testArray4, new ArrayList<Integer>());
        assertNotNull( myRes4 );
        assertEquals(myRes4.length, 0);
        
      //test with null array
        Object[] testArray5 = null;
        Object[] myRes5 = ArrayFlattener.flattenArray(testArray5, new ArrayList<Integer>());
        assertNull( myRes5 );
        
      //test with simple one dimensional array 
        Object[] testArray6 = {5};
        Object[] myRes6 = ArrayFlattener.flattenArray(testArray6, new ArrayList<Integer>());
        assertNotNull( myRes6 );
        assertEquals(myRes6[0], 5);
        
      //test with  three dimensional object array 
        Object[] testArray7 = (new Object[]{1,2,new Object[]{3,new Object[]{4,5}},6,7});
        Object[] myRes7 = ArrayFlattener.flattenArray(testArray7, new ArrayList<Integer>());
        assertNotNull( myRes7);
        assertEquals(myRes7[3], 4);
        
      //test with  string, error thrown and message logged, null returned
        Object[] testArray8 = (new Object[]{1,"po",new Object[]{3,new Object[]{4,5}},6,7});
        Object[] myRes8 = ArrayFlattener.flattenArray(testArray8, new ArrayList<Integer>());
        assertNull( myRes8);        
    }
}
