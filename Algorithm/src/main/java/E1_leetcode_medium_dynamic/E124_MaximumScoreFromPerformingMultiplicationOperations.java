package E1_leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.HashMap;

public class E124_MaximumScoreFromPerformingMultiplicationOperations {

    public static int[][] memo;

    public static int solution(
            int[] nums, int[] multipliers,
            int first, int last, int index, int n, int m) {
        if (index >= m || first > last || first >= n || last < 0) {
            return 0;
        }
        if (memo[first][last] != -1) {
            return memo[first][last];
        }
        int curRs = solution(nums, multipliers, first + 1, last, index + 1, n, m) + nums[first] * multipliers[index];
        curRs = Math.max(curRs, solution(nums, multipliers, first, last - 1, index + 1, n, m) + nums[last] * multipliers[index]);
        return memo[first][last] = curRs;
    }

    public static int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        memo = new int[n][n];

        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return solution(nums, multipliers, 0, n - 1, 0, n, m);
    }

    public static HashMap<Pair, Integer> memo1;

    public static int solution1(
            int[] nums, int[] multipliers,
            int first, int last, int index, int n, int m) {
        if (index >= m || first > last || first >= n || last < 0) {
            return 0;
        }
        Pair pair = new Pair(first, last);
        if (memo1.containsKey(pair)) {
            return memo1.get(pair);
        }
        int curRs = solution1(nums, multipliers, first + 1, last, index + 1, n, m) + nums[first] * multipliers[index];
        curRs = Math.max(curRs, solution1(nums, multipliers, first, last - 1, index + 1, n, m) + nums[last] * multipliers[index]);
        memo1.put(pair, curRs);
        return curRs;
    }

    public static int maximumScoreTopDown(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        memo1 = new HashMap<>();

        return solution1(nums, multipliers, 0, n - 1, 0, n, m);
    }

    public static int maximumScoreBottomUp(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        //Ex:
        //nums = [1,2,3], multipliers = [3,2,1]
        //- Ta có m = len(multipliers) cách chọn (first or last)
        //dp[i] là gì?
        //- Số lượng operation --> Liên quan đến first or last
        //dp[k] : k là số lượng operation
        //- dp[k-1] tính theo dp[k] ntn?
        //- Giả sử :
        //+ dp[k][first] : chính là số lượng value tối đa nếu ta thực hiện với (k operations) + cho đến first index
        int[][] dp=new int[m+1][m+1];

        //
        for(int i=1;i<=m;i++){
            //dp[i][j]
            //Ex:
            //nums = [1,2,3],
            //multipliers = [3,2,1]
            //- Xét all of left value
            for(int j=i;j>=1;j--){
                //3,2,3,1,2
                //i=1 => i=1 (5-i-1)
                // (j-1) (j)
                dp[i][j]=Math.max(dp[i-1][j-1]+ multipliers[i-1]*nums[j-1], dp[i-1][j+1]+ multipliers[i-1]*nums[j-1]);
            }
        }
        //
        return dp[m][m];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given two 0-indexed integer arrays nums and multipliers of size n and m respectively, where n >= m.
        //You begin with a score of 0. You want to perform exactly m operations. On the ith operation (0-indexed) you will:
        //- Choose one integer x from either the (start) or the (end) of the array nums.
        //- Add (multipliers[i] * x) to your score.
        //* Note that multipliers[0] corresponds to the (first) operation, multipliers[1] to the (second) operation, and so on.
        //- Remove x from nums.
        //- Lấy first or last để nhân với multipliers[i]
        //  * Return max score sau khi thực hiện m operations
        //* Return (the maximum score) after performing (m operations).
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == nums.length
        //m == multipliers.length
        //1 <= m <= 300
        //m <= n <= 10^5
        //-1000 <= nums[i], multipliers[i] <= 1000
        //==> Không quá lớn nên ta có thể thực hiện được với O(n^2)
        //
        //- Brainstorm
        //- Bài này có thể làm được bottom up không quá khó
        //- Nếu dùng dp[n][n] ==> Memory limit
        // + ==> Dùng HashMap< Pair<Integer, Integer>, Integer> map : Để thành n pairs thay vì (n*n) elements.
        //
        //#Reference:
        //1423. Maximum Points You Can Obtain from Cards
        //1690. Stone Game VII
        //2931. Maximum Spending After Buying Items
        //
        int[] nums = {1,2,3}, multipliers = {3,2,1};
//        int[] nums = {-5, -3, -3, -2, 7, 1}, multipliers = {-10, -5, 3, 4, 6};
        System.out.println(maximumScore(nums, multipliers));
        System.out.println(maximumScoreTopDown(nums, multipliers));
        System.out.println(maximumScoreBottomUp(nums, multipliers));
    }
}
