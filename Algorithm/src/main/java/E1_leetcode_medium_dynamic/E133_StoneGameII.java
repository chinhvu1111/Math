package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E133_StoneGameII {

    public static int[][] dp;
    public static int solution(int index, int m, int n, int[] suffixSum, int[] piles){
        if(index>=n){
            return 0;
        }
        if(dp[index][m]!=-1){
            return dp[index][m];
        }
        int maxIndex = Math.min(n-1, index+2*m-1);
        int curMaxStone=Integer.MIN_VALUE;
//        System.out.println(maxIndex);
        int curStone=0;

        //Index range : (i, n-1)
        //Upper bound:
        //- M=N
        //Time : O(n)
        for(int i=index;i<=maxIndex;i++){
            int x=i-index+1;
            //Index range = (i+1, n-1)
            int nextStones = solution(i+1, Math.max(x, m), n, suffixSum, piles);
            curMaxStone=Math.max(curMaxStone, suffixSum[i]-nextStones+curStone);
            //Bỏ qua cái hiện tại vì cái này sẽ add vào player sau
            curStone+=piles[i];
        }
        dp[index][m]=curMaxStone;
//        System.out.printf("dp[index][m]: %s, index: %s, m:%s\n", dp[index][m], index, m);
        return dp[index][m];
    }
    public static int stoneGameIITopDown(int[] piles) {
        int n= piles.length;
        dp=new int[n][n*2+1];
        int[] suffixSum=new int[n+1];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        for(int i=n-1;i>=0;i--){
            suffixSum[i]=suffixSum[i+1]+piles[i];
//            System.out.println(suffixSum[i]);
        }
        return solution(0, 1, n, suffixSum, piles);
    }

    public static int stoneGameIIBottomUp(int[] piles) {
        int n= piles.length;
        dp=new int[n][n*2+1];
        int[] suffixSum=new int[n+1];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        for(int i=n-1;i>=0;i--){
            suffixSum[i]=suffixSum[i+1]+piles[i];
        }
        return solution(0, 1, n, suffixSum, piles);
    }
    public static void main(String[] args) {
        //** Requirement:
        //- Alice and Bob continue their games with piles of stones.
        // There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
        // The objective of the game is to end with the most stones.
        //Alice and Bob take turns, with Alice starting first.
        //+ Initially, M = 1.
        //+ On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
        //+ Then, we set M = max(M, X).
        //The game continues until all the stones have been taken.
        //* Assuming Alice and Bob play optimally,
        // return the maximum number of stones Alice can get.
        //- Tức mỗi turn thì play có thể lấy:
        //  + X first remaining pipes (Tức là lấy randome số X của các pipes còn lại)
        //  + Update M = Max(M, X)
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= piles.length <= 100
        //1 <= piles[i] <= 10^4
        //
        //- Brainstorm
        //Input: piles = [2,7,9,4,4]
        //Output: 10
        //+ Alice: take 1 pipe => M=1
        //  + stone = 2
        //  + range: (1<=X<=2)
        //+ Bob : take two pipes => M=2
        //  + stone = 7+9
        //  + range: (1<=X<=4)
        //+ Alice : take two pipes:
        //  + stone = 4+4
        //  + range: (1<=x<=4)
        //
        //- Bài này có thể làm top down được không?
        //+ Ta thấy mỗi player sẽ chọn phương án mà người còn lại lấy được ít nhất
        //- Liệu có trường hợp mà stones lấy từ 2 players không bù cho nhau (Tổng không bằng sum) không?
        //  + Không vì min(M) = 2 : 1<=X<=2*M
        //      + Chỉ có thể chọn >=1 mà thôi
        //  + ==> Có thể làm top down được
        //+ Dp[i][j]:
        //  + Là giá trị stones max nhất mà player có thể lấy được tại index=i + (M = j)
        //
        //- Return số stones mà Alice lấy ntn?
        //  + solution = min(solution(...)
        //- Bài này ta sẽ cần thêm suffixSum để có thể tính sum cho từng player nếu:
        //  + index<=i<=maxIndex:
        //  + Ta sẽ cần tính nextStone của player tiếp
        //  + Sau đó cần suy ra player hiện tại sẽ lấy được bao nhiêu = suffixSum[i]-nextStone
        //  + Ngoài ra hiện tại cũng lấy sum(stones) từ (index -> i) ta cũng cần cộng thêm nữa.
        //
        //* Bottom up aproach?
        //  - Bài này dường như chỉ có cách top down.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Top down approach:
        //  + Space : O(n^2)
        //  + Time : O(N^3)
        //- It is quite complex to estimate
        //+ dp[index][m]
        //  + We have O(n*m) state
        //  + For each (i, j): we will try to loop:
        //      + (i, Max(i+2*j, n-i)):
        //         + Up bound = O(n)
        //  ==> O(N^3)
        //              0
        //       /    /  \   \
        //     0     1    2   3
        //   (1->n-1)
        //** Kinh nghiệm tính complexity trong memoziation:
        //- Dựa trên số lượng state của memo + Lấy upper bound để tính cận trên là được.
        //
//        int[] piles={2,7,9,4,4};
        int[] piles={1,2,3,4,5,100};
        System.out.println(stoneGameIITopDown(piles));
        System.out.println(stoneGameIIBottomUp(piles));
        //#Reference:
        //1563. Stone Game V
        //1686. Stone Game VI
        //1690. Stone Game VII
    }
}
