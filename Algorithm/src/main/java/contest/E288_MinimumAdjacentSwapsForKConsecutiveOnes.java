package contest;

import java.util.ArrayList;
import java.util.List;

public class E288_MinimumAdjacentSwapsForKConsecutiveOnes {

    public static int minMoves(int[] nums, int k) {
        int n=nums.length;
        int size;
        List<Integer> indicesOne=new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(nums[i]==1){
                indicesOne.add(i);
            }
        }
        size=indicesOne.size();
//        System.out.println(size);
        int[] prefixSum=new int[size+1];
        int sum=0;

        for(int i=0;i<size;i++){
            sum+=indicesOne.get(i);
            prefixSum[i+1]=sum;
        }
        int rs=Integer.MAX_VALUE;

        if(k%2==1){
            int r=(k-1)/2;
            for(int i=r;i<size-r;i++){
                //0,1,2,3,4
                int right = prefixSum[i+r+1]-prefixSum[i+1];
                int left = prefixSum[i]- prefixSum[i-r];
                rs=Math.min(rs, right-left);
            }
            return rs-r*(r+1);
        }
        int r=(k-2)/2;
        for(int i=r;i<size-r-1;i++){
            //0,1,2,3,4
            int right = prefixSum[i+r+2]-prefixSum[i+1];
            int left = prefixSum[i]- prefixSum[i-r];
            rs=Math.min(rs, right-left-indicesOne.get(i));//Wrong indicesOne.get(i) --> Phải lấy index từ arr[i]
        }
        return rs-r*(r+1)-(r+1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array, nums, and an integer k.
        // nums comprises of only 0's and 1's.
        //- In one move, you can choose two (adjacent indices) and (swap their values).
        //* Return (the minimum number of moves) required so that nums has (k consecutive 1's).
        //
        //Example 1:
        //Input: nums = [1,0,0,1,0,1], k = 2
        //Output: 1
        //Explanation:
        //  + In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //nums[i] is 0 or 1.
        //1 <= k <= sum(nums)
        //  + Lenth <= 10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- 1 <=> 1 is redundant
        //Example 1:
        //Input: nums = [1,0,0,1,0,1], k = 2
        //[1,0,0,1,1,1,0,1], k=5
        //[0,1,0,1,1,1,0,1]
        //[0,0,1,1,1,1,0,1]
        //[0,0,1,1,1,1,1,0]: rs=3
        //
        //[1,0,0,1,1,1,0,1], k=4
        //[1,0,0,1,1,1,1,0], rs = 1 rather than:
        //  + [0,1,0,1,1,1,0,1]
        //  + [0,0,1,1,1,1,0,1]: rs = 2
        //
        //- If we have:
        //  + [0,(0),0,(1),0,0,(1)]
        //  + The purpose is to swap (0) ==> We need to use (the nearest 1 value)
        //Ex:
        //Input: nums = [1,0,0,1,0,1], k = 2
        //- index=0:
        //  [1,0], nearest index with 1 value = 3
        //      + rs+=3-1=2
        //  [1,1]
        //- If we have some positions with zero value:
        //  + (The nearest index) is not enough ==> Because we only choose (each index) (only 1 time)
        //
        //- If we add new bit + remove the previous bit:
        //  + We need to update the number of steps
        //  => If we use the nearest position:
        //      + We also need to check from the both of directions (left, right)
        //Ex:
        //[0,0,(0),0,0,(1),0,1,0,1], k=3
        //  + Target: [0,0,(1,1,1),0,0,0,0,0]
        //- How to find the number of steps to move?
        //Ex:
        //[0,0,(0,0,0),1,0,1,0,1], k=3
        //- If we move the bit for 0,0,0:
        //  + There are some overlaping areas
        //- How we accumulate them in the optimal way?
        //Ex:
        //[0,0,(0,0,0),1,0,1,0,1], k=3
        //+ sum = 3+4+5 = 12
        //
        //- Thay vì quan tâm đến số 0 ==> Ta chỉ quan tâm đến số 1:
        //Ex:
        //arr                   = [1,0,1,0,1,0,1,0,1,1,1], k = 5
        //indices of one val    = [0,2,4,6,7,9,10,11]
        //- For the [0,2,4,6,7] ==> 5 of consecutive 1 value
        //- index=0:
        //  + Không có cách nào khác là move 1 bit từ (right -> left)
        //- [0,2,4,6,7] => [2,3,4,5,6]
        //  + Why is [2,3,4,5,6] mà không phải là:
        //  [0,1,2,3,4]
        //- [0,2,4,6,7] => [2,3,4,5,6]
        //  + cost: (2-0) + (3-2) + (4-4) + (6-5) + (7-6) = 2+1+1+1 = 5
        //- [0,2,4,6,7] => [0,1,2,3,4]
        //  _ cost: (2-1)+(4-2)+(6-3)+(7-4) = 1+2+3+3= 9
        //Ex:
        //[1,2,10]
        //==> Chọn 6 là điểm med chưa chắc tối ưu nhất
        //  ==> Ta sẽ chọn điểm mà chính giữa để các index đến đó min nhất:
        //      + med=(low+high)/2
        //* ==> Không liên quan đến việc chọn med (Hay nói khác đi việc chọn giữa:
        //  + [0,2,4,6,7] => [2,3,4,5,6] / [0,2,4,6,7] => [0,1,2,3,4] không ý nghĩa
        //
        //- Ta sẽ scan all index từ (i-> n-raius) để lấy các window size = radius
        //* Main point:
        //- i: 0 -> n-1
        //  +  i is median index
        //  + Công thức Math:
        //- Ta thấy rằng:
        //+ [0,2,4,6,7] => [2,3,4,5,6]
        //  [0,2,4,6,7] => [4,4,4,4,4] => + left + - right
        //      + cost = (6+7) - (2+0) = prefix sum helps
        //  [4,4,4,4,4] => [2,3,4,5,6] => trừ left + right
        //      + radius as r = 2
        //      + (4-2)+(4-3)+(5-4)+(6-4) = (r+1)*r
        //- If k is odd:
        //  + Cost = prefix_sum - (r+1)*r
        //- If k is even:
        //  + Cost = prefix_sum- i - (r+1)*r - (r+1)
        //
        //1.1, Cases
        //
        //1.2, Optimization
        //1,3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2009. Minimum Number of Operations to Make Array Continuous
        //2340. Minimum Adjacent Swaps to Make a Valid Array
        int[] nums = {1,0,0,1,0,1};
        int k = 2;
        System.out.println(minMoves(nums, k));
    }
}
