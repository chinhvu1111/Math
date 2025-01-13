package contest;

import java.util.Arrays;

public class E255_MaximumAmountOfMoneyRobotCanEarn {

    public static int[][][] memo;

    public static int solution(int x, int y, int numPrevent, int[][] coins, int n, int m) {
//        if(x==n||y==m){
//            return Integer.MIN_VALUE;
//        }
        boolean isPrevent = coins[x][y] < 0 && numPrevent > 0;
//        int dir=isPrevent?1:0;
        if (memo[x][y][numPrevent] != Integer.MIN_VALUE) {
            return memo[x][y][numPrevent];
        }
        int curRs = Integer.MIN_VALUE;

        if (isPrevent) {
            if (x + 1 < n) {
                curRs = solution(x + 1, y, numPrevent - 1, coins, n, m);
            }
            if (y + 1 < m) {
                curRs = Math.max(curRs, solution(x, y + 1, numPrevent - 1, coins, n, m));
            }
            if(curRs==Integer.MIN_VALUE){
                curRs=0;
            }
        }
        int curRsWithoutPreventing = Integer.MIN_VALUE;
        if (x + 1 < n) {
            curRsWithoutPreventing = solution(x + 1, y, numPrevent, coins, n, m);
        }
        if (y + 1 < m) {
            curRsWithoutPreventing = Math.max(curRsWithoutPreventing, solution(x, y + 1, numPrevent, coins, n, m));
        }
        if(curRsWithoutPreventing==Integer.MIN_VALUE){
            curRsWithoutPreventing=0;
        }
        curRsWithoutPreventing += coins[x][y];
//        System.out.printf("%s %s %s, %s\n", x, y, numPrevent, Math.max(curRs, curRsWithoutPreventing));
        return memo[x][y][numPrevent] = Math.max(curRs, curRsWithoutPreventing);
    }

    public static int maximumAmount(int[][] coins) {
        int n = coins.length;
        int m = coins[0].length;
        memo = new int[n][m][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }
        return solution(0, 0, 2, coins, n, m);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n grid. A robot starts at (the top-left corner of the grid (0, 0)) and
        // wants to reach (the bottom-right corner (m - 1, n - 1)).
        //- The robot can move either (right or down) at (any point in time).
        //- The grid contains a value coins[i][j] in each cell:
        //  + If coins[i][j] >= 0, (the robot gains that many coins).
        //  + If coins[i][j] < 0, (the robot encounters a robber), and (the robber steals the absolute value of coins[i][j] coins).
        //- The robot has a special ability to (neutralize robbers) in (at most 2 cells) on its path,
        //  preventing them from stealing coins in those cells.
        //* Note: The robot's total coins can be negative.
        //* Return the maximum profit the robot can gain on the route.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == coins.length
        //n == coins[i].length
        //1 <= m, n <= 500
        //-1000 <= coins[i][j] <= 1000
        //
        //- Brainstorm
        //- Recursion + memo
        //
//        int[][] coins = {{0,1,-1},{1,-2,3},{2,-3,4}};
//        int[][] coins = {{10,10,10},{10,10,10}};
        int[][] coins = {{10},{10}};
        //[[-3,-10,11,-16],[-13,19,-2,2],[-16,-11,5,13],[-5,13,-20,-6]]
//        int[][] coins = {{-10},{-1}};
//        int[][] coins = {
//                {-3, -10, 11, -16},
//                {-13, 19, -2, 2},
//                {-16, -11, 5, 13},
//                {-5, 13, -20, -6}};
        //output: 35
        //rs: 32
        System.out.println(maximumAmount(coins));
    }
}
