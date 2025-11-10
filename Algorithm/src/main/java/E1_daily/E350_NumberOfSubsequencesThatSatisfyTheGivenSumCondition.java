package E1_daily;

import java.util.Arrays;

public class E350_NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

    public static int numSubseq(int[] nums, int target) {
        int n=nums.length;
        Arrays.sort(nums);
        int rs=0;
        int mod = 1_000_000_007;
        int[] power=new int[n];
        power[0]=1;

        for (int i = 1; i < n; i++) {
            power[i]=(power[i-1]*2)%mod;
        }

        for (int i = 0; i < n; i++) {
            int low=i, high=n-1;
            int expectedRight = target-nums[i];
            int curRs=-1;
            while(low<=high){
                int mid=low+(high-low)/2;
                if(nums[mid]<=expectedRight){
                    curRs=mid;
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            if(curRs==-1){
                break;
            }
            //0,1,2
            rs=(rs+power[curRs-i])%mod;
//            System.out.printf("%s %s\n", curRs, power[curRs-i]);
        }
        return rs;
    }

    public static int numSubseqTwoPointers(int[] nums, int target) {
        int n = nums.length;
        int mod = 1_000_000_007;
        Arrays.sort(nums);

        // Precompute the value of 2 to the power of each value.
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++i) {
            power[i] = (power[i - 1] * 2) % mod;
        }

        int answer = 0;
        int left = 0, right = n - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                answer += power[right - left];
                answer %= mod;
                left++;
            } else {
                right--;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of integers nums) and (an integer target).
        //* Return the number of (non-empty subsequences of nums)
        // such that (the sum) of (the minimum) and (maximum element) on it is (less or equal) to target.
        //* Since the answer may be too large, return it modulo 10^9 + 7.
        //
        //Example 1:
        //
        //Input: nums = [3,5,6,7], target = 9
        //Output: 4
        //Explanation: There are 4 subsequences that satisfy the condition.
        //[3] -> Min value + max value <= target (3 + 3 <= 9)
        //[3,5] -> (3 + 5 <= 9)
        //[3,5,6] -> (3 + 6 <= 9)
        //[3,6] -> (3 + 6 <= 9)
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //Constraints:
        //
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //1 <= target <= 10^6
        //  + nums[i] <= 10^6 ==> Time: O(log(n))
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //Ex:
        //Input: nums = [3,5,6,7], target = 9
        //  + [3,5]<=9
        //max = [3,5,6,7]
        //min = [3,5,6,7]
        //  + If we use suffix and prefix that is quite hard to control min/max in range (i,j)
        //- For subsequence:
        //  + Sort array
        //- For nums[i] ==> find max(j) such that:
        //  + nums[i]+nums[j]<=target
        //Ex:
        //nums = [1,3,5,(6)]
        //  + [1,6]
        //  + [1,3,5,6 ]
        //  + [1,3,6]
        //  + [1,5,6]
        //  + [3,6]
        //  + [3,5,6]
        //- min/max = [3,6]
        //  + Check all of subsequences:
        //      + [3,6]
        //      + [3,5,6]
        //      Without the [...1]
        //+ [0,right]=2^1+2^2+2^3+...+2^n
        //  + right is used to combine with [0,left]
        //  + total of cases = 1+2+2^2+...+2^(right-1)
        //      ==> Mark = power[right-1]
        //  ==> rs = total - power[right-left-1]
        //
        //Ex:
        //(0,...,3)
        //  + 0,0
        //  + 0,1
        //  + 1,0
        //  + 1,1
        //  ==> 2^2
        //Ex:
        //- If we use (right) as (pivot)
        //[0,1,2,(3),4,(5)]
        //  + [0,1,2,3]...[5]
        //Ex:
        //- If we use (left) as (pivot)
        //[0,1,2,(3),4,5,(6),7]
        //  + [(3),...,(6)]
        //** ==> this way is better
        //** Ex:
        //- Choosing the pivot is very important.
        //nums = [3,5,6], target=9
        //  + [3,3]
        //  + [3,5]
        //  + [3,5,6]
        //  + [3,6]
        //** NOTE:
        //[3,5,6]
        //<=>
        //[1,0,0]
        //=> 0,0: 4 cases
        //* How about [3,5]
        //  ==> [1,1] ==> We have got that
        //  ==> currentRs=2^[right-left]
        //  + (i) is (0-indexed) ==> (right-left) is enough.
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Combining with two pointers method
        //** Sort:
        //  + We can use (two pointers) method
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(1)
        //
        int[] nums = {3,5,6,7};
        int target = 9;
        System.out.println(numSubseq(nums, target));
        System.out.println(numSubseqTwoPointers(nums, target));
    }
}
