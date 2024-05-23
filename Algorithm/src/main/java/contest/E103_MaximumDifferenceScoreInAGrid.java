package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E103_MaximumDifferenceScoreInAGrid {

    public static int solution(int[][] dp, int x, int y, int n, int m, List<List<Integer>> grid){
        if(dp[x][y]!=Integer.MIN_VALUE){
            return dp[x][y];
        }
        int curRs=Integer.MIN_VALUE;

        for(int i=x+1;i<n;i++){
            int consumedVal=grid.get(i).get(y)-grid.get(x).get(y);
            int nextVal = solution(dp, i, y, n, m, grid);
            nextVal=nextVal==Integer.MIN_VALUE?0:nextVal;
            curRs=Math.max(nextVal+consumedVal, curRs);
        }
        for(int i=y+1;i<m;i++){
            int consumedVal=grid.get(x).get(i)-grid.get(x).get(y);
            int nextVal = solution(dp, x, i, n, m, grid);
            nextVal=nextVal==Integer.MIN_VALUE?0:nextVal;
            curRs=Math.max(nextVal+consumedVal, curRs);
        }
        return dp[x][y]=curRs;
    }

    public static int maxScore(List<List<Integer>> grid) {
        int n= grid.size();
        int m= grid.get(0).size();
        int[][] dp=new int[n][m];
        int[][] min=new int[n][m];
        int[][] minSecond=new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        solution(dp, 0, 0, n, m, grid);
        int rs=Integer.MIN_VALUE;

        //Remove điểm không thể traverse ít nhất 1 lần
        //i>=n-1 && j>=n-1 ==> ignore
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                if(i>=n-1&&j>=n-1){
//                    continue;
//                }
//                rs=Math.max(rs, dp[i][j]);
//            }
//        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                min[i][j]=dp[i][j];
                minSecond[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1&&j==m-2){
                    continue;
                }
                if(i==n-2&&j==m-1){
                    continue;
                }
                if(i+1==n-1&&j+1==m-1){
                    minSecond[i][j]=0;
                    continue;
                }
                int curMin=Integer.MAX_VALUE;
                if(i+1<n){
                    curMin=Math.min(curMin, min[i+1][j]);
                }
                if(j+1<m){
                    curMin=Math.min(curMin, min[i][j+1]);
                }
                minSecond[i][j]=curMin;
                min[i][j]=Math.min(curMin, dp[i][j]);
//                System.out.printf("i: %s, j: %s, %s\n", i, j , min[i][j]);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(minSecond[i][j]!=Integer.MAX_VALUE){
                    rs=Math.max(dp[i][j]-minSecond[i][j], rs);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an m x n matrix grid consisting of (positive) integers.
        //- You can move from a cell in the matrix to any other cell that is either to the (bottom) or to the (right) (not necessarily adjacent).
        //- The score of a move from a cell with the (value c1) to a cell with the (value c2) is (c2 - c1).
        //- You can (start at any cell), and you have to make (at least one move).
        //* Return the (maximum total score) you can achieve.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //m == grid.length
        //n == grid[i].length
        //2 <= m, n <= 1000
        //4 <= m * n <= 10^5
        //1 <= grid[i][j] <= 10^5
        //  ==> Total of size <= 10^5 : O(n) được.
        //
        //- Brainstorm
        //Ex:
        //Input: grid =
        // [
        // [9,5,7,3],
        // [8,9,6,1],
        // [6,7,14,3],
        // [2,5,3,1]
        // ]
        //
        //Output: 9
        //
        //Explanation: We start at the cell (0, 1), and we perform the following moves:
        //- Move from the cell (0, 1) to (2, 1) with a score of 7 - 5 = 2.
        //- Move from the cell (2, 1) to (2, 2) with a score of 14 - 7 = 7.
        //The total score is 2 + 7 = 9.
        //
        //- Bài này có thể làm top down được.
        //
        //- Vấn đề là chỉ cần traverse 1 lần là đủ
        //  ==> Đứng [i][j] ta có thể traverse (1 -> n-i+n-j) lần.
//        int[][] grid = {
//                {9,5,7,3},
//                {8,9,6,1},
//                {6,7,14,3},
//                {2,5,3,1}};
//        int[][] grid = {{4,3,2},{3,2,1}};
//        int[][] grid = {{6,5,1},{5,7,9},{6,7,4},{6,10,5}};
        int[][] grid = {
                {4,3},
                {2,3}
        };
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            List<Integer> curList=new ArrayList<>();
            for(int j=0;j<grid[i].length;j++){
                curList.add(grid[i][j]);
            }
            list.add(curList);
        }
        System.out.println();
        System.out.println(maxScore(list));
    }
}
