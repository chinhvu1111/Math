package E1_daily;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class E319_DesignHitCounter {

    static class HitCounter {

        public TreeSet<Integer> timestamp;
        public HashMap<Integer, Integer> mapCount;

        public HitCounter() {
            timestamp=new TreeSet<>(Collections.reverseOrder());
            mapCount=new HashMap<>();
        }

        public void hit(int timestamp) {
            this.timestamp.add(timestamp);
            mapCount.put(timestamp, mapCount.getOrDefault(timestamp, 0)+1);
        }

        public int getHits(int timestamp) {
            int prev = timestamp-299;
            Iterator<Integer> iterator = this.timestamp.iterator();
            int rs=0;
            while (iterator.hasNext()){
                int curTimestamp = iterator.next();
                if(curTimestamp<prev){
                    break;
                }
                rs+=mapCount.get(curTimestamp);
            }
            return rs;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a hit counter which counts (the number of hits) received in (the past 5 minutes)
        // (i.e., the past 300 seconds).
        //- Your system should accept (a timestamp parameter) (in seconds granularity),
        // and you may assume that calls are being made to the system (in chronological order)
        // (i.e., timestamp is monotonically increasing).
        //- Several hits may arrive roughly (at the same time).
        //- Implement the HitCounter class:
        //  + HitCounter() Initializes the object of the hit counter system.
        //  + void hit(int timestamp) Records a hit that happened at timestamp (in seconds).
        //  Several hits may happen at the same timestamp.
        //  + int getHits(int timestamp) Returns the number of hits in the past 5 minutes
        //  (from timestamp) (i.e., the past 300 seconds).
        //
        //Example 1:
        //
        //Input
        //["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
        //[[], [1], [2], [3], [4], [300], [300], [301]]
        //Output
        //[null, null, null, null, 3, null, 4, 3]
        //
        //Explanation
        //HitCounter hitCounter = new HitCounter();
        //hitCounter.hit(1);       // hit at timestamp 1.
        //hitCounter.hit(2);       // hit at timestamp 2.
        //hitCounter.hit(3);       // hit at timestamp 3.
        //hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
        //hitCounter.hit(300);     // hit at timestamp 300.
        //hitCounter.getHits(300); // get hits at timestamp 300, return 4.
        //hitCounter.getHits(301); // get hits at timestamp 301, return 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= timestamp <= 2 * 10^9
        //All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
        //At most 300 calls will be made to hit and getHits.
        //
        //* Brainstorm:
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
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));
        hitCounter.hit(300);     // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.

    }
}
