package E1_weekly;

import java.util.Arrays;
import java.util.Comparator;

public class E17_MaximumCoinsHeroesCanCollect {

    public static long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
        int m=heroes.length;
        //Space: O(m)
        //Time: O(m)
        int[][] heroWithIndex=new int[m][2];
        int n=monsters.length;
        //Space: O(n)
        //Time: O(n)
        int[][] monsterCoins=new int[n][2];

        //Time: O(m)
        for (int i = 0; i < m; i++) {
            heroWithIndex[i][0]=heroes[i];
            heroWithIndex[i][1]=i;
        }
        //Time: O(m*log(m))
        Arrays.sort(heroWithIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        //Time: O(n)
        for(int i=0;i<n;i++){
            monsterCoins[i][0]=monsters[i];
            monsterCoins[i][1]=coins[i];
        }
        //Time: O(n*log(n))
        Arrays.sort(monsterCoins, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int j=0;
        long sumCoins=0;
        //Time: O(m)
        //Space: O(m)
        long[] rs=new long[m];

        //Time: O(m)
        for(int i=0;i<m;i++){
            while(j<n&&monsterCoins[j][0]<=heroWithIndex[i][0]){
                sumCoins+=monsterCoins[j][1];
                j++;
            }
            rs[heroWithIndex[i][1]]=sumCoins;
        }
        return rs;
    }

    public static long[] maximumCoinsRefactor(int[] heroes, int[] monsters, int[] coins) {
        int m=heroes.length;
        //Space: O(m)
        //Time: O(m)
        int[][] heroWithIndex=new int[m][2];
        int n=monsters.length;
        //Space: O(n)
        //Time: O(n)
        int[][] monsterCoins=new int[n][2];
        long[] prefixSum = new long[n];

        //Time: O(m)
        for (int i = 0; i < m; i++) {
            heroWithIndex[i][0]=heroes[i];
            heroWithIndex[i][1]=i;
        }
        //Time: O(m*log(m))
        Arrays.sort(heroWithIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        //Time: O(n)
        for(int i=0;i<n;i++){
            monsterCoins[i][0]=monsters[i];
            monsterCoins[i][1]=coins[i];
        }
        //Time: O(n*log(n))
        Arrays.sort(monsterCoins, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int) (o1[0] - o2[0]);
            }
        });
        long sumCoins=0;
        for (int i = 0; i < n; i++) {
            sumCoins+=monsterCoins[i][1];
            prefixSum[i]=sumCoins;
        }
        //Time: O(m)
        //Space: O(m)
        long[] rs=new long[m];
        int low=0;

        //Time: O(m)
        for(int i=0;i<m;i++){
            int index = searchVal(monsterCoins, heroWithIndex[i][0], low);
            if(index!=-1){
                low=index;
                rs[heroWithIndex[i][1]]=prefixSum[index];
            }else{
                break;
            }
        }
        return rs;
    }

    public static int searchVal(int[][] monsterCoins, int heroVal, int low){
        int high=monsterCoins.length-1;
        int rs=-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if(monsterCoins[mid][0]<=heroVal){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static long[] maximumCoinsRefer(int[] heroes, int[] monsters, int[] coins) {
        long[] ans = new long[heroes.length];
        int[][] monsterAndCoin = new int[monsters.length][2];
        for (int i = 0; i < monsters.length; i++) {
            monsterAndCoin[i][0] = monsters[i];
            monsterAndCoin[i][1] = coins[i];
        }

        // sort by ascending value of monster power
        Arrays.sort(monsterAndCoin, (a, b) -> a[0] - b[0]);

        long[] coinsSum = new long[coins.length];
        long prefixSum = 0;
        for (int i = 0; i < monsterAndCoin.length; i++) {
            prefixSum += monsterAndCoin[i][1];
            coinsSum[i] = prefixSum;
        }

        for (int i = 0; i < heroes.length; i++) {
            ans[i] = findTotalCoins(monsterAndCoin, heroes[i], coinsSum);
        }

        return ans;
    }

    private static long findTotalCoins(
            int[][] monsterAndCoin,
            int heroPower,
            long[] coinsSum
    ) {
        int l = 0;
        int r = monsterAndCoin.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (monsterAndCoin[mid][0] > heroPower) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l == 0 && monsterAndCoin[l][0] > heroPower) {
            // hero can't defeat any monsters
            return 0;
        }

        return coinsSum[r];
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (a battle) and (n heroes) are trying to defeat (m monsters).
        //- You are given two ("1-indexed" arrays) of (positive integers heroes) and (monsters of length n and m), respectively.
        //- heroes[i] is the power of (ith hero),
        //  and monsters[i] is the power of (ith monster).
        //- The (ith hero) can (defeat) the (jth monster) if
        //  + monsters[j] <= heroes[i].
        //- You are also given a (1-indexed array) coins of length (m consisting of positive integers).
        // coins[i] is (the number of coins) that (each hero) earns after defeating (the ith monster).
        //* Return (an array ans of length n) where
        //  + ans[i] is (the maximum number of coins) that the (ith hero) can collect from this battle.
        //* Notes:
        //- (The health of a hero) doesn't get reduced after defeating a monster.
        //- (Multiple heroes) can defeat (a monster), but (each monster) can be defeated by (a given hero) (only once).
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n == heroes.length <= 10^5
        //1 <= m == monsters.length <= 10^5
        //coins.length == m
        //1 <= heroes[i], monsters[i], coins[i] <= 10^9
        //  + n,m is quite big: Time: O(n)
        //  1<= heroes[i] <=10^9:
        //      => Long
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: heroes = [1,4,2], monsters = [1,1,5,2,3], coins = [2,3,4,5,6]
        //Output: [5,16,10]
        //Explanation: For each hero, we list the index of all the monsters he can defeat:
        //1st hero: [1,2] since the power of this hero is 1 and monsters[1], monsters[2] <= 1. So this hero collects coins[1] + coins[2] = 5 coins.
        //2nd hero: [1,2,4,5] since the power of this hero is 4 and monsters[1], monsters[2], monsters[4], monsters[5] <= 4.
        // So this hero collects coins[1] + coins[2] + coins[4] + coins[5] = 16 coins.
        //3rd hero: [1,2,4] since the power of this hero is 2 and monsters[1], monsters[2], monsters[4] <= 2.
        // So this hero collects coins[1] + coins[2] + coins[4] = 10 coins.
        //So the answer would be [5,16,10].
        //
        //- Nói chung là (sum các coins) mà (hero ith) có thể defeat:
        //
        //- 2 pointers
        //heroes = [1,4,2], monsters = [1,1,5,2,3], coins = [2,3,4,5,6]
        //Output: [5,16,10]
        //- Cần sort monster + index của coins để có thể check được sum coins
        //- Ở đây ta có thể dùng prefix sum:
        //  + Xác định sum coin cho đến index = i
        //- Còn 2 pointers cho heroes
        //
        //1.1, Optimization
        //- Nếu dùng two pointers
        //  + Traverse mất O(n+m)
        //      + Ta có thể chuyển nó thành O(n*log(m))
        //- Do find theo binary search:
        //  + Hero không cần sort nữa.
        //  => Nếu không sort thì sẽ không cache được (low)
        //
        //1.2, Complexity
        //- Space: O(m+n+log(n)+log(m))
        //- Time: O(n*log(n)+m*log(m)+m+n) => O(max(n,m)*log(m))
        //
        int[] heroes = {1,4,2}, monsters = {1,1,5,2,3}, coins = {2,3,4,5,6};
//        long[] rs = maximumCoins(heroes, monsters, coins);
        long[] rs = maximumCoinsRefactor(heroes, monsters, coins);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,",rs[i]);
        }
        System.out.println();
        //#Reference:
        //2323. Find Minimum Time to Finish All Jobs II
        //1464. Maximum Product of Two Elements in an Array
        //943. Find the Shortest Superstring
    }
}
