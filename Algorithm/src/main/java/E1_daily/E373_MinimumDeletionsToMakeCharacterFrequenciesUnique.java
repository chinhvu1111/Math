package E1_daily;

public class E373_MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public static int minDeletions(String s) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A string s is called good if there are no two different characters in s that have the same frequency.
        //- Given a string s, return the minimum number of characters you need to delete to make s good.
        //- The frequency of a character in a string is the number of times it appears in the string.
        //  + For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
        //
        //Example 2:
        //
        //Input: s = "aaabbbcc"
        //Output: 2
        //Explanation: You can delete two 'b's resulting in the good string "aaabcc".
        //Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^5
        //s contains only lowercase English letters.
        //  + s.length <= 10^5 => Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Ex:
        //Input: s = "aaabbbcc"
        //Output: 2
        //a[3],b[2],c[2]
        //==> Remove smallest
        //[3,3,2]
        //[3,2,1] ==> 2
        //
        //[3,3,3,3,2,2,2,1,1,1]
        //==>
        //count[3]= 4 ==> rs+=3
        //count[2]=7 ==> rs+=
        //3(3),2(2),1(1)
        //3,2,1
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
    }
}
