import java.util.ArrayDeque;
public class BST {
    private int key;
    private int site;
    private BST left;
    private BST right;
    private int balance = 0;
    public BST(int ik){
        key = ik;
        left = null;
        right = null;
        site = 0;
    }

    void deletewithchilds(BST t)
    {
        BST p = t.left;
        while( p.right != null && p.right.right != null)
        {
            p = p.right;
        }
        BST fp = p;
        p = p.right;
        if (p == null){
            t.key = fp.key;
            t.left = fp.left;}
        else{
            t.key = p.key;
            fp.right = p.left;
        }
    }

    void delete(int k)
    {
        BST t = this;
        BST ft = t;
        while(t.key != k){
            ft = t;
            if (t.key < k){
                t = t.right;
            } else {
                t = t.left;
            }
        }
        if(t.left == null){
            if(t == ft.right){
                ft.right = t.right;}
            else {ft.left = t.right;}
        }
        else if(t.right == null) {
            if(t == ft.right){
                ft.right = t.left;}
            else {ft.left = t.left;}
        }
        else {
            deletewithchilds(t);
        }
    }

    static BST insert(BST t, int ik,int s) {
        if (t == null) {
            BST term = new BST(ik);
            term.site = s;
            return term;
        }
        if(t.key > ik) {
            t.left = insert(t.left, ik,s + 1);
        } else {
            t.right = insert(t.right, ik,s + 1);
        }
        return t;
    }

    private void refresh(int s){
        this.site = s;
        if(this.left != null) this.left.refresh(s + 1);
        if(this.right != null) this.right.refresh(s + 1);
    }

    public BST leftRotate(){
        BST s = this;
        int site = s.site;
        BST t = s.right;
        s.right = t.left;
        t.left = s;
        t.refresh(site);
        return t;
    }

    public BST rightRotate(){
        BST s = this;
        int site = s.site;
        BST t = s.left;
        s.left = t.right;
        t.right = s;
        t.refresh(site);
        return t;
    }

    public void reverse() {
        if(left!=null){
            left.reverse();
        }
        System.out.print(key + " ");
        if(right != null){
            right.reverse();
        }
    }

    public void print(){
        System.out.println("");
        ArrayDeque<BST> store = new ArrayDeque<>();
        store.addLast(this);
        while(!store.isEmpty()){
            BST temp = store.removeFirst();
            if(temp.left!=null) store.addLast(temp.left);
            if(temp.right!=null) store.addLast(temp.right);
            System.out.print("  " + temp.key + "  ");
            if(!store.isEmpty() && temp.site != store.getFirst().site){
                System.out.println(' ');
            }
        }
        System.out.println(' ');
    }

    public static void main(String[] args) {
        BST t = new BST(1);
        t = insert(t, 2, 0); // 更新 t 为新树的根
        t = insert(t, 3, 0); // 更新 t 为新树的根
        t = insert(t, 4, 0); // 更新 t 为新树的根
        t = insert(t, 5, 0);
        t = insert(t, 6, 0);
        t = insert(t, 7, 0);
        t = insert(t, 8, 0);
        t = insert(t, 9, 0);
        t.reverse();
        t.print();
    }


}