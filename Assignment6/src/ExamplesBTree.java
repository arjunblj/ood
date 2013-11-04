import java.util.*;
import tester.*;

/**
 *
 * @author Arjun Balaji
 * balaji.a@husky.neu.edu
 * @version 11/1/2013
 *
 */

public class ExamplesBTree {
    
    /**
     * example comparator
     */
    StringByLength len;
    /**
     * example comparator
     */
    StringByLex lex;
    /**
     * example BTree used for testing
     */
    BTree mt;
    /**
     * example BTree used for testing
     */
    BTree mt2;
    /**
     * example BTree used for testing
     */
    BTree mt3;
    /**
     * example ArrayList used for testing
     */
    ArrayList<String> testAL1;
    /**
     * example ArrayList used for testing
     */
    ArrayList<String> mtAL;
    
    /**
     * initializes examples for testing
     */
    public void init() {
        len = new StringByLength();
        lex = new StringByLex();
        mt = new BTree(lex);
        mt2 = new BTree(lex);
        mt3 = new BTree(lex);
        
        testAL1 = new ArrayList<String>(Arrays.asList(
                "aardvark", "abacus", "abampere", "adiabaticlly",
                "anacardiaceous", "applicatively", "asphyxiants"));
        mtAL = new ArrayList<String>();
    }

    /**
     * testing the hashCode method
     * @param t the Tester used
     */
    public void testBTree(Tester t) {
        init();
        t.checkExpect(mt.contains("aardvark"), false);
        t.checkExpect(mt.contains("abacus"), false);
        
        mt.build(testAL1, 2);
        t.checkExpect(mt.contains("aardvark"));
        t.checkExpect(mt.contains("abacus"));
        t.checkExpect(mt.contains("abampere"), false);
        
        mt.build(testAL1, -3);
        t.checkExpect(mt.contains("aardvark"));
        t.checkExpect(mt.contains("applicatively"));
        
        init();
        mt.build(testAL1, -4);
        mt2.build(testAL1, -3);
        t.checkExpect(mt.hashCode() == mt2.hashCode());
        t.checkExpect(mt.hashCode() == mt3.hashCode(), false);
        
        init();
        mt.build(testAL1, -4);
        mt2.build(testAL1, -3);
        t.checkExpect(mt.hashCode() == mt2.hashCode());
        t.checkExpect(mt.hashCode() == mt3.hashCode(), false);
        
        init();
        mt.build(testAL1, 2);
        mt2.build(testAL1, 4);
        mt3.build(testAL1, -3);
        t.checkExpect(mt.toString(), "aardvark, abacus");
        t.checkExpect(mt2.toString(),
                "aardvark, abacus, abampere, adiabaticlly");
        t.checkExpect(mt3.toString(),
                "aardvark, abacus, abampere, adiabaticlly, "
                + "anacardiaceous, applicatively, asphyxiants");
        
        init();
        mt.build(testAL1, 2);
        mt2.build(testAL1, 4);
        mt3.build(testAL1, -3);
        mt.iterator();
        t.checkExpect(mt.iterator().next().equals("aardvark"));
        t.checkExpect(mt.iterator().hasNext());
        
        init();
        t.checkExpect(mt.binTree(len).equals(mt2.binTree(len)), true);

        init();
        mt.build(testAL1);
        t.checkExpect(mt.flatten(mtAL).equals(testAL1));

        init();
        t.checkExpect(mt.contains("aardvark"), false);
        t.checkExpect(mt.contains("abacus"), false);
        
        mt.build(testAL1, 2);
        t.checkExpect(mt.contains("aardvark"));
        t.checkExpect(mt.contains("abacus"));
        t.checkExpect(mt.contains("abampere"), false);
        
        mt.build(testAL1, -3);
        t.checkExpect(mt.contains("aardvark"));
        t.checkExpect(mt.contains("applicatively"));

        init();
        StringByLength len2 = new StringByLength();
        StringByLex lex2 = new StringByLex();
        t.checkExpect(len2.compare("a", "b"), 0);
        t.checkExpect(lex2.compare("a", "b") == -1);
        
        init();
        t.checkExpect(len.compare("haha", "ha"), 2);
        t.checkExpect(lex.compare("abc", "xyz"), -23);
        
//        init();
//        mt.active = 4;
//        t.checkException(new ConcurrentModificationException(), mt, 
//                "build(testAL1)");
        
        init();
        t.checkExpect(mt.equals(mt3));
        t.checkExpect(mt.equals(5), false);
        
        init();
        t.checkExpect(mt.iterator().hasNext(), false);
    }
    
}
