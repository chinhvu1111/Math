package E1_daily;

public class E56_FindValidMatrixGivenRowAndColumnSums {

    public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n=rowSum.length;
        int m=colSum.length;
        int[][] rs=new int[n][m];
        int[] curRowSum=new int[n];
        int[] curColSum=new int[m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                rs[i][j]=Math.min(rowSum[i]-curRowSum[i], colSum[j]-curColSum[j]);
                curRowSum[i]+=rs[i][j];
                curColSum[j]+=rs[i][j];
            }
        }
        return rs;
    }

    public static int[][] restoreMatrixOptimization(int[] rowSum, int[] colSum) {
        int n=rowSum.length;
        int m=colSum.length;
        int[][] rs=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                rs[i][j]=Math.min(rowSum[i], colSum[j]);
                rowSum[i]-=rs[i][j];
                colSum[j]-=rs[i][j];
                if(rowSum[i]<0){
                    rowSum[i]=0;
                }
                if(colSum[j]<0){
                    colSum[j]=0;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two arrays (rowSum) and (colSum) of (non-negative) integers where rowSum[i] is the sum of the elements in the (ith row)
        // and colSum[j] is the sum of the elements of the (jth column) of a 2D matrix.
        //- In other words, you do (not know) (the elements of the matrix), but you do know (the sums of each row and column).
        //* Find any matrix of (non-negative integers) of size (rowSum.length x colSum.length) that satisfies the rowSum and colSum requirements.
        //* Return a 2D array representing any matrix that fulfills the requirements.
        //- It's guaranteed that at least one matrix that fulfills the requirements exists.
        //* Return any matrix thoả mãn
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= rowSum.length, colSum.length <= 500
        //0 <= rowSum[i], colSum[i] <= 10^8
        //sum(rowSum) == sum(colSum)
        //+ Số khá lớn:
        //  + Nên không brute force được
        //
        //- Brainstorm
        //Example 2:
        //Input: rowSum = [5,7,10], colSum = [8,6,8]
        //Output: [[0,5,0],
        //         [6,1,0],
        //         [2,0,8]]
        //- matrix[i][j]:
        //  +
        //
        //1.The "why" behind the greedy approach:
        //The greedy method works because we're always filling in the smallest possible value that satisfies both the current row and column constraints. This ensures we're not "overspending" in any cell, which could lead to problems later.
        //
        //2.About the final number matching:
        //If we've correctly followed the constraints for all other cells, the final cell (last row, last column) will automatically have the correct value. This is because the sum of all row totals equals the sum of all column totals (given in the problem constraints).
        //
        //3.Proving there's no solution if the final number doesn't match:
        //If the final number doesn't match after following this greedy approach, it would mean that the sum of row totals and column totals weren't equal to begin with. This contradicts the problem's guarantee that a solution exists.
        //
        //4,The math behind it:
        //
        //- Let's say the total sum is S.
        //- As we fill each cell, we reduce both the corresponding row and column sums by that cell's value.
        //- The sum of all cells we've filled plus the remaining row and column sums always equals S.
        //- When we reach the last cell, we've accounted for everything except that cell in both the row and column sums.
        //- Therefore, the remaining values in the last row and last column must be equal, fitting perfectly in the last cell.
        //- Proof of correctness:
        //  + We never exceed row or column sums (we always take the minimum).
        //  + We always make progress (at least one sum is reduced to zero in each step).
        //  + The total sum is preserved throughout.
        //  + Therefore, we will eventually fill all cells while satisfying all constraints.
        //
        //- This greedy approach works because the problem has a specific structure that allows for a "locally optimal" choice
        // (filling the minimum possible value) to lead to a globally optimal solution.
        //  ==> Chứng minh cái này ntn?
        //  + Dùng ít --> Sau đó các giá trị sau sẽ tuỳ ý hơn ==> More optimal.
        //
        //- Đại loại là:
        //  + Ta sẽ điền mỗi vị trí
        //      + arr[i][j]<= rowSum[i]
        //      + arr[i][j]<= colSum[i]
        //  + Ta sẽ lấy min(rowSum[i] - curRowSum[i], colSum[j]-currentColSum[j])
        //  ==> Ta sẽ chọn theo greedy số mà nhỏ nhất ==> Không "overspending"
        //- currentRowSum[i]/ currentColSum[j]:
        //  + Chính là phần sum ta đã điền --> Mỗi lần lấy thì (phải trừ đi)
        //- Vì (sum row == sum col)
        //  ==> Đến cuối chúng phải bằng nhau và thoả mãn
        //- Key ở đây là filling càng nhỏ --> Lớn ==> Về sau càng optimal
        //
        //1.1, Optimization
        //- Thay đổi array đầu vào là được.
        //  + ==> Không recommend
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1253. Reconstruct a 2-Row Binary Matrix
        int[] rowSum = {5,7,10}, colSum = {8,6,8};
//        int[][] rs = restoreMatrix(rowSum, colSum);
        int[][] rs = restoreMatrixOptimization(rowSum, colSum);
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
