/**
 * 
 * @author Arjun Balaji / balaji.a@husky.neu.edu
 * @version 9/24/1994
 * Comments: It's my birthday! Happy submission day :)
 * 
 * This is the class used for testing FListInteger.
 */
public class TestFListInteger {
    
    /**
     * The main method invokes the tests.
     * @param args unused input
     */
    public static void main(String [] args) {
        TestFListInteger test = new TestFListInteger();
        
        test.testIsEmpty();
        test.testGet();
        test.testSet();
        test.testSize();
        test.testToString();
        test.testEquals();
        
        test.summarize();
        
    }
    
    /**
     * Prints a summary of all tests run.
     */
    private void summarize() {
        System.out.println();
        System.out.println(totalErrors + " errors found in " +
                totalTests + " tests.");
    }
    
    /**
     * the constructor for TestFListInteger
     */
    public TestFListInteger() {
        // an empty constructor since this class is just used for testing.
    }
    
    /**
     * example of FListInteger used in tests
     */
    FListInteger l0;
    /**
     * example of FListInteger used in tests
     */
    FListInteger l1;
    /**
     * example of FListInteger used in tests
     */
    FListInteger l2;
    /**
     * example of FListInteger used in tests
     */
    FListInteger l3;
    /**
     * example of FListInteger used in tests
     */
    FListInteger l4;
    /**
     * example of FListInteger used in tests
     */    
    FListInteger l5;

    /**
     * initializes examples used for testing
     */
    public void init() {
        l0 = new EmptyList();
        l1 = FListInteger.add(l0, new Integer(5));
        l2 = FListInteger.add(l1, new Integer(4));
        l3 = FListInteger.add(l2, new Integer(7));
        l4 = new EmptyList();
        l5 = FListInteger.add(l2, new Integer(7));
    }
    
    /**
     * testing the isEmpty method
     */
    private void testIsEmpty() {
        init();
        assertTrue("empty", FListInteger.isEmpty(l0));
        assertFalse("nonempty", FListInteger.isEmpty(l1));
    }
    
    /**
     * testing the get method
     */
    private void testGet() {
        init();
        assertTrue("FListInteger.get(l1, 0)", FListInteger.get(l1, 0) == 5);
        assertTrue("FListInteger.get(l2, 0)", FListInteger.get(l2, 0) == 4);
        assertTrue("FListInteger.get(l3, 0)", FListInteger.get(l3, 0) == 7);
    }
    
    /**
     * testing the set method
     */
    private void testSet() {
        init();
        assertTrue("l2.set(l2, 0, 39)", 
                FListInteger.get(l2.set(l2, 0, 39), 0) == 39);
        assertTrue("l2.set(l2, 0, 51)", 
                FListInteger.get(l2.set(l2, 0, 51), 0) == 51);
        assertTrue("l1.set(l1, 0, 10101)",
                FListInteger.get(l1.set(l1, 0, 10101), 0) == 10101);
    }
    
    /**
     * testing the size method
     */
    private void testSize() {
        init();
        assertTrue("FListInteger.size(l0)", FListInteger.size(l0) == 0);
        assertTrue("FListInteger.size(l1)", FListInteger.size(l1) == 1);
        assertTrue("FListInteger.size(l2)", FListInteger.size(l2) == 2);
        assertTrue("FListInteger.size(l3)", FListInteger.size(l3) == 3);
    }
    
    /**
     * testing the toString method
     */
    private void testToString() {
        init();
        assertTrue("l0.toString()", l0.toString().equals("[]"));
        assertTrue("l1.toString()", l1.toString().equals("[5]"));
        assertTrue("l2.toString()", l2.toString().equals("[4, 5]"));
    }
    
    /**
     * testing the equals method
     */
    private void testEquals() {
        init();
        assertTrue("l0.equals(l0)", l0.equals(l0));
        assertTrue("l3.equals(l5)", l3.equals(l5));
    }    
    
    ////////////////////////////////////////////////////////////////

    private int totalTests = 0;       // tests run so far
    private int totalErrors = 0;      // errors so far

    /**
     * Prints failure report if the result is not true.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertTrue(String name, boolean result) {
        if (!result) {
            System.out.println();
            System.out.println("***** Test failed ***** "
                    + name + ": " + totalTests);
            totalErrors = totalErrors + 1;
        }        
        else {
            System.out.println("----- Test passed ----- "
                    + name + ": " + totalTests);
        }
        totalTests = totalTests + 1;
    }

    /**
     * Prints failure report if the result is not false.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertFalse(String name, boolean result) {
        assertTrue(name, !result);
    }

}
