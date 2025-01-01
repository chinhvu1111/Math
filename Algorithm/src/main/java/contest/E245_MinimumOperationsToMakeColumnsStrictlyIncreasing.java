package contest;

public class E245_MinimumOperationsToMakeColumnsStrictlyIncreasing {

    public static int minimumOperations(int[][] grid) {
        int n= grid.length;
        int m=grid[0].length;
        int rs=0;
        for (int i = 0; i < m; i++) {
            int max=grid[0][i];
            for (int j = 1; j < n; j++) {
                while(grid[j][i]<=max){
                    grid[j][i]++;
                    rs++;
                }
                max=Math.max(max, grid[j][i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given a m x n matrix grid consisting of non-negative integers.
        //In one operation, you can increment the value of any grid[i][j] by 1.
        //Return the minimum number of operations needed to make all columns of grid strictly increasing.
    }
}
