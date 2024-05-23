package contest;

public class E88_MakeASquareWithTheSameColor {

    public static boolean canMakeSquare(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i+1>=n||j+1>=m){
                    continue;
                }
                int countB=0;
                int countW=0;
                if(grid[i][j]=='B'){
                    countB++;
                }else{
                    countW++;
                }
                if(grid[i][j+1]=='B'){
                    countB++;
                }else{
                    countW++;
                }
                if(grid[i+1][j]=='B'){
                    countB++;
                }else{
                    countW++;
                }
                if(grid[i+1][j+1]=='B'){
                    countB++;
                }else{
                    countW++;
                }
                if(countB==3||countW==3){
                    return true;
                }
                System.out.printf("%s %s\n", countB, countW);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        char[][] grid = {
                {'B','W','B'},
                {'B','W','W'},
                {'B','W','B'}};
        System.out.println(canMakeSquare(grid));
    }
}
