package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E94_PaintFence {

    public static int numWays(int n, int k) {
        //Space : O(n*k)
        int[][] dp=new int[n][k];
        //idx=0 : Chỉ có 1 char
        //idx=1 : Có 2 char
        //Time : O(k)
        for(int i=0;i<k;i++){
            dp[0][i]=1;
        }
        //1,1
        //1,2
        //2,1
        //2,2
        if(n>1){
            //Time : O(k)
            for(int i=0;i<k;i++){
                dp[1][i]=k;
            }
        }
        //Time : O(n*k*k)
        for(int i=2;i<n;i++){
            for(int j=0;j<k;j++){
                int val=0;
                for(int h=0;h<k;h++){
                    if(j!=h){
                        val+=dp[i-1][h];
                        val+=dp[i-2][h];
                    }
                }
                dp[i][j]=val;
            }
        }
        int rs=0;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<k;j++){
//                System.out.printf("%s,", dp[i][j]);
//            }
//            System.out.println();
//        }
        //Time : O(k)
        for(int i=0;i<k;i++){
            rs+=dp[n-1][i];
        }
        return rs;
    }

    public static int numWaysOptimization(int n, int k) {
        //Space : O(n*k)
        int[][] dp=new int[n][k];
        //idx=0 : Chỉ có 1 char
        //idx=1 : Có 2 char
        //Time : O(k)
        int sum1=0, sum2=0;
        for(int i=0;i<k;i++){
            dp[0][i]=1;
            sum1+=dp[0][i];
        }
        //1,1
        //1,2
        //2,1
        //2,2
        if(n>1){
            //Time : O(k)
            for(int i=0;i<k;i++){
                dp[1][i]=k;
                sum2+=dp[1][i];
            }
        }
        //Time : O(n*k*k)
        for(int i=2;i<n;i++){
            int sum=0;

            for(int j=0;j<k;j++){
                dp[i][j]+=sum2-dp[i-1][j];
                dp[i][j]+=sum1-dp[i-2][j];
                sum+=dp[i][j];
            }
            sum1=sum2;
            sum2=sum;
        }
        int rs=0;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<k;j++){
//                System.out.printf("%s,", dp[i][j]);
//            }
//            System.out.println();
//        }
        //Time : O(k)
        for(int i=0;i<k;i++){
            rs+=dp[n-1][i];
        }
        return rs;
    }

    public static HashMap<Integer, Integer> memo;

    public static int solution(int i, int k){
        if(i==1){
            return k;
        }
        if(i==2){
            return k*k;
        }
        if(memo.containsKey(i)){
            return memo.get(i);
        }
        int val=(k-1)*(solution(i-1, k)+solution(i-2, k));
        memo.put(i, val);
        return val;
    }

    public static int numWaysReference(int n, int k) {
        memo=new HashMap<>();
        memo.put(0, k);
        memo.put(1, k*k);
        //Space : O(n*k)
        return solution(n, k);
    }

    public static int numWaysReference1(int n, int k) {
        int[] dp=new int[n];
        dp[0]=k;
        dp[1]=k*k;

        for(int i=2;i<n;i++){
            dp[i]=(k-1)*(dp[i-1]+dp[i-2]);
        }
        //Space : O(n*k)
        return dp[n-1];
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given the two integers n and k
        //- n : là số lượng posts
        //- k : là số lượng colors
        //- You are painting a fence of (n posts) with (k different colors).
        // You must paint the posts following these rules:
        //+ Every post must be painted (exactly one color) ==> Cái này cho vui vì luôn color mỗi post 1 color
        //+ There cannot be (three or more consecutive posts) with the (same color).
        //* Return the number of ways you can paint the fence.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 50
        //1 <= k <= 10^5
        //The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
        //
        //- Brainstorm
        //Ex:
        //Input: n = 3, k = 2
        //Output: 6
        //- Bài này làm dynamic ntn?
        //- >=3 posts liên tiếp không được cùng color
        //Ex:
        //[A, B, C]
        //- Define Dp[n][k]
        //  + dp[i][j]:
        //   + i là index của post
        //   + j là color của post
        //- a,b,c
        //Thoả mãn khi:
        //+ (a!=b) or (b!=c), or (a!=c)
        //
        //- Tư duy trên sai
        //dp[i][j] : số lượng case thoả mãn
        //  + i là index của array
        //  + j là số lượng element giống nhau
        //- Số cases mà không thể có 3 posts cạnh nhau giống nhau
        //  + Có thể bao gồm cases 2 posts cạnh nhau khác nhau
        //  + Có thể bao gồm cases 2 posts cạnh nhau giống nhau
        //
        //Ex:
        //Input: n = 3, k = 2
        //Output: 6
        //Colors : 0,1,2
        //+ Cases 2 posts cạnh nhau khác nhau:
        //0,1,0
        //0,2,0
        //1,2,1
        //1,0,1
        //2,0,2
        //2,1,2
        //=> dp[3][0]=6
        //+ (Cases 2 posts cạnh nhau giống nhau) + (Không phải 3 posts giống nhau):
        //1,1,0
        //2,2,0
        //0,0,1
        //==> Cases này không xác định được.
        //
        //- Case 3 posts hoặc nhiều hơn 3 posts giống nhau ==> Khá khó để lấy bù
        //- Case 3 posts khác nhau:
        //Ex:
        //Colors : 0,1,2
        //0,1,1,2
        //0,0,1,2
        //1,0,0,2
        //0,(0,2,2)
        //- 3 character liên tiếp khác nhau:
        //  + Xét character thứ i:
        //   + Chỉ cần s[i]!=s[i-1]/s[i-2] là được.
        //dp[i][j] : là số lượng cases thu được khi đến vị trí thứ i + fill color[j]
        //
        //1.1, Optimization
        //- Ta có thể reduce time --> O(N) được không?
        //- Ta có thể lưu sum all của [i-1][0->k-1], [i-2][0->k-1] :
        //  + Nếu xét j!=h:
        //   + val+=sum1-[i-1][j]
        //   + val+=sum2-[i-2][j]
        //  + swap các sum:
        //   + sum1=sum2;
        //   + sum2=sum;
        //-> Time = O(N*K)
        //
        //1.2, Complexity
        //- Space : O(N*K)
        //- Time : O(N*K*K) => Optimize => O(N*K)
        //
        //2. Top down strategy
        //- totalWays[i] : Là số lượng cách để tô màu cho post thứ ith
        //Ex:
        //totalWays(1) = k, totalWays(2) = k * k.
        //- Use a different color than the previous post. If we use a different color, then there are k - 1 colors for us to use.
        // This means there are (k - 1) * totalWays(i - 1) ways to paint the ith post a different color than (i-1)th post
        //- Ta cần 3 posts liên tiếp được painted khác:
        //  totalWays(i)= (k-1) *(totalWays(i-1) + totalWays(i-2))
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space : O(N)
        //- Time : O(N)
//        int n=3, k=2;
        int n=7, k=2;
        //#Reference:
        //256. Paint House
        //265. Paint House II
        //
        System.out.println(numWays(n, k));
        System.out.println(numWaysOptimization(n, k));
        System.out.println(numWaysReference(n, k));
        System.out.println(numWaysReference1(n, k));
    }
}
