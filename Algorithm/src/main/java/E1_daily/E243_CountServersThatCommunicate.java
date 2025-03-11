package E1_daily;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E243_CountServersThatCommunicate {

    public static int countServers(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        List<int[]>[] colListNode =new ArrayList[m];
        List<int[]>[] rowListNode =new ArrayList[n];
        HashSet<Pair<Integer, Integer>> validNodes=new HashSet<>();

        for (int i = 0; i < n; i++) {
            rowListNode[i]=new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            colListNode[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    rowListNode[i].add(new int[]{i, j});
                    colListNode[j].add(new int[]{i, j});
                }
            }
        }
        for(int i=0;i<m;i++){
            if(colListNode[i].size()<=1){
                continue;
            }
            for(int[] e: colListNode[i]){
                validNodes.add(new Pair<>(e[0], e[1]));
            }
        }
        for(int i=0;i<n;i++){
            if(rowListNode[i].size()<=1){
                continue;
            }
            for(int[] e: rowListNode[i]){
                validNodes.add(new Pair<>(e[0], e[1]));
            }
        }
        return validNodes.size();
    }

    public static int countServersReference(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[] rowCounts = new int[grid[0].length];
        int[] colCounts = new int[grid.length];

        // Count servers in each row and each column
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rowCounts[col]++;
                    colCounts[row]++;
                }
            }
        }

        int communicableServersCount = 0;

        // Count servers that can communicate (in the same row or column)
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    if (rowCounts[col] > 1 || colCounts[row] > 1) {
                        communicableServersCount++;
                    }
                }
            }
        }

        return communicableServersCount;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a map of a server center), represented as a m * n integer matrix grid,
        // where
        //  + 1 means that on that cell there is (a server)
        //  + 0 means that it is (no server).
        //- Two servers are said to communicate
        //  + if they are on (the same row) or (on the same column).
        //* Return (the number of servers) that communicate with any other server.
        //  + Server can connect to others ==> Accumulate this server
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //1 <= m <= 250
        //1 <= n <= 250
        //grid[i][j] == 0 or 1
        //
        //- Brainstorm
        //-
        //
        //1.1, Optimization
        //- Không cần dùng hashset để add nodes
        //- count số server với mỗi column và row
        //  + Sau đó sử dụng nó để tính sum
        //- Accumulate the server if:
        //=========================
        //if (grid[row][col] == 1) {
        //                    if (rowCounts[col] > 1 || colCounts[row] > 1) {
        //                        communicableServersCount++;
        //                    }
        //                }
        //=========================
        //
        //1.2, Complexity
        //- Space: O(n*m) => O(max(n,m))
        //- Time: O(n*m)
        //
        int[][] grid = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        System.out.println(countServers(grid));
        System.out.println(countServersReference(grid));
        //#Reference:
        //1828. Queries on Number of Points Inside a Circle
        //1920. Build Array from Permutation
        //2598. Smallest Missing Non-negative Integer After Operations
    }
}
