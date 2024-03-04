package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E121_MaximumValueOfKCoinsFromPiles {

    public static int[][] memo;

    public static int solutionTopDown(List<List<Integer>> pipes, int n, int coins){
        if(coins==0||n==0){
            return 0;
        }
        if(memo[n-1][coins]!=-1){
            return memo[n-1][coins];
        }
        int min=Math.min(pipes.get(n-1).size(), coins);
        int curValCoins=0;
        for(int i=0;i<=min;i++){
            if(i!=0){
                curValCoins+=pipes.get(n-1).get(i-1);
            }
            memo[n-1][coins]= Math.max(memo[n-1][coins], solutionTopDown(pipes, n-1, coins-i) + curValCoins);
        }
        return memo[n-1][coins];
    }

    public static int maxValueOfCoinsTopDown(List<List<Integer>> piles, int k) {
        int n=piles.size();
        memo=new int[n+1][k+1];

        for(int[] arr: memo){
            Arrays.fill(arr, -1);
        }
        return solutionTopDown(piles, n, k);
    }

    public static int maxValueOfCoinsBottomUp(List<List<Integer>> piles, int k) {
        int n=piles.size();
        //Space: O(n*k)
        int[][] dp=new int[n+1][k+1];

        //Time: O(n*k*min(len(avg_pipes, avg_k))) ~ O(k*s)
        for(int i=1;i<=n;i++){
            for(int h=0;h<=k;h++){
                //0 -> k ~ k^2
                //length(pipes) ~ len^2
                int min=Math.min(piles.get(i-1).size(), h);
                int curCoins=0;

                for(int j=0;j<=min;j++){
                    if(j!=0){
                        curCoins+=piles.get(i-1).get(j-1);
                    }
                    dp[i][h]=Math.max(dp[i][h], dp[i-1][h-j]+curCoins);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- There are n piles of coins on a table.
        //- Each pile consists of a positive number of coins of (assorted denominations).
        //- In (one move), you can (choose any coin) on (top) of any pile, (remove) it, and (add) it to your wallet.
        //- Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom,
        // and a positive integer k
        //* Return (the maximum total value of coins) you can have in your wallet if you choose (exactly k coins) optimally.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == piles.length
        //1 <= n <= 1000
        //  - Pipes có lengh (<=1000)
        //1 <= piles[i][j] <= 10^5
        //1 <= k <= sum(piles[i].length) <= 2000
        //  - Số lượng pipe (<=2000)
        //  ==> Có thể xử lý O(n)
        //
        //- Brainstorm
        //- 1 move thì ta có thể chọn:
        //  + 1 trong n pipe
        //Ex:
        //Input: piles = [[1,100,3],[7,8,9]], k = 2
        //Output: 101
        //1     |   7
        //100   |   8
        //3     |   9
        //->
        //      |   7
        //100   |   8
        //3     |   9
        //val=1
        //->
        //      |   7
        //      |   8
        //3     |   9
        //val=101
        //
        //- Chọn k coins --> max sum
        //- Ta chọn không cần thứ tự giữa các pipes
        //  + Ta chọn sao cũng được
        //  ==> Nhưng ta cần chọn từ (top --> bottom) với mỗi pipe
        //
        //- Remove wallet
        //  + Change index++
        //  + Prefix sum.
        //- Tức là với:
        //  + Lấy (x) coins --> tính ra từ (x-1) coins như thế nào?
        //==> Ta sẽ chọn 1 coin từ N pipes.
        //- Nếu chọn 1 coin từ N pipes ==> Dù loop all pipes thì kết quả cũng không đúng
        //
        //- dp[i][k]
        //  + Tức là k coins nếu ta chọn (i)th pipe là lần cuối.
        //Ex:
        //Input: piles = [[1,100,3],[7,8,9]], k = 2
        //Output: 101
        //1     |   7
        //100   |   8
        //3     |   9
        //->
        //      |   7
        //100   |   8
        //3     |   9
        //val=1
        //->
        //      |   7
        //      |   8
        //3     |   9
        //val=101
        //
        //- Cases:
        //+ k=1 (Dùng 1 coin)
        //+ k=2 (Dùng 2 coins)
        //  + k=2 tính theo k=1
        //     + 1 coin đó có thể chọn từ n pipe
        //  ==> Tức là dp[k=1][i]
        //     + k là số coins
        //     + i là index của pipe mà ta chọn lần cuối cùng ==> Index của coin lần cuối cùng chọn không có giá trị
        //      gì nhiều lắm
        //- Nếu ta quy về 1 pipe
        //  + dp[1->k]: là số lượng value mà ta lấy được từ (1->k) coins từ 1 pipe
        //=> Map ra N pipes
        //  + dp[1->k][i]: là số lượng value mà ta lấy được từ (1->k) coins từ (index=i)th pipe
        //- Với max=1000 pipes
        //  + Ta phải chọn sao cho (x+x1+...+xn)
        //      + x1 : là số coins chọn từ first pipe
        //      + x2 : là số coins chọn từ second pipe
        //      + x3 : là số coins chọn từ third pipe
        //      .....
        //- Phần này ta sẽ dynamic lần 2 để tìm.
        //Ex:
        //- Cần chọn k coins
        //Input:
        //piles = [[1,100,3],[7,8,9]], k = 2
        //1     |   7
        //100   |   8
        //3     |   9
        //+ Chọn k=2 coins:
        //  + 1 coin pipe 1, 1 coin pipe 2 = 1 + 7 = 8
        //  + 2 coin pipe 1 = 7 + 8 = 15
        //  + 2 coin pipe 2 = 1 + 100 = 101
        //+ Chọn k=3 coins:
        //  + 2 coins từ pipe1, 1 coin từ pipe2 = (1+100) + 7 = 108
        //  + 1 coins từ pipe1, 2 coin từ pipe2 = 1 + (7+8) = 16
        //  + 3 coins từ pipe1 = 1 + 100 + 3 = 104
        //  + 3 coins từ pipe2 = 7 + 8 + 9 = 24
        //Max = 108
        //- Ta chỉ được phép chọn X coins từ pipe-i 1 lần duy nhất
        //- Giả sử dp[k]:
        //  + Là chứa số value lớn nhất khi chọn k coins
        //- val[k][i]:
        //  + Lưu value nếu chọn pipe thứ ith + chọn k coins từ nó
        //- Mỗi lần ta chỉ chọn 1 pipe
        //Ex:
        //1     |   7
        //100   |   8
        //3     |   900 => Thay 1 chút
        //+ Chọn k=2 coins:
        //  + 1 coin pipe 1, 1 coin pipe 2 = 1 + 7 = 8
        //  + 2 coin pipe 1 = 7 + 8 = 15
        //  + 2 coin pipe 2 = 1 + 100 = 101
        //+ dp[1]=7
        //+ dp[2]=101
        //dp[3] tính theo:
        //  + dp[2] : ==> Khá khó vì không rõ phần bù được tính ntn, nó phụ thuộc vào các giá trị cũ đã được tính trước đó
        //  + dp[1] :
        //
        //- Để dễ thì ta sẽ làm top down method trước
        //- N là số pipe được chọn, ta có thể:
        //  + Chọn pipe[N-1] <> không chọn
        //  + Chọn thì ta được phép chọn 1 -> k coins
        //  + Không chọn thì ta sẽ giữ nguyên coins
        //  + Tính tiếp cho N-1
        //==> dp[N][k] sẽ lặp lại:
        //  + N là số pipes được chọn tiếp, k là số coins còn lại ta cần chọn trong N pipes.
        //** Kinh nghiệm:
        //- Với dạng chọn snapsack ==> Ta sẽ quy về việc "được phép chọn đến vị trí thứ" ith
        //  + Sau đó giảm dần, thì ta luôn có 2 options:
        //      + Chọn
        //      + Không chọn
        //
        //- Bottom up approach:
        //- Vẫn xuất phát từ 1 -> n
        //+ dp[n][coins]= dp[n-1][coins-currentCoins]
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n*k)
        //- Time: O(n*k*k)
        //
        //#Reference:
        //2527. Find Xor-Beauty of Array
        //2420. Find All Good Indices
        //1872. Stone Game VIII
        Integer[][] pipes={{1,100,3},{7,8,9}};
        int k=2;
        List<List<Integer>> input=new ArrayList<>();
        for(Integer[] arr: pipes){
            input.add(Arrays.asList(arr));
        }
        System.out.println(maxValueOfCoinsTopDown(input, k));
        System.out.println(maxValueOfCoinsBottomUp(input, k));
    }
}
