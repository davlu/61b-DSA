public class ArrayDeque<val> {
    private int size = 8;
    private int array_size = 0;
    val[] items;
    int firstPointer = 4;
    int lastPointer = 5;
    public ArrayDeque(){
        this.items = (val []) new Object[8];
    }

    public ArrayDeque(ArrayDeque other){
        this.items = (val []) new Object[other.size];
        for(int i = 0; i<other.size; i++){
            this.items[i] = (val) other.items[i];
        }
        this.firstPointer = other.firstPointer;
        this.lastPointer = other.lastPointer;
        this.array_size = other.array_size;
        this.size = other.size;
    }
    public void addFirst(val item){
        if(this.array_size == this.size){
            resizeBig(this.array_size*2);
        }
        this.items[this.firstPointer] = item;
        if(this.firstPointer == 0){
            this.firstPointer = this.size-1;
        }
        else {
            this.firstPointer--;
        }
        this.array_size++;
    }
    public void addLast(val item){
        if(this.array_size == this.size){
            resizeBig(this.array_size*2);
            this.items[this.lastPointer] = item;
        }
        else {
            this.items[this.lastPointer] = item;
        }
        this.lastPointer = (this.lastPointer+1)%this.size;
        this.array_size++;
    }

    public boolean isEmpty(){
        return this.array_size == 0;
    }
    public int size(){
        return this.array_size;
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
        if(this.size > 16 && (double) this.array_size/this.size < 0.25){
            resizeSmall(this.array_size*4);
        }
        this.firstPointer = (this.firstPointer+1)%this.size;
        val holder = this.items[this.firstPointer];
        this.items[this.firstPointer] = null;
        if(array_size == 0){
            return holder;
        }
        this.array_size--;
        return holder;
    }
    public val removeLast(){
        if(this.size > 16 && (double) this.array_size/this.size < 0.25){
            resizeSmall(this.array_size*4);
        }
        this.lastPointer = Math.floorMod(this.lastPointer-1, this.size);
        val holder = this.items[this.lastPointer];
        this.items[this.lastPointer] = null;
        if(this.array_size == 0){
            return holder;
        }
        this.array_size--;
        return holder;
    }

    public void resizeBig(int new_size){
        val[] resized_items = (val []) new Object[new_size];
        int start = (this.firstPointer+1)%this.size;
        for(int i = 0; i < this.size; i++){
            resized_items[i] = this.items[start];
            if(start == this.size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.size = new_size;
        this.items = resized_items;
        this.firstPointer = this.size-1;
        this.lastPointer = this.array_size;
    }

    public void resizeSmall(int new_size){
        val[] resized_items = (val []) new Object[new_size];
        int start = (this.firstPointer+1)%this.size;
        for(int i = 0; i < this.array_size; i++){
            resized_items[i] = this.items[start];
            if(start == this.size-1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.size = new_size;
        this.items = resized_items;
        this.firstPointer = this.size-1;
        this.lastPointer = this.array_size;
    }

    public val get(int index){
        int temp = this.firstPointer;
        for(int i = 0; i <= index; i++){
            temp = (temp+1)%this.size;
        }
        return this.items[temp];
    }
}