package E1_weekly;

public class E2_SubstringsThatBeginAndEndWithTheSameLetter {

    public static long numberOfSubstrings(String s) {
        int n= s.length();
        long[] count = new long[26];
        long rs=0L;

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
            rs+=count[s.charAt(i)-'a'];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed string s consisting of only lowercase English letters.
        //* Return (the number of substrings) in s that (begin and end) with (the same character).
        //- A substring is (a contiguous non-empty sequence of characters) within (a string).
        //* return lại số lượng substring mà start và end cùng 1 character
        //
        //Example 1:
        //Input: s = "abcba"
        //Output: 7
        //Explanation:
        //The substrings of length 1 that start and end with the same letter are: "a", "b", "c", "b", and "a".
        //The substring of length 3 that starts and ends with the same letter is: "bcb".
        //The substring of length 5 that starts and ends with the same letter is: "abcba".
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //
        //- Brainstorm
        //Ex:
        //s = "abcba"
        //rs=7
        //
        //#Reference:
        //1512. Number of Good Pairs
        //1781. Sum of Beauty of All Substrings
        //1885. Count Pairs in Two Arrays
        String s = "abcba";
        System.out.println(numberOfSubstrings(s));
    }
}
