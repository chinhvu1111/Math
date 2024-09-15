package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E169_PaintHouseIII {

    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //Space: O(m*n*target)
        int[][][] dp=new int[m][n+1][target+1];
        int rs = Integer.MAX_VALUE;

        for(int i=0;i<m;i++){
            for(int j=0;j<=n;j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        if(houses[0]==0){
            for(int i=1;i<=n;i++){
                dp[0][i][1]=cost[0][i-1];
            }
        }else{
            dp[0][houses[0]][1]=0;
        }

        //Time: O(100*20*100)
        //Time: O(m*n^2*target)
        for(int i=1;i<m;i++){
            //The previous colors
            for(int j=1;j<=n;j++){
                //The previous groups
                for(int k=0;k<=target;k++){
                    if(dp[i-1][j][k]==Integer.MAX_VALUE){
                        continue;
                    }
                    //== color
                    if(houses[i]==0){
                        dp[i][j][k] = Math.min(dp[i-1][j][k] + cost[i][j-1], dp[i][j][k]);
                    }else if(j==houses[i]){
                        dp[i][houses[i]][k] = Math.min(dp[i-1][j][k], dp[i][j][k]);
                        continue;
                    }else {
                        // House ith has been painted
                        // ==> Tính dựa trên previous house (i-1)th
                        if(k+1<=target){
                            //Paint color của house[i] thôi ==> Chỉ tính cho color này thôi
                            dp[i][houses[i]][k+1] = Math.min(dp[i-1][j][k], dp[i][houses[i]][k+1]);
                        }
                        continue;
                    }
                    //!= color
                    for(int c=1;c<=n;c++){
                        //different colors
                        if(c!=j&&k<target){
                            dp[i][c][k+1] = Math.min(dp[i-1][j][k] + cost[i][c-1], dp[i][c][k+1]);
                        }
                    }
                }
            }
        }
        for (int pos = 0; pos< m ; pos++) {
            for(int i=1;i<=n;i++){
                for(int j=1;j<=target;j++){
                    System.out.printf("index= %s, color = %s, group number: %s, value: %s\n", pos, i, j, dp[pos][i][j]);
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(dp[m-1][i][target]!=Integer.MAX_VALUE){
                rs=Math.min(rs, dp[m-1][i][target]);
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    // Maximum cost possible plus 1
    static final int MAX_COST = 1000001;

    public static int minCostSpaceOptimization(int[] houses, int[][] cost, int m, int n, int target) {
        int[][] prevMemo = new int[target + 1][n];

        // Initialize prevMemo array
        for (int i = 0; i <= target; i++) {
            Arrays.fill(prevMemo[i], MAX_COST);
        }

        // Initialize for house 0, neighborhood will be 1
        for (int color = 1; color <= n; color++) {
            if (houses[0] == color) {
                // If the house has same color, no cost
                prevMemo[1][color - 1] = 0;
            } else if (houses[0] == 0) {
                // If the house is not painted, assign the corresponding cost
                prevMemo[1][color - 1] = cost[0][color - 1];
            }
        }

        for (int house = 1; house < m; house++) {
            int[][] memo = new int[target + 1][n];

            // Initialize memo array
            for (int i = 0; i <= target; i++) {
                Arrays.fill(memo[i], MAX_COST);
            }

            for (int neighborhoods = 1; neighborhoods <= Math.min(target, house + 1); neighborhoods++) {
                for (int color = 1; color <= n; color++) {

                    // If the house is already painted, and color is different
                    if (houses[house] != 0 && color != houses[house]) {
                        // Cannot be painted with different color
                        continue;
                    }

                    int currCost = MAX_COST;
                    // Iterate over all the possible color for previous house
                    for (int prevColor = 1; prevColor <= n; prevColor++) {
                        if (prevColor != color) {
                            // Decrement the neighborhood as adjacent houses has different color
                            currCost = Math.min(currCost, prevMemo[neighborhoods - 1][prevColor - 1]);
                        } else {
                            // Previous house has the same color, no change in neighborhood count
                            currCost = Math.min(currCost, prevMemo[neighborhoods][color - 1]);
                        }
                    }

                    // If the house is already painted cost to paint is 0
                    int costToPaint = houses[house] != 0 ? 0 : cost[house][color - 1];
                    memo[neighborhoods][color - 1] = currCost + costToPaint;
                }
            }
            // Update the table to have the current house results
            prevMemo = memo;
        }

        int minCost = MAX_COST;
        // Find the minimum cost with m houses and target neighborhoods
        // By comparing cost for different color for the last house
        for (int color = 1; color <= n; color++) {
            minCost = Math.min(minCost, prevMemo[target][color - 1]);
        }

        // Return -1 if the answer is MAX_COST as it implies no answer possible
        return minCost == MAX_COST ? -1 : minCost;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (a row of m houses) in a small city, (each house) must be painted (with one of the n colors) (labeled from 1 to n),
        // some houses that (have been painted last summer) (should not be painted) again.
        //- A neighborhood is (a maximal group of continuous houses) that are painted with (the same color).
        //  + For example:
        //  houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
        //- Given an array houses, an m x n matrix cost and an integer target where:
        //  + houses[i]: is (the color) of (the house i), and 0 if (the house is not painted yet).
        //  + cost[i][j]: is (the cost of paint) (the house i) with (the color j + 1).
        //* Return (the ("minimum") cost of painting all the remaining houses) in such a way that there are exactly ("target") (neighborhoods).
        // If (it is not possible), return -1.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //m == houses.length == cost.length
        //n == cost[i].length
        //1 <= m <= 100
        //1 <= n <= 20
        //1 <= target <= m
        //0 <= houses[i] <= n
        //1 <= cost[i][j] <= 10^4
        //  + m và n không quá lớn ==> O(n*m*k) đều được
        //
        //- Brainstorm
        //- return -1, khi:
        //  + target < số group hiện tại
        //  + target > số group có thể tạo ra được.
        //
        //- Đi điền color để get được min value:
        //Example 2:
        //
        //Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
        //Output: 11
        //Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
        //This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
        //Cost of paint the first and last house (10 + 1) = 11.
        //+ houses = [0,2,1,2,0]
        //- Xét đến vị trí i, ta có thể điền color j:
        //  + j khác color của house (i-1)th
        //      + Khác color thì số group sẽ +1
        //  + j giống color của house (i-1)th
        //      + Số group không đổi
        //
        //- Init value:
        //- Với các house đã điền rồi:
        //  + Ta sẽ chỉ map cho chính color của house đó thôi
        //
        //- dp[i][j][k]: cost min để paint color (jth) cho house (ith) mà có group là k
        //  + check so sánh house (ith) với house (i-1)th:
        //      + all of colors
        //          + all of groups
        //      + dp[i-1][j][k]
        //          + Nếu cùng color thì sẽ map ra trực tiếp:
        //              dp[i][j][k] = dp[i-1][j][k]
        //          + Khác color
        //              dp[i][j][k+1] = dp[i-1][j][k]
        //
        //- Ta chỉ cần phân biệt 2 houses có cùng color hay không là được.
        //Ex:
        //colors: [1,2]
        //0,2,1,2,0
        //1,2, cost = cost[0][0] + 0 = 1
        //2,2, cost = cost[0][1] + 0 = 10
        //
        //Ex:
        //houses = {0,2,1,2,0}
        //1,2,1,2,1
        //  + rs=1+5 = 6
        //  ==> Nhưng group = 5 (Wrong)
        //
        //- Chú ý case:
        // House ith has been painted before
        //  + Ta dựa trên previous house (i-1)th có color (x) sẽ tính được cho house[i] (Color has been painted)
        //  ==> Chú ý là tính color == house[i] thay thay vì color của the previous houses.
        //
        //* Ký năng debug:
        //  + Trace lên trên ==> tìm case bắt đầu xảy ra sai logic để tính toán
        //
        //- Special cases:
//        int[] houses = {0,2,1,2,0};
//        int[][] cost = {{1,10},{10,1},{10,1},{1,10},{5,1}};
//        int m = 5, n = 2, target = 3;
//        int[] houses = {0,0,0,0,0};
//        int[][] cost = {{1,10},{10,1},{10,1},{1,10},{5,1}};
//        int m = 5, n = 2, target = 3;
        //Ex:
        //house = [1,2,2,1,1]
        //costs = [1,1,1,1,5]
        //==> Sai do đoạn for bên trong cần tính theo:
        //  + cost[i][c-1] (Không phải theo cost[i][j-1])
        //
        int[] houses = {0,0,2,3};
        int[][] cost = {{5,4,1},{1,2,1},{4,4,2},{5,2,5}};
        int m = 4, n = 3, target = 4;
        //Ex:
        //house = [3,1,2,3]
        //costs = [1,1,0,0]
        //==> Sai do đoạn cộng cost cho:
        //  + house[i]!=j:
        //      + Tính theo cost[i][house[i]-1] thay vì là cost[i][j-1]
        //
        //1.1, Optimization
        //* Kinh nghiệm:
        //  - Nếu ta chỉ cần kết quả của previou house ==> Ta sẽ chỉ cần lưu lại kết quả cho previous house
        //  ==> Không cần lưu thêm 1 chiều index nữa ta chuyển từ:
        //      + dp[m][n][k] ==> dp[n][k]
        //
        //1.2, Complexity
        //- Time: O(m*n^2*target)
        //- Space: O(m*n*target)
        //
        System.out.println(minCostSpaceOptimization(houses, cost, m, n, target));
        System.out.println(minCost(houses, cost, m, n, target));
        //#Reference:
        //2318. Number of Distinct Roll Sequences
    }
}
