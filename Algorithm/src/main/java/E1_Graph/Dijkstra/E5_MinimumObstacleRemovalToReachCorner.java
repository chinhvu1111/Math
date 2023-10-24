package E1_Graph.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E5_MinimumObstacleRemovalToReachCorner {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int minimumObstacles(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        int[][] minDistance=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(minDistance[i], Integer.MAX_VALUE);
        }
        minDistance[0][0]=0;
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[2]-b[2]);
        queue.add(new int[]{0,0,0});

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m){
                    int nextDist=curNode[2]+grid[x1][y1];
                    if(nextDist<minDistance[x1][y1]){
                        minDistance[x1][y1]=nextDist;
                        queue.add(new int[]{x1, y1, nextDist});
                    }
                }
            }
        }
        return minDistance[n-1][m-1];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho grid[][]
        //+ 0 : empty cell
        //+ 1 : an obstacle that may be removed
        //* Return minimum the number of obstacles to remove when moving from (0,0) to (n-1, m-1)
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 105
        //2 <= m * n <= 105
        //grid[i][j] is either 0 or 1.
        //grid[0][0] == grid[m - 1][n - 1] == 0
        //
        //- Brainstorm
        //- First point, key word là minimum number ==> Có thể dùng dijikstra để giải
        //- Second point, cost to move from the current cell to next cell
        //+ Cái này đơn giản là cộng dồn thôi
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(V*E*Log(V))
        //- Space : O(V+E)
        //
        //#Reference:
        //1293. Shortest Path in a Grid with Obstacles Elimination
    }
}
