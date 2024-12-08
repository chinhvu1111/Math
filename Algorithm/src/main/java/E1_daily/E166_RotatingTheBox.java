package E1_daily;

import java.util.Arrays;

public class E166_RotatingTheBox {

    public static char[][] rotateTheBox(char[][] box) {
        int n=box.length;
        int m=box[0].length;
        char[][] rs=new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(rs[i], '.');
        }

        for(int i=0;i<n;i++){
            int emptyIndex=m-1;
            for(int j=m-1;j>=0;j--){
                if(box[i][j]=='*'){
                    rs[j][n-1-i]='*';
                    emptyIndex=j-1;
                }else if(emptyIndex!=j&&box[i][j]=='#'){
                    rs[emptyIndex--][n-1-i]='#';
                }else{
                    rs[j][n-1-i]=box[i][j];
                    if(box[i][j]!='.'){
                        emptyIndex=j-1;
                    }
                }
            }
        }
        return rs;
    }

    public static char[][] rotateTheBoxReference(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] result = new char[n][m];

        // Initialize the result grid with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = '.';
            }
        }

        // Apply gravity to let stones fall to the lowest possible empty cell in each column
        for (int i = 0; i < m; i++) {
            int lowestRowWithEmptyCell = n - 1;
            // Process each cell in row `i` in reversed order
            for (int j = n - 1; j >= 0; j--) {
                // Found a stone - let it fall to the lowest empty cell
                if (box[i][j] == '#') {
                    // Place it in the correct position in the rotated grid
                    result[lowestRowWithEmptyCell][m - i - 1] = '#';
                    lowestRowWithEmptyCell--;
                }
                // Found an obstacle - reset `lowestRowWithEmptyCell` to the row directly above it
                if (box[i][j] == '*') {
                    // Place the obstacle in the correct position in the rotated grid
                    result[j][m - i - 1] = '*';
                    lowestRowWithEmptyCell = j - 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
        //  + A stone '#'
        //  + A stationary obstacle '*'
        //  + Empty '.'
        //- The box is rotated 90 degrees clockwise, causing (some of the stones) to (fall) due to (gravity).
        //- (Each stone) falls down until it lands on (an obstacle, another stone, or the bottom) of the box.
        //- Gravity does not affect (the obstacles' positions), and (the inertia) from (the box's rotation) does not affect (the stones's horizontal positions).
        //- It is guaranteed that each stone in box rests on (an obstacle, another stone, or the bottom of the box).
        //
        //* Return an n x m matrix representing the box after the rotation described above.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == box.length
        //n == box[i].length
        //1 <= m, n <= 500
        //box[i][j] is either '#', '*', or '.'.
        //
        //- Brainstorm
        //- Simply, we can move the stones from right to left until meet the (boundary or obstacle)
        //- How to move (the all of nodes) at the same time
        //Example 3:
        //
        //Input: box = [["#","#","*",".","*","."],
        //              ["#","#","#","*",".","."],
        //              ["#","#","#",".","#","."]]
        //Output: [[".","#","#"],
        //         [".","#","#"],
        //         ["#","#","*"],
        //         ["#","*","."],
        //         ["#",".","*"],
        //         ["#",".","."]]
        //- For each row we need to traverse from (right -> left):
        //  + Save the furthest with max index
        //      + If we traverse to the obstacle:
        //          + update index = max index
        //      + If we traverse to (a stone):
        //          + update index = index + 1
        //          + We don't have the case (index=index+1) with value is equal to (obstacle)
        //Ex:
        //#,#,#,.,.,.
        //
        //- Index after rotating:
        //- grid[i][j]:
        //  + grid[2][1] => [1][0]
        //  grid[i][j] -> grid[j][n-1-j]
        //
//        char[][] box =
//                {{'#','.','*','.'},
//                {'#','#','*','.'}};
        char[][] box =
                {
                        {'#','#','*','.','*','.'},
                        {'#','#','#','*','.','.'},
                        {'#','#','#','.','#','.'}
                };
//        char[][] rs= rotateTheBox(box);
        char[][] rs= rotateTheBoxReference(box);
        //
        //1.1, Optimization
        //- Thực ra chỉ cần 2 lần if với:
        //  + val = '#'
        //  + val = '*'
        //==> Còn '.' thì bỏ qua không cần assignn
        //  + Ta assign 1 lần hết là được.
        //
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1826. Faulty Sensor
        //1878. Get Biggest Three Rhombus Sums in a Grid
        //2555. Maximize Win From Two Segments
        //
        for (int i = 0; i < rs.length; i++) {
            for (int j = 0; j < rs[0].length; j++) {
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
