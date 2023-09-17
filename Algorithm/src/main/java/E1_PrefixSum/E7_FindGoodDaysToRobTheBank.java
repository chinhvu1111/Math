package E1_PrefixSum;

import java.util.ArrayList;
import java.util.List;

public class E7_FindGoodDaysToRobTheBank {

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n=security.length;
        int[] increase =new int[n];
        int count=1;

        for(int i=1;i<n;i++){
            if(security[i]<=security[i-1]){
                count++;
                increase[i]=count;
            }else{
                count=1;
                increase[i]=count;
            }
        }
        count=1;
        List<Integer> rs=new ArrayList<>();

        if(time==0){
            rs.add(0);
            if(n!=1){
                rs.add(n-1);
            }
        }
        for(int i=n-2;i>=0;i--){
            if(security[i]<=security[i+1]){
                count++;
            }else{
                count=1;
            }
//            System.out.printf("Index=%s, %s %s\n", i, decrease[i], increase[i]);
            if(count>=time+1&&increase[i]>=time+1){
                rs.add(i);
            }
        }

        return rs;
    }

    public static List<Integer> goodDaysToRobBankRefactor(int[] security, int time) {
        int n=security.length;
        int[] increase =new int[n];

        for(int i=1;i<n-time;i++){
            if(security[i]<=security[i-1]){
                increase[i]=increase[i-1]+1;
            }
        }
        List<Integer> rs=new ArrayList<>();
        int count=0;
        if (time == 0) {
            if (n == 1) {
                rs.add(0);
            }
            if (n != 1) {
                rs.add(n - 1);
            }
        }

        for(int i=n-2;i>=time;i--){
            if(security[i]<=security[i+1]){
                count++;
            }else{
                count=0;
            }
//            System.out.printf("Index=%s, %s %s\n", i, decrease[i], increase[i]);
            if(count>=time&&increase[i]>=time){
                rs.add(i);
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- The rob want to rob the bank
        //+ You are given a 0-indexed integer array security, where security[i] is the number of guards on duty on the ith day.
        //The ith day is a good day to rob the bank if:
        //+ There are at least time days before and after the ith day,
        //+ The number of guards at the bank for the time days before i are non-increasing, and
        //+ The number of guards at the bank for the time days after i are non-decreasing.
        //- security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time]
        //* return all days which are suitable for robbing
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //1 <= security.length <= 10^5
        //0 <= security[i], time <= 10^5
        //
        //- Brainstorm
        //Ex:
        //time=2
        //[5,3,(3),3,5,6,2]
        //5>3>=(3)<=3<=5
        //- Bài này dùng 2 array (decrease - left) và (decrease - right)
        //[5,3,(3),3,5,6,2]
        // (decrease - left) a>=(b)<=c (decrease - right)
        //+ decrease - left: [1,2,3,4,1,1,2]
        //+ decrease - right: [1,5,4,3,2,1,1]
        //
        //1.1, Optimization
        //- Những thằng mà sát đầu i<=time hoặc n-i<=time
        //+ Ta có thể bỏ đi vì không thoả mãn
        //- Ta có thể refactor để code dễ hiểu hơn
        //+ Bỏ được những đoạn code đặc biệt liên quan đến specific case
        //E.G:
        //==========
        //if(time==0){
        //            if(n==1){
        //                rs.add(0);
        //            }
        //            if(n!=1){
        //                rs.add(n-1);
        //            }
        //        }
        //==========
        //- Tuy nhiên đoạn code này mình không bỏ đi được vì:
        //+ Mặc định mình viết chúng việc check result vào loop thứ 2
        //==> Nó sẽ bị miss case nếu không specific được case này
        //+ Mình 3 loops hoặc 2 loops + specific case code segment.
        //
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(n)
//        int[] security = {5,3,3,3,5,6,2};
//        int time = 2;
//        int[] security = {5,3,3,3,5,6,2};
//        int time = 1;
//        int[] security = {5,3,3,3,5,6,2};
//        int time = 0;
        int[] security = {1};
        int time = 0;
        System.out.println(goodDaysToRobBank(security, time));
        System.out.println(goodDaysToRobBankRefactor(security, time));
        //
        //#Reference:
        //665. Non-decreasing Array
        //1095. Find in Mountain Array
        //2420. Find All Good Indices
    }
}
