package E1_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E20_NumberOfClosedIslands {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int solution(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        List<int[]> listIslandCells=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0){
                    listIslandCells.add(new int[]{i, j});
//                    System.out.printf("Init: %s %s\n", i, j);
                }
            }
        }
        boolean[][] visited=new boolean[n][m];
        int rs=0;

        for(int[] nodes: listIslandCells){
            if(!visited[nodes[0]][nodes[1]]){
                visited[nodes[0]][nodes[1]]=true;
            }else{
                continue;
            }
            Queue<int[]> currentNodes=new LinkedList<>();
            currentNodes.add(nodes);
            boolean isClosedIsland= nodes[0] == 0 || nodes[0] == n - 1 || nodes[1] == 0 || nodes[1] == m - 1;

            while (!currentNodes.isEmpty()){
                int[] currentNode=currentNodes.poll();
                visited[currentNode[0]][currentNode[1]]=true;
//                System.out.printf("Node: %s %s\n", currentNode[0], currentNode[0]);

                for(int i=0;i<dx.length;i++){
                    int x1=currentNode[0]+dx[i];
                    int y1=currentNode[1]+dy[i];
//                    System.out.printf("Node: %s %s %s\n", x1, y1, visited[x1][y1]);

                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==0){
                        currentNodes.add(new int[]{x1, y1});
                        visited[x1][y1]=true;
                        if(x1==0||x1==n-1||y1==0||y1==m-1){
                            isClosedIsland=true;
                        }
                    }
                }
            }
            if(!isClosedIsland){
//                System.out.printf("%s %s\n",nodes[0], nodes[1]);
                rs++;
            }
        }
        return rs;
    }

    public static int solutionRefactor(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        List<int[]> listIslandCells=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0){
                    listIslandCells.add(new int[]{i, j});
//                    System.out.printf("Init: %s %s\n", i, j);
                }
            }
        }
        int rs=0;

        for(int[] nodes: listIslandCells){
            if(grid[nodes[0]][nodes[1]]==0){
                grid[nodes[0]][nodes[1]]=1;
            }else{
                continue;
            }
            Queue<int[]> currentNodes=new LinkedList<>();
            currentNodes.add(nodes);
            boolean isNotClosedIsland= nodes[0] == 0 || nodes[0] == n - 1 || nodes[1] == 0 || nodes[1] == m - 1;

            while (!currentNodes.isEmpty()){
                int[] currentNode=currentNodes.poll();
                grid[currentNode[0]][currentNode[1]]=1;
//                System.out.printf("Node: %s %s\n", currentNode[0], currentNode[0]);

                for(int i=0;i<dx.length;i++){
                    int x1=currentNode[0]+dx[i];
                    int y1=currentNode[1]+dy[i];
//                    System.out.printf("Node: %s %s %s\n", x1, y1, visited[x1][y1]);

                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==0){
                        currentNodes.add(new int[]{x1, y1});
                        grid[x1][y1]=1;
                        if(isNotClosedIsland){
                            continue;
                        }
                        if(x1==0||x1==n-1||y1==0||y1==m-1){
                            isNotClosedIsland=true;
                        }
                    }
                }
            }
            if(!isNotClosedIsland){
//                System.out.printf("%s %s\n",nodes[0], nodes[1]);
                rs++;
            }
        }
        return rs;
    }

    public static int closedIsland(int[][] grid) {
        return solutionRefactor(grid);
    }

    public static void main(String[] args) {
        //** Requirement
        //- An island is a maximal 4-directionally connected group of 0s
        //  + 1 hòn đạo là 1 tập hợp các số 0 được kết nối bởi tối đa 4 directions
        //- a (closed island) is an island totally (all left, top, right, bottom) surrounded by 1s.
        //  + Là island được bao quanh bởi toàn 1s (all left, top, right, bottom)
        //
        //- return số lượng closed island
        //* Clear requirement:
        //- Các số 0 kết hợp được với nhau khi nào mới là 1 island:
        //+ Chỉ cần có connect trên 1 trong 4 hướng là được.
        //- Closed island là gì:
        //+ Các mặt của island bắt buộc phải cover hết bởi water ( số 1)
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraints:
        //1 <= grid.length, grid[0].length <= 100
        //0 <= grid[i][j] <=1
        //--> Khá nhỏ có thể làm bfs, dfs được
        //
        //- Chuyển đổi về dạng tìm các tập hợp 0 sao cho all nodes trong tập hơp đó không có node nào giao biên.
        //
        //- List all node 0 : Để traverse
        //- Sau đó traverse dần dần --> dùng visited[x,y] để check
//        int[][] grid = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
//        int[][] grid = {{1,1,1,1,1,1,1},
//                {1,0,0,0,0,0,1},
//                {1,0,1,1,1,0,1},
//                {1,0,1,0,1,0,1},
//                {1,0,1,1,1,0,1},
//                {1,0,0,0,0,0,1},
//                {1,1,1,1,1,1,1}};
        //
        //1.1, Optimization
        //- Ở đây mình đang tìm cách BFS cho mọi điểm trong graph --> Điều này có vẻ không được tối ưu
        //==> Có vẻ traverse ngày lúc duyệt loop(N*M) sẽ làm phép toán nhanh hơn <> add all nodes vào queue.
        //
        //1.2, Complexity
        //-
        int[][] grid =
                       {{1,1,0,1,1,1,1,1,1,1},
                        {0,0,1,0,0,1,0,1,1,1},
                        {1,0,1,0,0,0,1,0,1,0},
                        {1,1,1,1,1,0,0,1,0,0},
                        {1,0,1,0,1,1,1,1,1,0},
                        {0,0,0,0,1,1,0,0,0,0},
                        {1,0,1,0,0,0,0,1,1,0},
                        {1,1,0,0,1,1,0,0,0,0},
                        {0,0,0,1,1,0,1,1,1,0},
                        {1,1,0,1,0,1,0,0,1,0}};
        System.out.println(closedIsland(grid));
        //#Reference:
        //695. Max Area of Island
        //1591. Strange Printer II
        //733. Flood Fill
        //2397. Maximum Rows Covered by Columns
    }
}
