import java.util.ArrayDeque;
public class blBST {
    private int key;
    private blBST left;
    private blBST right;
    private int balance = 0;
    private boolean leaf;
    public blBST(int ik){
        key = ik;
        left = null;
        right = null;
        leaf = true;
    }

    void deletewithchilds(blBST t)
    {
        blBST p = t.left;
        while( p.right != null && p.right.right != null)
        {
            p = p.right;
        }
        blBST fp = p;
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
        blBST t = this;
        blBST ft = t;
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

    public static int height(blBST t){
        if(t == null){
            return 0;
        }
        if(t.leaf){
            return 1;
        }
        if(t.left == null){
            return 1 + height(t.right);
        }
        if(t.right == null){
            return 1 + height(t.left);
        }
        return max(height(t.left),height(t.right));
    }

    private static int max(int a, int b) {
        if(a > b){return a;}
        return b;
    }

    static blBST insert(blBST t, int ik,int s) {
        if (t == null) {
            blBST term = new blBST(ik);
            return term;
        }
        if(t.key > ik) {
            t.left = insert(t.left, ik,s + 1);
        } else {
            t.right = insert(t.right, ik,s + 1);
        }
        t.leaf = false;
        t.balance = height(t.left) - height(t.right);
        t = t.Balance(t);
        return t;
    }

    private blBST Balance(blBST t){
        if(t == null){return null;}
        if(t.balance >= 2){
            t = t.rightRotate();
            if(t != null) t.balance = height(t.left) - height(t.right);
        }
        else if(t.balance <= -2){
            t = t.leftRotate();
            if(t != null) t.balance = height(t.left) - height(t.right);
        }
        if(t.left != null){t.left.balance = height(t.left.left) - height(t.left.right);}
        if(t.right != null){t.right.balance = height(t.right.left) - height(t.right.right);}
        if(t.left != null){t.left = t.left.Balance(t.left);}
        if(t.right != null){t.right = t.right.Balance(t.right);}
        return t;
    }

    public blBST leftRotate(){
        blBST s = this;
        blBST t = s.right;
        if(t != null) {
            s.right = t.left;
            t.left = s;
        }
        return t;
    }

    public blBST rightRotate(){
        blBST s = this;
        blBST t = s.left;
        if(t != null) {
            s.left = t.right;
            t.right = s;
        }
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
        ArrayDeque<blBST> store = new ArrayDeque<>();
        store.addLast(this);
        while(!store.isEmpty()){
            blBST temp = store.removeFirst();
            if(temp.left!=null) store.addLast(temp.left);
            if(temp.right!=null) store.addLast(temp.right);
            System.out.print("  " + temp.key + "  ");
            }

        System.out.println(' ');
    }

    public static void main(String[] args) {
        blBST t = new blBST(1);
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
        t.delete(3);
        t.delete(8);
        t = insert(t, 11, 0);
        t.reverse();
        t.print();
        /*TODO:删除节点还未优化平衡树模式，不过貌似不需要专门做这个逻辑*/
    }


}