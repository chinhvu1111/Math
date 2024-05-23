package E1_leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E140_AsFarFromLandAsPossible {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static int maxDistance(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dp=new int[n][m];
        Queue<int[]> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    nodes.add(new int[]{i, j});
                    dp[i][j]=0;
                }
            }
        }
        if(nodes.isEmpty()){
            return -1;
        }
        int rs=-1;

        while (!nodes.isEmpty()){
            int[] curNode=nodes.poll();

            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m
                        &&dp[x1][y1]>dp[curNode[0]][curNode[1]]+1){
                    if(grid[x1][y1]==0){
                        nodes.add(new int[]{x1, y1});
//                        System.out.printf("x1: %s, y1: %s, val: %s\n", x1, y1, dp[x1][y1]);
                        dp[x1][y1]=dp[curNode[0]][curNode[1]]+1;
                        rs=Math.max(rs, dp[x1][y1]);
                    }
                }
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //Given an n x n grid containing only values 0 and 1, where (0) represents water and (1) represents land,
        //* Find a water cell such that its distance to (the nearest land cell) is (maximized), and return the (distance).
        // If no land or water exists in the grid, return -1.
        //The distance used in this problem is the Manhattan distance:
        //  + the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
        //- Tìm a (water) cell sao cho:
        //  + Khoảng cách từ nó đến (nearest land) là Max.
        //      + Có thể có rất nhiều island ==> Lấy cái gần nhất.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == grid.length
        //n == grid[i].length
        //1 <= n <= 100
        //grid[i][j] is 0 or 1
        //
        //- Brainstorm
        //- Mỗi đỉnh có thể đi:
        //  + 4 directions.
        //- dp[i][j] = Math.min(dp[i-1][j],..., dp[i][j+1])
        //- Init:
        //  - Những phần == 0: dp[i][j]=0
        //
        //- BFS:
        //  + Đường đi ngắn nhất từ [i][j] -> 0.
        //  + arr[i][j]==1 thì add vào queue: ==> SAI
        //      + Nếu dp[i][j]+1<... --> Update vào queue.
        //      ==> Nếu gặp grid[i][j]==0 --> dp[i][j]=(deph luôn)
        //  + arr[i][j]==0 thì mới add vào queue
        //      + Mới cho traverse tiếp
        //          + Sau đó update nếu cần.
        int[][] grid = {{1,0,1},{0,0,0},{1,0,1}};
        System.out.println(maxDistance(grid));
    }
}
