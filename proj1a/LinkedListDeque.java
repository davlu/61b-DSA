
public class LinkedListDeque<T>{
    Node sentinel;
    int size;
    /**Node class with prev, next, and generic item attribute*/
    public class Node<T>{
        Node prev;
        Node next;
        T item;
        public Node(T i){
            this.item = i;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }


    public LinkedListDeque(LinkedListDeque other){
        this();
        for(int i = other.size-1; i >= 0; i--){
            this.addFirst((T)other.get(i));
        }


    }
    public void addFirst(T item){
        Node new_node = new Node(item);
        sentinel.next.prev = new_node;
        new_node.next = sentinel.next;
        new_node.prev = sentinel;
        sentinel.next = new_node;
        size += 1;
    }
    public T removeFirst(){
        T ret_item = (T)sentinel.next.item;
        Node temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        temp.next.prev = sentinel;
        if(size == 0){
            return ret_item;
        }
        this.size-=1;
        return ret_item;
    }

    public void addLast(T item){
        Node new_node = new Node(item);
        new_node.next = sentinel;
        new_node.prev = sentinel.prev;
        sentinel.prev.next = new_node;
        sentinel.prev = new_node;
        size += 1;
    }

    public T removeLast(){
        T ret_item = (T)sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if(size == 0){
            return ret_item;
        }
        this.size-=1;
        return ret_item;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size ==0;
    }

    public T get(int index){
        Node temp = sentinel;
        for(int i = -1; i < index; i++){
            temp = temp.next;
        }
        return (T)temp.item;
    }


    public T getRecursive(int index){
        Node temp = sentinel.next;
        return helper(index, temp);
    }

    public T helper(int i, Node n){
        if(i == 0){
            return (T) n.item;
        }
        else{
            n = n.next;
            return helper(--i, n);
        }
    }

    public void printDeque(){
        String deque = "";
        Node node = sentinel.next;
        while(node.item != null){
            deque = deque + " " + String.valueOf(node.item);
            node = node.next;
        }
        System.out.println(deque);
    }
}