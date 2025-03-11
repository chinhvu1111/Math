package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E259_DesignMostRecentlyUsedQueue_bucketing_classic {

    static class MRUQueue {

        private int bucketSize;
        private List<List<Integer>> data;
        private List<Integer> startingIndex;
        private int maxElement;

        public MRUQueue(int n) {
            this.bucketSize = (int) Math.sqrt(n);
            List<Integer> curList=new ArrayList<>();
            startingIndex=new ArrayList<>();
            data=new ArrayList<>();
            maxElement=n;

            //[1,2],[3,4]]
            int i=1;
            for(i=1;i<=n;i++){
                if(curList.isEmpty()){
                    startingIndex.add(i-1);
                }
                curList.add(i);
                if(curList.size()==bucketSize){
                    data.add(curList);
                    curList=new ArrayList<>();
                }
            }
            if(!curList.isEmpty()){
                data.add(curList);
//                startingIndex.add(i-1);
            }
//            System.out.println(data);
//            System.out.println(startingIndex);
        }

        public static int upperBound(List<Integer> startingIndex, int target){
            int low = 0;
            int high = startingIndex.size()-1;
            int rs=-1;

            while (low<=high){
                int mid=low+(high-low)/2;
//                System.out.println(mid);
                if(target>=startingIndex.get(mid)){
                    rs=mid;
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }
            return rs;
        }

        public int fetch(int k) {
            int bucketIndex = upperBound(startingIndex, k-1);
            System.out.println(startingIndex);
            System.out.println(data);
//            System.out.printf("val: %s, bucketIndex:%s\n", k, bucketIndex);
//            System.out.printf("pos: %s\n", k-startingIndex.get(bucketIndex)-1);
            int curElement = data.get(bucketIndex).get(k-startingIndex.get(bucketIndex)-1);
            //Remove cur element (O(sqrt(n))
            data.get(bucketIndex).remove(k-startingIndex.get(bucketIndex)-1);
            //Add new element
            //==> We need to move the data (Shuffle the data in spark context)
            //Update index for the starting index list
            //- Because we remove the bucket ==> only set and then removing
            for(int i=bucketIndex+1;i<startingIndex.size();i++){
                startingIndex.set(i, startingIndex.get(i)-1);
            }
            if(data.get(data.size()-1).size()>=bucketSize){
                data.add(new ArrayList<>());
                //Last element has index == n
                startingIndex.add(maxElement-1);
            }
            data.get(data.size()-1).add(curElement);
            if(data.get(bucketIndex).isEmpty()){
                data.remove(bucketIndex);
                startingIndex.remove(bucketIndex);
            }
            return curElement;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design (a queue-like data structure) that moves (the most recently used element) to (the end of the queue).
        //- Implement the MRUQueue class:
        //  + MRUQueue(int n) constructs the MRUQueue with n elements: [1,2,3,...,n].
        //  + int fetch(int k) moves the kth element (1-indexed) to the end of the queue and returns it.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 2000
        //1 <= k <= n
        //At most 2000 calls will be made to fetch.
        //
        //* Brainstorm:
        //arr = [1,3,4,5,10]
        //  + fetch(2):
        //      arr = [1,4,5,10,3]
        //  + fetch(4):
        //      arr = [1,4,5,3,10]
        //- For the linked list:
        //  + Fetch O(n) time
        //- We can use bucket characteristic to find (the specific element) faster
        //- We can use (multiple arrays) rather than (a single array)
        //Why sqrt(n) Buckets?
        //- Choosing as the bucket size comes from balancing two competing factors:
        //  + Minimizing search time: If the bucket size is too small, the number of buckets increases,
        //  and we'll end up spending more time locating the right bucket.
        //  If the bucket size is too large, the time spent searching within the bucket (to find thek-th element) becomes a bottleneck.
        //  + Efficient updates: Each time we modify a bucket, we want the update to be relatively fast.
        //  If buckets are too large, updates become expensive, but if they're too small, there are too many updates to handle.
        //
        //- n = size_bucket * bucket_number
        //  + n= sqrt(n) * sqrt(n)
        //- Problem:
        //  + Bucketing problem ==> How to extend the data
        //- This problem uses (the fixed size):
        /// ==> Can use this one
        //- [1,2,3,4],[5,10,6],[11,14,17]
        //  + k = 5
        //  + How to find (the specific bucket) including (kth item)?
        //      + data[k/sqrt(n)]
        //      ==> It does not work because we will (remove) the element from (the bucket)
        //      + In the end, there are (some empty buckets):
        //          + We need to (delete them) + (add new bucket)
        //- Store the start index of the element of each bucket:
        //  ==> Find (bucket index)
        //
        //- Start list:
        //  + index = i-1
        //- Remove dựa trên rule ở dưới
        //  ==> Start list dùng để xác định position of the bucket
        //      + Đánh dấu pos của (start of bucket) (Element index)
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(sqrt(n))
        //
//        MRUQueue mRUQueue = new MRUQueue(8); // Initializes the queue to [1,2,3,4,5,6,7,8].
//        System.out.println(mRUQueue.fetch(3)); // Moves the 3rd element (3) to the end of the queue to become [1,2,4,5,6,7,8,3] and returns it.
//        System.out.println(mRUQueue.fetch(5)); // Moves the 5th element (6) to the end of the queue to become [1,2,4,5,7,8,3,6] and returns it.
//        System.out.println(mRUQueue.fetch(2)); // Moves the 2nd element (2) to the end of the queue to become [1,4,5,7,8,3,6,2] and returns it.
//        System.out.println(mRUQueue.fetch(8)); // The 8th element (2) is already at the end of the queue so just return it.
        //
        MRUQueue mRUQueue = new MRUQueue(7); // Initializes the queue to [1,2,3,4,5,6,7,8].
        //[[1, 2], [3, 4], [5, 6], [7]]
        System.out.println(mRUQueue.fetch(3));
        //3rd = 3
        //[[1, 2], [4], [5, 6], [7,3]]
        System.out.println(mRUQueue.fetch(5));
        //5th = 6
        //[[1, 2], [4], [5], [7,3],[6]]
        System.out.println(mRUQueue.fetch(6));
        //5th = 3
        //[[1, 2], [4], [5], [7],[6,3]]
        System.out.println(mRUQueue.fetch(7));
        //7th = 3
        //[[1, 2], [4], [5], [7],[6,3]]
        System.out.println(mRUQueue.fetch(6));
        //6th = 6
        //[[1, 2], [4], [5], [7],[3,6]]
        System.out.println(mRUQueue.fetch(6));
        //6th = 6
        //[[1, 2], [4], [5], [7],[3,6]]
        System.out.println(mRUQueue.fetch(3));
        //3th = 4
        //[[1, 2], [5], [7],[3,6],[4]]
        //
        //#Reference:
        //2108. Find First Palindromic String in the Array
        //1788. Maximize the Beauty of the Garden: HARD
        //2136. Earliest Possible Day of Full Bloom: HARD
    }
}
