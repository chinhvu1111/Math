package E1_daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E241_MapOfHighestPeak {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int[][] highestPeak(int[][] isWater) {
        int n=isWater.length;
        int m=isWater[0].length;
        Queue<int[]> nodes=new LinkedList<>();
        boolean[][] visited=new boolean[n][m];
        int[][] rs=new int[n][m];

        //0 0 1
        //1 0 0
        //0 0 0
        //
        //1 1
        //0 0
        //=>
        //1 0 0 0 0 1 0 0 0 1
        //- We traverse by layer from the water cells
        //- If we traverse to the visited cell
        //  + Is there any invalid traverse?
        //Ex:
        //0 1 2 1 0
        //  ==> Because we traverse by layer that means we deal the water cells in the same way
        //  + We traverse unit by unit
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(isWater[i][j]==1){
                    nodes.add(new int[]{i, j, 0});
                    visited[i][j]=true;
                }
            }
        }
        //
        while(!nodes.isEmpty()){
            int size = nodes.size();
            for(int i=0;i<size;i++){
                int[] curNode=nodes.poll();
                visited[curNode[0]][curNode[1]]=true;
                rs[curNode[0]][curNode[1]]=curNode[2];

                for(int j=0;j<dx.length;j++){
                    int x1=curNode[0]+dx[j];
                    int y1=curNode[1]+dy[j];
                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                        visited[x1][y1]=true;
                        nodes.add(new int[]{x1, y1, curNode[2]+1});
                    }
                }
            }
        }
        return rs;
    }

    public static int[][] highestPeakReference(int[][] isWater) {
        int rows = isWater.length;
        int columns = isWater[0].length;
        final int INF = rows * columns; // Large value to represent uninitialized heights

        // Initialize the cellHeights matrix with INF (unprocessed cells)
        int[][] cellHeights = new int[rows][columns];
        for (int[] row : cellHeights) {
            Arrays.fill(row, INF);
        }

        // Set the height of water cells to 0
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (isWater[row][col] == 1) {
                    cellHeights[row][col] = 0; // Water cells have height 0
                }
            }
        }

        // Forward pass: updating heights based on top and left neighbors
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int minNeighborDistance = INF; // Initialize minimum neighbor distance

                // Check the cell above
                int neighborRow = row - 1;
                int neighborCol = col;
                if (isValidCell(neighborRow, neighborCol, rows, columns)) {
                    minNeighborDistance = Math.min(
                            minNeighborDistance,
                            cellHeights[neighborRow][neighborCol]
                    );
                }

                // Check the cell to the left
                neighborRow = row;
                neighborCol = col - 1;
                if (isValidCell(neighborRow, neighborCol, rows, columns)) {
                    minNeighborDistance = Math.min(
                            minNeighborDistance,
                            cellHeights[neighborRow][neighborCol]
                    );
                }

                // Set the current cell's height as the minimum of its neighbors + 1
                cellHeights[row][col] = Math.min(
                        cellHeights[row][col],
                        minNeighborDistance + 1
                );
            }
        }

        // Backward pass: updating heights based on bottom and right neighbors
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = columns - 1; col >= 0; col--) {
                int minNeighborDistance = INF; // Initialize minimum neighbor distance

                // Check the cell below
                int neighborRow = row + 1;
                int neighborCol = col;
                if (isValidCell(neighborRow, neighborCol, rows, columns)) {
                    minNeighborDistance = Math.min(
                            minNeighborDistance,
                            cellHeights[neighborRow][neighborCol]
                    );
                }

                // Check the cell to the right
                neighborRow = row;
                neighborCol = col + 1;
                if (isValidCell(neighborRow, neighborCol, rows, columns)) {
                    minNeighborDistance = Math.min(
                            minNeighborDistance,
                            cellHeights[neighborRow][neighborCol]
                    );
                }

                // Set the current cell's height as the minimum of its neighbors + 1
                cellHeights[row][col] = Math.min(
                        cellHeights[row][col],
                        minNeighborDistance + 1
                );
            }
        }

        return cellHeights; // Return the calculated cell heights
    }

    // Function to check if a cell is within grid bounds
    private static boolean isValidCell(int row, int col, int rows, int columns) {
        return row >= 0 && col >= 0 && row < rows && col < columns;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
        //  + If isWater[i][j] == 0, cell (i, j) is (a land) cell.
        //  + If isWater[i][j] == 1, cell (i, j) is (a water) cell.
        //- You must assign (each cell) a height in a way that follows these rules:
        //  + (The height of each cell) must be non-negative.
        //  + If the cell is a water cell, (its height) must be 0.
        //  + Any (two adjacent cells) must have (an absolute height difference) of at most 1.
        //      + A cell is adjacent to another cell if the former is directly (north, east, south, or west) of the latter
        //      (i.e., their sides are touching).
        //- Find (an assignment of heights) such that (the maximum height) in the matrix is maximized.
        //* Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height.
        //  + If there are multiple solutions, return any of them.
        //- We need to find the way to fill in (the height) to the matrix to maximize (the max height of the matrix)
        //
        //Input: isWater =
        // [
        // [0,1],
        // [0,0]
        // ]
        //Output:
        // [
        // [1,0],
        // [2,1]
        // ]
        //Explanation: The image shows the assigned heights of each cell.
        //The blue cell is the water cell, and the green cells are the land cells.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == isWater.length
        //n == isWater[i].length
        //1 <= m, n <= 1000
        //isWater[i][j] is 0 or 1.
        //There is at least one water cell.
        //  + n,m<=1000 ==> Time: O(n*m)
        //
        //- Brainstorm
        //- We try to maximize the height
        //
        //
//        int[][] isWater = {{0,1},{0,0}};
//        int[][] isWater = {{0,0,1},{1,0,0},{0,0,0}};
        int[][] isWater = {
                {0,1},
                {0,1}
        };
        //=>
        //[[1,0],[1,0]]
        //
        //- We need to mark (the all of the water cells) as visited
        //  + It is used to avoid the traverse from (water cell) to (water cell) in (the first loop)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //2. Dynamic programming
        //2.0,
        //- In this approach, we build on the idea that
        //  + the height of (each cell) should be (the smallest distance) to (any water cell).
        //
        // From there, we observe that once we know the smallest distances of a cell’s neighboring cells,
        // calculating the distance for the current cell becomes straightforward
        //- it’s just the smallest of the neighbors’ distances plus one.
        //- The core idea is to use dynamic programming to compute these distances efficiently.
        //- Dynamic programming works well here because:
        //  + Each cell's height can be derived from the heights of its neighboring cells.
        //  + By iterating over the grid in a specific order, we can ensure that all necessary states are computed before being used.
        //- However, the challenge is figuring out the correct order to compute these states.
        // In DP terms, we need to ensure all necessary states are computed before using them.
        //
//        int[][] rs = highestPeak(isWater);
        int[][] rs = highestPeakReference(isWater);
        int n=rs.length;
        int m=rs[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
