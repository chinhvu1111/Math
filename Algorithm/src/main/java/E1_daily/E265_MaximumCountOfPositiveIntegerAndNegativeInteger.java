package E1_daily;

public class E265_MaximumCountOfPositiveIntegerAndNegativeInteger {

    public static int searchUpperBound(int key, int[] nums){
        int low=0, high=nums.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]>key){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int searchLowerBound(int key, int[] nums){
        int low=0, high=nums.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]<key){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static int maximumCount(int[] nums) {
        int maxNegativeIndex=searchLowerBound(0, nums);
//        System.out.println(maxNegativeIndex);
        int minPositiveIndex=searchUpperBound(0, nums);
//        System.out.println(minPositiveIndex);
        int countNeg=0;
        int countPos=0;

        if(maxNegativeIndex!=-1){
            countNeg=Math.max(countNeg, maxNegativeIndex+1);
        }
        if(minPositiveIndex!=-1){
            countPos=Math.max(countPos, nums.length-minPositiveIndex);
        }
        return Math.max(countNeg, countPos);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array nums (sorted in non-decreasing order),
        //* return the maximum between (the number of positive integers) and (the number of negative integers).
        //- In other words, if (the number of positive integers) in nums is (pos) and (the number of negative integers) is (neg),
        // then return (the maximum of pos and neg).
        //- Note that 0 is (neither) positive nor negative.
        //
        //Example 1:
        //
        //Input: nums = [-2,-1,-1,1,2,3]
        //Output: 3
        //Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 2000
        //-2000 <= nums[i] <= 2000
        //nums is sorted in a non-decreasing order.
        //
        //- Brainstorm
        //
        //
        //1.1, Cases
        //
        //
        //1.2, Optimize
        //
        //
        //1.3, Complexity
        //
        //
        int[] nums = {-2,-1,-1,1,2,3};
        System.out.println(maximumCount(nums));
    }
}
