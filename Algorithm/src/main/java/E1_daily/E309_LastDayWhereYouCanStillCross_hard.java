package E1_daily;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class E309_LastDayWhereYouCanStillCross_hard {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static boolean pathExists(int row, int col, int[][] cells, int index){
        Queue<int[]> queue=new LinkedList<>();
        HashSet<Pair<Integer, Integer>> visited=new HashSet<>();

        for(int i=0;i<=index;i++){
            visited.add(new Pair<>(cells[i][0]-1, cells[i][1]-1));
        }
//        System.out.printf("Index: %s\n", index);
//        System.out.println(visited);
        for (int i = 0; i < col; i++) {
            Pair<Integer, Integer> curNode=new Pair<>(0, i);
            if(visited.contains(curNode)){
               continue;
            }
            queue.add(new int[]{0, i});
        }

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            visited.add(new Pair<>(curNode[0], curNode[1]));
            for (int i = 0; i < dx.length; i++) {
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];
                Pair<Integer, Integer> nextNode=new Pair<>(x1, y1);

                if(x1>=0&&y1>=0&&x1<row&&y1<col&&!visited.contains(nextNode)){
                    visited.add(nextNode);
                    queue.add(new int[]{x1, y1});
                    if(x1==row-1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static int latestDayToCross(int row, int col, int[][] cells) {
        int low=0, high=cells.length-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            boolean isValid = pathExists(row, col, cells, mid);
//            System.out.printf("%s %s\n", isValid, mid);
            if(isValid){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs+1;
    }

    private static int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static boolean canCross(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                queue.offer(new int[]{0, i});
                grid[0][i] = -1;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            if (r == row - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];
                if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = -1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return false;
    }

    public static int latestDayToCrossRefer(int row, int col, int[][] cells) {
        int left = 1;
        int right = row * col;

        while (left < right) {
            int mid = right - (right - left) / 2;
            if (canCross(row, col, cells, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    static class DSU {
        int[] root, size;

        public DSU(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (size[rootX] > size[rootY]) {
                int tmp = rootX;
                rootX = rootY;
                rootY = tmp;
            }
            root[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }

    public static int latestDayToCrossUnionFind(int row, int col, int[][] cells) {
        DSU dsu = new DSU(row * col + 2);
        int[][] grid = new int[row][col];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = cells.length - 1; i >= 0; i--) {
            int r = cells[i][0] - 1, c = cells[i][1] - 1;
            grid[r][c] = 1;
            int index1 = r * col + c + 1;
            for (int[] d : directions) {
                int newR = r + d[0], newC = c + d[1];
                int index2 = newR * col + newC + 1;
                if (newR >= 0 && newR < row && newC >= 0 && newC < col && grid[newR][newC] == 1) {
                    dsu.union(index1, index2);
                }
            }
            if (r == 0) {
                dsu.union(0, index1);
            }
            if (r == row - 1) {
                dsu.union(row * col + 1, index1);
            }
            if (dsu.find(0) == dsu.find(row * col + 1)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (a 1-based binary matrix) where 0 represents land and 1 represents water.
        //- You are given integers (row and col) representing (the number of rows and columns) in the matrix, respectively.
        //- Initially on (day 0), the entire matrix is ("land").
        // However, each day a new cell becomes flooded with water.
        // You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day,
        // the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
        //- You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells.
        //- You can start from any cell in the top row and end at any cell in the bottom row.
        //- You can only travel in the four cardinal directions (left, right, up, and down).
        //
        //* Return (the last day) where it is possible to walk from (the "top" to the "bottom") by only walking (on land cells).
        //  + Tức là return (day) mà ta có thể đi từ (top row -> bottom row)
        //
        //Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
        //Output: 2
        //Explanation: The above image depicts how the matrix changes each day starting from day 0.
        //The last day where it is possible to cross from top to bottom is on day 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= row, col <= 2 * 10^4
        //4 <= row * col <= 2 * 10^4
        //cells.length == row * col
        //1 <= ri <= row
        //1 <= ci <= col
        //All the values of cells are unique.
        //  + row, col <= 2 * 10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Input: row = 3, col = 3,
        // cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
        //Output: 3
        //Explanation: The above image depicts how the matrix changes each day starting from day 0.
        //The last day where it is possible to cross from top to bottom is on day 3.
        //
        //- Binary search + BFS
        //
        //1.1, Case
        //
        //
        //* Main point:
        //
        //
        //1.2, Optimization
        //- Union find
        //
        //1.3, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m*log(cell_length))
        //
        //# Reference:
        //803. Bricks Falling When Hit - HARD
        //2258. Escape the Spreading Fire - HARD
        int row = 3, col = 3;
        int[][] cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
        System.out.println(latestDayToCross(row, col, cells));
        System.out.println(latestDayToCrossRefer(row, col, cells));
        System.out.println(latestDayToCrossUnionFind(row, col, cells));
    }
}
