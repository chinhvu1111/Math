package E1_daily;

import java.util.Deque;
import java.util.LinkedList;

public class E37_MinimumNumberOfKConsecutiveBitFlips {

    public static int minKBitFlipsArray(int[] nums, int k) {
        int n= nums.length;
        boolean[] flipped=new boolean[n];
        //- When is current element need to flipped:
        //- Point ở đây là dùng slice window:
        //  + Đếm số lần flipped ở 0 trong 1 slice window
        //  ==> Move thì giảm dần nếu điểm left cần flipped
        //  ** View thay đổi khi xét từng element[i]:
        //  + Số lần flip của các element sẽ giảm theo từng (i)
        //      + Tại index = i ==> Sẽ không chịu tác động bởi flip[i-k]
        //      ==> Số lần flip sẽ được giảm đi tuỳ thuộc vào flip[i-k]= true/ false
        //- Điều kiện break:
        //  + Nếu 1 số cần flip với (index+k>n) : (0 -> 1)
        //  Ex:
        //  1,1,(0),1 (k=3), i+k>n
        //  + ==> cần flip 0 đằng sau có 1 thì không flip được do
        //      + (không đủ length)
        //      + Mà vẫn còn 0 cần -> 1 ==> Không chuyển được.
        int numFlippedElement=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(i>=k){
                if(flipped[i-k]){
                    numFlippedElement--;
                }
            }
            if(numFlippedElement%2==nums[i]){
                //0 -> 1
                //1 -> 0
                if(i+k>n){
                    return -1;
                }
                numFlippedElement++;
                flipped[i]=true;
                rs++;
            }
        }
        return rs;
    }

    public static int minKBitFlipsRefer(int[] nums, int k) {
        int n = nums.length; // Length of the input array
        Deque<Integer> flipQueue = new LinkedList<>(); // Queue to keep track of flips
        int flipped = 0; // Current flip state
        int result = 0; // Total number of flips

        for (int i = 0; i < n; i++) {
            // Remove the effect of the oldest flip if it's out of the current window
            if (i >= k) {
                flipped ^= flipQueue.poll();
            }

            // If the current bit is 0 (i.e., it needs to be flipped)
            if (flipped == nums[i]) {
                // If we cannot flip a subarray starting at index i
                if (i + k > n) {
                    return -1;
                }
                // Add a flip at this position
                flipQueue.offer(1);
                flipped ^= 1; // Toggle the flipped state
                result += 1; // Increment the flip count
            } else {
                flipQueue.offer(0);
            }
        }

        return result;
    }

    public static int minKBitFlipsWrong(int[] nums, int k) {
        int n= nums.length;
        //[1,(0),1,0,0,1,0], k=3
        //+ i=0: flip (1,2) 1 lần
        //+ i=1: flip (2,3) 1 lần +1 ==> Không flip trừ cái thằng mà mở rộng ra.
        //- Có case mà (i+k-1 == n-1):
        //  + Lúc đấy thì cần reverse: nums[i] = |numFlip - nums[i]|
        //- Có case i --> sau val ==1 khoảng dài thì numFlip sẽ hết giá trị sử dụng
        //Ex:
        //[1,|(0),0|,0,0,1,0], k=3
        //->
        //[1,1,1,(0),0,1,0]
        //- Nếu move slide window thì sao?
        //Ex:
        //[1,|0,1|,0,0,1,0]
        //  => [1,1,|0,0|,0,1,0]
        //[1,0,|1,0|,0,1,0]
        //
        //- Move ntn:
        //|1,2,3|,4,5
        //  + 1,2,3: 1 flip
        //1,|2,3,4|,5
        //  + 2,3,4: 1 flip
        //1,2,|3,4,5|
        //  + 3,4,5: 1 flip
        //
        //+ Flip cho index + pop index ra ngoài
        //+ Với những phần tử được flip nhiều lần thì sao?
        //  +
        //- Dùng queue thì sao?
        //+
        //
        int numFlip=0;
        int i;

        for(i=0;i<n;i++){
            if(nums[i]==0){
                break;
            }
        }
        int rs=0;
        for(;i<n;i++){
            if(nums[i]==0){
                if(i+k-1<n-1){
                    nums[i]=1;
                    if(i+k-1!=i){
                        nums[i+k-1]=1-nums[i+k-1];
                        numFlip=1-numFlip;
                    }
                }else if(i+k-1==n-1){
                    for(;i<n;i++){
                        nums[i]=Math.abs(numFlip-nums[i]);
                    }
                }
                rs++;
            }else{
                nums[i]=Math.abs(numFlip-nums[i]);
            }
        }
        for(i=0;i<n;i++){
            if(nums[i]==0){
                return -1;
            }
//            System.out.println(nums[i]);
        }
        return rs;
    }

    public static int minKBitFlipsSpaceOptimization(int[] nums, int k) {
        int currentFlips = 0; // Tracks the current number of flips
        int totalFlips = 0; // Tracks the total number of flips

        for (int i = 0; i < nums.length; ++i) {
            // If the window slides out of the range and the leftmost element is
            // marked as flipped (2), decrement currentFlips
            if (i >= k && nums[i - k] == 2) {
                currentFlips--;
            }

            // Check if the current bit needs to be flipped
            if ((currentFlips % 2) == nums[i]) {
                // If flipping would exceed array bounds, return -1
                if (i + k > nums.length) {
                    return -1;
                }
                // Mark the current bit as flipped
                nums[i] = 2;
                currentFlips++;
                totalFlips++;
            }
        }

        return totalFlips;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a binary array nums) and (an integer k).
        //- A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
        //* Return the minimum (number of k-bit flips) required so that there is (no 0) in the array.
        //- If it is (not possible), return -1.
        //+ A subarray is a contiguous part of an array.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= k <= nums.length
        //+ Time: O(k*n)
        //
        //- Brainstorm
        //- Biến đổi 1 -> 0 ==> Nếu biến đi biến lại thì có tác dụng k
        //Ex:
        //nums = [0,0,0,1,0], k = 2
        //  + [0,0,(0,1),0]
        //  + [(0,0),1,0,0] : 1
        //  + [1,1,1,0,0] : 2
        //  + [1,1,1,1,1] : 3
        //
        //nums =
        //[1,0,1,0,0,1,0], k=2
        //==>
        //[1,1,1,1,1,1,1]
        //+ 0 -> 1 số lần flip là odd
        //+ 1 -> 1 số lần flip là even
        //
        //[1,0,1,0,0,1,0], k=2
        //=>
        //[1,(0),1,0,0,1,0]
        //=> [1,1,0,0,0,1,0]
        //[1,1,(0),0,0,1,0]
        //=> [1,1,1,1,0,1,0]
        //[1,1,1,1,(0),1,0]
        //=> [1,1,1,1,1,0,0]
        //[1,1,1,1,1,0,0]
        //=> [1,1,1,1,1,1,1]
        //
        //- Chứng minh greedy method là đúng:
        //Suppose there was a better solution that didn't flip immediately upon seeing a 0. This would mean:
        //- We skip flipping at position i (where nums[i] = 0).
        //- We flip at some later position j (where j > i).
        //But this can't be better because:
        //- We still need to make the same number of flips (or more).
        //- We might run out of array length, making the problem unsolvable.
        //
        //** Solution:
        //- When is current element need to flipped:
        //- Point ở đây là dùng slice window:
        //  + Đếm số lần flipped ở 0 trong 1 slice window
        //  ==> Move thì giảm dần nếu điểm left cần flipped
        //  ** View thay đổi khi xét từng element[i]:
        //  + Số lần flip của các element sẽ giảm theo từng (i)
        //      + Tại index = i ==> Sẽ không chịu tác động bởi flip[i-k]
        //      ==> Số lần flip sẽ được giảm đi tuỳ thuộc vào flip[i-k]= true/ false
        //- Điều kiện break:
        //  + Nếu 1 số cần flip với (index+k>n) : (0 -> 1)
        //  Ex:
        //  1,1,(0),1 (k=3), i+k>n
        //  + ==> cần flip 0 đằng sau có 1 thì không flip được do
        //      + (không đủ length)
        //      + Mà vẫn còn 0 cần -> 1 ==> Không chuyển được.
        //
        //1.1, Optimization
        //- Dùng flipped array thì space hơi to --> Dequeue
        //
        //- Có thể xuống constant space nữa:
        //  + Dùng chính array nums để mark.
        //
        //1.2, Complexity
        //- Space: O(n) => optimize to O(k)
        //- Time : O(n)
//        int[] nums = {1,0,1,0,0,1,0};
        //int k=2;
        int[] nums = {0,1,0};
        int k=1;
//        System.out.println(minKBitFlipsWrong(nums, k));
        System.out.println(minKBitFlipsArray(nums, k));
        System.out.println(minKBitFlipsRefer(nums, k));
        System.out.println(minKBitFlipsSpaceOptimization(nums, k));
        //#Reference:
        //2167. Minimum Time to Remove All Cars Containing Illegal Goods
        //2450. Number of Distinct Binary Strings After Applying Operations
    }
}
