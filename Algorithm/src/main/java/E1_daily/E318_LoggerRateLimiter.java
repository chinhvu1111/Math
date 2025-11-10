package E1_daily;

import java.util.HashMap;

public class E318_LoggerRateLimiter {

    static class Logger {

        public static HashMap<String, Integer> mapCount;

        public Logger() {
            mapCount = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
//            if(mapCount.containsKey(message)){
//                if(timestamp>=mapCount.get(message)){
//                    mapCount.put(message, timestamp+10);
//                }else{
//                    return false;
//                }
//            }else{
//                mapCount.put(message, timestamp+10);
//            }
//            return true;
            if (!mapCount.containsKey(message)) {
                mapCount.put(message, timestamp);
                return true;
            }

            Integer oldTimestamp = mapCount.get(message);
            if (timestamp - oldTimestamp >= 10) {
                mapCount.put(message, timestamp);
                return true;
            } else
                return false;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a logger system that receives (a stream of messages) along with their timestamps.
        //- Each unique message should only be printed at most every 10 seconds
        // (i.e. a message printed at timestamp t will prevent other identical messages
        // from being printed until timestamp t + 10).
        //- All messages will come in chronological order. Several messages may arrive at the same timestamp.
        //- Implement the Logger class:
        //  + Logger() Initializes the logger object.
        //  + bool shouldPrintMessage(int timestamp, string message)
        //* Returns true if the message should be printed in the given timestamp, otherwise returns false.
        //
        //Example 1:
        //
        //Input
        //["Logger", "shouldPrintMessage", "shouldPrintMessage",
        // "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
        //[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
        //Output
        //[null, true, true, false, false, false, true]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //0 <= timestamp <= 10^9
        //Every timestamp will be passed in non-decreasing order (chronological order).
        //1 <= message.length <= 30
        //At most 104 calls will be made to shouldPrintMessage.
        //
        //* Brainstorm:
        //
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n+n*log(e))
        //
    }
}
