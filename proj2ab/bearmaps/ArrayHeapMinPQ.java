package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present. */
    private ArrayList<Node> items;
    private int rootIndex;
    public ArrayHeapMinPQ(){
        items = new ArrayList<Node>();
        items.add(null);
        rootIndex = 1;
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
    public void add(T item, double priority){
        if(items.size() == 1){
            items.add(new Node(item, priority));
        }
        else{
            helperAdd(item, priority, this.rootIndex);
        }
    }
    private void helperAdd(T item, double priority, int rootIndex){
        if(items.get(rootIndex).priority)
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

    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){

    }
}
