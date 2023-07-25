package E1_UnionFind;

import java.util.Arrays;

public class E1_SurroundedRegions {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static void dfs(int x, int y, char[][] board, boolean[][] visited, char[][] updateValues){
        visited[x][y]=true;
        updateValues[x][y]='O';
//        System.out.printf("%s %s\n", x, y);
        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];
            if(x1>=0&&x1<board.length&&y1>=0&&y1<board[0].length){
                if(visited[x1][y1]||board[x1][y1]=='X'){
                    continue;
                }
                dfs(x1, y1, board, visited, updateValues);
            }
        }
//        visited[x][y]=false;
    }

    public static void solve(char[][] board) {
        int n=board.length;
        int m=board[0].length;
        char[][] updateValues=new char[n][m];
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(updateValues[i],'1');
        }
        for(int i=0;i<n;i++){
            if(board[i][0]=='O'){
                dfs(i, 0, board, visited, updateValues);
            }
            if(board[i][m-1]=='O'){
                dfs(i, m-1, board, visited, updateValues);
            }
        }
        for(int i=0;i<m;i++){
            if(board[0][i]=='O'){
                dfs(0, i, board, visited, updateValues);
            }
            if(board[n-1][i]=='O'){
                dfs(n-1, i, board, visited, updateValues);
            }
        }
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(board[i][j]=='O'&&updateValues[i][j]=='O'){
                    board[i][j]=updateValues[i][j];
                }else{
                    board[i][j]='X';
                }
                System.out.printf("%s,", board[i][j]);
            }
            System.out.println();
        }

        return;
    }

    public static void main(String[] args) {
        //** Requirement
        //+ Region được captured bởi đảo tất cả các 0 --> x xung quang region đó.
        //- 0 không bị flipped khi:
        //  + 0 ở border
        //  + 0 kề với any 0 khác mà không flip được
        //* Capture all regions that are 4-directionally surrounded by 'X'.
        //
        //** Idea
        //* Method-1: Tological sort
        //1.
        //1.0,
        //- Constraint
        //m == board.length
        //n == board[i].length
        //1 <= m, n <= 200
        //board[i][j] is 'X' or 'O'.
        //
        //- Brainstorm
        //- Cái này dùng dfs là được.
        //
        //1.1, Optimization
        //-
        //1.2, Complexity
        //+ N is the number of cell in the graph
        //- Time complexity : O(N)
        //- Space complexity : O(N)
        //
        char[][] board={
                {'O','X','O','O','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','X'},
                {'O','X','O','X','O','O','O','O','X'},
                {'O','O','O','O','X','O','O','O','O'},
                {'X','O','O','O','O','O','O','O','X'},
                {'X','X','O','O','X','O','X','O','X'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','O','O','X','X','O','O'}};
        solve(board);
        //#Reference:
        //1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
        //2040. Kth Smallest Product of Two Sorted Arrays
        //2257. Count Unguarded Cells in the Grid
        //846. Hand of Straights
        //2503. Maximum Number of Points From Grid Queries
        //1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
    }
}
