import java.util.*;
public class FilterList<T> implements Iterable<T> {
    List<T> list;
    Predicate<T> p;
    public FilterList(List<T> L,Predicate<T> filter) {
        this.list = L;
        this.p = filter;
    }
    @Override
    public Iterator<T> iterator() {
        return new FilterListIterator();
    }

    public class FilterListIterator<T> implements Iterator<T>{
        int count;
        public FilterListIterator(){
            this.count = 0;

        }
        @Override
        public boolean hasNext() {
        while(count < list.size() && !p.test(list.get(count))){
            count += 1;
        }
        return list.size() > count;
        }
        @Override
        public T next() {
            if (hasNext()){
            T ans = (T) list.get(count);
            count += 1;
            return ans;
            }
            throw new NoSuchElementException();
        }
    }
}

