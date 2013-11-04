package rbtree;
import java.util.Comparator;

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