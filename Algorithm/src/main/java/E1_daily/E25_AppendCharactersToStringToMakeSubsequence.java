package E1_daily;

public class E25_AppendCharactersToStringToMakeSubsequence {

    public static int appendCharacters(String s, String t) {
        int n=s.length();
        int m=t.length();

        int i=0;
        for(int j=0;j<n;j++){
            if(t.charAt(i)==s.charAt(j)){
                i++;
                if(i>=m){
                    break;
                }
            }
        }
        return t.length()-i;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two strings s and t consisting of (only lowercase English letters).
        //* Return (the minimum number of characters)
        // that need to be appended to (the end of s) so that (t becomes a subsequence of s).
        //- A subsequence is a string that can be derived from another string
        // (by deleting some or no characters) without changing the order of the remaining characters.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= s.length, t.length <= 10^5
        //s and t consist only of lowercase English letters.
        //==> length khá lớn : O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: s = "coaching", t = "coding"
        //Output: 4
        //Explanation: Append the characters "ding" to the end of s so that s = "coachingding".
        //Now, t is a subsequence of s ("coachingding").
        //It can be shown that appending any 3 characters to the end of s will never make t a subsequence.
        //
        //- Tìm common longest string between s and t
        //s = "coaching"
        //t = "coding"
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(1)
        //- Time : O(n)
        //
        String s = "coaching", t = "coding";
        System.out.println(appendCharacters(s, t));
        //#Reference:
        //1713. Minimum Operations to Make a Subsequence
    }
}
