import java.util.*;
import java.io.*;

/**
 * 
 * @author Arjun Balaji arjunb@ccs.neu.edu
 * @version 10/19/2013
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
        return new BTreeIterator();
    }

    /** Modifies: 
     * this binary search tree by inserting the 
     * first numStrings <code>String</code>s from 
     * the given <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     * @param numStrings number of <code>String</code>s
     *        to iterate through and add to BTree
     *        If numStrings is negative or larger than the number of 
     *        <code>String</code>s in iter then all <code>String</code>s
     *        in iter should be inserted into the tree 
     */
    public void build(Iterable<String> iter, int numStrings) {
        if (active > 0) {
            throw new ConcurrentModificationException();
        }
        else {
            for (String s:iter) {
                if (active == 0 && numStrings != 0) {
                    this.insert(s);
                    numStrings = numStrings - 1;
                }
            }
        }
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
     * @version 10/8/2013
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

/**
 * 
 * @author Arjun Balaji
 * the SBL compares strings alphabetically
 * @version 10/19/2013
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

/**
 * 
 * @author Arjun Balaji 
 * @version 10/19/2013
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
 * @author Arjun Balaji / balaji.a@husky.neu.edu
 * @version 10/18/2013
 * 
 * This is the class used for testing BTree timing.
 */
class TimeTester {

    /**
     * The main method invokes the tests.
     * @param args unused input
     */
    public static void main(String [] args) {
        TimeTester tester = new TimeTester();
        tester.testList();
        System.out.println("\n\n Tests Completed");
    }

    /**
     * the constructor for TestFListInteger
     */
    public TimeTester() {
        // an empty constructor since this class is just used for testing.
    }
    
    /**
     * the lex comprator
     */
    StringByLex lex;
    /**
     * the len comparator
     */
    StringByLength length;
    /**
     * the reverse lex comparator
     */
    StringReverseByLex revlex;
    /**
     * the withoutprefix lex comparator
     */
    StringWithOutPrefixByLex wplex;
    /**
     * the ArrayList<String> representing file names
     */
    ArrayList<String> fileName;
    /**
     * the ArrayList<Integer> representing numbers
     */
    ArrayList<Integer> num;
    /**
     * the ArrayList<Comparator<String>> representing comparators
     */
    ArrayList<Comparator<String>> comp;
    /**
     * the ArrayList<String> representing classics
     */
    ArrayList<String> classics;
    /**
     * a long used to calculate run time
     */
    long startTime;
    /**
     * a long used to calculate run time
     */
    long endTime;
    /**
     * a long used to calculate run time
     */
    long current;

    /**
     * initializes examples used for testing
     */
    public void init() {
        length = new StringByLength();
        lex = new StringByLex();
        revlex = new StringReverseByLex();
        wplex = new StringWithOutPrefixByLex();

        fileName = new ArrayList<String>(Arrays.asList(
                "lexicographically_ordered.txt",
                "reverse_ordered.txt",
                "prefix_ordered.txt",
                "random_order.txt"));

        num = new ArrayList<Integer>(Arrays.asList(
                2000, 4000, 8000, 16000, 20000, 24000));

        comp = new ArrayList<Comparator<String>>(Arrays.asList(
                lex, revlex, wplex));
        
        classics = new ArrayList<String>(Arrays.asList(
                "hippooath_DUPLICATES.txt",
                "iliad.txt"));
    }

    /**
     * has loops that run through all the generated tests and print output
     * representing runtime
     */
    public void testList() {
        init();
        for (Integer w : num) {
            for (Comparator<String> c : comp) {
                for (String f : fileName) {
                    BTree b = BTree.binTree(c);
                    StringIterator s = new StringIterator(f);

                    startTime = System.currentTimeMillis();
                    b.build(s, w);
                    endTime = System.currentTimeMillis();
                    System.out.println(c + "," + w + "," + f + "," + "build"
                            + "," + (endTime - startTime));

                    startTime = System.currentTimeMillis();
                    Iterator<String> it = b.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    endTime = System.currentTimeMillis();
                    System.out.println(c + "," + w + "," + f + "," + "iterator"
                            + "," + (endTime - startTime));
                    
                    StringIterator c2 = new StringIterator("contains.txt");
                    startTime = System.currentTimeMillis();
                    while (c2.hasNext()) {
                        b.contains(c2.next());
                    }
                    endTime = System.currentTimeMillis();
                    System.out.println(c + "," + w + "," + f + "," + "contains"
                            + "," + (endTime - startTime));
                }
            }
        }
        
        for (Comparator<String> c : comp) {
            for (String f : classics) {
                BTree b = BTree.binTree(c);
                
                StringIterator s1 = new StringIterator(f);
                startTime = System.currentTimeMillis();
                b.build(s1, -10);
                endTime = System.currentTimeMillis();
                System.out.println(c + "," + f + "," + "build"
                        + "," + (endTime - startTime));

                startTime = System.currentTimeMillis();
                Iterator<String> it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
                endTime = System.currentTimeMillis();
                System.out.println(c + "," + f + "," + "contains"
                        + "," + (endTime - startTime));

                StringIterator c1 = new StringIterator("contains.txt");
                startTime = System.currentTimeMillis();
                while (c1.hasNext()) {
                    b.contains(c1.next());
                }
                endTime = System.currentTimeMillis();
                System.out.println(c + "," + f + "," + "iterator"
                        + "," + (endTime - startTime));
            }
        }
    }

}