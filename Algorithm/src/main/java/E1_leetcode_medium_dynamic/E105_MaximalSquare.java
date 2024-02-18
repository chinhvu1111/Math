package E1_leetcode_medium_dynamic;

public class E105_MaximalSquare {

    public static int maximalSquareWrong(char[][] matrix) {
        int n= matrix.length;
        int m= matrix[0].length;

        int[][] dp=new int[n][m];

        for(int i=0;i<n;i++){
            int curDepth=0;

            for(int j=m-1;j>=0;j--){
                if(matrix[i][j]=='0'){
                    curDepth=0;
                }else{
                    curDepth++;
                }
                dp[i][j]=curDepth;
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                System.out.printf("%s,", dp[i][j]);
//            }
//            System.out.println();
//        }
        int rs=0;

        for(int i=0;i<m;i++){
            int curDepth=0;
            int curMin=Integer.MAX_VALUE;

            for(int j=n-1;j>=0;j--){
                if(matrix[j][i]=='0'){
                    curDepth=0;
                    curMin=Integer.MAX_VALUE;
                }else{
                    curDepth++;
                    curMin=Math.min(dp[j][i], curMin);
                }
                dp[j][i]=Math.min(dp[j][i], Math.min(curDepth, curMin));
                rs=Math.max(rs, dp[j][i]);
            }
        }
        return rs*rs;
    }

    public static int maximalSquare(char[][] matrix) {
        int n= matrix.length;
        int m= matrix[0].length;

        int[][] dp=new int[n+1][m+1];
        int rs=0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(matrix[i-1][j-1]=='0'){
                    continue;
                }
                dp[i][j]=Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]))+1;
                rs=Math.max(dp[i][j], rs);
            }
        }
        return rs*rs;
    }

    public static int maximalSquareRefactor(char[][] matrix) {
        int n= matrix.length;
        int m= matrix[0].length;

        int[][] dp=new int[n][m];
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='0'){
                    continue;
                }
                if(i==0||j==0){
                    dp[i][j]=1;
                }else{
                    dp[i][j]=Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]))+1;
                }
                rs=Math.max(dp[i][j], rs);
            }
        }
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<m;j++){
        //         System.out.printf("%s,", dp[i][j]);
        //     }
        //     System.out.println();
        // }
        return rs*rs;
    }

    public static int maximalSquareSpaceOptimization(char[][] matrix) {
        int n= matrix.length;
        int m= matrix[0].length;

        int[] prevDp=new int[m+1];
        int[] curDp=new int[m+1];
        int rs=0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(matrix[i-1][j-1]=='0'){
                    continue;
                }
                curDp[j]=Math.min(curDp[j-1], Math.min(prevDp[j], prevDp[j-1]))+1;
                rs=Math.max(rs, curDp[j]);
            }
            for(int j=1;j<=m;j++){
                //Assign new value for the prev array
                prevDp[j]=curDp[j];
                //Reset
                curDp[j]=0;
            }
        }
        return rs*rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an m x n binary matrix filled with 0's and 1's,
        //* Find the largest square containing only 1's and return its area.
        //- Tìm max size square (hình vuông) trong matrix
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //m == matrix.length
        //n == matrix[i].length
        //1 <= m, n <= 300
        //matrix[i][j] is '0' or '1'
        //
        //- Brainstorm
        //Ex:
        //matrix =
        // [
        // ["1","0","1","0","0"],
        // ["1","0","1","1","1"],
        // ["1","1","1","1","1"],
        // ["1","0","0","1","0"]
        // ]
        //- Size của square là min depth của (i,j) từ:
        //+ Right
        //+ Down
        //- Mỗi (i,j) có 2 directions:
        //  + Right
        //  + Down
        //==> Một node(i,j) tính theo 2 node bên right or down.
        //
        //Ex:
        //matrix =
        // [
        // ["1","0","1","0","0"],
        // ["1","0","1","1","1"],
        // ["1","1","1","1","1"],
        // ["1","0","0","1","0"]
        // ]
        //dp =
        // [
        // ["1","0","1","0","0"],
        // ["1","0","3","2","1"],
        // ["5","4","3","2","1"],
        // ["1","0","0","1","0"]
        // ]
        //Ex
        //matrix =
        // [
        // ["4","0","3","0","0"],
        // ["3","0","2","3","2"],
        // ["2","1","1","2","1"],
        // ["1","0","0","1","0"]
        // ]
        //- Special cases:
        //Ex:
        //
        //{'1','1','1','1','1','1','1','1'},
        //{'1','1','1','1','1','1','1','0'},
        //{'1','1','1','1','1','1','1','0'},
        //{'1','1','1','1','1','0','0','0'},
        //{'0','1','1','1','1','0','0','0'}
        //
        //- Ở đây right và down không đủ để đánh giá square
        //{'1','5','4','3','2','1','1','1'},
        //{'1','4','1','1','1','1','1','0'},
        //{'1','3','1','1','1','1','1','0'},
        //{'1','2','1','1','1','0','0','0'},
        //{'0','1','1','1','1','0','0','0'}
        //
        //{'8','7','6','5','4','3','2','1'},
        //{'7','6','5','4','3','2','1','0'},
        //{'7','6','5','4','3','2','1','0'},
        //{'5','4','3','2','1','0','0','0'},
        //{'0','4','3','2','1','0','0','0'}
        //
        //[
        // ["0","0","1","0"],
        // ["1","1","1","1"],
        // ["1","1","1","1"],
        // ["1","1","1","0"],
        // ["1","1","0","0"],
        // ["1","1","1","1"],
        // ["1","1","1","0"]
        // ]
        //
        //[
        // ["0","0","1","0"],
        // ["4","3","2","1"],
        // ["4","3","2","1"],
        // ["3","2","1","0"],
        // ["2","1","0","0"],
        // ["4","3","2","1"],
        // ["3","2","1","0"]
        // ]
        //Ex:
        //4,3,3,3,2 ==> return 2
        //4,3,3,3,3,2 ==> return 3
        //- Tìm số min nhất trong window size=4
        //Ex:
        //4,3,3,3,3,2
        // window size=4, min val=3
        //==> cái này khá khó để solve trong O(n)
        //
        //[
        // ["0","0","1","0"],
        // ["1","1","1","1"],
        // ["1","1","1","1"],
        // ["1","1","1","0"],
        // ["1","1","0","0"],
        // ["1","1","1","1"],
        // ["1","1","1","0"]
        // ]
        //
        //Ex:
        //[0,1]
        //[1,1]
        //- Map ra value:
        //[0,1]
        //[1,1] ( 1 = min(0,1,1) + 1 )
        //* Solution:
        //- dp[i][j] lưu size của square toàn 1 mà ta có thể tìm thấy trong are (0,0) --> (i,j)
        //- Để (i,j) có thể tăng size thì các điểm:
        //+ (i-1,j)
        //+ (i,j-1)
        //+ (i-1,j-1)
        //==> Cần phải đều cho square như nhau tức là:
        //+ dp[i-1][j]==dp[i][j-1]==dp[i-1][j-1] ==> Tức là dp[i][j]= Min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
        //
        //- Bình thường để đỡ if else thì ta sẽ tính dp[i][j] <-> matrix[i-1][j-1]
        //+ Bài này hoàn toàn ta có thể refactor lại:
        //  + Gán các i==0||j==0: dp[i][j]=1
        //  + Còn phần mệnh đề dp[i][j] giữ nguyên
        //
        //1.2, Optimization
        //
        //Ex:
        //[0,1,1]
        //[1,1,0]
        //[1,1,1]
        //- Ta thấy là (i,j) tính theo (i-1,j-1), (i-1, j), (i, j-1)
        //==> Space ta chỉ cần lưu row trước đó + row hiện tại thôi.
        //
        //1.3, Complexity
        //- Space :
        //  + Pre-optimization : O(n*m)
        //- Time :
        //  + Pre-optimization : O(n*m)
        //
//        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix =
                {
                        {'1','1','1','1','1','1','1','1'},
                        {'1','1','1','1','1','1','1','0'},
                        {'1','1','1','1','1','1','1','0'},
                        {'1','1','1','1','1','0','0','0'},
                        {'0','1','1','1','1','0','0','0'}
                };
        System.out.println(maximalSquare(matrix));
        System.out.println(maximalSquareRefactor(matrix));
        System.out.println(maximalSquareSpaceOptimization(matrix));
        //#Reference:
        //174. Dungeon Game
        //741. Cherry Pickup
        //2304. Minimum Path Cost in a Grid
    }
}
