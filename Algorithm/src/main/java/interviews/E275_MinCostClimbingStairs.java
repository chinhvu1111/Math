package interviews;

public class E275_MinCostClimbingStairs {

    public static int minCostClimbingStairsSlow(int[] cost) {
        int n=cost.length;
        int[]dp=new int[n+1];

        for(int i=2;i<=n;i++){
            dp[i]=Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[n];
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        for(int i=2;i<n;i++){
            cost[i]+=Math.min(cost[i-1], cost[i-2]);
        }
        return Math.min(cost[n-1], cost[n-2]);
    }

    public static void main(String[] args) {
        //** Đề bài
        //- 1 ví trí có thể đến bằng cách bằng trước đó -1 or -2
        //- Tìm min để qua đến vị trí [n] (Tức là ngoài array)
        //** Bài này tư duy như sau:
        //1.
        //1.1, Bài này dùng tư duy quy hoạch động
        //1.2, Tối ưu:
        //- Cách tối ưu duy nhất là dùng chính array đó để tính dynamic
        //+ cost[i] + = min(cost[i-1], cost[i-2])
        int[] cost=new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairs(cost));
        int[] cost1=new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairsSlow(cost1));
    }
}
