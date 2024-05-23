package contest;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;

public class E91_FindTheIntegerAddedToArrayI {

//    public static int addedInteger(int[] nums1, int[] nums2) {
//        int n = nums1.length;
//        int[] count1=new int[1001];
//        int[] count2=new int[1001];
//        Arrays.fill(count1, Integer.MAX_VALUE);
//        Arrays.fill(count2, Integer.MAX_VALUE);
//
//        for(int e:nums1){
//            count1[e]++;
//        }
//        for(int e:nums2){
//            count2[e]++;
//        }
//        for(int i=0;i<1001;i++){
//
//        }
//        return 1;
//    }

    public static int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return nums2[0]-nums1[0];
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two arrays of equal length, nums1 and nums2.
        //- Each element in nums1 has been increased (or decreased in the case of negative) by an integer, represented by the variable x.
        //- As a result, nums1 becomes equal to nums2. Two arrays are considered equal
        // when they contain the same integers with the same frequencies.
        //* Return the integer x.
        //* Biến nums1 -> nums2:
        //  + Khi + số x ==> Số x có thể <0
        //  -> Return x
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums1.length == nums2.length <= 100
        //0 <= nums1[i], nums2[i] <= 1000
        //The test cases are generated in a way that there is an integer x
        // such that nums1 can become equal to nums2 by adding x to each element of nums1.
        //
        //- Brainstorm
        //Ex:
        //Input: nums1 = [2,6,4], nums2 = [9,7,5]
        //Output: 3
        //[2] = 1
        //[4] = 1
        //[6] = 1
        //
        //Ex:
        //Input: nums1 = [2,2,6,4], nums2 = [9,9,7,5]
    }
}
