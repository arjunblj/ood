package rbtree;
import java.util.*;

/**
 * 
 * @author Arjun Balaji
 * @version 11/1/2013
 *
 */
public class Node extends RBTree {

    /**
     * this Node's value
     */
    String data;

    /**
     * the RBT on the left side
     */
    RBTree left;

    /**
     * the RBT on the right side
     */
    RBTree right;    
    
    /**
     * The constructor for Node
     * @param data is the string representing data
     * @param left is the node on the left
     * @param right is the node on the right
     * @param isBlack is t if the node is black
     */
    Node(String data, RBTree left, RBTree right, boolean isBlack) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.isBlack = isBlack;
    }

    /**
     * determines if Node is empty
     * @return t if empty, false otherwise 
     */
    public boolean isEmpty() {
        return false;
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
        String s = "";
        if (!left.isEmpty()) {
            s = left.toString() + ", ";
        }
        s = s + data;
        if (!right.isEmpty()) {
            s = s + ", " + right.toString();
        }        
        return s;
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
        if (o instanceof RBTree) {
            return this.equals((RBTree) o);
        }
        else {
            return false;
        }
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
        return data.hashCode() * 2 + left.hashCode() + right.hashCode();
    }

    /**
     * adds the given string to the Node using the comp 
     * @param s is the string being added
     * @param comp is the comparator used for comparison
     * @return an RBTree with the string added
     */
    public RBTree add(String s, Comparator<String> comp) {
        return insert(s, comp).makeBlack();
    }

    /**
     * 
     * @param s is the string inputed in insert
     * @param comp is the comparator being used
     * @return an RBTree with the string inserted
     * inserts the given string into this Node
     */
    public RBTree insert(String s, Comparator<String> comp) {
        int compare = comp.compare(s, data);

        if (compare < 0) {
            return new Node(data, left.insert(s, comp), 
                    right, false).balance().makeBlack();
        }

        else if (compare > 0) {
            return new Node(data, left, 
                    right.insert(s, comp), false).balance().makeBlack(); 
        }

        else {
            return this;
        }
    }
    
    /**
     * Balances the RBTree
     * @return a balanced RBTree
     */
    public RBTree balance() {
        if (this.isBlack && !this.left.isBlack && !this.left.left.isBlack) {
            String temp1 = data;
            this.data = this.left.data;
            this.isBlack = false;

            RBTree temp2 = this.left.right;
            this.left.isBlack = true;

            RBTree temp3 = this.right;
            this.right.data = temp1;
            this.right.left = temp2;
            this.right.right = temp3;
            this.right.isBlack = true;
            return this;
        }            
        else if (this.isBlack && !this.left.isBlack && 
                !this.left.right.isBlack) {
            RBTree temp1 = this.left.right;
            this.left.right = temp1.left;

            RBTree temp2 = this.right;
            this.right = temp1;

            this.right.data = this.data;
            this.data = temp1.data;
            this.right.left = temp2;

            this.isBlack = false;
            this.right.isBlack = true;
            this.right.isBlack = true;
            return this;
        }
        else if (this.isBlack && !this.right.isBlack &&
                !this.right.right.isBlack) {
            String temp1 = this.data;
            RBTree temp2 = this.left;

            this.data = this.right.data;
            this.left = this.right.left;
            this.right = this.right.right;

            RBTree temp3 = this.left;
            this.left.data = temp1;
            this.left.left = temp2;
            this.left.right = temp3;

            this.isBlack = false;
            this.right.isBlack = true;
            this.right.isBlack = true;
            return this;
        }
        else if (this.isBlack && !this.right.isBlack &&
                !this.right.left.isBlack) {
            String temp1 = this.data;
            this.data = this.left.right.data;

            RBTree temp2 = this.left;
            this.left = this.right.left;

            RBTree temp3 = this.right.left;
            this.right.left = temp3.left;

            this.left.data = temp1;
            this.left.right = this.left.left;
            this.left.left = temp2;

            this.isBlack = false;
            this.right.isBlack = true;
            this.right.isBlack = true;
            return this;
        }
        else {
            return this;
        }
    }

    /**
     * @return the RBTree with a black root
     */
    public RBTree makeBlack() {
        this.isBlack = true;
        return this;
    }

    /**
     * converts the RBT to an ArrayList<String>
     * @param values is the ArrayList being flattened
     * @return an ArrayList<String> using the given RBT
     */
    public ArrayList<String> flatten(ArrayList<String> values) {
        this.left.flatten(values);
        values.add(this.data);
        this.right.flatten(values);
        return values;
    }

    /**
     * @return string representing the Node's data
     */
    public String getData() {
        return this.data;
    }

    /**
     * @return RBTree representing the left
     */
    public RBTree getLeft() {
        return left;
    }

    /**
     * @return RBTree representing the right
     */
    public RBTree getRight() {
        return right;
    }

    /**
     * @return checks if the Node contains the given string
     * @param s is the given string
     */
    public boolean contains(String s) {
        if (s.equals(data)) {
            return true;
        }
        else {
            return this.left.contains(s) || this.right.contains(s);
        }
    }
    
    /**
     * 
     * @return a boolean t if this is a valid RBTree
     */
    public boolean repOK() {
        return (left instanceof RBTree) &&
                (right instanceof RBTree) &&
                (data instanceof String) &&
                (this.isBlack) &&
                left.repOK() && right.repOK();
    }

}
