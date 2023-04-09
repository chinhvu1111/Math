package mock;

public class Test_4_meta {
    public int[][] matrix;
    public int[][] dp1;

    public Test_4_meta(int[][] matrix) {
        this.matrix=matrix;
        int n=matrix.length;
        int m=matrix[0].length;
        int [][] dp=new int[n][m];
        dp1=new int[n][m];

        for(int i=0;i<m;i++){
            int sum=0;

            for(int j=0;j<n;j++){
                sum+=matrix[j][i];
                dp[j][i]=sum;
            }
        }
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=0;j<m;j++){
                sum+=dp[i][j];
                dp1[i][j]=sum;
            }
        }
    }

    //** Đề bài:
    //- Cần trả về sum trong khoảng 2 points
    //+ (x, y), (x1, y1)
    //1.
    //1.1,
    //- Dữ kiện:
    //+ 1 <= m, n <= 200
    //+ -10^4 <= matrix[i][j] <= 10^4
    //+ 10^4 calls
    //- Idea:
    //VD:
    //2, 3, 5
    //(2),0, 1
    // 1, 0, 1
    // 0, 3,(0)
    //
    //- Ta được cho 2 điểm thực chất là ta có 6 điểm:
    //+ 4 điểm suy được
    //+ 2 điểm nếu chiếu xuống biên: (x1, y), (x1, y1) ==> Tổng chữ nhất giữa 2 điểm này
    //(x, y), (x1, y1) --> (x,y), (x, y1)
    //2 (x, y), 3, 5 (x, y1)
    //(4),      0,      1
    // 5,       0,      1
    // 5(x1,y), 3,      (7), (x1, y1)
    //
    //2, 3, 5
    //(4),3, 6
    // 5, 3, 7
    // 5, 6,(7)
    //==>
    //2, 5, 10
    //(4),7, 13
    // 5, 8, 15
    // 5, 11,(18)
    //
    //- Đầu tiên cần tìm sum (left --> right)
    //- Sau đó tính tiếp (up --> bottom) ==> ra được khoảng cách chữ nhất giữa 2 điểm [x, y]
    //
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int x,y;
        int x1,y1;

        if(row1<row2){
            x=row1;
            y=col1;
            x1=row2;
            y1=col2;
        }else{
            x=row1;
            y=col1;
            x1=row2;
            y1=col2;
        }
        int leftVal=0;
        int leftVal1=0;
        int rightVal=0;
        if(y>0){
            if(x>=1){
                leftVal=dp1[x-1][y-1];
            }
            leftVal1=dp1[x1][y-1];
        }
        if(x>=1){
            rightVal=dp1[x-1][y1];
        }
        int r1=rightVal-leftVal;
        int r2=dp1[x1][y1]-leftVal1;

        return r2-r1;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]
                {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        Test_4_meta test4Meta=new Test_4_meta(arr);
        System.out.println(test4Meta.sumRegion(2,1,4,3));
        //#Reference:
        //305. Number of Islands II
        //303. Range Sum Query - Immutable
        //308. Range Sum Query 2D - Mutable
    }
}
