package E1_hashmap;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E18_LargestPalindromicNumber {

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

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a string num consisting of digits only).
        //* Return (the largest palindromic integer) (in the form of a string) that can be formed using digits taken (from num).
        // It should not contain leading zeroes.
        //Notes:
        //- You do not need to use all the digits of num, but you must use at least one digit.
        //- The digits can be reordered.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= num.length <= 10^5
        //num consists of digits.
        //
        //- Brainstorm
        //- Chia ra thành 2 nữa append dạng:
        //  + even + even + 1 character lẻ với (Max value) ở giữa.
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
//        String num = "444947137";
//        String num = "00009";
        String num = "00000";
        System.out.println(largestPalindromic(num));
        //#Reference:
        //520. Detect Capital
        //2283. Check if Number Has Equal Digit Count and Digit Value
        //2062. Count Vowel Substrings of a String
    }
}
