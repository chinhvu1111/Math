package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E44_TheMaze {
    //u,r,d,l
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int n=maze.length;
        int m=maze[0].length;
        boolean[][][] visited=new boolean[n][m][4];
        boolean[][] trace=new boolean[n][m];

        Queue<int[]> nodes=new LinkedList<>();
        nodes.add(new int[]{start[0], start[1]});
        visited[start[0]][start[1]][0]=true;
        visited[start[0]][start[1]][1]=true;
        visited[start[0]][start[1]][2]=true;
        visited[start[0]][start[1]][3]=true;
        trace[start[0]][start[1]]=true;

        while(!nodes.isEmpty()){
            int[] curNode=nodes.poll();

            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];
                int prevX1=curNode[0];
                int prevY1=curNode[1];

                while(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1][i]&&maze[x1][y1]==0){
                    visited[x1][y1][i]=true;
                    prevX1=x1;
                    prevY1=y1;
                    x1=x1+dx[i];
                    y1=y1+dy[i];
                }
                System.out.printf("Prev: %s %s\n", prevX1, prevY1);
                if(!trace[prevX1][prevY1]&&maze[prevX1][prevY1]==0
                        &&(x1<0||x1>=n||y1<0||y1>=m||maze[x1][y1]==1)){
                    nodes.add(new int[]{prevX1, prevY1});
                    trace[prevX1][prevY1]=true;
                }
                if(trace[destination[0]][destination[1]]){
                    return true;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s,", trace[i][j]);
            }
            System.out.println();
        }
        return trace[destination[0]][destination[1]];
    }

    public static boolean dfs(int m, int n, int[][] maze, int[] curr, int[] dest, boolean[][] visit){
        if(visit[curr[0]][curr[1]]){
            return false;
        }
        if(curr[0]==dest[0]&&curr[1]==dest[1]){
            return true;
        }
        visit[curr[0]][curr[1]]=true;

        for(int i=0;i<4;i++){
            int r=curr[0], c=curr[1];

            while(r>=0&&r<m&&c>=0&&c<n&&maze[r][c]==0){
                r+=dx[i];
                c+=dy[i];
            }
            if(dfs(m, n, maze, new int[]{r-dx[i], c-dy[i]}, dest, visit)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasPathOptimization(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visit = new boolean[m][n];
        return dfs(m, n, maze, start, destination, visit);
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
        // The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
        // When the ball stops, it could choose the next direction.
        //-
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
//        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
//        int[] start = {0,4}, destination = {3,2};
        //#Reference:
        //265. Paint House II
        //1582. Special Positions in a Binary Matrix
        //654. Maximum Binary Tree
        //
        //1.1, Optimization
        //- Bài dạng này chỉ cần:
        //+ Loop : direction bên ngoài theo dx, dy
        //+ Khi escape loop --> r-dx[i], c-dy[i] ==> Loop tiếp
        //+ Nếu visited[node[0]][node[1]] ==> return false : cycle
        //+ nếu node[0]==dest[0], node[1]==dest[1] ==> return true : Đến đích
        //
        //1.2, Complexity
        //- Space : O(N+E)
        //- Time : O(N+E)
        int[][] maze = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        int[] start = {0,4}, destination = {4,4};
//        int[][] maze = {
//                {0,0,0},
//                {0,0,0},
//                {0,0,0}};
//        int[] start = {0,0}, destination = {1,2};
        System.out.println(hasPath(maze, start, destination));
    }
}
