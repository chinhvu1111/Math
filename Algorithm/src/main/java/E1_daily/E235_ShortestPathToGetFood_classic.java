package E1_daily;

import java.util.LinkedList;
import java.util.Queue;

public class E235_ShortestPathToGetFood_classic {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int getFood(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[] init=new int[]{-1, -1, 0};
        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                if(grid[i][j]=='*'){
                    init[0]=i;
                    init[1]=j;
                }
            }
        }
        if(init[0]==-1){
            return -1;
        }
        Queue<int[]> queue=new LinkedList<>();
        queue.add(init);
        boolean[][] visited=new boolean[n][m];
        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            visited[curNode[0]][curNode[1]]=true;
            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];
                if(x1>=0&&y1>=0&&x1<n&&y1<m&&!visited[x1][y1]&&grid[x1][y1]!='X'){
                    if(grid[x1][y1]=='#'){
                        return curNode[2]+1;
                    }
                    //- Nếu không có đoạn này thì timeout
                    visited[x1][y1]=true;
                    //
                    queue.add(new int[]{x1, y1, curNode[2]+1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are starving and you want to eat food as quickly as possible.
        //- You want to find the shortest path to arrive at any food cell.
        //- You are given an m x n character matrix, grid, of these different types of cells:
        //  + '*' is your location. There is exactly one '*' cell.
        //  + '#' is a food cell. There may be multiple food cells.
        //  + 'O' is free space, and you can travel through these cells.
        //  + 'X' is an obstacle, and you cannot travel through these cells.
        //- You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
        //* Return (the length of the shortest path for) you to reach (any food cell).
        //  + If there is no path for you to reach food, return (-1).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 200
        //grid[row][col] is '*', 'X', 'O', or '#'.
        //The grid contains exactly one '*'.
        //
        //- Brainstorm
        //
        //
        //1.1, Optimization
        //- Nếu không có đoạn này thì timeout
        //visited[x1][y1]=true;
        //  + Thêm vào trong if luôn + thêm cả ở ngoài
        //* BFS exp:
        //Ex:
        // A -> (B), C -> D -> (B)
        //  + Nếu đến B mình không add(B) vào visited luôn
        //  ==> Add(B) again (Không cần thiết) có thể khiến TLE
        //  ==> Do Add(B) nhiều lần từ (other nodes)
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1293. Shortest Path in a Grid with Obstacles Elimination
        //2385. Amount of Time for Binary Tree to Be Infected
        char[][] grid = {{'X','X','X','X','X','X'},{'X','*','O','O','O','X'},{'X','O','O','#','O','X'},{'X','X','X','X','X','X'}};
        System.out.println(getFood(grid));
    }
}
