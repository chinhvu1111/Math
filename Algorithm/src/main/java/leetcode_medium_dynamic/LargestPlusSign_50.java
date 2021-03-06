package leetcode_medium_dynamic;

import java.util.Arrays;

public class LargestPlusSign_50 {
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
        //Case n??y b??? sai:
        //+ index +1 ??? bi??n do t??nh c??? nh???ng tr?????ng h???p dp[i-1][j]=1 (N???u b???ng 0 th?? kh??ng c???n t??nh) --> ==0 continue
        //+ N???u ??? bi??n th?? c??ng kh??ng c???n t??nh --> V?? c??ng l???m ==1
//        int mines[][]=new int[][]{{0, 2}, {1, 0}, {2, 0}};
        //Case final:
        //dp[i][j]!=0 --> M??nh ch??a x??t ?????n v??? tr?? hi???n t???i
//        int n=5;
//        int mines[][]=new int[][]{{0,0},{0,3},{1,1},{1,4},{2,3},{3,0},{4,2}};
//        int n=3;
//        int mines[][]=new int[][]{{0,0}};
//        int n=5;
//        int mines[][]=new int[][]{{3,0}, {3,3}};
        //case 3: L???i do ?????ng t???i v??? tr?? dp[i][j] + c??c v??? tr?? tr??n =0 x???y ra 2 th:
        //+ dp[i][j]=1
        //+ dp[i][j]=0 --> T??nh dp[i][j]=0 (previous) + 1 (S??? g??y sai)
        //KINH NGHI???M:
        //+ D??ng index || && --> N?? s??? n???i v??o g??y sai --> () v??o
        //+ N???u mu???n lo???i b??? v??? tr?? i==0&&j==0 --> (i!=0 || j!=0)
//        int n = 2;
//        int mines[][] = new int[][]{{0, 1}, {1, 0}, {1, 1}};
        //V???i nh???ng b??i ki???u n??y th?????ng duy???t (bottom, right) v?? (left, botom) ??i c??ng v???i nhau
        //V???i kinh nghi???m nh???ng b??i m?? th??m 1 ????n v??? kh??ng gian dp[n][n] ==> dp[n+1][n+1]
        //--> Ki???u g?? c??ng ph???i x??t v??? tr?? ?????u ti??n [0][0] --> [1][1]
        //N?? ch??? t???t h??n ??? 1 ch???:
        //+ [0][i] --> Kh??ng ph???i quan t??m ?????n (0-i) b??? ??m --> Kh??ng ph???i t???n 1 c??u l???nh if
//        System.out.println(orderOfLargestPlusSign(n, mines));
        //Ta t?? duy nh?? sau:
        //- ??? b??n tr??n ta ???? t?? duy sai do ta ???? kh??ng x??t ?????n c??c kh??? n??ng c??c ph???n t??? b??? gi??n ??o???n n???u ??i theo c??c chi???u (left, righ, top, bottom)
        //M?? ch??? x??t ????n (k??ch th?????c d???u c???ng l???n nh???t) --> Sai
        //1, ??? ????y ta c???n l??u 4 h?????ng (left (T??? tr??i sang), top (T??? tr??n xu???ng), right (T??? ph??i sang), top (T??? d?????i l??n)
        //--> Ta c???n l??u k???t qu??? v??o 1 m???ng d???ng [4][i][j]
        //1.1, Kinh nghi???m: ????? [4][i][j] tr?????c --> Debug xem to??n b??? ma tr???n d??? h??n.
        //2, Sau ???? ta s??? l???y Max c??a min(all directions) c???a left, right, top, bottom ??? v??ng l???p cu???i c??ng
        //
        //T???i ??u l???i:
        //V?? ta t??nh max c???a min (left, top, right, bottom) th??? n??n:
        // Vi???c t??nh min --> Thay v?? update k??ch th?????c m???ng l??n 4 l???n ta c?? th??? update t???i 1 ph???n t??? duy nh???t l??:
        //dp[i][j]
//        grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
//        grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
//        grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
//        grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
        //--> Kinh nghi???m: N???u l???y min 4 h?????ng --> Ta ho??n to??n c?? th??? t??nh lu??n: (Ch??ng c?? th??? t??nh c??ng 1 l??c)
        //Min c???a all --> Ta s??? gi???m ???????c k??ch th?????c init dynamic array --> Reduce time
        System.out.println(orderOfLargestPlusSign1(n, mines));
    }
}
