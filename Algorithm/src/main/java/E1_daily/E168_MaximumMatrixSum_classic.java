package E1_daily;

public class E168_MaximumMatrixSum_classic {

    public static long maxMatrixSum(int[][] matrix) {
        long rs=0;
        int n=matrix.length;
        int m = matrix[0].length;
        int minVal=Integer.MAX_VALUE;
        int countNegativeValues = 0;

        for(int i=0;i<n;i++){
            int curCount = 0;
            for(int j=0;j<m;j++){
                int positiveVal = Math.abs(matrix[i][j]);
                if(matrix[i][j]<0){
//                    countNegativeValues++;
                    curCount++;
                }
                minVal=Math.min(minVal, positiveVal);
                rs+=positiveVal;
            }
            countNegativeValues+=curCount; // ==> Faster
        }
        if(countNegativeValues%2==0){
            return rs;
        }
        //We need to subtract the min value two times because:
        //  + sum = all(include b)
        //  + rs = (sum exclude(b)) - b
        //      + rs = sum - 2*b
        return rs-minVal* 2L;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an n x n integer matrix.
        //- You can do the following operation any number of times:
        //  + Choose (any two adjacent elements) of matrix (and multiply each of them) by -1.
        //- Two elements are considered adjacent if and only if they share (a border).
        //- Your goal is to maximize (the summation of the matrix's elements).
        //* Return (the maximum sum of the matrix's elements) using the operation mentioned above.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == matrix.length == matrix[i].length
        //2 <= n <= 250
        //-10^5 <= matrix[i][j] <= 10^5
        //  + n <= 250 ==> Time: O(n^2)
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: matrix =
        //[
        // [1,-1],
        // [-1,1]
        //]
        //Output: 4
        //Explanation: We can follow the following steps to reach sum equals 4:
        //- Multiply the 2 elements in the first row by -1.
        //- Multiply the 2 elements in the first column by -1.
        //- For each number, if we multiply it 2 times, it will become original value
        //
        //[
        //[2 1],
        //[1 0]
        //]
        //- Main point is that we need to realize we need try to get to the flip (row by row separately)
        //- How to recognize?
        //  + Max sum of the all of elements in the matrix with (the positive value)
        //  ==> Try to except for the (negative values)
        //Ex:
        //1,-2,3,-5
        //=> -1,2,-3,-5
        //=> -1,2,3,5
        //- Do we have any way that we can not flip them to have only one negative value?
        //Ex:
        //-1,2,3,4,-5,
        //=> 1,-2,3,-4,5
        //=> 1,2,3,4,5
        //- If we remove all of value except for the sign operator:
        //Ex:
        //signals = [-1,1,-1,1,-1]
        //+ We just count number of negative sign:
        //  + (2 negatives) sign can (cancel each other out)
        //- The number of sign:
        //  + Even: remaining sign = 0
        //  + Odd: remaining sign = 1
        //- Smaller problem:
        //  + Try to solve the problem with 1 dimension array
        //Ex:
        //nums = [-1,-2,-3]
        //- We only need to count the number of negative values
        //- We also choose the min(|abs|) to except
        //
        //* This is not true:
        //- We also can (flip colums)
        //
        //- Flip rows operation is not enough
        //* Main point:
        //- We need to apply flip for the whole matrix
        //  + We only need to flip:
        //      + 0 elements: The number of sign of the all of rows is ("even") number
        //      + 1 elements: The number of sign of the all of rows is ("odd") number
        //
        //- We need to subtract the min value two times because:
        //  + sum = all(include b)
        //  + rs = (sum exclude(b)) - b
        //      + rs = sum - 2*b
        //
        //1.1, Optimization
        //- We don't need to create temp count for each row
        //  + Create global count var.
        //  ==> Process slower after optimizing
        //- Operation between the bigger number will be slower:
        //  + Temp will be re-created = 0 for each loop
        //      + This operation is (faster) than doing this operation (in the global variable)
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*m)
        //
//        int[][] matrix = {{1,-1},{-1,1}};
        int[][] matrix = {{1,2,3},{-1,-2,-3},{1,2,3}};
        System.out.println(maxMatrixSum(matrix));
        //
        //#Reference:
        //879. Profitable Schemes
        //Delete Columns to Make Sorted II
        //2718. Sum of Matrix After Queries
        //2556. Disconnect Path in a Binary Matrix by at Most One Flip
        //955. Delete Columns to Make Sorted II
    }
}
