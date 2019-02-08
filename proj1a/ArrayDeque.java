public class ArrayDeque<T> {
    private int size = 8;
    private int array_Size = 0;
    private T [] items;
    private int firstPointer = 4;
    private int lastPointer = 5;
    public ArrayDeque() {
        this.items = (T []) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        this.items = (T []) new Object[other.size];
        for (int i = 0; i < other.size; i++) {
            this.items[i] = (T) other.items[i];
        }
        this.firstPointer = other.firstPointer;
        this.lastPointer = other.lastPointer;
        this.array_Size = other.array_Size;
        this.size = other.size;
    }
    private void addFirst(T item) {
        if(this.array_Size == this.size) {
            resizeBig(this.array_Size*2);
        }
        this.items[this.firstPointer] = item;
        if(this.firstPointer == 0) {
            this.firstPointer = this.size-1;
        }
        else {
            this.firstPointer--;
        }
        this.array_Size++;
    }
    private void addLast(T item) {
        if (this.array_Size == this.size) {
            resizeBig(this.array_Size*2);
            this.items[this.lastPointer] = item;
        }
        else {
            this.items[this.lastPointer] = item;
        }
        this.lastPointer = (this.lastPointer+1) % this.size;
        this.array_Size++;
    }

    private boolean isEmpty() {
        return this.array_Size == 0;
    }
    private int size() {
        return this.array_Size;
    }
    private void printDeque() {
        String deque = "";
        int start = this.firstPointer + 1;
        for (int i = 0; i < this.size; i++) {
            deque = deque + " " + this.items[start];
            if (start == this.size - 1){
                start = 0;
            }
            else {
                start += 1;
            }
        }
        System.out.println(deque);
    }
    private T removeFirst() {
        if (this.size > 16 && (double) this.array_Size / this.size < 0.25) {
            resizeSmall(this.array_Size * 4);
        }
        this.firstPointer = (this.firstPointer + 1) % this.size;
        T holder = this.items[this.firstPointer];
        this.items[this.firstPointer] = null;
        if (array_Size == 0) {
            return holder;
        }
        this.array_Size--;
        return holder;
    }
    private T removeLast() {
        if(this.size > 16 && (double) this.array_Size / this.size < 0.25) {
            resizeSmall(this.array_Size * 4);
        }
        this.lastPointer = Math.floorMod(this.lastPointer - 1, this.size);
        T holder = this.items[this.lastPointer];
        this.items[this.lastPointer] = null;
        if(this.array_Size == 0) {
            return holder;
        }
        this.array_Size--;
        return holder;
    }

    private void resizeBig(int new_Size) {
        T[] resized_Items = (T []) new Object[new_Size];
        int start = (this.firstPointer + 1) % this.size;
        for (int i = 0; i < this.size; i++){
            resized_Items[i] = this.items[start];
            if(start == this.size - 1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.size = new_Size;
        this.items = resized_Items;
        this.firstPointer = this.size - 1;
        this.lastPointer = this.array_Size;
    }

    private void resizeSmall(int new_Size) {
        T[] resized_Items = (T []) new Object[new_Size];
        int start = (this.firstPointer + 1) % this.size;
        for (int i = 0; i < this.array_Size; i++) {
            resized_Items[i] = this.items[start];
            if (start == this.size - 1){
                start = 0;
            }
            else{
                start += 1;
            }
        }
        this.size = new_Size;
        this.items = resized_Items;
        this.firstPointer = this.size - 1;
        this.lastPointer = this.array_Size;
    }

    private T get(int index) {
        int temp = this.firstPointer;
        for (int i = 0; i <= index; i++) {
            temp = (temp + 1) % this.size;
        }
        return this.items[temp];
    }
}