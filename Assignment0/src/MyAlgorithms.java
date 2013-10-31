import java.util.*;

/** CS3500 Assignment0 
*
* @author Arjun Balaji arjunb@ccs.neu.edu
* @version 1.0, 9/10/2013
*/

public class MyAlgorithms {

/**
 * The sort method uses Selection Sort in order to sort an
 * ArrayList of Strings.    
 * @param slist is inputed the ArrayList
 */
    public static void sort(ArrayList<String> slist) {
        if (slist == null) {
            return;
        } 
        else {
            for (int i = 0; i < slist.size() - 1; i++) {        
                for (int j = i + 1; j < slist.size(); j++) {
                    if (slist.get(i).compareTo(slist.get(j)) >= 1) {
                        String temp = slist.get(i);
                        slist.set(i, slist.get(j));
                        slist.set(j, temp);
                    }
                }
            }    
        }
    }
}

