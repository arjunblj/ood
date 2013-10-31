/**
 * 
 * @author Arjun Balaji balaji.a@husky.neu.edu
 * @version 9/27/2013
 *
 */
abstract class FListInteger {

    // ----- BASIC CREATORS -------------------------------
    
    /**
     * Produces an instance of this class
     * @return returns an instance of Empty List
     */
    public static FListInteger emptyList() {
        return new EmptyList();
    }
    
    /**
     * adds the given n to the given FListInteger
     * @param n the given n 
     * @param l is the given FListInteger
     * @return returns a new Add(l, n)
     */
    public static FListInteger add(FListInteger l, Integer n) {
        return new Add(l, n);
    }
    
    // ----- OTHER CREATORS -------------------------------
    
    /**
     * is the given list empty?
     * @param l is the given FLI
     * @return returns t if FLI is empty
     */
    public static boolean isEmpty(FListInteger l) {
        return l.isEmptyMethod();
    }
    
    /**
     * 
     * @return a boolean t if the FLI is empty
     */
    abstract boolean isEmptyMethod();
    
    /**
     * fetches given index from list
     * @param l is the given FLI
     * @param n is the given int
     * @return returns an integer at the given index n
     */
    public static Integer get(FListInteger l, int n) {
        return l.getMethod(n);
    }
    
    /**
     * 
     * @param n the index that's used to get the int
     * @return the integer at the given index
     */
    abstract Integer getMethod(int n);
    
    /**
     * sets the given value x0 at the index x in FListInteger
     * @param l is the given FLI
     * @param n is the given int
     * @param n0 is the given Integer
     * @return returns an FLI with the provided int set
     */    
    public static FListInteger set(FListInteger l, int n, Integer n0) {
        return l.setMethod(n, n0);
    }
    
    /**
     * 
     * @param n int representing index
     * @param n0 Integer that's being put into FLI
     * @return returns FLI with the int set
     */
    abstract FListInteger setMethod(int n, Integer n0);
    
    /**
     * produces the size of the given list
     * @param l is the inputed FLI
     * @return int representing the size of the FLI
     */
    public static int size(FListInteger l) {
        return l.sizeMethod();
    }
    
    /**
     * 
     * @return int representing the size of the FLI
     */
    abstract int sizeMethod();
    
    /**
     * turns given list into a String
     * @return a String with all the values in FLI (ie. [5, 6, 7])
     */    
    public abstract String toString();
    
    /**
     * checks if the given list and this list are equal 
     * @param x is the Object being compared
     * @return t if the Object is equal to the FLI
     */
    public abstract boolean equals(Object x);
    
    /**
     * @return returns an int with the given FLI's hashCode
     */
    public abstract int hashCode();
}

/**
 * 
 * @author arjun
 * @version 9/27/2013
 *
 */
class EmptyList extends FListInteger {
    /**
     * The constructor for Empty List
     */
    EmptyList() {
        // constructor is empty because EmptyList has no args
    }
    
    /**
     * Produces an instance of this class
     * @return new EmptyList instance
     */
    public static FListInteger emptyList() {
        return new EmptyList();
    }
    
    /**
     * checks if list is empty or not
     * @return t if list is empty
     */
    public boolean isEmptyMethod() {
        return true;
    }
    
    /**
     * fetches given index from the list
     * @param n is the given int
     * @return RuntimeException as nothing to get in empty list
     */
    public Integer getMethod(int n) {
        throw new RuntimeException("Nothing is in an empty list.");
    }
    
    /**
     * sets the given value x0 at the index x in FListInteger
     * @param n is the given int (index)
     * @param n0 is the inputed value
     * @return new RuntimeException as there's nothing to get in an empty list 
     */    
    public FListInteger setMethod(int n, Integer n0) {
        throw new RuntimeException("Nothing to set in an empty list.");
    }
    
    /**
     * produces size of given list
     * @return 0 is as size of empty is zero
     */
    public int sizeMethod() {
        return 0;
    }
    
    /**
     * turns given list into a String
     * @return "[]" as empty list has no contents
     */
    public String toString() {
        return "[]";
    }
    
    /**
     * checks if the given list and this list are equal 
     * @param x is the Object being compared
     * @return t if Obj is empty FLI, f otherwise
     */
    public boolean equals(Object x) {
        return x instanceof EmptyList;
    }
    
    /**
     * @return the object's hashcode (0 here)
     */
    public int hashCode() {
        return 0;
    }
}

/**
 * 
 * @author arjun
 * @version 9/27/13
 */
class Add extends FListInteger {
    /**
     * l is the FLI field
     */
    FListInteger l;
    /**
     * n is the integer
     */
    Integer n;
    /**
     * 
     * @param l the FLI in Add
     * @param n the Integer in Add
     */
    Add(FListInteger l, Integer n) {
        this.l = l;
        this.n = n;
    }
    
    /**
     * checks if the given list is empty
     * @return f because it's not empty
     */    
    public boolean isEmptyMethod() {
        return false;
    }
    
    /**
     * fetches given index from the list
     * @param x is the given int
     * @return Integer at given index
     */
    public Integer getMethod(int x) {
        if (x == 0) {
            return this.n;
        }
        else {
            return this.l.getMethod(x - 1);
        }
    }
    
    /**
     * sets the given value x0 at the index x in FListInteger
     * @param x is the given int
     * @param x0 is the given Integer
     * @return returns an FLI with the given x0 substituted
     */
    public FListInteger setMethod(int x, Integer x0) {
        if (x == 0) {
            return FListInteger.add(this.l, x0);
        }
        else {
            return FListInteger.add(FListInteger.set(this.l, x - 1, x0),
                    this.n);
        }
    }
    
    /**
     * produces size of given list
     * @return int with size of FLI
     */
    public int sizeMethod() {
        return 1 + FListInteger.size(this.l);
    }
    
    /**
     * turns given list into a String
     * @return a String with all the values in FLI (ie. [5, 6, 7])
     */
    public String toString() {
        if (FListInteger.isEmpty(this.l)) {
            return "[" + n.toString() + "]";
        }
        else {
            return "[" + n.toString() + ", "
                    + l.toString().substring(1, l.toString().length());
        }
    }
    
    /**
     * checks if the given obj and this list are equal 
     * @param x is the Object being compared
     * @return t if the FLI and given obj are equal
     */
    public boolean equals(Object x) {
        if (!(x instanceof Add)) {
            return false;
        }
        if (FListInteger.size(this) == (
                FListInteger.size((FListInteger) x))) {
            Add var = (Add) x;
            return (this.n.equals(var.n)) && this.l.equals(var.l);
        }
        else {
            return false;
        }

    }
    
    /**
     * @return int representing hashCode
     */
    public int hashCode() {
        return FListInteger.size(l);
    }
}