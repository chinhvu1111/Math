package E1_daily;

public class E246_CheckIfArrayIsSortedAndRotated {

    public static boolean check(int[] nums) {
        int n=nums.length;
        int i;

        for(i=0;i<n-1;i++){
            if(nums[i+1]<nums[i]){
                if(nums[i+1]>nums[0]){
                    return false;
                }else{
                    break;
                }
            }
        }
        i++;
//        System.out.println(i);
        for(;i<n-1;i++){
            if(nums[i+1]<nums[i]||nums[i+1]>nums[0]){
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static boolean checkReference(int[] nums) {
        int n = nums.length;
        if (n <= 1) return true;

        int inversionCount = 0;

        // For every pair, count the number of inversions.
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                ++inversionCount;
            }
        }

        // Also check between the last and the first element due to rotation
        if (nums[0] < nums[n - 1]) {
            ++inversionCount;
        }

        return inversionCount <= 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array nums,
        //* return true if the array was originally sorted in (non-decreasing order),
        // then rotated some number of positions (including zero).
        //  + Otherwise, return false.
        //- There (may be duplicates) in the original array.
        //- Note:
        //  + An array A rotated by (x positions) results in an array B of the same length such that A[i] == B[(i+x) % A.length],
        // where % is the modulo operation.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 100
        //1 <= nums[i] <= 100
        //
        //- Brainstorm
        //Ex:
        //nums = [3,4,5,1,2]
        //nums = [1,2,2,2,2]
        //
        //
        //1.1, Special cases
        //- 8,9,10,7,9,10
        //  + 8 -> 9 -> 10 -> 7 (7<8)
        //  => Return false
        //
        //1.2, Optimization
        //- We count the number of inversion pair following this rule:
        //  + if nums[i] < nums[i-1]: [10,5,2]
        //      => inversionCount++
        //  + if nums[0] < nums[n-1]:
        //      => inversionCount++
        //  ==> Return true when inversionCount<=1
        //  + inversionCount==0:
        //      + 1,2,2,3
        //  + inversionCount==1
        //      + 3,10,1,2,4
        //      ==> false (3<4)
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(1)
        //
//        int[] nums = {3,4,5,1,2};
//        int[] nums = {1,2,2,2,2};
//        int[] nums = {1,2,10,7,9,10};
//        int[] nums = {8,9,10,7,9,10};
        int[] nums = {3,1,2,2,4};
        System.out.println(check(nums));
        System.out.println(checkReference(nums));
    }
}
