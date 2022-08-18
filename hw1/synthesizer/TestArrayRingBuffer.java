package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        int remove1, remove2;
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);


        for (int i : arb) {
            System.out.println(i);
        }

        try {
            arb.enqueue(6);
        } catch (Exception e) {
            System.out.println("generate correct exception!");
        }

        remove1 = arb.dequeue();
        remove2 = arb.peek();
        assertEquals(1, remove1);
        assertEquals(2, remove2);
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);


    }



    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
