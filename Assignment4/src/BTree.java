import java.util.*;
import java.io.*;

/**
 * 
 * @author Arjun Balaji arjunb@ccs.neu.edu
 * @version 10/8/2013
 * Notes to instructor: this assignment was difficult, but
 * I was super psyched when I finished. <3
 *
 */
public class BTree implements Iterable<String> {
    
    /**
     * data is the value in the BST
     */
    String data;
    /**
     * the BST located on the left side
     */
    BTree left;
    /**
     * the BST located on the right side
     */
    BTree right;
    /**
     * the comparator used to sort the BTree
     */
    Comparator<String> comp;
    /**
     * is true if the BST is empty, false otherwise
     */
    boolean isEmpty;
    /**
     * represents the number of active iterators on the BST
     */
    int active;
    
    /**
     * 
     * @param data is the value in the BST
     * @param left is the BST on the left side
     * @param right is the BST on the right side
     * @param comp is the comparator used to sort the BST
     * @param isEmpty represents the number of active iterators
     */
    BTree(String data, BTree left, BTree right, 
            Comparator<String> comp, boolean isEmpty) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.comp = comp;
        this.isEmpty = isEmpty;
        this.active = 0;
    }
    
    
    /**
     * 
     * @param s is the string inputed in insert
     * inserts the given string into this BST
     */
    private void insert(String s) {
        if (this.isEmpty) {
            this.data = s;
            this.left = BTree.binTree(comp);
            this.right = BTree.binTree(comp);
            this.isEmpty = false;
        }
        else {
            if ((comp.compare(this.data, s) == 0)) {
                return;
            }
            else if ((comp.compare(this.data, s)) > 0) {
                left.insert(s);
            }
            else {
                right.insert(s);
            }
        }
    }

    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * 
     * @return produces a a string "x1, x2 ... xn"
     */
    public String toString() {
        if ((this.isEmpty)) {
            return "";
        }
        else {
            if ((this.left.isEmpty &&
                    this.right.isEmpty)) {
                return this.data;
            }
            else {
                if ((this.left.isEmpty &&
                        !this.right.isEmpty)) {
                    return this.right.toString() + ", " + this.data;
                }
                else {
                    if ((!this.left.isEmpty &&
                            this.right.isEmpty)) {
                        return this.left.toString() + ", " + this.data;
                    }
                    else {
                        return this.left.toString() + ", " +
                                this.data + ", " +
                                this.right.toString();
                    }
                }
            }
        }
    }

    /**
     * Factory method to generate 
     * an empty binary search tree
     * with the given <code>Comparator</code>
     *
     * @param comp the given <code>Comparator</code>
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static BTree binTree(Comparator<String> comp) {
        return new BTree("", null, null, comp, true);
    }

    /**
     * Modifies: 
     * this binary search tree by inserting the 
     * <code>String</code>s from the given 
     * <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     */
    public void build(Iterable<String> iter) {
        for (String s:iter) {
            if (active == 0) {
                this.insert(s);
            }
            else {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * 
     * @return returns the hashCode of this BST
     */
    public int hashCode() {
        return this.data.hashCode() * 2;
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of BTree.
     * Produces true if this tree and the given BTree 
     * contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>.
     * So if the first tree was built with Strings 
     * "hello" "bye" and "aloha" ordered
     * lexicographically,  and the second tree was built 
     * with <code>String</code>s "aloha" "hello" and "bye"  
     * and ordered lexicographically, 
     * the result would be true.
     *
     * @param o the object to compare with this
     * @return returns t if this BST and the given Object is true
     */
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }
    
    /**
     * converts the BST to an ArrayList<String>
     * @return an ArrayList<String> using the given BST
     */
    private ArrayList<String> flatten() {
        ArrayList<String> list = new ArrayList<String>();
        while (!this.isEmpty) {
            this.left.flatten();
            for (int i = 0; i < this.left.flatten().size(); i++) {
                list.add(this.left.flatten().get(i));
            }
            list.add(this.data);
            this.right.flatten();
            for (int i = 0; i < this.right.flatten().size(); i++) {
                list.add(this.right.flatten().get(i));
            }
            { 
                return list;
            }
        }
        return new ArrayList<String>();
    }
    
    /**
     * @return creates a new iterator as required
     */
    public Iterator<String> iterator() {
        return new BTreeIterator();
    }
    
    /**
     * 
     * @author Arjun Balaji
     * @version 10/8/2013
     * the iterator used by BTree
     *
     */
    private class BTreeIterator implements Iterator<String> {

        /** index for the element to generate next */
        int current;
        
        /**
         * Initialize the current index to 0
         * Also notify the enclosing class that a 
         * new iterator has been created.
         */
        BTreeIterator() {
            this.current = 0;
            active = active + 1;
        }
        
        /**
         * Can we generate the next element?
         * For safety we test for null as well
         * 
         * @return true if at least one element in the 
         * <code>ArrayList</code> has not been generated
         */
        public boolean hasNext() {
            return flatten() != null && this.current < flatten().size();
        }
        
        /**
         * If possible, generate the next element and advance the 
         * current index
         * Throw <code>NoSuchElementException</code> if no element
         * can be generated.
         * Throw <code>ConcurrentModificationException</code>
         * if some other invocation has thrown this exception already.
         * @return the next element in the <code>ArrayList</code>
         */
        public String next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            String result = flatten().get(current);
            current = current + 1;
            if (!this.hasNext()) {
                active = active - 1;
            }
            return result;
        }
        
        /**
         * remove throw
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

/**
 * 
 * @author Arjun Balaji 
 * @version 10/8/2013
 * the SBL class compares strings by length
 *
 */
class StringByLength implements Comparator<String> {

    /**
     * @param o1 is the first string being compared
     * @param o2 is the second string being compared
     * @return returns an int used to compare the inputs
     */
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

}

/**
 * 
 * @author Arjun Balaji
 * the SBL compares strings alphabetically
 * @version 10/8/2013
 */
class StringByLex implements Comparator<String> {

    /**
     * @param o1 is the first string being compared
     * @param o2 is the second string being compared
     * @return returns an int used to compare the inputs
     */
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);    
    }

}