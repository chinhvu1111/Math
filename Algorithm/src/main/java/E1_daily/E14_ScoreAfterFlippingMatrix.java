package E1_daily;

public class E14_ScoreAfterFlippingMatrix {

    public static int matrixScore(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        //Flip to get left most =1
        for(int i=0;i<n;i++){
            if(grid[i][0]==1){
                continue;
            }
            for(int j=0;j<m;j++){
                grid[i][j]=1-grid[i][j];
            }
        }
        for(int i=1;i<m;i++){
            int sumOne=0;

            for(int j=0;j<n;j++){
                sumOne+=grid[j][i];
            }
            if(sumOne<n-sumOne){
                for(int j=0;j<n;j++){
                    grid[j][i]=1-grid[j][i];
                }
            }
        }
        int rs=0;

        for(int i=0;i<n;i++){
            int pow=1;

            for(int j=m-1;j>=0;j--){
                rs+=pow*grid[i][j];
                pow=pow*2;
            }
        }
        return rs;
    }

    public static int matrixScoreBitManipulation(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int score = (1 << (m - 1)) * n;

        for(int i=1;i<m;i++){
            int countSameBits=0;

            for(int j=0;j<n;j++){
                if(grid[j][i]==grid[j][0]){
                    countSameBits++;
                }
            }
            countSameBits = Math.max(countSameBits, n-countSameBits);
            int columnScore = (1<<(m-i-1))* countSameBits;
            score+=columnScore;
        }
        return score;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an (m x n) binary matrix grid.
        //- A move consists of choosing (any (row) or (column)) and (toggling each value) in that row or column
        // + (i.e., changing all 0's to 1's, and all 1's to 0's).
        //- (Every row) of the matrix is interpreted as (a binary number), and (the score of the matrix) is (the sum of these numbers).
        //* Return (the highest possible score) after making (any number of moves) (including zero moves).
        //Ex:
        //Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
        //Output: 39
        //Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
        //* Ta có thể biến đổi (row/ col) ==> Flip all bits
        //  + Sao cho (sum all of binary number) of each row => MAX.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 20
        //grid[i][j] is either 0 or 1.
        //==> Số row, col không lớn lắm ==> Recursively
        //
        //- Brainstorm
        //- Khi flip row:
        //  + Hướng đến việc flip có bit 1 (left most) nhiều nhất
        //  + Lật toàn bộ bit
        //- Khi flip column:
        //  + Flip bit thứ (i)
        //
        //- Sẽ cố gắng flip bit ở col(index=0) thành 1 hết
        //  + Sau đó sẽ dừng flip row ==> Chuyển qua flip columns thôi
        //  + Liệu logic trên có đúng không?
        //Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
        //Output: 39
        //[
        // [0,0,1,1],
        // [1,0,1,0],
        // [1,1,0,0]
        //]
        //=>
        //[
        // [1,1,0,0],
        // [1,0,1,0],
        // [1,1,0,0]
        //]
        //=> Flip remaining columns
        //
        //Input: grid = [[1,0,1,1],[1,0,1,0],[1,1,0,0]]
        //Output: 39
        //[
        // [1,0,1,1],
        // [1,0,1,0],
        // [1,0,0,0]
        //]
        //=> Giả sử làm ngược lại flip row đầu (Mặc dù left most bit chuyển từ 1 -> 0)
        //[
        // [0,1,0,0],
        // [1,0,1,0],
        // [1,0,0,0]
        //]
        //=> Flip col(index=1)
        //[
        // [0,0,0,0],
        // [1,1,1,0],
        // [1,1,0,0]
        //]
        //=> Flip row(index=0)
        //[
        // [1,1,1,1],
        // [1,1,1,0],
        // [1,1,0,0]
        //]
        //==> Cái kết quả này không khác gì việc =
        //[
        // [1,0,1,1],
        // [1,0,1,0],
        // [1,0,0,0]
        //]
        //+ (flip col (index=1)) : Thể nên đoạn trên làm vô ích.
        //
        //- Ta sẽ follow old logic:
        //=> Flip remaining columns
        //
        //100000
        //1048576
//        int[][] grid = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        int[][] grid = {
                {0,1},
                {1,1}
        };
        //
        //1.1, Optimization:
        //1.2, Complexity:
        //- Space: O(1)
        //- Time : O(n*m(
        //
        //2. Greedy way without modifying the input
        //- Idea thì tương tự nhau --> Chỉ là với bit manipulation --> Tính luôn init sum = (index=0 (bit))(111...111)
        //  + Sau đó với mỗi column:
        //      + Dựa trên số bit khác nhau --> Đánh giá xem cho sum là bao nhiêu.
        //
        //#Reference:
        //2128. Remove All Ones With Row and Column Flips
        System.out.println(matrixScore(grid));
        System.out.println(matrixScoreBitManipulation(grid));
        System.out.println(1<<21-1);
    }
}
