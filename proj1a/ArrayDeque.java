public class ArrayDeque<val> {
    private int size = 8;
    private int array_size = 0;
    val[] items;
    int firstPointer = 4;
    int lastPointer = 5;
    public ArrayDeque(){
        items = (val []) new Object[8];
    }

    public ArrayDeque(ArrayDeque other){

    }
    public void addFirst(val item){
        if(array_size == size){
            resizeBig();
        }
        items[firstPointer] = item;
        firstPointer = (firstPointer-1)%size;
        array_size++;
    }
    public void addLast(val item){
        if(array_size == size){
            resizeBig();
        }
        items[lastPointer] = item;
        lastPointer = (lastPointer+1)%size;
        array_size++;
    }
    public void resizeBig(){

    }
    public boolean isEmpty(){
        return array_size == 0;
    }
    public int size(){
        return array_size;
    }
    public void printDeque(){
        String deque = "";
        int start = firstPointer+1;
        for(int i = 0; i < size; i++){
            deque = deque + " " + this.items[start];
            if (start == size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        System.out.println(deque);
    }
    public val removeFirst(){
        if(array_size > 16 && array_size/size < 0.25){
            resizeSmall();
        }
        val holder = this.items[++firstPointer];
        this.items[firstPointer] = null;
        array_size--;
        return holder;
    }
    public val removeLast(){
        if(array_size > 16 && array_size/size < 0.25){
            resizeSmall();
        }
        val holder = this.items[--lastPointer];
        this.items[lastPointer] = null;
        array_size--;
        return holder;
    }
    public void resizeSmall(){

    }
    public val get(int index){
        return items[index];
    }
}
