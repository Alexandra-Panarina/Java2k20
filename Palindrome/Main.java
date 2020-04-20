package Palindrome;

public class Main {

    public static boolean isPalindrome(String input) {
        String reversed = "";

        for(int i = input.length()-1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        return input.equals(reversed);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("test"));
    }
}
