package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void getMaxTest(){
        class antiLengthComparator<T> implements Comparator<T> {
            public antiLengthComparator(){
                super();
            }
            @Override
            public int compare(T o1, T o2) {
                return -(((String)o1).length() - ((String)o2).length());
            }
        }

        class LengthComparator<T> implements Comparator<T> {
            public LengthComparator(){
                super();
            }
            @Override
            public int compare(T o1, T o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        }
        MaxArrayDeque mad1 = new MaxArrayDeque(new antiLengthComparator());
        mad1.addFirst("Hello");
        mad1.addFirst("Hell");
        mad1.addFirst("Hel");
        mad1.addFirst("He");
        mad1.addFirst("H");
        assertEquals(mad1.max(),"H");
        assertEquals(mad1.max(new LengthComparator()),"Hello");
    }
}
