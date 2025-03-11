package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E265_SortMatrixByDiagonals {

    public static int[][] sortMatrix(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        for(int i=m-1;i>=1;i--){
            List<Integer> sortElement=new ArrayList<>();
            int x=0;
            int y=i;
            while(x<n&&y<m){
                sortElement.add(grid[x][y]);
                x++;
                y++;
            }
            Collections.sort(sortElement);
            x=0;
            y=i;
            int index=0;
            while(x<n&&y<m){
                grid[x][y]=sortElement.get(index++);
                x++;
                y++;
            }
        }
        for(int i=0;i<n;i++){
            List<Integer> sortElement=new ArrayList<>();
            int x=i;
            int y=0;
            while(x<n&&y<m){
                sortElement.add(grid[x][y]);
                x++;
                y++;
            }
            sortElement.sort(Collections.reverseOrder());
            x=i;
            y=0;
            int index=0;
            while(x<n&&y<m){
                grid[x][y]=sortElement.get(index++);
                x++;
                y++;
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an n x n square matrix of integers grid. Return the matrix such that:
        //
        //The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
        //The diagonals in the top-right triangle are sorted in non-decreasing order.
        //
        //Example 1:
        //
        //Input: grid = [[1,7,3],[9,8,2],[4,5,6]]
        //Output: [[8,2,3],[9,6,7],[4,5,1]]
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //grid.length == grid[i].length == n
        //1 <= n <= 10
        //-105 <= grid[i][j] <= 105
        //
        //- Brainstorm
        //
        //Input: grid = [
        // [1,7,3],
        // [9,8,2],
        // [4,5,6]
        // ]
        //Output: [[8,2,3],[9,6,7],[4,5,1]]
        //[0,2] => [2,0]
        //
        int[][] grid = {{1,7,3},{9,8,2},{4,5,6}};
        int[][] rs = sortMatrix(grid);
        int n=grid.length;
        int m=grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%s, ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
