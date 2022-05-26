package leetcode_medium_dynamic;

import java.util.Arrays;

public class OutOfBoundaryPaths_39 {
    private static int dx[]=new int[]{-1, 0, 1, 0};
    private static int dy[]=new int[]{0, 1, 0, -1};

    public static int findPaths(
            int m,
            int n,
            int maxMove,
            int startRow,
            int startColumn) {
        if(maxMove==0){
            return 0;
        }
        //Chậm do tính Math.pow() liên tục --> Dùng thuật toán tính mũ (Quy hoạch động chẳng hạn --> ) Chậm
        int mod=1000000007;

        int dp[][][]=new int[m][n][2];
        int rs=0;
        dp[startRow][startColumn][0]=1;
        dp[startRow][startColumn][1]=1;

        for(int i=0;i<dx.length;i++){
            int x=startRow+dx[i];
            int y=startColumn+dy[i];
            if(x<0||y<0||x>=m||y>=n){
                rs++;
            }
        }
//        if((!isFirst&&i==startRow&&j==startColumn)){
//            rs= (int) ((rs+c)%(Math.pow(10,9)+7));
//            isFirst=true;
//        }

        for(int k=1;k<maxMove;k++){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    int method=0;
                    boolean isOutBound=false;
                    int c=0;

                    for(int l=0;l<dx.length;l++){
                        int x=i+dx[l];
                        int y=j+dy[l];
                        if(x<0||y<0||x>=m||y>=n){
                            isOutBound=true;
                            c++;
                            continue;
                        }
                        method= (dp[x][y][0]+method)%mod;
                    }
                    //Chỗ này mình thiếu mất cases quay đi quay lại điểm bắt đầu thì xử lý như thế nào?
                    if(isOutBound){
                        rs= (rs+ method * c % mod) %mod;
                    }
                    dp[i][j][1]=method;
                }
            }
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    dp[i][j][0]=dp[i][j][1];
                }
            }
        }
//        if(m-1!=0){
//            rs+=dp[0][0][1]*2;
//            rs+=dp[0][n-1][1]*2;
//            rs+=dp[m-1][0][1]*2;
//            rs+=dp[m-1][n-1][1]*2;
//        }else{
//            rs+=dp[0][0][1]*3;
//            rs+=dp[0][n-1][1]*3;
//        }
//
//        for(int i=1;i<m-1&&n-2>=0;i++){
//            rs+=dp[i][n-2][1];
//        }
//        for(int i=1;i<n-1&&m-2>=0;i++){
//            rs+=dp[m-2][i][1];
//        }
        //6071862286
        //914783380
         return rs;
    }

    //Nhanh hơn dc cùng lắm 1 ms --> Không nhanh hơn
    public static int findPaths1(
            int m,
            int n,
            int maxMove,
            int startRow,
            int startColumn) {
        if(maxMove==0){
            return 0;
        }

        //Chậm do tính Math.pow() liên tục --> Dùng thuật toán tính mũ (Quy hoạch động chẳng hạn --> ) Chậm
        int mod=1000000007;
        int dp[][]=new int[m][n];
        int rs=0;
        dp[startRow][startColumn]=1;

        for(int i=0;i<dx.length;i++){
            int x=startRow+dx[i];
            int y=startColumn+dy[i];
            if(x<0||y<0||x>=m||y>=n){
                rs++;
            }
        }
//        if((!isFirst&&i==startRow&&j==startColumn)){
//            rs= (int) ((rs+c)%(Math.pow(10,9)+7));
//            isFirst=true;
//        }

        for(int k=1;k<maxMove;k++){
            int temp[][]=new int[m][n];

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    int method=0;
                    boolean isOutBound=false;
                    int c=0;

                    for(int l=0;l<dx.length;l++){
                        int x=i+dx[l];
                        int y=j+dy[l];
                        if(x<0||y<0||x>=m||y>=n){
                            isOutBound=true;
                            c++;
                            continue;
                        }
                        method= (dp[x][y]+method)%mod;
                    }
                    //Chỗ này mình thiếu mất cases quay đi quay lại điểm bắt đầu thì xử lý như thế nào?
                    if(isOutBound){
                        rs= (rs+ method * c % mod) %mod;
                        //Tại sao không phải là dp[i][j]
                        //Tức là tính số cách đến được (i, j) (method) --> Sum (all)
                        //c : Số hưởng mà ra ngoài bàn cở nếu đến (i, j) + Đi tiếp 1 bước nữa
                        //--> rs + c* method
//                        rs= (int) ((rs+ dp[i][j] * c % (Math.pow(10,9)+7)) %(Math.pow(10,9)+7));
                    }
                    temp[i][j]=method;
                }
            }
            dp=temp;
        }
//        if(m-1!=0){
//            rs+=dp[0][0][1]*2;
//            rs+=dp[0][n-1][1]*2;
//            rs+=dp[m-1][0][1]*2;
//            rs+=dp[m-1][n-1][1]*2;
//        }else{
//            rs+=dp[0][0][1]*3;
//            rs+=dp[0][n-1][1]*3;
//        }
//
//        for(int i=1;i<m-1&&n-2>=0;i++){
//            rs+=dp[i][n-2][1];
//        }
//        for(int i=1;i<n-1&&m-2>=0;i++){
//            rs+=dp[m-2][i][1];
//        }
        //6071862286
        //914783380
        return rs;
    }

    public static int dp1[][][];

    public static int findPathMemorization(int n, int m, int k, int sr, int sc){
        dp1=new int[n][m][k+1];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(dp1[i][j], -1);
            }
        }
        return findPathsMemo(n,m, k, sr, sc, dp1);

    }

    public static int findPathsMemo(int n, int m, int k, int sr, int sc,int[][][]dp) {
        if(sr<0||sr>n||sc<0||sc>m){
            return 1;
        }
        if(k==0){
            return 0;
        }
        if(dp[sr][sc][k]!=-1){
            return dp[sr][sc][k];
        }

        int count=0;

        for(int i=0;i<dx.length;i++){
            int x=sr+dx[i];
            int y=sc+dy[i];

            count+=findPathsMemo(n,m, k-1, x, y, dp);
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(findPaths(2,2, 2,0, 0));
//        System.out.println(findPaths(1,3, 3,0, 1));
//        System.out.println(findPaths(2,2, 1,0, 0));
//        System.out.println(findPaths(1,2, 50,0, 0));
        //Quá số method
//        System.out.println(findPaths(8,50, 23,5, 26));
        //914783380
        System.out.println(findPaths(36,5, 50,15, 3));
        System.out.println(findPaths1(36,5, 50,15, 3));
        System.out.println(findPaths(3,8, 0,2, 0));
        System.out.println(findPaths1(3,8, 0,2, 0));

        System.out.println(findPathMemorization(36,5, 50,15, 3));
    }
}
