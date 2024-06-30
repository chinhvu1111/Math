package contest;

public class E139_FindTheMaximumLengthOfValidSubsequenceI {

    public static int maximumLength(int[] nums) {
        int n= nums.length;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%2;
        }
        int[] maxLen=new int[2];
        int[] maxLen1=new int[2];

        for(int i=0;i<n;i++){
            //1,0,1
            if(nums[i]==0){
                maxLen[0]=Math.max(1, maxLen[1]+1);
            }else{
                maxLen[1]=Math.max(1, maxLen[0]+1);
            }
            //1,1
            //0,0
            if(nums[i]==0){
                maxLen1[0]=Math.max(1, maxLen1[0]+1);
            }else{
                maxLen1[1]=Math.max(1, maxLen1[1]+1);
            }
        }
        return Math.max(maxLen[0], Math.max(maxLen[1], Math.max(maxLen1[0], maxLen1[1])));
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums.
        //- (A subsequence sub of nums) with (length x) is called valid if it satisfies:
        //  + (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
        //* Return the length of the longest valid subsequence of nums.
        //A subsequence is an array that can be derived from another array by (deleting some) or (no elements)
        // without changing the order of the remaining elements.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 2 * 10^5
        //1 <= nums[i] <= 10^7
        //==> Time : O(n)
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: nums = [1,2,1,1,2,1,2]
        //Output: 6
        //Explanation:
        //The longest valid subsequence is [1, 2, 1, 2, 1, 2].
        //+ (1+3)%2 ==0
        //+ 1%2 = 1
        //+ 3%2= 1
        //==> (1+1)%2 = 0
        //
        //- Convert ==> 0,1
        //
        //Example 2:
        //Input: nums = [1,2,1,1,2,1,2]
        //=> [1,0,1,1,0,1,0]
        //- [0] => [1]
        //
        //1,1
//        int[] nums = {1,3};
//        int[] nums = {1};
//        int[] nums = {2,2,3,2,2};
//        int[] nums = {1,1,3,1,1};
        int[] nums = {1,1,2,1,1};
        System.out.println(maximumLength(nums));
    }
}
