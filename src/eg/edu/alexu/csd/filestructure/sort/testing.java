package eg.edu.alexu.csd.filestructure.sort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class testing {


    public static void main(String[] args) throws FileNotFoundException    {
    	  PrintStream slow = new PrintStream(new FileOutputStream("sloow.txt"));
    	  PrintStream fast = new PrintStream(new FileOutputStream("faast.txt"));
        ArrayList<Integer> unordered = new ArrayList(); long t; 
        ISort tests = new Sort();
        
       int ip; Random r = new Random();
       for(int i=1000;i<=100000;i+=1000) {
    	   unordered.clear();
    	   for(int j=0;j<i;j++) {
    	   unordered.add(r.nextInt(50000));  		
    	   }
    	  long start= System.currentTimeMillis();
    	  tests.sortSlow(unordered);  
     	  t= System.currentTimeMillis()-start;
    	  System.setOut(slow);
   	      System.out.println(i + "\t" + t);
    	  start = System.currentTimeMillis();
    	  tests.sortFast(unordered);
    	  t=System.currentTimeMillis()-start;
    	  System.setOut(fast);
    	  System.out.println(i + "\t" + t);
       } 
        
    }
	


}
