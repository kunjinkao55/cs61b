import java.security.Key;

public class BRT {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        int val;
        Node left,right;
        int N;
        boolean color;

        Node(Key key, int val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }

        private boolean isRed(Node x){
            if(x == null) return false;
            return x.color == RED;
        }

        private boolean isLeaf(){
            return this.left == null && this.right == null;
        }
    }
    Node root;
    public BRT(){
        this.root = null;
    }
    private static int size(Node x){
//        if(x.isLeaf()){
//            return 1;
//        }
//        int size = 1;
//        if(x.left != null) size += size(x.left);
//        if(x.right != null) size += size(x.right);
//        return size;
        if(x == null) return 0;
        return x.N;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, int val){
        root = put(root, key, val);
    }

    public Node put(Node h, Key key, int val){
        if(h == null){
            return new Node(key,val,1,RED);
        }

        if(key < h.key)
    }

}
