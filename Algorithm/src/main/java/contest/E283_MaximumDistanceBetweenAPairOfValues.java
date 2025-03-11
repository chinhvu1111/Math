package contest;

public class E283_MaximumDistanceBetweenAPairOfValues {

    public static int searchLeftMostSmaller(int[] nums, int key){
        int low=0, high=nums.length-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]<=key){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int maxDistance(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int rs=0;

        for(int i=m-1;i>=0;i--){
            int index=searchLeftMostSmaller(nums1, nums2[i]);
            if(index!=-1){
                rs=Math.max(rs, i-index);
            }
        }
        return rs;
    }

    public static int maxDistanceOptimization(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int rs=0;
        int i=n-1,j=m-1;

        while(j>=0&&i>=0){
            int num2=nums2[j];
            while(i>=1&&num2>=nums1[i-1]){
                i--;
            }
            if(i>=0&&num2>=nums1[i]){
                rs=Math.max(rs, j-i);
                i--;
            }
            j--;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (non-increasing) (0-indexed integer arrays nums1 and nums2).
        //- A pair of indices (i, j),
        //  + where 0 <= i < nums1.length and 0 <= j < nums2.length,
        //  + is valid if both i <= j and nums1[i] <= nums2[j].
        //- The distance of the pair is (j - i).
        //* Return (the maximum distance) of any valid pair (i, j).
        //  + If there are no valid pairs, return 0.
        //- An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
        //
        //Example 1:
        //
        //Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
        //Output: 2
        //Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
        //The maximum distance is 2 with pair (2,4).
        //
        // Idea
        //1. Binary search
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums1.length, nums2.length <= 10^5
        //1 <= nums1[i], nums2[j] <= 10^5
        //Both nums1 and nums2 are non-increasing.
        //  + length<=10^5 ==> Time: O(n)/ O(n*k)
        //
        //- Brainstorm
        //- Loop nums2 from right to left:
        //  + For each element find the (lower bound element) with (smaller value)
        //
        //Example 1:
        //
        //Input: nums1 = [55,30,6,4,2], nums2 = [100,20,10,10,5]
        //For elements[i] = 5 ==> left_index = 3
        //  + current rs = 5 - 3
        //1.1, Cases
        int[] nums1 = {55,30,5,4,2}, nums2 = {100,20,10,10,5};
        System.out.println(maxDistance(nums1, nums2));
        System.out.println(maxDistanceOptimization(nums1, nums2));
        //1.2, Optimization
        //- Use 2 pointers method:
        //  + We don't need to traverse the multiple time some indices
        //
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(m*log(n)) ==> O(n+m)
        //
    }
}
