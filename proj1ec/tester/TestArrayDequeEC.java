package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void RandomCompareTest(){
        String message = "";
        Integer randVal;
        StudentArrayDeque<Integer> L2 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> L1 = new ArrayDequeSolution<>();
        int N = 1000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                case 0:
                    // addLast
                    randVal = StdRandom.uniform(0, 100);
                    L1.addLast(randVal);
                    L2.addLast(randVal);
                    message =  message + "\n" +"addLast(" + randVal + ")" ;
                    break;
                case 1:
                    // addFirst
                    randVal = StdRandom.uniform(0, 100);
                    L1.addFirst(randVal);
                    L2.addFirst(randVal);
                    message =  message + "\n" +"addFirst(" + randVal + ")" ;
                    break;
                case 2:
                    // removeLast
                    if (L1.isEmpty()) {
                        assertTrue(L2.isEmpty());
                    } else {
                        Integer a1 = L1.removeLast();
                        Integer a2 = L2.removeLast();
                        message = message + "\n" +"removeLast()";
                        assertEquals(message,a1,a2);
                    }
                    break;
                case 3:
                    // removeFirst
                    if (L1.isEmpty()) {
                        assertTrue(L2.isEmpty());
                    } else {
                        Integer a1 = L1.removeLast();
                        Integer a2 = L2.removeLast();
                        message = message + "\n" + "removeFirst()";
                        assertEquals(message,a1,a2);
                    }
                    break;
            }
        }

        // 验证最终所有元素一致
        //assertEquals(L1, L2);
    }
}
