package contest;

public class E48_SubmatricesWithTopLeftElementAndSumLessThank {

    public static int countSubmatrices(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] left=new int[n][m];

        for(int i=0;i<n;i++){
            int curSum=0;
            for(int j=0;j<m;j++){
                curSum+=grid[i][j];
                left[i][j]=curSum;
            }
        }
        for(int i=0;i<m;i++){
            int curSum=0;
            for(int j=0;j<n;j++){
                curSum+=left[j][i];
                grid[j][i]=curSum;
            }
        }
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]<=k){
                    rs++;
                }
//                System.out.printf("%s,", grid[i][j]);
            }
//            System.out.println();
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Cho k và matrix
        //- Tính số sub-matrix chứa top-left element và có sum <= k
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //m == grid.length
        //n == grid[i].length
        //1 <= n, m <= 1000 ==> O(n^2) dc
        //0 <= grid[i][j] <= 1000 `
        //1 <= k <= 10^9
        //
        //- Brainstorm
        //-
        //Ex:
        //7,6,3
        //6,6,1
        //-> Ta chỉ cần tính sum tại matrix[i][j]
        //left[i][j] : Loop theo row trước
        //7,13,16
        //6,13,13
        //
        //dp[i][j]: Loop theo col trước
        //
//        int[][] grid = {{7,6,3},{6,6,1}};
//        int k = 18;
//        int[][] grid = {{7,2,9},{1,5,0},{2,6,6}};
//        int k = 20;
        int[][] grid = {{1},{1}};
        int k = 20;
        System.out.println(countSubmatrices(grid, k));
    }
}
