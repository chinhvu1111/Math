package contest;

public class E319_SmallestPalindromicRearrangementII {
    public static void main(String[] args) {
        //** Requirement
        //- You are (given a palindromic string s) and (an integer k).
        //* Return the (k-th) (lexicographically smallest palindromic permutation of s).
        //- If there are fewer than (k distinct palindromic permutations),
        //  * return an empty string.
        //* Note: Different rearrangements that yield the same palindromic string are considered (identical) and are (counted once).
        //- A string is palindromic if it reads the same forward and backward.
        //- A permutation is a rearrangement of all the characters of a string.
        //- A string a is lexicographically smaller than a string b if in the first position where a and b differ,
        // string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
        //- If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically smaller one.
        //
        //Example 1:
        //
        //Input: s = "abba", k = 2
        //Output: "baab"
        //Explanation:
        //The (two distinct palindromic rearrangements) of "abba" are "abba" and "baab".
        //Lexicographically, "abba" comes before "baab". Since k = 2, the output is "baab".
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^4
        //s consists of lowercase English letters.
        //s is guaranteed to be palindromic.
        //1 <= k <= 10^6
        //  + length<=10^4 ==> Time: O(n)
        //  + k<=10^6 ==> Time: O(k)
        //
        //- Brainstorm
        //- Next palindrome of (the palindrome string y)
        //
        //Example 1:
        //
        //Input: s = "abba", k = 2
        //Output: "baab"
        //Explanation:
        //The (two distinct palindromic rearrangements) of "abba" are "abba" and "baab".
        //abba -> baab
        //1,2,2,1 => 2,1,1,2
        //==> 12 ==> 21
        //  + Increment (the left half)
        //
        //Ex:
        //abaacaaba
        //left = abaa
        //All palindrome s:
        //[aaab,aaba,abaa,baaa]
        //Ex: More complex
        //left = abaaccdddaaa
        //All palindrome s:
        //aaaaaabccddd
        //aaaaaabcdcdd
        //aaaaaabcdcdd
        //aaaaaabcddcd
        //aaaaaabcdddc
        //aaaaaabdccdd
        //==> swap(i,j) + sort(i+1,n)
        //
    }
}
