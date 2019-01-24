public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
	int cumulative = 0;
        while (x < 10) {
            System.out.print(cumulative + " ");
            x = x + 1;
	    cumulative = cumulative + x;
        }
    }
}