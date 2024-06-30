package contest;

import java.util.*;

public class E129_MaximumTotalDamageWithSpellCasting {

    public static long maximumTotalDamage(int[] power) {
        int n=power.length;
        HashMap<Long, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            mapCount.put((long) power[i], mapCount.getOrDefault((long)power[i], 0)+1);
        }
        List<Long> keys=new ArrayList<>(mapCount.keySet());
        Collections.sort(keys);
        int m=keys.size();
        long[][] dp=new long[m+1][2];
        long rs=0;
        // System.out.println(keys);
//        System.out.println(mapCount);

        for(int i=1;i<=m;i++){
            long curPower= keys.get(i-1);
            //ignore:
            //  + curPower+1
            //  + curPower-1
            //  + curPower+2
            //  + curPower-2
            //==> -1/-2
            //
            long prev=-1;
            long prevOfPrev=-1;

            if(i>=2){
                prev=keys.get(i-2);
            }
            if(i>=3){
                prevOfPrev=keys.get(i-3);
            }
            long addVal=mapCount.get(curPower)*curPower;
            //- prev>=0 --> có số đằng trước
            if(i>=2&&prev==curPower-2){
                dp[i][1]=Math.max(dp[i-2][0], dp[i-2][1])+addVal;
            }else if(i>=3&&prevOfPrev==curPower-2){
                dp[i][1]=Math.max(dp[i-3][0], dp[i-3][1])+addVal;
            }else if(i>=2&&prev==curPower-1){
                dp[i][1]=Math.max(dp[i-2][0], dp[i-2][1])+addVal;
            }else if(i>=1){
                dp[i][1]=Math.max(dp[i-1][0], dp[i-1][1])+addVal;
            }else {
                dp[i][1]=addVal;
            }
            System.out.println(dp[i][1]);
            dp[i][0]=rs;
            rs=Math.max(rs, Math.max(dp[i][1], dp[i][0]));
            //2,3,5
            //+ prev = cur - 2
            //  + ignore prevOfPrev
            //+ prev = cur - 1
            //  + Xét prevOfPrev ==> có = cur - 2 không
            //+ prevOfPrev = cur - 2
            //  ==> Trùng case trên
            //  + Cách nhau 1 unit nên không có case prevOfPrev =cur-1
        }
//        for (int i = 1; i <= m; i++) {
//            System.out.printf("%s, not choose:%s, choose: %s ", keys.get(i-1), dp[i][0], dp[i][1]);
//        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- A magician has various spells.
        //- You are given an array power, where each element represents the damage of a spell.
        //- Multiple spells can have the same damage value.
        //- It is a known fact that if a magician decides to cast a spell with a damage of power[i],
        // they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
        //- Each spell can be cast only once.
        //* Return the maximum possible total damage that a magician can cast.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= power.length <= 10^5
        //1 <= power[i] <= 10^9
        //- Length khá lớn -> O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: power = [1,1,3,4]
        //Output: 6
        //Explanation:
        //The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.
        //
        //- Power >0
        //Input: power = [1,1,3,4]
        //count[1]=2
        //count[3]=1
        //count[4]=1
        //
        //- Max power có thể cast
        //- 3,5
        //  + cast(3) ==> Lost 1,2,4,(5)
        //  + cast(5) ==> Lost (3),4,6,7
        //- Chọn giữa 3 và 5 ntn?
        //
        //- DP được không?
        //- [i+- 1/2] ==> tính theo dp được không
        //Ex:
        //power = [1,1,3,4]
        //val = [1,3,4]
        //count=[2,1,1]
        //dp[i]:
        //  - Max nhất nếu cast tại (i)
        //dp[0]=2
        //dp[1]=3
        //dp[2]=6
        //
        //[1,6,7]
        //[1,2,1]
//        int[] power = {1,1,3,4};
//        int[] power = {7,1,6,6};
        int[] power = {5,9,2,10,2,7,10,9,3,8};
        //{2=2, 3=1, 5=1, 7=1, 8=1, 9=2, 10=2}
        System.out.println(maximumTotalDamage(power));
    }
}
