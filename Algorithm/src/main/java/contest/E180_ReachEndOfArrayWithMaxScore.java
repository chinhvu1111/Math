package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E180_ReachEndOfArrayWithMaxScore {

    public static long findMaximumScore1(List<Integer> nums) {
        int n = nums.size();
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(dp[j], dp[i] + (j - i) * (long)nums.get(i));
            }
        }

        return dp[n - 1];
    }

    static long memo[];
    public static long solution(int index, List<Integer> nums, int n){
        if(index==n){
            return 0;
        }
        long maxRs=0;
        if(memo[index]!=-1){
            return memo[index];
        }
        for(int i=index+1;i<n;i++){
            maxRs=Math.max(maxRs, solution(i, nums, n)+(i-index)*(long) nums.get(index));
        }
        return memo[index]=maxRs;
    }

    public static long findMaximumScore(List<Integer> nums) {
        int n=nums.size();
        memo=new long[n];
        Arrays.fill(memo, -1);
        solution(0, nums, n);
        return memo[0];
    }

    public static long findMaximumScoreOptimization(List<Integer> nums) {
        int n = nums.size();
        long curVal=nums.get(0);
        int prevIndex = 0;
        long rs=0;

        for (int i = 1; i < n-1; i++) {
            if(nums.get(i)>curVal){
                rs+=curVal*(i-prevIndex);
                curVal=nums.get(i);
                prevIndex=i;
            }
        }
        rs+=curVal*(n-1-prevIndex);
        return rs;
    }

    public static long findMaximumScoreOptimization1(List<Integer> nums) {
        long curVal=0;
        long rs=0;

        for (Integer num : nums) {
            rs += curVal;
            curVal = Math.max(curVal, num);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums of length n.
        //Your goal is to start at (index 0 and reach index n - 1). You can only jump to indices greater than your current index.
        //The score for a jump from index i to index j is calculated as (j - i) * nums[i].
        //* Return (the maximum possible total score) by the time you reach the last index.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //
        //** Brainstorm
        //
        //
        //** Kinh nghiệm
        //- Đánh giá xem có thực hiện được không:
        //  + memo có 1 dimension
        //  + bên trong 1 loop
        //  ==> Time: O(n^2)
        //      + Nếu n<=10^5 ==> Không thực hiện được.
        //
        //- Để lấy được max score:
        //  + Chọn giữa:
        //      + nums[i] đi đến nums[n-1]
        //          + score = nums[i]*(n-1-i)
        //      + nums[i] đi đến nums[i+k] và nums[i+1] đi đến nums[n-1]:
        //          + score = nums[i]*k + nums[i+k]*(n-1-i-k)
        //      ==> Ta thấy rằng ta sẽ ưu tiên chọn số lớn hơn
        //          + Nếu nums[i]>nums[i+k]:
        //              + Ta sẽ đi trực tiếp từ (i) -> (n-1)
        //          + Nếu nums[i]<=nums[i+k]:
        //              + Ta sẽ đi từ (i) -> (i+k) -> (n-1)
        //
        //Ex:
        //nums = [4,3,1,3,2]
        //- init val = 4
        //  + Nếu có số đằng sau >4:
        //      + Có switch ngay không?
        //      Ex:
        //      nums = [4,3,(5),1,(7),2]
        //      4 -> 5 -> 7
        //          + Nên switch ngay:
        //          + Vì nếu reach đến 7:
        //              + 4 value sẽ được tính vào kết quả
        //          + Không switch:
        //              + Score = 4*(4-0) + 7*(1)
        //          + Switch:
        //              + Score = 4*(2-0) + 5*(2) + 7*(1)
        //          ==> Bên dưới lớn hơn ==> Nên switch
        //
        //1.1, Optimization
        //- Ta có thể bỏ đi mấy đoạn if else comparison vì:
        //  + 1 unit ta sẽ cộng lên 1 value ==> Chứ không cần đợi đến khi (nums[i]> nums[prevIndex])
        //      + rs+=curVal
        //      + curVal=Max(nums[i],curVal)
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        Integer[] nums = {1,3,1,5};
        Integer[] nums = {4,3,1,3,2};
        List<Integer> numList=new ArrayList<>();
        numList.addAll(Arrays.asList(nums));
        System.out.println(findMaximumScore1(numList));
        System.out.println(findMaximumScore(numList));
        System.out.println(findMaximumScoreOptimization(numList));
        System.out.println(findMaximumScoreOptimization1(numList));
//        System.out.println(findMaximumScore(numList));
        //#Reference:
        //1353. Maximum Number of Events That Can Be Attended
        //2982. Find Longest Special Substring That Occurs Thrice II
        //911. Online Election
        //2541. Minimum Operations to Make Array Equal II
        //240. Search a 2D Matrix II
        //2753. Count Houses in a Circular Street II
    }
}
