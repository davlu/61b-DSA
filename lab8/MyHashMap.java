import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {
    private int initialSize;
    private double loadFactor;
    private int size;
    private Node[] table;
    private Set<K> keySet;
    public MyHashMap(){
        this.initialSize = 16;
        this.loadFactor = 0.75;
        table = (Node[]) new Object[initialSize];
        keySet = new HashSet<>();
    }
    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
    }
    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
    }
    public int hashIndex(int hashCode){
        return hashCode%initialSize;
    }
    public void clear(){
        table = (Node[]) new Object[initialSize];
    }
    private class Node{

        /**
         * Stores KEY as the key in this key-value pair, VAL as the value, and
         * NEXT as the next node in the linked list.
         */
        Node(K k, V v, Node n) {
            key = k;
            val = v;
            next = n;
        }
        /**
         * Returns the Entry in this linked list of key-value pairs whose key
         * is equal to KEY, or null if no such Entry exists.
         */
        Node get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(k);
        }
        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        Node next;
    }
    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return keySet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        if(!containsKey(key)){
            return null;
        }
        return table[key.hashCode()%size()].get(key).val;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        int hashIndex = hashIndex(key.hashCode());
        Node addItem = new Node(key, value, null);
        if(table[hashIndex] == null){
            table[key.hashCode()] = addItem;
        }
        table[Math.floorMod(key.hashCode(),size())].next = addItem;
        this.keySet.add(key);
        size++;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return this.keySet;
    }
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}
