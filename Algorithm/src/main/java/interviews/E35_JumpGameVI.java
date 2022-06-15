package interviews;

import java.util.*;

public class E35_JumpGameVI {

    public static int maxResult(int[] nums, int k) {
        int n=nums.length;
        int dp[]=new int[n+1];
//        int rs=0;
        if(dp.length>=1){
            dp[0]=nums[0];
//            rs=dp[0];
        }else{
            return 0;
        }
        for(int i=1;i<n;i++){
            int max=Integer.MIN_VALUE;

            for(int j=1;j<=k&&i-j>=0;j++){
                max=Math.max(max, dp[i-j]+nums[i]);
            }
            dp[i]=max;
//            rs=Math.max(rs, max);
        }
//        for(int i=n-1;i>n-1-k&&i>=0;i--){
//            rs=Math.max(rs, dp[i]);
//        }
        return dp[n-1];
    }

    public static int maxResultWrongIdea(int[] nums, int k) {
        int n=nums.length;
//        int rs=0;
        if(n==0){
            return 0;
        }
        int rs=nums[0];

        for(int i=0;i<n;i++){
            int index=0;
            int negativeNumber=Integer.MIN_VALUE;
            int indexNegative=-1;
            int initRs=rs;

            for(int j=1;j<=k;j++){
                if(i+j==n-1){
                    index=n-1;
                    rs+=nums[n-1];
                    break;
                }
                if(i+j<n&&negativeNumber<nums[i+j]){
                    negativeNumber=nums[i+j];
                    indexNegative=i+j;
                }
                if(i+j<n&&nums[i+j]>0){
                    rs+=nums[i+j];
                    index=i+j;
                }
            }
            if(index==n-1){
                break;
            }

            if(initRs==rs){
                rs+=negativeNumber;
                index=indexNegative;
            }

            i=index-1;
//            rs=Math.max(rs, max);
        }
//        for(int i=n-1;i>n-1-k&&i>=0;i--){
//            rs=Math.max(rs, dp[i]);
//        }
        return rs;
    }

    public static int maxResultDeque(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + dp[q.peekFirst()];
            while(!q.isEmpty() && dp[q.peekLast()] <= dp[i]) q.pollLast();
            q.offerLast(i);
            if(i - q.peekFirst() >= k) q.pollFirst();
        }
        return dp[n - 1];
    }

    public int maxResultPriorityQueue(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)-> (b[0] - a[0]));
        queue.add(new int[]{nums[0], 0});
        int max = nums[0];
        for(int i=1;i<n;i++){
            while(queue.peek()[1]+k<i){
                queue.poll();
            }
            max = nums[i]+queue.peek()[0];
            queue.add(new int[]{max, i});

        }
        return max;
    }

    public static int maxResultPriorityOptimized(int[] nums, int k) {
        int n = nums.length;
        int dp[]=new int[n];
        // Compare method for place element in
// reverse order
        PriorityQueue<Integer> integers=new PriorityQueue<>((a, b) -> {
            if (a < b)
                return 1;
            if (a > b)
                return -1;
            return 0;
        });
        int rs=0;

        if(nums.length>=1){
            dp[0]=nums[0];
        }
        integers.add(dp[0]);

        for(int i=1;i<n;i++){
            while(!integers.isEmpty()&&integers.peek()+k<i){
                integers.poll();
            }
            dp[i]=dp[integers.peek()]+nums[i];
            rs=Math.max(rs, dp[i]);
            integers.add(i);
        }

        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{10,-5,-2,4,0,3};
//        int arr[]=new int[]{10};
//        int arr[]=new int[]{1,-5,-20,4,-1,3,-6,-3};
        //Case 1: Sai do index bị reset về 1 do chưa check (i+j<n)
//        int arr[]=new int[]{100,-1,-100,-1,100};
        //Case 2: Sai do nếu chọn -1 --> Chọn -2/ -3 ==> Sẽ bị sai khi chọn -2
        //--> Kết quả sẽ không tối ưu
        int arr[]=new int[]{0,-1,-2,-3,1};
//        System.out.println(maxResult(arr, 2));
//        System.out.println(maxResult1(arr, 3));
//        System.out.println(maxResultWrongIdea(arr, 2));
        System.out.println(maxResultPriorityOptimized(arr, 2));
    }
}
