package E1_daily;

import java.util.Arrays;
import java.util.Comparator;

public class E221_ShiftingLettersII {

    public static char shiftChar(char c, int nextNum){
        nextNum=nextNum%26;
        return (char)((c-'a'+nextNum+'z'-'a'+1)%('z'-'a'+1)+'a');
    }

    public static String shiftingLetters(String s, int[][] shifts) {
//        Arrays.sort(shifts, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
        int n=s.length();
        int[] prefixSum=new int[n+1];
        int m=shifts.length;

        for(int i=0;i<m;i++){
            if(shifts[i][2]==1){
                prefixSum[shifts[i][0]]++;
                prefixSum[shifts[i][1]+1]--;
            }else{
                prefixSum[shifts[i][0]]--;
                prefixSum[shifts[i][1]+1]++;
            }
        }
        StringBuilder rs=new StringBuilder();
        int count=0;
        for(int i=0;i<n;i++){
            count+=prefixSum[i];
            rs.append(shiftChar(s.charAt(i), count));
        }
        return rs.toString();
    }

    public static String shiftingLettersReference(String s, int[][] shifts) {
        int n = s.length();
        int[] diffArray = new int[n]; // Initialize a difference array with all elements set to 0.

        // Process each shift operation
        for (int[] shift : shifts) {
            if (shift[2] == 1) { // If direction is forward (1)
                diffArray[shift[0]]++; // Increment at the start index
                if (shift[1] + 1 < n) {
                    diffArray[shift[1] + 1]--; // Decrement at the end+1 index
                }
            } else { // If direction is backward (0)
                diffArray[shift[0]]--; // Decrement at the start index
                if (shift[1] + 1 < n) {
                    diffArray[shift[1] + 1]++; // Increment at the end+1 index
                }
            }
        }

        StringBuilder result = new StringBuilder(s);
        int numberOfShifts = 0;

        // Apply the shifts to the string
        for (int i = 0; i < n; i++) {
            numberOfShifts = (numberOfShifts + diffArray[i]) % 26; // Update cumulative shifts, keeping within the alphabet range
            if (numberOfShifts < 0) numberOfShifts += 26; // Ensure non-negative shifts
            // Calculate the new character by shifting `s[i]`
            char shiftedChar = (char) ('a' +
                    ((s.charAt(i) - 'a' + numberOfShifts) % 26));
            result.setCharAt(i, shiftedChar);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s of lowercase English letters and a 2D integer array shifts where
        // shifts[i] = [starti, endi, directioni].
        //- For every i, shift the characters in s
        // from the index (start-i) to the index (end-i) (inclusive) (forward) if direction-i = 1,
        // or shift the characters (backward) if direction-i = 0.
        //- Shifting a character forward means (replacing it with the next letter) in the alphabet (wrapping around so that 'z' becomes 'a').
        //- Similarly, shifting a character backward means (replacing it with the previous letter) in the alphabet
        // (wrapping around so that 'a' becomes 'z').
        //* Return (the final string) after (all such shifts) to s are applied.
        //
        //Example 1:
        //
        //Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
        //Output: "ace"
        //Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
        //Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
        //Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length, shifts.length <= 5 * 10^4
        //shifts[i].length == 3
        //0 <= start-i <= end-i < s.length
        //0 <= direction-i <= 1
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //- Sweep line
        //- 0+1 ==> keep
        //  + -1+1 = 0:
        //      + Keep
        //  + -1+-1 = -2:
        //      + Backward 2 times
        //
        //1.1, Optimization
        //** Kinh nghiệm:
        //----- Công thức shift
        //nextNum=nextNum%26;
        //(char)((c-'a'+nextNum+'z'-'a'+1)%('z'-'a'+1)+'a');
        //----- Công thức shift
        //
        //- Conclude the formula:
        //numberOfShifts = (numberOfShifts + diffArray[i]) % 26; // Update cumulative shifts, keeping within the alphabet range
        //if (numberOfShifts < 0) numberOfShifts += 26; // Ensure non-negative shifts
        // Calculate the new character by shifting `s[i]`
        //char shiftedChar = (char) ('a' +
        //     ((s.charAt(i) - 'a' + numberOfShifts) % 26));
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String s = "abc";
        int[][] shifts = {{0,1,0},{1,2,1},{0,2,1}};
        //0,1,1,-2
//        System.out.println(shiftChar('c',1));
        System.out.println(shiftChar('c',-1));
        System.out.println(shiftChar('c',-3));
        System.out.println(shiftChar('c',-30));
        System.out.println(shiftChar('a',0));
        //a,b,c,...,z
        //0,1,2
        //3
        System.out.println(shiftingLetters(s, shifts));
        System.out.println(shiftingLettersReference(s, shifts));
        //
        //#Reference:
        //218. The Skyline Problem
        //307. Range Sum Query - Mutable
        //370. Range Addition
    }
}
