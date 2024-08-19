package contest;

public class E170_MaximumEnergyBoostFromTwoDrinks {

    public static long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n=energyDrinkA.length;
        long[][] dp=new long[n+1][3];
        dp[1][0]=energyDrinkA[0];
        dp[1][1]=energyDrinkB[0];
        dp[1][2]=0;

        for(int i=2;i<=n;i++){
//            long maxZero = Math.max(dp[i-1][0], dp[i-1][2]);
//            long maxOne = Math.max(dp[i-1][1], dp[i-1][2]);
            //0,1
            dp[i][0] = Math.max(dp[i-1][0]+energyDrinkA[i-1], dp[i-2][1]+energyDrinkA[i-1]);
            dp[i][1] = Math.max(dp[i-1][1]+energyDrinkB[i-1], dp[i-2][0]+energyDrinkB[i-1]);
//            dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }
//        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
        return Math.max(dp[n][0], dp[n][1]);
    }

    public static long maxEnergyBoostRefer(int[] energyDrinkA, int[] energyDrinkB) {
        int n=energyDrinkA.length;
        long[][] dp=new long[n+1][3];
        dp[1][0]=energyDrinkA[0];
        dp[1][1]=energyDrinkB[0];
        dp[1][2]=0;

        for(int i=2;i<=n;i++){
            //0,1
            dp[i][0] = Math.max(dp[i-1][0]+energyDrinkA[i-1], Math.max(dp[i-2][0]+energyDrinkB[i-1], dp[i-1][2]+energyDrinkB[i-1]));
            dp[i][1] = Math.max(dp[i-1][1]+energyDrinkB[i-1], Math.max(dp[i-2][1]+energyDrinkA[i-1], dp[i-1][2]+energyDrinkA[i-1]));
            dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }
        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two integer (arrays energyDrinkA) and (energyDrinkB) of (the same length n) by a futuristic sports scientist.
        //- These arrays represent (the energy boosts per hour) provided by (two different energy drinks), A and B, respectively.
        //- You want to maximize (your total energy boost) by drinking (one energy drink per hour).
        //- However, if you want to switch from consuming one energy drink to the other,
        // you need to (wait for one hour) to (cleanse your system) (meaning you won't get any energy boost in that hour).
        //* Return (the maximum total energy boost) you can gain (in the next n hours).
        //- Note that you can start consuming (either of the two energy drinks).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == energyDrinkA.length == energyDrinkB.length
        //3 <= n <= 10^5
        //1 <= energyDrinkA[i], energyDrinkB[i] <= 10^5
        //+ Length khá lớn => Time: O(n)/ O(n*k)
        //
        //** Brainstorm
        //- Wait 1 hour nữa mới được uống tức là sao:
        //  + ==> lần sau sẽ uống mới (i++)
        //      + Xét đến i:
        //          + Tính theo (i-2)
        //- Cái này có vẻ làm DP được
        //- dp[i][2]
        //- dp[i][0]: Max khi uống loại 1
        //- dp[i][1]: Max khi uống loại 2
        //- Cases:
        //  + dp[i][0] = Max(dp[i-1][0]+eA[i], dp[i-2][0]+eB[i])
        //  + dp[i][1] = Max(dp[i-1][1]+eB[i], dp[i-2][1]+eA[i])
        //
//        int[] energyDrinkA = {4,1,1}, energyDrinkB = {1,1,3};
//        int[] energyDrinkA = {4}, energyDrinkB = {1};
//        int[] energyDrinkA = {5,5,6,3,4,3,3,4};
//        int[] energyDrinkB = {5,3,3,4,4,6,6,3};
        //rs= 35
        //[4,3,4,4,3,6,5,5]
        //[4,6,4,4,5,3,4,4]
        int[] energyDrinkA = {4,3,4,4,3,6,5,5};
        int[] energyDrinkB = {4,6,4,4,5,3,4,4};
        //rs= 34
        System.out.println(maxEnergyBoostRefer(energyDrinkA, energyDrinkB));
        System.out.println(maxEnergyBoost(energyDrinkA, energyDrinkB));
    }
}
