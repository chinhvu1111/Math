package E1_hashmap;

import java.util.HashSet;

public class E17_SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        HashSet<Integer> colZero = new HashSet<Integer>();
        HashSet<Integer> rowZero = new HashSet<Integer>();
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    rowZero.add(i);
                    colZero.add(j);
                }
            }
        }
        for (int col : colZero) {
            for (int j = 0; j < n; j++) {
                matrix[j][col] = 0;
            }
        }
        for (int row : rowZero) {
            for (int j = 0; j < m; j++) {
                matrix[row][j] = 0;
            }
        }
    }

    public static void setZeroesOptimization(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < m; i++) {
            //Cái này phải xét riêng vì ta có sự update các (cells==0) bên trên với (index<i)
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            //Cái này là col đầu tiên được traversed
            //--> Cái này có thể để trong được
            if (matrix[i][0] == 0) {
                firstColZero = true;
            }
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
//                    System.out.printf("%s %s\n", i, j);
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowZero) {
            for (int i = 0; i < m; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
//        for (int[] ints : matrix) {
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%s, ", ints[j]);
//            }
//            System.out.println();
//        }
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == matrix.length
        //n == matrix[0].length
        //1 <= m, n <= 200
        //-2^31 <= matrix[i][j] <= 2^31 - 1
        //
        //- Brainstorm
        //
        //- Giả sử matrix[i][j]==0
        //+ matrix[0][j]=0
        //+ matrix[i][0]=0
        //--> Tức là
        // (i-1,j-1), (i-1,j)
        // (i,j-1),   (i,j)
        //+ Tức là (i,j)==0
        //  + Update (i-1,j), (i,j-1)
        //Ex:
        //{{0,1,2,0},
        // {3,4,5,2},
        // {1,0,1,5}}
        //
        //Ex:
        //{{0,0,2,0},
        // {3,4,5,2},
        // {0,0,1,5}}
        //
        //- Tức là matrix[i][0], matrix[j][0] là để mark để:
        //  + matrix[i][j]=0
        //
        //Ex:
        //{1,1,2,1},
        //{8,0,6,2},
        //{1,3,1,5}};
        //==>
        //{1,0,2,1},
        //{0,0,6,2},
        //{1,3,1,5}};
        //
        //- Ta cần chia ra 2 cases đặc biệt với:
        //  + i==0
        //  + j==0
        //--> Nếu các row or col đó được dùng để mark
        //- Tức là matrix[i][0], matrix[j][0] là để mark để:
        //  + matrix[i][j]=0
        //  + firstRowZero
        //  + firstColZero
        //==> Thế nên nó có thể xảy ra nhầm lẫn
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n*m)
        //
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}};
//        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
//        int[][] matrix = {
//                {0,1,2,0},
//                {3,4,0,2},
//                {1,3,1,5}};
//        int[][] matrix = {
//                {1,1,2,1},
//                {8,0,6,2},
//                {1,3,1,5}};
//        int[][] matrix = {
//                {1, 0, 2, 1},
//                {8, 0, 6, 2},
//                {1, 3, 1, 5}};
//        setZeroes(matrix);
        setZeroesOptimization(matrix);
        //#Reference:
        //289. Game of Life
        //2125. Number of Laser Beams in a Bank
        //2123. Minimum Operations to Remove Adjacent Ones in Matrix
    }
}
