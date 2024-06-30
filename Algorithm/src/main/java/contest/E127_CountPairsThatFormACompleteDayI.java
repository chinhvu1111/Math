package contest;

public class E127_CountPairsThatFormACompleteDayI {

    public static int countCompleteDayPairs(int[] hours) {
        int n= hours.length;
        int rs=0;

        for (int i = 0; i < n; i++) {
            for(int j=i+1;j<n;j++){
                if((hours[i]+hours[j])%24==0){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] hours = {12,12,30,24,24};
        System.out.println(countCompleteDayPairs(hours));
    }
}
