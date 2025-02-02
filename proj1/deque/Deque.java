package deque;

public interface Deque<T> {
        /**头节点插入*/
        public void addFirst(T item);
        /**尾节点插入*/
        public void addLast(T item);

        /**大小*/
        public int size();
        /**打印*/
        public void printDeque();
        /**头移除*/
        public T removeFirst();
        /**尾移除*/
        public T removeLast();
        /**查找*/
        public T get(int inx);
        public default boolean isEmpty()
        {
                return this.size() == 0;
        }
}
