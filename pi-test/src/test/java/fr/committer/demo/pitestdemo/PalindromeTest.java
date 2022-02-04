package fr.committer.demo.pitestdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {
    @Test
    public void whenPalindrom_thenAccept() {
        Palindrome palindromeTester = new Palindrome();
        Assertions.assertTrue(palindromeTester.isPalindrome("noon"));
    }
}
