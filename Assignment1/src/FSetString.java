/**
 * 
 * @author Arjun Balaji, arjunb@ccs.neu.edu
 * @version 9/17/2013
 *
 */
public abstract class FSetString {
    
    /**
     * creates a new empty set.
     * @return returns a new EmptySet()
     */
    public static FSetString emptySet() {
        return new EmptySet();
    }
    
    /**
     * 
     * @param s0 is an FSetString
     * @param k0 is a String
     * @return returns a new Insert with the s0 and k0 args
     */
    public static FSetString insert(FSetString s0, String k0) {
        return new Insert(s0, k0);
    }    
    
    /**
     * 
     * @param s0 is an FSetString
     * @param k0 is a String
     * @return passes contains to containsMethod
     */
    public static boolean contains(FSetString s0, String k0) {
        return s0.containsMethod(k0);
    }
    
    /**
     * 
     * @param k0 is the String
     * @return abstract method for EmptySet and Insert
     */
    abstract boolean containsMethod(String k0);
    
    /**
     * 
     * @param s0 is the inputed FSetString
     * @param k0 is the inputed String
     * @return passes k0 to addMethod
     */
    public static FSetString add(FSetString s0, String k0) {
        return s0.addMethod(k0);
    }
    
    /**
     * 
     * @param k0 the string passed from add
     * @return abstract method for EmptySet and Insert
     */
    abstract FSetString addMethod(String k0);
    
    /**
     * 
     * @param s0 is the FSetString
     * @return passes to sizeMethod
     */
    public static int size(FSetString s0) {
        return s0.sizeMethod();
    }
    
    /**
     * 
     * @return abstract method for EmptySet and Insert
     */
    abstract int sizeMethod();
    
    /**
     * 
     * @param s0 is the inputed String
     * @return passes s0 to isEmptyMethod
     */
    public static boolean isEmpty(FSetString s0) {
        return s0.isEmptyMethod(s0);
    }
    
    /**
     * 
     * @param s0 is the inputed FSetString
     * @return abstract method for EmptySet and Insert
     */
    abstract boolean isEmptyMethod(FSetString s0);
    
    /**
     * 
     * @param s0 is the inputed FSetString
     * @param s1 is another FSetString
     * @return passes to isSubsetMethod
     */
    public static boolean isSubset(FSetString s0, FSetString s1) {
        return s0.isSubsetMethod(s1); 
    }
    
    /**
     * 
     * @param s1 is the provided FSS
     * @return abstract method for EmptySet and Insert
     */
    abstract boolean isSubsetMethod(FSetString s1);
    
    /**
     * 
     * @param s0 is the provided FSS
     * @param k0 is the provided String
     * @return passes to absentMethod
     */
    public static FSetString absent(FSetString s0, String k0) {
        return s0.absentMethod(k0);
    }
    
    /**
     * 
     * @param k0 is the provided String
     * @return abstract method for EmptySet and Insert
     */
    abstract FSetString absentMethod(String k0);
    
    /**
     * 
     * @param s0 is the provided FSS
     * @param s1 is another provided FSS
     * @return passes to unionMethod
     */
    public static FSetString union(FSetString s0, FSetString s1) {
        return s0.unionMethod(s1);
    }
    
    /**
     * 
     * @param s1 is the provided FSS
     * @return abstract method for EmptySet and Insert
     */
    abstract FSetString unionMethod(FSetString s1);
    
    /**
     * 
     * @param s0 is one FSS
     * @param s1 is another provided FSS
     * @return passes to intersectMethod
     */
    public static FSetString intersect(FSetString s0, FSetString s1) {
        return s0.intersectMethod(s1);
    }
    
    /**
     * 
     * @param s1 is the provided FSS
     * @return abstract method for EmptySet and Insert
     */
    abstract FSetString intersectMethod(FSetString s1);
    
    /**
     * @return abstract method for EmptySet and Insert
     */
    public abstract int hashCode();
    
    /**
     * @return abstract method for EmptySet and Insert
     */
    public abstract String toString();
    
    /**
     * @param o is the object being compared
     * @return abstract method for EmptySet and Insert
     */
    public abstract boolean equals(Object o);
    
}

/**
 * 
 * @author Arjun Balaji, arjunb@ccs.neu.edu
 * @version 9/18/2013
 *
 */
class EmptySet extends FSetString {
    
    /**
     * the constructor for the empty set
     */
    public EmptySet() {
     // empty constructor because it's the Empty Set
    } 
    
    /**
     * @return false since it's an empty Set
     * @param k is inputed String
     */
    boolean containsMethod(String k) {
        return false;
    }

    /**
     * @return false since it's an empty Set
     * @param k0 is the inputed String
     */
    FSetString addMethod(String k0) {
        return FSetString.insert(this, k0);
    }
    
    /**
     * @return 0 as the size of an empty Set is 0
     */
    int sizeMethod() {
        return 0;
    }
    
    /**
     * @param s0 is the inputed FSS
     * @return true because this is an empty Set
     */
    boolean isEmptyMethod(FSetString s0) {
        return true;
    }

    /**
     * @param s1 is the inputed FSS
     * @return returns true because an empty Set is always a subset
     */
    boolean isSubsetMethod(FSetString s1) {
        return true;
    }

    /**
     * @param k0 is the inputed String
     * @return returns an empty set
     */
    FSetString absentMethod(String k0) {
        return FSetString.emptySet();
    }

    /**
     * @param s1 is the provided FSS
     * @return returns the provided FSS (as it's a union with an empty set)
     */
    FSetString unionMethod(FSetString s1) {
        return s1;
    }

    /**
     * @param s1 is the inputed FSS
     * @return returns an empty Set
     */
    FSetString intersectMethod(FSetString s1) {
        return FSetString.emptySet();
    }

    /**
     * @return 0, since an empty set has a hashcode of 0
     */
    public int hashCode() {
        return 0;
    }

    /**
     * @return return size with 0 hardcoded for empty list
     */
    public String toString() {
        return "{...(" + 0 + " elements)...}";
    }

    /**
     * @param o is the Object is being compared
     * @return false because it can't be empty Set
     */
    public boolean equals(Object o) {
        return false;
    }    
    
}

/**
 * @author Arjun Balaji, arjunb@ccs.neu.edu
 * @version 9/17/2013
 */
class Insert extends FSetString { 
    
    /**
     * s0 is the FSS
     */
    FSetString s0;
    
    /**
     * k0 is the String
     */
    String k0;
    
    /**
     * 
     * @param s0 is the inputed FSS
     * @param k0 is the inputed String
     */
    public Insert(FSetString s0, String k0) {
        this.s0 = s0;
        this.k0 = k0;
    }

    /**
     * @return returns true if k is contained in the FSS
     * @param k is the String passed 
     */
    boolean containsMethod(String k) {
        if (k0.equals(k))
        { 
            return true; 
        }
        else
        { 
            return FSetString.contains(s0, k); 
        }
    }

    /**
     * @param k is the inputed string
     * @return returns with string added to FSS
     */
    FSetString addMethod(String k) {
        if  (!this.containsMethod(k)) 
        {
            return FSetString.insert(this, k);        
        } 
        else {
            return this;
        }
    }
    
    /**
     * @return returns size of FSS
     */
    int sizeMethod() {
        if (FSetString.contains(s0, k0))
        { 
            return FSetString.size(s0);
        } 
        else {
            return 1 + FSetString.size(s0);
        }
    }

    /**
     * @param s is the FSS
     * @return returns false, since Empty is in EmptySet
     */
    boolean isEmptyMethod(FSetString s) {
        return false;
    }

    /**
     * @param s2 is the inputed FSS
     * @return returns false if not a subset, true otherwise
     */
    boolean isSubsetMethod(FSetString s2) {
        if (FSetString.contains(s2, k0))
        { 
            return FSetString.isSubset(s0, s2); 
        }
        else {
            return false;
        }
    }

    /**
     * @param k is the inputed string
     * @return returns an FSS
     */
    FSetString absentMethod(String k) {
        if (k.equals(k0))
        { 
            return FSetString.absent(s0, k); 
        }
        else
        { 
            return FSetString.insert(FSetString.absent(s0, k), k0); 
        }
    }

    /**
     * @param s1 is the inputed FSS
     * @return returns an FSS
     */
    FSetString unionMethod(FSetString s1) {
        if (FSetString.contains(s1, k0))
        { 
            return FSetString.union(s0, s1); 
        }
        else
        { 
            return FSetString.insert(FSetString.union(s0, s1), k0); 
        }
    }

    /**
     * @param s1 is the inputed FSS
     * @return returns an FSS
     */
    FSetString intersectMethod(FSetString s1) {
        if (FSetString.contains(s1, k0))
        { 
            return FSetString.insert(FSetString.union(s0, s1), k0); 
        }
        else
        {
            return FSetString.intersect(s0, s1); 
        }
    }

    /**
     * @return returns an int (the hashCode)
     */
    public int hashCode() {
        return s0.hashCode() * k0.hashCode();
    }

    /**
     * @return returns a string with size
     */
    public String toString() {
        return "{...(" + FSetString.size(this) + " elements)...}";
    }

    /**
     * @param o is the object being compared
     * @return shows if 2 objects are equal
     */
    public boolean equals(Object o) {
        if (o == null)
        { 
            return false; 
        }
        else {
            return this.hashCode() == o.hashCode();
        }
    }
}
