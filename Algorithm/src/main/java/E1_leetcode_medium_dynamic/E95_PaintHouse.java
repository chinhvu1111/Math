package E1_leetcode_medium_dynamic;

public class E95_PaintHouse {

    public static int minCost(int[][] costs) {
        int n=costs.length;
        int[][] dp=new int[n][3];
        dp[0][0]=costs[0][0];
        dp[0][1]=costs[0][1];
        dp[0][2]=costs[0][2];

        for(int i=1;i<n;i++){

            for(int j=0;j<3;j++){
                int minVal=Integer.MAX_VALUE;

                for(int h=0;h<3;h++){
                    if(h!=j){
                        minVal=Math.min(minVal, dp[i-1][h]);
                    }
                }
                dp[i][j]=minVal+costs[i][j];
//                System.out.printf("i: %s, j: %s, val: %s\n", i, j, dp[i][j]);
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<3;i++){
            rs=Math.min(rs, dp[n-1][i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- There is a row of n houses, where each house can be painted one of three colors:
        //+ red
        //+ blue
        //+ green.
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
        //costs[i].length == 3
        //1 <= n <= 100
        //1 <= costs[i][j] <= 20
        //
        //- Brainstorm
        //- dp[i][j]: Là min cost khi fill color thứ (j) vào house thứ (i)
        //Ex:
        //Input: costs = [[1,5,3],[2,9,4]]
        //Output: 5
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
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        System.out.println(minCost(costs));
    }
}
