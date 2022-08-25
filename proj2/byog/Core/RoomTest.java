package byog.Core;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {
    @Test
    public void overloadTest() {
        Room a = new Room(10, 20, 0, 0);
        Room[] list = new Room[1];
        list[0] = a;

        Room b = new Room(1, 2, 0, 0);
        assertEquals(true, b.overlap(list, 1));

        Room c = new Room(1, 2, 20, 30);
        assertEquals(false, c.overlap(list, 1));

    }
}
