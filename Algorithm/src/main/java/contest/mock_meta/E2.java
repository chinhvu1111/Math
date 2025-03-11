package contest.mock_meta;

import java.util.ArrayList;
import java.util.List;

public class E2 {

      static class BinaryMatrix {
      public int get(int row, int col) {
          int[][] mat = {
                  {0, 0}, {1,1}
          };
          return mat[row][col];
      }
      public List<Integer> dimensions(){
          List<Integer> size=new ArrayList<>();
          size.add(2);
          size.add(2);
          return size;
      }
  };

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int n=dimension.get(0);
        int m=dimension.get(1);
        int index=m-1;
        int finalRs=-1;

        for(int i=0;i<n;i++){
            int low=0, high=index;
            int rs=-1;
            while(low<=high){
                int mid= low+(high-low)/2;
                if(binaryMatrix.get(i, mid)==1){
                    rs=mid;
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            if(rs!=-1){
                index=rs;
                finalRs=rs;
            }
        }
        return finalRs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A row-sorted binary matrix) means that (all elements) are 0 or 1 and (each row of the matrix) is sorted in (non-decreasing order).
        //- Given (a row-sorted binary matrix binaryMatrix),
        //* return the index (0-indexed) of the leftmost column with a 1 in it.
        //  + If such an index does not exist, return -1.
        //- You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
        //  + BinaryMatrix.get(row, col)
        //      + returns the element of the matrix at index (row, col) (0-indexed).
        //  + BinaryMatrix.dimensions()
        //      + returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
        //- Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
        // Also, any solutions that attempt to circumvent the judge will result in disqualification.
        //- For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //rows == mat.length
        //cols == mat[i].length
        //1 <= rows, cols <= 100
        //mat[i][j] is either 0 or 1.
        //mat[i] is sorted in non-decreasing order.
        //
        //- Brainstorm
        //
        //0 0 1 1 ==> find 1 ==> index = 2
        //0 1 0 0 ==> find 1 in range (0,1)
        //0 0 0 1 ==> find 1 in range (0,1)
        //rs = 1
        System.out.println(leftMostColumnWithOne(new BinaryMatrix()));
    }
}
