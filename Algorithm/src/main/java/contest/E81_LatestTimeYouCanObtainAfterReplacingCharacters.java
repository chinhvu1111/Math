package contest;

public class E81_LatestTimeYouCanObtainAfterReplacingCharacters {

    public static String findLatestTime(String s) {
        //00 --> 11
        //00 --> 59
        //
        //0? => 09
        //1? => 11
        String hourSegment = s.split(":")[0];
        String minuteSegment = s.split(":")[1];
        StringBuilder hourRs=new StringBuilder();
        StringBuilder minuteRs=new StringBuilder();

        if(hourSegment.charAt(0)=='?'){
            if(hourSegment.charAt(1)!='?'&&hourSegment.charAt(1)>='2'){
                hourRs.append('0');
            }else{
                hourRs.append('1');
            }
        }else{
            hourRs.append(hourSegment.charAt(0));
        }
        if(hourSegment.charAt(1)=='?'){
            if(hourSegment.charAt(0)=='0'){
                hourRs.append('9');
            }else{
                hourRs.append('1');
            }
        }else{
            hourRs.append(hourSegment.charAt(1));
        }
        if(minuteSegment.charAt(0)=='?'){
            minuteRs.append('5');
        }else{
            minuteRs.append(minuteSegment.charAt(0));
        }
        if(minuteSegment.charAt(1)=='?'){
            minuteRs.append('9');
        }else{
            minuteRs.append(minuteSegment.charAt(1));
        }

        return hourRs.append(":").append(minuteRs).toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s representing a 12-hour format time where some of the digits (possibly none) are replaced with a "?".
        //12-hour times are formatted as "HH:MM", where HH is between 00 and 11, and MM is between 00 and 59.
        // The earliest 12-hour time is 00:00, and the latest is 11:59.
        //You have to replace all the "?" characters in s with digits such that the time we obtain by
        // the resulting string is a valid 12-hour format time and is the latest possible.
        //* Return the resulting string.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        //
//        String s= "1?:?4";
//        String s= "1?:0?";
//        String s= "0?:0?";
//        String s= "?3:12";
//        String s= "??:1?";
        String s= "?1:?6";
        System.out.println(findLatestTime(s));
    }
}
