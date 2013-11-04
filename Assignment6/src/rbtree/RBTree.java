package rbtree;
import java.util.*;

/**
 * 
 * @author arjun
 * @version 11/1/2013
 */
public abstract class RBTree {

    /**
     * the data in the RBT
     */
    String data;
    /**
     * the left RBT
     */
    RBTree left;
    /**
     * the right RBT
     */
    RBTree right;
    /**
     * is true if the Node is black, false otherwise
     */
    boolean isBlack;

    /**
     * 
     * @param s is the string inputed in insert
     * @param comp is the comparator being used
     * @return an RBTree with the string inserted
     * inserts the given string into this BST
     */
    public abstract RBTree insert(String s, Comparator<String> comp);

    /**
     * 
     * @return a balanced RBTree
     */
    public abstract RBTree balance();

    /**
     * 
     * @return an RBTree with the root black
     */
    public abstract RBTree makeBlack();

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * 
     * @return returns the hashCode of this BST
     */
    public abstract int hashCode();

    /**
     * @return the data String
     */
    public abstract String getData();

    /**
     * @return the Left RBTree
     */
    public abstract RBTree getLeft();

    /**
     * @return the Right RBTree
     */
    public abstract RBTree getRight();

    /**
     * @return t if the RBTree is empty
     */
    public abstract boolean isEmpty();

    /**
     * converts the BST to an ArrayList<String>
     * @param values is the given ArrayLit<String>
     * @return an ArrayList<String> using the given BST
     */
    public abstract ArrayList<String> flatten(ArrayList<String> values);

    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public abstract boolean contains(String s);

    /**
     * 
     * @param s the string used for testing
     * @param comp the comp used for sorting
     * @return a Btree with the element added
     */
    public abstract RBTree add(String s, Comparator<String> comp);

    /**
     * @param comp the comparator used for sorting
     * @return an empty leaf
     */
    public static Leaf leafFactory(Comparator<String> comp) {
        return new Leaf(comp);
    }
    
    /**
     * 
     * @return a boolean t if this is a valid RBTree
     */
    public abstract boolean repOK();

}
