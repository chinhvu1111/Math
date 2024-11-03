package E1_PrefixSum;

public class E12_MaxSumOfRectangleNoLargerThanK_classic {

    public static int sumRec(int i, int j, int i1, int j1, int[][] dp){
        return dp[i1][j1] - dp[i-1][j1] - dp[i1][j-1] + dp[i-1][j-1];
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int n=matrix.length;
        int m = matrix[0].length;
        int[][] prefixSum=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i-1][j-1];
            }
        }

        int rs=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                int[] prefixSumRec=new int[m+1];
                for(int h=1;h<=m;h++){
                    prefixSumRec[h]=sumRec(i, 1, j, h, prefixSum);
                }
                for(int h=1;h<=m;h++){
                    for(int l=h-1;l>=0;l--){
                        if(prefixSumRec[h]-prefixSumRec[l]<=k){
                            rs=Math.max(rs, prefixSumRec[h]-prefixSumRec[l]);
                        }
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n matrix and an integer k,
        //* return (the max sum of a rectangle) in the matrix such that its sum is (no larger) than (k).
        //- It is guaranteed that there will be (a rectangle) with a sum (no larger) than k.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == matrix.length
        //n == matrix[i].length
        //1 <= m, n <= 100
        //-100 <= matrix[i][j] <= 100
        //-10^5 <= k <= 10^5
        //  + n<=100, Time: O(n^m)
        //
        //- Brainstorm
        //
        //Input:
        // matrix =
        // [
        // [1,0,1],
        // [0,-2,3]
        // ],
        //k = 2
        //Output: 2
        //Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
        //
//        int[][] matrix = {{1,0,1},{0,-2,3}};
//        int k = 2;
//        int[][] matrix = {{2,2,-1}};
//        int k = 3;
        int[][] matrix = {{2,2,-1}};
        int k = 0;
        System.out.println(maxSumSubmatrix(matrix, k));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O(n^2*m^2)
        //- Space: O(n*m)
        //
        //#Reference:
        //2971. Find Polygon With the Largest Perimeter
        //1418. Display Table of Food Orders in a Restaurant
        //1125. Smallest Sufficient Team
    }
}
