package rbtree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Arjun Balaji
 * @version 11/1/2013
 *
 */
public class Leaf extends RBTree {

    /**
     * is t if the Node is black
     */
    boolean isBlack;
    /**
     * the comp used to sort the tree
     */
    Comparator<String> comp;

    /**
     * 
     * @param comp the comparator that leaf takes
     */
    public Leaf(Comparator<String> comp) {
        this.isBlack = true;
        this.comp = comp;
    }

    /**
     * determines if Leaf is empty
     * @return t if empty, false otherwise 
     */
    public boolean isEmpty() {
        return true;
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
        return "";
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of RBTree.
     * Produces true if this tree and the given RBTree 
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
        return (o instanceof Leaf);
    }

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * 
     * @return returns the hashCode of this RBTree
     */
    public int hashCode() {
        return 17;
    }

    /**
     * adds the given string to the Node using the comp 
     * @param s is the string inputed in insert
     * @param c is the comparator inputed in insert
     * @return an RBTree with s added to it
     */
    public RBTree add(String s, Comparator<String> c) {
        return insert(s, c).makeBlack();
    }

    /**
     * 
     * @param s is the string inputed in insert
     * @param c is the comparator inputed in insert
     * @return BTree w/ the given string into this Node
     */
    public RBTree insert(String s, Comparator<String> c) {
        return new Node(s, new Leaf(c), new Leaf(c), true);
    }

    /**
     * Effect:
     * Checks to see if this Leaf contains s
     * @param s <code>String</code> to look for in this
     * @param c is the comparator being compared 
     * @return true if this contains s
     */
    public boolean contains(String s, Comparator<String> c) {
        return false;
    }

    /**
     * @return a Leaf as there's nothing to balance in the empty
     */
    public Leaf balance() {
        return new Leaf(this.comp);
    }
    
    /**
     * @return an RBTree with the root turned to black 
     */
    public RBTree makeBlack() {
        return this;
    }

    /**
     * @return a message since Leaf has no data
     */
    public String getData() {
        return "Leaf has no data.";
    }

    /**
     * @return RBTree representing the left
     */
    public RBTree getLeft() {
        throw new RuntimeException("Leaf has no left! Try again!");
    }

    /**
     * @return RBTree representing the right
     */
    public RBTree getRight() {
        throw new RuntimeException("Leaf has no right! Try again!");
    }

    /**
     * converts the RBT to an ArrayList<String>
     * @return an ArrayList<String> using the given RBT
     * @param values is the arraylist of values that's returned
     */
    public ArrayList<String> flatten(ArrayList<String> values) {
        return values;
    }

    /**
     * @return checks if the Node contains the given string
     * @param s is the given String that contains is checking
     */
    public boolean contains(String s) {
        return false;
    }

    /**
     * 
     * @return a boolean t if this is a valid RBTree
     */
    public boolean repOK() {
        return (this instanceof Leaf) &&
               this.isBlack;
    }

}
