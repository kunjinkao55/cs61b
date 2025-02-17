public class IntList {
    public int first;
    public IntList rest;
    public IntList(int f, IntList r)
    {
        first = f;
        rest = r;
    }
    public void addAdjacent(){
        IntList p = rest;
        IntList t = this;
        while(p!=null){
            if(t.first==p.first){
                t.first = t.first * 2;
                t.rest = p.rest;
                p.rest = null;
                p = t.rest;
            }
            else{
                p = p.rest;
                t = t.rest;
            }
        }
    }

    public void print(){
        IntList p = this;
        while(p!=null)
        {
            System.out.print(p.first + " ");
            p = p.rest;
        }
    }
    public static void main(String[] args) {
        IntList L = new IntList(2,new IntList(2,new IntList (4, new IntList(5,new IntList(2,new IntList (2,new IntList(2,null)))))));
        L.print();
        System.out.println(" ");
        L.addAdjacent();
        L.print();
    }
}