public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        return (a - b == 1 || b - a == 1);
    }
}
