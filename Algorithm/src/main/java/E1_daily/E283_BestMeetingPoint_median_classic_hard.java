package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E283_BestMeetingPoint_median_classic_hard {

    public static int minTotalDistance(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        List<int[]> listHouses=new ArrayList<>();

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                if(grid[i][j]==1){
                    listHouses.add(new int[]{i, j});
                }
            }
        }
        listHouses.sort((o1, o2) -> o1[0] - o2[0]);
        int size=listHouses.size();
//        for (int i = 0; i < size; i++) {
//            System.out.printf("x:%s, y:%s\n", listHouses.get(i)[0], listHouses.get(i)[1]);
//        }
        int[] midX=listHouses.get(size/2);
        int x = midX[0];
        listHouses.sort((o1, o2) -> o1[1] - o2[1]);
        int[] midY=listHouses.get(size/2);
        int y = midY[1];
//        System.out.printf("x:%s, y:%s\n", x, y);
        
        int dist=0;

        for (int i = 0; i < size; i++) {
            dist+=Math.abs(listHouses.get(i)[0]-x) + Math.abs(listHouses.get(i)[1]-y);
        }
        return dist;
    }

    public static int minTotalDistanceRefer(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private static int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private static List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private static List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an m x n binary grid) where (each 1) marks (the home of (one) friend),
        //* Return (the ("minimal") total travel distance).
        //- (The total travel distance) is the (sum of the distances) between (the houses of the friends) and (the meeting point).
        //- The distance is calculated using (Manhattan Distance),
        //  + where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
        //* Meeting point can be:
        //  + 0 or 1
        //
        //Input: grid = [
        //  [1,0,0,0,1],
        //  [0,0,0,0,0],
        //  [0,0,1,0,0]
        //]
        //Output: 6
        //Explanation:
        //Given three friends living at (0,0), (0,4), and (2,2).
        //The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
        //So return 6.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 200
        //grid[i][j] is either 0 or 1.
        //There will be at least two friends in the grid.
        //  + m,n>=200 ==> Time: O(n^3)
        //
        //* Brainstorm:
        //- Find the distance from (any node) to (all of 1 nodes)
        //
        //Input: grid = [
        //  [1,0,(0),0,1],
        //  [0,0, 0,0,(0)],
        //  [0,0, 1,0, 0]
        //]
        //Output: 6
        //- Meeting point: [0,2], [1,4]
        //- List of nodes: [0,0], [0,4], [2,2]
        //- dist([0,2]) = 2 + 2 + 2 = 6
        //- dist([1,4]) = (4+1) + (1+2) + 1 = 9
        //  + dist([0,2],[1,4]) = (1-0) + (4-2) = 3
        //
        //- Solve the problem using 1 dimension
        //nums = [0,0,1,0,(0),0,1,0,0,1,0,0]
        //  + dist = 2 + 2 + 5 = 9
        //nums = [0,0,1,0,0,0,1,(0),0,1,0,0]
        //  + dist = 5 + 1 + 2 = 8
        //  + There is a (additional) distance between (index=6) and (index=7)
        //  ==> (index=6) ==> best position
        //
        //- That means we need to find by (x and y) axis (separately)
        //
        //nums = [0,0,1,0,0,0,1,0,0,1,0,(0),0,1,0,0,1,0,0]
        //  ==> Middle point is best point
        //p1: index1
        //p2: index2
        //p3: index3
        //p4: index4
        //p5: index5
        //meeting point: index6
        //  + dist = 3*x - index1 - index2 - index3 + index4 + index5 - 2*x
        //    dist = index4 + index5 + x - index1 - index2 - index3
        //    ==> MIN
        //    ==> x min but x>=index3
        //    ==> x== index3
        //  ==> Middle = (n+1)/2 sort by (x or y)
        //
        //- Find middle point
        //  + For to find the distance
        //
        //- We need to solve the array with 1 dimension first
        //
        //
        //- Brute force:
        //  + Time: O(n^2*m^2)
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Use (row and columns) (separately) rather than (sort list of nodes)
        //
        //1.3, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m+n*m*log(n*m))
        //
        int[][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(minTotalDistance(grid));
        System.out.println(minTotalDistanceRefer(grid));
        //
        //#Reference:
        //2387. Median of a Row Wise Sorted Matrix
        //3013. Divide an Array Into Subarrays With Minimum Cost II - HARD
        //3458. Select K Disjoint Special Substrings
    }
}
