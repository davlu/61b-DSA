package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present. */
    private ArrayList<Node> items;
    private int rootIndex;
    private int nextOpen;
    private int size;
    public ArrayHeapMinPQ(){
        items = new ArrayList<Node>();
        items.add(null);
        rootIndex = 1;
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
    public void add(T item, double priority){ /** if same value, first one is "smaller" than other**/
        items.add(new Node(item, priority));
        swim(nextOpen);
        nextOpen += 1;
    }
    public void swap(int index1, int index2){
        Node temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
    }
    public void swim(int k){
        if(items.get(parentIndex(k)).compareTo(items.get(k)) > 0){
            swap(parentIndex(k), k);
            swim(parentIndex(k));
        }
    }
    public void sink(int k){
        int smaller;
        if(items.get(rightChildIndex(k)).compareTo(items.get(leftChildIndex(k))) > 0){
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

    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){

    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest(){

    }
    /* Returns the number of items in the PQ. */
    public int size(){
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){

    }
}
