package contest;

import java.util.Arrays;

public class E239_CountPathsWithTheGivenXORValue {
    public static int mod=1_000_000_007;

    public static int countPathsWithXorValue(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        int[][][] dp=new int[n][m][64];
        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[0][0][grid[0][0]]=1;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int h=0;h<=63;h++){
                    if(i-1>=0){
                        if(dp[i-1][j][h]!=-1){
                            if(dp[i][j][h^grid[i][j]]==-1){
                                dp[i][j][h^grid[i][j]]=0;
                            }
                            dp[i][j][h^grid[i][j]]=(dp[i][j][h^grid[i][j]]+dp[i-1][j][h])%mod;
                        }
                    }
                    if(j-1>=0){
//                        System.out.println(h^grid[i][j]);
//                        System.out.println("test: "+h);
                        if(dp[i][j-1][h]!=-1){
                            if(dp[i][j][h^grid[i][j]]==-1){
                                dp[i][j][h^grid[i][j]]=0;
                            }
                            dp[i][j][h^grid[i][j]]=(dp[i][j][h^grid[i][j]]+dp[i][j-1][h])%mod;
                        }
                    }
                }
            }
        }
        //9 xor 2
        //10011001
        //11010101
        //
        return dp[n-1][m-1][k]==-1?0:dp[n-1][m-1][k];
    }
    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array grid with size m x n. You are also given an integer k.
        //- Your task is to calculate the number of paths you can take from the top-left cell (0, 0)
        // to the bottom-right cell (m - 1, n - 1) satisfying the following constraints:
        //  + You can either move to the (right or down). Formally, from the cell (i, j)
        // you may move to the cell (i, j + 1) or to the cell (i + 1, j) if the target cell exists.
        //  + The XOR of (all the numbers) on the path must be equal to (k).
        //* Return (the total number of such paths).
        //
        //Since the answer can be very large, return the result modulo 10^9 + 7.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= m == grid.length <= 300
        //1 <= n == grid[r].length <= 300
        //0 <= grid[r][c] < 16
        //0 <= k < 16
        //  + Length<=300 ==> O(n^2)
        //
        //- Brainstorm
        //- (0,0) -> (m-1,n-1)
        //a b  c
        //e f (g)
        //h i  k
        //
        //
        int[][] grid = {
                {2, 1, 5},
                {7, 10, 0},
                {12, 6, 4}};
        int k = 11;
//        System.out.println(16^3);
        //
        System.out.println(countPathsWithXorValue(grid, k));
    }
}
