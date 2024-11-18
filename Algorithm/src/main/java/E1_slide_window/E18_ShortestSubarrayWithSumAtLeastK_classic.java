package E1_slide_window;

import javafx.util.Pair;

import java.util.*;

public class E18_ShortestSubarrayWithSumAtLeastK_classic {

    public static int shortestSubarray(int[] nums, int k) {
        int n=nums.length;
        //Space: O(n)
        //Time: O(n)
        long[] prefix=new long[n+1];
        //0,1,2,3
        //0,nums[0]

        //Time: O(n)
        for(int i=1;i<=n;i++){
            prefix[i]=prefix[i-1]+nums[i-1];
        }
        //Space: O(n)
        //Time: O(n*log(n))
        PriorityQueue<Pair<Long, Integer>> minHeap=new PriorityQueue<>(new Comparator<Pair<Long, Integer>>() {
            @Override
            public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
                return (int) (o1.getKey() - o2.getKey());
            }
        });
        minHeap.add(new Pair<>(0L,-1));
        int rs=Integer.MAX_VALUE;
        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(log(n))
            while(!minHeap.isEmpty()&&prefix[i+1]-minHeap.peek().getKey()>=k){
                Pair<Long, Integer> curValidElement = minHeap.poll();
                rs= (int) Math.min(rs, i - curValidElement.getValue());
            }
            minHeap.add(new Pair<>(prefix[i+1], i));
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    // Binary search to find (the largest index) where (cumulative sum is <= target)

    public static int findValidIndex(List<Pair<Long, Integer>> nums, long target){
        int left=0, right=nums.size()-1;
        int rs=-1;

        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums.get(mid).getKey()<=target){
                rs=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return rs;
    }

    public static int shortestSubarrayBinarySearchMonotoicStack(int[] nums, int k) {
        int n=nums.length;
        int rs=Integer.MAX_VALUE;
        long sum=0;

        List<Pair<Long, Integer>> cumulativeSumStack=new ArrayList<>();
        cumulativeSumStack.add(new Pair<>(0L, -1));

        for(int i=0;i<n;i++){
            sum+=nums[i];
            while(!cumulativeSumStack.isEmpty()&&sum<=cumulativeSumStack.get(cumulativeSumStack.size()-1).getKey()){
                cumulativeSumStack.remove(cumulativeSumStack.size()-1);
            }
            cumulativeSumStack.add(new Pair<>(sum, i));
            int validIndex = findValidIndex(cumulativeSumStack, sum-k);
            if(validIndex!=-1){
                rs=Math.min(rs, i-cumulativeSumStack.get(validIndex).getValue());
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int shortestSubarrayDeque(int[] nums, int k) {
        int n=nums.length;
        int rs=Integer.MAX_VALUE;
        long[] prefixSums = new long[n + 1];

        for(int i=1;i<=n;i++){
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
        }
        Deque<Integer> candidateIndices=new ArrayDeque<>();

        for(int i=0;i<=n;i++){
            // Remove candidates from front of deque where subarray sum meets target
            while(!candidateIndices.isEmpty()&&prefixSums[i]-prefixSums[candidateIndices.peekFirst()]>=k){
                rs=Math.min(rs, i-candidateIndices.pollFirst());
            }
            // Maintain monotonicity by removing indices with larger prefix sums
            while(!candidateIndices.isEmpty()&&prefixSums[i]<=prefixSums[candidateIndices.peekLast()]){
                candidateIndices.pollLast();
            }
            candidateIndices.addLast(i);
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given (an integer array nums) and (an integer k),
        //* return (the length of the shortest non-empty subarray) of nums with (a sum of at least k).
        //  If there is no such subarray, return -1.
        //- A subarray is a contiguous part of an array.
        //- (Sum of the subarray) >= k
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //-10^5 <= nums[i] <= 10^5
        //1 <= k <= 10^9
        //  + Length <= 10^5 ==> Time: O(n)
        //  + k is quite big
        //
        //- Brainstorm
        //- element may be less than 0
        //  + (Add element) can (increase or decrease) the sum
        //  <> Remove the leftmost element has the same idea
        //=> We cannot solve this with (slice window) approach.
        //
        //Ex:
        //nums = [2,-1,2], k = 3
        //prefixSum = [2,1,3]
        //- How to find th (shortest length) of the subarray?
        //- Priority Queue:
        //  - We find the smallest value in the queue
        //- (Min sum value) is not mean that the prefix sum has:
        //  + Min size
        //  + Max size
        //Ex:
        //-1,-2,-4,-5,10,20,40
        //sum = -12, size = 4
        //-1,-2,-4,-5,10,-13,20,40
        //sum = -15, size = 6
        //- Pop until we get the prefixSum[i] - peek_prefix_sum <k:
        //  + update (all of case with corresponding index) of the elements that we have popped
        //- For j>i:
        //  + With smaller value ==> we need to combine with (the smaller prefix sum) + (window_range need to be smaller)
        //  ==> Find the index nearer (Tìm index gần nó hơn ==> Pop được vì nếu lấy (older index)) ==> Window_range is bigger (Không tốt)
        //  ==> pop ok
        //  + With bigger value ==> we need to combine with (the bigger prefix sum) ==> pop ok (Pop cái nhỏ hơn rồi nên chỉ kết hợp với cái lớn hơn)
        //* CLASSIC IDEA:
        //- target=-1
        //-1,-3,5,6,-1,10,(-1),2,3,4,5
        //
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //2. Binary search + Monotonic stack
        //2.1,
        //- We use the binary search to find the valid index with (largest value + valid sum)
        //** Stack <=> Max index
        //  ==> Good for the shortest length
        //- At the ith position with prefixSum[i]:
        //  + We only care about:
        //      + the prefixSum[j] < prefixSum[j]
        //      + j<i
        //* ==> We can pop (all of elements) at (the top of the stack) with (bigger value than prefixSum[i])
        //- Stack --> rear -> top: incremental
        //  ==> We can search normally
        //- We create the max stack by:
        //  + Delete all of (the prefix sum >= sum)
        //  + Binary search to find (the largest index) where (cumulative sum is <= target)
        //
        //* KINH NGHIỆM:
        //- Nếu đứng ở ith pos ta quan tâm những giá trị ntn + (furthest or nearest):
        //  + Ta cần phải quyết định pop hết (top, rear) cho hợp lý
        //- Shortest + previous smaller value:
        //  + Pop top
        //
        //3. Deque
        //- Remove candidates from front of deque where subarray sum meets target
        //  + Update shortest subarray length
        //- Maintain monotonicity by removing indices with larger prefix sums
        //** KINH NGHIỆM:
        //- Thường nếu làm được bằng dequeue thì sẽ:
        //  + Poll first
        //  + Poll last được
        //  ==> Để không cần search <=> thay vào đó lúc poll sẽ tính luôn
        //Ex:
        //- Poll first ==> Tính luôn vì targetSum thoả mãn
        //  + min ở first
        //- Poll last ==> Loại bỏ phần tử không thoả mãn
        //  ==> Làm cho dequeue luôn tăng dần
        //  => min -> max
        //
        //3.1, Optimization
        //3.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //891. Sum of Subsequence Widths
        //3316. Find Maximum Removals From Source String
        //2281. Sum of Total Strength of Wizards
        //
        int[] nums = {2,-1,2};
        int k = 3;
        System.out.println(shortestSubarray(nums, k));
        System.out.println(shortestSubarrayBinarySearchMonotoicStack(nums, k));
        System.out.println(shortestSubarrayDeque(nums, k));
    }
}
