package E1_PrefixSum;

public class E10_MagicSquaresInGrid {

    public static int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int rs=0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(m)
            for(int j=0;j<m;j++){
                if(i+2>=n||j+2>=m){
                    continue;
                }
//                System.out.printf("%s %s\n", i, j);
                boolean isValid=true;
                int sum = grid[i][j]+grid[i][j+1]+grid[i][j+2];
                //Time: O(9)
                for(int k=1;k<=2;k++){
                    int curSum=0;
                    for(int h=0;h<=2;h++){
                        curSum+=grid[i+k][j+h];
                    }
                    if(sum!=curSum){
                        isValid=false;
                        break;
                    }
                }
                if(!isValid){
                    continue;
                }
                int[] count=new int[10];

                for(int k=0;k<=2;k++){
                    int curSum=0;
                    for(int h=0;h<=2;h++){
                        if(grid[i+h][j+k]>=10||grid[i+h][j+k]<=0||count[grid[i+h][j+k]]<0){
                            isValid=false;
                            break;
                        }
                        count[grid[i+h][j+k]]--;
                        curSum+=grid[i+h][j+k];
                    }
                    if(sum!=curSum||!isValid){
                        isValid=false;
                        break;
                    }
                }
                if(!isValid){
                    continue;
                }
                int sumDiagonal = grid[i][j]+grid[i+1][j+1]+grid[i+2][j+2];
                if(sumDiagonal!=sum){
                    continue;
                }
                sumDiagonal = grid[i][j+2]+grid[i+1][j+1]+grid[i+2][j];
                if(sumDiagonal!=sum){
                    continue;
                }
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A 3 x 3 magic square is a 3 x 3 grid filled with (distinct numbers) from (1 to 9) such that
        // each row, column, and (both diagonals) all have the (same sum).
        //- Given a row x col grid of integers,
        //* How many 3 x 3 ("magic square" subgrids) are there?
        // (Each subgrid is contiguous).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //row == grid.length
        //col == grid[i].length
        //1 <= row, col <= 10
        //0 <= grid[i][j] <= 15
        //==> Số k lớn lắm
        //
        //- Brainstorm
        //- Ta cần:
        //  + Limit số
        //  + Distinct numbers == 9
        //- Dùng count[10]:
        //  + Xuất hiện 1 số count[val]--;
        //  + if(count[val]<0) --> break
        //
        //- Failure:
        //- Liên quan đến break --> Vô tội vạ làm j không tăng lên ==> kết quả sai
        //
//        int[][] grid = {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
//        int[][] grid = {
//                {7,0,5},
//                {2,4,6},
//                {3,8,1}};
        int[][]grid = {
                {3,10,2,3,4},
                {4,5,6,8,1},
                {8,8,1,6,8},
                {1,3,5,7,1},
                {9,4,9,2,9}};
        System.out.println(numMagicSquaresInside(grid));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*m*9)
        //
        //#Reference:
        //1072. Flip Columns For Maximum Number of Equal Rows
        //1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
        //1383. Maximum Performance of a Team
    }
}
