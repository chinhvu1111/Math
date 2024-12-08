package E1_daily;

import java.util.HashSet;
import javafx.util.Pair;

public class E164_BombEnemy {

    public static int markKill(int row, int col, char[][] grid){
        HashSet<Pair<Integer, Integer>> visited=new HashSet<>();
        // Traverse upwards
        for(int r=row-1;r>=0;r--){
            if(grid[r][col]=='W'){
                break;
            }
            if(grid[r][col]=='E'){
                visited.add(new Pair<>(r, col));
            }
        }
        for (int r = row + 1; r < grid.length; r++) {
            if (grid[r][col] == 'W') break;
            if(grid[r][col]=='E'){
                visited.add(new Pair<>(r, col));
            }
        }
        // Traverse leftwards
        for (int c = col - 1; c >= 0; c--) {
            if (grid[row][c] == 'W') break;
            if(grid[row][c]=='E'){
                visited.add(new Pair<>(row, c));
            }
        }
        // Traverse rightwards
        for (int c = col + 1; c < grid[0].length; c++) {
            if (grid[row][c] == 'W') break;
            if(grid[row][c]=='E'){
                visited.add(new Pair<>(row, c));
            }
        }
        return visited.size();
    }

    public static int maxKilledEnemies(char[][] grid) {
        int n= grid.length;
        int m = grid[0].length;
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='0'){
                    rs=Math.max(rs, markKill(i, j, grid));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0',
        //* return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.
        //- The bomb kills all the enemies in the same row and column from the planted point
        // until it hits the wall since it is too strong to be destroyed.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //
        //
        char[][] grid = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        System.out.println(maxKilledEnemies(grid));
        //
    }
}
