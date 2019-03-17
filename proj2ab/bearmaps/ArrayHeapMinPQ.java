package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<T> items;
    private HashMap<T, Double> itemValueMap;
    private HashMap<T, Integer> itemIndexMap;
    private int nextOpen;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        itemValueMap = new HashMap<>();
        itemIndexMap = new HashMap<>();
        nextOpen = 1;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present. */
    /** if same value, first one is "smaller" than other**/
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("This item is already in the PQ.");
        }
        items.add(item);
        itemValueMap.put(item, priority);
        itemIndexMap.put(item, swim(nextOpen));
        nextOpen++;
        size++;
    }

    private void swap(int index1, int index2) {
        T temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
    }

    private int swim(int k) {
        int returnInt = k;
        if (parentIndex(k) == 0) {
            return returnInt;
        }
        T parentItem = items.get(parentIndex(k));
        T currentItem = items.get(k);
        if (itemValueMap.get(parentItem).compareTo(itemValueMap.get(currentItem)) > 0) {
            swap(parentIndex(k), k);
            returnInt = swim(parentIndex(k));
        }
        return returnInt;
    }

    private int sink(int k) {
        int smaller;
        int returnInt = k;
        if (leftChildIndex(k) > size()) {
            return returnInt;
        } else if (rightChildIndex(k) > size()) {
            smaller = leftChildIndex(k);
        } else {
            T rightItem = items.get(rightChildIndex(k));
            T leftItem = items.get(leftChildIndex(k));
            if (itemValueMap.get(rightItem).compareTo(itemValueMap.get(leftItem)) > 0) {
                smaller = leftChildIndex(k);
            } else {
                smaller = rightChildIndex(k);
            }
        }
        if (itemValueMap.get(items.get(smaller)).compareTo(itemValueMap.get(items.get(k))) < 0) {
            swap(smaller, k);
            returnInt = sink(smaller);
        }
        return returnInt;
    }

    private int leftChildIndex(int k) {
        return k * 2;
    }

    private int rightChildIndex(int k) {
        return k * 2 + 1;
    }

    private int parentIndex(int k) {
        return k / 2;
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return itemValueMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (this.size() == 0) {
            throw new NoSuchElementException("The PQ is empty");
        }
        return items.get(1);
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (this.size() == 0) {
            throw new NoSuchElementException("The PQ is empty");
        }
        T removed = items.get(1);
        T lastItem = items.get(nextOpen - 1);
        swap(1, nextOpen - 1);
        items.remove(nextOpen - 1);
        itemValueMap.remove(removed);
        size--;
        nextOpen--;
        itemIndexMap.put(lastItem, sink(1));
        return removed;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("Item does not exist in PQ.");
        }
        itemValueMap.remove(item);
        itemValueMap.put(item, priority);
        int currentItemIndex = itemIndexMap.get(item);
        int parentInd = parentIndex(currentItemIndex);
        if (this.size() == 0 || this.size() == 1) {
            return;
        } else if (parentInd != 0) {
            T parentItem = items.get(parentInd);
            T currentItem = items.get(currentItemIndex);
            if (itemValueMap.get(parentItem).compareTo(itemValueMap.get(currentItem)) > 0) {
                itemIndexMap.put(item, swim(itemIndexMap.get(item)));
                return;
            }
        }
        itemIndexMap.put(item, sink(itemIndexMap.get(item)));
    }

    public double returnItemPriority(T item){
        double val = this.itemValueMap.get(item);
        return val;
    }
}
