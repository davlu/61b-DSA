/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

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

	public static boolean checkEquivalence(LinkedListDeque expected, LinkedListDeque actual){
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

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.

		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	public static void comprehensiveTest(){
		System.out.println("Running big tests.");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		boolean passed = checkEmpty(true, lld1.isEmpty());
		lld1.addFirst(0);
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.removeFirst();
		passed = checkEmpty(true, lld1.isEmpty()) && passed;
		System.out.println("Adding and removing and empty tests pt. 1: ");
		printTestStatus(passed);

		LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>();
		lld2.addFirst(0);
		lld2.removeLast();
		lld2.addFirst(2);
		lld2.removeLast();
		boolean passed2 = checkEmpty(true, lld2.isEmpty());
		System.out.println("Adding and removing and empty tests pt. 2: ");
		printTestStatus(passed2);

		LinkedListDeque<Integer> lld0 = new LinkedListDeque<Integer>();
		boolean passed9 = checkEmpty(true, lld0.isEmpty());
		lld0.addFirst(1);
		lld0.addFirst(2);
		lld0.removeLast();
		lld0.removeLast();
		passed9 = checkEmpty(true, lld0.isEmpty()) && passed9;
		System.out.println("adding remove test BIG BOI: ");
		printTestStatus(passed9);

		LinkedListDeque<Integer> lld3 = new LinkedListDeque<Integer>();
		lld3.addLast(0);
		lld3.removeLast();
		lld3.addLast(2);
		lld3.removeLast();
		lld3.isEmpty();
		boolean passed3 = checkEmpty(true, lld3.isEmpty());
		System.out.println("Adding and removing and empty tests pt. 3: ");
		printTestStatus(passed3);

		LinkedListDeque<Integer> lld4 = new LinkedListDeque<Integer>();
		lld4.addLast(0);
		lld4.removeFirst();
		lld4.isEmpty();
		boolean passed4 = checkEmpty(true, lld4.isEmpty());
		System.out.println("Adding and removing and empty tests pt. 4: ");
		printTestStatus(passed4);

		LinkedListDeque<Integer> lld5 = new LinkedListDeque<Integer>();
		lld5.isEmpty();
		lld5.addLast(1);
		lld5.addLast(2);
		lld5.size();
		lld5.addLast(4);
		lld5.addFirst(5);
		lld5.isEmpty();
		lld5.removeLast()      ;
		lld5.addLast(8);
		lld5.addLast(9);
		lld5.addLast(10);
		boolean passed5 = checkSize(6,lld5.size());
		System.out.println("size test #1: ");
		printTestStatus(passed5);

		LinkedListDeque<Integer> lld6 = new LinkedListDeque<Integer>();
		lld6.addLast(1);
		lld6.addLast(2);
		lld6.addLast(3);
		lld6.addLast(4);
		lld6.addLast(5);
		boolean passed6 = checkReturn(4 , lld6.get(3));
		System.out.println("iterative get test #1: ");
		printTestStatus(passed6);

		LinkedListDeque<Integer> lld7 = new LinkedListDeque<Integer>();
		lld7.addLast(1);
		lld7.addLast(2);
		lld7.addLast(3);
		lld7.addLast(4);
		lld7.addLast(5);
		lld7.addFirst(0);
		boolean passed7 = checkReturn(3 , lld7.getRecursive(3));
		System.out.println("recursive get test #1: ");
		printTestStatus(passed7);
		lld7.printDeque();

		LinkedListDeque<Integer> lld8 = new LinkedListDeque<Integer>(lld7);
		boolean passed8 = checkEquivalence(lld8, lld7);
		System.out.println("deep copy test for LLD: ");
		printTestStatus(passed8);

		LinkedListDeque<Integer> lld00 = new LinkedListDeque<Integer>();
		lld00.addLast(0);
		lld00.addLast(1);
		lld00.removeFirst();
		lld00.isEmpty();
		lld00.addLast(4);
		lld00.addLast(5);
		lld00.addLast(6);
		lld00.addLast(7);
		boolean passed00 = checkReturn(1, lld00.removeFirst());
		System.out.println("add remove test BLORP: ");
		printTestStatus(passed00);
	}
	public static void comprehensiveTest1(){
		LinkedListDeque<Integer> lld00 = new LinkedListDeque<Integer>();
		lld00.addLast(0);
		lld00.addLast(1);
		lld00.removeFirst();
		lld00.isEmpty();
		lld00.addLast(4);
		lld00.addLast(5);
		lld00.addLast(6);
		lld00.addLast(7);
		boolean passed00 = checkReturn(1, lld00.removeFirst());
		System.out.println("add remove test BLORP: ");
		printTestStatus(passed00);
	}
	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		//addIsEmptySizeTest();
		//addRemoveTest();
		//comprehensiveTest();
		comprehensiveTest1();
	}
} 