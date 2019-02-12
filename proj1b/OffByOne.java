public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff * diff == 1;
    }
}
