package fr.committer.demo.pitestdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PalindromeTest {
    @Test
    void whenPalindrom_thenAccept() {
        Palindrome palindromeTester = new Palindrome();
        Assertions.assertTrue(palindromeTester.isPalindrome("noon"));
        int one = 1;
        Assertions.assertEquals(one,1);
    }
}
