import static org.junit.Assert.*;

import org.junit.Test;

public class FilkTest {

    @Test
    public void testSameNumber(){
        int a = 128;
        int b = 128;
        assertTrue(Flik.isSameNumber(a, b));


    }
}
