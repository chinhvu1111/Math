package E1_leetcode_medium_dynamic;

public class E134_Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dpLeft=new int[n][m];
        int[][] dpRight=new int[n][m];
        int[][] dpUp=new int[n][m];
        int[][] dpDown=new int[n][m];

        for(int i=0;i<n;i++){
            int left=0;
            int right=0;

            for(int j=0;j<m;j++){
                int k=m-j-1;
                if(grid[i][j]==1){
                    left++;
                    dpLeft[i][j]=left;
                }else{
                    left=0;
                }
                if(grid[i][k]==1){
                    right++;
                    dpRight[i][k]=right;
                }else{
                    right=0;
                }
            }
        }
        for(int i=0;i<m;i++){
            int up=0, down=0;
            for(int j=0;j<n;j++){
                int k=n-j-1;
                if(grid[j][i]==1){
                    up++;
                    dpUp[j][i]=up;
                }else{
                    up=0;
                }
                if(grid[k][i]==1){
                    down++;
                    dpDown[k][i]=down;
                }else{
                    down=0;
                }
            }
        }
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int l=0;l<n;l++){
                    //(i,j) -> (i+l,j)
                    //(i,j+l) -> (i+l,j+l)
                    //==> Nhầm
                    //x: là row
                    //y: là col
                    //=>
                    //(i,j) -> (i,j+l)
                    //(i+l,j) -> (i+l,j+l)
                    if(i+l>=n||j+l>=m){
                        continue;
                    }
                    int downX=i+l;
                    int downy=j;
                    int rightX=i;
                    int rightY=j+l;
                    int crossX=i+l;
                    int crossY=j+l;
                    int current = Math.min(dpDown[i][j], dpRight[i][j]);
                    int down=Math.min(dpUp[downX][downy], dpRight[downX][downy]);
                    int right = Math.min(dpLeft[rightX][rightY], dpDown[rightX][rightY]);
                    int cross = Math.min(dpUp[crossX][crossY], dpLeft[crossX][crossY]);
                    rs=Math.max(rs, Math.min(l+1, Math.min(current, Math.min(down, Math.min(cross, right)))));
                    System.out.printf("dpDown: %s, dpRight:%s, i: %s, j: %s\n",dpDown[i][j], dpRight[i][j], i, j);
//                    System.out.printf("dpLeft: %s, dpDown:%s, i: %s, j: %s\n",dpLeft[rightX][rightY], dpDown[rightX][rightY], rightX, rightY);
                }
            }
        }
        return rs*rs;
    }

    public static int largest1BorderedSquareOptimization(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][][] dp=new int[n+1][m+1][2];
        int curMaxSize=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    //up
                    dp[i+1][j+1][0]=dp[i][j+1][0]+1;
                    //Down
                    dp[i+1][j+1][1]=dp[i+1][j][1]+1;
                    int expectedSize=Math.min(dp[i+1][j+1][0], dp[i+1][j+1][1]);

                    for(int l=expectedSize;l>curMaxSize;l--){
                        int len=Math.min(dp[i-l+2][j+1][1], dp[i+1][j-l+2][0]);
                        if(len>=l){
                            curMaxSize= l;
                            break;
                        }
                    }
                }
            }
        }
        return curMaxSize*curMaxSize;
    }
    public static void main(String[] args) {
        //** Requirement:
        //Given a 2D grid of 0s and 1s,
        //* return the number of elements in the largest square subgrid that has all 1s on its border,
        // or 0 if such a subgrid doesn't exist in the grid.
        //- Return max size của sub-matrix có border toàn 1
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= grid.length <= 100
        //1 <= grid[0].length <= 100
        //grid[i][j] is 0 or 1
        //
        //- Brainstorm
        //Ex:
        //Input: grid =
        // [[1,1,1],
        //  [1,0,1],
        //  [1,1,1]]
        //Output: 9
        //Ex:
        //Input: grid =
        // [[1,1,0,0]]
        //Output: 1
        //==> Do là square
        //
        // [[1,1,1],
        //  [1,0,1],
        //  [1,1,1]]
        //--> Nếu dp[i][j] lưu số lượng chữ số 1 right của [i][j]
        // [[1,2,3],
        //  [1,0,1],
        //  [1,2,3]]
        //--> Nếu dp[i][j] lưu số lượng chữ số 1 left của [i][j]
        // [[3,2,1],
        //  [1,0,1],
        //  [3,2,1]]
        //- 1 hình vuông được xác định bằng:
        //  + Đường chéo:
        //      + (i,j) và (i+k,j+k)
        //      + k = n --> 0
        //  + Và 4 đỉnh của hình vuông.
        //- Với mỗi đỉnh (i,j), cần xác định:
        //  + Số số 1 left
        //  + Số số 1 right
        //  + Số số 1 down
        //  + Số số 1 up
        //* Note (lỗi sai):
        //==> Nhầm (Sang trục toạ độ)
        //- Rule ntn mới đúng:
        //+ x: là row
        //+ y: là col
        //=>
        //(i,j) -> (i,j+l)
        //(i+l,j) -> (i+l,j+l)
        //- Phần cộng thêm từ đường chéo ==> Xét min với cả l nữa vì:
        //  + Có cases thoả mãn min=5 (Từ 4 điểm) ==> Nhưng l=4 ==> rs = 4 chứ không phải 5.
        //1.1, Optimization
        //- Ta sẽ chỉ cần lưu chiều up, left mà thôi
        //Ex:
        //{{1,1,1},
        // {1,0,1},
        // {1,1,1}}
        //- Số chữ số 1 up:
        //dp[i=2][j=2][0] = 3
        //- Số chữ số 1 left:
        //dp[i=2][j=2][1] = 3
        //- Idea ở đây là lấy min của (left,up) của (i,j)
        //  ==> Vì là min nên (dp[i-min+1][j], dp[i][j-min+1])==1
        //  + Với dp[i-min+1][j] --> Ta cần left => [1]
        //  + Với dp[i][j-min+1] --> Ta cần up => [0]
        //  ==> Lấy min của 2 số trên ta sẽ thu được kết quả
        //* Điểm chính ở đây là 1 từ 1 (node!=0) + prefix sum của nó
        // ==> Suy ra các node ở vị trí cần tìm --> Để có thể có square.
        //
        //- Ngoài ra để giảm số lần loop length (l) --> Ta có thể update max thường xuyên
        //  ==> Loop có thể break ngay để giảm thời gian thực hiện
        //
        //- Để tránh dùng biến tạm + loop nhiều vòng
        //  ==> Ta có thể dùng trực tiếp array:
        //  + Up
        //   + dp[i+1][j+1][0] = dp[i][j+1][0] + 1
        //  + Down
        //   + dp[i+1][j+1][0] = dp[i+1][j][1] + 1
        //
        //1.2, Complexity
        //- Space : O(n^2)
        //- Time : O(n^3)
        //
        //#Reference:
        //1333. Filter Restaurants by Vegan-Friendly, Price and Distance
        //2012. Sum of Beauty in the Array
        //691. Stickers to Spell Word
        //495. Teemo Attacking
        //1467. Probability of a Two Boxes Having The Same Number of Distinct Balls
        //1423. Maximum Points You Can Obtain from Cards
        //
//        int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
//        int[][] grid = {{0,0},{1,1}};
//        int[][] grid = {
//                {0,1},
//                {1,1}};
//        int[][] grid = {
//                {1,0},
//                {1,1}};
        int[][] grid = {
                {1,1,1,0,1,0,1},
                {1,1,0,1,1,1,1},
                {1,1,1,1,1,1,0},
                {0,1,1,1,1,0,0},
                {0,1,0,1,1,1,1},
                {1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1}};
        System.out.println(largest1BorderedSquare(grid));
        System.out.println(largest1BorderedSquareOptimization(grid));
    }
}
