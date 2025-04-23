package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private int tableSize;
    private final double loadFactor;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        loadFactor = 0.75;
        size = 0;
        tableSize = 16;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        loadFactor = 0.75;
        size = 0;
        tableSize = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        loadFactor = maxLoad;
        size = 0;
        tableSize = initialSize;

    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    @SuppressWarnings("unchecked")
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table =  (Collection<Node>[]) new Collection[tableSize];
        for(int i = 0; i < tableSize; i++){
            table[i] =this.createBucket();
        }
        return table;
    }


    // Your code won't compile until you do so!
    @Override
    public void clear() {
        buckets = createTable(tableSize);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int number = key.hashCode() % tableSize;
        if(number < 0){
            number += tableSize;
        }
        Collection<Node> bucket = buckets[number];
        if(bucket != null){
            for(Node node:bucket){
                if (node.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int site = key.hashCode() % tableSize;
        if(site < 0){
            site += tableSize;
        }
        Collection<Node> bucket = buckets[site];
        if(bucket != null){
            for(Node node:bucket){
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        //第一种情况：已经包含的key，只修改value
        if (this.containsKey(key)){
            int site = key.hashCode() % tableSize;
            if(site < 0){//负数处理
                site += tableSize;
            }
            Collection<Node> head = buckets[site];
            for(Node curr: head){
                if(curr.key.equals(key)){
                    curr.value = value;
                }
            }
            return;
        }
        //加入新key-value对
        size += 1;
        if((double) size / tableSize > loadFactor){//需要扩容
            int newTableSize = tableSize * 2;
            Collection<Node>[] newBuckets = createTable(newTableSize);
            for(Collection<Node> bucket:buckets){
                for(Node item:bucket){
                    int site = item.key.hashCode() % newTableSize;
                    if(site < 0){
                        site += newTableSize;
                    }
                    newBuckets[site].add(createNode(item.key,item.value));
                }
            }
            tableSize = newTableSize;
            buckets = newBuckets;
        }
        int site = key.hashCode() % tableSize;
        if(site < 0){
            site += tableSize;
        }
        buckets[site].add(createNode(key,value));
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for(Collection<Node> bucket:buckets){
            for(Node curr:bucket){
                keys.add(curr.key);
            }
        }
        return keys;
    }
//TODO:可选练习
    @Override
    public V remove(K key) {
        if (this.containsKey(key)){
            int site = key.hashCode() % tableSize;
            if(site < 0){//负数处理
                site += tableSize;
            }
            Collection<Node> head = buckets[site];
            for(Node curr: head){
                if(curr.key.equals(key)){
                    V ans = curr.value;
                    head.remove(curr);
                    return ans;
                }
            }
        }
            return null;
    }

    @Override
    public V remove(K key, V value) {
        if (this.containsKey(key)){
            int site = key.hashCode() % tableSize;
            if(site < 0){//负数处理
                site += tableSize;
            }
            Collection<Node> head = buckets[site];
            for(Node curr: head){
                if(curr.key.equals(key) && curr.value.equals(value)){
                    V ans = curr.value;
                    head.remove(curr);
                    return ans;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        private int bucketIndex;
        private Iterator<Node> bucketIterator;

        MyHashMapIterator() {
            bucketIndex = 0;
            bucketIterator = getNextBucketIterator();
        }

        /** 定位下一个非空 bucket 并返回它的 iterator */
        private Iterator<Node> getNextBucketIterator() {
            while (bucketIndex < buckets.length) {
                if (!buckets[bucketIndex].isEmpty()) {
                    return buckets[bucketIndex++].iterator();
                }
                bucketIndex++;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator == null) {
                return false;
            }
            if (bucketIterator.hasNext()) {
                return true;
            }
            bucketIterator = getNextBucketIterator();
            return bucketIterator != null && bucketIterator.hasNext();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return bucketIterator.next().key;
        }
    }

}
