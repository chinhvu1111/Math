package E1_bitmanipulation;

public class E10_SumOfAllSubsetXORTotals {

    public static int subsetXORSum(int[] nums) {
        int n=nums.length;
        //111
        //
        int size =(1<<n)-1;
        int rs=0;
//        System.out.println(size);//111 = 1+2+4

        for(int i=size;i>=1;i--){
            int pos=0;
//            int countBit=Integer.bitCount(i);
            int curRs=0;
            int initVal=i;
//            System.out.printf("%s %s\n", i, countBit);

            while (initVal>0){
                //1100001 : lẻ
                //1100000 : chẵn
                if((initVal&1)==1){
//                    System.out.println(pos);
                    curRs^=nums[n-pos-1];
                }
                initVal = initVal>>1;
                pos++;
//                pos++;
            }
            rs+=curRs;
        }
        return rs;
    }

    public static int subsetXORSumEfficient(int[] nums) {
        int result = 0;
        // Capture each bit that is set in any of the elements
        for (int num : nums) {
            result |= num;
        }
        // Multiply by the number of subset XOR totals that will have each bit set
        return result << (nums.length - 1);
    }

    public static void main(String[] args) {
        //* Requirement
        //- (The XOR total of an array) is defined as (the bitwise XOR of all its elements), or 0 if the array is empty.
        //+ For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
        //+ Given an array nums, return (the sum of all XOR totals) for (every subset of nums).
        //- Note: Subsets with the (same elements) should be (counted multiple times).
        //- (An array a) is (a subset) of (an array b) if a can be obtained from b by (deleting) some (possibly zero) (elements of b).
        //Ex:
        //Input: nums = [5,1,6]
        //Output: 28
        //Explanation: The 8 subsets of [5,1,6] are:
        //- The empty subset has an XOR total of 0.
        //- [5] has an XOR total of 5.
        //- [1] has an XOR total of 1.
        //- [6] has an XOR total of 6.
        //- [5,1] has an XOR total of 5 XOR 1 = 4.
        //- [5,6] has an XOR total of 5 XOR 6 = 3.
        //- [1,6] has an XOR total of 1 XOR 6 = 7.
        //- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
        //0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 12
        //1 <= nums[i] <= 20
        //
        //- Brainstorm
        //- loop toàn bộ các cases bằng bit manipulation
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(2^n)
        //
        //2.
        //
        //
        //
        //#Reference:
        //1712. Ways to Split Array Into Three Subarrays
        //832. Flipping an Image
        //1893. Check if All the Integers in a Range Are Covered
        int[] nums = {5,1,6};
        System.out.println(subsetXORSum(nums));
        System.out.println(subsetXORSumEfficient(nums));
    }
}
