package E2_design;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class E9_DesignLogStorageSystem {

    public static class LogSystem {

        public TreeMap<String, HashSet<Integer>> value;

        public LogSystem() {
            value=new TreeMap<>();
        }

        public void put(int id, String timestamp) {
            HashSet<Integer> oldIds = value.computeIfAbsent(timestamp, k -> new HashSet<>());
            oldIds.add(id);
        }

        public Date getDateFromString(String dtStr){
            DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
            Date dt;
            try {
                dt=df.parse(dtStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return dt;
        }

        public static String getStrFromDate(Date dt){
            DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
            return df.format(dt);
        }

        public Date truncate(Date dt, String granularity){
            Calendar cal = Calendar.getInstance(); // locale-specific
            cal.setTime(dt);
            if(granularity.equals("Year")){
                cal.set(Calendar.MONTH, 0);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
            }else if(granularity.equals("Month")){
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
            }else if(granularity.equals("Day")){
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
            }else if(granularity.equals("Hour")){
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
            }else if(granularity.equals("Minute")){
                cal.set(Calendar.SECOND, 0);
            }
            return cal.getTime();
        }

        public static Date add(Date dt, String granularity){
            Calendar cal = Calendar.getInstance(); // locale-specific
            cal.setTime(dt);
            if(granularity.equals("Year")){
                cal.add(Calendar.YEAR, 1);
            }else if(granularity.equals("Month")){
                cal.add(Calendar.MONTH, 1);
            }else if(granularity.equals("Day")){
                cal.add(Calendar.DATE, 1);
            }else if(granularity.equals("Hour")){
                cal.add(Calendar.HOUR, 1);
            }else if(granularity.equals("Minute")){
                cal.add(Calendar.MINUTE, 1);
            }else{
                cal.add(Calendar.SECOND, 1);
            }
            return cal.getTime();
        }

        public List<Integer> retrieve(String start, String end, String granularity) {
            Date startDt=getDateFromString(start);
            startDt=truncate(startDt, granularity);
            String startStr=getStrFromDate(startDt);
            SortedMap<String, HashSet<Integer>> navigator = value.tailMap(startStr);
            Date endDt = getDateFromString(end);
            endDt=truncate(endDt, granularity);
            endDt=add(endDt, granularity);
            String endDtStr=getStrFromDate(endDt);
            HashSet<Integer> keys=new HashSet<>();

            for(Map.Entry<String, HashSet<Integer>> e: navigator.entrySet()){
                if(e.getKey().compareTo(endDtStr)>=0){
                    break;
                }
                keys.addAll(e.getValue());
            }
            return new ArrayList<>(keys);
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given several logs, where each log contains a unique ID and timestamp.
        // Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59.
        // All domains are zero-padded decimal numbers.
        //
        //Implement the LogSystem class:
        //LogSystem() Initializes the LogSystem object.
        //+ void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
        //+ int[] retrieve(string start, string end, string granularity)
        //* Returns the IDs of the logs whose timestamps are (within the range) from (start to end) inclusive.
        // start and end all have the same format as timestamp, and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.).
        // For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day"
        // means that we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017,
        // and the (Hour, Minute, and Second for each log entry can be ignored).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= id <= 500
        //2000 <= Year <= 2017
        //1 <= Month <= 12
        //1 <= Day <= 31
        //0 <= Hour <= 23
        //0 <= Minute, Second <= 59
        //granularity is one of the values ["Year", "Month", "Day", "Hour", "Minute", "Second"].
        //At most 500 calls will be made to put and retrieve.
        //
        //- Brainstorm
        //Ex
        //Input
        //["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
        //[[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"],
        // ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"],
        // ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
        //Output
        //[null, null, null, null, [3, 2, 1], [2, 1]]
        //
        //- Ở đây phần quan trọng là retrieve method:
        //+ Map (time range) to unique
        //- granularity: Mức độ chi tiết đến đâu
        //- Ta sẽ dùng sortSet, map từ String --> Set of unique key
        //- granularity : Mức độ detail ==> Ta chỉ cần convert ra theo mức độ là được
        //Ex:
        //- year
        //start = ("2017:01:01:23:00:00", "year") => 2017:00:00:00:00:00
        //end = ("2018:01:01:23:00:00", "year") => truncate + 1 => 2019:00:00:00:00:00
        //- month
        //start = ("2017:01:01:23:00:00", "year") => 2017:00:00:00:00:00
        //end = ("2018:01:01:23:00:00", "year") => truncate + 1 => 2019:00:00:00:00:00
        // Log 3 is not returned because Jan. 1, 2016 00:00:00 comes before the start of the range.
        //logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
        LogSystem logSystem=new LogSystem();
        logSystem.put(1,"2017:01:01:23:59:59");
        logSystem.put(2,"2017:01:01:22:59:59");
        logSystem.put(3,"2016:01:01:00:00:00");
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"));
        //#Reference:
        //2953. Count Complete Substrings
        //842. Split Array into Fibonacci Sequence
        //1670. Design Front Middle Back Queue
    }
}
