package contest;

import java.util.*;

public class E316_ImplementRouter_wrong {

    static class Router {
        public static Queue<int[]> packets;
        public static HashMap<Integer, TreeSet<int[]>> cache;
        public static HashSet<String> duplicatedChecker;
        public static int mem;

        public Router(int memoryLimit) {
            packets=new LinkedList<>();
            cache=new HashMap<>();
            mem=memoryLimit;
            duplicatedChecker=new HashSet<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            String key = source+"_"+destination+"_"+timestamp;
            if(duplicatedChecker.contains(key)){
                return false;
            }
            if(packets.size()==mem&&!packets.isEmpty()){
                int[] removedPacket = packets.poll();
                String removedKey = removedPacket[0]+"_"+removedPacket[1]+"_"+removedPacket[2];
                duplicatedChecker.remove(removedKey);
                TreeSet<int[]> curSet = cache.getOrDefault(removedPacket[1], new TreeSet<>((o1, o2) -> {
                    if (o1[2] != o2[2]) {
                        return o1[2] - o2[2];
                    }
                    if (o1[1] != o2[1]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                }));
                curSet.remove(removedPacket);
                cache.put(removedPacket[1], curSet);
            }
            String newKey = source+"_"+destination+"_"+timestamp;
            duplicatedChecker.add(newKey);
            int[] newPacket = new int[]{source, destination, timestamp};
            packets.add(newPacket);
            TreeSet<int[]> curSet = cache.getOrDefault(destination, new TreeSet<>((o1, o2) -> {
                if (o1[2] != o2[2]) {
                    return o1[2] - o2[2];
                }
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }));
            curSet.add(newPacket);
            cache.put(destination, curSet);
            return true;
        }

        public int[] forwardPacket() {
            if(packets.isEmpty()){
                return new int[]{};
            }
            int[] removedPacket = packets.poll();
            String removedKey = removedPacket[0]+"_"+removedPacket[1]+"_"+removedPacket[2];
            duplicatedChecker.remove(removedKey);
            TreeSet<int[]> curSet = cache.getOrDefault(removedPacket[1], new TreeSet<>((o1, o2) -> {
                if (o1[2] != o2[2]) {
                    return o1[2] - o2[2];
                }
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }));
            curSet.remove(removedPacket);
            cache.put(removedPacket[1], curSet);
            return removedPacket;
        }

        public int getCount(int destination, int startTime, int endTime) {
            if(!cache.containsKey(destination)){
                return 0;
            }
            TreeSet<int[]> curSet = cache.get(destination);
//            int count=0;
//            Iterator<int[]> iterator = curSet.iterator();
//            while (iterator.hasNext()){
//                int[] curNode = iterator.next();
//                if(curNode[2]>endTime){
//                    break;
//                }
//                if(curNode[2]>=startTime){
//                    count++;
//                }
//            }
//            return count;
            NavigableSet<int[]> rangeSubset =
                    curSet.subSet(
                            new int[]{-1, -1, startTime}, true,
                            new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, endTime}, true);
            //This solution is still TLE
            //==> We have another way as (Segment tree)

            // Count the number of elements in the range [x, y]
            return rangeSubset.size();
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a data structure that can efficiently manage data packets in a network router.
        // Each data packet consists of the following attributes:
        //  + source: A unique identifier for the machine that generated the packet.
        //  + destination: A unique identifier for the target machine.
        //  + timestamp: The time at which the packet arrived at the router.
        //
        //- Implement the Router class:
        //Router(int memoryLimit): Initializes the Router object with a fixed memory limit.
        //  + memoryLimit is the maximum number of packets the router can store at any given time.
        //  + If adding (a new packet) would exceed this limit, the (oldest packet) must be removed to free up space.
        //- bool addPacket(int source, int destination, int timestamp):
        // Adds a packet with the given attributes to the router.
        //  + A packet is considered a duplicate if another packet with the same source, destination,
        //  and timestamp already exists in the router.
        //  + Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
        //- int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.
        //  + Remove the packet from storage.
        //  + Return the packet as an array [source, destination, timestamp].
        //  + If there are no packets to forward, return an empty array.
        //- int getCount(int destination, int startTime, int endTime):
        //  + Returns the number of packets currently stored in the router (i.e., not yet forwarded)
        //  that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
        //* Note that queries for addPacket will be made in increasing order of timestamp.
        //
        //Example 1:
        //
        //Input:
        //["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]
        //
        //[[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]
        //
        //Output:
        //[null, true, true, false, true, true, [2, 5, 90], true, 1]
        //
        //Explanation
        //
        //Router router = new Router(3); // Initialize Router with memoryLimit of 3.
        //router.addPacket(1, 4, 90); // Packet is added. Return True.
        //router.addPacket(2, 5, 90); // Packet is added. Return True.
        //router.addPacket(1, 4, 90); // This is a duplicate packet. Return False.
        //router.addPacket(3, 5, 95); // Packet is added. Return True
        //router.addPacket(4, 5, 105); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
        //router.forwardPacket(); // Return [2, 5, 90] and remove it from router.
        //router.addPacket(5, 2, 110); // Packet is added. Return True.
        //router.getCount(5, 100, 110); // The only packet with destination 5
        // and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= memoryLimit <= 10^5
        //1 <= source, destination <= 2 * 10^5
        //1 <= timestamp <= 10^9
        //1 <= startTime <= endTime <= 10^9
        //At most 10^5 calls will be made to addPacket, forwardPacket, and getCount methods altogether.
        //queries for (addPacket) will be made in (increasing order of ["timestamp"]).
        //  + Time: O(n*k)
        //
        //- Brainstorm
        //- Queue as order
        //- TreeSet ==> Check duplicate
        //- Query:
        //  + HashMap<Dest, List<Int>> ==> Binary search
        //
//        Router router = new Router(3); // Initialize Router with memoryLimit of 3.
//        System.out.println(router.addPacket(1, 4, 90)); // Packet is added. Return True.
//        System.out.println(router.addPacket(2, 5, 90)); // Packet is added. Return True.
//        System.out.println(router.addPacket(1, 4, 90)); // This is a duplicate packet. Return False.
//        System.out.println(router.addPacket(3, 5, 95)); // Packet is added. Return True
//        System.out.println(router.addPacket(4, 5, 105)); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
//        int[] removedNode = router.forwardPacket(); // Return [2, 5, 90] and remove it from router.
//        System.out.printf("%s %s %s\n", removedNode[0], removedNode[1], removedNode[2]);
//        System.out.println(router.addPacket(5, 2, 110)); // Packet is added. Return True.
//        System.out.println(router.getCount(5, 100, 110)); // The only packet with destination 5 and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
//        Router router = new Router(2); // Initialize Router with memoryLimit of 3.
//        System.out.println(router.addPacket(4, 5, 1));
//        System.out.println(router.getCount(5, 1, 1));
        //["Router","addPacket","getCount"]
        //[[4],[4,5,1],[5,1,1]]
        //Output:
        //[null,true,0]
        //Expected:
        //[null,true,1]
        //
        //["Router","addPacket","addPacket","addPacket","getCount","getCount","getCount","getCount"]
        //[[2],[5,3,1],[3,1,1],[1,2,2],[1,1,2],[2,2,2],[1,2,2],[2,1,2]]
        //Output:
        //[null,true,true,true,0,1,0,1]
        //Expected:
        //[null,true,true,true,1,1,0,1]
        //
        Router router = new Router(2); // Initialize Router with memoryLimit of 3.
        System.out.println(router.addPacket(5, 3, 1));
        System.out.println(router.addPacket(3, 1, 1));
        System.out.println(router.addPacket(1, 2, 2));
        System.out.println(router.getCount(1, 1, 2));
        System.out.println(router.getCount(2, 2, 2));
    }
}
