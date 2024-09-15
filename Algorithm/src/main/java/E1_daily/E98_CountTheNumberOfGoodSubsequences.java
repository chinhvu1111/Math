package E1_daily;

public class E98_CountTheNumberOfGoodSubsequences {

    public static int countGoodSubsequences(String s) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A subsequence of a string is good if it is not empty and (the frequency of each one) of (its characters) is the same.
        //- Given a string s,
        //* return the number of good subsequences of s.
        //  + Since the answer may be too large, return it modulo 109 + 7.
        //- (A subsequence is a string) that can be derived from another string by (deleting some) or (no characters)
        // (without changing the order) of the remaining characters.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 10^4
        //s consists of only lowercase English letters.
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: s = "aabb"
        //Output: 11
        //Explanation: The total number of subsequences is 24. There are five subsequences which are not good: "aabb", "aabb", "aabb", "aabb",
        // and the empty subsequence. Hence, the number of good subsequences is 24-5 = 11.
        //
        //
    }
}
