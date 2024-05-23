package E1_daily;

import java.util.*;

public class E15_FindTheMaximumSumOfNodeValues {

    public static long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n=nums.length;
        List<int[]> xorVal =new ArrayList<>();

        for(int i=0;i<n;i++){
            xorVal.add(new int[]{nums[i]^k, nums[i]});
        }
        Collections.sort(xorVal, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]-o2[1]-(o1[0]-o1[1]);
            }
        });
        long rs=0;
        for(int i=0;i<n;i++){
            int[] curXor = xorVal.get(i);
            if(i==n-1){
                rs+=curXor[1];
                break;
            }
            int[] nextCurXor = xorVal.get(i+1);

            if(curXor[0]-curXor[1]+nextCurXor[0]-nextCurXor[1]>0){
                rs+=curXor[0]+nextCurXor[0];
                i++;
            }else{
                rs+=curXor[1];
            }
        }
        return rs;
    }

    public static long solution(int even, int index, int k, long[][] memo, int[] nums){
        if(index==nums.length){
            return (even==1)? 0: Integer.MIN_VALUE;
        }
        if(memo[index][even]!=-1){
            return memo[index][even];
        }
        long xorVal = (nums[index]^k) + solution(even^1, index+1, k, memo, nums);
        long notXorVal = nums[index] + solution(even, index+1, k, memo, nums);
        return memo[index][even]=Math.max(xorVal, notXorVal);
    }

    public static long maximumValueSumTopDown(int[] nums, int k, int[][] edges) {
        int n=nums.length;
        long[][] memo=new long[n][2];

        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], -1);
        }
        return solution(1, 0, k, memo, nums);
    }

    public static long maximumValueSumBottomUp(int[] nums, int k, int[][] edges) {
        int n=nums.length;
        long[][] dp=new long[n+1][2];
        dp[n][1]=0;
        dp[n][0]=Integer.MIN_VALUE;

        for(int i=n-1;i>=0;i--){
            dp[i][1]=Math.max(dp[i+1][1]+nums[i], dp[i+1][0]+(nums[i]^k));
            dp[i][0]=Math.max(dp[i+1][0]+nums[i], dp[i+1][1]+(nums[i]^k));
        }
        return dp[0][1];
    }

    public static long maximumValueSumGreedy(int[] nums, int k, int[][] edges) {
        long sum = 0;
        int count = 0, positiveMinimum = (1 << 30), negativeMaximum = -1 * (1 << 30);

        for (int nodeValue : nums) {
            int operatedNodeValue = nodeValue ^ k;
            sum += nodeValue;
            int netChange = operatedNodeValue - nodeValue;
            if (netChange > 0) {
                positiveMinimum = Math.min(positiveMinimum, netChange);
                sum += netChange;
                count++;
            } else {
                negativeMaximum = Math.max(negativeMaximum, netChange);
            }
        }

        // If the number of positive netChange values is even, return the sum.
        if (count % 2 == 0) {
            return sum;
        }

        // Otherwise return the maximum of both discussed cases.
        return Math.max(sum - positiveMinimum, sum + negativeMaximum);
    }

    public static void main(String[] args) {
        //** Requirement
        //- There exists (an undirected tree) with n nodes numbered (0 to n - 1).
        // You are given a 0-indexed 2D integer array edges of length n - 1, where edges[i] = [ui, vi]
        // indicates that there is an edge between nodes (ui) and (vi) in the tree.
        // You are also given (a positive integer k), and (a 0-indexed array of non-negative integers nums) of length n,
        // where nums[i] represents the value of (the node numbered i).
        //- Alice wants (the sum of values) of (tree nodes) to be (maximum), for which Alice can perform
        // the following operation (any number of times) (including zero) on the tree:
        //- Choose any edge [u, v] connecting the nodes u and v, and update their values as follows:
        //  + nums[u] = nums[u] XOR k
        //  + nums[v] = nums[v] XOR k
        //* Return the (maximum possible sum) of the values Alice can achieve by performing the operation (any number of times).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n == nums.length <= 2 * 10^4
        //1 <= k <= 10^9
        //0 <= nums[i] <= 10^9
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= edges[i][0], edges[i][1] <= n - 1
        //The input is generated such that edges represent a valid tree.
        //
        //- Brainstorm
        //          0
        //        /  \
        //      1     2
        //
        //Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
        //Output: 6
        //Explanation: Alice can achieve the maximum sum of 6 using a single operation:
        //- Choose the edge [0,2].
        //  + nums[u] = nums[u] XOR k => nums[0] = nums[0] xor 3 = 1 xor 3 = 2
        //  + nums[v] = nums[v] XOR k => nums[2] = nums[2] xor 3 = 1 xor 3 = 2
        //  + nums[0] and nums[2] become: 1 XOR 3 = 2,
        //  + The array nums becomes: [1,2,1] -> [2,2,2].
        //The total sum of values is 2 + 2 + 2 = 6.
        //It can be shown that 6 is the maximum achievable sum of values.
        //
        //- Tức là dựa vào edges --> Đổi val của node
        //- Liệu có rule nào không?
        //  + Số lần thực hiện có khiến kết quả thay đổi ?
        //      + Ta thực hiện việc change vertex dựa trên edges ==> Nếu change nhiều lần trên 1 edge
        //      <=> XOR với k nhiều lần ==> Nó sẽ không có tác dụng gì
        //      Ex: 5 xor 3 xor 3 = 5
        //- Every nodes --> khi changed thì chỉ có thể thành 1 điểm tương ứng
        //- Problems:
        //  + Khi thay đổi 1 edge (u,v) ==> (x,y)
        //  + 1 thằng khác có chưa (u/v) ==> (x,y) sẽ bị reversed về (u/v)
        //- Giá trị cuối sẽ phụ thuộc vào việc có execute operation hay không trên từng edge:
        //Ex:
        //Input:
        //+ nums = [7,7,7,7,7,7], k = 3,
        //+ edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
        //Output: 42
        //Explanation: The maximum achievable sum is 42 which can be achieved by Alice performing no operations.
        //
        //- Ta thấy các edges --> Đại diện cho tree
        //  + Nếu các edges chia tree thành các trees độc lập
        //  ==> Tính sum của chúng không phụ thuộc vào nhau.
        //Ex:
        //      1
        //    /  \
        //   2    3 -- 5
        //=> Ta sẽ phải đủ edges= [1-2,1-3,3-5]
        //==> Ta thấy rằng ta có thể luôn luôn xor cover được all nodes (X=X xor K)
        //  + Giữa 2 - 5 không có edges nào:
        //  ==> Không thể thực hiện việc chọn (u=2, v=5) để XOR.
        //** Ta có thể chọn (u=2, v=5) để XOR miễn là:
        //= cách :
        //  + xor (1,2)
        //  + xor (1,3) : Để reverse 1
        //  + xor (3,5) : Để reverse 3
        //==> Ta có thể chọn tuỳ ý cặp (u và v) từ list node để XOR
        //  + Miễn là chúng nằm bên trong cùng 1 tree.
        //  ==> Đề bài: There exists an undirected tree with n nodes
        //  --> Cho 1 directed tree --> All nodes connected.
        //
        //          0
        //        /
        //      2  --- 1
        //        \
        //         4 -- 3
        //- Ta sẽ không quan tâm đến edeges nữa
        //  + Ta chỉ quan tâm đến node thôi
        //==> Lúc đó ta chỉ cần sort 1 lần:
        //  + Sau đó chọn các cặp đôi 1 (Đi theo 1 path nào đó) ==> Cộng dần lên (i+=2)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(n*log(n))
        //- Space : O(n)
        //
        //2. Dynamic programming
        //2.0,
        //- Top down approach
        //- Ta sẽ cần phải modify trên m nodes (m là số chẵn)
        //- Nếu ta Xor 1 node tại (index) thì:
        //  + m trước đó là even: chuỗi tiếp theo sẽ cần modify (m-1) là số lẻ
        //  + m trước đó là odd: chuỗi tiếp theo sẽ cần modify (m-1) là số chẵn
        //==> Cái even trong lời giải múc đích là để đến cuối:
        //  + Khi (index==n-1) : Nếu đến cuối là lẻ ==> return MIN (Tức là kết quả này không thoả mãn)
        //  + Khi (index==n-1) : Nếu đến cuối là chẵn ==> return 0 (Tức là kết quả này thoả mãn)
        //
        //* NOTE:
        //- Chú ý phép XOR:
        //  + (a^b + c) != ((a^b) + c)
        //  ==> Cần đóng () cho chắc
        // (nums[index]^k) + solution(even^1, index+1, k, memo, nums);
        //
        //- Bottom up:
        //- dp[index][1] = max(dp[index-1][1]+nums[index-1], dp[index-1][0]+nums[index-1])
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Time complexity: O(n)
        //  + Chỉ tính cho : dp[n][2] ==> Tính cho O(n*2) ~ O(n)
        //- Space complexity: O(n)
        //
        //3. Greedy (Finding local maxima and minima)
        //- Chưa chữa
        //
        int[] nums = {24,78,1,97,44};
        int[][] edges = {{0,2},{1,2},{4,2},{3,4}};
        int k = 6;
        System.out.println(maximumValueSum(nums, k, edges));
        System.out.println(maximumValueSumTopDown(nums, k, edges));
        nums = new int[]{24,78,1,97,44};
        edges = new int[][]{{0,2},{1,2},{4,2},{3,4}};
        System.out.println(maximumValueSumBottomUp(nums, k, edges));
        System.out.println(maximumValueSumGreedy(nums, k, edges));
        //#Reference
        //2925. Maximum Score After Applying Operations on a Tree
        //2973. Find Number of Coins to Place in Tree Nodes
        System.out.println(5^3^3);
    }
}
