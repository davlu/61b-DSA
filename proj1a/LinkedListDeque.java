public class LinkedListDeque<val>{
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
    }


    public LinkedListDeque(LinkedListDeque other){
        this();
        for(int i = other.size-1; i >= 0; i++){
            this.addFirst((val)other.get(i));
        }


    }
    public void addFirst(val item){
        Node new_node = new Node(item);
        sentinel.next.prev = new_node;
        new_node.next = sentinel.next;
        new_node.prev = sentinel;
        sentinel.next = new_node;
        size += 1;
    }
    public val removeFirst(){
        val ret_item = (val)sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        return ret_item;
    }

    public void addLast(val item){
        Node new_node = new Node(item);
        new_node.next = sentinel;
        new_node.prev = sentinel.prev;
        sentinel.prev.next = new_node;
        sentinel.prev = new_node;
        size += 1;
    }

    public val removeLast(){
        val ret_item = (val)sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        return ret_item;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size ==0;
    }

    public val get(int index){
        Node temp = sentinel;
        for(int i = 0; i < size; i++){
            temp = sentinel.next;
        }
        return (val)temp.item;
    }

    
    public val getRecursive(int index){
        Node temp = sentinel;
        return helper(index, temp);
    }

    public val helper(int i, Node n){
        if(i == 0){
            return (val) n.item;
        }
        else{
            n = n.next;
            return helper(i--, n);
        }
    }

    public void printDeque(){
        String deque = "";
        Node node = sentinel.next;
        while(node.item != null){
            deque = deque + " " + (String) node.item;
            node = node.next;
        }
        System.out.println(deque);
    }
}
