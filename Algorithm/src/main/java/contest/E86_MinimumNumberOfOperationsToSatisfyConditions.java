package contest;

import java.util.Arrays;

public class E86_MinimumNumberOfOperationsToSatisfyConditions {

    public static int solution(int index, int[][] countNum, int[][]dp, int n, int m, int prevVal){
        if(index>=m){
            return 0;
        }
        if(dp[index][prevVal]!=-1){
            return dp[index][prevVal];
        }
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<=9;i++){
            if(i!=prevVal){
                int curChange = n- countNum[i][index];
                int newVal = curChange+solution(index+1, countNum, dp, n, m, i);
                rs=Math.min(rs, newVal);
//                System.out.printf("index=%s, i=%s, newVal: %s\n", index, i, newVal);
            }
        }
        return dp[index][prevVal]=rs;
    }

    public static int minimumOperations(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] countNum=new int[10][m];
        int[][] dp=new int[m][11];

        for(int i=0;i<m;i++){
            Arrays.fill(dp[i], -1);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int curVal=grid[j][i];
                countNum[curVal][i]++;
            }
        }
        return solution(0, countNum, dp, n, m, 10);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D matrix grid of size m x n.
        //- In one operation, you can change the value of (any cell) to (any non-negative number).
        // You need to perform some operations such that each cell grid[i][j] is:
        //  + Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
        //  + Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
        //* Return the minimum number of operations needed.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n, m <= 1000
        //0 <= grid[i][j] <= 9
        //
        //- Brainstorm
        //- Bài này do các cell liên quan đến nhau
        //  + Nên cần tìm rule change value.
        //- Case nào mà ta cần change right value + trùng hợp nó giống down value của chính thằng change luôn
        //Ex:
        //2 2 1
        //1 3 2
        //->
        //2 3 1
        //1 3 2
        //=> rs=1
        //
        //Ex:
        //2 2 1
        //1 3 3
        //-> 2 to 3
        //2 3 1
        //1 3 3
        //-> 3==3 -> 3 to 4
        //2 3 1
        //1 3 4
        //- Thử case nó ảnh hưởng đến cả kết quả sau
        //Ex: Thêm vài columns nữa
        //2 2 1 2 4
        //1 3 3 4 5
        //-> 2 to 3
        //2 3 1 2 (4)
        //1 3 3 4  5
        //-> 4 to 5 để 4==5 bên dưới
        //2 3 1 2 5
        //1 3 3 4 5
        //* Ta thấy rằng:
        //- current == down
        //- current != right
        //
        //- Trong 1 column toàn bộ value phải bằng nhau.
        //- Trong 1 row các giá trị khác nhau theo pair.
        //
        //Ex:
        //2 2 1 2 4
        //1 3 3 4 5
        //-> col=0: 1 to 2
        //2 2 1 2 4
        //2 3 3 4 5
        //- Vấn đề ta cần quan tâm là:
        //  + Đổi value làm sao cho 2 column liên tiếp cho số lần change là minimal
        //- Có các cách changes sau:
        //  + Đổi thành 1 số mới tinh ==> Không có trong next col
        //  + Đổi thành 1 số có trong next col + count của nó là ít nhất ==> Để giảm số lần đổi sau
        //  + Đổi thành 1 số có trong next col + có trong current col thoả mãn:
        //      + count của nó ít nhất trong current col
        //      + count của nó ít nhất trong next col
        //
        int[][] grid = {{1,0,2},{1,0,2}};
        System.out.println(minimumOperations(grid));
    }
}
