package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    int size = 0;
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
                return right.get(k);
            } else {
                return left.get(k);
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

        public void changeVal(K k, V v) {
            if (this.key.equals(k)) {
                this.val = v;
            }
            if (k.compareTo(this.key) > 0) { // 正确的比较方式
                right.changeVal(k, v);
            } else {
                left.changeVal(k, v);
            }
        }

        public void insert(K k, V v) {
            if(k.compareTo(this.key) > 0){
                if(this.right == null){
                    this.right = new BSTNode(k, v);
                } else {
                    this.right.insert(k, v);
                }
            } else {
                if(this.left == null){
                    this.left = new BSTNode(k, v);
                } else {
                    this.left.insert(k, v);
                }
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
        if (bst == null || !containsKey(key)) {
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
        if(bst != null && containsKey(key)){
            bst.changeVal(key, value);
            return;
        }
        size += 1;
        if(bst == null){
            bst = new BSTNode(key, value);
            return;
        }
        bst.insert(key, value);
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
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
