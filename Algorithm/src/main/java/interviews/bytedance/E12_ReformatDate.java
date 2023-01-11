package interviews.bytedance;

import java.util.HashMap;

public class E12_ReformatDate {

    public static String reformatDate(String date) {
        String[] months=new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        HashMap<String, Integer> numberToMonth=new HashMap<>();

        for(int i=0;i<months.length;i++){
            numberToMonth.put(months[i], i+1);
        }
        String[] dtS=date.split(" ");
        int day= Integer.parseInt(dtS[0].replaceAll("st|nd|rd|th", ""));
        int month= numberToMonth.get(dtS[1]);
        int year= Integer.parseInt(dtS[2]);
        return year+"-"+getNumber(month)+"-"+ getNumber(day);
    }

    static HashMap<String, Integer> cacheMonths=new HashMap<>();

    public static String reformatDateRefactor(String date) {
        String[] months=new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        if(cacheMonths.size()==0){
            for(int i=0;i<months.length;i++){
                cacheMonths.put(months[i], i+1);
            }
        }

        String[] dtS=date.split(" ");
        int day= Integer.parseInt(dtS[0].substring(0, dtS[0].length()-2));
        String monthStr= dtS[1];
        int year= Integer.parseInt(dtS[2]);
        String finalMonth=getNumber(cacheMonths.get(monthStr));

        return year+"-"+finalMonth+"-"+ getNumber(day);
    }

    public static String getNumber(int day){
        if(day<=9){
            return "0"+day;
        }
        return String.valueOf(day);
    }

    public static void main(String[] args) {
//        String s=reformatDate("20th Oct 2052");
        String s1=reformatDateRefactor("20th Oct 2052");
        System.out.println(s1);
        //#Reference:
        //1390. Four Divisors
        //1773. Count Items Matching a Rule
        //1307. Verbal Arithmetic Puzzle
        //157. Read N Characters Given Read4
    }
}
