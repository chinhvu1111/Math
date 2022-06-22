package interviews;

public class E40_KnightProbabilityInChessboard {

    static int dx[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int dy[] = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public static boolean insideBoard(int x, int y, int n){
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static double knightProbability(int n, int k, int row, int column) {
        double dp[][][]=new double[n][n][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0]=1;
            }
        }

        int currentPos=0;

        for(int t=1;t<=k;t++){
            int bx=row+1;
            int by=column+1;
            if(t<k){
                bx=n;
                by=n;
            }

            for(int i=0;i<bx;i++){
                for(int j=0;j<by;j++){
                    double proba=0;
                    for(int h=0;h<dx.length;h++){
                        int x=i+dx[h];
                        int y=j+dy[h];

                        if(insideBoard(x, y, n)){
                            proba+=dp[x][y][currentPos]/8;
                        }
                    }
//                    dp[i][j][currentPos^1]=dp[i][j][currentPos];
//                    System.out.println(proba);
                    dp[i][j][currentPos^1]=proba;
                }
            }
            currentPos=currentPos^1;
        }
        return dp[row][column][currentPos];
    }

    public static void main(String[] args) {
//        int n=3;
//        int k=2;
//        int k=1;
//        int n=3;
        int k=1;
        int n=1;
        System.out.println(knightProbability(n, k, 0, 0));
    }
}
