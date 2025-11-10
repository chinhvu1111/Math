package E1_daily;

public class E271_NextPermutation_classic {

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start){
        int i=start, j=nums.length-1;

        while(i<j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void nextPermutation(int[] nums) {
        int n= nums.length;
        int i;

        for(i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                break;
            }
        }
//        System.out.println(i);
        if(i>=0){
            int swapIndex=n-1;
            for(int j=i+1;j<n;j++){
                //Ex:
                //1,5,8,(4),7,6,(5),3,1
                //4 ==> Find 5 (Min nhất >4)
                //  + Break khi 4>3 (4>=x)
                if(nums[i]>=nums[j]){
                    break;
                }
                swapIndex=j;
            }
            swap(nums, i, swapIndex);
        }
        reverse(nums, i+1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
        //  + For example, for arr = [1,2,3], the following are all the permutations of arr:
        //      + [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
        //- The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
        // More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
        // then the next permutation of that array is the permutation that follows it in the sorted container.
        // If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
        //
        //  + For example, the next permutation of arr = [1,2,3] is [1,3,2].
        //  + Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
        //  + While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
        //- Given an array of integers nums, find the next permutation of nums.
        //+ The replacement must be in place and use only constant extra memory.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 100
        //0 <= nums[i] <= 100
        //
        //* Brainstorm:
        //- Decrease (i) from (n-1 => 0)
        //  + If nums[i]<nums[i+1]:
        //      + Check j:[i+1 -> n-1]
        //      to find nums[j]>nums[i]
        //      ==> swap(i,j)
        //Ex:
        //1,5,8,(4),7,6,(5),3,1
        //=>
        //1,5,8,(5),7,6,(4),3,1
        //  ==> [7,6,(4),3,1] is asc order
        //  ==> Reverse(suffix_arr) to get (MIN array)
        //
//        int[] nums= {1,2,3};
//        int[] nums= {3,2,1};
        //* Special case:
        //  ==> Only sort: {1,2,3}
        //
        //Ex:
        //1,5,8,(4),7,6,(5),3,1
        //4 ==> Find 5 (Min nhất >4)
        //  + Break khi 4>3 (4>=x)
        //
        //1.1, Case
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        int[] nums= {2,3,1};
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%s, ", nums[i]);
        }
        System.out.println();
    }
}
