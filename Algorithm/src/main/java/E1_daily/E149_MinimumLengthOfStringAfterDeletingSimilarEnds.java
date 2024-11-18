package E1_daily;

public class E149_MinimumLengthOfStringAfterDeletingSimilarEnds {

    public static int minimumLength(String s) {
        int l=0, r=s.length()-1;

        while(l<r){
//            System.out.printf("%s %s\n", l, r);
            if(s.charAt(l)==s.charAt(r)){
                int c = s.charAt(l);
                while(l<r&&s.charAt(l)==c){
                    l++;
                }
                while(l<=r&&s.charAt(r)==c){
                    r--;
                }
            }else{
                break;
            }
        }
//        System.out.printf("%s %s\n", l, r);
        return r-l+1;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given a string s consisting only of characters 'a', 'b', and 'c'.
        // You are asked to apply the following algorithm on the string (any number of times):
        //  + Pick (a non-empty prefix) from the string s where (all the characters) (in the prefix) are equal.
        //  + Pick (a non-empty suffix) from the string s where (all the characters) (in this suffix) are equal.
        //  + The (prefix and the suffix) should (not intersect) at any index.
        //  + (The characters) from (the prefix and suffix) must be the "same".
        //  + Delete both (the prefix and the suffix).
        //* Return (the minimum length of s) after performing (the above operation) (any number of times) (possibly zero times).
        //Example 2:
        //
        //Input: s = "cabaabac"
        //Output: 0
        //Explanation: An optimal sequence of operations is:
        //- Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
        //- Take prefix = "a" and suffix = "a" and remove them, s = "baab".
        //- Take prefix = "b" and suffix = "b" and remove them, s = "aa".
        //- Take prefix = "a" and suffix = "a" and remove them, s = "".
        //
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s only consists of characters 'a', 'b', and 'c'.
        //  + length <= 10^5 ==> Time: O(n)
        //  + Lower cases: a,b,c ==> Not much
        //
        //- Brainstorm
        //- Let's use the two pointers
        //- For each turn:
        //  + We loop from the first and last of the string
        //- Loop in the (first) over the same character
        //- Loop in the (last) over the same character
        //- l (from start), r (from the last)
        //- Check the s[l]==s[r]:
        //  + Check the next step
        //  + <> break
        //- l!=r:
        //  + Prefix and suffix are not intersect any index
        //
        //- For the second loop:
        //  + while(l<=r&&s.charAt(r)==c)
        //      + Ex: (3,4)
        //          + Left traverse(3): l = 4
        //          + Right traverse(4): r = 4 ==> (l<=r)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        String s = "ca";
        String s = "cabaabac";
        System.out.println(minimumLength(s));
        //
        //#Reference:
        //1044. Longest Duplicate Substring
        //1987. Number of Unique Good Subsequences
        //1682. Longest Palindromic Subsequence II
    }
}
