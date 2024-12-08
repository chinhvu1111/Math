package E1_daily;

import java.util.Objects;

public class E167_GetBiggestThreeRhombusSumsInAGrid {

    public static int[] getBiggestThree(int[][] grid) {
        int n=grid.length;
        int m = grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        //** Requirement
        //- A rhombus sum is (the sum of the elements) that form (the border of a regular rhombus shape) in grid.
        //- The rhombus must have (the shape of a square) (rotated 45 degrees) with (each of the corners) centered in a grid cell.
        //- Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:
        //
        //* Return (an n x m matrix) representing (the box) after (the rotation described above).
        //- Note that the rhombus can have an area of 0, which is depicted by (the purple rhombus) in the (bottom right corner).
        //* Return (the biggest three distinct rhombus sums) in the grid in (descending order).
        // If there are less than three distinct values, return all of them.
        //
        //Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
        //Output: [228,216,211]
        //Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
        //- Blue: 20 + 3 + 200 + 5 = 228
        //- Red: 200 + 2 + 10 + 4 = 216
        //- Green: 5 + 200 + 4 + 2 = 211
        //- Tức return lại (3 biggest rhombus sum) nếu <3 ==> return all
        //- rhombus: Hình thoi
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 50
        //1 <= grid[i][j] <= 10^5
        //  + n,m<50 ==> Time: O(n^3)
        //
        //- Brainstorm
        //
        int[][] grid = {{3,4,5,1,3},{3,3,4,2,3},{20,30,200,40,10},{1,5,5,4,1},{4,3,2,2,5}};
        int[] rs=getBiggestThree(grid);
        for (int i = 0; i < Objects.requireNonNull(rs).length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //
    }
}
