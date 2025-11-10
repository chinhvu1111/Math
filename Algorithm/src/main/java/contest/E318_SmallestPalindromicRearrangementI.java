package contest;

public class E318_SmallestPalindromicRearrangementI {

    public static String smallestPalindrome(String s) {
        int[] countChars=new int[26];
        int n=s.length();

        for (int i = 0; i < n; i++) {
            countChars[s.charAt(i)-'a']++;
        }
        StringBuilder leftStr=new StringBuilder();
        char remainingChar = '1';

        for (int i = 0; i < 26; i++) {
            if(countChars[i]%2==1){
                remainingChar=(char)(i+'a');
            }
            for (int j = 0; j <countChars[i]/2 ; j++) {
                leftStr.append((char)(i+'a'));
            }
        }
        String left=leftStr.toString();
        String right=leftStr.reverse().toString();
        if(remainingChar!='1'){
            return left+remainingChar+right;
        }
        return left+right;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a palindromic string s.
        //* Return (the lexicographically smallest palindromic permutation) of s.
        //- A string is palindromic if it reads the same forward and backward.
        //- A permutation is a rearrangement of all the characters of a string.
        //- A string a is lexicographically smaller than a string b if in the first position where a and b differ,
        // string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
        //- If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically smaller one.
        //
        //Example 2:
        //
        //Input: s = "babab"
        //Output: "abbba"
        //Explanation:
        //Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^5
        //s consists of lowercase English letters.
        //s is guaranteed to be palindromic.
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- Count char
        //Ex:
        //Input: s = "babab"
        //Output: "abbba"
        //[b:3],[a:2]
        //a,b,b,b,a
        //- How about:
        //[b:3],[a:2],[d:3]
        //a,b,d,b,d,b,a
        //
        String s = "babab";
        System.out.println(smallestPalindrome(s));
    }
}
