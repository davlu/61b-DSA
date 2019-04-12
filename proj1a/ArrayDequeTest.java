/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkReturn(int expected, int actual){
        if(expected != actual){
            System.out.println("return value" + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkEquivalence(ArrayDeque expected, ArrayDeque actual){
        if(!(expected.equals(actual))){
            System.out.println("expected and actual are not the same.");
            System.out.println("expected: ");
            expected.printDeque();
            System.out.println("actual: ");
            actual.printDeque();
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.

        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        // should be empty
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void comprehensiveTest(){
        System.out.println("Running big tests.");
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        ad1.addFirst(0);
        passed = checkEmpty(false, ad1.isEmpty()) && passed;
        ad1.removeFirst();
        passed = checkEmpty(true, ad1.isEmpty()) && passed;
        System.out.println("Adding and removing and empty tests pt. 1: ");
        printTestStatus(passed);

        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(0);
        ad2.removeLast();
        ad2.addFirst(2);
        ad2.removeLast();
        boolean passed2 = checkEmpty(true, ad2.isEmpty());
        System.out.println("Adding and removing and empty tests pt. 2: ");
        printTestStatus(passed2);

        ArrayDeque<Integer> ad0 = new ArrayDeque<Integer>();
        boolean passed9 = checkEmpty(true, ad0.isEmpty());
        ad0.addFirst(1);
        ad0.addFirst(2);
        ad0.removeLast();
        ad0.removeLast();
        passed9 = checkEmpty(true, ad0.isEmpty()) && passed9;
        System.out.println("adding remove test BIG BOI: ");
        printTestStatus(passed9);

        ArrayDeque<Integer> ad3 = new ArrayDeque<Integer>();
        ad3.addLast(0);
        ad3.removeLast();
        ad3.addLast(2);
        ad3.removeLast();
        ad3.isEmpty();
        boolean passed3 = checkEmpty(true, ad3.isEmpty());
        System.out.println("Adding and removing and empty tests pt. 3: ");
        printTestStatus(passed3);

        ArrayDeque<Integer> ad4 = new ArrayDeque<Integer>();
        ad4.addLast(0);
        ad4.removeFirst();
        ad4.isEmpty();
        boolean passed4 = checkEmpty(true, ad4.isEmpty());
        System.out.println("Adding and removing and empty tests pt. 4: ");
        printTestStatus(passed4);

        ArrayDeque<Integer> ad5 = new ArrayDeque<Integer>();
        ad5.isEmpty();
        ad5.addLast(1);
        ad5.addLast(2);
        boolean passed5 = checkSize(2,ad5.size());
        ad5.addLast(4);
        ad5.addFirst(5);
        passed5 = checkEmpty(false, ad5.isEmpty()) && passed5;
        ad5.removeLast()      ;
        ad5.addLast(8);
        ad5.addLast(9);
        ad5.addLast(10);
        passed5 = checkSize(6,ad5.size()) && passed5;
        System.out.println("size test #1: ");
        printTestStatus(passed5);

        ArrayDeque<Integer> ad6 = new ArrayDeque<Integer>();
        ad6.addLast(1);
        ad6.addLast(2);
        ad6.addLast(3);
        ad6.addLast(4);
        ad6.addLast(5);
        boolean passed6 = checkReturn(4 , ad6.get(3));
        System.out.println("iterative get test #1: ");
        printTestStatus(passed6);

        ArrayDeque<Integer> ad7 = new ArrayDeque<Integer>();
        ad7.addLast(1);
        ad7.addLast(2);
        ad7.addLast(3);
        ad7.addLast(4);
        ad7.addLast(5);
        ad7.addFirst(0);
        boolean passed7 = checkReturn(3 , ad7.get(3));
        System.out.println("iterative get test #2: ");
        printTestStatus(passed7);
        ad7.printDeque();

        ArrayDeque<Integer> ad8 = new ArrayDeque<Integer>(ad7);
        boolean passed8 = checkEquivalence(ad8, ad7);
        System.out.println("deep copy test for AD: ");
        printTestStatus(passed8);

        ArrayDeque<Integer> ad00 = new ArrayDeque<Integer>();
        ad00.addLast(0);
        ad00.addLast(1);
        ad00.removeFirst();
        ad00.isEmpty();
        ad00.addLast(4);
        ad00.addLast(5);
        ad00.addLast(6);
        ad00.addLast(7);
        boolean passed00 = checkReturn(1, ad00.removeFirst());
        System.out.println("add remove test BLORP: ");
        printTestStatus(passed00);
    }
    public static void comprehensiveTest1(){
        ArrayDeque<Integer> ad00 = new ArrayDeque<Integer>();
        ad00.addLast(0);
        ad00.addLast(1);
        ad00.removeFirst();
        ad00.isEmpty();
        ad00.addLast(4);
        ad00.addLast(5);
        ad00.addLast(6);
        ad00.addLast(7);
        boolean passed00 = checkReturn(1, ad00.removeFirst());
        System.out.println("add remove test BLORP: ");
        printTestStatus(passed00);

        ArrayDeque<Integer> ad01 = new ArrayDeque<Integer>();
        ad01.isEmpty();
        ad01.addFirst(1);
        ad01.addFirst(2);
        ad01.addFirst(3);
        ad01.addFirst(4);
        ad01.addFirst(5);
        ad01.addFirst(6);
        ad01.addFirst(7);
        ad01.isEmpty();
        ad01.addFirst(9);
        ad01.addFirst(10);
        boolean passed01 = checkReturn(1, ad01.removeLast());
        System.out.println("add remove test 01: ");
        printTestStatus(passed01);

        ArrayDeque<Integer> ad02 = new ArrayDeque<Integer>();
        ad02.addFirst(0);
        ad02.addFirst(1);
        ad02.addFirst(2);
        ad02.removeFirst();
        ad02.addFirst(4);
        ad02.addFirst(5);
        ad02.addFirst(6);
        ad02.removeFirst();
        boolean passed02 = checkReturn(0, ad02.removeLast());
        System.out.println("add remove test 02: ");
        printTestStatus(passed02);

        ArrayDeque<Integer> ad03 = new ArrayDeque<Integer>();
        ad03.isEmpty();
        ad03.addLast(1);
        ad03.addLast(2);
        ad03.addLast(3);
        ad03.addLast(4);
        ad03.addLast(5);
        ad03.addLast(6);
        ad03.addLast(7);
        ad03.addLast(8);
        ad03.addLast(9);
        boolean passed03 = checkReturn(9, ad03.removeLast());
        System.out.println("add remove test 03: ");
        printTestStatus(passed03);
    }

    public static void comprehensiveTest2(){
        ArrayDeque<Integer> ad01 = new ArrayDeque<Integer>();
        ad01.addLast(0);
        ad01.removeLast();
        ad01.addFirst(2);
        ad01.addFirst(3);
        boolean passed01 = checkReturn(2, ad01.get(1));
        ad01.addFirst(5);
        passed01 = checkReturn(2,ad01.removeLast()) && passed01;
        ad01.addFirst(7);
        ad01.addLast(8);
        ad01.addLast(9);
        passed01 = checkReturn(5,ad01.get(1)) && passed01;
        ad01.addFirst(11);
        ad01.addLast(12);
        ad01.addLast(13);
        passed01 = checkReturn(12, ad01.get(6)) && passed01;
        ad01.addFirst(15);
        passed01 = checkReturn(13,ad01.removeLast()) && passed01;
        System.out.println("add remove test 04: ");
        printTestStatus(passed01);


        ArrayDeque<Integer> ad02 = new ArrayDeque<Integer>(ad01);
        System.out.println("Testing ad01 deque and ad02 deque. Printing ad01: ");
        ad01.printDeque();
        System.out.println("Now printing ad02: ");
        ad02.printDeque();

    }

    public static void resizeSmallTest(){
        ArrayDeque<Integer> ad01 = new ArrayDeque<Integer>();
        //ad01.resizeBig(1000);
        ad01.addFirst(1);
        ad01.removeFirst();
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        /*
        addIsEmptySizeTest();
        addRemoveTest();
        comprehensiveTest();
        comprehensiveTest1();
        comprehensiveTest2();
        */
        resizeSmallTest();
    }
}