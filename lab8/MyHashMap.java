import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int size;
    private ArrayList<Node>[] table;
    private HashSet<K> keys;
    public MyHashMap(){
        table = (ArrayList<Node>[]) new ArrayList[initialSize];
        keys = new HashSet<>();
    }
    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
    }
    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
    }
    public int hashIndex(K item, int size){
        return Math.floorMod(item.hashCode(), size);
    }
    public void clear(){           //
        this.table = (ArrayList<Node>[]) new ArrayList[initialSize];
        this.size = 0;
        this.keys = new HashSet<K>();
    }
    private class Node{            //
        K key;
        V val;
        Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){          //
        return keys.contains(key);
    }
    private Node getEntry(K key){
        int idx = hashIndex(key, this.initialSize);
        ArrayList<Node>bucket = this.table[idx];
        if(bucket!=null){
            for(Node n : bucket){
                if(n.key.equals(key)){
                    return n;
                }
            }
        }
        return null;
    }

    public V get(K key){
        if(!containsKey(key)){
            return null;
        }
        int idx = hashIndex(key, this.initialSize);
        ArrayList<Node> bucket = this.table[idx];
        if(bucket != null){
            for(Node e : bucket){
                if(e.key.equals(key)){
                    return e.val;
                }
            }
        }
        return null;
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
        if((double)this.size/this.initialSize > this.loadFactor){
            rehash(initialSize*2);
        }
        if(table[hashIndex(key, this.initialSize)] == null){
            table[hashIndex(key, this.initialSize)] = new ArrayList<Node>();
        }
        if(containsKey(key)){
            ArrayList<Node> bucket = table[hashIndex(key, this.initialSize)];
            for(Node e : bucket){
                if(e.key == key){
                    e.val = value;
                }
            }
        }
        else {
            int hashIndex = hashIndex(key, this.initialSize);
            Node addItem = new Node(key, value);
            table[hashIndex].add(addItem);
            this.keys.add(key);
            this.size++;
        }
    }

    private void rehash(int targetSize){
        ArrayList<Node>[] newMap = (ArrayList<Node>[]) new ArrayList[targetSize];
        for(K key : this.keys){
            int index = hashIndex(key, targetSize);
            ArrayList<Node> bucket = newMap[index];
            if(bucket== null){
                bucket = new ArrayList<Node>();
                newMap[index] = bucket;
            }
            Node newEntry = getEntry(key);
            bucket.add(newEntry);
            V entry = get(key);
            Node newEntryW = new Node(key, entry);
        }
        this.table = newMap;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return this.keys;
    }
    public Iterator<K> iterator() {
        return keys.iterator();
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
