package E1_daily;

import java.util.Arrays;

public class E267_HouseRobberIV {

    public static int solution(int[] nums, int k){
        int n=nums.length;
        int low=0;
        int high= Arrays.stream(nums).max().getAsInt();
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            int count=0;
            int prevIndex=-1;

            for(int i=0;i<n;i++){
                if(nums[i]<=mid&&(prevIndex!=i-1||prevIndex==-1)){
                    prevIndex=i;
                    count++;
                    if(count==k){
                        break;
                    }
                }
            }
            if(count==k){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int minCapability(int[] nums, int k) {
        return solution(nums, k);
    }

    public static int minCapabilityRefer(int[] nums, int k) {
        // Store the maximum nums value in maxReward.
        int minReward = 1;
        int maxReward = Arrays.stream(nums).max().getAsInt();
        int totalHouses = nums.length;

        // Use binary search to find the minimum reward possible.
        while (minReward < maxReward) {
            int midReward = (minReward + maxReward) / 2;
            int possibleThefts = 0;

            for (int index = 0; index < totalHouses; ++index) {
                if (nums[index] <= midReward) {
                    possibleThefts += 1;
                    index++; // Skip the next house to maintain the
                    // non-adjacent condition
                }
            }

            if (possibleThefts >= k) maxReward = midReward;
            else minReward = midReward + 1;
        }

        return minReward;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are several (consecutive houses) along a street, each of which has (some money) inside.
        //- There is also a robber, who wants to steal money from the homes, but he (refuses) to steal from (adjacent homes).
        //- The capability of the robber is (the maximum amount of money) he steals from one house of all the houses he robbed.
        //- You are given (an integer array nums) representing how much money is (stashed) in each house.
        //- More formally, (the ith house) from the left has nums[i] dollars.
        //- You are also given an integer k,
        //  representing (the "minimum" number of houses) the robber will steal from.
        //- It is always possible to steal (at least k houses).
        //* Return (the minimum capability of the robber) out of all the possible ways to steal at least k houses.
        //
        //Example 1:
        //
        //Input: nums = [2,3,5,9], k = 2
        //Output: 5
        //Explanation:
        //There are three ways to rob at least 2 houses:
        //- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
        //- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
        //- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
        //Therefore, we return min(5, 9, 9) = 5.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //1 <= k <= (nums.length + 1)/2
        //  + Length <= 10^5 ==> Time: O(n)
        //  + val <= 10^9 ==> Long
        //      ==> Binary search
        //
        //* Brainstorm:
        //- Binary search
        //- Min of max
        //
        //- Check whether the specific x is valid:
        //  + x>= k values
        //- How to check?
        //Ex:
        //nums = [2,3,5,9,1,7,8,3,2,1], k = 4
        //[2,3,5,9,1,7,8,3,2,1]
        //[2, ,5, ,1, ,8,2]
        //[2, ,5,,1,3,1]
        //  ==> From 1 to 3: We ignore 8
        //* NOTE:
        //- At least k houses
        //
        //[2,3,5,9,1,7,8,3,2,1]
        //- We predict max = 5
        //==> [0,0,1,0,1,0,0,1,1,1]
        //- How about?
        //==> [0,0,1,1,0,1,0,0,1,1,1]
        //==> Check consecutive characteristic
        //
        //* Không cần phải lo việc lựa chọn có ảnh hưởng đến kết quả hay không
        //  => (Tức là ignore house ==> Không ảnh hưởng đến kết quả)
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(max_val))
        //- Space: O(log(n)) -> O(1)
        //
        int[] nums = {2,3,5,9};
        int k = 2;
//        int rs=minCapability(nums, k);
        int rs=minCapabilityRefer(nums, k);
        System.out.println(rs);
        //
        //#Reference:
        //1351. Count Negative Numbers in a Sorted Matrix
        //3036. Number of Subarrays That Match a Pattern II
        //2448. Minimum Cost to Make Array Equal
    }
}
