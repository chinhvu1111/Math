package contest;

public class E159_FindIfDigitGameCanBeWon {

    public static boolean canAliceWin(int[] nums) {
        int n= nums.length;
        int aliceScoreSingle=0;
        int aliceScoreDouble=0;
        int sum=0;

        for(int i=0;i<n;i++){
            if(nums[i]<10){
                aliceScoreSingle+=nums[i];
            }else if(nums[i]<100&&nums[i]>=10){
                aliceScoreDouble+=nums[i];
            }
            sum+=nums[i];
        }
        return aliceScoreSingle>sum-aliceScoreSingle||aliceScoreDouble>sum-aliceScoreDouble;
    }

    public static void main(String[] args) {
        int[]  nums = {1,2,3,4,10};
        System.out.println(canAliceWin(nums));
    }
}
