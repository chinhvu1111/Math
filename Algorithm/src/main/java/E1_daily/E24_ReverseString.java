package E1_daily;

public class E24_ReverseString {

    public static void reverseString(char[] s) {
        int n=s.length;
        int left=0, right=n-1;

        while(left<right){
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Write a function that reverses a string.
        // The input string is given as an array of characters s.
        //You must do this by modifying the input array in-place with O(1) extra memory.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s[i] is a printable ascii character.
        //
        //- Brainstorm
        //
        //
    }
}
