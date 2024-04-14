package E1_two_pointers;

public class E12_RemoveDuplicatesFromSortedArrayII {

    public static int removeDuplicates(int[] nums) {
        int n=nums.length;
        int p=0,p1=0;

        while (p1<n){
            int curVal = nums[p1++];
            nums[p++]=curVal;
            int index=0;

            while(p1<n&&nums[p1]==curVal){
                if(index==0){
                    nums[p++]=nums[p1];
                }
                index++;
                p1++;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an integer array nums sorted in non-decreasing order,
        // remove some duplicates in-place such that (each unique element appears at most twice).
        // The relative order of the elements should be kept the same.
        //Since it is impossible to change the length of the array in some languages,
        // you must instead have the result be placed in the first part of the array nums.
        // More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
        // It does not matter what you leave beyond the first k elements.
        //* Return k after placing the final result in the first k slots of nums.
        //- Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
        //- Remove các số sao cho:
        //  + Mỗi số chỉ xuất hiện nhiều nhất 2 lần
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 3 * 10^4
        //-10^4 <= nums[i] <= 10^4
        //nums is sorted in non-decreasing order.
        //
        //- Brainstorm
        //Ex
        //[1,1,1,2,2,3]
        //[(1),1,1,(2),2,3]
        //- two pointers:
        //- 1 pointers đếm số lượng == nhau ==> count==2
        //- 1 pointers dùng để gán lại vào k elements đầu tiên.
        int[] nums = {1,1,1,2,2,3};
//        int[] nums = {0,0,1,1,1,1,2,3,3};
//        int[] nums = {0};
        //
        //#Reference:
        //2605. Form Smallest Number From Two Digit Arrays
        //436. Find Right Interval
        //1946. Largest Number After Mutating Substring
        //
        System.out.println(removeDuplicates(nums));
    }
}
