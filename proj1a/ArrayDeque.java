public class ArrayDeque<val> {
    private int size = 8;
    val[] items;
    int firstPointer = 4;
    int lastPointer = 5;
    public ArrayDeque(){
        items = (val []) new Object[8];
    }

    public ArrayDeque(ArrayDeque other){

    }
    public void addFirst(val item){
        items[firstPointer] = item;
        firstPointer = (firstPointer-1)%size;
    }
    public void addLast(val item){
        items[lastPointer] = item;
        lastPointer = (lastPointer+1)%size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        String deque = "";
        for(int i = firstPointer+1; i < lastPointer; i++){
            if(i>this.size-1){
                i = i%this.size;
            }
            deque = deque + " " + this.items[i];
        }
        System.out.println(deque);
    }
    public val removeFirst(){
        val holder = this.items[firstPointer++];
        this.items[firstPointer++] = null;
        return holder;
    }
    public val removeLast(){
        val holder = this.items[lastPointer--];
        this.items[lastPointer--] = null;
        return holder;
    }
    public val get(int index){
        return items[index];
    }
}
