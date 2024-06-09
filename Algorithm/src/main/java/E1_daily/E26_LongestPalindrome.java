package E1_daily;

public class E26_LongestPalindrome {

    public static int longestPalindrome(String s) {
        int n=s.length();
        int[] count=new int[59];

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'A']++;
        }
        int rs=0;
        boolean isOddExist=false;

        for(int i=0;i<59;i++){
            if(count[i]!=0&&count[i]%2==0){
                rs+=count[i];
            }else if(count[i]!=0&&rs%2==0){
                rs+=count[i]-1;
                isOddExist=true;
            }
        }
        if(isOddExist){
            rs++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s which consists of (lowercase or uppercase letters),
        //* return (the length of the longest palindrome) that can be built with (those letters).
        //- Letters are case sensitive, for example, "Aa" is not considered a palindrome.
        //==> Return max length palindrome mà có thể build được từ những characters đã cho
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //
        //
        //- Brainstorm
        //- String with the length is even number:
        //  + Ex:
        //  baab ==> các character phải cùng even
        //- String with the length is odd number:
        //  + Ex:
        //  cabac
        //==> All even + 1 character odd : WRONG IDEA
        //
        //--> Vấn đề ở đây là có thể nó có nhiều odd number
        //Ex:
        //aabcccaaddddd
        //- Từ số lẻ characters ==> Ta có thể lấy ra even characters
        //---> Bao gồm cả UPPER CASE
        //* Solution:
        //- Ta sẽ cộng hết cả odd và even:
        //  + odd : rs+=odd-1
        //  + even : rs+=even
        //- if odd exists:
        //  + rs++
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
        //#Reference:
        //2131. Longest Palindrome by Concatenating Two Letter Words
        //2384. Largest Palindromic Number
        //
        String s="abccccdd";
        System.out.println(longestPalindrome(s));
        System.out.println('a'-'A');
        System.out.println(26+32);
    }
}
