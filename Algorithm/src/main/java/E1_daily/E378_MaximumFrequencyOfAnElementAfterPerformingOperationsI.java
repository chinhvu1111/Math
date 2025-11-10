package E1_daily;

import java.util.Arrays;
import java.util.HashMap;

public class E378_MaximumFrequencyOfAnElementAfterPerformingOperationsI {

    public static int findLowerIndex(int[] nums, int target){
        int low=0, high=nums.length-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(nums[mid]>=target){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int findUpperIndex(int[] nums, int target){
        int low=0, high=nums.length-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]<=target){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static int maxFrequency(int[] nums, int k, int numOperations) {
        int n=nums.length;
        Arrays.sort(nums);
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int lastIndexOfVal=0;
        int rs=0;

        for (int i = 0; i < n; i++) {
            if(nums[i]!=nums[lastIndexOfVal]){
                mapCount.put(nums[lastIndexOfVal], i-lastIndexOfVal);
                rs=Math.max(rs, i-lastIndexOfVal);
                lastIndexOfVal=i;
            }
        }
        mapCount.put(nums[lastIndexOfVal], nums.length-lastIndexOfVal);
        rs=Math.max(rs, nums.length-lastIndexOfVal);

        for(int i=nums[0];i<=nums[n-1];i++){
            int l=findLowerIndex(nums, i-k);
            int r=findUpperIndex(nums, i+k);
//            System.out.printf("%s %s\n", l, r);

            if(l==-1||r==-1){
                continue;
            }

            int temRs;
            if(mapCount.containsKey(i)){
                temRs=Math.min(r-l+1, mapCount.get(i)+numOperations);
            }else{
                temRs=Math.min(r-l+1, numOperations);
            }
            rs=Math.max(rs, temRs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums and two integers (k and numOperations)
        //- You must perform an operation (numOperations) times on nums, where in each operation you:
        //  + Select (an index i) that was not selected in (any previous operations).
        //  + Add (an integer) in the range [-k, k] to nums[i].
        //* Return (the maximum possible frequency) of any element in nums after performing the operations.
        //
        //Example 1:
        //Input: nums = [1,4,5], k = 1, numOperations = 2
        //Output: 2
        //Explanation:
        //We can achieve a maximum frequency of two by:
        //Adding 0 to nums[1]. nums becomes [1, 4, 5].
        //Adding -1 to nums[2]. nums becomes [1, 4, 4].
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 105
        //1 <= nums[i] <= 105
        //0 <= k <= 105
        //0 <= numOperations <= nums.length
        //  + length <= 10^5 ==> time: O(n*k)
        //
        //* Brainstorm:
        //Example 1:
        //Input: nums = [1,4,5], k = 1, numOperations = 2
        //Output: 2
        //==> max frequency that we can make after doing numOperations
        //  + Do exact numOperations time for the array
        //+ dp[n][k] ==> Invalid (TLE)
        //- Sort nums[1,4,5]
        //
        //- Binary search:
        //- Find the (max frequency)
        //- 1 <= result <= n
        //- Sub-problem:
        //  + result=x ==> check whether the x is valid or not
        //- nums = [1,4,5], k = 1, numOperations = 2
        //- Prove that max(count) = x
        //
        //- Select (an index i) that was not selected in (any previous operations).
        //  ==> (Each index) need to be done an operation (at least one time)
        //
        //- 1,0,-1,0....: ==> Check this???
        // 0[1,4,5]: count[1]=1, count[4]=1, count[5]=1
        //+1:[2,5,6]: count[2]=1, count[5]=1, count[6]=1
        //-1:[0,3,4]: count[0]=1, count[3]=1, count[4]=1
        //- Hash[value][k]
        //  + O(n*n) ==> TLE
        //
        //==> Find the path with (max frequency)
        //
        //* Understand the requirement in the wrong way:
        //- Add integer between [-k,k]
        //- Each element has the value from [min, max]
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
        int[] nums = {1,4,5};
        int k = 1, numOperations = 2;
        System.out.println(maxFrequency(nums, k, numOperations));
    }
}
