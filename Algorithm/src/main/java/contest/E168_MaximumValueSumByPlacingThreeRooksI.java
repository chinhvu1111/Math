package contest;

import java.util.Arrays;

public class E168_MaximumValueSumByPlacingThreeRooksI {

    public static long[][] memo;

    public static void placeRooks(int[][] board, boolean[] rows, boolean[] cols, int rookCount, long currentSum, int start, long[] maxSum, long[] maxCol) {
        if (rookCount == 3) {
            maxSum[0] = Math.max(maxSum[0], currentSum);
            return;
        }
//        if(memo[start][rookCount]!=Long.MIN_VALUE){
//
//        }

        int m = board.length;
        int n = board[0].length;

        for (int i = start; i < m * n; i++) {
            int row = i / n;
            int col = i % n;

            if(i+1<n*m&&rookCount==2&&maxCol[i+1]<0&&currentSum<maxSum[0]){
                continue;
            }
            if (!rows[row] && !cols[col]) {
                rows[row] = true;
                cols[col] = true;

                placeRooks(board, rows, cols, rookCount + 1, currentSum + board[row][col], i + 1, maxSum, maxCol);

                rows[row] = false;
                cols[col] = false;
            }
        }
    }

    public static long maximumValueSum(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        long[] maxSum = new long[1];
        Arrays.fill(maxSum, Long.MIN_VALUE);
//        memo=new long[n*m][3];
//        for(int i =0;i<n*m;i++){
//            Arrays.fill(memo[i], Long.MIN_VALUE);
//        }
        long[] maxCol=new long[n*m];
        long max=Long.MIN_VALUE;
        for(int i=n*m-1;i>=0;i--){
            int row = i / n;
            int col = i % n;
            max=Math.max(board[row][col], max);
            maxCol[i]=max;
        }
        placeRooks(board, rows, cols, 0, 0, 0, maxSum, maxCol);
        return maxSum[0];
    }

    public static long maximumValueSum1(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Precompute maximums for each row and column
        long[] maxRow = new long[m];
        long[] maxCol = new long[n];

        for (int i = 0; i < m; i++) {
            maxRow[i] = Long.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                maxRow[i] = Math.max(maxRow[i], board[i][j]);
            }
        }

        for (int j = 0; j < n; j++) {
            maxCol[j] = Long.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                maxCol[j] = Math.max(maxCol[j], board[i][j]);
            }
        }

        long maxSum = Long.MIN_VALUE;

        // Try every combination of three distinct rows and columns
        for (int r1 = 0; r1 < m; r1++) {
            for (int r2 = r1 + 1; r2 < m; r2++) {
                for (int r3 = r2 + 1; r3 < m; r3++) {
                    for (int c1 = 0; c1 < n; c1++) {
                        for (int c2 = c1 + 1; c2 < n; c2++) {
                            for (int c3 = c2 + 1; c3 < n; c3++) {
                                long sum = (long)board[r1][c1] + (long)board[r2][c2] + (long)board[r3][c3];
                                maxSum = Math.max(maxSum, sum);
                            }
                        }
                    }
                }
            }
        }

        return maxSum;
    }

    public static long maxRooksSum(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        long[][][] dp = new long[4][1 << m][1 << n];

        for(int i=1;i<=3;i++){
            for(int j=0;j<dp[i].length;j++){
                Arrays.fill(dp[i][j], Long.MIN_VALUE);
            }
        }
        for (int k = 1; k <= 3; k++) {
            for (int rowMask = 0; rowMask < (1 << m); rowMask++) {
                for (int colMask = 0; colMask < (1 << n); colMask++) {
//                    dp[k][rowMask][colMask] = Long.MIN_VALUE;
                    for (int i = 0; i < m; i++) {
                        if ((rowMask & (1 << i)) != 0) continue;
                        for (int j = 0; j < n; j++) {
                            if ((colMask & (1 << j)) != 0) continue;
                            int newRowMask = rowMask | (1 << i);
                            int newColMask = colMask | (1 << j);
                            if(dp[k - 1][rowMask][colMask]==Long.MIN_VALUE){
                                continue;
                            }
                            dp[k][newRowMask][newColMask] = Math.max(dp[k][newRowMask][newColMask],
                                    dp[k - 1][rowMask][colMask] + board[i][j]);
//                            System.out.println(dp[k][newRowMask][newColMask]);
                        }
                    }
                }
            }
        }

        long maxSum = Long.MIN_VALUE;
        for (int rowMask = 0; rowMask < (1 << m); rowMask++) {
            for (int colMask = 0; colMask < (1 << n); colMask++) {
                maxSum = Math.max(maxSum, dp[3][rowMask][colMask]);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a m x n 2D array board representing a chessboard, where board[i][j] represents the value of the cell (i, j).
        //- Rooks in the same row or column attack each other.
        // You need to place three rooks on the chessboard such that the rooks do not attack each other
        //* Return the maximum sum of the cell values on which the rooks are placed.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= m == board.length <= 100
        //3 <= n == board[i].length <= 100
        //-10^9 <= board[i][j] <= 10^9
        //==> length nhỏ
        //  + O(n^3)/ O(n^2*k)
        //
        //** Brainstorm
        //- Dùng recursion được không
        //
        //
//        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] board = {{-3,1,1,1},{-3,1,-3,1},{-3,2,1,1}};
//        int[][] board = {{-53,-86,-80},{-28,16,-42},{-88,38,-66}};
//        int[][] board = {{-254181552,909083515,168998817},{405927678,422106940,441895162},{860220524,-558157613,929273732}};
//        int[][] board = {
//                {-28,-58,50,67,68,-6,35,67,-73,-21},
//                {-89,65,-24,42,-64,-58,-62,29,31,-3},
//                {-53,34,-37,48,8,-74,-66,52,27,19},
//                {94,60,-97,-5,-38,-89,-89,-69,-31,74},
//                {-56,2,65,-94,11,-95,30,39,77,-18},
//                {-23,77,-7,-36,-92,41,-15,-75,42,-67},
//                {65,-58,79,34,43,-76,78,90,-70,94},
//                {-97,-39,-100,62,-71,-58,-71,-16,96,86},
//                {63,-71,-3,-53,83,2,93,94,41,-54},
//                {-80,-45,-49,-35,-100,-52,81,6,79,90}};
        int[][] board= {{-24,-3,-64},{13,-8,-74},{23,65,-99}};
        System.out.println(maximumValueSum(board));
        System.out.println(maximumValueSum1(board));
        System.out.println(maxRooksSum(board));
    }
}
