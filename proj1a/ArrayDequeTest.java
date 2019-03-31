/*
 * test unit of ArrayDeque
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void test() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        assertEquals(L.size(), 0);
        for (int i = 0; i < 10; i++) {
            L.addFirst(i);
            L.addLast(++i);
        }
        assertEquals(L.size, 16);
        assertEquals(L.size(), 10);
        L.printDeque();
        System.out.println(" ");
        for (int i = 0; i < 9; i++) {
            System.out.println(L.removeFirst());
        }
        assertEquals(L.size(), 1);
        assertEquals(L.size, 8);
        L.printDeque();
        assertEquals(L.get(0), new Integer(9));
    }
}
