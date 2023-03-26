package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E50_LargestPlusSign {
    public static int orderOfLargestPlusSign(int n, int[][] mines) {
        int dp[][] = new int[n + 1][n + 1];
        int dp1[][] = new int[n + 1][n + 1];
        int rs = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i + 1], 1);
            dp[i + 1][0] = 0;
            Arrays.fill(dp1[i], 1);
            dp1[i][n] = 0;
        }
        for (int i = 0; i < mines.length; i++) {
            int x = mines[i][0];
            int y = mines[i][1];
            dp[x + 1][y + 1] = 0;
            dp1[x][y] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
//                if(i==1||j==1){
//                    continue;
//                }
                if (i == 1 || j == 1 ||dp[i - 1][j]==0 || dp[i][j - 1]==0 || dp[i][j]==0) {
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i != n - 1 && j != n - 1 && dp1[i + 1][j]!=0 && dp1[i][j + 1]!=0 && dp1[i][j]!=0) {
                    dp1[i][j] = Math.min(dp1[i + 1][j], dp1[i][j + 1]) + 1;
                }
                if (dp1[i][j] == dp[i + 1][j + 1]) {
                    rs = Math.max(dp1[i][j], rs);
                }
            }
        }
        if (rs == 0 || rs == 1) {
            return rs;
        }
        return rs;
    }

    public static int orderOfLargestPlusSign1(int n, int[][] mines) {
        int rs = 0;
        int arr[][]=new int[n][n];
        int dp[][][]=new int[4][n][n];
        int length=mines.length;

        for(int i=0;i<n;i++){
            Arrays.fill(arr[i], 1);
        }

        for(int i=0;i<length;i++){
            int x=mines[i][0];
            int y=mines[i][1];
            arr[x][y]=0;
        }

        for(int i=0;i<n;i++){
            int countLeft=0;
            int countTop=0;

            for(int j=0;j<n;j++){
                if(arr[i][j]==1){
                    countLeft++;
                    dp[0][i][j]=countLeft;
                }else {
                    countLeft=0;
                }
                if(arr[j][i]==1){
                    countTop++;
                    dp[1][j][i]=countTop;
                }else{
                    countTop=0;
                }
            }
        }
        for(int i=n-1;i>=0;i--) {
            int countRight=0;
            int countBottom=0;

            for(int j=n-1;j>=0;j--){
                if(arr[i][j]==1){
                    countRight++;
                    dp[2][i][j]=countRight;
                }else{
                    countRight=0;
                }
                if(arr[j][i]==1){
                    countBottom++;
                    dp[3][j][i]=countBottom;
                }else{
                    countBottom=0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                rs=Math.max(rs,Math.min(dp[0][i][j], Math.min(dp[1][i][j], Math.min(dp[2][i][j], dp[3][i][j]))));
            }
        }
        return rs;
    }

    public static int orderOfLargestPlusSign2(int N, int[][] mines) {
        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], N);
        }

        for (int[] m : mines) {
            grid[m[0]][m[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
                grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
                grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
                grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
            }
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int n=5;
        int mines[][]=new int[][]{{4, 2}};
//        int n=1;
//        int mines[][]=new int[][]{{0, 0}};
//        int n=2;
//        int mines[][]=new int[][]{{0, 0}, {0, 1}, {1, 0}};
//        int n=1;
//        int mines[][]=new int[][]{{0, 0}};
//        int n=3;
        //Case này bị sai:
        //+ index +1 ở biên do tính cả những trường hợp dp[i-1][j]=1 (Nếu bằng 0 thì không cần tính) --> ==0 continue
        //+ Nếu ở biên thì cũng không cần tính --> Vì cùng lắm ==1
//        int mines[][]=new int[][]{{0, 2}, {1, 0}, {2, 0}};
        //Case final:
        //dp[i][j]!=0 --> Mình chưa xét đến vị trí hiện tại
//        int n=5;
//        int mines[][]=new int[][]{{0,0},{0,3},{1,1},{1,4},{2,3},{3,0},{4,2}};
//        int n=3;
//        int mines[][]=new int[][]{{0,0}};
//        int n=5;
//        int mines[][]=new int[][]{{3,0}, {3,3}};
        //case 3: Lỗi do đứng tại vị trí dp[i][j] + các vị trí trên =0 xảy ra 2 th:
        //+ dp[i][j]=1
        //+ dp[i][j]=0 --> Tính dp[i][j]=0 (previous) + 1 (Sẽ gây sai)
        //KINH NGHIỆM:
        //+ Dùng index || && --> Nó sẽ nối vào gây sai --> () vào
        //+ Nếu muốn loại bỏ vị trí i==0&&j==0 --> (i!=0 || j!=0)
//        int n = 2;
//        int mines[][] = new int[][]{{0, 1}, {1, 0}, {1, 1}};
        //Với những bài kiểu này thường duyệt (bottom, right) và (left, botom) đi cùng với nhau
        //Với kinh nghiệm những bài mà thêm 1 đơn vị không gian dp[n][n] ==> dp[n+1][n+1]
        //--> Kiểu gì cũng phải xét vị trí đầu tiên [0][0] --> [1][1]
        //Nó chỉ tốt hơn ở 1 chỗ:
        //+ [0][i] --> Không phải quan tâm đến (0-i) bị âm --> Không phải tốn 1 câu lệnh if
//        System.out.println(orderOfLargestPlusSign(n, mines));
        //Ta tư duy như sau:
        //- Ở bên trên ta đã tư duy sai do ta đã không xét đến các khả năng các phần tử bị gián đoạn nếu đi theo các chiều (left, righ, top, bottom)
        //Mà chỉ xét đên (kích thước dấu cộng lớn nhất) --> Sai
        //1, Ở đây ta cần lưu 4 hưởng (left (Từ trái sang), top (Từ trên xuống), right (Từ phái sang), top (Từ dưới lên)
        //--> Ta cần lưu kết quả vào 1 mảng dạng [4][i][j]
        //1.1, Kinh nghiệm: Để [4][i][j] trước --> Debug xem toàn bộ ma trận dễ hơn.
        //2, Sau đó ta sẽ lấy Max cùa min(all directions) của left, right, top, bottom ở vòng lặp cuối cùng
        //
        //Tối ưu lại:
        //Vì ta tính max của min (left, top, right, bottom) thế nên:
        // Việc tính min --> Thay vì update kích thước mảng lên 4 lần ta có thể update tại 1 phần tử duy nhất là:
        //dp[i][j]
//        grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
//        grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
//        grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
//        grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
        //--> Kinh nghiệm: Nếu lấy min 4 hướng --> Ta hoàn toàn có thể tính luôn: (Chúng có thể tính cùng 1 lúc)
        //Min của all --> Ta sẽ giảm được kích thước init dynamic array --> Reduce time
        System.out.println(orderOfLargestPlusSign1(n, mines));
    }
}
