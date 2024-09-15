package E1_daily;

public class E85_Convert1DArrayInto2DArray {

    public static int[][] construct2DArray(int[] original, int m, int n) {
        if(n*m!=original.length){
            return new int[][]{};
        }
        int[][] rs=new int[m][n];

        for(int i=0;i<n*m;i++){
            //Ex:
            //n=2,m=3
            //1,2,3,4,5,6
            //0,1 : index=0
            //3,4 : index=1
            //5,6 : index=2
            int rowIndex = i/n;
            int colIndex = i%n;
            rs[rowIndex][colIndex] = original[i];
        }
        return rs;
    }

    public int[][] construct2DArrayBasic(int[] original, int m, int n) {
        // Check if it is possible to form an m x n 2D array
        if (m * n != original.length) {
            // If not, return an empty 2D array
            return new int[0][0];
        }

        // Initialize the result 2D array with m rows and n columns
        int resultArray[][] = new int[m][n];

        // Initialize a counter to track the current index in the original array
        int index = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Assign the current element from original array to the 2D array
                resultArray[i][j] = original[index];
                // Move to the next element in the original array
                index++;
            }
        }

        return resultArray;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n.
        //- You are tasked with creating a 2-dimensional (2D) array with  m rows and n columns
        // using all the elements from original.
        //- The elements from indices (0 to n - 1) (inclusive) of original should form the first row of the constructed
        // 2D array,
        // the elements from indices (n to 2 * n - 1) (inclusive) should form the second row of the constructed 2D array,
        // and so on.
        //* Return an (m x n 2D array) constructed according to the above procedure,
        // or an empty 2D array if it is impossible.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //- CT:
        //- rs[i/n][i%n] = nums[index++]
        //  + n là số lượng columns
        //
//        int[] original = {1,2,3,4};
//        int m = 2, n = 2;
//        int[] original = {1,2,3};
//        int m = 1, n = 3;
        int[] original = {1,2};
        int m = 1, n = 1;
//        int[][] rs= construct2DArrayBasic(original, m, n);
        int[][] rs= construct2DArray(original, m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
