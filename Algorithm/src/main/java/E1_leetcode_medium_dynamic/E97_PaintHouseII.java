package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E97_PaintHouseII {

    public static int minCostII(int[][] costs) {
        int n=costs.length;
        int k=costs[0].length;
        int[][] dp=new int[n][k];
        int[] minLeft=new int[k];
        int[] minRight=new int[k];

        for(int i=0;i<k;i++){
            dp[0][i]=costs[0][i];
        }

        //Nhớ đặt xe về quê thứ 5
        for(int i=1;i<n;i++){
            minLeft[0]=dp[i-1][0];
            minRight[k-1]=dp[i-1][k-1];

            for(int j=1;j<k;j++){
                minLeft[j]=Math.min(minLeft[j-1], dp[i-1][j]);
                minRight[k-j-1]=Math.min(minRight[k-j], dp[i-1][k-j-1]);
            }
//            for (int value : minLeft) {
//                System.out.printf("%s, ", value);
//            }
//            System.out.println();
//            for (int value : minRight) {
//                System.out.printf("%s, ", value);
//            }
//            System.out.println();
            for(int j=0;j<k;j++){
                int left=Integer.MAX_VALUE, right=Integer.MAX_VALUE;

                if(j>=1){
                    left=minLeft[j-1];
                }
                if(j<k-1){
                    right=minRight[j+1];
                }
                dp[i][j]=Math.min(left, right)+costs[i][j];
//                System.out.printf("i: %s, j: %s, val: %s, %s\n",i, j, Math.min(left, right), dp[i][j]);
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<k;i++){
            rs=Math.min(rs, dp[n-1][i]);
        }
        return rs;
    }

    public static int minCostIIOptimization(int[][] costs) {
        int n=costs.length;
        int k=costs[0].length;
        int[][] dp=new int[n][k];
        for(int i=0;i<k;i++){
            dp[0][i]=costs[0][i];
        }

        for(int i=1;i<n;i++){
            int firstMin=Integer.MAX_VALUE;
            int firstIndex=-1;
            int secondMin=Integer.MAX_VALUE;

            for(int j=0;j<k;j++){
                if(firstMin>dp[i-1][j]){
                    firstIndex=j;
                    if(secondMin>firstMin){
                        secondMin=firstMin;
                    }
                    firstMin=dp[i-1][j];
                }else if(secondMin>dp[i-1][j]){
                    secondMin=dp[i-1][j];
                }
            }
//            System.out.printf("%s %s\n", firstMin, secondMin);
            for(int j=0;j<k;j++){
                if(j!=firstIndex){
                    dp[i][j]=costs[i][j]+firstMin;
                }else{
                    dp[i][j]=costs[i][j]+secondMin;
                }
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<k;i++){
            rs=Math.min(rs, dp[n-1][i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- There is a row of n houses, where each house can be painted one of k colors:
        //- (The cost of painting) each house with (a certain color is different).
        //- Requirement:
        //- costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
        //- You have to paint all the houses such that (no two adjacent houses) have the same color.
        //* Return the minimum cost to (paint all houses).
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //costs.length == n
        //costs[i].length == k
        //1 <= n <= 100
        //2 <= k <= 20
        //1 <= costs[i][j] <= 20
        //
        //- Brainstorm
        //- dp[i][j] : Là min cost khi paint house thứ (i) color (j)
        //- Để tối ưu time từ:
        //+ O(N*K^2) -> O(N*K) cần giải bài toán con:
        //+ Given array= {1,2,6,7}
        //- Tìm phần tử min nhất của array exclude itself
        //  + Có thể giải trong O(N) bằng cách dùng prefixMin
        //array= {1,2,6,7}
        //- c = {1,1,1,1}
        //- PrefixMinRight = {1,2,6,7}
        //  ==> min trừ index=2 (val=6) = Min(PrefixMinRight[index-1], PrefixMinRight[index+1])
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(N*K+K)
        //- Time : O(N*K)
        //
        //2. Sắp xếp lại costs ==> Table
        //- Biến bài toán iện tại thành bài toán cơ bản hơn để giải
        //Ex:
        //int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        //==>
        //      RED | GREEN | BLUE
        //row1   17 |   2   | 17
        //row2   16 |  16   | 5
        //row3   14 |  3    | 19
        //- Ta đổi thành table
        //- Chuyển thành bài toán:
        //+ Chọn mỗi row duy nhất 1 column
        //+ Chọn sao cho column được chọn ở 2 row cạnh nhau là khác nhau
        //==> Chọn sao cho tổng cost là MIN
        //- Thực ra bài này thì bình thường vẫn giải là O(N*K^2), tối ưu lại.
        //
        //- Thực ra idea tối ưu ở đây là:
        //- Chọn từ row-0 --> row-(n-1):
        //  + Assume row-i có min=X (column-index=Y)
        //  + Row-(i+1) sẽ ưu tiên chọn previous cost=X + cộng vào all of columns trừ 1 column (column-index=Y)
        //      + Vậy ta điền gì vào đó?
        //        Ta sẽ điền second MIN của row-i vào đó ==> cộng thêm vào là được
        //--> Với row trước đó để không điền trùng column row sau:
        //+ Ta sẽ lấy FIRST MIN và SECOND MIN của previous row là được.
        //  + First min : Để điền cho hầu hết column sau.
        //  + Second min : Để điền vào colum trùng index trước đó.
        //** Chú ý:
        //+ Tìm first Min hay second Min cần:
        //  + if else ngay cả bên trong phần if(firstMin>dp[i-1][j]) vì:
        //    Nếu giá trị cũ của firstMin < secondMin thì sao: Ta cần update lại
        //    Không update lại thì sẽ bị case:
        //    Ex: 9,7,5,3,1 (Chuỗi giảm dần --> SecondMin sẽ không bao giờ được update)
        //
        //2.1, Optimization
        //- Có thể tối ưu space ==> O(1) được.
        //
        //2.2, Complexity:
        //- Space : O(n*k)
        //- Time : O(n*k)
//        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        int[][] costs = {{4,16},{15,5},{18,17},{10,12},{14,10},{3,10},{2,11},{18,14},{9,1},{14,13}};
//        int[][] costs = {{1,5,3},{2,9,4}};
        System.out.println(minCostII(costs));
        System.out.println(minCostIIOptimization(costs));
    }
}
