public class LinkedListDeque<T> implements Deque<T> {

    private Node sentinel;
    private int size;

    /**
     * Node class with prev, next, and generic item attribute
     */
    public class Node<T> {
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


    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = other.size - 1; i >= 0; i--) {
            this.addFirst((T) other.get(i));
        }


    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);
        sentinel.next.prev = newNode;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public T removeFirst() {
        T retItem = (T) sentinel.next.item;
        Node temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        temp.next.prev = sentinel;
        if (size == 0) {
            return retItem;
        }
        this.size -= 1;
        return retItem;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public T removeLast() {
        T retItem = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size == 0) {
            return retItem;
        }
        this.size -= 1;
        return retItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        Node temp = sentinel;
        for (int i = -1; i < index; i++) {
            temp = temp.next;
        }
        return (T) temp.item;
    }

    @Override
    public void printDeque() {
        String deque = "";
        Node node = sentinel.next;
        while (node.item != null) {
            deque = deque + " " + String.valueOf(node.item);
            node = node.next;
        }
        System.out.println(deque);
    }

    public T getRecursive(int index) {
        Node temp = sentinel.next;
        return helper(index, temp);
    }

    private T helper(int i, Node n) {
        if (i == 0) {
            return (T) n.item;
        } else {
            n = n.next;
            return helper(--i, n);
        }
    }

}
