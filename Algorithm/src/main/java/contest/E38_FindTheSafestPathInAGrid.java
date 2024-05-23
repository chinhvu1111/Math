package contest;

import java.util.*;

public class E38_FindTheSafestPathInAGrid {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static void dfs(int x, int y, int[][] w, boolean[][] visited, int currentW){
        if(w[x][y]<=currentW&&w[x][y]!=0){
            return;
        }
        w[x][y]=currentW;
//        System.out.printf("%s %s %s\n", x, y, w[x][y]);
        for(int i=0;i<dx.length;i++){
            int x1=dx[i]+x;
            int y1=dy[i]+y;
            if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&!visited[x1][y1]){
                visited[x1][y1]=true;
                dfs(x1,y1, w, visited, currentW+1);
                visited[x1][y1]=false;
            }
        }
    }
    public static int[][] dp;

    public static int maxWeight(int[][] w, int x, int y, boolean[][] visited, int min){
//        if(dp[x][y]!=-1&&min<=dp[x][y]){
//            return min;
//        }
//        if(dp[x][y]!=-1){
//            return Math.min(dp[x][y], min);
//        }
        if(x==w.length-1&&y==w[0].length-1){
//            System.out.printf("Last: %s\n", w[x][y]);
//            System.out.printf("Infor: %s %s %s\n", x, y, w[x][y]);
            return dp[x][y]=Math.min(w[x][y], min);
        }
        int rs=Integer.MIN_VALUE;
        min=Math.min(min, w[x][y]);

        for(int i=0;i<dx.length;i++){
            int x1=dx[i]+x;
            int y1=dy[i]+y;

            if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&!visited[x1][y1]){
                visited[x1][y1]=true;
                int curVal=maxWeight(w, x1, y1, visited, min);
                rs=Math.max(rs, curVal);
                System.out.printf("x: %s, y: %s, sub-value: %s, current rs: %s\n", x, y, curVal, rs);
//                System.out.printf("Sub infor: %s %s, %s %s %s\n", x, y, x1, y1, curVal);
                visited[x1][y1]=false;
            }
        }
        if(dp[x][y]==-1){
            dp[x][y]=(rs==Integer.MIN_VALUE)?w[x][y]: rs;
        }else{
//            dp[x][y]=Math.max(rs==Integer.MIN_VALUE?w[x][y]: rs, dp[x][y]);
//            dp[x][y]=Math.min(dp[x][y], min);
            dp[x][y]=Math.max(min, rs);
        }
        System.out.printf("x: %s, y: %s, val: %s, final rs: %s\n", x, y, dp[x][y], rs);
//        System.out.printf("Infor: %s %s %s\n", x, y, Math.min(rs, w[x][y]));
        return dp[x][y];
    }

    public static int maximumSafenessFactorWrong(List<List<Integer>> grid) {
        if(grid.size()==0){
            return 0;
        }
        int n=grid.size();
        int m=grid.get(0).size();
        int[][] weight=new int[n][m];
        dp=new int[n][m];
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i][j]=-1;
                if(grid.get(i).get(j)==0){
                    weight[i][j]=Integer.MAX_VALUE;
                }else{
                    weight[i][j]=0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid.get(i).get(j)==1&&!visited[i][j]){
                    visited[i][j]=true;
                    dfs(i, j, weight, visited, 0);
//                    visited[i][j]=false;
                }
            }
        }
//        visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s ", weight[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        visited=new boolean[n][m];
        visited[0][0]=true;
        int rs=maxWeight(weight, 0, 0, visited, Integer.MAX_VALUE);

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s ", dp[i][j]);
            }
            System.out.println();
        }
        return rs;
    }

    public static int maximumSafenessFactorTimeout(List<List<Integer>> grid) {
        if(grid.size()==0){
            return 0;
        }
        int n=grid.size();
        int m=grid.get(0).size();

        if(grid.get(0).get(0)==1||grid.get(n-1).get(m-1)==1){
            return 0;
        }
        int[][] weight=new int[n][m];
//        dp=new int[n][m];
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
//                dp[i][j]=-1;
                if(grid.get(i).get(j)==0){
                    weight[i][j]=Integer.MAX_VALUE;
                }else{
                    weight[i][j]=0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid.get(i).get(j)==1&&!visited[i][j]){
                    visited[i][j]=true;
                    dfs(i, j, weight, visited, 0);
//                    visited[i][j]=false;
                }
            }
        }
//        visited=new boolean[n][m];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                System.out.printf("%s ", weight[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
        visited=new boolean[n][m];

        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2]-o1[2];
            }
        });
        queue.add(new int[]{0, 0, weight[0][0]});

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            int x=curNode[0];
            int y=curNode[1];
            int currentMinSafe=curNode[2];
            visited[x][y]=true;

            if(x==n-1&&y==m-1){
                return currentMinSafe;
            }
            for(int i=0;i<dx.length;i++){
                int x1=dx[i]+x;
                int y1=dy[i]+y;
                if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&!visited[x1][y1]){
                    int curMin=Math.min(currentMinSafe, weight[x1][y1]);
                    queue.add(new int[]{x1, y1, curMin});
                    visited[x1][y1]=true;
                }
            }
        }
        return -1;
    }

    public static void bfs(int[][] w, List<List<Integer>> grid){
        Queue<int[]> queue=new LinkedList<>();
        int n=w.length;
        int m=w[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid.get(i).get(j)==1){
                    w[i][j]=0;
                    queue.add(new int[]{i, j});
                }
            }
        }
        boolean[][] visited=new boolean[n][m];
//        System.out.printf("%s %s %s\n", x, y, w[x][y]);
        while (!queue.isEmpty()){
            int[] curNode=queue.poll();
            int curX=curNode[0];
            int curY=curNode[1];
//            visited[curX][curY]=true;

            for(int i=0;i<dx.length;i++){
                int x1=dx[i]+curX;
                int y1=dy[i]+curY;
                if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&w[x1][y1]>w[curX][curY]+1){
//                    visited[x1][y1]=true;
                    queue.add(new int[]{x1, y1});
                    w[x1][y1]=w[curX][curY]+1;
                }
            }
        }
    }

    public static int maximumSafenessFactorBFS(List<List<Integer>> grid) {
        if(grid.size()==0){
            return 0;
        }
        int n=grid.size();
        int m=grid.get(0).size();

        if(grid.get(0).get(0)==1||grid.get(n-1).get(m-1)==1){
            return 0;
        }
        int[][] weight=new int[n][m];
//        dp=new int[n][m];
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
//                dp[i][j]=-1;
                if(grid.get(i).get(j)==0){
                    weight[i][j]=Integer.MAX_VALUE;
                }else{
                    weight[i][j]=0;
                }
            }
        }
        bfs(weight, grid);
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                System.out.printf("%s ", weight[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
        visited=new boolean[n][m];

        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2]-o1[2];
            }
        });
        queue.add(new int[]{0, 0, weight[0][0]});

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            int x=curNode[0];
            int y=curNode[1];
            int currentMinSafe=curNode[2];
            visited[x][y]=true;

            if(x==n-1&&y==m-1){
                return currentMinSafe;
            }
            for(int i=0;i<dx.length;i++){
                int x1=dx[i]+x;
                int y1=dy[i]+y;
                if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&!visited[x1][y1]){
                    int curMin=Math.min(currentMinSafe, weight[x1][y1]);
                    queue.add(new int[]{x1, y1, curMin});
                    visited[x1][y1]=true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
        //  + A cell containing a thief if grid[r][c] = 1
        //  + An empty cell if grid[r][c] = 0
        //- You are initially positioned at cell (0, 0).
        // In one move, you can move to any adjacent cell in the grid, including (cells containing thieves).
        //- (The safeness factor) of a path on the grid is defined as (the minimum manhattan distance) from (any cell) in the path to (any thief) in the grid.
        //* Return (the maximum safeness factor) of (all paths) leading to cell (n - 1, n - 1).
        //  + An adjacent cell of cell (r, c), is one of the cells
        //  (r, c + 1),
        //  (r, c - 1),
        //  (r + 1, c) and
        //  (r - 1, c) if it exists.
        //- The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.
        //* Tức là tìm path sao cho:
        //  - (The safeness factor) of a path on the grid is defined as (the minimum manhattan distance) from (any cell) in the path to (any thief) in the grid.
        //  + Trong 1 path thì MIN value từ any cell => the thief.
        //  + Trong all path --> MAX value
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= grid.length == n <= 400
        //grid[i].length == n
        //grid[i][j] is either 0 or 1.
        //+ There is (at least one thief) in the grid.
        //
        //- Brainstorm
        //E.g:
        //Input: grid =
        // [
        // [0,0,0,1],
        // [0,0,0,0],
        // [0,0,0,0],
        // [1,0,0,0]
        // ]
        //Output: 2
        //Explanation: The path depicted in the picture above has a safeness factor of 2 since:
        //- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2).
        // The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
        //- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2).
        // The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
        //It can be shown that there are no other paths with a higher safeness factor.
        //
        //- Mỗi cell ta có 4 lựa chọn traverse:
        //  + (r, c + 1) : Min nhất đến 1 gần nhất
        //  + (r, c - 1)
        //  + (r + 1, c)
        //  + (r - 1, c)
        //==> dp[x][y] sẽ là max nhất của 4 directions.
        //- Làm ntn thì bị 1 vấn đề là w[i][j] có thể đi đến:
        //  + (r, c + 1) :
        //      + dp[r][c+1] có thể không đúng vì gía trị này đại diện cho 1 tập hợp các node -> (n-1, n-1) khác nhau
        //      + visited[r][c+1] => (n-1, n-1) khác nhau ==> Thế nên không thể làm ntn được.
        //  + (r, c - 1)
        //  + (r + 1, c)
        //  + (r - 1, c)
        //==> Cách làm này không đúng nữa.
        //
        //- Bài này dùng Dijikstra + BFS
        //- Nếu dùng DFS --> loop(n*m) + DFS ==> timeout
        //* Kinh nghiệm:
        //  - Với dạng bài traverse from multiple nodes ==> Dùng BFS
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n*m)
        //- Time : O(n^2*log(n))
        //
//        int[][] grid = {
//                {0,0,0,1},
//                {0,0,0,0},
//                {0,0,0,0},
//                {1,0,0,0}};
//        int[][] grid = {
//                {1,1,1},
//                {0,1,1},
//                {0,0,0}};
//        int[][] grid = {
//                {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
//                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
//                {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0}};
        int[][] grid = {
                {0,0,1},
                {0,0,0},
                {0,0,0}};
        //2 1 0
        //3 2 1
        //4 3 2
        //
        //2 1 0
        //3 2 1
        //4 3 2
        //
        int n=grid.length;
        int m=grid[0].length;
        List<List<Integer>> input=new ArrayList<>();

        for(int i=0;i<n;i++){
            List<Integer> currentList=new ArrayList<>();
            for (int j = 0; j < m; j++) {
                currentList.add(grid[i][j]);
            }
            input.add(currentList);
        }
        //#Reference:
        //1631. Path With Minimum Effort
//        System.out.println(maximumSafenessFactorWrong(input));
        System.out.println(maximumSafenessFactorTimeout(input));
        System.out.println(maximumSafenessFactorBFS(input));
    }
}
