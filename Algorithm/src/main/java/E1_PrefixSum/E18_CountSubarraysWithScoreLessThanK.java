package E1_PrefixSum;

public class E18_CountSubarraysWithScoreLessThanK {

    public static int findMinIndex(long[] prefixSum, long k, int index){
        int low=0, high=index;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            long leftSum=mid==0?0:prefixSum[mid-1];
            long curSum = (prefixSum[index]-leftSum)*(index-mid+1);
            if (curSum<k) {
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static long countSubarrays(int[] nums, long k) {
        int n=nums.length;
        long sum=0;
        long[] prefixSum=new long[n];

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        long rs=0;
        for (int i = 0; i < n; i++) {
            int leftIndex=findMinIndex(prefixSum, k, i);
            if(leftIndex!=-1){
                rs+=i-leftIndex+1;
            }
        }
        return rs;
    }

    public static long countSubarraysSlideWindow(int[] nums, long k) {
        int n = nums.length;
        long res = 0, total = 0;
        for (int i = 0, j = 0; j < n; j++) {
            total += nums[j];
            while (i <= j && total * (j - i + 1) >= k) {
                total -= nums[i];
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- The score of an array is defined as the product of its sum and its length.
        //  + For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
        //- Given (a positive integer array nums) and (an integer k),
        //* return (the number of (non-empty subarrays) of nums) whose score is strictly (less than) k.
        //- A subarray is a contiguous sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: nums = [2,1,4,3,5], k = 10
        //Output: 6
        //Explanation:
        //The 6 subarrays having scores less than 10 are:
        //- [2] with score 2 * 1 = 2.
        //- [1] with score 1 * 1 = 1.
        //- [4] with score 4 * 1 = 4.
        //- [3] with score 3 * 1 = 3.
        //- [5] with score 5 * 1 = 5.
        //- [2,1] with score (2 + 1) * 2 = 6.
        //Note that subarrays such as [1,4] and [4,3,5]
        // are not considered because their scores are 10 and 36 respectively, while we need scores strictly less than 10.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //1 <= k <= 10^15
        //  + nums.length <= 10^5 ==> Time: O(n*k)
        //  + k <= 10^15 ==> Long
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: nums = [2,1,4,3,5], k = 10
        //Output: 6
        //- nums[i] >=1:
        //  + (a+b)*2 > a*1
        //- Find (the max length) <=10
        //- (Prefix sum + binary search)
        //
        //- For each index, find the longest subarray end with (i) such that:
        //  + sum < k
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //* Slide window:
        //- a,(b,c,d),e,f
        //  + (b+c+d)*3<k
        //      + rs+=3
        //- a,(b,c,d,e),f
        //  + (b+c+d+e)*4>=k
        //      + ==> i++
        //  + (c+d+e)*3<k
        //      + rs+=3
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n*log(n)) ==> O(n)
        //
        int[] nums = {2,1,4,3,5};
        int k = 10;
        System.out.println(countSubarrays(nums, k));
        System.out.println(countSubarraysSlideWindow(nums, k));
        //
        //#Reference:
        //2875. Minimum Size Subarray in Infinite Array
        //1394. Find Lucky Integer in an Array
        //3134. Find the Median of the Uniqueness Array
    }
}
