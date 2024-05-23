package contest;

public class E102_TakingMaximumEnergyFromTheMysticDungeon {

    public static int maximumEnergy(int[] energy, int k) {
        int n=energy.length;
        int[] dp=new int[n+1];
        int rs=Integer.MIN_VALUE;

        for(int i=1;i<=n;i++){
            if(i-k>=0){
                dp[i]=dp[i-k]+energy[i-1];
                if(dp[i]<=energy[i-1]){
                    dp[i]=energy[i-1];
                }
            }else{
                dp[i]=energy[i-1];
            }
        }
        //k=3
        //energy = [5,2,-10,-5,1]
        //n=5
        //i=n-k --> n-1 ==> (n-k+1, n)
        for(int i=n-k+1;i<=n;i++){
            rs=Math.max(dp[i], rs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- In a mystic dungeon, n magicians are standing in a line.
        //- Each magician has an attribute that (gives you energy).
        //  + Some magicians can give you (negative energy), which means taking energy from you.
        //  + You have been cursed in such a way that after (absorbing energy) from (magician i), you will be instantly transported to magician (i + k).
        //  -> This process will be (repeated) until you reach the magician where (i + k) does (not exist).
        //  -> In other words, you will (choose a starting point) and then teleport with k jumps until you reach the end of the magicians' sequence,
        // absorbing all the energy during the journey.
        //You are given an array energy and an integer k.
        //* Return (the maximum possible energy) you can gain.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= energy.length <= 105
        //-1000 <= energy[i] <= 1000
        //1 <= k <= energy.length - 1
        //  + Length của energy khá lớn ==> O(n)
        //
        //- Brainstorm
        //Example 1:
        //Input:  energy = [5,2,-10,-5,1], k = 3
        //Output: 3
        //
        //- Jump nhưng được chọn điểm bắt đầu
        //  ==> Tức là nếu dp[i] <=0 ==> dp[i]=0 tốt hơn.
        //
//        int[] energy = {5,2,-10,-5,1};
//        int k = 3;
//        int[] energy = {-2,-3,-1};
//        int k = 2;
        int[] energy = {-1000,-2,-3,-1};
        int k = 2;
        System.out.println(maximumEnergy(energy, k));
    }
}
