package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E303_CountTheNumberOfIdealArrays {

    public static int idealArraysWrong(int n, int maxValue) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for (int i = 1; i <= maxValue; i++) {
            for(int j=1;j*j<=i;j++){
                if(i%j==0){
                    mapCount.put(j, mapCount.getOrDefault(j, 0)+1);
                }
            }
        }
        int[][]dp=new int[n+1][maxValue+1];

        for (int i = 0; i < n; i++) {
//            for (int j = 1; j <= maxValue; j++) {
//            }
        }
        return 1;
    }

    public static int maxN=10010;
    public static int maxP=15;
    //c(k)(n) <=> c[n][k]
    public static int[][] c=new int[maxN+maxP][maxP+1];
    public static int[] sieve =new int[maxN];
    public static List<Integer>[] ps=new List[maxN];
    public static int mod = 1_000_000_007;

    public static void preCalculate(){
        if(c[0][0]==1){
            return;
        }
        for(int i=0;i<maxN;i++){
            ps[i]=new ArrayList<>();
        }
        //24 = 2*2*2*3
        //==> sieve[2],sieve[4],sieve[6],sieve[8],...,sieve[24]=2
        //9 = 3*3
        //==> sieve[3]=3,sieve[9]=3
        //
        //x=2*3*7 = 42
        for (int i = 2; i < maxN; i++) {
            if(sieve[i]==0){
                for(int j=i;j<maxN;j+=i){
                    if(sieve[j]==0){
                        sieve[j]=i;
                    }
                }
            }
        }
        for(int i=2;i<maxN;i++){
            int x=i;
            //x=2*3*7 = 42
            //ps[i] = [1,1,1]
            //
            //x=2*2*2*3 = 24
            //ps[i] = [3,1]
            while(x>1){
                //p could be:
                //[42,21,7]
                int p=sieve[x];
                //Count(k)
                int count=0;
                while(x%p==0){
                    x/=p;
                    count++;
                }
                ps[i].add(count);
            }
        }
        c[0][0]=1;
        for(int i=1;i<maxN+maxP;i++){
            c[i][0]=1;
            for(int j=1;j<=Math.min(i, maxP);j++){
                c[i][j]=(c[i-1][j]+c[i-1][j-1])%mod;
            }
        }
    }

    public static int idealArrays(int n, int maxValue) {
        preCalculate();
        long rs=0;
        for(int i=1;i<=maxValue;i++){
            long curRs=1;
            //end with(i)
            //
            //24 = 2*2*2*3
            //Put them in 5 positions
            //c(k)(n) <=> c[n][k]
            //
            //x=2*2*2*3 = 24
            //ps[i] = [3,1]
            //==> add(3) không chỉ mỗi khe điền 1 số 2
            //  + Coordinate (3 2 digits) to (n+p-1) positions
            for(int p: ps[i]){
                curRs=(curRs*c[n+p-1][p])%mod;
            }
            rs=(rs+curRs)%mod;
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers (n and maxValue), which are used to describe (an ideal array).
        //- (A 0-indexed integer array arr) of (length n) is considered ideal if the following conditions hold:
        //  + Every arr[i] is a value from (1 to maxValue), for 0 <= i < n.
        //  + Every arr[i] is (divisible by arr[i - 1]), for 0 < i < n.
        //* Return (the number of distinct ideal arrays) of length n.
        //* Since the answer may be very large, return it modulo (10^9 + 7).
        //
        //Example 2:
        //
        //Input: n = 5, maxValue = 3
        //Output: 11
        //Explanation: The following are the possible ideal arrays:
        //- Arrays starting with the value 1 (9 arrays):
        //   - With no other distinct values (1 array): [1,1,1,1,1]
        //   - With 2nd distinct value 2 (4 arrays): [1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
        //   - With 2nd distinct value 3 (4 arrays): [1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
        //- Arrays starting with the value 2 (1 array): [2,2,2,2,2]
        //- Arrays starting with the value 3 (1 array): [3,3,3,3,3]
        //There are a total of 9 + 1 + 1 = 11 distinct ideal arrays.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 10^4
        //1 <= maxValue <= 10^4
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //- Count the number of layer
        //Ex:
        // 8 -> 4 -> 2
        //  + Layer = 3
        //Ex:
        // 8 -> 4 -> 2
        //   \
        //    2 --> 1
        //- Count ==> This is good point to use the dynamic programming
        //Ex:
        //dp[i] = f(i-1)
        //- n=1
        //1,2,3,...,maxValue
        //- n=2
        //- Check (1,2,3,...,maxValue)
        //  + 1: 1
        //  + 2: 1,2 (end with) = dp[i-1][1] + dp[i-1][2]
        //  + 3: 1,3 = dp[i-1][1] + dp[i-1][3]
        //  + 4: 1,2,4
        //  + 5: 1,5
        //  + 6: 1,2,3
        //- maxValue = 6
        //+ dp[1] = 6
        //
        //- dp[2] (end with 1->maxValue)
        //==> (count(1) + count(2) + count(3) +...+ count(6))
        // = [1]*6 + [2]*2 + [3]*2 + [4]*1 + [5]*1 + [6]*1
        //- For each number x in (1 -> maxValue):
        //  + We need to check the numbers that x is divisible by them
        //  ==> It takes O(n) time
        //  ==> Over complicated
        //
        //- 1 -> maxValue:
        //  + [1]*6 + [2]*2 + [3]*2 + [4]*1 + [5]*1 + [6]*1 ==> Always fix???
        //  ==> It is not correct because we need to (re-calculate):
        //      + [1],[2],...,maxValue if possible
        //
        //- One bit of general knowledge to have is that you can uniquely factor x into a unique product of primes
        // (called the Fundamental Theorem of Arithmetic). For instance, 24 = 2 * 2 * 2 * 3.
        //- Using the bucketed prime factors we can reconstruct the ideal array by multiplying each bucket from left to right.
        // Treating empty buckets as 1 and buckets with factors as the product of all of them.
        //- Consider the following bucketing for n=5 and x=24=2*2*2*3:
        //[[], [2,2], [], [2], [3]]
        //
        //- We can get the ideal array:
        //[1, 4, 4, 8, 24]
        //Or some other examples:
        //
        //[[2], [], [2], [2], [3]]    -> [2, 2, 4,  8, 24]
        //[[], [], [2], [2,2], [3]]   -> [1, 1, 2,  8, 24]
        //[[3], [], [2], [2,2], []]   -> [3, 3, 6, 24, 24]
        //[[], [], [], [3], [2,2,2]]  -> [1, 1, 1,  3, 24]
        //[[], [], [], [], [2,2,2,3]] -> [1, 1, 1,  1, 24]
        //
        //- Notice a few things:
        //- We always (end up at x), since we (end up multiplying all the factors) eventually
        // and (the order of multiplication) doesn't matter.
        //
        //* Calculating the number of ways to bucket x
        //- Using basic combinatorics we can calculate the number of ways
        // we can (k_i copies) of (a prime factor p_i) into (n buckets).
        //  + C(k)(n)
        //
        //* Explanation:
        //x=2*3*7 = 42
        //ps[i] = [1,1,1]
        //
        //x=2*2*2*3 = 24
        //ps[i] = [3,1]
        //
        //n=5
        //[x,x,x,x,x]
        //[p,p,p,p]
        //
        //- Assuming that a certain prime factor of x has (p occurrences), the number of different (k-i)
        //  sequences that can be formed from these prime factors is a classic combinatorial problem,
        //  which can be solved using the partition method.
        //- In n empty slots, (n−1) dividers can be inserted, (plus p) prime factors, making a total of (n−1+p) positions.
        //- In these positions, p prime factors are placed, and the number of schemes is C(n+p−1,p).
        // For different prime factors, there is no influence between them, and the multiplication principle can be used for calculation.
        //
        //* Explanation:
        //x=2*2*2*3 = 24
        //ps[i] = [3,1]
        //==> add(3) không chỉ mỗi khe điền 1 số 2
        //  + Coordinate (3 2 digits) to (n+p-1) positions
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O((n+ω(m))⋅ω(m)+mω(m)).
        //  + Let m be the maxValue, and n be the length of the arr array.
        //  + ω(m) represents (the number of distinct prime factors) of m, and (its average order) in number theory is log*(logm).
        //  For more details, please refer to the Prime omega function.
        //- Space: O((n+log(m))⋅log(m)).
        //
        //#Refer:
        //- Explain the documentation:
        //https://leetcode.com/problems/count-the-number-of-ideal-arrays/solutions/6675762/explaining-the-editorial-by-kosievdmerwe-gvhh/
        //
        //
        int n = 2, maxValue = 5;
        System.out.println(idealArrays(n, maxValue));
    }
}
