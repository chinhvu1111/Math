package interviews;

import java.util.HashMap;
import java.util.HashSet;

public class E190_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap=new HashMap();
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(hashMap.containsKey(target-nums[i])){
                return new int[]{i, hashMap.get(target-nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr=new int[]{2,7,11,15};
        //
        //
        //
        //#Reference:
        //
        //- 4Sum
        //- Two Sum III - Data structure design
        //- Two Sum Less Than K
        //- Count Number of Pairs With Absolute Difference K
        //- Number of Pairs of Strings With Concatenation Equal to Target
        //- Find All K-Distant Indices in an Array
        //- First Letter to Appear Twice
        //- Number of Excellent Pairs
        //- Number of Arithmetic Triplets
        //- Node With Highest Edge Score
        //- Check Distances Between Same Letters
        //- Find Subarrays With Equal Sum
        //- Largest Positive Integer That Exists With Its Negative
    }
}
