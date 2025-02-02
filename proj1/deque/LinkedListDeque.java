package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private static class Node<T> {
        T item;
        Node next;
        Node pre;

        private Node(T i, Node n,Node p) {
            item = i;
            next = n;
            pre = p;
        }

        private T get (int inx){
            Node p = this;
            while(inx != 0)
            {
                inx -=1;
                p = p.next;
            }
            return (T) p.item;
        }
        private T getRecursive (int inx){
            Node p = this;
            if(inx == 0)
            {
                return (T) this.item;
            }else{
                return (T) this.next.getRecursive(inx - 1);
            }
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        sentinel = new Node("sentinel",null,null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    /**头节点插入*/
    public void addFirst(T item){
        Node temp = sentinel.next;
        Node newnode = new Node(item,temp,sentinel);
        sentinel.next = newnode;
        temp.pre = newnode;
        size += 1;
    }

    /**尾节点插入*/
    public void addLast(T item){
        Node temp = sentinel.pre;
        Node newnode = new Node(item,sentinel,temp);
        sentinel.pre = newnode;
        temp.next = newnode;
        size += 1;
    }

    /**empty?*/
     public boolean isEmpty(){
         if(sentinel.pre ==sentinel &&sentinel.next==sentinel)
         {return true;}
         return false;
     }

     /**大小*/
     public int size(){
         return size;
     }

     /**打印*/
     public void printDeque(){
         Node p = sentinel.next;
         while(p != sentinel)
         {
             System.out.print(p.item +" ");
             p = p.next;
         }
         System.out.println("");
     }

     /**头移除*/
     public T removeFirst(){
         if(this.isEmpty()){
             return null;
         }else {
             Node ans = sentinel.next;
             sentinel.next = ans.next;
             ans.next.pre = sentinel;
             ans.next = null;
             ans.pre = null;
             size -= 1;
             return (T) ans.item;
         }
     }

     /**尾移除*/
     public T removeLast(){
         if(this.isEmpty()){
             return null;
         }else {
             Node ans = sentinel.pre;
             sentinel.pre = ans.pre;
             ans.pre.next = sentinel;
             ans.next = null;
             ans.pre = null;
             size -= 1;
             return (T) ans.item;
         }
     }

     /**查找*/
     public T get(int inx){
         if(size <= inx)
         {
             return null;
         }
         return (T)sentinel.next.get(inx);
     }

    public T getRecursive(int index)
    {
        if(size <= index)
        {
            return null;
        }
        return (T)sentinel.next.getRecursive(index);
    }
     /**
      * 沟槽迭代器
      */
     @Override
     public Iterator<T> iterator(){
         return new LinkedListDequeIterator();
     }

     /**判断是否相等*/
     public boolean equals(Object o)
     {
         if((!(o instanceof LinkedListDeque)||(this.size()!=((LinkedListDeque<?>)o).size())))
         {
             return false;
         }else
         {
             Node p1 = this.sentinel.next;
             Node p2 = ((LinkedListDeque<?>) o).sentinel.next;
             while(p1!= sentinel)
             {
                 if(! p1.item.equals(p2.item))
                 {
                     return false;
                 }else{
                     p1 = p1.next;
                     p2 = p2.next;
                 }
             }
         }
         return true;
     }

     private class LinkedListDequeIterator<T> implements Iterator <T>{
         private Node<T> p;

         public LinkedListDequeIterator(){
             p = sentinel.next;
         }

         @Override
         public boolean hasNext() {
             return p != sentinel;
         }

         @Override
         public T next() {
             T item =p.item;
             p = p.next;
             return item;
         }
     }
    /**test*/
    public static void main(String args[])
    {
        LinkedListDeque ts = new LinkedListDeque();
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
