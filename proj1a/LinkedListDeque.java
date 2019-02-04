public class LinkedListDeque<val>{
    Node tail;
    Node head;
    Node sentinel;
    int size;
    /**Node class with prev, next, and generic item attribute*/
    public class Node<val>{
        Node prev;
        Node next;
        val item;
        public Node(val i){
            this.item = i;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        tail = sentinel;
        head = sentinel;
    }
    public void addFirst(val item){
        Node temp = sentinel.next;
        Node new_node = new Node(item);
        sentinel.next = new_node;
        new_node.prev = sentinel;
        new_node.next = temp;
        temp.prev = new_node;
        size += 1;
    }
    public void addLast(val item){
        Node temp = sentinel.prev;
        Node new_node = new Node(item);
        new_node.next = temp;
        new_node.prev = temp.prev;
        temp.prev = new_node;
        temp.prev.next = new_node;
        size += 1;
    }

    public int size(){
        return size;
    }
}
