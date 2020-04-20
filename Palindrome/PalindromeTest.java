package Palindrome;
import org.junit.Assert;
import org.junit.Test;

public class PalindromeTest {

    @Test
    public void checkPalindrome() {
        Assert.assertEquals(true, Main.isPalindrome("teet"));
    }

    @Test
    public void isNotPalindrome() {
        Assert.assertEquals(false, Main.isPalindrome("test"));
    }
}


