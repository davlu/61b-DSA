public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> testDeque = new LinkedListDeque<Character>();
        for(char c: word.toCharArray()){
            testDeque.addLast(c);
        }
        return testDeque;

    }
    public boolean isPalindrome(String word){
        Palindrome palindrome = new Palindrome();
        Deque palindromeDeque = palindrome.wordToDeque(word);
        Deque palindromeDequeBack = palindrome.wordToDeque(word);
        return helper(palindromeDeque, palindromeDequeBack);
    }
    private boolean helper(Deque p1, Deque p2){
        if (p1.size() == 0 || p2.size()==0){
            return true;
        }
        else if(p1.removeFirst() == p2.removeLast()){
            return helper(p1, p2);
        }
        return false;
    }
}
