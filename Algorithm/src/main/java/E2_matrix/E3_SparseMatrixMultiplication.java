package E2_matrix;

public class E3_SparseMatrixMultiplication {

    public static int[][] multiply(int[][] mat1, int[][] mat2) {
        int numRowMat1=mat1.length;
        int numRowMat2=mat2.length;
        if(numRowMat1==0||numRowMat2==0){
            return new int[][]{};
        }
        int numColMat2=mat2[0].length;
        int[][] rs =new int[numRowMat1][numColMat2];

        for(int i=0;i<numRowMat1;i++){
            for(int j=0;j<numColMat2;j++){
                int curVal=0;
                for(int h=0;h<numRowMat2;h++){
                    curVal+=mat1[i][h]*mat2[h][j];
                }
                rs[i][j]=curVal;
            }
        }
        return rs;
    }

    public static int[][] multiplyOptimization(int[][] mat1, int[][] mat2) {
        int numRowMat1=mat1.length;
        int numRowMat2=mat2.length;
        if(numRowMat1==0||numRowMat2==0){
            return new int[][]{};
        }
        int numColMat1=mat1[0].length;
        int numColMat2=mat2[0].length;
        //Space : O(numRowMat1*numColMat2)
        //Time : O(numRowMat1*numColMat2)
        int[][] rs =new int[numRowMat1][numColMat2];

        //Time : O(numRowMat1)
        for(int i=0;i<numRowMat1;i++){
            //Time : O(numColMat1)
            for(int j=0;j<numColMat1;j++){
                if(mat1[i][j]==0){
                    continue;
                }
                //Time : O(numColMat2)
                for(int h=0;h<numColMat2;h++){
                    rs[i][h]+=mat1[i][j]*mat2[j][h];
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Bài này chủ yếu sẽ là multiply matrix
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == mat1.length
        //k == mat1[i].length == mat2.length
        //n == mat2[i].length
        //1 <= m, n, k <= 100
        //-100 <= mat1[i][j], mat2[i][j] <= 100
        //
        //- for 3 lần thôi
        //- Ở đây mình sẽ lấy all value của (từng row) trong mat 1 * lần lượt với all value của (từng column) trong (mat2)
        //-
        //
        //1.1, Optimization
        //- Nếu value trước ==0 --> Ta có kết quả luôn không cần loop thêm
        //- Idea
        //Ex:
        // a b * | f g h |
        // d e   | i k l |
        //=
        // (a*f+b*i) | (a*g+b*k) | (a*h+b*l)
        // (d*f+e*i) | (d*g+e*k) | (d*h+e*l)
        //+ Ta sẽ thay đổi quy luật nhân matrix 1 chút:
        //+ Ta thấy là a,b sẽ tác động đến row có index=0
        //==> Ta sẽ tính theo từng cell nếu (a==0/b==0) ==> Ta không cần cộng dồn vào các row với index=0
        //+ Quy luật là:
        //  + Xét a: a*f, a*g, a*h (mat2 index row=0) ==> rs[row_index(mat1)][column_index(mat2)]
        //  + Xét b: b*i, b*k, b*l (mat2 index row=1)
        //--> Tối ưu được:
        //+ a==0 ==> Bỏ qua không cộng thêm.
        //
        //+ Tìm cách (nhân vào + cộng dồn)
        //
        //1.2, Complexity
        //- Time : O(numRowMat1*numColMat1*numColMat2)
        //- Space : O(numRowMat1*numColMat2)
        //
        //#Reference:
        //2306. Naming a Company
        //2087. Minimum Cost Homecoming of a Robot in a Grid
        //1338. Reduce Array Size to The Half
        //2581. Count Number of Possible Root Nodes
        //1491. Average Salary Excluding the Minimum and Maximum Salary
        //2347. Best Poker Hand
        int[][] mat1 = {{1,0,0},{-1,0,3}}, mat2 = {{7,0,0},{0,0,0},{0,0,1}};
        multiply(mat1, mat2);
        multiplyOptimization(mat1, mat2);
    }
}
