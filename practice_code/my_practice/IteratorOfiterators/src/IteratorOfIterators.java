import java.util.*;
public class IteratorOfIterators implements Iterator<Integer> {
    List<Integer> list;

    public IteratorOfIterators(List<Iterator<Integer>> a){
        list = new LinkedList();
        while(!a.isEmpty()) {
            Iterator<Integer> curr = a.removeFirst();
            if(curr.hasNext()) {
                list.addLast(curr.next());
                a.addLast(curr);
            }
        }
    }
    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }
//广度优先搜索
    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
            }
        return list.removeFirst();
        }
}
