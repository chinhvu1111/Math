package contest;

public class E327_BuildArrayFromPermutation {

    public static int[] buildArray(int[] nums) {
        int n=nums.length;
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            rs[i]=nums[nums[i]];
        }
        return rs;
    }

    public static int[] buildArrayRefer(int[] nums) {
        int n=nums.length;

        for(int i=0;i<n;i++){
            nums[i]+=1000*(nums[nums[i]]%1000);
        }
        for(int i=0;i<n;i++){
            nums[i]=nums[i]/1000;
        }
        return nums;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a zero-based permutation nums) (0-indexed),
        //build (an array ans of the ("same") length)
        // where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
        //- (A zero-based permutation nums) is (an array of distinct integers) from (0 to nums.length - 1) (inclusive).
        //
        //Example 1:
        //
        //Input: nums = [0,2,1,5,3,4]
        //Output: [0,1,2,4,5,3]
        //Explanation: The array ans is built as follows:
        //ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
        //    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
        //    = [0,1,2,4,5,3]
        //
        //* Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //
        //1.1, Case
        int[] nums = {0,2,1,5,3,4};
//        int[] rs = buildArray(nums);
        int[] rs = buildArrayRefer(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
        //1.2, Optimization
        //- 1 <= nums.length <= 1000
        //- Modify the current array:
        //  ==> Modify nums[2] = 1000*(nums[2])%1000
        //- Only modify 1 time
        //  + nums[2] = 14*1000
        //      ==> Revert nums[2]=nums[2]/1000
        //
        //1.3, Complexity:
        //- Space: O(n) --> O(1)
        //- Time: O(n)
        //
        //# Reference:
        //2343. Query Kth Smallest Trimmed Number
        //573. Squirrel Simulation
        //957. Prison Cells After N Days
        //
    }
}
