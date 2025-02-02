package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int max_index = 0;
        for(int i =0,cnt = 0;cnt < this.size();i++,cnt++){
            if (comparator.compare(this.get(i),this.get(max_index))>0) {
                max_index = i;
            }
        }
        return this.get(max_index);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxIndex = this.head();
        for (int i = 0,cnt = 0;cnt < this.size();i++,cnt++) {
            if (c.compare(this.get(i),this.get(maxIndex))>0) {
                maxIndex = i;
            }
        }
        return this.get(maxIndex);
    }
}
