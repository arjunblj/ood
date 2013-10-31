import java.util.ArrayList;

import tester.Tester;

/**
 * The ExamplesMyAlgorithms class tests the MyAlgorithms class. 
 * @author Arjun Balaji arjunb@ccs.neu.edu
 * @version 1.0, 9/10/2013
*/

public class ExamplesMyAlgorithms {
    
    /**
     * "a" is an example of MyAlgorithms created for testing. 
     */

    MyAlgorithms a = new MyAlgorithms();
    
    /**
     * testSort holds all testing methods.
     * @param t is the Testing Library
     */
    void testSort(Tester t) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("PBR");
        test.add("Coors Lite");
        test.add("Natty Light");
        test.add("Budweiser");
        test.add("Heineken");
        
        t.checkExpect(test.get(0), "PBR");
        t.checkExpect(test.get(4), "Heineken");
        
        MyAlgorithms.sort(test);
        
        t.checkExpect(test.get(0), "Budweiser");
        t.checkExpect(test.get(4), "PBR");        
    }
}