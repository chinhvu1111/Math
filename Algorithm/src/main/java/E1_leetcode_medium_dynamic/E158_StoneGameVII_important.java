package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E158_StoneGameVII_important {

//    public static int solution(int[] stones, int left, int right, int curSum){
//        if(left==right){
//            return 0;
//        }
//        int curDiff=0;
//
//        //Diff = x-y
//        curDiff=Math.max(solution(stones, left, right-1, curSum-stones[right])+curSum-stones[right], curDiff);
//        curDiff=Math.max(solution(stones, left+1, right, curSum-stones[left])+curSum-stones[left], curDiff);
//        return curDiff;
//    }

    public static int[][][] memo;

    public static int solution(int[] stones, int left, int right, int curSum, int player){
        if(left==right){
            return 0;
        }
        if(memo[left][right][player]!=-1){
            return memo[left][right][player];
        }
        if(player==0){
            int diff=0;
            diff=Math.max(solution(stones, left+1, right, curSum-stones[left], 1)+curSum-stones[left], diff);
            diff=Math.max(solution(stones, left, right-1, curSum-stones[right], 1)+curSum-stones[right], diff);
            return memo[left][right][player] = diff;
        }else{
            int diff=Integer.MAX_VALUE;
            diff=Math.min(solution(stones, left+1, right, curSum-stones[left], 0)-curSum+stones[left], diff);
            diff=Math.min(solution(stones, left, right-1, curSum-stones[right], 0)- curSum+stones[right], diff);
            return memo[left][right][player] = diff;
        }
    }

    public static int solutionOptimization(int[] stones, int left, int right, int curSum, int[][] memo){
        if(left==right){
            return 0;
        }
        if(memo[left][right]!=-1){
            return memo[left][right];
        }
        int diff=0;
        diff=Math.max(curSum-stones[left] - solutionOptimization(stones, left+1, right, curSum-stones[left], memo), diff);
        diff=Math.max(curSum-stones[right] - solutionOptimization(stones, left, right-1, curSum-stones[right], memo), diff);
        return memo[left][right] = diff;
    }

    public static int stoneGameVII(int[] stones) {
        int n=stones.length;
        int sum=0;
        memo=new int[n][n][2];

        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        for(int i=0;i<n;i++){
            sum+=stones[i];
        }
//        System.out.println(sum);
        return solution(stones, 0, n-1, sum, 0);
    }

    public static int stoneGameVIIOptimization(int[] stones) {
        int n=stones.length;
        int sum=0;
        int[][] memo=new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], -1);
        }

        for(int i=0;i<n;i++){
            sum+=stones[i];
        }
//        System.out.println(sum);
        return solutionOptimization(stones, 0, n-1, sum, memo);
    }

    public static int stoneGameVIIBottomUp(int[] stones) {
        int n=stones.length;
        int[][] dp=new int[n][n];
        int[] prefixSum = new int[n+1];

        for(int i=1;i<=n;i++){
            prefixSum[i]=prefixSum[i-1] + stones[i-1];
        }
//        System.out.println(sum);
        //0,1,2,...,(n-3),(n-2),(n-1)(i)
        for(int i=n-1;i>=0;i--){
            for(int j=i+1;j<n;j++){
                //Left : dp[i+1][j]
                //  + prefixSum[j]-prefixSum[i] => prefixSum[j+1]-prefixSum[i+1]
                //Right : dp[i][j-1]
                //  + prefixSum[j-1]-prefixSum[i-1] => prefixSum[j]-prefixSum[i]
                dp[i][j]=Math.max(prefixSum[j+1]-prefixSum[i+1]-dp[i+1][j], prefixSum[j]-prefixSum[i]-dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Alice and Bob take turns playing a game, with (Alice starting first).
        //- There are (n stones) arranged in a row.
        //- On each player's turn, they can remove either (the leftmost) stone or (the rightmost) stone
        // from the row and receive points equal to the (sum of the remaining stones' values) in the row.
        //- The winner is the one with (the higher score) when there are (no stones) left to remove.
        //- Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize (the score's difference).
        // Alice's goal is to maximize the difference in the score.
        //Given an array of integers stones where stones[i] represents the value of the ith stone from the left,
        //* Return the difference in Alice and Bob's score if they both play optimally.
        //
        //Example 1:
        //
        //Input: stones = [5,3,1,4,2]
        //Output: 6
        //Explanation:
        //- Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
        //- Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
        //- Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
        //- Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
        //- Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
        //The score difference is 18 - 12 = 6.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == stones.length
        //2 <= n <= 1000
        //1 <= stones[i] <= 1000
        //
        //- Brainstorm
        //- Bài này có thể làm top down được.
        //- Nếu Alice and Bob chơi optimally --> return diff
        //- Players behaiviors:
        //  + Alice muốn maximize diff
        //  + Bob muốn minimize diff
        //- 2 players <=> 2 behaviors khác nhau
        //  ==> Chuyển về chung 1 cái được k?
        //
        //(a,b,c,d)
        //Alice: remove a
        // + b+c+d
        //bob: remove d
        // + b+c
        //Alice : remove c
        //  + b
        // Diff = (b+c+d+b) - (b+c) = d+b
        //==> Diff = chính là phần remove của B
        //- Alice chơi trước ==> Phần remove của B phải lớn nhất
        //Ex:
        //5,3,1,4,2
        //Alice: 5,(3,1,4,2) : 3+1+4+2
        //Bob: 3,(1,4,2) : 1+4+2
        //Alice: 1,(4,2) : 4+2
        //Bob: 4,(2) : 2
        //diff = 3+4
        //==> Bob sẽ chơi để lấy được (min remove)
        //- Với min remove có thể dồn về cùng 1 case được không?.
        //
        //* Problems:
        //  - Gặp khó khăn khi phân chia case + caching:
        //      + Do nếu pass sum vào function ==> Không thể caching được vì đến cuối mới biết kết quả có hợp lệ hay không.
        //- Nếu muốn cache thì cần phải tính được tập con + Cache giá trị của tập con được.
        //Ex:
        //5,3,1,4,2
        //Alice: 5,(3,1,4,2) : 3+1+4+2
        //  + diff = subDiff(3,1,4,2) + (3+1+4+2) = 1+4+2 = 7
        //Bob: 3,(1,4,2) : 1+4+2
        //  + diff = subDiff(1,4,2) - (1+4+2) = 4- (1+4+2) = -3
        //Alice: 1,(4,2) : 4+2
        //  + diff = subDiff(4,2) + (4+2) = -2 + (4+2) = 4
        //Bob: 4,(2) : 2
        //  + diff = subDiff(2) - (2) = -2
        //diff = 3+4
        //
        //- Với idea trên thì:
        //  + player == 0: max(solution() + scoreAlice, curScore)
        //  + player == 1: min(solution() - scoreBob, curScore)
        //
        //- Bottom up approach
        //- dp[i][j]: Diff giữa any player nếu tính từ (i -> j)
        //* Cách loop định tiến:
        //- Thường mấy dạng kiểu này thường xét theo (length=2 ==> n)
        //
        //** Bài này ta sẽ loop cố định theo (left):
        //+ (n-2 -> n-1)
        //+ (n-3 -> n-2), (n-3 -> n-1)
        //+ (n-4 -> n-3), (n-4 -> n-2), (n-4 -> n-1)
        //- Vì bài này xét từ (small subset -> big subset):
        //  + Cần tính prefix sum:
        //- Left : dp[i+1][j]
        //  + prefixSum[j]-prefixSum[i] => prefixSum[j+1]-prefixSum[i+1]
        //- Right : dp[i][j-1]
        //  + prefixSum[j-1]-prefixSum[i-1] => prefixSum[j]-prefixSum[i]
        //
        //* CT:
        //- dp[i][j] = max(leftMostScore - dp[i+1][j], rightMostScore - dp[i][j-1])
        //
        //1.1, Optimization
        //- Ta thấy:
        //  + player == 0: max(solution() + scoreAlice, curScore)
        //  + player == 1: min(solution() - scoreBob, curScore)
        //- Merge điều kiện ntn?
        //  + Alice: scoreAlice - diff of bob = scoreAlice - (bobScore - scoreAlice) = Alice's score - bob's score
        //  <> Bob tương tự
        //==> Ta sẽ không care player là ai nữa
        //==> diff = Max(leftMostScore - solution(), rightMostScore - solution())
        //
        //1.2, Complexity
        //- Space: O(n^2 + n) = O(n^2)
        //- Time : O(n^2) = O(n^2)
        //
        //#Reference:
        //1406. Stone Game III
        //1510. Stone Game IV
        //1563. Stone Game V
        //
        int[] stones = {5,3,1,4,2};
//        int[] stones = {5,3,1};
        System.out.println(stoneGameVII(stones));
        System.out.println(stoneGameVIIOptimization(stones));
        System.out.println(stoneGameVIIBottomUp(stones));
    }
}
