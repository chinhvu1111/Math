package E1_daily;

import java.util.Stack;

public class E192_NumberOfValidSubarrays {

    public static int validSubarrays(int[] nums) {
        int n=nums.length;
        int rs=0;

        Stack<Integer> stack=new Stack<>();

        for(int i=0;i<n;i++){
            while (!stack.isEmpty()&&nums[i]<nums[stack.peek()]){
                rs+=i-stack.pop();
            }
            stack.add(i);
        }
        while (!stack.isEmpty()){
            rs+=n-stack.pop();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums,
        //* Return the number of (non-empty subarrays)
        // with the (leftmost element) of the subarray (not larger than) other elements in the subarray.
        //- A subarray is a contiguous part of an array.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 5 * 10^4
        //0 <= nums[i] <= 10^5
        //
        //- Brainstorm
        // nums = [1,4,2,5,3]
        //- 1: [4,2,5,3]
        //- 2: [5,3]
        //- Go from right -> left:
        //Ex:
        //2<=x
        //==> 3 could be <=x
        //  + We just decrease index of 3
        //Ex:
        //7,4,[6],5
        //  + nums[i] <= nums[j]
        //==> Check nums[i],...,nums[j]
        //  + nums[i-1]<=nums[j]:
        //      + Keep(j)
        //  + nums[i-1]>nums[j]:
        //      + j--
        //      + But between [i,j] we can have the elements are greater than nums[j]???
        //
        //- Subarray --> Continuous
        //- x<=y<=z
        //  + x1>x ==> break
        //
        //nums = [1,4,2,5,3]
        //- Go from right to left
        //- index=4
        //  + stack = [4]
        //- index=3
        //  + stack = [3]
        //- index=2
        //  + stack = [2,3]
        //- index=1
        //  + stack = [1,2,3]
        //==> Confusion
        //nums = [2,4,6,7]
        //[2],[2,4],[2,4,6],[2,4,6,7]
        //  + rs+(4-0) = 4
        //nums = [5,7,3,4,6,7,1]
        //[5],[5,7]
        //[3],[3,4],[3,4,6],[3,4,6,7] = 4
        //[4],[4,6],[4,6,7] = 3
        //  + rs+=4+3+2+1 ==> n*(n+1)
        //  ==> Increase for each element
        //      + rs+=(i-index_peek())
        //- If we don't have the last element that break the consequence
        //  + rs+=(n-1)-(stack.pop())
        //
        int[] nums = {1,4,2,5,3};
        System.out.println(validSubarrays(nums));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //1403. Minimum Subsequence in Non-Increasing Order
        //2713. Maximum Strictly Increasing Cells in a Matrix
        //3183. The Number of Ways to Make the Sum
    }
}
