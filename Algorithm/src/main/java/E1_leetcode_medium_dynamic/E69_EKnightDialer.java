package E1_leetcode_medium_dynamic;

public class E69_EKnightDialer {

    public static int knightDialer(int n) {
        int dx[]=new int[]{-2, -2, -1, 1, 2, 2, 1, -1, };
        int dy[]=new int[]{-1, 1, 2, 2, 1, -1, -2, -2};
        int arr[][]=new int[][]
                {{1,2,3}, {4,5,6}, {7,8,9}, {-1, 0, -1}};
        int m=arr.length;
        int l=arr[0].length;
        int dp[][][]=new int[m][l][n];
        int mod=1000_000_007;

        for(int j=0;j<m;j++){
            for(int k=0;k<l;k++){
                if(arr[j][k]!=-1){
                    dp[j][k][0]=1;
                }
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<l;k++){
                    if(arr[j][k]==-1){
                        continue;
                    }
                    int currentValue=0;

                    for(int h=0;h<dx.length;h++){
                        int x=dx[h]+j;
                        int y=dy[h]+k;

                        if(x>=0&&y>=0&&x<m&&y<l&&arr[x][y]!=-1){
                            currentValue=(currentValue+dp[x][y][i-1])%mod;
                        }
                    }
                    dp[j][k][i]=currentValue;
                }
            }
        }
        long rs=0;

        for(int j=0;j<m;j++){
            for(int k=0;k<l;k++){
                rs=(rs+dp[j][k][n-1])%mod;
            }
        }
        return (int)rs;
    }

    public static int knightDialerOptimized(int n) {
        int mod=1000_000_007;
        int matrix[][]={
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}
        };

        //Cách này dùng công thức toán --> Ta có thể bỏ qua.
        return 0;
    }

    public static void main(String[] args) {
        int n=2;
        System.out.println(knightDialer(n));
    }
}
