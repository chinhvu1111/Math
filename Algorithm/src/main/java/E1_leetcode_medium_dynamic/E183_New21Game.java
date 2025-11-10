package E1_leetcode_medium_dynamic;

public class E183_New21Game {

    public static double new21Game(int n, int k, int maxPts) {
        double[] dp=new double[n+1];
        dp[0]=1;
        for (int i=1;i<=n;i++){
            for (int j = 1; j <=maxPts ; j++) {
                if(i>=j && i-j<k){
                    //Sum all (dp[i-1] => dp[i-maxPts])
                    //Ex:
                    //(dp[2-1] => dp[2-maxPts])
                    //  + (dp[1] => dp[2-maxPts])
                    //(dp[3-1] => dp[3-maxPts])
                    //  + (dp[2] => dp[3-maxPts])
                    //(dp[4-1] => dp[4-maxPts])
                    //  + (dp[3] => dp[4-maxPts])
                    //
                    //- We just make the (left shift) sum
                    //- Slide window
                    //  + dp[i]-=dp[i-1]
                    //  + dp[i]+=dp[i-maxPts]
                    //==> Wrong
                    //(dp[2-1] => dp[2-maxPts])
                    //  ==> Big to small
                    //(dp[3-1] => dp[3-maxPts])
                    //  + 2-maxPts < 3-1???
                    //==> Từ nhỏ đến lớn
                    //- s+=dp[3-1]
                    //- s-=dp[i]
                    //* Main point:
                    //- Cần phải tính s cho next value ==> In the current turn
                    //
                    //
                    //Small to big
                    dp[i]+=dp[i-j]/maxPts;
                }
            }
        }
        //  0 1 2 3 4 5 ... n
        //1
        //2
        //3
        //4
        //5
        //...
        //n
        //- Focus on the sum from (k to n)
        //
        double rs=0;
        for (int i = k; i <= n; i++) {
            rs+=dp[i];
        }
        return rs;
    }

    public static double new21GameRefactor(int n, int k, int maxPts) {
        double[] dp=new double[n+1];
        dp[0]=1;
        double s=k>0?1:0;
        for (int i=1;i<=n;i++){
            //Calculate the current value
            dp[i]=s/maxPts;
            if(i<k){
                s+=dp[i];
            }
            //Set dp[i]= maxPts/s
            //= Remember that s is the probability of all the states that we could have reached i from.
            // We need to divide it by maxPts as we mentioned earlier, that is the probability of reaching i from each of the states.
            if(i-maxPts>=0&&i-maxPts<k){
                s-=dp[i-maxPts];
            }
        }
        double rs=0;
        for (int i = k; i <= n; i++) {
            rs+=dp[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Alice plays (the following game), loosely based on the card game "21".
        //- Alice starts with (0 points) and draws numbers while she has (less than) k points.
        // During (each draw), she gains (an integer number of points) randomly from the range [1, maxPts],
        // where (maxPts) is an integer.
        //- (Each draw) is (independent) and the outcomes have (equal probabilities).
        //- Alice stops (drawing numbers) when she gets (k) or (more points).
        //* Return (the probability) that Alice has (n or fewer points).
        //
        //- Answers within (10^-5) of the actual answer are considered accepted.
        //
        //Example 2:
        //
        //Input: n = 6, k = 1, maxPts = 10
        //Output: 0.60000
        //Explanation: Alice gets (a single card), then stops.
        //In 6 out of 10 possibilities, she is at or below 6 points.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //0 <= k <= n <= 10^4
        //1 <= maxPts <= 10^4
        //  + 0 <= k <= n <= 10^4 ==> Time: O(n)
        //
        //* Brainstorm:
        //
        //Example 2:
        //
        //Input: n = 6, k = 1, maxPts = 10
        //Output: 0.60000
        //Explanation: Alice gets a single card, then stops.
        //In 6 out of 10 possibilities, she is at or below 6 points.
        //
        //- rs = dp[1]+dp[2]+...+dp[n]
        //- Choose [1 -> maxPts]
        //- >=k ==> Stop
        //- k<n: return 0
        //- k>=n:
        //Ex:
        //k=5
        //  + Choose k times:
        //      + Each time, we choose one of values in range [1,maxPts]
        //1+2+4+1+3
        //- For i to n:
        //  rs+=dp[i]
        //
        //- dp[i] for k≤i≤n is the state of the game when it's over, and Alice has n or fewer points.
        // It implies that the answer to the problem is the sum of dp for all such states.
        //- When i moves to the right, s needs to change accordingly.
        //- Essentially, s is a sliding window. We remove dp[i−maxPts] and add dp[i] until i≥k.
        //
        //Sum all (dp[i-1] => dp[i-maxPts])
        //Ex:
        //(dp[2-1] => dp[2-maxPts])
        //  + (dp[1] => dp[2-maxPts])
        //(dp[3-1] => dp[3-maxPts])
        //  + (dp[2] => dp[3-maxPts])
        //(dp[4-1] => dp[4-maxPts])
        //  + (dp[3] => dp[4-maxPts])
        //
        //** Main point:
        //- We just make the (left shift) sum
        //- Slide window
        //  + dp[i]-=dp[i-1]
        //  + dp[i]+=dp[i-maxPts]
        //==> Wrong
        //(dp[2-1] => dp[2-maxPts])
        //  ==> Big to small
        //(dp[3-1] => dp[3-maxPts])
        //  + 2-maxPts < 3-1???
        //==> Từ nhỏ đến lớn
        //- s+=dp[3-1]
        //- s-=dp[i]
        //
        //** Main point:
        //- Cần phải tính s cho next value ==> In the current turn
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //2926. Maximum Balanced Subsequence Sum - HARD
        //2029. Stone Game IX
        //3409. Longest Subsequence With Decreasing Adjacent Difference
        int n = 6, k = 1, maxPts = 10;
        System.out.println(new21Game(n, k, maxPts));
        System.out.println(new21GameRefactor(n, k, maxPts));
    }
}
