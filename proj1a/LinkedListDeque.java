public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;
    /**Node class with prev, next, and generic item attribute*/
    private class Node<T> {
        Node prev;
        Node next;
        T item;
        public Node(T i) {
            this.item = i;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }


    private LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = other.size - 1; i >= 0; i--) {
            this.addFirst((T)other.get(i));
        }


    }
    public void addFirst(T item) {
        Node new_Node = new Node(item);
        sentinel.next.prev = new_Node;
        new_Node.next = sentinel.next;
        new_Node.prev = sentinel;
        sentinel.next = new_Node;
        size += 1;
    }
    public T removeFirst() {
        T ret_Item = (T) sentinel.next.item;
        Node temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        temp.next.prev = sentinel;
        if (size == 0) {
            return ret_Item;
        }
        this.size -= 1;
        return ret_Item;
    }

    public void addLast(T item) {
        Node new_Node = new Node(item);
        new_Node.next = sentinel;
        new_Node.prev = sentinel.prev;
        sentinel.prev.next = new_Node;
        sentinel.prev = new_Node;
        size += 1;
    }

    private T removeLast() {
        T ret_Item = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size == 0) {
            return ret_Item;
        }
        this.size -= 1;
        return ret_Item;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size ==0;
    }

    private T get(int index) {
        Node temp = sentinel;
        for (int i = -1; i < index; i++) {
            temp = temp.next;
        }
        return (T) temp.item;
    }


    private T getRecursive(int index) {
        Node temp = sentinel.next;
        return helper(index, temp);
    }

    private T helper(int i, Node n) {
        if (i == 0) {
            return (T) n.item;
        }
        else {
            n = n.next;
            return helper(--i, n);
        }
    }

    public void printDeque(){
        String deque = "";
        Node node = sentinel.next;
        while (node.item != null) {
            deque = deque + " " + String.valueOf(node.item);
            node = node.next;
        }
        System.out.println(deque);
    }
}