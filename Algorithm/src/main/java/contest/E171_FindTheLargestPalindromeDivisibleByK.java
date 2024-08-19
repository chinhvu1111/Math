package contest;

import java.math.BigInteger;

public class E171_FindTheLargestPalindromeDivisibleByK {

    public String largestPalindromic(String num) {
        StringBuilder rs=new StringBuilder();
        int n= num.length();
        int[] count=new int[26];

        for(int i=0;i<n;i++){
            count[num.charAt(i)-'0']++;
        }
        char c='0';
        boolean isOddExist=false;

        for(int i=25;i>=0;i--){
            if(count[i]!=0&&count[i]%2==0){
                for(int j=0;j<count[i]/2;j++){
                    rs.append((char)(i+'0'));
                }
            }else if(count[i]!=0){
                for(int j=0;j<(count[i]-1)/2;j++){
                    rs.append((char)(i+'0'));
                }
                if(c<=(char)(i+'0')){
                    c=(char)(i+'0');
                }
                isOddExist=true;
            }
        }
        int m=rs.length();
        if(rs.length()!=0&&rs.charAt(0)=='0'){
            if(isOddExist){
                return c+"";
            }
            return "0";
        }
        StringBuilder suffix=new StringBuilder();
        for(int i=rs.length()-1;i>=rs.length()-m;i--){
            suffix.append(rs.charAt(i));
        }
        if(isOddExist){
            rs.append(c);
        }
        rs.append(suffix);
        return rs.toString();
    }

    public static String largestPalindrome(int n, int k) {
        return "";
    }

    public static String largestKPalindromic(int n, int k) {
        // Calculate half length
        int halfLength = (n + 1) / 2;

        // Create the largest half
        char[] half = new char[halfLength];
        for (int i = 0; i < halfLength; i++) {
            half[i] = '9';
        }

        BigInteger halfNumber = new BigInteger(new String(half));
        BigInteger bigK = BigInteger.valueOf(k);

        while (halfNumber.compareTo(BigInteger.ZERO) > 0) {
            // Form the full palindrome
            StringBuilder fullPalindrome = new StringBuilder(halfNumber.toString());
            if (n % 2 == 0) {
                fullPalindrome.append(new StringBuilder(fullPalindrome).reverse());
            } else {
                fullPalindrome.append(new StringBuilder(fullPalindrome.substring(0, fullPalindrome.length() - 1)).reverse());
            }

            BigInteger palindromeNumber = new BigInteger(fullPalindrome.toString());
            if (palindromeNumber.mod(bigK).equals(BigInteger.ZERO)) {
                return fullPalindrome.toString();
            }

            halfNumber = halfNumber.subtract(BigInteger.ONE);
        }

        return "";  // If no such number exists
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two positive integers n and k.
        //- An integer x is called k-palindromic if:
        //  + x is a palindrome.
        //  + x is divisible by k.
        //* Return (the largest integer) having (n digits) (as a string) that is k-palindromic.
        //- Note that the integer must not have leading zeros.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^5
        //1 <= k <= 9
        //  + 10^5 khá lớn ==> Time = O(n)/ O(n*k)
        //  + 1<=nums[i]<=9
        //
        //** Brainstorm
        //
        //
//        int n = 5, k = 6;
        int n = 13, k = 5;
        System.out.println(largestKPalindromic(n, k));
    }
}
