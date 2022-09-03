package flik;

import static org.junit.Assert.*;
import org.junit.Test;


public class FlikTest {

    @Test
    public void isSameNumberTest(){
        Flik f = new Flik();

        assertTrue(f.isSameNumber(10, 10));

        assertFalse(f.isSameNumber(10, 5));
    }


    @Test
    public void bigAmountTest(){
        Flik f = new Flik();

        for(int i = 0, j = 0 ; i < 500 && j < 500 ; i++, j++){
            assertTrue(String.valueOf(i) + " and " + String.valueOf(j) + " should be equal.", f.isSameNumber(i, j));
        }
    }
}
