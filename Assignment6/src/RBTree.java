import java.util.*;

public class RBTree {
    
    /**
     * data is the value in the RBT
     */
    String data;
    /**
     * the BST located on the left side
     */
    RBTree left;
    /**
     * the RBT located on the right side
     */
    RBTree right;
    /**
     * the comparator used to sort the RBT
     */
    Comparator<String> comp;
    /**
     * is true if the RBT is empty, false otherwise
     */
    boolean isEmpty;
    /**
     * is true if the Node is black, false otherwise
     */
    boolean isBlack;
    /**
     * represents the number of active iterators on the BST
     */
    int active;
    
    /**
     * 
     * @param data is the value in the RBT
     * @param left is the RBT on the left side
     * @param right is the RBT on the right side
     * @param comp is the comparator used to sort the RBT
     * @param isEmpty represents whether the RBT is empty
     * @param isBlack represents whether the Node is black or not
     */
    RBTree(String data, RBTree left, RBTree right, 
            Comparator<String> comp, boolean isEmpty, boolean isBlack) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.comp = comp;
        this.isEmpty = isEmpty;
        this.isBlack = isBlack;
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
            this.left = binTree(comp);
            this.right = binTree(comp);
            this.isEmpty = false;
        }
        else {
            if ((comp.compare(this.data, s) == 0)) {
                return;
            }
            else if ((comp.compare(this.data, s)) > 0) {
                left.insert(s);
                this.balance();
            }
            else {
                right.insert(s);
                this.balance();
            }
        }
    }
    
    private void balance() {
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
            }
            else {
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
     * an empty RBTree
     * with the given <code>Comparator</code>
     *
     * @param comp the given <code>Comparator</code>
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static RBTree binTree(Comparator<String> comp) {
        return new RBTree("", null, null, comp, true, true);
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
    private ArrayList<String> flatten(ArrayList<String> values) {

        if (!this.isEmpty) {
            if (!this.left.isEmpty) {
                values = this.left.flatten(values);
            }
            values.add(this.data);
            if (!this.right.isEmpty) {
                values = this.right.flatten(values);
            }

            return values;
        }
        else {
            return values;
        }
    }

    /**
     * @return creates a new iterator as required
     */
    public Iterator<String> iterator() {
        return new RBTreeIterator();
    }
    
    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(String s) {
        if (!this.isEmpty) {
            if (s.equals(data)) {
                return true;
            }
            else {
                return this.left.contains(s) || this.right.contains(s);
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * 
     * @author Arjun Balaji
     * @version 10/30/2013
     * the iterator used by RBTree
     *
     */
    private class RBTreeIterator implements Iterator<String> {

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
        RBTreeIterator() {
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