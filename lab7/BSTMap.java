import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private Node root;
    private int size;
    private class Node{
        K key;
        V value;
        Node left;
        Node right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if(get(key) != null){
            return true;
        }
        return false;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return getHelper(key, root);
    }

    private V getHelper(K key, Node bst){
        if(bst == null){
            return null;
        }
        else if(bst.key.compareTo(key)==0){
            return bst.value;
        }
        else if(bst.key.compareTo(key)>0){
            return getHelper(key, bst.left);
        }
        else{
            return getHelper(key, bst.right);
        }
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root = helperInsert(root, key, value);
        size++;
    }

    private Node helperInsert(Node holder, K key, V value){
        if(holder == null){
            holder = new Node(key, value);
        }
        else if(holder.key.compareTo(key) > 0){
            holder.left = helperInsert(holder.left, key, value);
        }
        else{
            holder.right = helperInsert(holder.right, key, value);
        }
        return holder;
    }

    /**prints out your BSTMap in order of INCREASING Key*/
    public void printInOrder(){
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node holder){
        if(holder == null){
            return;
        }
        else {
            printInOrderHelper(holder.left);
            System.out.println(holder.value);
            printInOrderHelper(holder.right);
        }
    }
    public Set<K> keySet(){
        throw new UnsupportedOperationException(":(");
    }
    public V remove(K key){
        throw new UnsupportedOperationException(":(");
    }
    public V remove(K key, V value){
        throw new UnsupportedOperationException(":(");
    }
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException(":(");
    }
}
