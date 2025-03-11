package contest.mock_meta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class E1 {

    public static int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = null;
        try {
            curDate = formatter.parse(date1);
            Date curDate1 = formatter.parse(date2);
            long differenceInMillis = curDate.getTime() - curDate1.getTime();
            long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);
            return (int) Math.abs(differenceInDays);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //
    }
}
