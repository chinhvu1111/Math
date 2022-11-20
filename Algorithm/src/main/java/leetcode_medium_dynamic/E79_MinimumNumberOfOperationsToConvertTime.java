package leetcode_medium_dynamic;

public class E79_MinimumNumberOfOperationsToConvertTime {

//    public static String addTime(String s, String addingTime){
//        String[] times =s.split(":");
//        int hour= Integer.parseInt(times[0]);
//        int minutes= Integer.parseInt(times[1]);
//        int addingTimeMinutes= Integer.parseInt(addingTime);
//
//        if(minutes<addingTimeMinutes){
//            addingTimeMinutes-=minutes;
//            minutes=60-addingTimeMinutes;
//        }else{
//            minutes=minutes-addingTimeMinutes;
//        }
//        if(hour==0){
//            hour=23;
//        }else{
//            hour--;
//        }
//        return hour+":"+minutes;
//    }

    public static int rangeTimeMinutes(String startTime, String endTime){
        String[] timesStart =startTime.split(":");
        int hourStart= Integer.parseInt(timesStart[0]);
        int minutesStart= Integer.parseInt(timesStart[1]);

        String[] timesEnd =endTime.split(":");
        int hourEnd= Integer.parseInt(timesEnd[0]);
        int minutesEnd= Integer.parseInt(timesEnd[1]);

        int rangeTime=(hourEnd-hourStart)*60+minutesEnd-minutesStart;
        return rangeTime;
    }

    public static int convertTime(String current, String correct) {
        int rangeTime=rangeTimeMinutes(current, correct);
        int[] timeRange=new int[]{1, 5, 15, 60};
        int[] dp=new int[rangeTime+1];
        int m=timeRange.length;

        for(int i=1;i<=rangeTime;i++){
            int minValue=Integer.MAX_VALUE;

            for(int j=0;j<m;j++){
                if(i<timeRange[j]){
                    break;
                }
                if(i>=timeRange[j]&&dp[j]!=Integer.MAX_VALUE){
                    minValue=Math.min(minValue, dp[i-timeRange[j]]+1);
                }
            }
            dp[i]=minValue;
        }

        return dp[rangeTime];
    }

    public static void main(String[] args) {
        String current="02:30";
        String correct = "04:35";

        System.out.println(convertTime(current, correct));
        //
        //** Đề bài
        //- Bài này nói về việc move giữa currentTime và correct time có bao nhiêu cách (Cộng thời gian lên)
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1, Bài này ta chỉ quan tâm đến khoảng cách minutes giữa current và correct
        //--> Sau đó dùng phương pháp dynamic programming để tính số cách.
        //
    }
}
