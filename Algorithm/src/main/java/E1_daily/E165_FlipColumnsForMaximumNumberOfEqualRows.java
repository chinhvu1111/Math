package E1_daily;

import java.util.HashMap;

public class E165_FlipColumnsForMaximumNumberOfEqualRows {

    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        int n= matrix.length;
        int m = matrix[0].length;
        HashMap<String, Integer> mapCount=new HashMap<>();
        int rs=0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            StringBuilder curPattern=new StringBuilder();
            //Time: O(m)
            for(int j=1;j<m;j++){
                if(matrix[i][j]!=matrix[i][j-1]){
                    curPattern.append("|");
                }
                curPattern.append("*");
            }
            //Time: O(m)
            String s= curPattern.toString();
            int oldCount = mapCount.getOrDefault(s, 0);
            mapCount.put(s, oldCount+1);
            rs=Math.max(rs, oldCount+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n binary matrix.
        //- You can choose (any number of columns) in the matrix and flip (every cell) in that column
        // (i.e., Change the value of the cell from 0 to 1 or vice versa).
        //* Return (the maximum number of rows) that have (all values) equal after (some number of flips).
        //
        //Example 2:
        //
        //Input: matrix = [
        // [0,1],
        // [1,0]
        //]
        //Output: 2
        //Explanation: After flipping values in the first column, both rows have equal values.
        //- Tức là flips any columns ==> (Max number of rows) that have (all values equal).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == matrix.length
        //n == matrix[i].length
        //1 <= m, n <= 300
        //matrix[i][j] is either 0 or 1.
        //  + n<=300: Time: O(n^2)
        //
        //- Brainstorm
        //
        //Example 3:
        //
        //Input: matrix =
        // [
        // [0,0,0],
        // [0,0,1],
        // [1,1,0]
        // ]
        //Output: 2
        //Explanation: After flipping values in the first two columns, the last two rows have equal values.
        // [
        // [1,1,0],
        // [1,1,1],
        // [0,0,0]
        // ]
        //
        //- Do we have the same idea as before?
        //
        // [
        // [0,0,0],
        // [0,0,1],
        // [1,1,0]
        // ]
        //- Flip all cells of the columns:
        //  + How to determine what columns need to by flipped
        //- Let me rotate a little bit:
        //
        //0,1,0
        //1,0,1
        //1,1,1
        //- How about using XOR operator?
        // [
        // [0,0,0],
        // [0,0,1],
        // [1,1,0]
        // ]
        //==>
        // [
        // [0,0,1],
        // [0,0,0],
        // [1,1,1]
        // ]
        //- n,m<=300:
        //  + 1<<300 ==> Time limit
        //
        //Row [0, 0, 0, 1, 1, 0, 0] produces the pattern: ***|**|**|
        //Row [0, 1, 1, 1, 1, 1, 0] produces the pattern: *|*****|*|
        //The solution is simply the frequency of the most common pattern across all rows in the matrix.
        //
        //
        int[][] matrix = {{0,0,0},{0,0,1},{1,1,0}};
        System.out.println(maxEqualRowsAfterFlips(matrix));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1293. Shortest Path in a Grid with Obstacles Elimination
        //1210. Minimum Moves to Reach Target with Rotations
        //1672. Richest Customer Wealth
    }
}
