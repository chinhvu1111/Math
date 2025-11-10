package E1_leetcode_medium_dynamic;

public class E182_NumberOfWaysOfCuttingAPizza {

    public static int ways(String[] pizza, int k) {
        int n=pizza.length;
        int m=pizza[0].length();
        int[][][] dp=new int[k][n][m];
        int[][] apples=new int[n+1][m+1];

        for(int i=n-1;i>=0;i--){
            for (int j = m-1; j >=0 ; j--) {
                apples[i][j]=((pizza[i].charAt(j)=='A')?1:0)+apples[i+1][j]+apples[i][j+1]-apples[i+1][j+1];
                dp[0][i][j]=apples[i][j]>0?1:0;
            }
        }
        int mod=1_000_000_007;

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    for (int nextRow = j+1; nextRow < n; nextRow++) {
                        if(apples[j][l]-apples[nextRow][l]>0){
                            dp[i][j][l]+=dp[i-1][nextRow][l];
                            dp[i][j][l]%=mod;
                        }
                    }
                    for (int nextCol = l+1; nextCol < m; nextCol++) {
                        if(apples[j][l]-apples[j][nextCol]>0){
                            dp[i][j][l]+=dp[i-1][j][nextCol];
                            dp[i][j][l]%=mod;
                        }
                    }
                }
            }
        }
        return dp[k-1][0][0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a rectangular pizza) represented as (a rows x cols matrix) containing the following characters:
        //  + 'A' (an apple) and '.' (empty cell)
        //- and given (the integer k).
        //- You have to cut the pizza into (k pieces) using (k-1 cuts).
        //- For each cut you choose the direction:
        //  + vertical or horizontal,
        // then you choose (a cut position at) (the cell boundary) and cut the pizza into (two pieces).
        //- If you cut the pizza (vertically), give (the left part of the pizza) to a person.
        //- If you cut the pizza (horizontally), give (the upper part of the pizza) to a person.
        //  + Give (the last piece of pizza) to (the last person).
        //* Return (the number of ways of cutting) the pizza such that (each piece) contains (at least one) apple.
        //- Since the answer can be a huge number, return this modulo 10^9 + 7.
        //
        //Ex:
        //Input: pizza = ["A..","AAA","..."], k = 3
        //Output: 3
        //Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= rows, cols <= 50
        //rows == pizza.length
        //cols == pizza[i].length
        //1 <= k <= 10
        //pizza consists of characters 'A' and '.' only.
        //  + rows, cols <= 50 ==> Time: O(n^3)
        //
        //* Brainstorm:
        //
        //Input: pizza = [
        //  "A..",
        //  "AAA",
        //  "..."
        //],
        // k = 3
        //Output: 3
        //Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
        //- Top down approach is valid or not?
        //
        //  "A..",
        //  "AAA",
        //  "..."
        //
        //- After cutting, how to save the intermediate result?
        //  "A..",
        //  ------
        //  "AAA",
        //  "..."
        //==> We just need to keep
        //  + Top left point, bottom right point
        //  ==> memo[i][j]
        //- We also need to memo[i][j][k]
        //- Sub-problem:
        //  + We need to check:
        //  in the rectangle:
        //      (i,j),(i1,j1) ==> Have apple or not
        //
        //- Bottom right node:
        //  ==> This node is (n-1,m-1)
        //  + Because we only remove (top/left)
        //  ==> Right is always kept
        //- Keep dp[i][j][k]
        //- Formula:
        //  + dp[i][j][k] = dp[i-l][j][k-1]
        //  ==> sum all of values
        //  ==> dp[i][j][k] += dp[i-1][j][k-1]
        //
        //pizza:
        //  [
        //  "A..",
        //  "AAA",
        //  "..."
        //  ]
        //[
        // [[1,1,1],[1,1,1],[0,0,0]],
        // [[0,0,0],[0,0,0],[0,0,0]],
        // [[0,0,0],[0,0,0],[0,0,0]]]
        //apples=
        //[
        // [4,2,1,0],
        // [3,2,1,0],
        // [0,0,0,0],
        // [0,0,0,0]]
        //==> apples[i][j] is the number of apple from ([n-1][m-1] ==> [i][j])
        //
        //* Exp:
        //- We just start with rectangle with (("non-zero") number of apple)
        //  + For each cut ==> If it is valid => add (new way) from (the previous one)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Space: O(n*m*k)
        //- Time: O(n*m*k*(n+m))
        //
//        String[] pizza = {"A..","AAA","..."};
//        int k = 3;
        String[] pizza = {"A..","A..","..."};
        int k = 1;
        System.out.println(ways(pizza, k));
    }
}
