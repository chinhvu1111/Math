package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E103_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dp=new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0]=grid[0][0];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //Right: (x,y) -> (x, y+1)
                //Down: (x,y) -> (x+1, y)
                int yRight=j+1;
                if(yRight<m){
                    dp[i][yRight]=Math.min(dp[i][yRight], dp[i][j]+grid[i][yRight]);
                }
                int xDown=i+1;
                if(xDown<n){
                    dp[xDown][j]=Math.min(dp[xDown][j], dp[i][j]+grid[xDown][j]);
                }
            }
        }
        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Cho 1 matrix
        //* Tìm path mà từ (0,0) --> (m-1,n-1) có sum là MIN.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 200
        //0 <= grid[i][j] <= 200
        //
        //- Brainstorm
        //- Với bài dạng đường đi ntn
        //--> Làm DFS sẽ dễ dàng hơn
        //- Với dạng bài đi (right, down)
        //==> ta có thể làm như bình thường được
        //==> Loop row by row.
        //
        //1.2, Optimization
        //
        //1.3, Complexity:
        //- Space : O(n*m)
        //- Time : O(n*m)
        //#Reference:
        //174. Dungeon Game
        //741. Cherry Pickup
        //2304. Minimum Path Cost in a Grid
    }
}
