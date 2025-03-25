package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>,Iterable<K>{
    private int size = 0;
    private BSTNode bst;

    private class BSTNode{
        K key;
        V val;
        BSTNode left, right;
        BSTNode(K k,V v){
            key = k;
            val = v;
        }

        BSTNode get(K k){
            if (this.key.equals(k)) {
                return this;
            }
            if (k.compareTo(this.key) > 0) { // 正确的比较方式
                if(right != null) {return right.get(k);}
                return null;
            } else {
                if(left != null) {return left.get(k);}
                return null;
            }
        }

        boolean SearchKey(K k){
            if (this.key.equals(k)) {
                return true;
            }
            if (k.compareTo(this.key) > 0) { // 正确的比较方式
                return right != null && right.SearchKey(k);
            } else {
                return left != null && left.SearchKey(k);
            }
        }

        public boolean insert(K k, V v) {
            if (this.key.equals(k)) {
                this.val = v;
                return false;
            }
            if(k.compareTo(this.key) > 0){
                if(this.right == null){
                    this.right = new BSTNode(k, v);
                    return true;
                } else {
                    return this.right.insert(k, v);
                }
            } else {
                if(this.left == null){
                    this.left = new BSTNode(k, v);
                    return true;
                } else {
                    return this.left.insert(k, v);
                }
            }
        }

        public void print() {
            if(this.left != null){
                this.left.print();
            }
            System.out.print(this.val + " ");
            if(this.right != null){
                this.right.print();
            }
        }

        public BSTNode remove(K k, BSTNode b) {
            if (b == null) {
                return null;
            }

            if (k.compareTo(b.key) < 0) {
                b.left = remove(k, b.left);
            } else if (k.compareTo(b.key) > 0) {
                b.right = remove(k, b.right);
            } else {
                // 找到待删除节点
                if (b.left == null) {
                    return b.right;
                } else if (b.right == null) {
                    return b.left;
                } else {
                    // 找到右子树的最小值替换当前节点
                    BSTNode minNode = findMin(b.right);
                    b.key = minNode.key;
                    b.right = remove(minNode.key,b.right);
                }
            }
            return b;
        }

        private BSTNode findMin(BSTNode root) {
            BSTNode min = root;
            while(min.left != null){
                min = min.left;
            }
            return min;
        }
    }

    @Override
    public void clear() {
        bst = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if(bst == null){
            return false;
        }
        return bst.SearchKey(key);
    }

    @Override
    public V get(K key) {
        if (bst == null) {
            return null;
        }

        BSTNode lookup = bst.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {

        size += 1;
        if(bst == null){
            bst = new BSTNode(key, value);
            return;
        }
        if(!bst.insert(key, value))
        {
            size -= 1;
        };
    }

    @Override
    public Set<K> keySet() {
        Set set = new HashSet<K>();
        for(K key: this){
            set.add(key);
        }
        return set;
    }

    public void printInOrder(){
        if(bst == null){
            return;
        }
        bst.print();
    }

    private class BSTMapIter implements Iterator<K> {


        private Stack<BSTNode> stack;
        public BSTMapIter(BSTNode b) {
            stack = new Stack<>();
            pushAllLeft(b);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            BSTNode temp = stack.pop();
            K ret = temp.key;
            pushAllLeft(temp.right);
            return ret;
        }

        private void pushAllLeft(BSTNode node){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
        }
    }

    @Override
    public V remove(K key) {
        BSTNode ans = bst.get(key);
        bst = bst.remove(key,bst);
        if(ans != null){
            this.size -= 1;
            return ans.val;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        BSTNode ans = bst.get(key);
        if (ans.val.equals(value)){
            return this.remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter(bst);
    }
}
