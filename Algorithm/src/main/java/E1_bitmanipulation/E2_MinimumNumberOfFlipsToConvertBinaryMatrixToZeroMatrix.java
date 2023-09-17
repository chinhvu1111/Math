package E1_bitmanipulation;

public class E2_MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    public static int [][] dir={{-1,0},{0, 1}, {1, 0}, {0, -1}};

    public int minFlips(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<1<<m;i++){
            int[][] arrTmp=new int[n][m];

            for(int j=0;j<n;j++){
                for(int h=0;h<m;h++){
                    arrTmp[j][h]=mat[j][h];
                }
            }
            int countFlip=0;

            for(int j=0;j<m;j++){
                if(((i>>j)&1)==1){
                    flipbit(0, j, arrTmp);
                    countFlip++;
                }
            }

            for(int j=1;j<n;j++){
                for(int h=0;h<m;h++){
                    if(arrTmp[j-1][h]==1){
                        flipbit(j, h, arrTmp);
                        countFlip++;
                    }
                }
            }
            boolean isLastRowAllZero=true;

            for(int j=0;j<m;j++){
                if(arrTmp[n-1][j]!=0){
                    isLastRowAllZero=false;
                    break;
                }
            }
            // System.out.printf("%s %s\n", i, countFlip);
            if(isLastRowAllZero){
                rs=Math.min(rs, countFlip);
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }
    public static void flipbit(int x, int y, int[][] mat){
        mat[x][y]^=1;
        int l=dir.length;
        int n=mat.length;
        int m=mat[0].length;

        for(int i=0;i<l;i++){
            int x1=dir[i][0]+x;
            int y1=dir[i][1]+y;

            if(x1>=0&&x1<n&&y1>=0&&y1<m){
                mat[x1][y1]^=1;
            }
        }
    }
    public static void main(String[] args) {
        //** Đề bài:
        //- Given m x n binary matrix mat
        //- Each step we can flip one of the cell and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1).
        // A pair of cells are called neighbors if they share one edge.
        //* Return the (minimum) number of steps required to convert mat to a zero matrix or -1 if you cannot.
        //+ A binary matrix is a matrix with all cells equal to 0 or 1 only.
        //+ A zero matrix is a matrix with all cells equal to 0.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //m == mat.length
        //n == mat[i].length
        //1 <= m, n <= 3
        //mat[i][j] is either 0 or 1.
        //
        //- Brainstorm
        //- Với mỗi ô chỉ cần tác động tối đa 1 lần ==> Vì flip 2 lần ==> Qyay về ban đầu
        //- Order không quan trọng --> vì flip thì result như nhau
        //- Chọn cells nào để flip
        //* Dạng bài này:
        //- Cần cố định phần nào đó ==> Xét all cases hàng đó
        //- Sau đó từ từng case đó suy luận ra các cases còn lại
        //Example:
        //+ Như trong trường hợp này ta cần cố định first row
        //==> Sau đó từng xét từng case của first row ==> Dựa vào đó flip các row còn lại.
        //
        //#Reference:
        //2123. Minimum Operations to Remove Adjacent Ones in Matrix
        //2128. Remove All Ones With Row and Column Flips
        //2174. Remove All Ones With Row and Column Flips II
    }
}
