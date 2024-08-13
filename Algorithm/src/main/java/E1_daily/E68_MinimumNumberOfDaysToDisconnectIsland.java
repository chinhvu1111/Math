package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E68_MinimumNumberOfDaysToDisconnectIsland {
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static void dfs(int x, int y, int n, int m, int[][] grid, int[] count, boolean[][] visited){
        count[0]++;
        for(int h=0;h<dx.length;h++){
            int x1=x+dx[h];
            int y1=y+dy[h];
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1&&!visited[x1][y1]){
                visited[x1][y1]=true;
                dfs(x1, y1, n, m, grid, count, visited);
//                visited[x1][y1]=false;
            }
        }
    }

    public static int minDays(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dpCount=new int[n][m];
        boolean[][] visited=new boolean[n][m];
        List<int[]> nodes=new ArrayList<>();
        int countOne=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0){
                    continue;
                }
                nodes.add(new int[]{i, j});
                countOne++;
                for(int h=0;h<dx.length;h++){
                    int x1=i+dx[h];
                    int y1=j+dy[h];
                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1){
                        dpCount[x1][y1]++;
                    }
                }
            }
        }
        int[] count=new int[1];
        int countTraverse=0;

        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1&&!visited[i][j]) {
                    if(countTraverse==1){
                        return 0;
                    }
                    visited[i][j]=true;
                    dfs(i, j, n, m, grid, count, visited);
                    countTraverse++;
                }
            }
        }

        if(countOne==1){
            return 1;
        }
        if(count[0]!=countOne||countOne==0){
            return 0;
        }
        for(int i=0;i<nodes.size();i++){
            visited=new boolean[n][m];
            int[] node=nodes.get(i);
            int[] root=(i>=1)?nodes.get(i-1):nodes.get(i+1);
            count=new int[]{0};
            visited[node[0]][node[1]]=true;
            visited[root[0]][root[1]]=true;
            dfs(root[0], root[1], n, m, grid, count, visited);
            visited[node[0]][node[1]]=false;
            visited[root[0]][root[1]]=false;
            if(count[0]!=countOne-1){
                return 1;
            }
        }

        int minAdjNum=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    minAdjNum=Math.min(minAdjNum, dpCount[i][j]);
                }
            }
        }
        if(minAdjNum+1==countOne){
            return countOne;
        }
        return minAdjNum;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an m x n binary grid grid where 1 represents land and 0 represents water.
        //- An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.
        //- The grid is said to be connected if we have (exactly one island), otherwise is said disconnected.
        //In one day, we are allowed to change any single land cell (1) into a water cell (0).
        //* Return the minimum number of days to disconnect the grid.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 30
        //grid[i][j] is either 0 or 1.
        //
        //- Brainstorm
        //- Làm thế nào để disconnect:
        //  + Số day để ngắn 1 cell không connect được đến cell khác
        //- Để disconnect 1 cell:
        //  + Xoá các node kết nối với nó là được
        //- Chọn node có số nodes kết nối với nó ít nhất là được
        //
        //- return 0 : nếu đã disconnected rồi
        //
//        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        //- Special case
        //+ Case chỉ cần xoá 1 node --> disconnected
        //- Điểm đó là điểm then chốt để connect island
        //  + Ta sẽ xét case này riêng ra
        //
//        int[][] grid = {
//                {1,1,0,1,1},
//                {1,1,1,1,1},
//                {1,1,0,1,1},
//                {1,1,0,1,1}};
//        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] grid = {
                {0,1,1},
                {1,1,1},
                {1,1,0}};
        System.out.println(minDays(grid));
        //#Reference:
        //2556. Disconnect Path in a Binary Matrix by at Most One Flip
    }
}
