package contest;

import java.util.ArrayList;
import java.util.List;

public class E322_MakeArrayNondecreasing {

    public static int maximumPossibleSize(int[] nums) {
        List<Integer> list=new ArrayList<>();
        int n=nums.length;

        for (int i = 0; i < n; i++) {
            if(list.isEmpty()){
                list.add(nums[i]);
            }else if(list.get(list.size()-1)<=nums[i]){
                list.add(nums[i]);
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums).
        //- In one operation, you can select (a subarray) and replace it with (a single element) equal to (its maximum value).
        //* Return (the maximum possible size of the array) after (performing zero or more operations)
        // such that (the resulting array) is (non-decreasing).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: nums = [4,2,5,3,5]
        //
        //Output: 3
        //
        //Explanation:
        //
        //One way to achieve the maximum size is:
        //
        //Replace subarray nums[1..2] = [2, 5] with 5 → [4, 5, 3, 5].
        //Replace subarray nums[2..3] = [3, 5] with 5 → [4, 5, 5].
        //The final array [4, 5, 5] is non-decreasing with size 3.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 2 * 105
        //1 <= nums[i] <= 2 * 105
        //  + Time: O(n*k)
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [4,2,5,3,5]
        //Output: 3
        //size = n-2 = 5-2 = 3
        //
        //Ex:
        //nums = [3,2,5]
        //==> Remove 2
        //
        //
        int[] nums = {4,2,5,3,5};
        System.out.println(maximumPossibleSize(nums));
    }
}
