package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{
    private T[] items;  // 存储元素的数组
    private int head;   // 头部指针，指向第一个元素
    private int tail;   // 尾部指针，指向下一个可插入的位置
    private int size;   // 当前元素数量

    // 构造函数
    public ArrayDeque() {
        items = (T[]) new Object[8];  // 初始容量为 8
        head = 0;
        tail = 0;
        size = 0;
    }
    public int head(){
        return head % items.length;
    }
    // 返回双端队列的大小
    @Override
    public int size() {
        return size;
    }

    // 检查双端队列是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 在头部插入元素
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);  // 如果数组已满，扩容
        }
        head = (head - 1 + items.length) % items.length;  // 计算新的头部位置
        items[head] = item;
        size++;
    }

    // 在尾部插入元素
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);  // 如果数组已满，扩容
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;  // 计算新的尾部位置
        size++;
    }

    // 移除并返回头部元素
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;  // 如果双端队列为空，返回 null
        }
        T item = items[head];
        items[head] = null;  // 清除引用
        head = (head + 1) % items.length;  // 更新头部指针
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);  // 如果元素数量过少，缩容
        }
        return item;
    }

    // 移除并返回尾部元素
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;  // 如果双端队列为空，返回 null
        }
        tail = (tail - 1 + items.length) % items.length;  // 计算新的尾部位置
        T item = items[tail];
        items[tail] = null;  // 清除引用
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);  // 如果元素数量过少，缩容
        }
        return item;
    }

    // 获取指定索引的元素
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return items[(head + index) % items.length];  // 计算实际索引
    }

    // 打印双端队列中的所有元素
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    // 调整数组大小
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);  // 将元素按顺序复制到新数组
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    // 检查两个双端队列是否相等
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    public class ArrayDequeIterator<T> implements Iterator<T>{
        int p;
        int count;
        public ArrayDequeIterator(){
            p = head();
            count = 0;
        }
        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public T next() {
            T item = (T) items[p];
            p += 1;
            count +=1;
            p = p % items.length;
            return item;
        }
    }

    public static void main(String args[])
    {
        ArrayDeque ts = new ArrayDeque();
        System.out.println(ts.isEmpty());
        ts.addFirst(1);
        ts.addFirst("plj");
        ts.addLast("锟斤拷");
        ts.printDeque();
        for(Object item:ts){
            System.out.print(item);
        }
    }
}