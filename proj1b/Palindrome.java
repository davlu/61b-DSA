public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> testDeque = new LinkedListDeque<Character>();
        for (char c : word.toCharArray()) {
            testDeque.addLast(c);
        }
        return testDeque;

    }

    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque palindromeDeque = palindrome.wordToDeque(word);
        Deque palindromeDequeBack = palindrome.wordToDeque(word);
        return helper(palindromeDeque, palindromeDequeBack);
    }

    private boolean helper(Deque p1, Deque p2) {
        if (p1.size() == 0 || p2.size() == 0) {
            return true;
        } else if (p1.removeFirst() == p2.removeLast()) {
            return helper(p1, p2);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int start = 0;
        int end = word.length() - 1;
        while ((start < end && word.length() % 2 == 0)
                || (!(start >= end) && word.length() % 2 != 0)) {
            if (cc.equalChars(word.charAt(start), word.charAt(end))) {
                start += 1;
                end -= 1;
            } else {
                return false;
            }
        }
        return true;
    }
}
