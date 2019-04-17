package bearmaps.proj2c.utils;

import java.util.List;

public interface TrieSet61B {

    /** Clears all items out of Trie */
    void clear();

    /** Returns true if the Trie contains KEY, false otherwise */
    boolean contains(String key);

    /** Inserts string KEY into Trie */
    void add(String key);

    /** Returns a list of all words that start with PREFIX */
    List<String> keysWithPrefix(String prefix);

}
