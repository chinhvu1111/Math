package E1_daily;

import java.util.Arrays;

public class E142_MaximumNumberOfMovesInAGrid {

    public static int[][] memo;

    public static int dfs(int x, int y, int n, int m, int[][] grid){
        if(memo[x][y]!=0){
            return memo[x][y];
        }
        int curVal=0;
        if(y+1<m){
            if(x>=1&&grid[x-1][y+1]>grid[x][y]){
                curVal=dfs(x-1, y+1, n, m, grid)+1;
            }
            if(grid[x][y+1]>grid[x][y]){
                curVal=Math.max(curVal, dfs(x, y+1, n, m, grid)+1);
            }
            if(x+1<n&&grid[x+1][y+1]>grid[x][y]){
                curVal=Math.max(curVal, dfs(x+1, y+1, n, m, grid)+1);
            }
        }
//        System.out.printf("%s, %s, val: %s\n", x, y, curVal);
        return memo[x][y]=curVal;
    }

    public static int maxMoves(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        memo=new int[n][m];
        int rs=0;
        for(int j=0;j<n;j++){
            if(memo[j][0]==0){
                dfs(j, 0, n, m, grid);
            }
            rs=Math.max(rs, memo[j][0]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement: Bài này là mở rộng của bài trước:
        //  + Tức là đi dài nhất có thể từ 1 cell nào đó
        //- You are given a 0-indexed m x n matrix grid consisting of positive integers.
        //- You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
        //  + From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1)
        //  + (i,j) có thể đi:
        //      + (i,j+1)
        //      + (i+1,j+1)
        //      + (i-1,j+1)
        // such that the value of the cell you move to, should be strictly (bigger than) the value of (the current cell).
        //* Return the maximum number of moves that you can perform.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //2 <= m, n <= 1000
        //4 <= m * n <= 10^5
        //1 <= grid[i][j] <= 10^6
        //
        //- Brainstorm
        //- j tăng dần
        //  + Kiểu gì ta cũng đi cùng lắm (m) moves
        //- Bài này có thể thành bài array 1 chiều:
        //  + Tìm sequence với longest length + Incremental
        //
        //Example 2:
        //
        //Input:
        //  grid = [
        //      [3,2,4],
        //      [2,1,9],
        //      [1,1,7]
        //      ]
        //Output: 0
        //Explanation: Starting from any cell in the first column we cannot perform any moves.
        //
        //- Có thể có cell ở giữa grid mà nó smaller than the previous nodes:
        //- Bài này có thể có khả năng đi lại node không?
        //- 1 node đi đến:
        //  + Smaller value
        //  + Bigger value
        //Ex:
        //9 2 5 3
        //2 4 3 6
        //3 6 7 1
        //- Bài này ta có thể đi all of cells:
        //  + Những sẽ luôn cache longest length với mỗi cell để tránh recalculate
        //
        //- Bài này ta có thể dùng dfs để tính.
        //- Cũng có thể làm theo BFS
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time:O(n*m)
        //
        //#Reference:
        //1337. The K Weakest Rows in a Matrix
        //3129. Find All Possible Stable Binary Arrays I
        //2488. Count Subarrays With Median K
        //
        //2.
        //2.0,
        // Method-1: Dynamic programming
        //- Brainstorm
        //
        //
//        int[][] grid = {{2,4,3,5},{5,4,9,3},{3,4,2,11},{10,9,13,15}};
        int[][] grid = {{3,2,4},{2,1,9},{1,1,7}};
        System.out.println(maxMoves(grid));
    }
}
