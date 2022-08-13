import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);
    @Test
    public void testOffBy5() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('a', 'h'));
        assertTrue(offBy5.equalChars('f', 'a'));

    }
}
