/**
 * import java.util.*;
 * @author arjun
 *
 */

public class TestBTree {
    
    public static void main(String [] args) {
    
        TestBTree test = new TestBTree();
        test.testBuild();
    }
    
    public TestBTree() {
    }
    
    StringByLength len;
    BTree BT0LG;
    BTree mt;    
    
    public void init() {
        StringByLength len = new StringByLength();
        mt = BTree.binTree(len);
        BT0LG = BTree.binTree(len);
    }

    private void testBuild() {
        init();
        System.out.println(BT0LG.toString());
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
