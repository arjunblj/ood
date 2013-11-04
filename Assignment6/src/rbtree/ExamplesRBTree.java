package rbtree;

import java.util.*;
import tester.*;

/**
 * 
 * @author Arjun Balaji
 * @version 11/1/2013
 *
 */
public class ExamplesRBTree {
    
    /**
     * example comparator
     */
    StringByLength len;
    /**
     * example comparator
     */
    StringByLex lex;
    /**
     * Empty tree 1
     */
    Leaf mt1;
    /**
     * Empty tree 2
     */
    Leaf mt2;
    /**
     * Empty tree 3
     */
    Leaf mt3;
    /**
     * ArrayList used for testing.
     */
    ArrayList<String> arlist;
    /**
     * Node used for testing
     */
    Node node1;
    
    /**
     * initializes examples for testing
     */
    public void init() {
        len = new StringByLength();
        lex = new StringByLex();
        mt1 = new Leaf(len);
        mt2 = new Leaf(lex);
        mt3 = new Leaf(len);
        arlist = new ArrayList<String>();
        node1 = new Node("hi", mt1, mt3, true);
    }
    
    /**
     * testing methods in the Leaf class
     * @param t the Tester used
     */
    public void testLeaf(Tester t) {
        init();
        // testing toString
        t.checkExpect(mt1.toString(), "");
        
        // testing isEmpty
        t.checkExpect(mt1.isEmpty());
        
        // testing equals
        t.checkExpect(mt2.equals("hi"), false);
        t.checkExpect(mt1.equals(mt3));
        
        // testing hashCode
        t.checkExpect(mt1.hashCode() == 17);
        
        // testing add
        t.checkExpect(mt1.add("hi", len).contains("hi"));
        t.checkExpect(mt1.add("hi", len).isBlack);
        
        init();
        
        // testing insert
        t.checkExpect(mt1.insert("hi", len).contains("hi"));
        t.checkExpect(mt1.contains("arjun"), false);
        t.checkExpect(mt1.contains("arjun", len), false);

        init();
        
        // testing contains
        t.checkExpect(mt1.contains("random string"), false);
        t.checkExpect(mt1.insert("hello", len).contains("hello"));
        t.checkExpect(mt1.contains("hello"), false);
        
        // testing balance
        t.checkExpect(mt1.balance().equals(new Leaf(len)));
        
        init();
        
        // testing makeBlack
        t.checkExpect(mt1.makeBlack(), mt1);
        
        // testing getData
        t.checkExpect(mt1.getData(), "Leaf has no data.");
        
        // checking getLeft
        t.checkException(new RuntimeException("Leaf has no left! Try again!"), 
                mt1, "getLeft");

        // checking getRight
        t.checkException(new RuntimeException("Leaf has no right! Try again!"), 
                mt1, "getRight");
        
        // checking flatten
        t.checkExpect(mt1.flatten(arlist), arlist);
        
        // checking repOK
        init();
        t.checkExpect(mt1.repOK());
        mt1.isBlack = false;
        t.checkExpect(mt1.repOK(), false);
    }
    
    /**
     * Testing the methods in the Node class
     * @param t is the tester being used
     */
    public void testNode(Tester t) {
        init();
        
        // checking isEmpty
        t.checkExpect(node1.isEmpty(), false);
        
        // checking toString
        node1.insert("hey", len);
        node1.insert("h", len);
        
        // check hashCode
        init();
        t.checkExpect(node1.hashCode(), 6692);
        
        // check flatten
        init();
        t.checkExpect(node1.flatten(arlist), new ArrayList<String>(
                Arrays.asList("hi")));
        
        // check getData
        init();
        t.checkExpect(node1.getData(), "hi");
        
        // check getLeft
        t.checkExpect(node1.getLeft(), mt1);
        
        // check getRight
        t.checkExpect(node1.getRight(), mt3);
        
        // check contains
        t.checkExpect(node1.contains("hi"));
        t.checkExpect(node1.contains("uefuiwae"), false);
        
        // checkRepOK
        init();
        t.checkExpect(node1.repOK());
        node1.isBlack = false;
        t.checkExpect(node1.repOK(), false);
    }
    
    /**
     * Testing the methods in the RBTree class
     * @param t is the tester being used
     */
    public void testRBTree(Tester t) {
        init();
        t.checkExpect(RBTree.leafFactory(len), mt1);
    }

    /**
     * Testing the methods in the 2 comparator classes
     * @param t is the tester being used
     */
    public void testComparators(Tester t) {
        t.checkExpect(len.compare("haha", "ha"), 2);
        t.checkExpect(lex.compare("abc", "xyz"), -23);
    }
}
