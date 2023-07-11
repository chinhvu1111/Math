package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E19_NumberOfEnclaves {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static int solution(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        System.out.printf("%s %s\n", n, m);
        Queue<int[]> nodes=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(grid[i][m-1]==1){
                nodes.add(new int[]{i, m-1});
                visited[i][m-1]=true;
            }
//            System.out.printf("%s %s\n", i, grid[i][0]);
            if(grid[i][0]==1){
                nodes.add(new int[]{i, 0});
                visited[i][0]=true;
            }
        }
        for(int j=0;j<m;j++){
            if(grid[n-1][j]==1){
                nodes.add(new int[]{n-1, j});
                visited[n-1][j]=true;
            }
            if(grid[0][j]==1){
                nodes.add(new int[]{0, j});
                visited[0][j]=true;
            }
        }
        System.out.println(nodes);
        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
            visited[currentNode[0]][currentNode[1]]=true;
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1&&!visited[x1][y1]){
                    nodes.add(new int[]{x1, y1});
                    visited[x1][y1]=true;
                }
            }
        }
        int result=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1&&!visited[i][j]){
                    result++;
                }
            }
        }
        return result;
    }
    public static int numEnclaves(int[][] grid) {
        return solution(grid);
    }

    public static void main(String[] args) {
        //** Requirement
        //- A move consists of walking from one (land cell) to (another adjacent) (4-directionally) (land cell)
        // or walking off the boundary of the grid.
        //- Trả lại các cell mà ta từ đó không thể đi ra ngoài grid được.
        //- Ta có thể đi 4 directions
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraints:
        //+ m == grid.length
        //+ n == grid[i].length
        //+ 1 <= m, n <= 500
        //--> Khá ít
        //
        //- Tư duy là xét những nodes ở viền trước --> Sau đó khi xét max= 500 nodes
        //==> Nhưng đỉnh còn lại chính là nhưng điểm hợp lệ.
        //
        //1.1, Optimization
        //- Ở đây thay vì add các nodes vào all queue --> Thì theo lời giải mẫu nó sẽ:
        //+ BFS từng node 1 ==> Theo đó reduce được time.
        //
        //1.2, Complexity
        //- Time complexity : O(4*N*M) = O(M*N)
        //- Space complexity : O(N*M)
//        int[][] grid = {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        int[][] grid = {
                {0,0,0,1,1,1,0,1,0,0},
                {1,1,0,0,0,1,0,1,1,1},
                {0,0,0,1,1,1,0,1,0,0},
                {0,1,1,0,0,0,1,0,1,0},
                {0,1,1,1,1,1,0,0,1,0},
                {0,0,1,0,1,1,1,1,0,1},
                {0,1,1,0,0,0,1,1,1,1},
                {0,0,1,0,0,1,0,1,0,1},
                {1,0,1,0,1,1,0,0,0,0},
                {0,0,0,0,1,1,0,0,0,1}
        };
//        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(numEnclaves(grid));
        //#Reference:
        //1254. Number of Closed Islands
        //305. Number of Islands II
        //2166. Design Bitset
        //1654. Minimum Jumps to Reach Home
    }
}
