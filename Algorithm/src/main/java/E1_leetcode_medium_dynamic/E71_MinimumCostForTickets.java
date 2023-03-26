package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E71_MinimumCostForTickets {

    public static int mincostTickets(int[] days, int[] costs) {
        int maxDays= Arrays.stream(days).max().getAsInt();
        int dp[]=new int[maxDays+1];
        int daysArr[]=new int[366];

        for(int i=0;i<days.length;i++){
            daysArr[days[i]]=1;
        }
        int costMin1=Integer.min(costs[0], Integer.min(costs[1], costs[2]));
        int costMin2=Integer.min(costs[1], costs[2]);

        for(int i=1;i<=maxDays;i++){
            if(daysArr[i]==0){
                dp[i]=dp[i-1];
                continue;
            }
            int currentMin=Integer.MAX_VALUE;

            //Case 1
            int value=0;
            if(i>=1){
                value=dp[i-1];
            }
            currentMin=Math.min(currentMin, value+costMin1);

            //Case 2
            value=0;
            if(i>=7){
                value=dp[i-7];
            }
            currentMin=Math.min(currentMin, value+costMin2);

            //Case 3
            value=0;
            if(i>=30){
                value=dp[i-30];
            }
            currentMin=Math.min(currentMin, value+costs[2]);
            dp[i]=currentMin;
        }
        return dp[maxDays];
    }

    public static int mincostTicketsOptimized(int[] days, int[] costs) {
        int[] dp = new int[30];
        int travelIdx = 0;
        dp[days[0]%30] = Math.min(costs[0], Math.min(costs[1], costs[2]));
        for (int i = 1; i < days.length; i++) {
            int travelDay = days[i];
            int lastTravelDay = days[i-1];
            if (travelDay > lastTravelDay + 30) {
                // rewrite entire array if last travel day is 30 day or more
                Arrays.fill(dp, dp[lastTravelDay%30]);
            } else {
                // fill non travel days
                int day = lastTravelDay;
                while (++day < travelDay) {
                    dp[day%30] = dp[lastTravelDay%30];
                }
            }

            // add cost as min of buy 1/7/30 ticket to cover previous days
            int cost1 = dp[(travelDay+29)%30] + costs[0];
            int cost7 = dp[(travelDay+23)%30] + costs[1];
            int cost30 = dp[travelDay%30] + costs[2];
            dp[travelDay%30] = Math.min(cost1, Math.min(cost7, cost30));
        }

        int lastDay = days[days.length - 1];
        return dp[lastDay%30];
    }

    public static void main(String[] args) {
//        int days[]=new int[]{1,4,6,7,8,20};
//        int costs[]=new int[]{2,7,15};
//        int days[]=new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
//        int costs[]=new int[]{2,7,15};
//        int days[]=new int[]{1,4,6,7,8,20};
//        int costs[]=new int[]{7,2,15};
        int days[]=new int[]{1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29};
        int costs[]=new int[]{3,14,50};
        System.out.println(mincostTickets(days, costs));
        System.out.println(mincostTicketsOptimized(days, costs));
        //1, Cases sai:
        //1.1, Case khi giá thuê 7 days < 1 days
        //Nên nếu chỉ xét min(dp[i-1]+cost[i-1]) ==> Thiếu
        // dp[i-1]+cost[i-1] < dp[i-7]+cost[i-7]
        //--> Ta có thể thuê 7 ngày luôn thay vì 1 ngày.
        //1.2, Cases sai
        // VD: Do từ ngày đầu tiên ta có thể thuê 50 ngày sau đó --> Nên sau đó ta không cần + thêm giá vào nữa.
        //1.3, i<=7 vẫn có thể thuê giá tiền của 7 ngày --> Ta không if(i>=7) nữa.
        //Mà chỉ if (i>=7) value =cost[i-7]
        //else value=0
    }
}
