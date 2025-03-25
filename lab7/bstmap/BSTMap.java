package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
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
        return Set.of();
    }

    public void printInOrder(){
        if(bst == null){
            return;
        }
        bst.print();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
