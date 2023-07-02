package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E16_JumpGameVIII {

    //  (-1,-1)(-1,0)(-1,1)
    //  (0,-1) (0,0) (0,1)
    //  (1,-1) (1,0) (1,1)
    public static int[] dx={-1,-1,-1,0,1,1,1,0};
    public static int[] dy={-1,0,1,1,1,0,-1,-1};

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        if(grid[0][0]==1){
            return -1;
        }else if(n==m&&n==1){
            return 1;
        }
        Queue<int[]> nodes=new LinkedList<>();
        boolean[][] visited=new boolean[n][m];
        visited[0][0]=true;
        nodes.add(new int[]{0,0,1});

        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==0&&!visited[x1][y1]){
                    if(x1==n-1&&y1==m-1){
                        return currentNode[2]+1;
                    }
                    nodes.add(new int[]{x1, y1, currentNode[2]+1});
                    visited[x1][y1]=true;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Trả lại length của clear path là :
        //+ Số lượng cell trên đường phải đi qua từ (top left) --> (bottom right)
        //+ Tất cả các cell mang giá trị = 0
        //+ Các ô được kết nối 8 directions
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //n == grid.length
        //n == grid[i].length
        //1 <= n <= 100
        //grid[i][j] is 0 or 1
        //
        //-
        int[][] grid={
                {1,0,0},
                {1,1,0},
                {1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
        //#Reference:
        //863. All Nodes Distance K in Binary Tree
        //2435. Paths in Matrix Whose Sum Is Divisible by K
    }
}
