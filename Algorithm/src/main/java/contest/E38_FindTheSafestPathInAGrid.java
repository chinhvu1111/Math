package contest;

import java.util.ArrayList;
import java.util.List;

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
        if(dp[x][y]!=-1&&min<=dp[x][y]){
            return min;
        }
        if(x==w.length-1&&y==w[0].length-1){
//            System.out.printf("Last: %s\n", w[x][y]);
//            System.out.printf("Infor: %s %s %s\n", x, y, w[x][y]);
            return dp[x][y]=w[x][y];
        }
        int rs=0;
        min=Math.min(min, w[x][y]);

        for(int i=0;i<dx.length;i++){
            int x1=dx[i]+x;
            int y1=dy[i]+y;
            if(x1>=0&&x1<visited.length&&y1>=0&&y1<visited[0].length&&!visited[x1][y1]){
                visited[x1][y1]=true;
                int curVal=maxWeight(w, x1, y1, visited, min);
                rs=Math.max(rs, curVal);
//                System.out.printf("Sub infor: %s %s, %s %s %s\n", x, y, x1, y1, curVal);
                visited[x1][y1]=false;
            }
        }
//        System.out.printf("Infor: %s %s %s\n", x, y, Math.min(rs, w[x][y]));
        return dp[x][y]=Math.min(rs, w[x][y]);
    }

    public static int maximumSafenessFactor(List<List<Integer>> grid) {
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

    public static void main(String[] args) {
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
        int[][] grid = {
                {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0}};
//        int[][] grid = {
//                {0,0,1},
//                {0,0,0},
//                {0,0,0}};
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
        System.out.println(maximumSafenessFactor(input));
    }
}
