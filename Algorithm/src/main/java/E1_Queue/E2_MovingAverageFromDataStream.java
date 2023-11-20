package E1_Queue;

import java.util.LinkedList;
import java.util.Queue;

public class E2_MovingAverageFromDataStream {
    static double sum=0;
    static int sizeAvg=0;
    static int maxSize=0;
    static Queue<Integer> queues = null;

    public  E2_MovingAverageFromDataStream(int size) {
        queues=new LinkedList<>();
        maxSize=size;
    }

    public double next(int val) {
        if(!queues.isEmpty()&&queues.size()==maxSize){
            sum-=queues.remove();
            sizeAvg--;
        }
        sum+=val;
        queues.add(val);
        sizeAvg++;
        // System.out.printf("Sum:%s, size: %s\n", sum, size);
        return sum/sizeAvg;
    }
    public static void main(String[] args) {
        //#Reference:
        //2910. Minimum Number of Groups to Create a Valid Assignment
        //937. Reorder Data in Log Files
        //120. Triangle
    }
}
