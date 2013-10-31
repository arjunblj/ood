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
    ArrayList<String> al1;
    BTree mt;    
    
    public void init() {
        StringByLength len = new StringByLength();
        mt = BTree.binTree(len);
        BT0LG = BTree.binTree(len);
        al1 = new ArrayList<String>();
        al1.add("Brendan");        
        al1.add("Bob");
        al1.add("Jose");
        al1.add("Ev");
    }

    private void testBuild() {
        init();
        assertTrue("testEmpty", BT0LG.equals(mt));
        assertTrue("testBinTree", BT0LG.equals(BTree.binTree(len)));        
        BT0LG.insert("Steve");
        assertTrue("testInsert", BT0LG.data.equals("Steve"));
        BT0LG.build(al1);
        assertTrue("testBuild", BT0LG.left.data.equals("Bob"));
        assertTrue("testBuild2", BT0LG.data.equals("Steve"));
        assertTrue("testBuild3", BT0LG.right.data.equals("Brendan"));
        assertTrue("testBuild4", BT0LG.left.right.data.equals("Jose"));
        assertTrue("testBuild5", BT0LG.left.left.data.equals("Ev"));
        System.out.println(BT0LG.toString());
        assertTrue("testHasNext", BT0LG.iterator().hasNext() == true);
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
