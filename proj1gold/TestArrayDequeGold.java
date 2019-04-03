import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArray() {
        ArrayDequeSolution<Integer> d1 = new ArrayDequeSolution();
        StudentArrayDeque<Integer> d2 = new StudentArrayDeque();
        int num;
        for (int i = 0; i < 9; i++) {
            num = StdRandom.uniform(10);
            d1.addLast(num);
            d2.addLast(num);
        }
        for (int i = 0; i < 30; i++) {
            int op = StdRandom.uniform(4);
            if (!d1.isEmpty() && !d2.isEmpty())
                op /= 2;
            switch(op) {
                case 0:
                    d1.removeFirst();
                    d2.removeFirst();
                    break;
                case 1:
                    d1.removeLast();
                    d2.removeLast();
                    break;
                case 2:
                    num = StdRandom.uniform(10);
                    d1.addFirst(num);
                    d2.addFirst(num);
                    break;
                case 3:
                    num = StdRandom.uniform(10);
                    d1.addLast(num);
                    d2.addLast(num);
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            assertEquals(d1.get(i), d2.get(i));
        }
        assertEquals(d1.size(), d2.size());

        System.out.print("success");
     }
}
