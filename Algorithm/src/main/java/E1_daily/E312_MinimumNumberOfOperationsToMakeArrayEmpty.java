package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E312_MinimumNumberOfOperationsToMakeArrayEmpty {

    public static int minOperations(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for (int i = 0; i < n; i++) {
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
        }
//        System.out.println(mapCount);
        int rs=0;
        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            if(e.getValue()%3==0){
                rs+=e.getValue()/3;
            }else if(e.getValue()>=2&&(e.getValue()-2)%3==0){
                rs+=(e.getValue()-2)/3+1;
            }else if(e.getValue()>=4&&(e.getValue()-4)%3==0){
                rs+=(e.getValue()-2)/3+2;
            } else{
                return -1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed array nums consisting of positive integers.
        //- There are two types of operations that you can apply on the array (any) (number of times):
        //  + Choose (two elements) with (equal values) and delete them from the array.
        //  + Choose (three elements) with (equal values) and delete them from the array.
        //* Return (the minimum number of operations) required to make (the array empty),
        // or -1 if it is not possible.
        //
        //Example 1:
        //
        //Input: nums = [2,3,3,2,2,4,2,3,4]
        //Output: 4
        //Explanation: We can apply the following operations to make the array empty:
        //- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
        //- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
        //- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
        //- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
        //It can be shown that we cannot make the array empty in less than 4 operations.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //* Main point:
        //10 = 3*2+2*2
        // x = 3*k+2*l
        //- Check:
        //x=3*k+2
        //x=3*k+2*2
        //==> It is enough
        //x=3*k+2*3 ==> Always (x%3) ==0
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //
        //
        int[] nums = {14,12,14,14,12,14,14,12,12,12,12,14,14,12,14,14,14,12,12};
        System.out.println(minOperations(nums));
    }
}
