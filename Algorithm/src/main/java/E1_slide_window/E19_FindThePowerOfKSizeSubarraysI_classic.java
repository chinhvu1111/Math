package E1_slide_window;

import java.util.*;

public class E19_FindThePowerOfKSizeSubarraysI_classic {

    public static int[] resultsArray(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return new int[]{};
        }
        //Space: O(n)
        //Time: O(n)
        int[] rs=new int[n-k+1];
        Arrays.fill(rs, -1);
        int i;
        int index=0;
        Queue<Integer> listIndexes=new LinkedList<>();
        //Index, value
        TreeSet<int[]> sortNodes=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o2[1]-o1[1];
                }
                return o2[0]-o1[0];
            }
        });
        int lastIndexDecrease=-1;
        listIndexes.add(0);
        sortNodes.add(new int[]{0, nums[0]});

        //Time: O(k)
        for(i=1;i<k;i++){
            //1,2,3,4,3,5
            if(nums[i-1]!=nums[i]-1){
                lastIndexDecrease=i-1;
            }
            int[] curNode={i, nums[i]};
            sortNodes.add(curNode);
            listIndexes.add(i);
        }
        if(listIndexes.peek()>lastIndexDecrease){
            rs[index]=sortNodes.first()[1];
        }
        index++;
        i=k;
        //Time: O(n)
        for(;i<n;i++){
            if(nums[i-1]!=nums[i]-1){
                lastIndexDecrease=i-1;
            }
            int prevIndex=i-k;
            int[] removedNode={prevIndex, nums[prevIndex]};
            //Time: O(log(n))
            sortNodes.remove(removedNode);
            int[] curNode={i, nums[i]};
            sortNodes.add(curNode);
            if(prevIndex>=lastIndexDecrease){
                rs[index]=sortNodes.first()[1];
            }
            index++;
        }
        return rs;
    }

    public static int[] resultsArrayDequeue(int[] nums, int k) {
        int n=nums.length;
        Deque<int[]> dequeue=new ArrayDeque<>();
        int[] rs=new int[n-k+1];

        if(k==1){
            System.arraycopy(nums, 0, rs, 0, n);
            return rs;
        }
        //- nums= [0,1]
        //+ k=2
        // => (0 -> 0+2-1)
        //
        dequeue.add(new int[]{nums[0], 0});
        boolean isFirstValid=true;
        for(int i=1;i<k;i++){
            if (!dequeue.isEmpty()&&dequeue.peekLast()[0]==nums[i]-1){
                dequeue.pollLast();
            }
            if((nums[i]!=nums[i-1]+1)){
                isFirstValid=false;
            }
            dequeue.add(new int[]{nums[i], i});
        }
        rs[0]=isFirstValid?nums[k-1]:-1;
        for(int i=1;i+k-1<n;i++){
//            boolean isConsecutive = true;
//            if(i+k-2>=0&&(nums[i+k-1]!=nums[i+k-2]+1)){
//            }
            while(!dequeue.isEmpty()&&(dequeue.getFirst()[1]<i)){
                dequeue.pollFirst();
            }
            int curElement = nums[i+k-1];
            if (!dequeue.isEmpty()&&dequeue.peekLast()[0]==curElement-1){
                dequeue.pollLast();
            }
            if(dequeue.isEmpty()){
               rs[i]=nums[i+k-1];
            }else{
                rs[i]=-1;
            }
            dequeue.add(new int[]{nums[i+k-1], i+k-1});
        }
        return rs;
    }

    public static int[] resultsArrayRefer(int[] nums, int k) {
        int length = nums.length;
        int[] result = new int[length - k + 1];
        Deque<Integer> indexDeque = new ArrayDeque<>();

        for (int currentIndex = 0; currentIndex < length; currentIndex++) {
            // Remove elements that are out of the window
            if (
                    !indexDeque.isEmpty() &&
                            indexDeque.peekFirst() < currentIndex - k + 1
            ) {
                indexDeque.pollFirst();
            }

            // Check if current element breaks the consecutive and sorted condition
            if (
                    !indexDeque.isEmpty() &&
                            nums[currentIndex] != nums[currentIndex - 1] + 1
            ) {
                indexDeque.clear(); // Invalidate the entire deque if condition breaks
            }

            // Add current element index to the deque
            indexDeque.offerLast(currentIndex);

            // Check if the window is of size k and update result
            if (currentIndex >= k - 1) {
                if (indexDeque.size() == k) { // Valid window of size k
                    result[currentIndex - k + 1] = nums[indexDeque.peekLast()];
                } else {
                    result[currentIndex - k + 1] = -1; // Not valid, return -1
                }
            }
        }

        return result;
    }

    public static int[] resultsArrayCounter(int[] nums, int k) {
        if (k == 1) {
            return nums; // If k is 1, every single element is a valid subarray
        }
        int n=nums.length;
        int[] rs=new int[n-k+1];

        Arrays.fill(rs, -1);
        int consecutiveElements=1;

        for(int i=0;i<n-1;i++){
            if(nums[i]+1==nums[i+1]){
                consecutiveElements++;
            }else{
                consecutiveElements=1;
            }
            if(consecutiveElements>=k){
                rs[i-k+2]=nums[i+1];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of (integers nums of length n) and (a positive integer k).
        //- The power of an array is defined as:
        //  + (Its maximum element) if all of its elements are consecutive and sorted in ascending order.
        //  + -1 otherwise.
        //You need to find (the power of all subarrays of nums) of (size k).
        //* Return an integer array results of (size n - k + 1), where results[i] is the power of nums[i..(i + k - 1)].
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == nums.length <= 500
        //1 <= nums[i] <= 10^5
        //1 <= k <= n
        //
        //** Brainstorm
        //Example 1:
        //
        //Input: nums = [1,2,3,4,3,2,5], k = 3
        //Output: [3,4,-1,-1,-1]
        //Explanation:
        //There are 5 subarrays of nums of size 3:
        //[1, 2, 3] with the maximum element 3.
        //[2, 3, 4] with the maximum element 4.
        //[3, 4, 3] whose elements are not consecutive.
        //[4, 3, 2] whose elements are not sorted.
        //[3, 2, 5] whose elements are not consecutive.
        //
        //- Slide window
//        int[] nums = {1,2,3,4,3,2,5};
//        int k = 3;
//        int[] nums = {3,2,3,2,3,2};
//        int k = 2;
//        int[] nums = {3,2};
//        int k = 2;
//        int[] nums = {2, 3};
//        int k = 2;
//        int[] nums = {3};
//        int k = 1;
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n)+k)
        //
        //2., Dequeue
        //2.0, Brainstorm
        //- (a,b,c) is consecutive
        //  + (a,b,c)+d is consecutive so we only need to check the relation between (c,d):
        //- (a,b,c) is not consecutive:
        //  + (a,b) relation is not consecutive or (b,c) relation is not consecutive
        //  + We just pop all elements out of the dequeue following the ruke:
        //      + The relation is consecutive
        //      + Index of range
        //- How to remove the max element out of the dequeue?
        //  + We assume the dequeue is always ascending
        //  + If rs[i] has the value
        //  ==> We only care about the nums[i] value
        //- How to check the relation between nums[i] and the previous elements?
        //  + We just need to check the relation between nums[i] and last element in the dequeue (PeekLast)
        //- We can remove all of element out of the dequeue:
        //  + If we want to check the relation ==> We only need to check nums[i] and nums[i-1]
        //  + Add(nums[i]) to the queue that is enough
        //
        //2.1, Optimization
        //2.2, Complexity
        //
        //
//        int[] nums = {1,3,4};
//        int k = 2;
//        int[] nums = {1,2,3,4,3,2,5};
//        int k = 3;
//        int[] nums = {3,2,3,2,3,2};
//        int k = 2;
        //Expected = [-1,3,-1,3,-1]
//        int[] nums = {1,4};
//        int k = 1;
//        int[] nums = {4,30,31,32,33,34,5,6,4,4};
//        int k = 5;
        //  + the array is ascending (Just partially increasing), It returns false at index=3
        //- We can not check
        //  + the size(queue)<=1 ==> Update rs[i]
        //- We need to check whether queue== empty or not:
        //- 4,30,31,32,33
        //=> 4,33
        //- We can use last element to check the relationship between nums[i+k-1] and the last of (the previous consecutive elements)
        //  + 32,33,34,(5,6)
        //
        //==> WRONG
        //* Solution:
        //- Idea is remove the first element by index
        //  + If index<i remove
        //- Always add the last element
        //** EXP:
        //- Always cache the last element ==> Check the relation between the (new element) and (the collection of the last previous elements)
        //- We just remove the last element if:
        //  + new element = last element +1
        //  ==> Just (remove only one time) because we are removing for each element in array
        //- We update the result only:
        //  + queue.isEmpty() ==> All the element are ascending
        //* NOTE:
        //- if dequeue == Empty:
        //  + It means the nums[i] is the elemen with max value.
        //
        //- We need to add the list of elements for index==0 firstly.
        //
//        int[] nums = {12,320,459,403,4,5,6,7,8,9,10,200,201,202,203,204,205,206,205,206,207,208,209,210,198};
//        int k = 7;
        int[] nums = {809,378,31,384,385,386,387,388,389,390,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,395,396,397,398,399,400,401,402,403,587,170,516,637};
        int k =22;
        //- Đoạn này lỗi do while với (i==0):
        //  + Nểu remove all last elements lần lượt:
        //      + If(lastElement = nums[i+k-1]-1) ==> Poll
        //      + lastElement = polled_element ==> Không cần thiết
        //          ==> Lúc nào ta cũng remove for each looping turn rồi
        //  + 290,290,291 ==> Cho dù nếu while (lỗi k update lastElement):
        //      + Delete mất 2 element cạnh nhau ==> Wrong
        //
        //384,385,386,387,388,389,(390,390),391,392,393,394,395,396,397,398,399,400,401,402,403,404
        //
        //rs =          [-1,34,-1, 6,-1,-1]
        //Expected =    [-1,34,-1,-1,-1,-1]
//        int[] rs= resultsArray(nums, k);
//        int[] rs= resultsArrayDequeue(nums, k);
//        int[] rs= resultsArrayRefer(nums, k);
        int[] rs= resultsArrayCounter(nums, k);
        //[3,4]
        //-1,4
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
        //- Special cases:
        //nums= [3,2,3,2,3,2]
        //rs = [-1,3,-1,3,-1]
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //3. Optimized Via Counter
        //3.0, Brainstorm
        //- Idea is the just start from index=0
        //- Set consecutiveCount=1
        //- Check every (nums[i]+1==nums[i+1]):
        //  + True: consecutiveCount++;
        //  + Reset consecutiveCount == 1
        //- If consecutiveCount>=k:
        //  + Update rs[i-k+2] = nums[index+1]
        //      + index = i-k+2 because we start from (i==0)
        //
        //3.1, Optimization
        //3.2, Complexity
        //- Space: O(1) (Extra space)
        //- Time: O(n)
        //
        //#Reference:
        //2149. Rearrange Array Elements by Sign
        //2908. Minimum Sum of Mountain Triplets I
        //689. Maximum Sum of 3 Non-Overlapping Subarrays
        //
        //2779. Maximum Beauty of an Array After Applying Operation
        //2039. The Time When the Network Becomes Idle
        //1095. Find in Mountain Array
        //
    }
}
