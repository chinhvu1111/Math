package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E55_LuckyNumbersInAMatrix {

    public static List<Integer> luckyNumbers (int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[] minRow=new int[n];
        int[] maxCol=new int[m];

        for(int i=0;i<n;i++){
            minRow[i]=Integer.MAX_VALUE;
            for(int j=0;j<m;j++){
                minRow[i]=Math.min(minRow[i], matrix[i][j]);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                maxCol[i]=Math.max(maxCol[i], matrix[j][i]);
            }
        }
        List<Integer> rs=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==minRow[i]&&matrix[i][j]==maxCol[j]){
                    rs.add(matrix[i][j]);
                }
            }
        }
        return rs;
    }

    public static List<Integer> luckyNumbersOptimization(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int rMinMax= Integer.MAX_VALUE;
        int colMaxMin= Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            int minRow=Integer.MAX_VALUE;
            for(int j=0;j<m;j++){
                minRow=Math.min(minRow, matrix[i][j]);
            }
            rMinMax=Math.max(minRow, rMinMax);
        }
        for(int i=0;i<m;i++){
            int maxCol=0;
            for(int j=0;j<n;j++){
                maxCol=Math.max(maxCol, matrix[j][i]);
            }
            colMaxMin=Math.min(maxCol, colMaxMin);
        }
        List<Integer> rs=new ArrayList<>();

        if(colMaxMin==rMinMax){
            new ArrayList<>(colMaxMin);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n matrix of distinct numbers,
        //* Return (all lucky numbers) in the matrix in any order.
        //- A lucky number is an element of the matrix such that
        //  + it is (the minimum element) in (its row) and (maximum) in (its column).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == mat.length
        //n == mat[i].length
        //1 <= n, m <= 50
        //1 <= matrix[i][j] <= 10^5.
        //All elements in the matrix are distinct.
        //
        //- Brainstorm
        //Example 1:
        //Input: matrix =
        // [
        //  [3,7,8],
        //  [9,11,13],
        //  [15,16,17]
        //]
        //Output: [15]
        //Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
        //
        //1.1, Optimization
        //- Ta có thể giảm space --> O(1)
        //- Min row == max col
        //- Ta có thể chứng minh chỉ có 1 matrix[i][j] là beautiful
        //
        //Ex:
        //1   2   3
        //8   (4) 9 ==> Chỉ có thể 1 element
        //15  3  10
        //Ex:
        //a  b   c
        //d (e)  f
        //g  h  (k)
        //- Giả sử e thoả mãn:
        //+ d >= e
        //+ f >= e
        //+ e >= h
        //- Giả sử k thoả mãn:
        //+ g >= k
        //+ h >= k
        //+ k >= f
        //- e>=h, mà h>=k, k>=f
        //  ==> e>=k, k>=f
        //  ==> e >= f ==> (Sai)
        //- Chỉ tồn tại 1 điểm thôi.
        //
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        int[][] matrix =
                {
                        {3,7,8},
                        {9,11,13},
                        {15,16,17}
                };
        System.out.println(luckyNumbers(matrix));
        System.out.println(luckyNumbersOptimization(matrix));
        //#Reference
        //1288. Remove Covered Intervals
        //804. Unique Morse Code Words
        //741. Cherry Pickup
    }
}
