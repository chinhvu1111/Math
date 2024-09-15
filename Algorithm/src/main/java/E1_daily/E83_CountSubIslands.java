package E1_daily;

public class E83_CountSubIslands {
    public static void dfs(int x, int y, int[][] grid, int islandNumber, int n, int m){
        grid[x][y]=islandNumber;
        for(int i=0;i<dx.length;i++){
            int x1 =x+dx[i];
            int y1 =y+dy[i];
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1){
                dfs(x1, y1, grid, islandNumber, n, m);
            }
        }
    }

    public static boolean dfs1(int x, int y, int[][] grid1, int[][] grid2, int islandNumber, int n, int m){
        boolean curRs = grid1[x][y]==islandNumber;
        grid2[x][y]=islandNumber;

        for(int i=0;i<dx.length;i++){
            int x1 =x+dx[i];
            int y1 =y+dy[i];
            //Grid[x1][y1]==0:
            //  + Không đi tiếp nữa
            //==> Đi tiếp nữa thì sao:
            //  + Mark (False) hết cả đám thôi
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid2[x1][y1]==1){
//                System.out.printf("x: %s, y: %s\n", x1, y1);
                if(grid1[x1][y1]==0){
                    curRs=false;
//                    continue;
                }
//                if(grid2[x1][y1]!=initNumberGrid2){
//                    curRs=false;
//                }
                //Viết ntn rất nguy hiểm
                boolean curTempRs=dfs1(x1, y1, grid1, grid2, islandNumber, n, m);
                curRs = curRs && curTempRs;
            }
        }
        return curRs;
    }

    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int n=grid1.length;
        int m=grid1[0].length;
        int islandNumber=2;
        int rs=0;
//        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid1[i][j]==1){
                    dfs(i, j, grid1, islandNumber, n, m);
                    islandNumber++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid2[i][j]==1){
                    int initNumber = grid1[i][j]==0?-1:grid1[i][j];
                    boolean curRs = dfs1(i, j, grid1, grid2, initNumber, n, m);
                    if(grid1[i][j]==0){
                        continue;
                    }
                    if(curRs){
                        rs++;
                    }
                }
            }
        }
        return rs;
    }

    public static boolean dfsOptimization(int x, int y, int[][] grid1, int[][] grid2, int islandNumber, int n, int m){
        boolean curRs = grid1[x][y]!=0;
        grid2[x][y]=-1;

        for(int i=0;i<dx.length;i++){
            int x1 =x+dx[i];
            int y1 =y+dy[i];
            //Grid[x1][y1]==0:
            //  + Không đi tiếp nữa
            //==> Đi tiếp nữa thì sao:
            //  + Mark (False) hết cả đám thôi
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid2[x1][y1]==1){
//                System.out.printf("x: %s, y: %s\n", x1, y1);
//                if(grid2[x1][y1]!=initNumberGrid2){
//                    curRs=false;
//                }
                //Viết ntn rất nguy hiểm
                boolean curTempRs=dfsOptimization(x1, y1, grid1, grid2, islandNumber, n, m);
                curRs = curRs && curTempRs;
            }
        }
        return curRs;
    }

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int countSubIslandsOptimization(int[][] grid1, int[][] grid2) {
        int n=grid1.length;
        int m=grid1[0].length;
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid2[i][j]==1){
                    int initNumber = grid1[i][j]==0?-1:grid1[i][j];
                    boolean curRs = dfsOptimization(i, j, grid1, grid2, initNumber, n, m);
                    if(curRs){
                        rs++;
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (m x n binary matrices) (grid1 and grid2) containing only 0's (representing water) and 1's (representing land).
        //- An island is a group of 1's connected 4-directionally (horizontal or vertical).
        //- Any cells (outside of the grid) are considered (water cells).
        //- An island in grid2 is considered (a sub-island) if there is (an island) in grid1
        // that contains all the cells that make up this island in grid2.
        //  - Tức là (1 island) trong (grid1) sẽ include toàn bộ cell của (1 island) nào đó của (grid2)
        //* Return (the number of islands) in grid2 that are considered (sub-islands).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == grid1.length == grid2.length
        //n == grid1[i].length == grid2[i].length
        //1 <= m, n <= 500
        //grid1[i][j] and grid2[i][j] are either 0 or 1.
        //  + Size của grid cũng không quá lớn
        //      + m,n<=500 ==> Time: O(n*m) được.
        //
        //- Brainstorm
        //- Ta phải xác định cách mà 1 island của grid 2 include 1 island nào đó trong grid1
        //- Ở đây ta có thể dùng union find:
        //  + Để dễ thì ta sẽ đánh dấu thứ tự của các island bên trong grid1
        //- Đánh dấu grid1 theo từng island:
        //  + Dùng dfs cho dễ.
        //- Sau đó mới traverse từng node của grid2
        //
        //- Điều kiện false ở grid2:
        //  + Có 1 thằng (i,j) thuộc grid2 mà:
        //      + grid1[i][j] == 0
        //  + Có k thằng (i,j) thuộc grid2 mà:
        //      + grid1[i][j] có value khác nhau ==> Thuộc nhiều island
        //
        //** Kinh nghiệm:
        //- Predicate (không viết (recursion function) (required run) check vào đó) vì:
        //  + false && function():
        //      ==> Nếu false đằng trước nó không chạy function sau nữa
        //      ==> Ảnh hưởng đến kết quả chung.
        //
        //1.1, Optimization
        //- Không cần đánh dấu grid1:
        //  + Chỉ cần traverse grid2 ==> Nếu gặp bất cứ grid1[i][j]==0:
        //      + rs=false
        //- Nó sẽ cover toàn bộ cases:
        //  + Có 1 thằng (i,j) thuộc grid2 mà:
        //      + grid1[i][j] == 0
        //  + Có k thằng (i,j) thuộc grid2 mà:
        //      + grid1[i][j] có value khác nhau ==> Thuộc nhiều island
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
//        int[][] grid1 = {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
//        int[][] grid2 = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};
//        int[][] grid1 = {{1,0,1,0,1},{1,1,1,1,1},{0,0,0,0,0},{1,1,1,1,1},{1,0,1,0,1}};
//        int[][] grid2 = {{0,0,0,0,0},{1,1,1,1,1},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1}};
        int[][] grid1 = {
                {1,1,1,1,0,0},
                {1,1,0,1,0,0},
                {1,0,0,1,1,1},
                {1,1,1,0,0,1},
                {1,1,1,1,1,0},
                {1,0,1,0,1,0},
                {0,1,1,1,0,1},
                {1,0,0,0,1,1},
                {1,0,0,0,1,0},
                {1,1,1,1,1,0}
        };
        int[][] grid2 = {
                {1,1,1,1,0,1},
                {0,0,1,0,1,0},
                {1,1,1,1,1,1},
                {0,1,1,1,1,1},
                {1,1,1,0,1,0},
                {0,1,1,1,1,1},
                {1,1,0,1,1,1},
                {1,0,0,1,0,1},
                {1,1,1,1,1,1},
                {1,0,0,1,0,0}
        };
        //
        //{0,1,1,1,0,1},
        //{1,0,0,0,1,1},
        //{1,0,0,0,1,0},
        //{1,1,1,1,1,0}
        //
        //{1,1,0,1,1,1},
        //{1,0,0,1,0,1},
        //{1,1,1,1,1,1},
        //{1,0,0,1,0,0}
//        System.out.println(countSubIslands(grid1, grid2));
        System.out.println(countSubIslandsOptimization(grid1, grid2));
        //#Reference:
        //663. Equal Tree Partition
        //462. Minimum Moves to Equal Array Elements II
        //1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
        //1019. Next Greater Node In Linked List
        //928. Minimize Malware Spread II
        //2906. Construct Product Matrix
    }
}
