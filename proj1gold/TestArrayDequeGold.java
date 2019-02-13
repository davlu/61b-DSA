import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRunner() {
        StudentArrayDeque<Integer> studentImplementation = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> correctImplementation = new ArrayDequeSolution<Integer>();
        Integer studentInt;
        Integer correctInt;
        boolean passed;
        String message = "";
        for (int i = 0; i < 1000; i++) {
            int decision = (int) (Math.random() * 4);
            int randInt = (int) (Math.random() * 1000);
            if (decision == 0) {
                studentImplementation.addFirst(randInt);
                correctImplementation.addFirst(randInt);
                message = message + "addFirst(" + randInt + ")\n";
            } else if (decision == 1) {
                studentImplementation.addLast(randInt);
                correctImplementation.addLast(randInt);
                message = message + "addLast(" + randInt + ")\n";
            } else if (studentImplementation.size() != 0 && correctImplementation.size() != 0) {
                if (decision == 2) {
                    studentInt = studentImplementation.removeFirst();
                    correctInt = correctImplementation.removeFirst();
                    message = message + "removeFirst()\n";
                } else {
                    studentInt = studentImplementation.removeLast();
                    correctInt = correctImplementation.removeLast();
                    message = message + "removeLast()\n";
                }
                assertEquals(message, correctInt, studentInt);
            }
        }
    }
}
