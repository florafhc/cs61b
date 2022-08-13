public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        Deque<Character> list = new LinkedListDeque<>();
        for (int i = 0; i < length; i++) {
            list.addLast(word.charAt(i));
        }
        return list;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> list = wordToDeque(word);
        if (list.size() == 0 || list.size() == 1) {
            return true;
        } else {
            int half = list.size() / 2;
            for (int i = 0; i < half; i++) {
                if (list.removeFirst() != list.removeLast()) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> list = wordToDeque(word);
        if (list.size() == 0 || list.size() == 1) {
            return true;
        } else {
            int half = list.size() / 2;
            for (int i = 0; i < half; i++) {
                if (!cc.equalChars(list.removeFirst(), list.removeLast())) {
                    return false;
                }
            }
            return true;
        }
    }

}
