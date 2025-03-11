package contest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E274_MaximumSumWithAtMostKElements {

    public static long maxSum(int[][] grid, int[] limits, int k) {
        PriorityQueue<Integer> decElements=new PriorityQueue<>();
        int n= grid.length;
        int m=grid[0].length;

        for(int i=0;i<n;i++){
            int[] g=grid[i];
            Arrays.sort(g);
            //4,3 limit =1
            for(int j=m-1;j>=0&&j>=m-limits[i];j--){
                decElements.add(g[j]);
                if(decElements.size()==k+1){
                    decElements.poll();
                }
            }
        }
        long rs=0;
        while (!decElements.isEmpty()){
            rs+=decElements.poll();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer matrix grid of size n x m, an integer array limits of length n, and an integer k.
        //- The task is to find the maximum sum of at most k elements from the matrix grid such that:
        //- The number of elements taken from the ith row of grid does not exceed limits[i].
        //* Return the maximum sum.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == grid.length == limits.length
        //m == grid[i].length
        //1 <= n, m <= 500
        //0 <= grid[i][j] <= 10^5
        //0 <= limits[i] <= m
        //0 <= k <= min(n * m, sum(limits))
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: grid = [[1,2],[3,4]], limits = [1,2], k = 2
        //
        //
        int[][] grid = {{1,2},{3,4}};
        int[] limits = {1,2};
        int k = 2;
        System.out.println(maxSum(grid, limits, k));
    }
}
