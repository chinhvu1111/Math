package contest;

import java.util.HashMap;
import java.util.Map;

public class E262_FirstCompletelyPaintedRowOrColumn {

    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        HashMap<Integer, int[]> valToPos=new HashMap<>();
        //1 2
        //3 4
        //row = 0:
        //  + cell count = 2 ==> Completed
        int[] rowCountCol=new int[n];
        int[] colCountRow=new int[m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                valToPos.put(mat[i][j], new int[]{i, j});
            }
        }
        int l=arr.length;
        int index=-1;

        for(int i=0;i<l;i++){
            int[] curPos = valToPos.get(arr[i]);
            rowCountCol[curPos[0]]++;
            if(rowCountCol[curPos[0]]==m){
                index=i;
                break;
            }
            colCountRow[curPos[1]]++;
            if(colCountRow[curPos[1]]==n){
                index=i;
                break;
            }
        }
        return index;
    }

    public static int firstCompleteIndexReference(int[] arr, int[][] mat) {
        // Map to store the index of each number in the arr
        Map<Integer, Integer> numToIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            numToIndex.put(arr[i], i);
        }

        int result = Integer.MAX_VALUE;
        int numRows = mat.length;
        int numCols = mat[0].length;

        // Check for the earliest row to be completely painted
        for (int row = 0; row < numRows; row++) {
            // Tracks the greatest index in this row
            int lastElementIndex = Integer.MIN_VALUE;
            for (int col = 0; col < numCols; col++) {
                int indexVal = numToIndex.get(mat[row][col]);
                lastElementIndex = Math.max(lastElementIndex, indexVal);
            }
            // Update result with the minimum index where this row is fully painted
            result = Math.min(result, lastElementIndex);
        }

        // Check for the earliest column to be completely painted
        for (int col = 0; col < numCols; col++) {
            int lastElementIndex = Integer.MIN_VALUE;
            for (int row = 0; row < numRows; row++) {
                int indexVal = numToIndex.get(mat[row][col]);
                lastElementIndex = Math.max(lastElementIndex, indexVal);
            }
            // Update result with the minimum index where this column is fully painted
            result = Math.min(result, lastElementIndex);
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a (0-indexed integer array arr), and an m x n integer matrix mat.
        //- (arr and mat) both contain all the integers in the range [1, m * n].
        //- Go through (each index i) in arr starting from (index 0) and paint the cell in mat containing the integer arr[i].
        //* Return (the smallest index i) at which either (a row or a column) will be completely painted in mat.
        //  + Return (the index) such that we get the (a row or a column) is visited
        //
        //Example 1:
        //
        //image explanation for example 1
        //Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
        //Output: 2
        //Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == mat.length
        //n = mat[i].length
        //arr.length == m * n
        //1 <= m, n <= 10^5
        //1 <= m * n <= 10^5
        //1 <= arr[i], mat[r][c] <= m * n
        //All the integers of arr are unique.
        //All the integers of mat are unique.
        //  + m*n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- The number is unique
        //  + We can use the value to get the row and column
        //
        //1.1, Optimization
        //- Reference solution is slower
        //  + Map value to index
        //- For each row we traverse all of columns in this row:
        //  + Get max index of that
        //  ==> Get min index for all of rows
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        int[] arr = {1,3,4,2};
        int[][] mat = {{1,4},{2,3}};
        System.out.println(firstCompleteIndex(arr, mat));
        System.out.println(firstCompleteIndexReference(arr, mat));
    }
}
