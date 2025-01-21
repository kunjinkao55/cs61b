package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
  public  void testThreeAddThreeRemove(){
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList();
        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
  }
  @Test
  public void randomizedTest(){
      AListNoResizing<Integer> L1 = new AListNoResizing<>();
      BuggyAList<Integer> L2 = new BuggyAList<>();
      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L1.addLast(randVal);
              L2.addLast(randVal);
              //System.out.println("addLast(" + randVal + ")");

          } else if (operationNumber == 1) {
              // size
              int size1 = L1.size();
              int size2 = L2.size();
              //System.out.println("size1: " + size1 );
              //System.out.println("size2: " + size2 );

          } else if(L1.size() <=0){
              continue;
          } else if(operationNumber == 2){
              //gerLast
              int last1 = L1.getLast();
              int last2= L2.getLast();
              //System.out.println("last1: " + last1);
              //System.out.println("last2: " + last2);

          }
          else{
              int last1 = L1.removeLast();
              int size1 = L1.size();
              int last2 = L2.removeLast();
              int size2 = L2.size();
              //System.out.println("last1: " + last1 + " and "+"size1: " + size1);
              //System.out.println("last2: " + last2 + " and "+"size2: " + size2);

          }
      }
  }

}
