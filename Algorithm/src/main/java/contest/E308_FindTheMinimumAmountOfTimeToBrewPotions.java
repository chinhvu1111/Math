package contest;

import java.util.ArrayList;
import java.util.List;

public class E308_FindTheMinimumAmountOfTimeToBrewPotions {

    public static boolean isValid(List<long[]> curOrder, List<long[]> prevOrder){
//        0	0	5	30	40	60
//        1	52	53	58	60	64
        //
        int n=curOrder.size();
        for (int i = 0; i < n; i++) {
            //x, y
            //x1, y1
            //x ------ y
            //    x1-------y1
            //
//            long x=curOrder.get(i)[0];
            long y=prevOrder.get(i)[1];
            long x1=curOrder.get(i)[0];
//            long y1=prevOrder.get(i)[1];
            if(x1<y){
                return false;
            }
        }
        return true;
    }

    public static long minTime(int[] skill, int[] mana) {
        long rs=0;
        int n=mana.length;
        int m=skill.length;
        List<long[]> prevOrder=new ArrayList<>();
        long start=0;
//        System.out.println(skill.length);
//        System.out.println(mana.length);

        for(int i=0;i<n;i++){
            if(prevOrder.isEmpty()){
                prevOrder.add(new long[]{0L, (long) mana[i] *skill[0]});
                rs=Math.max(rs, prevOrder.get(prevOrder.size()-1)[1]);
                start=Math.max(start, prevOrder.get(prevOrder.size()-1)[1]);
                for (int j = 1; j < m; j++) {
                    prevOrder.add(new long[]{prevOrder.get(j-1)[1], prevOrder.get(j-1)[1]+(long) mana[i] *skill[j]});
                    rs=Math.max(rs, prevOrder.get(prevOrder.size()-1)[1]);
                }
                continue;
            }
            long low=start, high=rs;
            long curRs=-1;
            List<long[]> curPos=new ArrayList<>();
            while(low<=high){
                long mid=low+(high-low)/2;
                List<long[]> curOrder=new ArrayList<>();
                boolean isValid=true;
                curOrder.add(new long[]{mid, mid+(long) mana[i] *skill[0]});
                long y=prevOrder.get(0)[1];
                long x1=curOrder.get(0)[0];
                if(x1<y){
                    isValid=false;

                }
                for (int j = 1; j < m; j++) {
                    curOrder.add(new long[]{curOrder.get(j-1)[1], curOrder.get(j-1)[1]+(long) mana[i] *skill[j]});
                    y=prevOrder.get(j)[1];
                    x1=curOrder.get(j)[0];
//            long y1=prevOrder.get(i)[1];
                    if(x1<y){
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
                    System.out.println(mid);
                    curPos=curOrder;
                    curRs=mid;
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            if(curRs!=-1){
                prevOrder=curPos;
                rs=Math.max(rs, curPos.get(curPos.size()-1)[1]);
            }
            start=Math.max(start, curRs);
        }
        return rs;
    }

    public static long minTimeRefactor(int[] skill, int[] mana) {
        long rs=0;
        int n=mana.length;
        int m=skill.length;
        //Space: O(m)
        long[][] prevOrder=new long[m][2];
//        long[][] curOrder=new long[m][2];
//        long[][] curPos=null;
        long start=0;
//        System.out.println(skill.length);
//        System.out.println(mana.length);

        //Time: O(n*m*log(max))
        for(int i=0;i<n;i++){
            if(i==0){
                prevOrder[0][0]=0;
                prevOrder[0][1]=(long) mana[i] *skill[0];
                rs=Math.max(rs, prevOrder[0][1]);
                start=Math.max(start, prevOrder[0][1]);
                for (int j = 1; j < m; j++) {
//                    prevOrder.add(new long[]{prevOrder.get(j-1)[1], prevOrder.get(j-1)[1]+(long) mana[i] *skill[j]});
                    prevOrder[j][0]=prevOrder[j-1][1];
                    prevOrder[j][1]=prevOrder[j-1][1]+(long) mana[i] *skill[j];
                    rs=Math.max(rs, prevOrder[j][1]);
                }
                continue;
            }
            long low=start, high=rs;
            long curRs=-1;

            while(low<=high){
                long mid=low+(high-low)/2;
                boolean isValid=true;
                long x1=mid;
                long y1=mid+(long) mana[i] *skill[0];
                long y=prevOrder[0][1];

                if(x1<y){
                    isValid=false;
                }
                for (int j = 1; j < m; j++) {
//                    curOrder[j][0]= curOrder[j-1][1];
//                    curOrder[j][1]= curOrder[j-1][1]+(long) mana[i] *skill[j];
                    x1= y1;
                    y1= x1+(long) mana[i] *skill[j];
                    y=prevOrder[j][1];
//            long y1=prevOrder.get(i)[1];
                    if(x1<y){
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
//                    System.out.println(mid);
                    curRs=mid;
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            if(curRs!=-1){
                long init=curRs;
                for (int j = 0; j < m; j++) {
                    prevOrder[j][0]=init;
                    prevOrder[j][1]=init+ (long) mana[i] *skill[j];
                    init=prevOrder[j][1];
                }
                rs=Math.max(rs, prevOrder[m-1][1]);
            }
            start=Math.max(start, curRs+1);
        }
        return rs;
    }

    static boolean isValid(int[] skill, int[] mana, long[] prevPotionEndTimes, int currPotionIdx, long startTime)
    {
        long endTime = startTime;
        for (int i = 0; i < skill.length; i++)
        {
            if (endTime < prevPotionEndTimes[i])
                return false;
            endTime += ((long)skill[i] * mana[currPotionIdx]);
        }
        return true;
    }
    static long getStartTime(int[] skill, int[] mana, int currPotionIdx, long lowTime, long[] prevPotionEndTimes)
    {
        int wizardCount = skill.length;
        long highTime = prevPotionEndTimes[wizardCount - 1];
        long ans = highTime;
        //============================================================================================
        while(lowTime <= highTime)
        {
            long midTime = lowTime + (highTime - lowTime) / 2;
            if (isValid(skill, mana, prevPotionEndTimes, currPotionIdx, midTime))
            {
                ans = midTime;
                highTime = midTime - 1;
            }
            else
                lowTime = midTime + 1;
        }
        //============================================================================================
        return ans;
    }
    public static long minTimeRefer(int[] skill, int[] mana)
    {
        int wizardCount = skill.length, potionCount = mana.length;
        long prevStartTime = 0; //startTime of brewing "previous" Potion

        //potionEndTimes[i] denotes end time at which ith wizard finishes brewing "current" Potion
        long[] potionEndTimes = new long[wizardCount];
        //===================================================================================================
        for (int i = 0; i < potionCount; i++)
        {
            long startTime = (i == 0)? 0 : getStartTime(skill, mana, i, prevStartTime + 1, potionEndTimes);

            long endTime = startTime;
            for (int j = 0; j < wizardCount; j++)
            {
                endTime += ((long)(skill[j]) * (mana[i]));
                potionEndTimes[j] = endTime;
            }

            prevStartTime = startTime;
        }
        //======================================================================================================
        return potionEndTimes[wizardCount - 1];

    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two integer arrays), (skill and mana), of length n and m, respectively.
        //- In a laboratory, (n wizards) must brew (m potions) in order.
        //- (Each potion) has (a mana capacity mana[j]) and must pass through (all the wizards) (sequentially) to be brewed properly.
        //- The time taken by (the ith wizard) on (the jth potion) is
        //  + time[ij] = skill[i] * mana[j].
        //- Since the brewing process is delicate, (a potion) must be passed to (the next wizard) immediately after
        // (the current wizard) completes their work.
        //- This means the timing must be ("synchronized") so that (each wizard) begins working on (a potion) ("exactly") when it arrives.
        //* Return (the minimum amount of time) required for the potions to be (brewed properly).
        //
        //Example 1:
        //Input: skill = [1,5,2,4], mana = [5,1,4,2]
        //Output: 110
        //
        //Explanation:
        //
        //Potion Number	Start time	Wizard 0 done by	Wizard 1 done by	Wizard 2 done by	Wizard 3 done by
        //0	0	5	30	40	60
        //1	52	53	58	60	64
        //2	54	58	78	86	102
        //3	86	88	98	102	110
        //- As an example for why (wizard 0) cannot start working on (the 1st potion) before time t = 52,
        // consider the case where the wizards started preparing (the 1st potion) at time t = 50.
        //- At time t = 58, wizard 2 is done with (the 1st potion), but wizard 3 will still be working on (the 0th potion) till time t = 60.
        //* (Each wizard) do (only 1) task (at the time)
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == skill.length
        //m == mana.length
        //1 <= n, m <= 5000
        //1 <= mana[i], skill[i] <= 5000
        //  + Time: O(n^2)
        //  + Result is long ==> ...
        //
        //- Brainstorm
        //- Min amount of time to do that
        //==> Start=0
        //
        //Example 1:
        //
        //Input: skill = [1,5,2,4], mana = [5,1,4,2]
        //
        //Output: 110
        //
        //Explanation:
        //
        //Potion Number	Start time	Wizard 0 done by	Wizard 1 done by	Wizard 2 done by	Wizard 3 done by
        //0	0	5	30	40	60
        //1	52	53	58	60	64
        //2	54	58	78	86	102
        //3	86	88	98	102	110
        //- As an example for why wizard 0 cannot start working on the 1st potion before time t = 52,
        // consider the case where the wizards started preparing the 1st potion at time t = 50.
        //- At time t = 58, wizard 2 is done with the 1st potion, but wizard 3 will still be working on the 0th potion till time t = 60.
        //
        //- How to find 52 number?
        //
        //Ex:
        //1----3 4----6
        //   2-----5
        //=> Overlapping
        //==> Increase to (6)
        //
        //- Shift to right ==> To find the valid
        //  + Find (the min position)
        //  ==> Binary search
        //
        //- low = 0, max= previous_max
        //
        //1.1, Cases
        //
        int[] skill = {1,5,2,4}, mana = {5,1,4,2};
//        System.out.println(minTime(skill, mana));
        System.out.println();
        System.out.println(minTimeRefactor(skill, mana));
//        System.out.println(minTimeRefer(skill, mana));
        //
        //1.2, Optimization
        //- If we create two array and use this array as the swap case
        //  + We will get the TLE issue
        //- We use only one array
        //  + Use this array to check
        //  ==> Use (accumulator sum var) to check
        //==> Faster
        //
        //1.3, Complexity
        //- Space: O(m)
        //- Time: O(n*m*log(max))
        //
    }
}
