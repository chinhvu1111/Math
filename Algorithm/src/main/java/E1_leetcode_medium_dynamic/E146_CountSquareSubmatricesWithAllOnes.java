package E1_leetcode_medium_dynamic;

public class E146_CountSquareSubmatricesWithAllOnes {

    public static int countSquares(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==1){
                    dp[i][j]=1;
                    rs++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==0){
                    continue;
                }
                int top=i==0?0:dp[i-1][j];
                int left=j==0?0:dp[i][j-1];
                int topLeft=(i!=0)&&(j!=0)?dp[i-1][j-1]:0;
                dp[i][j]=Math.min(top, Math.min(left, topLeft))+1;
                rs+=dp[i][j]-1;
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                System.out.printf("%s,", dp[i][j]);
//            }
//            System.out.println();
//        }
        return rs;
    }

    public static int countSquaresOptimization(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]!=0&&i>0&&j>0){
                    matrix[i][j]=Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1]))+1;
                }
                rs+=matrix[i][j];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a m * n matrix of ones and zeros,
        //* Return (how many square submatrices) have (all ones).
        //
        //Example 1:
        //
        //Input: matrix =
        //[
        //  [0,1,1,1],
        //  [1,1,1,1],
        //  [0,1,1,1]
        //]
        //Output: 15
        //Explanation:
        //There are 10 squares of side 1.
        //There are 4 squares of side 2.
        //There is  1 square of side 3.
        //Total number of squares = 10 + 4 + 1 = 15.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= arr.length <= 300
        //1 <= arr[0].length <= 300
        //0 <= arr[i][j] <= 1
        //--> Size cũng không quá lớn.
        //
        //- Brainstorm
        //- Dạng này có vẻ làm rồi:
        //- dp[i][j] = Min(dp[i][j-1], dp[i-1][j])+1 ==> WRONG
        //==> Thiếu
        //Ex:
        //Example 1:
        //
        //Input: matrix =
        //[
        //  [0,1,0,0],
        //  [1,1,0,0],
        //  [0,0,0,0]
        //]
        //-->
        //Điền ntn:
        //
        //Input: matrix =
        //[
        //  [0,1,0,0],
        //  [1,2,0,0],
        //  [0,0,0,0]
        //]
        //==> Thực ra không tồn tại matrix có size=2
        //
        //* CT:
        //- dp[i][j] = Min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])+1
        //
        //Example 1:
        //
        //Input: matrix =
        //[
        //  [0,1,1,1],
        //  [1,1,1,1],
        //  [0,1,1,1]
        //]
        //Input: matrix =
        //[
        //  [0,1,1,1],
        //  [1,1,2,2],
        //  [0,1,2,3]
        //- Tính số square ntn?
        //+ Tại matrix[i][j] có thể có size square = x
        //  + Số square tối đa có thể tảo từ matrix[i][j] <=> [i,j] là góc) = dp[i][j]-1.
        //
        //1.1, Optimization
        //- Ta có thể tối ưu space from O(n^2) -> O(1)
        //- Không cần lưu lại dp ==> Change matrix trực tiếp luôn.
        //Ex:
        //Input: matrix =
        //[
        //  [0,1,1,1],
        //  [1,1,1,1],
        //  [0,1,1,1]
        //]
        //* CT:
        //+ matrix[i][j]=Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1]))+1;
        //+ if else sao cho luôn rs+=matrix[i][j], chỉ rs+matrix[i][j] changed khi matrix[i][j] changed trong if else condition.
        //
        //1.2, Complexity
        //- Before optimizing
        //  + Space : O(n^2)
        //  + Time : O(n^2)
        //- Optimization
        //  + Space : O(1)
        //  + Time : O(n^2)
        //
        //#Reference:
        //2087. Minimum Cost Homecoming of a Robot in a Grid
        //2088. Count Fertile Pyramids in a Land
        //
        int[][] matrix =
                {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(countSquares(matrix));
        System.out.println(countSquaresOptimization(matrix));
    }
}
