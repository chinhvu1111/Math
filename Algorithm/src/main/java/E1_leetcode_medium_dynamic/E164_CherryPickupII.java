package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E164_CherryPickupII {

    public static int[] dx={1, 1, 1};
    public static int[] dy={-1, 0, 1};
    public static int solution(int x, int y, int x1, int y1, boolean[][] visited, int[][] grid, int[][][][] memo, int n, int m){
        if(x1>=n){
            return 0;
        }
        if(memo[x][y][x1][y1]!=-1){
            return memo[x][y][x1][y1];
        }
        int curRs=0;

        for(int i=0;i<dx.length;i++){
            for(int j=0;j<dx.length;j++){
                int nextX=x+dx[i];
                int nextY=y+dy[i];
                int nextX1=x1+dx[j];
                int nextY1=y1+dy[j];
                if(nextX>=n||nextY>=m||nextY<0||nextX1>=n||nextY1<0||nextY1>=m||nextY==nextY1){
                    continue;
                }
                visited[nextX][nextY]=true;
                visited[nextX1][nextY1]=true;
                curRs=Math.max(curRs, solution(nextX, nextY, nextX1, nextY1, visited, grid, memo, n, m));
                visited[nextX][nextY]=false;
                visited[nextX1][nextY1]=false;
            }
        }
        return memo[x][y][x1][y1]=curRs+grid[x][y]+grid[x1][y1];
    }
    public static int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][][] memo=new int[n][m][n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int h=0;h<n;h++){
                    Arrays.fill(memo[i][j][h], -1);
                }
            }
        }
        boolean[][] visited=new boolean[n][m];
        return solution(0, 0, 0, m-1, visited, grid, memo, n, m);
    }

    public static int solutionOptimization(int x, int y, int x1, int y1, int[][] grid, int[][][] memo, int n, int m){
        if(x1>=n){
            return 0;
        }
        if(memo[x][y][y1]!=-1){
            return memo[x][y][y1];
        }
        int curRs=0;

        for(int i=0;i<dx.length;i++){
            for(int j=0;j<dx.length;j++){
                int nextX=x+dx[i];
                int nextY=y+dy[i];
                int nextX1=x1+dx[j];
                int nextY1=y1+dy[j];
                if(nextX>=n||nextY>=m||nextY<0||nextX1>=n||nextY1<0||nextY1>=m||nextY==nextY1){
                    continue;
                }
                curRs=Math.max(curRs, solutionOptimization(nextX, nextY, nextX1, nextY1, grid, memo, n, m));
            }
        }
        return memo[x][y][y1]=curRs+grid[x][y]+grid[x1][y1];
    }

    public static int cherryPickupOptimization(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] memo=new int[n][m][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        return solutionOptimization(0, 0, 0, m-1, grid, memo, n, m);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents
        // (the number of cherries) that you can collect from the (i, j) cell.
        //- You have two robots that can collect cherries for you:
        //  + Robot #1 is located at the top-left corner (0, 0), and
        //  + Robot #2 is located at the top-right corner (0, cols - 1).
        //* Return (the maximum number of cherries) collection using (both robots) by following the rules below:
        //- From a cell (i, j), robots can move to cell
        //  + (i + 1, j - 1): diagonal left
        //  + (i + 1, j): down
        //  + (i + 1, j + 1): diagonal right
        //- When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
        //- When both robots stay in the same cell, only one takes the cherries.
        //- Both robots cannot move outside of the grid at any moment.
        //- Both robots should reach the (bottom) row in grid.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //rows == grid.length
        //cols == grid[i].length
        //2 <= rows, cols <= 70
        //0 <= grid[i][j] <= 100
        //  + len(rows), len(cols) không quá lớn
        //  ==> Xử lý trong O(n^4) được
        //
        //** Brainstorm
        //- 2 robots cùng move thì lưu intermediate dp ntn?
        //- Ta sẽ cho 2 robots run cùng lúc
        //  + Depth của 2 thằng sẽ luôn == nhau
        //- Mỗi thằng 1 visited riêng không overlap với nhau là được.
        //- Top down approach:
        //  + Ta có thể dùng memo để giải quyết vì size của (row/ col) không quá lớn.
        //
        //- Bottom up approach:
        //
        //
        //1.1, Optimization
        //* KINH NGHIỆM:
        //  + Với những bài dynamic dạng 2 objects cùng depth
        //  ==> Ta có thể giảm (space/time) from O(D^4) ==> O(D^4)
        //  + Ngoài ra đi từ (top -> down) sẽ:
        //      + Không cần dùng visited ==> Vì sẽ không go back to the previous level.
        //
        //1.2, Complexity
        //- Space: O(n^2*m^2)
        //- Time: O(n^2*m^2)
        //
        //#Reference:
        //1966. Binary Searchable Numbers in an Unsorted Array
        //2174. Remove All Ones With Row and Column Flips II
        //2915. Length of the Longest Subsequence That Sums to Target
        //
        int[][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        System.out.println(cherryPickup(grid));
        System.out.println(cherryPickupOptimization(grid));
    }
}
