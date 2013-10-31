import java.util.*;

/**
 * Implementing ArrayList Iterator
 * @author Viera K. Proulx vkp
 * @version 2013-010-01
 */
class ArrayListIterator2 implements Iterable<String> {
    /** the <code>ArrayList</code> to iterate over */
    ArrayList<String> arlist;
    
    /** the number of currently active iterators */
    int active = 0;        
    
    /** constructor saves the <code>ArrayList</code> to iterate over */
    ArrayListIterator2(ArrayList<String> arlist){
        this.arlist = arlist;
    }
    
    /** allowing modifications only if no active iterator exists */
    public void add(String s) {
        if (active == 0) {
            this.arlist.add(s);
        }
        else {
          throw new ConcurrentModificationException();
        }
    }
    
    /** produce a new iterator for our <code>ArrayList</code> */
    public Iterator<String> iterator() {
        return new AListIterator();
    }
    
    /**
     * Inner class that implements the <code>Iterator</code> interface
     * 
     * @author Viera K. Proulx vkp
     * @version 2013-10-01
     */
    class AListIterator implements Iterator<String> {
        
        /** index for the element to generate next */
        int current;
        
        /**
         * Initialize the current index to 0
         * Also notify the enclosing class that a 
         * new iterator has been created.
         */
        AListIterator() {
            this.current = 0;
            active = active + 1;
        }
        
        /**
         * Can we generate the next element?
         * For safety we test for null as well
         * 
         * @return true if at least one element in the 
         * <code>ArrayList</code> has not been generated
         */
        public boolean hasNext() {
            return arlist != null && this.current < arlist.size();
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
          // check if at the end
          if (!this.hasNext()) {
            throw new NoSuchElementException();
          }
          // now deliver the data and update itself
          String result = arlist.get(current);
          current = current + 1;
          if (!this.hasNext()) {
              active = active - 1;
          }
          return result;
        }
        
        /** 
         * do nothing (should throw NotImplementedException)
         */
        public void remove() { }
    }
    
}

/**
 * Tests for all kinds of situations with iterators 
 * @author Viera K. Proulx vkp
 * @version 2013-10-01
 */
class ExamplesArrayListIterator {

    public static void main(String[] argv) {
        /** what we want to produce */
        ArrayList<String> result = 
            new ArrayList<String>(Arrays.asList(
                                  "hello",
                                  "aloha",
                                  "sayonara",
                                  "bye"));
              
        System.out.println("Build the array list iterator");
        ArrayListIterator2 ariter = new ArrayListIterator2(result);
        
        System.out.println("Print all data using the iterator");
        for (String s : ariter) {
            System.out.println(s);
        }
       
        System.out.println("Concurrent iterator use 1");       
        try{
            Iterator<String> it = ariter.iterator();
            System.out.println("iterator 1: " + it.next());
            Iterator<String> it2 = ariter.iterator();
            System.out.println("iterator 2: " + it2.next());
            
            System.out.println("ConcurrentModificationException not thrown!!!");
            ariter.add("tata");
        }
        catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException thrown correctly");
            System.out.println(ariter.active);
            ariter.active = 0;
        }  
        
        
        System.out.println("Concurrent iterator use 2");
        
        try{
            Iterator<String> it = ariter.iterator();
            System.out.println("iterator 1: " + it.next());
            for (String s : ariter) {
              System.out.println(s);
            }
            
            System.out.println("ConcurrentModificationException 2 not thrown!!!");
            ariter.add("tata");
        }
        catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException 2 thrown correctly");
        }   
    }
}