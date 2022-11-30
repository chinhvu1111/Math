package contest;

public class E9_DifferenceBetweenOnesAndZerosInRowAndColumn {
    public static int[][] onesMinusZeros(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] rowSum =new int[n][2];
        int[][] columnSum =new int[m][2];

        for(int i=0;i<n;i++){
            int currentOnes=0;
            int currentZeros=0;

            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    currentOnes++;
                }else{
                    currentZeros++;
                }
            }
            rowSum[i][0]=currentZeros;
            rowSum[i][1]=currentOnes;
        }
        for(int i=0;i<m;i++){
            int currentOnes=0;
            int currentZeros=0;

            for(int j=0;j<n;j++){
                if(grid[j][i]==1){
                    currentOnes++;
                }else{
                    currentZeros++;
                }
            }
            columnSum[i][0]=currentZeros;
            columnSum[i][1]=currentOnes;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j]=rowSum[i][1]+columnSum[j][1]-rowSum[i][0]-columnSum[j][0];
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        int[][] grid=new int[][]{
                {0,1,1},{1,0,1},{0,0,1}
        };
        onesMinusZeros(grid);
    }
}
