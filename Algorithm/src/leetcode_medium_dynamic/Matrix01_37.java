package leetcode_medium_dynamic;

public class Matrix01_37 {

    public static int[][] updateMatrix(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        int dp[][]=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j]==0){
                    continue;
                }

                dp[i][j]=10000;
                int min=100000;
                int x=i-1;
                int y=j-1;

                //Speed up
                if((x>=0&&dp[x][j]==0)||(y>=0&&dp[i][y]==0)){
                    min=0;
                    dp[i][j]=min+1;
                    continue;
                }
                if(x>=0&&dp[x][j]!=0){
                    min=Math.min(min,dp[x][j]);
                }
                if(y>=0&&dp[i][y]!=0){
                    min=Math.min(min, dp[i][y]);
                }
//                if(y>=0&&dp[i][y]==0){
//                    min=0;
//                }
                dp[i][j]=Math.min(dp[i][j], min+1);
            }
        }

        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(mat[i][j]==0){
                    continue;
                }
                int min=100000;
                int x=i+1;
                int y=j+1;

                //Speed up
                if((x<n&&dp[x][j]==0)||(y<m&&dp[i][y]==0)){
                    min=0;
                    dp[i][j]=min+1;
                    continue;
                }
                if(x<n&&dp[x][j]!=0){
                    min=Math.min(min,dp[x][j]);
                }
                if(y<m&&dp[i][y]!=0){
                    min=Math.min(min, dp[i][y]);
                }
//                if(y<m&&dp[i][y]==0){
//                    min=0;
//                }
                dp[i][j]=Math.min(dp[i][j], min+1);
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int [][]arr=new int[][]{{0,0,0},{0,1,0},{1,1,1}};
        updateMatrix(arr);
    }
}
