package interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class E282_RemoveStonesToMinimizeTheTotal {

    //** Đề bài:
    //- Cho piles[] : piles[i] là số viên đá ở ith pile
    //- k là số lần apply operation:
    //+ Chọn pipe[i] và remove floor(piles[i] / 2) stones từ đó.
    //--> Tìm tổng số rock min sau khi apply k lần operations.
    //
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- Total min --> Remove max
    //+ Vì các pipe độc lập --> Ta sẽ chọn move pipe có nhiều rocks nhất <=> .
    //+ Ta sẽ sắp xếp array theo tăng dần value --> sau đó sẽ chọn ra max
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int n=piles.length;
        int sum=0;

        for (int pile : piles) {
            queue.add(pile);
            sum+=pile;
        }
        int removeTotal=0;

        for(int i=0;i<k;i++){
            if(!queue.isEmpty()){
                int value=queue.poll();
                removeTotal+=value/2;
                value= value-value/2;
                queue.add(value);
            }
        }
        return sum-removeTotal;
    }

    public static void main(String[] args) {
        int[] piles = {5,4,9};
        int k = 2;
        System.out.println(minStoneSum(piles, k));
        //#Reference:
        //1963. Minimum Number of Swaps to Make the String Balanced
        //2208. Minimum Operations to Halve Array Sum
        //2530. Maximal Score After Applying K Operations
        //2558. Take Gifts From the Richest Pile
    }
}
