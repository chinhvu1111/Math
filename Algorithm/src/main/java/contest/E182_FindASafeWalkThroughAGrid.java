package contest;

import java.util.*;

public class E182_FindASafeWalkThroughAGrid {

    public static int[][] memo;
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};
    public static int solution(int x, int y, List<List<Integer>> grid, int n, int m ,boolean[][] visited, int curHealth){
        if(x==n-1&&y==m-1){
            return memo[x][y]=grid.get(x).get(y);
        }
        if(memo[x][y]!=Integer.MAX_VALUE){
            return memo[x][y];
        }
        curHealth=curHealth-grid.get(x).get(y);
//        if(curHealth<1){
//            return Integer.MAX_VALUE;
//        }
        int nextMin=Integer.MAX_VALUE;
        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];
            if(x1>=0&&y1>=0&&x1<n&&y1<m&&!visited[x1][y1]){
                visited[x1][y1]=true;
                int nextVal = solution(x1, y1, grid, n, m, visited, curHealth);
                visited[x1][y1]=false;
//                if(nextVal==Integer.MAX_VALUE||nextVal==-1){
//                    continue;
//                }
                if(nextVal==Integer.MAX_VALUE){
                    continue;
                }
                nextMin=Math.min(nextMin, nextVal);
            }
        }
        if(nextMin==Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return memo[x][y]=nextMin+grid.get(x).get(y);
    }

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0, health-grid.get(0).get(0)});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int currHealth = current[2];
            System.out.printf("x: %s ,y: %s, health: %s\n", x, y, currHealth);

            if (x == m - 1 && y == n - 1) {
                if(currHealth >=1){
                    return true;
                }
                System.out.printf("%s %s %s\n", x, y, currHealth);
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    int newHealth = currHealth - grid.get(newX).get(newY);
                    if (newHealth > 0) {
                        queue.offer(new int[]{newX, newY, newHealth});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return false;
    }

//    public static boolean solution(int x, int y, List<List<Integer>> grid, int n, int m ,boolean[][] visited, int curHealth){
//        curHealth = curHealth-grid.get(x).get(y);
////        System.out.printf("%s %s %s\n", x, y, curHealth);
//        if(x==n-1&&y==m-1){
//            return curHealth>=1;
//        }
//        if(curHealth<1){
//            return false;
//        }
//        for(int i=0;i<dx.length;i++){
//            int x1=x+dx[i];
//            int y1=y+dy[i];
//            if(x1>=0&&y1>=0&&x1<n&&y1<m&&!visited[x1][y1]){
//                visited[x1][y1]=true;
//                boolean nextVal = solution(x1, y1, grid, n, m, visited, curHealth);
//                visited[x1][y1]=false;
//                if(nextVal){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public static boolean findSafeWalk2(List<List<Integer>> grid, int health) {
        int n=grid.size();
        int m = grid.get(0).size();
        memo=new int[n][m];
        boolean[][] visited=new boolean[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        int rs= solution(0, 0, grid, n, m, visited, health);
        System.out.println(rs);
        return rs!=-1&&rs<health;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an m x n binary matrix grid and an integer health.
        //- You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
        //- You can move up, down, left, or right from one cell to another adjacent cell as long as (your health) remains (positive).
        //- Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
        //  + x=x-1
        //* Return (true) if you can reach (the final cell) with (a health value of 1 or more), and false otherwise.
        //  + health >=1
        //  <> reture false
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 50
        //2 <= m * n
        //1 <= health <= m + n
        //grid[i][j] is either 0 or 1.
        //  + m,n không lớn ==> Time: O(n*m) được
        //
        //** Brainstorm
        //- Bài này dùng memo được
        //
        //
//        int[][] grid = {{0,1,0,0,0},{0,1,0,1,0},{0,0,0,1,0}};
//        int health = 1;
//        int[][] grid = {{1,1,1,1}};
//        int health = 4;
//        int[][] grid = {
//                {0,1,1,0,0,0},
//                {1,0,1,0,0,0},
//                {0,1,1,1,0,1},
//                {0,0,1,0,1,0}};
//        int health = 3;
//        int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
//        int health = 5;
        int[][] grid = {
                {1,0,1,1},
                {0,0,0,1},
                {1,0,1,1},
                {0,1,1,0},
                {1,0,0,1}};
        int health = 4;
        //Bị case này khi đi qua rồi mặc dù không ==> optimal
        //  + Nhưngz
        List<List<Integer>> gridList=new ArrayList<>();
        int n=grid.length;

        for(int i=0;i<n;i++){
            List<Integer> curList=new ArrayList<>();
            for(int j=0;j<grid[0].length;j++){
                curList.add(grid[i][j]);
            }
            gridList.add(curList);
        }
//        System.out.println(findSafeWalk(gridList, health));
//        System.out.println(findSafeWalk1(gridList, health));
        System.out.println(findSafeWalk(gridList, health));
    }
}
