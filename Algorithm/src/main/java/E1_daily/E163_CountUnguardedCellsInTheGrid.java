package E1_daily;

import java.util.HashSet;

import javafx.util.Pair;

public class E163_CountUnguardedCellsInTheGrid {

    //   0
    //3    1
    //  2
    //(3+2)%4
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static class Node {
        int x;
        int y;
        int direction;

        public Node(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

//        @Override
//        public int hashCode() {
//            return (x +" "+ y +" "+ direction).hashCode();
//        }
    }

    public static int countUnguarded(int n, int m, int[][] guards, int[][] walls) {
        HashSet<Pair<Integer, Integer>> visitedCell = new HashSet<>();
        HashSet<Pair<Integer, Integer>> wallSet = new HashSet<>();
        HashSet<Pair<Integer, Integer>> guardSet = new HashSet<>();
        HashSet<Node> visitedCellWithDirection = new HashSet<>();

        for (int[] w : walls) {
            wallSet.add(new Pair<>(w[0], w[1]));
        }
        for (int[] g : guards) {
            guardSet.add(new Pair<>(g[0], g[1]));
        }
        //Time: O(g)
        for (int[] g : guards) {
            //Time: O(n+m)
//            System.out.printf("Start: x:%s, y:%s\n", g[0], g[1]);
            for (int i = 0; i < dx.length; i++) {
                int x = g[0] + dx[i];
                int y = g[1] + dy[i];
                Pair<Integer, Integer> nextPair = new Pair<>(x, y);
                Node curNode = new Node(x, y, i);
                Node reversedNode = new Node(x, y, (i + 2) % 4);

                while (x >= 0 && x < n && y >= 0 && y < m && !wallSet.contains(nextPair) && !guardSet.contains(nextPair) && (!visitedCellWithDirection.contains(curNode) && !visitedCellWithDirection.contains(reversedNode))) {
                    visitedCellWithDirection.add(curNode);
                    visitedCellWithDirection.add(reversedNode);
                    visitedCell.add(nextPair);
//                    System.out.printf("x:%s, y:%s\n", x, y);
//                    if(visitedCellWithDirection.contains(curNode)||visitedCellWithDirection.contains(reversedNode)){
//                        break;
//                    }
                    x = x + dx[i];
                    y = y + dy[i];
                    curNode = new Node(x, y, i);
                    reversedNode = new Node(x, y, (i + 2) % 4);
                    nextPair = new Pair<>(x, y);
                }
            }
        }
        return n * m - walls.length - guards.length - visitedCell.size();
    }

    private static final int UNGUARDED = 0;
    private static final int GUARDED = 1;
    private static final int GUARD = 2;
    private static final int WALL = 3;

    public static void markguarded(int row, int col, int[][] grid) {
        // Traverse upwards
        for (int r = row - 1; r >= 0; r--) {
            if (grid[r][col] == WALL || grid[r][col] == GUARD) break;
            grid[r][col] = GUARDED;
        }
        // Traverse downwards
        for (int r = row + 1; r < grid.length; r++) {
            if (grid[r][col] == WALL || grid[r][col] == GUARD) break;
            grid[r][col] = GUARDED;
        }
        // Traverse leftwards
        for (int c = col - 1; c >= 0; c--) {
            if (grid[row][c] == WALL || grid[row][c] == GUARD) break;
            grid[row][c] = GUARDED;
        }
        // Traverse rightwards
        for (int c = col + 1; c < grid[0].length; c++) {
            if (grid[row][c] == WALL || grid[row][c] == GUARD) break;
            grid[row][c] = GUARDED;
        }
    }

    public static int countUnguardedIterativeSimulation(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // Mark guards' positions
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = GUARD;
        }

        // Mark walls' positions
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = WALL;
        }

        // Mark cells as guarded by traversing from each guard
        for (int[] guard : guards) {
            markguarded(guard[0], guard[1], grid);
        }

        // Count unguarded cells
        int count = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == UNGUARDED) count++;
            }
        }
        return count;
    }

    public static int countUnguardedRecursiveWithOptimization(int m, int n, int[][] guards, int[][] walls) {
        // Create and initialize grid
        int[][] grid = new int[m][n];

        // Mark guards' positions
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = GUARD;
        }

        // Mark walls' positions
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = WALL;
        }

        // Horizontal passes
        for (int row = 0; row < m; row++) {
            // Left to right pass
            boolean isGuardLineActive = grid[row][0] == GUARD;
            for (int col = 1; col < n; col++) {
                isGuardLineActive = updateCellVisibility(
                        grid,
                        row,
                        col,
                        isGuardLineActive
                );
            }

            // Right to left pass
            isGuardLineActive = grid[row][n - 1] == GUARD;
            for (int col = n - 2; col >= 0; col--) {
                isGuardLineActive = updateCellVisibility(
                        grid,
                        row,
                        col,
                        isGuardLineActive
                );
            }
        }

        // Vertical passes
        for (int col = 0; col < n; col++) {
            // Top to bottom pass
            boolean isGuardLineActive = grid[0][col] == GUARD;
            for (int row = 1; row < m; row++) {
                isGuardLineActive = updateCellVisibility(
                        grid,
                        row,
                        col,
                        isGuardLineActive
                );
            }

            // Bottom to top pass
            isGuardLineActive = grid[m - 1][col] == GUARD;
            for (int row = m - 2; row >= 0; row--) {
                isGuardLineActive = updateCellVisibility(
                        grid,
                        row,
                        col,
                        isGuardLineActive
                );
            }
        }

        // Count unguarded cells
        int count = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == UNGUARDED) {
                    count++;
                }
            }
        }

        return count;
    }

    // Helper method to update cell visibility
    private static boolean updateCellVisibility(
            int[][] grid,
            int row,
            int col,
            boolean isGuardLineActive
    ) {
        // If current cell is a guard, reactivate the guard line
        if (grid[row][col] == GUARD) {
            return true;
        }

        // If current cell is a wall, deactivate the guard line
        if (grid[row][col] == WALL) {
            return false;
        }

        // If guard line is active, mark cell as guarded
        if (isGuardLineActive) {
            grid[row][col] = GUARDED;
        }

        return isGuardLineActive;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two integers m and n) representing (a 0-indexed m x n grid).
        //- You are also given two 2D integer arrays (guards and walls)
        //  + where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the (ith guard and jth wall) respectively.
        //- A guard can see every cell in the (four cardinal directions) (north, east, south, or west)
        // starting from their position unless obstructed by (a wall) or (another guard).
        //- A cell is guarded if there is (at least one guard) that can see it.
        //* Return (the number of (unoccupied cells)) that are (not guarded).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= m, n <= 10^5
        //2 <= m * n <= 10^5
        //1 <= guards.length, walls.length <= 5 * 10^4
        //2 <= guards.length + walls.length <= m * n
        //guards[i].length == walls[j].length == 2
        //0 <= rowi, rowj < m
        //0 <= coli, colj < n
        //All the positions in guards and walls are unique.
        //  + Length<= 10^4 ==> O(10^4)
        //  + m*n<=10^5 ==> one dimension array
        //
        //- Brainstorm
        //- If we check by scanning wall by wall:
        //  + We can scan (all of the cells) that will be protected by (any guard)
        //
        //Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
        //Output: 7
        //Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
        //There are a total of 7 unguarded cells, so we return 7.
        //
        //G W V X V V
        //X G X X W V
        //X X W G X X
        //X X V X V V
        //- If we use the visited with 2 dimension, The memory will be exceeded
        //- We can use the visited with 1 dimension
        //- We loop all of the guards to cover all of cell
        //  + If we see the cell with the same direction or in the reversion direction
        //  ==> We skip to move next cell
        //- Use the hashset<Pair<Integer, Integer>>
        //- Use node(x,y,i) and Pair<Integer, Integer>
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(m*n+g*(n+m)+m+n)
        //
        //2.
        //2.0,
        //- We use the grid as the marker includes:
        //  + UNGUARDED = 0
        //  + GUARDED = 1
        //  + GUARD = 2
        //  + WALL = 3
        //- For each guard:
        //  + We traverse 4 direction using the 4 loops:
        //      + Traverse upwards
        //      + Traverse downwards
        //      + Traverse leftwards
        //      + Traverse rightwards
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n*m)
        //- Time: O(g*(n+m)+n*m)
        //
        //3. Grid with markers
        //- We have the same idea above
        //
//        int m = 4, n = 6;
//        int[][] guards = {{0,0},{1,1},{2,3}}, walls = {{0,1},{2,2},{1,4}};
        int m = 2, n = 7;
        int[][] guards = {{1, 5}, {1, 1}, {1, 6}, {0, 2}}, walls = {{0, 6}, {0, 3}, {0, 5}};
        System.out.println(countUnguarded(m, n, guards, walls));
        System.out.println(countUnguardedIterativeSimulation(m, n, guards, walls));
        System.out.println(countUnguardedRecursiveWithOptimization(m, n, guards, walls));
        //
        //#Reference:
        //361. Bomb Enemy
        //999. Available Captures for Rook
    }
}
