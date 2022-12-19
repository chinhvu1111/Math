package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E226_NumberOfRecentCalls_stack {

    public static class RecentCounter {

        public Deque<Integer> times;

        public RecentCounter() {
            times=new LinkedList<>();
        }

        public int ping(int t) {
            while (!times.isEmpty()&&times.peekFirst()<t-3000){
                times.removeFirst();
            }
            times.add(t);
            return times.size();
        }
    }

    public static void main(String[] args) {
    }
}
