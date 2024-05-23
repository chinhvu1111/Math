package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.HashSet;

public class E153_NumberOfGoodWaysToSplitAString {

    public static int numSplits(String s) {
        HashSet<Character> leftSet=new HashSet<>();
        HashSet<Character> rightSet=new HashSet<>();
        int n=s.length();
        int[] leftDistinct=new int[n];
        int[] rightDistinct=new int[n];

        for(int i=0;i<n;i++){
            leftSet.add(s.charAt(i));
            rightSet.add(s.charAt(n-i-1));
            leftDistinct[i]=leftSet.size();
            rightDistinct[n-i-1]=rightSet.size();
        }
        int rs=0;

        for(int i=0;i<n;i++){
            if(i+1<n&&leftDistinct[i]==rightDistinct[i+1]){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s.
        //A split is called good if you can split s into two non-empty strings sleft and sright
        // where their concatenation is equal to s (i.e., sleft + sright = s)
        // and the number of distinct letters in sleft and sright is the same.
        //* Return the number of good splits you can make in s.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of only lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //Input: s = "aacaba"
        //Output: 2
        //Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
        //("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
        //("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
        //("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
        //("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
        //("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
        //
        //- Split 1 lần ==> Nhưng ra 2 strings khác nhau sao cho:
        //  + left, right cùng distinct characters.
        //
        //
        String s = "aacaba";
        System.out.println(numSplits(s));
    }
}
