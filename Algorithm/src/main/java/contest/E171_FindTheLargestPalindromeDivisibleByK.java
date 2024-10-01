package contest;

import java.math.BigInteger;

public class E171_FindTheLargestPalindromeDivisibleByK {

    public static String largestPalindromic(String num) {
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

    public static String largestKPalindromicTLE(int n, int k) {
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

    public static String largestPalindrome(int n, int k) {
        if(k==1||k==3||k==9){
            return full9(n);
        }else if(k==2) {
            return handle2(n);
        }else if(k==4) {
            return handle4(n);
        }else if(k==5) {
            return handle5(n);
        }else if(k==6) {
            return handle6(n);
        }else if(k==7) {
            return handle7(n);
        }else if(k==8) {
            return handle8(n);
        }else{
            return "-1";
        }
    }

    public static String full9(int n){
        StringBuilder rs=new StringBuilder();
        for(int i=0;i<n;i++){
            rs.append(9);
        }
        return rs.toString();
    }

    public static String handle2(int n){
        if(n==1){
            return "8";
        }else if(n==2){
            return "88";
        }
        String s = full9(n-2);
        return "8"+s+"8";
    }

    public static String handle4(int n){
        if(n==1){
            return "8";
        }else if(n==2){
            return "88";
        }else if(n==3){
            return "888";
        }else if(n==4){
            return "8888";
        }
        String s = full9(n-4);
        return "88"+s+"88";
    }
    public static String handle5(int n){
        if(n==1){
            return "5";
        }else if(n==2){
            return "55";
        }
        String s = full9(n-2);
        return "5"+s+"5";
    }

    public static String handle6(int n){
        if(n==1){
            return "6";
        }else if(n==2){
            return "66";
        }
        String s = handle2(n);
        if(n%2==0){
            s = s.substring(0,n/2-1)+"77"+s.substring(n/2+1);
        }else{
            s = s.substring(0,n/2)+"8"+s.substring(n/2+1);
        }
        return s;
    }

    public static String handle7(int n){
        if(n == 1) return "7";
        if(n == 2) return "77";
        String s = full9(n);
        if(n%2 == 0){
            for(int i=9; i>=0; i--){
                s = s.substring(0, n/2-1) + i+""+i + s.substring(n/2+1, n);
                int remainder = 0;
                for(int j=0; j<n; j++) remainder = (remainder * 10 + s.charAt(j) - '0')%7;
                if(remainder == 0) return s;
            }
        }
        else{
            for(int i=9; i>=0; i--){
                s = s.substring(0, n/2) + i + s.substring(n/2+1, n);
                int remainder = 0;
                for(int j=0; j<n; j++) remainder = (remainder * 10 + s.charAt(j) - '0')%7;
                if(remainder == 0) return s;
            }
        }
        return "-1";
    }

    public static String handle8(int n){
        if(n == 1) return "8";
        if(n == 2) return "88";
        if(n == 3) return "888";
        if(n == 4) return "8888";
        if(n == 5) return "88888";
        if(n == 6) return "888888";
        String s = full9(n);
        s = "888"+s.substring(3, n-3) + "888";
        return s;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two positive integers n and k.
        //- An integer x is called k-palindromic if:
        //  + x is a palindrome.
        //  + x is divisible by k.
        //* Return (the largest integer) having (n digits) (as a string) that is k-palindromic.
        //- Note that the integer must not have leading zeros.
        //- Đại loại là tìm max integer có n chữ số:
        //  + Nhưng nó là palindrome number
        //
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^5
        //1 <= k <= 9
        //  + 10^5 khá lớn ==> Time = O(n)/ O(n*k)
        //  + 1<=nums[i]<=9
        //      + Có 9 k:
        //          + Ta cần xét từng k một.
        //
        //** Brainstorm
        //- Bài này đại loại là chia để list các palindrome thì:
        //  + List các số có len = (n+1)/2
        //Ex:
        // n = 5
        //=> len = 3
        //  999
        //  => (999 * 2) sau đó trừ đi 1 character ở giữa hay không tuỳ thuộc vào n:
        //  + Lẻ
        //  + Chẵn
        //
        //- Nếu làm như bình thường thì đoạn cast sẽ bị timeout:
        //
        //- Handled every case separately. We can have k values from 1 to 9, and we needed to generate the largest palindrome that is divisible by k.
        //- We can first generate a palindrome with n 9's and then adjust the starting, ending, and middle parts to make the number divisible by k.
        //k = 1, 3, 9: We return all 9's because 9 is divisible by 1, 3, and 9 itself.
        //k = 2:
        //  + We need an even number, so we replace the first and last digit with 8, and keep the rest as 9's.
        //  Ex:
        //  899998 ==> Last char == First char (Vì là palindrome)
        //k = 4: The last two digits should be divisible by 4, so we replace the first and last digits with 88, and keep the rest as 9's.
        //  + 2 số cuối là 88:
        //  Ex: 8899988
        //k = 5: The last digit should either be 0 or 5.
        //  - To maximize the number, we replace the first and last digits with 5, and keep the rest as 9's.
        //  - Ex: 59995
        //k = 6:
        //  - Là số chẵn + có tổng % 3 ==0
        //  - 1458: có 1 + 4 + 5 + 8 = 18, nên nó chia hết cho 3 và chữ số tận cùng là chẵn, vì thế nó chia hết cho 6.
        // The number must be divisible by both 2 and 3. We first generate the string using the k = 2 function,
        //  - ensuring divisibility by 2. To ensure divisibility by 3, we adjust the mid-values:
        //  - Ex:
        //      n==5
        //      (89998 % 2) == 0
        //      + Để 89998 % 3 ==0:
        //      16+8 ==> Biến đổi 9 -> 8
        //      n==6
        //      (899998 % 2) == 0
        //      16+2*x % 3 ==0:
        //      ==> x nên là 7, chuyển 9 -> 7
        //  + If n is even, change 2 mid-values to 7 to reflect a palindrome, thereby removing 4 from the number.
        //  Ex: 89[99]98
        //  + Change 2 số ở giữa = 7
        //  + If n is odd, we only need to remove an extra one, so we change the mid-value to 8.
        //k = 7:
        //  + We generate all 9's and then try changing the mid-values (2 mid-values if n is even, and 1 mid-value if n is odd) from 9 to 0.
        //  + After applying each change, we check divisibility by 7 using modulo operation from start to end. If divisible, we return the number;
        // otherwise, we keep changing the mid-values.
        //  + if n là chẵn:
        //      + Change 2 giá trị ở mid:
        //          00 --> 99: sao cho remainder các số % 7 ==0
        //          return s
        //  + if n là lẻ:
        //      + Change 1 giá trị ở mid:
        //          0 --> 9: sao cho remainder các số % 7 ==0
        //          return s
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n) (String)
        //- Time: O(n*10) (Handle 7)
        //
//        int n = 5, k = 6;
        int n = 13, k = 5;
//        System.out.println(largestPalindromic(n, k));
//        System.out.println(largestKPalindromicTLE(n, k));
        System.out.println(largestPalindrome(n, k));
    }
}
