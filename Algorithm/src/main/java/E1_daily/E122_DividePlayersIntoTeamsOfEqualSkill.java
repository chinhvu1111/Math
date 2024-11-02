package E1_daily;

import java.util.HashMap;

public class E122_DividePlayersIntoTeamsOfEqualSkill {

    public static long dividePlayers(int[] skill) {
        int n=skill.length;
        long sum=0;

        for(int i=0;i<n;i++){
            sum+=skill[i];
        }
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int subSum= (int) (sum / (n / 2));
//        System.out.println(subSum);

        for(int e: skill){
            mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
        }
//        System.out.println(mapCount);
        long rs=0;

        for(int e: skill){
            Integer curCount = mapCount.get(e);
            if(curCount==null){
                continue;
            }
            Integer countRemainingVal = mapCount.get(subSum-e);
            if(countRemainingVal==null){
                continue;
            }
            if(e!=subSum-e){
                if(countRemainingVal==1){
                    mapCount.remove(subSum-e);
                }else{
                    mapCount.put(subSum-e, countRemainingVal-1);
                }
            }else if(countRemainingVal==1){
                return -1;
            }else{
                mapCount.put(subSum-e, countRemainingVal-1);
            }
            curCount = mapCount.get(e);
            if(curCount==1){
                mapCount.remove(e);
            }else{
                mapCount.put(e, curCount-1);
            }
//            System.out.println(mapCount);
            rs+= (long) e *(subSum-e);
        }
//        System.out.println(mapCount);
        return !mapCount.isEmpty()?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a positive integer array skill) of (even length n) where skill[i] denotes the skill of (the ith player).
        //- Divide the players into n / 2 teams of size 2 such that (the total skill of each team) is equal.
        //- (The chemistry of a team) is equal to ((the product) of the skills of the players) on that team.
        //* Return (the sum of the chemistry) of (all the teams),
        // or return -1 if there is (no way) to divide the players into teams
        // such that (the total skill of each team) is equal.
        //- Chia làm sao cho skill của mỗi team (2 người) là bằng nhau
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= skill.length <= 10^5
        //skill.length is even.
        //1 <= skill[i] <= 1000
        //  + n khá lớn ==> Time: O(n)
        //
        //- Brainstorm
        //- Tìm được sum của mỗi team = sum/(n/2)
        //- Dùng mapCount là được.
        //
        //1.1, Optimization
        //-
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] skill = {3,2,5,1,3,4};
        System.out.println(dividePlayers(skill));
        //#Reference:
        //453. Minimum Moves to Equal Array Elements
    }
}
