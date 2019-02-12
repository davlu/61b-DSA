public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> testDeque = new LinkedListDeque<Character>();
        for(char c: word.toCharArray()){
            testDeque.addLast(c);
        }
        return testDeque;

    }
    public boolean isPalindrome(String word){
        return false;
    }
}
