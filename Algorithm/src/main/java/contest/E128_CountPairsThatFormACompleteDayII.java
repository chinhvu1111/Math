package contest;

import java.util.HashMap;

public class E128_CountPairsThatFormACompleteDayII {

    public static long countCompleteDayPairs(int[] hours) {
        long[] count=new long[25];
        int n=hours.length;

        for(int i=0;i<n;i++){
            count[hours[i]%24]++;
        }
        long rs=0;

        for(int i=0;i<24;i++){
            if(count[i]!=0){
                if(i==(24-i)%24){
                    rs+=count[i]*(count[i]-1)/2;
                    count[i]=0;
                }
                if(i!=(24-i)%24&&count[24-i]!=0){
                    rs+=count[i]*(count[24-i]);
                    count[i]=0;
                    count[24-i]=0;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an integer array hours representing times in hours,
        //* Return an integer denoting the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.
        //- A complete day is defined as a time duration that is an exact multiple of 24 hours.
        //For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= hours.length <= 5 * 10^5
        //1 <= hours[i] <= 10^9
        //+ Chỉ xử lý O(n)
        //+ hours[i] cũng khá lớn
        //
        //- Brainstorm
        //Ex:
        //hours = [12,12,30,24,24]
        //+ Dùng tính chất mod được không
        //+ 12%24 = 12
        //+ 12%24 = 12 => count[12] = 2
        //  + rs=1
        //  + rs = 2*(2-1)/2
        //12,12,12
        //+ count = 3: rs = 2+1 = 3
        //
        //+ 0,24,48
//        int[] hours = {12,12,30,24,24};
//        int[] hours = {72,48,24,3};
        int[] hours = {13, 11};
        //count[13]=1
        //count[11]=1
        //- Giả sử:
        //count[13]=1
        //count[11]=2
        //
        //hours = [12,12,30,24,24]
        //count[12]=2
        //count[6]=1
        //count[0]=2
        System.out.println(countCompleteDayPairs(hours));
    }
}
