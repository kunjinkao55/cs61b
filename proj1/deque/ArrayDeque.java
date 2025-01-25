package deque;
public class ArrayDeque<T> implements Deque<T> {
    private int poSize;
    private int neSize;
    public T[] items;
    public ArrayDeque(){
        poSize = 0;
        neSize = 0;
        items = (T[])new Object[8];
    }
    /**重点 用取模运算模拟环形结构，头插相当于数组反向插入数据，类似Python的列表*/
    /**大小*/
    public int size(){
        return poSize + neSize;
    }

    public void resize(){
        T[] temp = (T[]) new Object[items.length / 2];
        int p = 0;
        while(items[p] != null)
        {p+=1;}
        while(items[p] == null)
        {p +=1;}
        int i = 0;
        while(items[p % items.length] != null){
            temp[i] = items[p % items.length ];
            i+=1;
            p+=1;
        }
        items = temp;
        int tempsize = this.size();
        neSize = 0;
        poSize = tempsize;
    }
    /**头节点插入*/
    /**环形模拟: 头插相当于插入数组最末端*/
    public void addFirst(T item){
        if(this.size() >= items.length){
            T[] temp = (T[]) new Object[this.size() * 2];
            System.arraycopy(items,0,temp,0,this.size());
            items = temp;
        }
        /**
         *  0 1 2 3 4 5 6 7
         *  a b c d e ? ? f
         *  neSize = 1,items.length = 8
         *  下一个头插目标位置: 6 正确
         *  因为items.length 始终大于neSize,(刚好相等的时候会扩容),所以不需要取模
         * */
        items[items.length - neSize - 1] = item;
        neSize+=1;
    }

    /**尾节点插入*/
    public void addLast(T item){
        if(this.size() >= items.length){
            T[] temp = (T[]) new Object[this.size() * 2];
            System.arraycopy(items,0,temp,0,this.size());
            items = temp;
        }
        items[poSize] = item;
        poSize+=1;
    }

    /**empty?*/
    public boolean isEmpty(){
        return this.size() == 0;
    }


    /**打印 先打印负半圈，再打印正半圈*/
    public void printDeque(){
        for(int i = items.length - neSize;i<items.length;i++){
            System.out.print(items[i]+" ");
        }
        for(int i = 0;i<poSize;i++){
            System.out.print(items[i]+" ");
        }
        System.out.println("");
    }

    /**头移除*/
    public T removeFirst(){
        if (this.size()==0)
        {return null;}
        if(this.size()< items.length / 4 && items.length < 12)
        {this.resize();}
        T ans = items[(items.length - neSize)%items.length];
        items[(items.length - neSize)%items.length] = null;
        neSize -= 1;
        return ans;
    }

    /**尾移除*/
    public T removeLast(){
        if (this.size()==0)
        {return null;}
        if(this.size()< items.length / 4 && items.length < 12)
        {this.resize();}
        int index = this.size()-neSize - 1;
        while(index < 0)
        {index += items.length;}
        T ans = items[(index) % items.length];
        items[index % items.length] = null;
        poSize -= 1;
        return ans;
    }

    /**查找*/
    public T get(int inx){
        return items[inx];
    }

    //public Iterator<T> iterator(){};
    public boolean equals(Object o){
        if(!(o instanceof ArrayDeque) || (this.items.length != ((ArrayDeque<?>) o).items.length)){
            return false;
        }
        for(int p = 0;p < items.length;p++)
        {
            if (this.items[p] != ((ArrayDeque<?>) o).items[p])
            {
                return false;
            }
        }
        return true;
    }
}