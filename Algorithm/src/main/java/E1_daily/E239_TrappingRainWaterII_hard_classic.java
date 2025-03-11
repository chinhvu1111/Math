package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E239_TrappingRainWaterII_hard_classic {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int trapRainWater(int[][] heightMap) {
        int n= heightMap.length;
        int m=heightMap[0].length;
        //Time: O(n*m*log(n*m))
        PriorityQueue<int[]> minBoundary=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        HashSet<Pair<Integer, Integer>> visited=new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0||i==n-1||j==0||j==m-1){
                    minBoundary.add(new int[]{i, j, heightMap[i][j]});
                    visited.add(new Pair<>(i, j));
                }
            }
        }
        int rs=0;

        //Time: O(n*m)
        while(!minBoundary.isEmpty()){
            int[] curVal=minBoundary.poll();
            Queue<int[]> queue=new LinkedList<>();
            queue.add(new int[]{curVal[0], curVal[1]});
            while (!queue.isEmpty()){
                int[] curCell = queue.poll();
                visited.add(new Pair<>(curCell[0], curCell[1]));
                for (int i = 0; i < dx.length; i++) {
                    int x1=curCell[0]+dx[i];
                    int y1=curCell[1]+dy[i];
                    Pair<Integer, Integer> curPair = new Pair<>(x1, y1);
//                    System.out.printf("%s %s\n", x1, y1);
                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited.contains(curPair)){
                        visited.add(curPair);
                        if(heightMap[x1][y1]<=curVal[2]){
                            rs+=curVal[2]-heightMap[x1][y1];
                            queue.add(new int[]{x1, y1});
                        }else{
                            //Update this cell to the boundary
                            minBoundary.add(new int[]{x1, y1, heightMap[x1][y1]});
                        }
                    }
                }
            }
        }
        return rs;
    }

    public static int trapRainWaterRefer(int[][] heightMap) {
        // Direction arrays
        int[] dRow = { 0, 0, -1, 1 };
        int[] dCol = { -1, 1, 0, 0 };

        int numOfRows = heightMap.length;
        int numOfCols = heightMap[0].length;

        boolean[][] visited = new boolean[numOfRows][numOfCols];

        // Priority queue (min-heap) to process boundary cells in increasing height order
        PriorityQueue<Cell> boundary = new PriorityQueue<>();

        // Add the first and last column cells to the boundary and mark them as visited
        for (int i = 0; i < numOfRows; i++) {
            boundary.offer(new Cell(heightMap[i][0], i, 0));
            boundary.offer(
                    new Cell(heightMap[i][numOfCols - 1], i, numOfCols - 1)
            );
            // Mark left and right boundary cells as visited
            visited[i][0] = visited[i][numOfCols - 1] = true;
        }

        // Add the first and last row cells to the boundary and mark them as visited
        for (int i = 0; i < numOfCols; i++) {
            boundary.offer(new Cell(heightMap[0][i], 0, i));
            boundary.offer(
                    new Cell(heightMap[numOfRows - 1][i], numOfRows - 1, i)
            );
            // Mark top and bottom boundary cells as visited
            visited[0][i] = visited[numOfRows - 1][i] = true;
        }

        // Initialize the total water volume to 0
        int totalWaterVolume = 0;

        // Process cells in the boundary (min-heap will always pop the smallest height)
        while (!boundary.isEmpty()) {
            // Pop the cell with the smallest height from the boundary
            Cell currentCell = boundary.poll();

            int currentRow = currentCell.row;
            int currentCol = currentCell.col;
            int minBoundaryHeight = currentCell.height;

            // Explore all 4 neighboring cells
            for (int direction = 0; direction < 4; direction++) {
                // Calculate the row and column of the neighbor
                int neighborRow = currentRow + dRow[direction];
                int neighborCol = currentCol + dCol[direction];

                // Check if the neighbor is within the grid bounds and not yet visited
                if (
                        isValidCell(
                                neighborRow,
                                neighborCol,
                                numOfRows,
                                numOfCols
                        ) &&
                                !visited[neighborRow][neighborCol]
                ) {
                    // Get the height of the neighbor cell
                    int neighborHeight = heightMap[neighborRow][neighborCol];

                    // If the neighbor's height is less than the current boundary height, water can be trapped
                    if (neighborHeight < minBoundaryHeight) {
                        // Add the trapped water volume
                        totalWaterVolume += minBoundaryHeight - neighborHeight;
                    }

                    // Push the neighbor into the boundary with updated height (to prevent water leakage)
                    boundary.offer(
                            new Cell(
                                    Math.max(neighborHeight, minBoundaryHeight),
                                    neighborRow,
                                    neighborCol
                            )
                    );
                    visited[neighborRow][neighborCol] = true;
                }
            }
        }

        // Return the total amount of trapped water
        return totalWaterVolume;
    }

    // Class to store the height and coordinates of a cell in the grid
    private static class Cell implements Comparable<Cell> {

        int height;
        int row;
        int col;

        // Constructor to initialize a cell
        public Cell(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }

        // Overload the compareTo method to make the priority queue a min-heap based on height
        @Override
        public int compareTo(Cell other) {
            // Min-heap comparison
            return Integer.compare(this.height, other.height);
        }
    }

    // Helper function to check if a cell is valid (within grid bounds)
    private static boolean isValidCell(
            int row,
            int col,
            int numOfRows,
            int numOfCols
    ) {
        return row >= 0 && col >= 0 && row < numOfRows && col < numOfCols;
    }

    public static int trapRainWaterUsingArrayAsVisited(int[][] heightMap) {
        int n= heightMap.length;
        int m=heightMap[0].length;
        PriorityQueue<int[]> minBoundary=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        boolean[][] visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0||i==n-1||j==0||j==m-1){
                    minBoundary.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j]=true;
                }
            }
        }
        int rs=0;

        while(!minBoundary.isEmpty()){
            int[] curVal=minBoundary.poll();
            Queue<int[]> queue=new LinkedList<>();
            queue.add(new int[]{curVal[0], curVal[1]});
            while (!queue.isEmpty()){
                int[] curCell = queue.poll();
                visited[curCell[0]][curCell[1]]=true;
                for (int i = 0; i < dx.length; i++) {
                    int x1=curCell[0]+dx[i];
                    int y1=curCell[1]+dy[i];
                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                        visited[x1][y1]=true;
                        if(heightMap[x1][y1]<=curVal[2]){
                            rs+=curVal[2]-heightMap[x1][y1];
                            queue.add(new int[]{x1, y1});
                        }else{
                            minBoundary.add(new int[]{x1, y1, heightMap[x1][y1]});
                        }
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an m x n integer matrix heightMap) representing (the height of each unit cell) in (a 2D elevation map),
        //* return (the volume of water) it can trap after raining.
        //
        //Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] // ==> (Height) of (each column) in order to create the moutain
        //Output: 4
        //Explanation: After the rain, water is trapped between the blocks.
        //We have two small ponds 1 and 3 units trapped.
        //The total volume of water trapped is 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == heightMap.length
        //n == heightMap[i].length
        //1 <= m, n <= 200
        //0 <= heightMap[i][j] <= 2 * 10^4
        //  + m,n<=200 ==> Time: O((n*m)^2)
        //
        //- Brainstorm
        //Ex
        //Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
        //- We have 1 unit water where:
        //  +   1
        //    1 0 1
        //      1
        //  +   1 1
        //    1 0 0 1
        //      1 1
        //- For the 1 dimension trapping water:
        //Ex:
        // 1
        // 1       1
        // 1     1 1
        // 1 1   1 1
        // 1 1   1 1
        // 1 1 1 1 1
        //=> For (each unit) in the column we will check to get (how much water has been kept)?
        //  + We also solve this by using the
        //- For ex:
        //nums = [3,2,1,4]
        //rs = 3
        //       1
        // 1 0 0 1
        // 1 1 0 1
        // 1 1 1 1
        //stack = [3]
        //stack = [3,2] (2< peek) ==> We can not get any cell
        //stack = [3,2,1]
        //stack = [3,2,1] + 4
        //  + 4>1
        //  + Use column with (4 unit) that we can get (the water cells) from the (previous elements)
        //      + Use 4 to pop elements from (1,2,3) until we face the cell having the value is bigger 4
        //      + How many = 3*3 - (1+2+3) = 3
        //          = max*length - sum(all of the previous values)
        //
        //For subproblem:
        //1 1 1 1 1 1
        //1 1 0 1 0 1
        //1 1 0 1 0 1
        //1 1 1 1 0 1
        //==> Find how many cell with (0 zero) that are covered by (1 value)?
        //
        //- Evaluate time complexity:
        //  + Time: O(n*m*2*10^4) = O(200*200*2*10^4) = O(400000000)
        //  ==> TLE
        //
        //- We should not to traverse (row by row)
        //  + Let's traverse (column by column)
        //
        //- It is better if we visualize the input as the 2 dimension array:
        //Ex:
        //[
        // [1,4,3,1,3,2],
        // [3,2,1,3,2,4],
        // [2,3,3,2,3,1]
        //]
        //- BFS?
        //- From boundary how do we calculate the water cells?
        //- Let take the cell with min height that is in the boundary
        //- From this cell:
        //7 7 7 7
        //7 1 1 7
        //7 7 7 7
        //+ rs=(7-1)*2
        //7 7 7 8 7
        //7 1 9 6 7
        //7 8 7 9 7
        //+ rs=(7-1)+(7-6)
        //
        //7 8 8 8 7
        //8 1 9 8 7
        //7 8 7 9 7
        //+ rs = 8-1
        //
        //Ex:
        // 1
        // 1       1 ==> Get this
        // 1     1 1
        // 1 1   1 1
        // 1 1   1 1
        // 1 1 1 1 1
        // B ==> Boundary
        //- We see that we map (the lower column) to (higher column) to get the water cells
        //- We also have:
        //Ex:
        //
        //           1
        // 1         1   1
        // 1       1 1   1
        // 1     1 1 1   1
        // 1 1   1 1 1   1
        // 1 1   1 1 1 1 1
        // 1 1 1 1 1 1 1 1
        // x x x x x ==> Visited
        //x 7
        //x 6 7
        //x x x
        //  ==> 6 is less than all of cells in the boundary
        //- From boundary, from the cell with min height:
        //  + We get the water cell if they are covered by the boundary
        //4 4 4 4
        //4 1 3 4
        //4 4 4 4
        //* Main point:
        //  + We will get (4-3) because 3 is covered by at least the boundary that (We have been get the min height)
        //  + We go to it's neighborhood to accumulate the value
        //      + If we see any cell with bigger value
        //      ==> Include this cell to (the boundary)
        //- Use BFS to get all of water cells
        //
        //* Code:
        //- Min heap as the boundary
        //- BFS for traversal the neighborhood of each cell is in the boundary
        //
        //1.1, Optimization
        //- Use the visited as the array rather than hashset<pair>
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m*log(n*m))
        //
        //#Reference:
        //2503. Maximum Number of Points From Grid Queries
        //
//        int[][] heightMap = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        int[][] heightMap = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        System.out.println(trapRainWater(heightMap));
        System.out.println(trapRainWaterUsingArrayAsVisited(heightMap));
        System.out.println(trapRainWaterRefer(heightMap));
        //
    }
}
