public class test {
    public static void printTenth(int[] a) {
        try {
            System.out.println(a[10]);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("No tenth item available!");
            throw(e);
        }
    }
    public static void main(String[] args) {
        printTenth(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        printTenth(new int[]{0, 1, 2, 3});
        printTenth(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }
}
