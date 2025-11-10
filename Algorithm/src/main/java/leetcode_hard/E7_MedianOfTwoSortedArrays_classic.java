package leetcode_hard;

public class E7_MedianOfTwoSortedArrays_classic {

    public static int solution(
            int[] nums1, int[] nums2, int k,
            int start1, int end1, int start2, int end2){
        // If the segment of on array is empty, it means we have passed all
        // its element, just return the corresponding element in the other array.
        if(end1<start1){
            return nums2[k-start1];
        }
        if(end2<start2){
            return nums1[k-start2];
        }
        int mid1 = (start1+end1)/2;
        int mid2= (start2+end2)/2;

        // If k is in the right half of A + B, remove the smaller left half.
        if(mid1+mid2<k){
            if(nums1[mid1]>nums2[mid2]){
                return solution(nums1, nums2, k, start1, end1, mid2+1, end2);
            }else{
                return solution(nums1, nums2, k, mid1+1, end1, start2, end2);
            }
        }else{
            // Otherwise, remove the larger right half.
            if(nums1[mid1]>nums2[mid2]){
                return solution(nums1, nums2, k, start1, mid1-1, start2, end2);
            }else{
                return solution(nums1, nums2, k, start1, end1, start2, mid2-1);
            }
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int len=n+m;
        if(len%2==1){
            return solution(nums1, nums2, len/2, 0, n-1, 0, m-1);
        }else{
            return ((double) solution(nums1, nums2, len/2, 0, n-1, 0, m-1)
                    + solution(nums1, nums2, len/2-1, 0, n-1, 0, m-1))/2;
        }
    }

    public static double findMedianSortedArraysRefer(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        if(m>n){
            return findMedianSortedArrays(nums2, nums1);
        }
        int left=0, right=m;
        //Length(nums1) <= Length(nums2)

        while(left<=right){
            //A: A-left   |   A-right
            //B: B-left   |   B-right
            //partitionA+partitionB = (m+n+1)/2
            int partitionA = (left+right)/2;
            int partitionB = (m+n+1)/2-partitionA;
            int maxLeftA = (partitionA==0)?Integer.MIN_VALUE: nums1[partitionA-1];
            //Non element in partition A
            int minRightA = (partitionA==m)?Integer.MAX_VALUE: nums1[partitionA];
            int maxLeftB = (partitionB==0)?Integer.MIN_VALUE:nums2[partitionB-1];
            //Non element in partition B
            int minRightB = (partitionB==n)?Integer.MAX_VALUE:nums2[partitionB];
            //A: maxLeftA, minRightA
            //B: maxLeftB, minRightB
            //
            //The median condition:
            //  + Size = (m+n+1)/2
            //  + Equality
            if(maxLeftA<=minRightB&&maxLeftB<=minRightA){
                //==>
                // maxLeftA <= minRightB
                // maxLeftB <= minRightA
                //
                if((m+n)%2==0){
                    return (double) (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB))/2;
                }else{
                    return Math.max(maxLeftA, maxLeftB);
                }
            }else if(maxLeftA > minRightB){
                //We need to extend B
                //==> Parititon A should be decreased
                right=partitionA-1;
            }else{
                left=partitionA+1;
            }
        }
        return 0D;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (two sorted arrays nums1 and nums2) of size m and n respectively,
        //* return (the median of the two sorted arrays).
        //- The overall run time complexity should be O(log (m+n)).
        //
        //Example 1:
        //
        //Input: nums1 = [1,3], nums2 = [2]
        //Output: 2.00000
        //Explanation: merged array = [1,2,3] and median is 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //nums1.length == m
        //nums2.length == n
        //0 <= m <= 1000
        //0 <= n <= 1000
        //1 <= m + n <= 2000
        //-106 <= nums1[i], nums2[i] <= 106
        //
        //* Brainstorm:
        //- We should not merge this array
        //- Let make an assumtion:
        //
        //Example 2:
        //
        //Input: nums1 = [1,2], nums2 = [3,4]
        //Output: 2.50000
        //Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
        //
        //- (Low, high) is in the (first/ second) array
        //
        //Example 2:
        //
        //Input: nums1 = [1,2,5,6], nums2 = [3,4,7,8,10,16]
        //x = (nums[4]+nums[5])/2
        //x = (nums[(10-1)/2]+ nums[n/2])
        // length = 3 ==> (nums[1]+nums[1])/2
        //
        //- If we combine two of array:
        //  + x=nums[n+m]
        //
        //Input: nums1 = [1,2,5,6], nums2 = [3,4,7,8,10,16]
        //+ Length = 4+6 = 10
        //x = (nums[4]+nums[5])/2
        //- Assume x = 16
        //  + index = 3+6 = 9
        //
        //* Main point:
        //- We don't care about merging the array because:
        //  + If we find (mid1, mid2 indices) from arr1, arr2
        //  ==> all elements from the left of (mid1, mid2) are less than (nums[mid1], nums[mid2])
        //Ex:
        //A: ..(A-left).. mid1 ..(A-right)..
        //B: ..(B-left).. mid2 ..(B-right)..
        //
        //(A-left)(A-right, B-right)
        //+ They are not overlap
        //
        //(A-left)(B-left, B-right)
        //==> Not overlap
        //
        //- What matters is that we set (an index k) at the beginning and
        // we want to find (the kth smallest element) using (the Binary Search-like algorithm) discussed previously
        // (for convenience, we will discuss only (the kth element) for now).
        //
        //- if(mid1+mid2<k): ==> We can extend more
        //  + nums1[mid1]<nums2[mid2]
        //  ==> We can (increase (end1) to mid+1) because this value will be less than nums2[mid2]
        //  <=> Remove (the half of num1) because we don't need them in (the next process)
        //==> Vice verse for if(mid1+mid2>=k)
        //
        // If k is in the right half of A + B, remove the smaller left half.
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Suppose (the partition index) is (partitionA), we specify that (the smaller half) contains (m + n + 1) / 2 elements,
        // and
        // we can use this feature to our advantage by directly making (partitionB) equal to (m + n + 1) / 2 - partitionA,
        // thus (the smaller halves of both arrays) always contain a total of (m + n + 1) / 2 elements, as shown in the picture below.
        //
        //A: A-left   |   A-right
        //B: B-left   |   B-right
        //========================
        //partitionA+partitionB = (m+n+1)/2
        //int partitionA = (left+right)/2;
        //int partitionB = (m+n+1)/2-partitionA;
        //========================
        //int maxLeftA = (partitionA==0)?Integer.MIN_VALUE: nums1[partitionA-1];
        //Non element in partition A
        //int minRightA = (partitionA==m)?Integer.MAX_VALUE: nums1[partitionA];
        //int maxLeftB = (partitionB==0)?Integer.MIN_VALUE:nums2[partitionB-1];
        //Non element in partition B
        //int minRightB = (partitionB==n)?Integer.MAX_VALUE:nums2[partitionB];
        //========================
        //A: maxLeftA, minRightA
        //B: maxLeftB, minRightB
        //========================
        //
        //The median condition:
        //  + Size = (m+n+1)/2
        //  + Equality
        //
        //1.3, Complexity
        //- Space: O(log(min(n,m))
        //- Time: O(log(min(n,m))
        //
//        int[] nums1 = {1,2}, nums2 = {3,4};
        int[] nums1 = {1,3}, nums2 = {2};
//        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArraysRefer(nums1, nums2));
        //
        //#Reference:
    }
}
