package E1_daily;

public class E159_ShortestSubarrayToBeRemovedToMakeArraySorted {

    public static int findLengthOfShortestSubarray(int[] arr) {
        int n=arr.length;
        int firstRight=n-1;
        int rs;

        while(firstRight>=1&&arr[firstRight-1]<=arr[firstRight]){
            firstRight--;
        }
        //0,1,2 => (0,1) will be removed
        rs=firstRight;
//        System.out.println(firstRight);
        for(int i=0;i<n-1;i++){
            if(i==0||arr[i]>=arr[i-1]){
                rs=Math.min(n-1-i, rs);
            }else{
                break;
            }
        }
//        System.out.println(rs);
//        System.out.println();
        int left=0;

        while(left<n-1&&firstRight<n&&left<firstRight&&(left==0||arr[left-1]<=arr[left])){
            if(arr[left]<=arr[firstRight]){
                rs=Math.min(rs, firstRight-left-1);
//                System.out.printf("%s %s\n", left, firstRight);
                left++;
            }else{
                firstRight++;
            }
        }
        return rs;
    }

    public static int findLengthOfShortestSubarrayRefactor(int[] arr) {
        int right = arr.length - 1;
        while (right > 0 && arr[right] >= arr[right - 1]) {
            right--;
        }

        int ans = right;
        int left = 0;
        while (left < right && (left == 0 || arr[left - 1] <= arr[left])) {
            // find next valid number after arr[left]
            while (right < arr.length && arr[left] > arr[right]) {
                right++;
            }
            // save length of removed subarray
            ans = Math.min(ans, right - left - 1);
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given (an integer array arr),
        //remove a subarray (can be empty) from arr such that
        //  + (the remaining elements) in arr are (non-decreasing).
        //* Return (the length of the shortest subarray) to remove.
        //- A subarray is a contiguous subsequence of the array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= arr.length <= 10^5
        //0 <= arr[i] <= 10^9
        //  + Length<=10^5 ==> Time: O(n) or O(n*log(n))
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: arr = [1,2,3,10,4,2,3,5]
        //Output: 3
        //Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
        //Another correct solution is to remove the subarray [3,10,4].
        //- The array could be fluctuated
        //  + Increase, decrease, increase, increase, decrease
        //  Ex: 1,3,6,1,2,4,5,1
        //  1->3->6: Increase
        //  6->1: Decrease
        //  1->2->4->5: Increase
        //  5->1: Decrease
        //  ==> We can remove the subarray from (increase to decrease)???
        //  + It is not correct because we got:
        //      + 1->3->6
        //      + 6->1
        //      + 1->2->4->5
        //      ==> We need to remove (3->6) to get (1->2->4->5)
        //Ex:
        //1,3,6,1,2,4,5
        //- We can remove in the middle by keeping (-1, x), (y,n-1)
        //- We can use binary search by combine (first and last):
        //- We need to calculate the (suffix non-increasing):
        //  + If (nums[i]<=nums[i+1]
        //      + prefix[i] = prefix[i+1]+1
        //      + set.add([prefix[i],i])
        //  + <> break
        //+ rs = set.size()
        //
        //- We need to calculate the (prefix non-decreasing):
        //- We loop from left to right:
        //  + For each element nums[i] ==> We need to find the (corresponding nums[right])
        //- Hold on:
        //  + We can use two pointers rather than using binary search
        //  + first_right:
        //      + min right index such that nums[right]<nums[right+1]
        //  + If(nums[left]<=nums[first_right])
        //      + left++
        //      + rs= min(first_right-left-1, rs)
        //      Ex: 0,1,...,1,3,4
        //  else:
        //      first_right++
        //
        //1.1, Optimization
        //- We don't need to (loop right) inside the (left loop):
        //  + To find the (suitable right) rather than (combine all of conditions) into the (while condition)
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        int[] arr = {1,2,3,10,4,2,3,5};
//        int[] arr = {1,2,3};
//        int[] arr = {2,2,2,1,1,1};
//        int[] arr = {1,2,3,3,10,1,3,3,5};
        int[] arr = {6,3,10,11,15,20,13,3,18,12};
        System.out.println(findLengthOfShortestSubarray(arr));
        System.out.println(findLengthOfShortestSubarrayRefactor(arr));
        //#Reference
        //2972. Count the Number of Incremovable Subarrays II
        //2970. Count the Number of Incremovable Subarrays I
    }
}
