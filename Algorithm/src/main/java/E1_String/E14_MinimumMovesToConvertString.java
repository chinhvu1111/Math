package E1_String;

public class E14_MinimumMovesToConvertString {
    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s consisting of n characters which are either 'X' or 'O'.
        //A move is defined as selecting three consecutive characters of s and converting them to 'O'. Note that if a move is applied to the character 'O', it will stay the same.
        //* Return the minimum number of moves required so that all the characters of s are converted to 'O'.
        //
        //Ex:
        //Input: s = "XXOX"
        //Output: 2
        //Explanation: XXOX -> OOOX -> OOOO
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //3 <= s.length <= 1000
        //s[i] is either 'X' or 'O'.
        //
        //- Brainstorm
        //Ex:
        // 00X0XX
        //+ s.charAt(i)=='X': i+=2; rs++;
        //#Reference:
        //1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
        //1898. Maximum Number of Removable Characters
        //551. Student Attendance Record I
        //
    }
}
