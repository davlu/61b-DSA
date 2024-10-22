import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {
    Queue<String> tas = new Queue<>();

    @Test
    public void testQuickSort() {
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas = QuickSort.quickSort(tas);
        assert isSorted(tas);
    }

    @Test
    public void testMergeSort() {
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas = MergeSort.mergeSort(tas);
        assert isSorted(tas);
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
