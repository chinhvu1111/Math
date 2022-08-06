package leetcode_medium_dynamic;

public class E68_MinimumFallingPathSum {

    public static int minFallingPathSum(int[][] matrix) {
//        int dx[]=new int[]{-1, -1, -1};
//        int dy[]=new int[]{-1, 0, 1};
        int n=matrix.length;
        int m=matrix[0].length;
        int dp[][]=new int[n][m];

        for(int i=0;i<m;i++){
            dp[0][i]=matrix[0][i];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                int min;

                min=dp[i-1][j];
                if(j>=1){
                    min=Math.min(min, dp[i-1][j-1]);
                }
                if(j+1<n){
                    min=Math.min(min, dp[i-1][j+1]);
                }
                dp[i][j]=min+matrix[i][j];
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            rs=Math.min(rs, dp[n-1][i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        int arr[][]=new int[][]{{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(minFallingPathSum(arr));
    }
}
