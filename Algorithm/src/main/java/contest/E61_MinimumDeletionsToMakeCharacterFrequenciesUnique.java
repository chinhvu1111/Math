package contest;

public class E61_MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public static int minDeletions(String s) {
        return 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- A string s is called good if there are (no two different characters in s that have the same frequency).
        //Given a string s,
        //* Return the minimum number of characters you need to delete to make s good.
        //The frequency of a character in a string is the number of times it appears in the string.
        // For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s contains only lowercase English letters.
        //
        //- Brainstorm
        //- Ta chỉ có thể delete --> làm string thành good (Không có 2 character có cùng count)
        //Ex
        //aaabbbcc
        //2,3,3
    }
}
