public class LinkedListDeque<val> {
    
    /**Node class with prev, next, and generic item attribute*/
    public class Node{
        Node prev;
        Node next;
        val item;
        public Node(Node p, Node n, val i){
            this.prev = p;
            this.next = n;
            this.item = i;
        }
    }

    public LinkedListDeque(val v){

    }
}
