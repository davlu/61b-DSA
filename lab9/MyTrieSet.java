import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
public class MyTrieSet implements TrieSet61B {
    Node root;
    public MyTrieSet(){
        this.root = new Node();
    }

    private class Node{
        HashMap<Character, Node> links;
        char character;
        boolean isEnd;
        Node(){
            this.links = new HashMap<>();
        }
        Node(char s, boolean isEnd){
            this.character = s;
            this.isEnd = isEnd;
            this.links = new HashMap<>();
        }
    }
    /** Clears all items out of Trie */
    public void clear(){
        this.root = new Node();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key){
        Node curr = this.root;
        for(int i =0; i<key.length()-1;i++){
            char c = key.charAt(i);
            if(!curr.links.containsKey(c)){
                return false;
            }
            curr = curr.links.get(c);
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    public void add(String key){
        if(key == null || key.length() == 0){
            return;
        }
        Node curr = this.root;
        for(int i = 0; i < key.length()-1; i++){
            char c = key.charAt(i);
            if(!curr.links.containsKey(c)){
                curr.links.put(c, new Node(c, false));
            }
            curr = curr.links.get(c);
        }
        curr.isEnd = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix){
        List<String> allWords = new ArrayList<>();
        Node curr = this.root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(!curr.links.containsKey(c)){
                return allWords;
            }
            curr = curr.links.get(c);
        }
        for(String s: helperStringFinder(curr)){
            allWords.add(prefix+s);
        }
        return allWords;
    }

    private List<String> helperStringFinder(Node n){
        List<String> allWords = new ArrayList<>();
        if(n==null){
            return null;
        }
        String start = Character.toString(n.character);
        for(char c: n.links.keySet()){
            Node next = n.links.get(c);
            String newWord = start + next.character;
            if(n.isEnd){
                allWords.add(newWord);
            }
            List<String> potential = helperStringFinder(next);
            if(potential!= null){
                allWords = potential;
            }
        }
        return allWords;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
