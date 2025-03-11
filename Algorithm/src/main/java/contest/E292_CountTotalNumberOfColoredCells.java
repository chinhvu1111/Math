package contest;

public class E292_CountTotalNumberOfColoredCells {

    public static long coloredCells(int n) {
        long rs=1;
        int r=1;

        for(int i=1;i<=n;i++){
            rs+=((r-1)* 4L);
            r++;
        }

        return rs;
    }

    public static long coloredCellsRefer(int n) {
        return 1 + (long) n * (n - 1) * 2;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There exists (an infinitely large two-dimensional) grid of (uncolored unit cells_.
        //- You are given a positive integer n, indicating that you must do (the following routine for n minutes):
        //  + (At the first) minute, color any arbitrary unit cell blue.
        //  + (Every minute) thereafter, color blue every uncolored cell that touches (a blue cell).
        //- Below is a pictorial representation of the state of the grid after (minutes 1, 2, and 3).
        //
        //     1
        //   1 1 1
        //     1
        //
        //Example 2:
        //
        //Input: n = 2
        //Output: 5
        //Explanation: After 2 minutes, there are 4 colored cells on the boundary and 1 in the center, so we return 5.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 10^5
        //  + ==> Time: O(n)
        //
        //- Brainstorm
        //
        // n = 2
        //     1
        //   1 1 1
        //     1
        //rs = 5 = 1+(2*4-4)
        //
        // n = 3
        //      1
        //    1 1 1
        //  1 1 1 1 1
        //    1 1 1
        //      1
        //rs = 13
        //
        //rs = 13 = 5 + (3*4-4) = 13
        //
        //1.1, Cases
        //1.2, Optimization
        //+ rs = 1 + (2*4-4) + (3*4-4) + ...  + (n*4-4)
        //  rs = 1 + 4*(2-1) + 4*(3-1) + ...  + 4*(n-1)
        //  = n*(n+1)/2*4 - 2*n+1
        //  = n*(n+1)*2 - 2*n+1
        //  = n*(n-1)*2 + 1
        //
        //  + return 1 + (long) n * (n - 1) * 2;
        //- Space: O(n) --> O(1)
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n) ==> O(1)
        //
        //#Reference:
        //3235. Check if the Rectangle Corner Is Reachable
        //2946. Matrix Similarity After Cyclic Shifts
        //1837. Sum of Digits in Base K
        //
        System.out.println(coloredCells(1));
        System.out.println(coloredCells(2));
        System.out.println(coloredCellsRefer(2));
        //
    }
}
