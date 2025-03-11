package E1_daily;

public class E236_BitwiseXOROfAllPairings {

    public static int xorAllNums(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;

        int xor1 = 0;
        int xor2 = 0;
        if(m%2!=0){
            for (int i = 0; i < n; i++) {
                xor1^=nums1[i];
            }
        }
        if(n%2!=0){
            for (int i = 0; i < m; i++) {
                xor2^=nums2[i];
            }
        }
        return xor1^xor2;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (0-indexed arrays), nums1 and nums2, consisting of (non-negative integers).
        //- There exists another array (nums3), which contains (the bitwise XOR) of (all pairings of integers) between nums1 and nums2
        // (every integer in nums1 is paired with (every integer) in nums2 exactly once).
        //* Return (the bitwise XOR) of (all integers) in nums3.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums1.length, nums2.length <= 10^5
        //0 <= nums1[i], nums2[j] <= 10^9
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
        //Output: 13
        //Explanation:
        //A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
        //The bitwise XOR of all these numbers is 13, so we return 13.
        //nums1 = [a,b], nums2= [c,d]
        //(a xor c) xor (a xor d) xor (b xor c) xor (b xor d)
        //(c xor d) xor (c xor d)
        //
        //nums1 = [a,b], nums2= [c,d,e]
        //(a xor c) xor (a xor d) xor (a xor e) xor (b xor c) xor (b xor d) xor (b xor e)
        //= (c xor d) xor (a xor e) xor (c xor d) xor (b xor e)
        //= (a xor b)
        //
        //nums1 = [a,b,c], nums2= [d,e]
        //(a xor d) xor (a xor e) xor (b xor d) xor (b xor e) xor (c xor d) xor (c xor e)
        //= (d xor e) xor (d xor e) xor (d xor e)
        //= (d xor e)
        //- The rule we can define as the pattern above.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] nums1 = {2,1,3}, nums2 = {10,2,5,0};
        System.out.println(xorAllNums(nums1, nums2));
    }
}
