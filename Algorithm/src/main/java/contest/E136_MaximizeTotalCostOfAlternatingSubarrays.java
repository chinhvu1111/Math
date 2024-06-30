package contest;

public class E136_MaximizeTotalCostOfAlternatingSubarrays {

    public static long maximumTotalCost(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        long[][] dp=new long[n][2];
        long rs=Long.MIN_VALUE;
        dp[0][0]=nums[0];
        dp[0][1]=Integer.MIN_VALUE;
        rs=dp[0][0];

        for(int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]) + nums[i];
            dp[i][1]=dp[i-1][0] - nums[i];
            rs=Math.max(dp[i][0], dp[i][1]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums with length n.
        //- The cost of a subarray nums[l..r], where 0 <= l <= r < n, is defined as:
        //  + cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)^r − l
        //- Your task is to split nums into subarrays
        //  + such that (the total cost of the subarrays) is maximized,
        //  + ensuring (each element) belongs to (exactly one) subarray.
        //- Formally, if nums is split into k subarrays, where k > 1, at indices (i1, i2, ..., ik − 1),
        // where 0 <= i1 < i2 < ... < ik - 1 < n - 1, then the total cost will be:
        //  + cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
        //* Return an integer denoting (the maximum total cost of the subarrays) after (splitting the array optimally).
        //Note: If nums is not split into subarrays, i.e. k = 1, the total cost is simply cost(0, n - 1).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //+ Length khá lớn ==> O(n), O(n*log(n)
        //+ Số cũng khá lớn ==> Long
        //
        //- Brainstorm
        //- Method:
        //  + Dynamic, prefix sum, slide window
        //- Để có được sum max thì:
        //  + Nên ưu tiên các cost dương hết
        //- Các phần tử sẽ có dấu:
        //  + Âm: index trong array % 2 == 1
        //  + Dương: index trong array % 2 == 0
        //Ex:
        //Input: nums = [1,-2,3,4]
        //Output: 10
        //1+(-2)*(-1)+3 - 4 ==> Vì trừ đi 4 nên tách ra array riêng
        //- Có thể làm khi gặp (- số dương) thì tách ra không:
        //Ex:
        //Input: nums = [1,-2,3,4,10]
        //1+(-2)*(-1)+3 - 4 ==> Không nên tách 4 ra vì
        //  + -4+10 > 4-10
        //-> Nhưng ở đây mình hoàn toàn có thể tách +4 +10 riêng rẽ ra là được
        //==> 4 âm không thêm vào là được.
        //Ex:
        //Input: nums = [1,-2,-3,4,10]
        //  + (1 + 2 - 3) + 4 + 10 = 14
        //  + 1 + (-2 + 3) + 4 + 10 = 16
        //  ==> Nên tách -2 với -3 riêng ra
        //+ Chọn nhóm -2 với -3 ntn
        //
        //- Nếu add thêm số dương vào giữa -2 và -3
        //  ==> -3 chỉ cần dựa vào số dương trước đó --> Thành số dương >=0
        //Ex:
        //Input: nums = [1,-2,6,7,-3,4,10]
        //
        //- (a<0), (b<0)
        //  + nếu b<a ==> nhóm (a,b)
        //- (a>0), (b<0)
        //  + nhóm (a,b) : a+b
        //
        //Ex:
        //Input: nums = [1,-2,-3,-10,-4]
        //  + (1 + 2)+(-3+10-4)
        //  + (1 + 2-3+10-4) = 6
        //  + (1)+(-2+3-10+4)
        //  + (1)+(-2)+(-3+10-4)
        //
        //- 1 số có thể nhận dấu (-/+)
        //  + Dấu + : dp[i][0] = max(dp[i-1][0], dp[i-1][1]) + nums[i]
        //  + Dấu - : dp[i][0] = dp[i-1][0] - nums[i]
        //
//        int[] nums = {1,-2,3,4};
//        int[] nums = {1,-1,1,-1};
//        int[] nums = {0};
//        int[] nums = {1,-1};
//        int[] nums = {1,-2,-3,-10,-4};
        int[] nums = {1,-2,-3,4,10};
        System.out.println(maximumTotalCost(nums));
    }
}
