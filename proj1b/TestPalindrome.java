import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

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
        String input1 = "";
        assertTrue(palindrome.isPalindrome(input1));

        String input2 = "a";
        assertTrue(palindrome.isPalindrome(input2));

        String input3 = "Apple";
        assertFalse(palindrome.isPalindrome(input3));

        String input4 = "assessa";
        assertTrue(palindrome.isPalindrome(input4));
    }

    @Test
    public void testIsPalindromeNew() {
        CharacterComparator offByOne = new OffByOne();
        String input1 = "";
        assertTrue(palindrome.isPalindrome(input1, offByOne));

        String input2 = "a";
        assertTrue(palindrome.isPalindrome(input2, offByOne));

        String input3 = "flake";
        assertTrue(palindrome.isPalindrome(input3, offByOne));

        String input4 = "aa";
        assertFalse(palindrome.isPalindrome(input4, offByOne));
    }
}
