package contest;

public class E140_FindTheMaximumLengthOfValidSubsequenceII {

    public static int maximumLength(int[] nums, int k) {
        int n= nums.length;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
        }
        int[][] maxLen=new int[k][k];
        int rs=0;

        for(int i=0;i<n;i++){
            //x%k = y
            //k=5
            //1,3,1 ==> Ok
            int curVal=nums[i];

            for(int j=0;j<k;j++){
                maxLen[curVal][j]=maxLen[j][curVal]+1;
                rs=Math.max(rs, maxLen[curVal][j]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums and a positive integer k.
        //- A subsequence sub of nums with length x is called valid if it satisfies:
        //  + (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
        //* Return the length of the longest valid subsequence of nums.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 10^3 = 1000
        //1 <= nums[i] <= 10^7
        //1 <= k <= 10^3
        //+ k cũng không lớn lắm ==> O(n*k) được
        //
        //- Brainstorm
        //- x%k
        //  + 0 -> k-1
        //
        //Example 2:
        //+ Input: nums = [1,4,2,3,1,4], k = 3
        //+ Output: 4
        //+ Explanation:
        //The longest valid subsequence is [1, 4, 1, 4] = [1,1,1,1]
        //nums = [1,1,2,0,1,1]
        //- index=4
        //  + 1,2,1
        //  [1,2] = [2,1]+1
        //  + 1,1,1,1
        //  + 1,0
        //
        int[] nums = {1,4,2,3,1,4};
        int k = 3;
        System.out.println(maximumLength(nums, k));
    }
}
