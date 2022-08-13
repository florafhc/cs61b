public class OffByN implements CharacterComparator {
    private int diff;
    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        return (a - b == diff || b - a == diff);
    }
}
