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
        this.items = (val []) new Object[other.size];
        for(int i = 0; i<other.size; i++){
            items[i] = (val) other.items[i];
        }
        this.firstPointer = other.firstPointer;
        this.lastPointer = other.lastPointer;
        this.array_size = other.array_size;
        this.size = other.size;
    }
    public void addFirst(val item){
        if(array_size == size){
            resizeBig(array_size*2);
        }
        items[firstPointer] = item;
        if(firstPointer == 0){
            firstPointer = size-1;
        }
        else {
            firstPointer--;
        }
        array_size++;
    }
    public void addLast(val item){
        if(array_size == size){
            resizeBig(array_size*2);
        }
        items[lastPointer] = item;
        lastPointer = (lastPointer+1)%size;
        array_size++;
    }

    public boolean isEmpty(){
        return array_size == 0;
    }
    public int size(){
        return array_size;
    }
    public void printDeque(){
        String deque = "";
        int start = this.firstPointer+1;
        for(int i = 0; i < this.size; i++){
            deque = deque + " " + this.items[start];
            if (start == this.size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        System.out.println(deque);
    }
    public val removeFirst(){
        if(this.array_size > 16 && (double) this.array_size/this.size < 0.25){
            resizeSmall(array_size/2);
        }
        this.firstPointer = this.firstPointer+1%this.size;
        val holder = this.items[this.firstPointer];
        this.items[this.firstPointer] = null;
        this.array_size--;
        return holder;
    }
    public val removeLast(){
        if(this.array_size > 16 && (double) this.array_size/this.size < 0.25){
            resizeSmall(this.array_size/2);
        }
        this.lastPointer = Math.floorMod(this.lastPointer-1, this.size);
        val holder = this.items[this.lastPointer];
        this.items[this.lastPointer] = null;
        this.array_size--;
        return holder;
    }
    public void resizeSmall(int new_size){
        this.array_size = new_size;
        val[] resized_items = (val []) new Object[this.array_size];
        int start = this.firstPointer+1;
        for(int i = 0; i < this.size; i++){
            resized_items[i] = this.items[start];
            if (start == this.size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.items = resized_items;
        this.firstPointer = this.array_size-1;
        this.lastPointer = this.size;
    }

    public void resizeBig(int new_size){
        this.array_size = new_size;
        val[] resized_items = (val []) new Object[this.array_size];
        int start = this.firstPointer+1;
        for(int i = 0; i < this.size; i++){
            resized_items[i] = this.items[start];
            if(start == this.size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.items = resized_items;
        this.firstPointer = this.array_size-1;
        this.lastPointer = this.size;
    }
    public val get(int index){
        int temp = firstPointer;
        for(int i = 0; i <= index; i++){
            temp = (temp+1)%this.size;
        }
        return items[temp];
    }
}
