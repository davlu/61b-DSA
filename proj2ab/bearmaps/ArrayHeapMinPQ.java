package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<T> items;
    private HashMap<T, Node> itemValueMap;
    private int nextOpen;
    private int size;
    public ArrayHeapMinPQ(){
        items = new ArrayList<>();
        itemValueMap = new HashMap<>();
        items.add(null);
        nextOpen = 1;
    }
    private class Node implements Comparable<Node>{
        private T item;
        private double priority;
        public Node(T i, double p){
            this.item = i;
            this.priority = p;
        }
        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.priority, o.priority);
        }
    }
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present. */
    public void add(T item, double priority){ /** if same value, first one is "smaller" than other**/
        if(contains(item)){
            throw new IllegalArgumentException("This item is already in the PQ.");
        }
        Node addedItem = new Node(item, priority);
        items.add(item);
        itemValueMap.put(item, addedItem);
        swim(nextOpen);
        nextOpen++;
        size++;
    }
    public void swap(int index1, int index2){
        T temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
    }
    public void swim(int k){
        T parentItem = items.get(parentIndex(k));
        T currentItem = items.get(k);
        if(itemValueMap.get(parentItem).compareTo(itemValueMap.get(currentItem)) > 0){
            swap(parentIndex(k), k);
            swim(parentIndex(k));
        }
    }
    public void sink(int k){
        int smaller;
        T rightItem = items.get(rightChildIndex(k));
        T leftItem = items.get(leftChildIndex(k));
        if(itemValueMap.get(rightItem).compareTo(itemValueMap.get(leftItem)) > 0){
            smaller = leftChildIndex(k);
        }
        else{
            smaller = rightChildIndex(k);
        }
        swap(smaller, k);
        sink(smaller);
    }
    public int leftChildIndex(int k){
        return k*2;
    }
    public int rightChildIndex(int k){
        return k*2 + 1;
    }
    public int parentIndex(int k){
        return k/2;
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item){
        return itemValueMap.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        if(this.size() == 0){
            throw new NoSuchElementException("The PQ is empty");
        }
        return items.get(1);
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest(){
        T removed = items.get(1);
        swap(1, nextOpen-1);
        items.remove(nextOpen-1);
        sink(1);
        return removed;
    }
    /* Returns the number of items in the PQ. */
    public int size(){
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){
        if(!contains(item)){
            throw new NoSuchElementException("Item does not exist in PQ.");
        }
        itemValueMap.remove(item);
        Node newPriorityItem = new Node(item, priority);
        itemValueMap.put(item, newPriorityItem);
    }
}
