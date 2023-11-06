package E1_Array;

import java.util.Arrays;

public class E12_WiggleSort {

    public static void wiggleSort(int[] nums) {
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(i%2==1){
                if(nums[i]<nums[i-1]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }else{
                if(i-1>=0&&nums[i]>nums[i-1]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= nums.length <= 5 * 10^4
        //0 <= nums[i] <= 10^4
        //It is guaranteed that there will be an answer for the given input nums.
        //
        //- Brainstorm
        //Ex:
        //nums = [3,5,2,1,6,4]
        //Result : 3,5,1,6,2,4
        //- Tức là những element ở index=1,3,5,7,9
        // ==> Sẽ lơn hơn các phần tử bên left và right của nó
        //
        //- nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4]
        //+ Ta thấy nums[2] <= nums[1], nums[3]
        //- Nếu cần:
        //+ a<b mà gặp a>b ==> swap
        //
        //+ index=2, 3,5,2,1,6,4
        //+ index=3 (arr[3]<arr[2]) (swap)
        // 3,5,1,2,6,4
        //+ 3,5,1,2,6,4 [replace 2,6]
        //3,5,1,6,2,4
        //
        //- i odd : nums[i]<nums[i-1] => swap
        //- i even : nums[i]>nums[i-1]
        //Ex:
        //1,2,3 ==> swap vẫn đúng với 1,3,2 (3>1)
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(1)
        //- Time : O(n)
        //
        //#Reference:
        //324. Wiggle Sort II
        //1968. Array With Elements Not Equal to Average of Neighbors
//        int[] arr={3,5,2,1,6,4};
        int[] arr={6,6,5,6,3,8};
        wiggleSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
