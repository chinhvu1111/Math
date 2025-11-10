package E1_daily;

public class E367_WaysToMakeAFairArray {

    public static int waysToMakeFairOptimization(int[] A) {
        int res = 0, n = A.length, left[] = new int[2], right[] = new int[2];
        for (int i = 0; i < n; i++)
            right[i%2] += A[i];
        for (int i = 0; i < n; i++) {
            right[i%2] -= A[i];
            //left_event + right_odd == left_odd + right_even
            if (left[0]+right[1] == left[1]+right[0]) res++;
            left[i%2] += A[i];
        }
        return res;
    }

    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] prefixOdd=new int[n];
        int[] prefixEven=new int[n];
        int[] suffixOdd=new int[n];
        int[] suffixEven=new int[n];

        int prefixOddSum=0;
        int prefixEvenSum=0;
        int suffixOddSum=0;
        int suffixEvenSum=0;
        int sum=0;

        for (int i = 0; i < n; i++) {
            if(i%2==0){
                prefixEvenSum+=nums[i];
            }else{
                prefixOddSum+=nums[i];
            }
            prefixOdd[i]=prefixOddSum;
            prefixEven[i]=prefixEvenSum;
            if((n-i-1)%2==0){
                suffixEvenSum+=nums[n-i-1];
            }else{
                suffixOddSum+=nums[n-i-1];
            }
            suffixOdd[n-i-1]=suffixOddSum;
            suffixEven[n-i-1]=suffixEvenSum;
            sum+=nums[i];
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("PrefixEven[i]: %s, PrefixOdd[i]: %s\n", prefixEven[i], prefixOdd[i]);
//            System.out.printf("SuffixEven[i]: %s, SuffixOdd[i]: %s\n", suffixEven[i], suffixOdd[i]);
//        }
        int rs=0;
        for(int i=0;i<n;i++){
            if(i%2==1){
                int leftSumOdd = prefixOdd[i-1];
                int rightSumOdd = (i+1)<n?suffixEven[i+1]:0;
                int currentOdd = leftSumOdd + rightSumOdd;
//                System.out.printf("left: %s, right: %s, val: %s\n", leftSumOdd, rightSumOdd, currentOdd);
                if(sum-nums[i]==2*currentOdd){
                    rs++;
                }
            }else{
                int leftSumEven = (i-1)>=0?prefixEven[i-1]:0;
                int rightSumEven = (i+1)<n?suffixOdd[i+1]:0;
                int currentEven = leftSumEven + rightSumEven;
                if(sum-nums[i]==2*currentEven){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums).
        //- You can choose exactly one index (0-indexed) and remove the element.
        //* Notice that the index of the elements may change after the removal.
        //
        //- For example, if nums = [6,1,7,4,1]:
        //Choosing to remove index 1 results in nums = [6,7,4,1].
        //Choosing to remove index 2 results in nums = [6,1,4,1].
        //Choosing to remove index 4 results in nums = [6,1,7,4].
        //- An array is (fair) if (the sum of the "odd"-indexed values) equals (the sum of the "even"-indexed values).
        //* Return the number of indices that you could choose such that after the removal, nums is fair.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^4
        //  + Time: O(n)
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: nums = [2,1,6,4]
        //Output: 1
        //Explanation:
        //Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
        //Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
        //Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
        //Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
        //There is 1 index that you can remove to make nums fair.
        //- Remove 1 index at (i):
        //  + left sum is unchanged
        //  + left and right sum is swapped
        //      + if i is odd:
        //          + sum(odd) = sum(odd) + sum(right_even) - sum(right_odd)
        //      + if i is even:
        //          + sum(even) = sum(even) + sum(right_odd) - sum(right_even)
        //
        //- Keep the order ==> queue/stack
        //- Binary search
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //- left_even + right_odd == left_odd + right_even
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(n)
        //
        int[] nums = {1,1,1};
        System.out.println(waysToMakeFair(nums));
        System.out.println(waysToMakeFairOptimization(nums));
    }
}
