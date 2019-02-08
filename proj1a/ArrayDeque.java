public class ArrayDeque<T> {

    private int size = 8;
    private int arraySize = 0;
    private T[] items;
    private int firstPointer = 4;
    private int lastPointer = 5;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        this.items = (T[]) new Object[other.size];
        for (int i = 0; i < other.size; i++) {
            this.items[i] = (T) other.items[i];
        }
        this.firstPointer = other.firstPointer;
        this.lastPointer = other.lastPointer;
        this.arraySize = other.arraySize;
        this.size = other.size;
    }

    public void addFirst(T item) {
        if (this.arraySize == this.size) {
            resizeBig(this.arraySize * 2);
        }
        this.items[this.firstPointer] = item;
        if (this.firstPointer == 0) {
            this.firstPointer = this.size - 1;
        } else {
            this.firstPointer--;
        }
        this.arraySize++;
    }

    public void addLast(T item) {
        if (this.arraySize == this.size) {
            resizeBig(this.arraySize * 2);
            this.items[this.lastPointer] = item;
        } else {
            this.items[this.lastPointer] = item;
        }
        this.lastPointer = (this.lastPointer + 1) % this.size;
        this.arraySize++;
    }

    public boolean isEmpty() {
        return this.arraySize == 0;
    }

    public int size() {
        return this.arraySize;
    }

    public void printDeque() {
        String deque = "";
        int start = this.firstPointer + 1;
        for (int i = 0; i < this.size; i++) {
            deque = deque + " " + this.items[start];
            if (start == this.size - 1) {
                start = 0;
            } else {
                start += 1;
            }
        }
        System.out.println(deque);
    }

    public T removeFirst() {
        if (this.size > 16 && (double) this.arraySize / this.size < 0.25) {
            resizeSmall(this.arraySize * 4);
        }
        this.firstPointer = (this.firstPointer + 1) % this.size;
        T holder = this.items[this.firstPointer];
        this.items[this.firstPointer] = null;
        if (arraySize == 0) {
            return holder;
        }
        this.arraySize--;
        return holder;
    }

    public T removeLast() {
        if (this.size > 16 && (double) this.arraySize / this.size < 0.25) {
            resizeSmall(this.arraySize * 4);
        }
        this.lastPointer = Math.floorMod(this.lastPointer - 1, this.size);
        T holder = this.items[this.lastPointer];
        this.items[this.lastPointer] = null;
        if (this.arraySize == 0) {
            return holder;
        }
        this.arraySize--;
        return holder;
    }

    private void resizeBig(int newSize) {
        T[] resizedItems = (T[]) new Object[newSize];
        int start = (this.firstPointer + 1) % this.size;
        for (int i = 0; i < this.size; i++) {
            resizedItems[i] = this.items[start];
            if (start == this.size - 1) {
                start = 0;
            } else {
                start += 1;
            }
        }
        this.size = newSize;
        this.items = resizedItems;
        this.firstPointer = this.size - 1;
        this.lastPointer = this.arraySize;
    }

    private void resizeSmall(int newSize) {
        T[] resizedItems = (T[]) new Object[newSize];
        int start = (this.firstPointer + 1) % this.size;
        for (int i = 0; i < this.arraySize; i++) {
            resizedItems[i] = this.items[start];
            if (start == this.size - 1) {
                start = 0;
            } else {
                start += 1;
            }
        }
        this.size = newSize;
        this.items = resizedItems;
        this.firstPointer = this.size - 1;
        this.lastPointer = this.arraySize;
    }

    public T get(int index) {
        int temp = this.firstPointer;
        for (int i = 0; i <= index; i++) {
            temp = (temp + 1) % this.size;
        }
        return this.items[temp];
    }
}
