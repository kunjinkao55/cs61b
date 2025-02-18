import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class test<T> implements Iterable<T>{
    @Override
    public Iterator<T> iterator() {
        List a1 = new LinkedList();
        List a2 = new LinkedList();
        List a3 = new LinkedList();
        for(int i = 0;i<31;i++){
            if(i % 5 == 0) {
                a1.addLast(i);
            } else if(i % 5 == 1){
                a2.addLast(i);
            } else {
                a3.addLast(i);
            }
        }
        for(Object i:a1){
            System.out.print(i);
        }
        System.out.println("");
        for(Object i:a2){
            System.out.print(i);
        }
        System.out.println("");
        for(Object i:a3){
            System.out.print(i);
        }
        System.out.println("");
        Iterator b1 = a1.iterator();
        Iterator b2 = a2.iterator();
        Iterator b3 = a3.iterator();
        List<Iterator<Integer>> list = new LinkedList<>();
        list.addLast(b1);
        list.addLast(b2);
        list.addLast(b3);
        Iterator test = new IteratorOfIterators(list);
        return test;
    }
}
