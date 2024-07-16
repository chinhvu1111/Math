package contest;

public class E147_CountSubmatricesWithEqualFrequencyOfXandY {

    public static int numberOfSubmatrices(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dpX=new int[n][m];
        int[][] dpY=new int[n][m];
//        int[][] dpDot=new int[n][m];
        int rs=0;

        for(int i=0;i<n;i++){
            int countX=0;
            int countY=0;
//            int countDot=0;
            for(int j=0;j<m;j++){
                if(grid[i][j]=='X'){
                    countX++;
                }else if(grid[i][j]=='Y') {
                    countY++;
                }
                int prevX=0;
                int prevY=0;
//                int prevDot=0;
                if(i!=0){
                    prevX=dpX[i-1][j];
                    prevY=dpY[i-1][j];
//                    prevX=dpDot[i-1][j];
                }
                dpX[i][j]=prevX+countX;
                dpY[i][j]=prevY+countY;
                if(dpX[i][j]==dpY[i][j]&&dpY[i][j]!=0){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contains:
        //  + grid[0][0]
        //  + an equal frequency of 'X' and 'Y'.
        //  + at least one 'X'.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= grid.length, grid[i].length <= 1000
        //+ grid[i][j] is either 'X', 'Y', or '.'.
        //+ Số cũng lớn ==> O(n^2) ==> O(n^3) được
        //
        //- Brainstorm
        //- Dùng dp[i][j]:
        //  + 3 cái dp cho 'x','y','.'
        //x . .
        //y x x
        //y . y
        //dp[i][j] = dp[i-1][j] + current prefix (loop in row for col index=( 0 --> j) )
    }
}
