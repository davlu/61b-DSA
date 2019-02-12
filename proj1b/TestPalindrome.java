import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne compare = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertFalse(palindrome.isPalindrome("rAcecAR"));
        assertTrue(palindrome.isPalindrome("rAcecAr"));
        assertTrue(palindrome.isPalindrome("RACECAR"));
    }

    @Test
    public void testIsOffByOnePalindrome() {
        assertTrue(palindrome.isPalindrome("flake", compare));
        assertFalse(palindrome.isPalindrome("racecar", compare));
        assertFalse(palindrome.isPalindrome("aba", compare));
        assertTrue(palindrome.isPalindrome("FLAKE", compare));
        assertFalse(palindrome.isPalindrome("POOP", compare));
        assertFalse(palindrome.isPalindrome("Racecar", compare));
    }
}
