package E1_PrefixSum;

public class E9_LargestMagicSquare {

    public static boolean isValid(int[][] prefixSumCol, int[][] prefixSumRow, int[][]grid, int i, int j, int k, int n, int m){
        int sum = prefixSumCol[i+1][j+1+k] - prefixSumCol[i+1][j];
//        System.out.println(sum);
        //(i+1+l, j),(i+1, j+1),(i+1+l,j+k+1)
        for(int l=2;l<=k;l++){
            if(prefixSumCol[i+1+l][j+k+1]-prefixSumCol[i+1+l][j]!=sum){
//                System.out.println("Col1 false");
                return false;
            }
        }
        //(i,j+l+1)
        //(i+1,j+l+1)
        //(i+1+k,j+1+l)
        for(int l=1;l<=k;l++){
            if(prefixSumRow[i+1+k][j+1+l]-prefixSumRow[i][j+1+l]!=sum){
//                System.out.printf("Col2 false %s\n", prefixSumRow[i+1+l][j+1]-prefixSumRow[i][j+1]);
                return false;
            }
        }
        int diagonalSum=0;
        for(int l=0;l<=k;l++){
            diagonalSum+=grid[i+l][j+l];
        }
        if(diagonalSum!=sum){
//            System.out.println("Diagonal1 false");
            return false;
        }
        diagonalSum=0;
        for(int l=0;l<=k;l++){
            diagonalSum+=grid[i+k-l][j+l];
        }
        if(diagonalSum!=sum){
//            System.out.println("Diagonal2 false");
            return false;
        }
//        System.out.printf("valid %s\n", k);
        return true;
    }

    public static int largestMagicSquare(int[][] grid) {
        int n= grid.length;
        int m=grid[0].length;
        int[][] prefixSumCol=new int[n+1][m+1];
        int[][] prefixSumRow=new int[n+1][m+1];

        //Time: O(n*m)
        for(int i=1;i<=n;i++){
            int sum=0;
            for(int j=1;j<=m;j++){
                sum+=grid[i-1][j-1];
                prefixSumCol[i][j]=sum;
            }
        }
        //Time: O(n*m)
        for(int i=1;i<=m;i++){
            int sum=0;
            for(int j=1;j<=n;j++){
                sum+=grid[j-1][i-1];
                prefixSumRow[j][i]=sum;
            }
        }
        int rs=1;

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(m)
            for(int j=0;j<m;j++){
                int len = Math.min(n-i-1, m-j-1);
                //Time: O(min(n,m))
                for(int k=len;k>=1;k--){
//                    System.out.printf("%s %s %s\n", i, j, k);
                    //Time: O(min(n,m))
                    if(isValid(prefixSumCol, prefixSumRow, grid, i, j, k, n, m)){
                        rs=Math.max(k+1, rs);
                        break;
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A k x k (magic square) is a k x k grid filled with integers such that:
        //  + every (row sum), every (column sum), and both (diagonal sums) are all equal.
        //  + The integers in the magic square (do not have to be distinct).
        //- Every 1 x 1 grid is trivially a magic square.
        //- Given an m x n integer grid,
        //* Return the size (i.e., the side length k) of the largest magic square
        // that can be found within this grid.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 50
        //1 <= grid[i][j] <= 106
        //==> Size không lớn ==> O(N^3)
        //
        //- Brainstorm
        //Ex:
        //Input: grid =
        //[
        // [7,1,4,5,6],
        // [2,5,1,6,4],
        // [1,5,4,3,2],
        // [1,2,7,3,4]
        //]
        //[
        //5,1,6
        //5,4,3
        //2,7,3
        //]
        //Output: 3
        //
        //- Để check 1 maxtrix có phải magic square hay không?
        //Ex:
        //a,b,c
        //d,e,f
        //g,h,k
        //==> Có vẻ không có rule gì đặc biệt
        //- Cần check all sum row, sum col, sum diagonal
        //
        //- Bài này làm prefix sum cho col, row
        //- Col:
        //  + (i+1+l, j),(i+1, j+1),(i+1+l,j+k+1)
        //- Row:
        //  + (i,j+l+1)
        //  + (i+1,j+l+1)
        //  + (i+1+k,j+1+l)
        //- Cần check all square + khoảng cách diagonal
        //
//        int[][] grid = {
//                {7,1,4,5,6},
//                {2,5,1,6,4},
//                {1,5,4,3,2},
//                {1,2,7,3,4}};
        int[][] grid =
                {
                        {8,1,6},
                        {3,5,7},
                        {4,9,2},
                        {7,10,9}
                };
        System.out.println(largestMagicSquare(grid));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m*min(n,m)^2)
        //
        //#Reference:
        //840. Magic Squares In Grid
    }
}
