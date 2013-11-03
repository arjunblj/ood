import java.util.*;

import rbtree.RBTree;

/**
 * 
 * @author Arjun Balaji arjunb@ccs.neu.edu
 * @version 11/1/2013
 *
 */
public class BTree implements Iterable<String> {

    /**
     * the given red black tree
     */
    RBTree tree;
    /**
     * the comparator used to sort the BTree
     */
    Comparator<String> comp;
    /**
     * represents the number of active iterators on the BST
     */
    int active;

    /**
     * the constructor for BTree
     * @param comp is the comparator used to sort the BTree
     */
    BTree(Comparator<String> comp) {
        this.comp = comp;
        this.tree = RBTree.leafFactory(comp);
        this.active = 0;
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
     * @param numStrings the # of ints to iterate through
     */
    public void build(Iterable<String> iter, int numStrings) {
        if (active > 0) {
            throw new ConcurrentModificationException();
        }
        else {
            for (String s:iter) {
                if (numStrings != 0) {
                    tree = tree.insert(s, comp);
                    numStrings = numStrings - 1;
                }
            }
        }
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
        if (active > 0) {
            throw new ConcurrentModificationException();
        }
        else {
            for (String s:iter) {
                tree = tree.insert(s, comp);
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
        return this.tree.hashCode() * 2;
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
        return o instanceof BTree;
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
        return tree.toString();
    }

    /**
     * @return creates a new iterator as required
     */
    public Iterator<String> iterator() {
        return new BTreeIterator();
    }

    /**
     * Factory method to generate 
     * an empty RBTree
     * with the given <code>Comparator</code>
     *
     * @param comp the given <code>Comparator</code>
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static BTree binTree(Comparator<String> comp) {
        return new BTree(comp);
    }

    /**
     * converts the BST to an ArrayList<String>
     * @return an ArrayList<String> using the given BST
     * @param values is the array list outputed w/ the flattened BTree
     */
    protected ArrayList<String> flatten(ArrayList<String> values) {
        return tree.flatten(values);
    }

    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(String s) {
        return tree.contains(s);    
    }

    /**
     * Checks if this BTree is a valid BTree
     * @return t if it's a valid BTree
     */
    public boolean repOK() {
        return (this.comp instanceof Comparator) &&
                (this.tree instanceof RBTree) &&
                (this.tree.repOK());
    }


    /**
     * 
     * @author Arjun Balaji
     * @version 10/31/2013
     * the iterator used by BTree
     *
     */
    private class BTreeIterator implements Iterator<String> {

        /** index for the element to generate next */
        int current;

        /**
         * values used to flatten the BTree
         */
        ArrayList<String> values = new ArrayList<String>();

        /**
         * Initialize the current index to 0
         * Also notify the enclosing class that a 
         * new iterator has been created.
         */
        BTreeIterator() {
            this.current = 0;
            active = active + 1;
            values = flatten(values);
        }

        /**
         * Can we generate the next element?
         * For safety we test for null as well
         * 
         * @return true if at least one element in the 
         * <code>ArrayList</code> has not been generated
         */
        public boolean hasNext() {
            return values != null && this.current < values.size();
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
            String result = values.get(current);
            current = current + 1;
            if (!this.hasNext()) {
                active = active - 1;
            }
            return result;
        }

        /**
         * remove throws an exception since it's not supported
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}