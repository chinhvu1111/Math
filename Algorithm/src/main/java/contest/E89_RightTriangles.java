package contest;

public class E89_RightTriangles {

    public static long numberOfRightTriangles(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        long[][] dpNumRight=new long[n][m];
        long[][] dpNumLeft=new long[n][m];
        long[][] dpNumTop=new long[n][m];
        long[][] dpNumDown=new long[n][m];
        for(int i=0;i<n;i++){
            long countOne=0;
            for(int j=0;j<m;j++){
                dpNumRight[i][j]=countOne;
                if(grid[i][j]==1){
                    countOne++;
                }
            }
        }
        for(int i=0;i<n;i++){
            long countOne=0;
            for(int j=m-1;j>=0;j--){
                dpNumLeft[i][j]=countOne;
                if(grid[i][j]==1){
                    countOne++;
                }
            }
        }
        for(int i=0;i<m;i++){
            long countOne=0;
            for(int j=0;j<n;j++){
                dpNumTop[j][i]=countOne;
                if(grid[j][i]==1){
                    countOne++;
                }
            }
        }
        for(int i=0;i<m;i++){
            long countOne=0;
            for(int j=n-1;j>=0;j--){
                dpNumDown[j][i]=countOne;
                if(grid[j][i]==1){
                    countOne++;
                }
            }
        }
        //#4
        //(i,j) -> (i,j-1)
        //(i,j) -> (i,j-1)
        //  1
        //1 1
        long rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=1){
                    continue;
                }
                rs+=dpNumRight[i][j]*dpNumDown[i][j];
                rs+=dpNumLeft[i][j]*dpNumDown[i][j];
                rs+=dpNumTop[i][j]*dpNumRight[i][j];
                rs+=dpNumTop[i][j]*dpNumLeft[i][j];
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-You are given a 2D boolean matrix grid.
        //* Return an integer that is the number of right triangles that can be made with the 3 elements of grid
        // such that all of them have (a value of 1).
        //- Note:
        //A collection of 3 elements of grid is a right triangle if one of its elements is in
        // the same row with another element and in the same column with the third element.
        // The 3 elements do not have to be next to each other.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= grid.length <= 1000
        //1 <= grid[i].length <= 1000
        //0 <= grid[i][j] <= 1
        //==> Số cũng k quá lớn
        //
        //- Brainstorm
        //Ex:
        //grid =
        // [[1,0,1],
        //  [1,0,1],
        //  [1,0,0]]
        //- 1 đỉnh có thể tạo nhiều triangles
        //  + [0,1] có thể gộp với
        //   + ([0,0],[0,2])
        //   + ([2,0],[1,2])
        //- Đếm ntn?
        //- Đếm từ trên xuống dưới theo 1 chiếu có:
        // + Thiếu / thừa không?
        //Ex:
        //grid =
        // [[1,0,1],
        //  [1,0,1],
        //  [1,0,1]]
        //- Đếm triangle với dạng:
        //#1
        //(i,j) -> (i,j+1)
        //(i,j) -> (i+1,j)
        //1 1
        //1
        //#2
        //(i,j) -> (i,j-1)
        //(i,j) -> (i+1,j)
        //1 1
        //  1
        //#3
        //(i,j) -> (i,j+1)
        //(i,j) -> (i-1,j)
        //1
        //1 1
        //#4
        //(i,j) -> (i,j-1)
        //(i,j) -> (i,j-1)
        //  1
        //1 1
        //
        //- Chia tiếp với mỗi đỉnh là góc
        //- Làm prefix sum
//        int[][] grid = {
//                {1,0,1},
//                {1,0,0},
//                {1,0,0}};
        int[][] grid = {
                {1,0,1},
                {1,0,1},
                {1,0,1}};
        System.out.println(numberOfRightTriangles(grid));
    }
}
