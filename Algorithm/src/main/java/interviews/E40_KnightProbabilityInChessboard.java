package interviews;

public class E40_KnightProbabilityInChessboard {

    static int dx[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int dy[] = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public static boolean insideBoard(int x, int y, int n){
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static double knightProbability(int n, int k, int row, int column) {
        double dp[][][]=new double[n][n][k+1];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0]=1;
            }
        }

        for(int t=1;t<=k;t++){

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    double proba=0;
                    for(int h=0;h<dx.length;h++){
                        int x=i+dx[h];
                        int y=j+dy[h];

                        if(insideBoard(x, y, n)&&t-1>=0){
                            proba+=dp[x][y][t-1]/8;
                        }
                    }
//                    System.out.println(proba);
                    dp[i][j][t]=proba;
                }
            }
        }
        return dp[row][column][k];
    }

    public static void main(String[] args) {
//        int k=2;
//        int n=3;
        int k=1;
        int n=3;
        System.out.println(knightProbability(n, k, 1, 1));
    }
}
