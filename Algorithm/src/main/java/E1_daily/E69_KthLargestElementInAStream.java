package E1_daily;

import java.util.PriorityQueue;

public class E69_KthLargestElementInAStream {

    public static class KthLargest {

        public PriorityQueue<Integer> sortQueue;
        public int size;

        public KthLargest(int k, int[] nums) {
            this.size=k;
            sortQueue=new PriorityQueue<>();
            for(int e: nums){
                sortQueue.add(e);
                if(sortQueue.size()>k){
                    sortQueue.poll();
                }
            }
        }

        public int add(int val) {
            sortQueue.add(val);
            if(sortQueue.size()>this.size){
                sortQueue.poll();
            }
            return sortQueue.peek();
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Design a class to find the kth largest element in a stream.
        //- Note that it is the (kth largest element) (in the sorted order), (not) (the kth distinct element).
        //- Implement KthLargest class:
        //  + KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
        //  + int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //
        //#Reference:
        //1825. Finding MK Average
        //2102. Sequentially Ordinal Rank Tracker
    }
}
