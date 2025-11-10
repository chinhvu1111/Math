package leetcode_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E10_OptimalAccountBalancing {

    public static int minTransfers(int[][] transactions) {
        int n=transactions.length;
        HashMap<Integer, Integer> graph=new HashMap<>();

        for(int[] tran: transactions){
            graph.put(tran[0], graph.getOrDefault(tran[0], 0)-tran[2]);
            graph.put(tran[1], graph.getOrDefault(tran[1], 0)+tran[2]);
        }
        List<Integer> nonEmptyAmount=new ArrayList<>();
        for (int e: graph.values()){
            if(e!=0){
                nonEmptyAmount.add(e);
            }
        }
//        System.out.println(nonEmptyAmount);
        //Out goal is to clear the list
        return dfs(0, nonEmptyAmount, nonEmptyAmount.size());
    }

    public static int dfs(int index, List<Integer> nonEmptyAmount, int n){
        while (index<n&&nonEmptyAmount.get(index)==0){
            index++;
        }

        if(index==n){
            return 0;
        }
        int cost=Integer.MAX_VALUE;
        for (int i = index+1; i <n ; i++) {
//            System.out.printf("%s\n", nonEmptyAmount.get(i)*nonEmptyAmount.get(index));
            if(nonEmptyAmount.get(i)*nonEmptyAmount.get(index)<0){
                nonEmptyAmount.set(i, nonEmptyAmount.get(i)+nonEmptyAmount.get(index));
                cost=Math.min(cost, 1+dfs(index+1, nonEmptyAmount, n));
                nonEmptyAmount.set(i, nonEmptyAmount.get(i)-nonEmptyAmount.get(index));
            }
        }
//        System.out.println(cost);
        return cost;
    }

    public static int minTransfersRefer(int[][] transactions) {
        int n=transactions.length;
        HashMap<Integer, Integer> graph=new HashMap<>();

        for(int[] tran: transactions){
            graph.put(tran[0], graph.getOrDefault(tran[0], 0)-tran[2]);
            graph.put(tran[1], graph.getOrDefault(tran[1], 0)+tran[2]);
        }
        List<Integer> nonEmptyAmount=new ArrayList<>();
        for (int e: graph.values()){
            if(e!=0){
                nonEmptyAmount.add(e);
            }
        }
//        System.out.println(nonEmptyAmount);
        //Out goal is to clear the list
        int m=nonEmptyAmount.size();
        int[] memo=new int[1<<m];
        Arrays.fill(memo, -1);
        memo[0]=0;
        return m-dfs1(nonEmptyAmount, memo, (1<<m)-1, m);
    }

    public static int dfs1(List<Integer> nonEmptyAmount, int[] memo, int mask, int n){
        if(memo[mask]!=-1){
            return memo[mask];
        }
        int balanceSum=0, rs=0;
        for (int i = 0; i <n ; i++) {
            int curBit=1<<i;
            if((mask&curBit)!=0){
                balanceSum+=nonEmptyAmount.get(i);
                rs=Math.max(rs, dfs1(nonEmptyAmount, memo, mask^curBit, n));
            }
        }
        //- Tức là tính tổng nợ của all mỗi lần run
        //  + Sum ==0 ==> Cần balance (Vì kiểu gì cũng trái dấu)
        //  ==> We can combine the subgroup altogether
        //  + Sum!=0 ==> Đã cộng rồi không cần thêm
        //  ==> Mỗi lần như thế coi như là 1 lần remove bit (person) ==> Để (sum==0)

        //===> Đoạn này cần clear thêm
        memo[mask]=rs+((balanceSum==0)?1:0);
//        System.out.println(cost);
        return memo[mask];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of transactions) transactions where
        //  + transactions[i] = [from-i, to-i, amount-i]
        // indicates that the person with ID = (from-i) gave (amount-i $) to the person with ID = (to-i).
        //* Return (the minimum number of transactions) required to settle (the debt).
        //  + Min (the number of transactions) such that (the debt of all people) are 0.
        //  ==> Make the number of transaction afterward
        //
        //Example 2:
        //
        //Input: transactions =
        // [
        //  [0,1,10],
        //  [1,0,1],
        //  [1,2,5],
        //  [2,0,5]
        // ]
        //Output: 1
        //Explanation:
        //Person #0 gave person #1 $10.
        //Person #1 gave person #0 $1.
        //Person #1 gave person #2 $5.
        //Person #2 gave person #0 $5.
        //Therefore, person #1 only need to give person #0 $4, and all debt is settled.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= transactions.length <= 8
        //transactions[i].length == 3
        //0 <= from-i, to-i < 12
        //from-i != to-i
        //1 <= amount-i <= 100
        //  + Length(transaction) <= 8 ==> Can use bitmask
        //  + Length(amount) <= 100 ==> Can use the recursion as method
        //
        //* Brainstorm:
        //
        //- Debt means (the negative value) of (the node)
        //- Given graph:
        //  + For each subgraph, we have "only" (one person) debt (the remaining people)
        //  + Is that correct?
        //  ==> Nope
        //  ==> We try to minimize this debt
        //
        //Example 2:
        //
        //Input: transactions =
        // [
        //  [0,1,10],
        //  [1,0,1],
        //  [1,2,5],
        //  [2,0,5]
        // ]
        //   /-------5------\
        // 0 --10--- 1 --5-- 2
        //  \      /
        //   --1---
        //
        //  [0,1,10],
        //      + [0:-10],[1:10]
        //  [1,0,1],
        //      + [0:-9],[1:9]
        //  [1,2,5],
        //      + [0:-9],[1:4],[2:5]
        //  [2,0,5]
        //      + [0:-4],[1:4],[2:0]
        //
        //- How to determine the money of each person?
        //==> Just loop all of transaction is enough
        //
        //1.1, Case
        //Ex:
        //Input: transactions =
        // [
        //  [0,1,10],
        //      + [0:-10],[1:10]
        //  [1,2,5],
        //      + [0:-10],[1:5],[2:5]
        //  [2,3,10]
        //      + [0:-10],[1:5],[2:-5],[3:5]
        // ]
        //
        // [
        //  [0,1,10],
        //      + [0:-10],[1:10]
        //  [1,2,5],
        //      + [0:-10],[1:5],[2:5]
        //  [2,3,10]
        //      + [0:-10],[1:5],[2:-5],[3:10]
        //  [3,0,4]
        //      + [0:-6],[1:5],[2:-5],[3:6]
        // ]
        //
        //- Get all of amount afterward
        //- Try settle the debts for them.
        //- Move (the money) between people
        //  + Just make move if the multiplation between (i,j) <=> (amount[i]*amount[j]<0)
        //  ==> Update the element for arraylist at index takes (O(1)) time.
        //  ==> Use backtracking (bruteforce)
        //
        //1.2, Optimization
        //- Can use bitmask in the memo method.
        //  + We just add the memo using the same method
        //
        //- If the total balance of the current group is zero,
        // it means that all debts can be settled with (a minimal number of transactions),
        // which is (the sum of the optimal solutions) to the subgroups.
        //- If it's not zero, the debts cannot simply cancel out, and
        // you'll need (additional steps) and transactions to balance everything out.
        //
        //Another Example:
        //Now, let's consider a different scenario where the balances don't cancel each other out easily.
        //
        //python
        //Copy
        //Edit
        //balances = [10, -5, -5]
        //Step 1: Calculate the total balance
        //The total balance of this group is:
        //
        //10 + (-5) + (-5) = 0
        //Since the total balance of the group is zero, you can settle these debts among the people in 1 transaction.
        //
        //** Why Increment Answer by 1?
        //- The total balance of the total_mask is 0,
        // meaning the debts cancel each other out,
        // so you can handle this group optimally in 1 transaction.
        //
        //1.3, Complexity
        //- Time: O((m-1)!) ==> O(m*2^m)
        //- Space: O(m)
        //
        int[][] transactions = {{0,1,10},{1,0,1},{1,2,5},{2,0,5}};
        System.out.println(minTransfers(transactions));
        System.out.println(minTransfersRefer(transactions));
    }
}
