/**
package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class RandomCompareTest {
    @Test
    public void anotheRandomizedTest() {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        int N = 1000;
        int o1 = 0, o2 = 0, o3 = 0, o4 = 0, o0 = 0, on1 = 0;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(-1, 5);
            if (operationNumber == 0) {
                // addLast
                o0 +=1;
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
            }else if(operationNumber == -1){
                on1 += 1;
                int randVal = StdRandom.uniform(0, 100);
                L1.addFirst(randVal);
                L2.addFirst(randVal);
            } else if (operationNumber == 1) {
                // size
                o1 += 1;
                int size1 = L1.size();
                int size2 = L2.size();
                assertEquals(size1,size2);

            } else if(L1.size() <=0){
                continue;
            } else if(operationNumber == 2){
                o2+=1;
                int index = StdRandom.uniform(0, L1.size());
                int last1 = L1.get(index);
                int last2= L2.get(index);
                assertEquals(last1,last2);

            }
            else if(operationNumber == 3){
                o3+=1;
                int last1 = L1.removeLast();
                int last2 = L2.removeLast();
                assertEquals(last1,last2);
            }else if(operationNumber >= 4){
                o4 +=1;
                int first1 = L1.removeFirst();
                int first2 = L2.removeFirst();
                assertEquals(first1,first2);
            }
        }
        L1.printDeque();
        L2.printDeque();
        System.out.println(L1.size());
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        int N = 1000000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 6); // 生成 0~5，共6种操作
            switch (operationNumber) {
                case 0:
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    L1.addLast(randVal);
                    L2.addLast(randVal);
                    break;
                case 1:
                    // addFirst
                    randVal = StdRandom.uniform(0, 100);
                    L1.addFirst(randVal);
                    L2.addFirst(randVal);
                    break;
                case 2:
                    // 检查 size
                    assertEquals(L1.size(), L2.size());
                    break;
                case 3:
                    // get
                {
                    if(L1.size() == 0)
                        break;
                    int index = StdRandom.uniform(0, L1.size());
                    assertEquals(L1.get(index), L2.get(index));
                }
                break;
                case 4:
                    // removeLast
                    if (L1.isEmpty()) {
                        assertNull(L1.removeLast());
                        assertNull(L2.removeLast());
                    } else {
                        assertEquals(L1.removeLast(), L2.removeLast());
                    }
                    break;
                case 5:
                    // removeFirst
                    if (L1.isEmpty()) {
                        assertNull(L1.removeFirst());
                        assertNull(L2.removeFirst());
                    } else {
                        assertEquals(L1.removeFirst(), L2.removeFirst());
                    }
                    break;
            }
        }

        // 验证最终所有元素一致
        assertEquals(L1.size(), L2.size());
        System.out.print(L1.equals(null));
    }
}
*/
