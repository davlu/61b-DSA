package bearmaps.proj2c.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TrieSet implements TrieSet61B {
    private Node root;

    public TrieSet() {
        this.root = new Node();
    }

    private class Node {
        HashMap<Character, Node> links;
        char character;
        boolean isEnd;

        Node() {
            this.links = new HashMap<>();
        }

        Node(char s, boolean isEnd) {
            this.character = s;
            this.isEnd = isEnd;
            this.links = new HashMap<>();
        }
    }

    /**
     * Clears all items out of Trie
     */
    public void clear() {
        this.root = new Node();
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     */
    public boolean contains(String key) {
        Node curr = this.root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.links.containsKey(c)) {
                return false;
            }
            curr = curr.links.get(c);
        }
        return curr.isEnd;
    }

    /**
     * Inserts string KEY into Trie
     */
    public void add(String key) {
        if (key == null || key.length() == 0 || contains(key)) {
            return;
        }
        Node curr = this.root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.links.containsKey(c)) {
                curr.links.put(c, new Node(c, false));
            }
            curr = curr.links.get(c);
        }
        curr.isEnd = true;
    }

    /**
     * Returns a list of all words that start with PREFIX
     */
    public List<String> keysWithPrefix(String prefix) {
        List<String> allWords = new ArrayList<>();
        Node curr = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = Character.toLowerCase(prefix.charAt(i));
            if (!curr.links.containsKey(c)) {
                return allWords;
            }
            curr = curr.links.get(c);
        }
        for (String s : helperStringFinder(prefix, curr)) {
            allWords.add(s);
        }
        return allWords;
    }

    private List<String> helperStringFinder(String prefix, Node n) {
        List<String> allWords = new ArrayList<>();
        if (n == null) {
            return null;
        }
        for (char c : n.links.keySet()) {
            Node next = n.links.get(c);
            String newWord = prefix + next.character;
            if (next.isEnd) {
                allWords.add(newWord);
            }
            List<String> potential = helperStringFinder(newWord, next);
            for (String s : potential) {
                allWords.add(s);
            }
        }
        return allWords;
    }
}
