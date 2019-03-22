import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

/**
 * Created by Jenny Huang on 3/12/19.
 */
public class TestMyTrieSet {

    // assumes add/contains work
    @Test
    public void sanityClearTest() {
        MyTrieSet t = new MyTrieSet();
        for (int i = 0; i < 455; i++) {
            t.add("hi" + i);
            //make sure put is working via contains
            assertTrue(t.contains("hi" + i));
        }
        t.clear();
        for (int i = 0; i < 455; i++) {
            assertFalse(t.contains("hi" + i));
        }
    }

    // assumes add works
    @Test
    public void sanityContainsTest() {
        MyTrieSet t = new MyTrieSet();
        assertFalse(t.contains("waterYouDoingHere"));
        t.add("waterYouDoingHere");
        assertTrue(t.contains("waterYouDoingHere"));
    }

    // assumes add works
    @Test
    public void sanityPrefixTest() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s : saStrings) {
            t.add(s);
        }
        for (String s : otherStrings) {
            t.add(s);
        }

        List<String> keys = t.keysWithPrefix("sa");
        for (String s : saStrings) {
            assertTrue(keys.contains(s));
        }
        for (String s : otherStrings) {
            assertFalse(keys.contains(s));
        }
    }

    @Test
    public void basicTest() {
        MyTrieSet t = new MyTrieSet();
        t.add("sam");
        t.add("sam");
        t.add("same");
        t.add("apple");
        t.add("samson");
        t.add("samblahblhablah");
        t.add("apples");
        t.add("bear");

        List<String> key = t.keysWithPrefix("sa");
        assertEquals(key.size(), 4);
        assertTrue(key.contains("samson"));
        assertTrue(key.contains("sam"));
        assertTrue(key.contains("samblahblhablah"));
        assertTrue(key.contains("same"));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyTrieSet.class);
    }


}
