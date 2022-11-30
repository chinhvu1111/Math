package contest;

public class E10_MinimumPenaltyForAShop {

    public static int bestClosingTime(String customers) {
        int n=customers.length();
        int result=Integer.MAX_VALUE;
        int index=0;
        int countPenaltyHours=0;
        int[] startPenalty=new int[n];
        int[] endPenalty=new int[n];

        for(int i=0;i<n;i++){
            if('N'==customers.charAt(i)){
                countPenaltyHours++;
            }
            startPenalty[i]=countPenaltyHours;
        }
        countPenaltyHours=0;
        for(int i=n-1;i>=0;i--){
            if('Y'==customers.charAt(i)){
                countPenaltyHours++;
            }
            endPenalty[i]=countPenaltyHours;
        }
        if(n!=0){
            result=endPenalty[0];
        }
        for(int i=0;i<n-1;i++){
            if(result>startPenalty[i]+endPenalty[i+1]){
                index=i+1;
                result=startPenalty[i]+endPenalty[i+1];
            }
        }
        if(customers.charAt(n-1)=='Y'&&result>startPenalty[n-1]){
            index=n;
        }
        return index;
    }

    public static void main(String[] args) {
//        String customers="YYNY";
//        String customers="NNNNN";
        //321
//        String customers="YYYY";
//        String customers="Y";
        String customers="N";
        System.out.println(bestClosingTime(customers));
    }
}
