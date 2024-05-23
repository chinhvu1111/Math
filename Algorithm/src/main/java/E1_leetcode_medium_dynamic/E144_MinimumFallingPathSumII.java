package E1_leetcode_medium_dynamic;

public class E144_MinimumFallingPathSumII {

    public static int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp=new int[n][m];

        for(int i=0;i<m;i++){
            dp[0][i]=grid[0][i];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int curRs=Integer.MAX_VALUE;
                for(int l=0;l<m;l++){
                    if(l!=j){
                        curRs=Math.min(curRs, dp[i-1][l]);
                    }
                }
                dp[i][j]=curRs+grid[i][j];
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<m;i++){
            rs=Math.min(rs, dp[n-1][i]);
        }
        return rs;
    }

    public static int minFallingPathSumOptimization(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp=new int[m];
        int[] prevDp=new int[m];

        for(int i=0;i<m;i++){
            prevDp[i]=grid[0][i];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int curRs=Integer.MAX_VALUE;
                for(int l=0;l<m;l++){
                    if(l!=j){
                        curRs=Math.min(curRs, prevDp[l]);
                    }
                }
                dp[j]=curRs+grid[i][j];
            }
            for(int j=0;j<m;j++){
                prevDp[j]=dp[j];
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<m;i++){
            rs=Math.min(rs, prevDp[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an n x n integer matrix grid,
        //* Return (the minimum sum) of (a falling path with non-zero shifts).
        //- A falling path with non-zero shifts is a choice of exactly one element
        // from each row of grid such that no two elements chosen in adjacent rows are in the same column.
        //* Return lại min sum việc chọn từ mỗi row sao cho:
        //  + Không có 2 elements chọn từ 2 row giáp nhau ==> Luôn thuộc 2 column khác nhau.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == grid.length == grid[i].length
        //1 <= n <= 200
        //-99 <= grid[i][j] <= 99
        //
        //- Brainstorm
        //Ex:
        //[[1,2,3],
        // [4,5,6],
        // [7,8,9]]
        //- dp[i][j] = Min(dp[i-1][all j]
//        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] grid = {{7}};
        System.out.println(minFallingPathSum(grid));
        System.out.println(minFallingPathSumOptimization(grid));
    }
}
