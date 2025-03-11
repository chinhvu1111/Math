package E1_daily;

import java.util.Arrays;

public class E240_GridGame {

    public static void findMaxPath(int n, int m, int[][] grid, long[][] dp, int[][][] index){
        dp[0][0]=grid[0][0];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                long topVal=0;
                if(i>0){
                    topVal=dp[i-1][j];
                }
                long leftVal = 0;
                if(j>0){
                    leftVal=dp[i][j-1];
                }
                dp[i][j]=Math.max(leftVal, topVal)+grid[i][j];
                if(leftVal<topVal){
                    index[i][j]=new int[]{i-1,j};
                }else{
                    index[i][j]=new int[]{i,j-1};
                }
            }
        }
    }

    public static long gridGameWrong(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        long[][] dp=new long[n][m];
        int[][][] index=new int[n][m][2];

        findMaxPath(n, m, grid, dp, index);
        int x=n-1;
        int y=m-1;
        grid[x][y]=0;
        grid[0][0]=0;
        while(x!=0||y!=0){
            int[] parent = index[x][y];
            grid[parent[0]][parent[1]]=0;
            x=parent[0];
            y=parent[1];
        }
        dp=new long[n][m];
        index=new int[n][m][2];
        findMaxPath(n, m, grid, dp, index);
        return dp[n-1][m-1];
    }

    public static long gridGame(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        long[][] prefixSum=new long[n][m+1];

        for(int i=0;i<n;i++){
            long sum=0;
            for(int j=1;j<=m;j++){
                sum+=grid[i][j-1];
                prefixSum[i][j]=sum;
            }
        }
        long minVal=Long.MAX_VALUE;
        //1 3 1 [15]
        // 1 3 3 1
        //index_pivot = 3
        //==>
        //0 0 0 [0]
        //1 3 3 0
        //
        for(int i=1;i<=m;i++){
            long curRsFirstRow = prefixSum[0][m]-prefixSum[0][i];
            long curRsSecondRow = prefixSum[1][i-1];
            minVal=Math.min(minVal, Math.max(curRsSecondRow,curRsFirstRow));
        }
        return minVal;
    }

    public static long gridGameReference(int[][] grid) {
        long topSum = Arrays.stream(grid[0]).asLongStream().sum(), bottomSum = 0;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; ++i) {
            topSum -= grid[0][i];
            ans = Math.min(ans, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents (the number of points at position (r, c)) on the matrix.
        //- Two robots are playing a game on this matrix.
        //- (Both robots) initially start at (0, 0) and want to reach (1, n-1).
        //  + Each robot may only move to the
        //      + right ((r, c) to (r, c + 1))
        //      or
        //      + down ((r, c) to (r + 1, c)).
        //- At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting (all the points) from the cells on its path.
        //- For all cells (r, c) traversed on the path,
        //  + grid[r][c] is set to 0.
        //- Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path.
        //* Note that their paths may intersect with one another.
        //- The first robot wants to (minimize) (the number of points) collected by the second robot.
        //- In contrast, the second robot wants to maximize the number of points it collects.
        //* If both robots play optimally,
        //** return (the number of points) collected by (the second robot).
        //
        //- The first robot go first
        //  ==> Collect max points
        //- The second robo go after the first robot
        //
        //Input: grid = [[2,5,4],[1,5,1]]
        //Output: 4
        //Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
        //The cells visited by the first robot are set to 0.
        //The second robot will collect 0 + 0 + 4 + 0 = 4 points.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //grid.length == 2
        //n == grid[r].length
        //1 <= n <= 5 * 10^4
        //1 <= grid[r][c] <= 10^5
        //  + Time: O(n)
        //  + Value of the grid[r][c]>0
        //      + Dijikstra
        //
        //- Brainstorm
        //- Two robot run independently
        //Ex:
        //Input:
        // grid =
        // [
        // [1,2,1,15],
        // [3,3,3,1]
        // ]
        //Output: 7
        //
        //- Using dynamic programming
        //
        //dp[i][j]: Max coins we can get at (i,j)
        //  + dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        //
//        int[][] grid = {{2,5,4},{1,5,1}};
//        int[][] grid = {{1,3,1,15},{1,3,3,1}};
        //
        //- There is a case that we get less coin:
        //  + We go down and then going right ==> To minimize the coin that second robot can get
        //
        //- 2 rows ==> We can use PREFIX SUM
        //
        //* MAIN POINT:
        //a + b
        //c + d
        //==> the second robot can get max(c,b)
        //  + We need minimize that
        //
        //1 3 1 [15]
        // 1 3 3 1
        //index_pivot = 3
        //==>
        //0 0 0 [0]
        //1 3 3 0
        //+ the second robot : MAX((first = 0), (second = 7))
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*2)
        //- Time: O(n*2)
        //
        int[][] grid = {
                {20,3,20,17,2,12,15,17,4,15},
                {20,10,13,14,15,5,2,3,14,3}};
//        System.out.println(gridGameWrong(grid));
        System.out.println(gridGame(grid));
        System.out.println(gridGameReference(grid));
        //
        //#Reference:
        //2435. Paths in Matrix Whose Sum Is Divisible by K
        //1296. Divide Array in Sets of K Consecutive Numbers
        //3413. Maximum Coins From K Consecutive Bags
    }
}
