package contest;

import java.util.ArrayList;
import java.util.List;

public class E291_FindMissingAndRepeatedValues {

    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        int n=grid.length;
        int[] count=new int[n*n+1];

        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                count[grid[i][j]]++;
            }
        }
        int[] rsArr=new int[2];

        for (int i = 1; i <=n*n; i++) {
            if(count[i]==0){
                rsArr[1]=i;
            }
            if(count[i]==2){
                rsArr[0]=i;
            }
        }
        return rsArr;
    }

    public static int[] findMissingAndRepeatedValuesRefer(int[][] grid) {
        long sum = 0, sqrSum = 0;
        long n = grid.length;
        long total = n * n;

        // Calculate actual sum and squared sum from grid
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n; ++col) {
                sum += grid[row][col];
                sqrSum += (long) grid[row][col] * grid[row][col];
            }
        }

        // Calculate differences from expected sums
        // Expected sum: n(n+1)/2, Expected square sum: n(n+1)(2n+1)/6
        long sumDiff = sum - (total * (total + 1)) / 2;
        long sqrDiff = sqrSum - (total * (total + 1) * (2 * total + 1)) / 6;

        // Using math: If x is repeated and y is missing
        // sumDiff = x - y
        // sqrDiff = x² - y²
        int repeat = (int) (sqrDiff / sumDiff + sumDiff) / 2;
        int missing = (int) (sqrDiff / sumDiff - sumDiff) / 2;

        return new int[] { repeat, missing };
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given a 0-indexed 2D integer matrix grid of size n * n) with values in the range [1, n^2].
        //- (Each integer) appears (exactly once) except a which (appears twice) and b which is (missing).
        //- The task is to find (the repeating) and (missing) numbers (a and b).
        //* Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
        //
        //Example 1:
        //
        //Input: grid = [[1,3],[2,2]]
        //Output: [2,4]
        //Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n == grid.length == grid[i].length <= 50
        //1 <= grid[i][j] <= n * n
        //For all x that 1 <= x <= n * n there is exactly one x that is not equal to any of the grid members.
        //For all x that 1 <= x <= n * n there is exactly one x that is equal to exactly two of the grid members.
        //For all x that 1 <= x <= n * n except two of them there is exatly one pair of i, j that 0 <= i, j <= n - 1 and grid[i][j] == x.
        //
        //- Brainstorm
        //
        //1.1, Cases
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n) => O(1)
        //- Time: O(n*n)
        //
        int[][] grid = {{1,3},{2,2}};
        int[] rs = findMissingAndRepeatedValues(grid);
        System.out.printf("%s %s\n", rs[0], rs[1]);
        //
        //#Reference:
        //828. Count Unique Characters of All Substrings of a Given String
        //2343. Query Kth Smallest Trimmed Number
        //2322. Minimum Score After Removals on a Tree
    }
}
