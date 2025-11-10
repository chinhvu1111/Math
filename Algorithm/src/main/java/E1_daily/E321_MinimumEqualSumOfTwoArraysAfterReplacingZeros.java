package E1_daily;

public class E321_MinimumEqualSumOfTwoArraysAfterReplacingZeros {

    public static long minSum(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int count1=0, count2=0;
        long sum1=0, sum2=0;

        for(int i=0;i<n;i++){
            if(nums1[i]==0){
                count1++;
            }
            sum1+=nums1[i];
        }
        for(int i=0;i<m;i++){
            if(nums2[i]==0){
                count2++;
            }
            sum2+=nums2[i];
        }
//        if(sum1>sum2){
//            System.out.printf("sub: sum1>sum2: %s\n", sum1-sum2);
////            if(sum1-sum2+count1<=count2* 9L&&sum1-sum2+count1*9L>=count2){
////                return sum1+count1;
////            }
//            if(sum1-sum2+count1>=count2){
//                return sum1+count1;
//            }
//        }else {
//            System.out.printf("sub: sum1<=sum2: %s\n", sum1-sum2);
////            System.out.printf("%s\n", sum2-sum1+count2<=count1* 9L);
////            System.out.printf("%s\n", sum2-sum1+count2*9L>=count1);
////            if(sum2-sum1+count2<=count1* 9L&&sum2-sum1+count2*9L>=count1){
////                return sum2+count2;
////            }
//            if(sum2+count2>=sum1+count1){
//                //sum2 + [0,0] ==> sum1 + [0,0,0,0]
//                //sum2 + [0,0,0,0] ==> sum1 + [0,0]
//                //  + Max(sum1 + count1, sum2 + count2)
//                return sum2+count2;
//            }
//        }
        if(count1==0&&count2>sum1-sum2){
            return -1;
        }
        if(count2==0&&count1>sum2-sum1){
            return -1;
        }
        return Math.max(sum2+count2, sum1+count1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two arrays nums1 and nums2) consisting of (positive integers).
        //- You have to replace all (the 0's) in both arrays with (strictly positive integers)
        // such that (the sum of elements) of (both arrays) becomes ("equal").
        //* Return (the minimum equal sum) you can obtain, or -1 if it is impossible.
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
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums1.length, nums2.length <= 10^5
        //0 <= nums1[i], nums2[i] <= 10^6
        //  + Time: nums1.length, nums2.length <= 10^5 ==> Time: O(n)
        //
        //Example 1:
        //
        //Input: nums1 = [3,2,0,1,0], nums2 = [6,5,0]
        //Output: 12
        //Explanation: We can replace 0's in the following way:
        //- Replace the two 0's in nums1 with the values 2 and 4.
        //      + The resulting array is nums1 = [3,2,2,1,4].
        //- Replace the 0 in nums2 with the value 1.
        //      + The resulting array is nums2 = [6,5,1].
        //Both arrays have an equal sum of 12. It can be shown that it is (the minimum sum) we can obtain.
        //
        //* Brainstorm:
        //- Transfer the problem to:
        //  + [0,0,0,...] - [0,0..] = diff between 2 sums
        //- sum1<sum2
        //  + diff = (sum2-sum1)- count2(0)
        //
        //- Wrong understood:
        //  + Replace ==> max number
        //
        //- We don't have the number limit <=9
        //- If both sum1 and sum2 have count1 and count2 that are not equal to 0
        //  + ==> We can find the way to replace
        //- We need to check (count1 and count2) (respectively)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
//        int[] nums1 = {3,2,0,1,0}, nums2 = {6,5,0};
        int[] nums1 = {2,0,2,0}, nums2 = {1,4};
        //1+4-(2+2) = 5 - 4 = 1 < 1*count1
        //
//        int[] nums1 = {0,7,28,17,18}, nums2 = {1,2,6,26,1,0,27,3,0,30};
        //Output: 98
//        int[] nums1 = {8,13,15,18,0,18,0,0,5,20,12,27,3,14,22,0}, nums2 = {29,1,6,0,10,24,27,17,14,13,2,19,2,11};
        //Output: 179
        System.out.println(minSum(nums1, nums2));
    }
}
