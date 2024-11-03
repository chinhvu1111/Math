package E1_leetcode_medium_dynamic;

import java.util.*;

public class E179_ConstrainedSubsequenceSum_classic {

    public static int constrainedSubsetSum(int[] nums, int k) {
        int n= nums.length;
        //Space: O(n)
        int[] dp=new int[n];
        //Time: O(n*log(n))
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        int max;
        set.add(new int[]{nums[0], 0});
        dp[0]=nums[0];
        max=dp[0];

        //Time: O(n)
        for(int i=1;i<n;i++){
//            if(i-k-1>=0){
            if(set.size()==k+1){
//                System.out.printf("%s %s\n", i, i-k-1);
                int[] removedElement = new int[]{dp[i-k-1], i-k-1};
                set.remove(removedElement);
            }
            int[] maxElement = set.last();
            dp[i]=(Math.max(maxElement[0], 0))+nums[i];
            //Time: O(log(n))
            set.add(new int[]{dp[i], i});
            max=Math.max(max, dp[i]);
        }
        return max;
    }

    public static int constrainedSubsetSumHeap(int[] nums, int k) {
        int n=nums.length;
        PriorityQueue<int[]> maxHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]-o1[0];
            }
        });
        maxHeap.add(new int[]{nums[0], 0});
        int rs=nums[0];

        for(int i=1;i<n;i++){
            while(i-maxHeap.peek()[1]>k){
                maxHeap.poll();
            }
            int curr=Math.max(0, maxHeap.peek()[0])+nums[i];
            rs=Math.max(rs, curr);
            maxHeap.add(new int[]{curr, i});
        }
        return rs;
    }

    public static int constrainedSubsetSumTreeMap(int[] nums, int k) {
        int n=nums.length;
        int rs=Integer.MIN_VALUE;
        TreeMap<Integer, Integer> window=new TreeMap<>();
        window.put(0, 0);
        //Space: O(n)
        int[] dp=new int[n];

        //Time: O(n)
        for(int i=0;i<n;i++){
            dp[i] = nums[i] + window.lastKey();
            //Time: O(log(k))
            window.put(dp[i], window.getOrDefault(dp[i], 0)+1);

            if(i>=k){
                window.put(dp[i-k], window.get(dp[i-k])-1);
                if(window.get(dp[i-k])==0){
                    window.remove(dp[i-k]);
                }
            }
        }
        //Time: O(n)
        for (int e:dp){
            rs=Math.max(e, rs);
        }
        return rs;
    }

    public static int constrainedSubsetSumMonotonicDeque(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && i - queue.peek() > k) {
                queue.poll();
            }
            dp[i] = (!queue.isEmpty() ? dp[queue.peek()] : 0) + nums[i];
            while (!queue.isEmpty() && dp[queue.peekLast()] < dp[i]) {
                queue.pollLast();
            }
            if (dp[i] > 0) {
                queue.offer(i);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int num : dp) {
            ans = Math.max(ans, num);
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums and an integer k,
        //* return the (maximum sum) of (a non-empty subsequence) of
        // that array such that for (every) (two consecutive integers) in the subsequence,
        // nums[i] and nums[j],
        //  + where i < j,
        //  + the condition (j - i <= k) is satisfied.
        //- A subsequence of an array is obtained by (deleting some number of elements) (can be zero) from the array,
        // (leaving the remaining elements) in their (original order).
        //- Chọn 1 subsequence sao cho max sum.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= k <= nums.length <= 10^5
        //-10^4 <= nums[i] <= 10^4
        //
        //- Brainstorm
        //- Vấn đề ở đây là (j-i<=k)
        //- Nếu ta làm (bottom-up + memo) thì complexity:
        //  Time = O(n^2) ==> TLE
        //- Với mỗi element[i] ta có thể chọn:
        //  + element[i-k],element[i-k+1],...,element[i-1]
        //  ==> Ở đây ta chọn max là được
        //- Vấn đề là còn phải lưu kết quả của việc tính đển [i-k-j]
        //- dp[i] = sum max + choose(i) as the last element
        //- If we use max heap:
        //  + dp[i] = max(dp[i-k],dp[i-k+1],...,dp[i-1]) + nums[i]
        //  + We need to remove the (dp[i-k-1])
        //  ==> remove(dp[i-k-1], i-k-1)
        //- TreeSet sort by:
        //  - dp[i]-dp[j] and i-j
        //- We use the max heap:
        //  + Pop the peek if the size of heap is greater than k
        //
        //- Nếu max(dp[i-k-j]) < 0:
        //  + Thì ta không lấy ==> (a<0)?0:a
        //
        //- Do phải chọn:
        //  + Init: max = nums[0]
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        //2. Heap
        //2.0,
        //Could we have the case below?
        //Ex:
        //(3,0)
        //(5,2)
        //(10,3)
        //(2,4) ==> (Bigger index) with (smaller value)
        //- Last value ==> Min value
        //- If the last element has the (i-index) > k: (1)
        //  + pop this value
        //
        //- If the last element has the (i-index) <= k: (2)
        //  + We don't need to pop this value
        //
        //- (1) nó sẽ có case ntn:
        //  + Dùng min heap chưa tốt
        //
        //- Idea ở đây là:
        //  + Ta sẽ chỉ cần remove dp[i-k] khi nó đứng ở max mà thôi (Tức là ở vị trí có ảnh hưởng đến current max)
        //  ==> Dùng max heap
        //  + Vì sẽ có những case:
        //      + j<i-k mà chưa được remove do không ở MAX
        //      Nhưng về sau có thể dp[i-k-l] sẽ có thể lên MAX:
        //      ==> Ta sẽ remove ở peek() những thằng có (i - heap.peek()[1] > k)
        //
        //** Kinh nghiệm:
        //- Làm mấy bài remove để chọn giá trị nhỏ hơn:
        //  + Phải biết được giá trị đó là gì nếu dùng MIN HEAP <=> TreeSet
        //      + Remove thực sự để (size == k)
        //  + Nếu dùng MAX HEAP thì ta có thể lựa chọn remove liên tục nếu giá trị đó là MAX
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        //3. Tree Map
        //3.0,
        //- TreeMap dùng tương tự min heap
        //- Remove phải biết dp[i-k]
        //  + Remove khi size của min heap
        //
        //3.1, Optimization
        //3.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(k))
        //
        //4.
        //4.0, Monotonic Deque
        //-
        //
//        int[] nums = {10,2,-10,5,20};
//        int k = 2;
//        int[] nums = {10,-2,-10,-5,20};
//        int k = 2;
        //
        int[] nums = {-5266,4019,7336,-3681,-5767};
        int k = 2;
        System.out.println(constrainedSubsetSum(nums, k));
        System.out.println(constrainedSubsetSumHeap(nums, k));
        System.out.println(constrainedSubsetSumTreeMap(nums, k));
        System.out.println(constrainedSubsetSumMonotonicDeque(nums, k));
        //#Reference:
        //2862. Maximum Element-Sum of a Complete Subset of Indices
    }
}
