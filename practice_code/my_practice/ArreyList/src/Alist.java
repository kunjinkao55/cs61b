import static java.lang.Math.*;

public class Alist <any>{
/**知识点：数组型链表，泛型链表，链表嵌套*/
    private int size;
    private any[] items;
/**构造函数*/
    public Alist(int length){
        /**creates*/
        items = (any[])new Object[length];
        size = 0;
    }
/**在下标inx处插入元素x，x泛型*/
    public void changeAtInx(any x,int inx){
        if(inx > size){
            System.out.println("item doesn't exist at "+ inx);
            return;
        }
        items[inx] = x;
    }
    public void addAtInx(any x,int inx){
        any[] temp;
        if(inx >= items.length){
            temp = (any[])new Object [inx + 1];
        }else if(size >= items.length){
            temp = (any[])new Object [(size + 1) * 2];
        }else{
            temp = (any[])new Object [items.length];
        }
        System.arraycopy(items,0,temp,0,min(inx,size));
        System.arraycopy(items,min(inx,size),temp,min(size,inx+1),max(0,size - inx));
        if(inx >= items.length){
            size = inx;
        }
        items = temp;
        size += 1;
        items[inx] = x;
    }

    public any getlast(){
        return items[size - 1];
    }

    public any get(int inx){
        return items[inx];
    }

    public int size(){
        return size;
    }

    public void removelast(){
        size-=1;
        items[size] = null;
        if(size <= items.length * 0.75){
            any[] temp = (any[])new Object[size+1];
            System.arraycopy(items,0,temp,0,size);
            items = temp;
        }
    }

    public void remove(int inx){
        System.arraycopy(items,inx + 1,items,inx,size - inx);
        size -= 1;
    }
//打印链表
    public void print(){
        int p = 0;
        System.out.println("size == "+size +" ");
        System.out.println("items.length == "+items.length+" ");
        while(p < size)
        {
            if(items[p] == this)
            {
                System.out.print("这里是数组自己 ");
                p+=1;
                continue;
            }
            if(items[p] instanceof Alist)
            {
                System.out.print("(inside:");
                ((Alist<?>) items[p]).print();
                System.out.print(")");
                p+=1;
                continue;
            }
            System.out.print(items[p] + " ");
            p+=1;
        }
        System.out.println("");
    }

    public static void copy(Alist x,int xbegin,Alist y,int ybegin,int num){
        System.arraycopy(x.items,xbegin,y.items,ybegin,num);
        y.size = num;
    }
    public static void main(String[] args) {
        Alist x = new Alist(1);
        x.addAtInx(false,5);
        x.addAtInx(1,x.size);
        x.addAtInx(2.2,100);
//        x.addAtInx("fuck",0);
//        Alist y = new Alist(4);
//        copy(x,0,y,0,x.size);
        //x.addAtInx(y,0);

        //y.addAtInx(y,y.size);
        //x.remove(3);
        //x.removelast();
        x.print();
        for(int cnt = 29;cnt>0;cnt-=1){
            x.removelast();
        }
        x.print();
    }
}