package E1_Graph.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E2_TheMaze2 {

    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};
    //[[0,0,1,0,0],
    // [0,0,0,0,0],
    // [0,0,0,1,0],
    // [1,1,0,1,1],
    // [0,0,0,0,0]]

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n=maze.length;
        int m=maze[0].length;
        int[][][] dp=new int[n][m][2];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> minPaths=new PriorityQueue<int[]>((o1, o2)-> o1[2]-o2[2]);
        minPaths.add(new int[]{start[0], start[1], 0});
        boolean visited[][][]=new boolean[n][m][2];
        dp[start[0]][start[1]][0]=0;
        dp[start[0]][start[1]][1]=0;
        visited[start[0]][start[1]][0]=true;
        visited[start[0]][start[1]][1]=true;

        while(!minPaths.isEmpty()){
            int[] currentNode=minPaths.poll();
            int x=currentNode[0];
            int y=currentNode[1];
            int currentWeight=currentNode[2];
            if(x==destination[0]&&y==destination[1]){
                int rs=Integer.MAX_VALUE;
                for(int i=0;i<2;i++){
                    rs=Math.min(rs, dp[x][y][i]);
                }
                return rs;
            }

            for(int i=0;i<dx.length;i++){
                int x1=x+dx[i];
                int y1=y+dy[i];
                int prevWeight=1;
                int dir=i%2;
                // System.out.printf("Current node: %s %s %s, next node: %s %s\n", x, y, currentWeight, x1, y1);

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&maze[x1][y1]==0
                        &&(!visited[x1][y1][dir]||currentWeight+prevWeight<dp[x1][y1][dir])){
                    boolean isIgnore=false;
                    while(x1+dx[i]>=0&&x1+dx[i]<n&&y1+dy[i]>=0&&y1+dy[i]<m
                            &&maze[x1+dx[i]][y1+dy[i]]!=1){
                        // if(dp[x1][y1][dir]<=currentWeight+prevWeight){
                        //     isIgnore=true;
                        //     break;
                        // }
                        // visited[x1][y1][dir]=true;
                        // dp[x1][y1][dir]=currentWeight+prevWeight;
                        x1=x1+dx[i];
                        y1=y1+dy[i];
                        prevWeight++;
                    }
                    if(!isIgnore){
                        if(dp[x1][y1][dir]<=currentWeight+prevWeight){
                            continue;
                        }
                        // System.out.printf("Add node %s %s old value: %s, new value: %s, dir %s\n", x1, y1, dp[x1][y1][dir], currentWeight+prevWeight, i);
                        dp[x1][y1][dir]=currentWeight+prevWeight;
                        visited[x1][y1][dir]=true;
                        minPaths.add(new int[]{x1, y1, currentWeight+prevWeight});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //+ Wall : 1
        //+ Empty : 0
        //- 1 quả bóng có thể lăn 4 hướng --> Chỉ có thể đổi hướng khi gặp wall
        //==> Tìm đường đi ngắn nhất từ quả bóng --> Khung thành.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == maze.length
        //n == maze[i].length
        //1 <= m, n <= 100
        //maze[i][j] is 0 or 1.
        //start.length == 2
        //destination.length == 2
        //0 <= startrow, destinationrow < m
        //0 <= startcol, destinationcol < n
        //
        //- Brainstorm
        //- Nó chỉ có thể đi 1 hướng 1 lúc
        //+ Ở đây ta có thể sử dụng Dijkstra được không?
        //+ đi 1 trong 4 hướng --> Loop cho đến khi gặp wall
        //+ Vì để tránh việc loop dài --> Ta sẽ ghi lại weight tại mỗi node có 4 direction
        //- Nếu direction hiện tại:
        //+ Cùng hướng với ô hiện tại + giá trị trước đó > giá trị hiện tại break
        //+ Nếu khác hướng --> Check đến ô cuối cùng + Bỏ qua hướng:
        //  + Nếu 1 trong 4 hướng có giá trị < hơn --> Bỏ qua
        //
        //- Special case:
        //[[0,0,1,0,0],
        // [0,0,0,0,0],
        // [0,0,0,1,0],
        // [1,1,0,1,1],
        // [0,0,0,0,0]]
        //start=[0,4]
        //dest=[1,2]
        //
        //#Reference:
        //490. The Maze
        //499. The Maze III
    }
}
