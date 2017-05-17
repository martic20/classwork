package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuntTest {

   
   
	@Test
    public void testPuntBuit() {
        // comprova que el constructor Punt() deixi a 0 els valors
    	Punt a = new Punt(3,4);
    	Punt b = new Punt(4,5);
    	Segment s = new Segment(a,b);
    	System.out.println(s.longitud());
        assertEquals("some text",1.41, s.longitud(),0.01); 
    }
 
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("TestPunt");
    }
}
