package deque;

public interface Deque<T> {
    /**头节点插入*/
    void addFirst(T item);
    /**尾节点插入*/
    void addLast(T item);

    /**大小*/
    int size();
    /**打印*/
    void printDeque();
    /**头移除*/
    T removeFirst();
    /**尾移除*/
    T removeLast();
    /**查找*/
    T get(int inx);
    default boolean isEmpty() {
            return this.size() == 0;
    }
}
