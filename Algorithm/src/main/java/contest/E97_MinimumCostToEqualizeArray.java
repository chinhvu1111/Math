package contest;

import java.util.Arrays;

public class E97_MinimumCostToEqualizeArray {

    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int n=nums.length;
        int max= Arrays.stream(nums).max().getAsInt();
        int[] dp=new int[n];

        for(int i=0;i<n;i++){

        }
        return 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given an integer array nums and two integers (cost1 and cost2).
        // You are allowed to perform either of the following operations any number of times:
        //  - Choose an (index i) from nums and (increase nums[i] by 1) for (a cost of cost1).
        //  - Choose two different (indices i, j), from nums and (increase nums[i] and nums[j] by 1) for a cost of (cost2).
        //* Return (the minimum cost) required to make (all elements) in the array (equal).
        //Since the answer may be very large, return it modulo 10^9 + 7.
        //Ex:
        //Input: nums = [4,1], cost1 = 5, cost2 = 2
        //Output: 15
        //
        //Explanation:
        //
        //The following operations can be performed to make the values equal:
        //  - Increase nums[1] by 1 for a cost of 5. nums becomes [4,2].
        //  - Increase nums[1] by 1 for a cost of 5. nums becomes [4,3].
        //  - Increase nums[1] by 1 for a cost of 5. nums becomes [4,4].
        //The total cost is 15.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //1 <= cost1 <= 10^6
        //1 <= cost2 <= 10^6
        //==> Khá to
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [2,3,3,3,5], cost1 = 2, cost2 = 1
        //- Mỗi lần tăng lên 1
        //==> Điểm đến cuối là max của all elements, để có thể đuổi kịp 1 số nào đấy:
        //rs= [5,5,5,5,5]
        //- cost2 tăng 2 index (i,j) ==> sẽ được 2 count:
        //  + Nếu cost2/cost1 >2 : Ta sẽ ưu tiên dùng cost2 cho những element có cùng count trước.
        //      + Do count của các element có thể >2
        //  + Nếu cost2/cost1 <2 : Ta có thể dùng all cost1
        //
        //- Count element > 2 thì dùng cost 2 ntn?
        //
    }
}
