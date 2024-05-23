package E1_daily;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class E12_KthSmallestPrimeFraction {

    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        for(int i=0;i<n;i++){
            //k=3
            //0,1,2
            if (n-i-1>=k) {
                return new int[]{arr[i], arr[n-k-1]};
            }
            k-=n-i-1;
        }
        return new int[]{arr[0], arr[n-1]};
    }
    public static int[] kthSmallestPrimeFractionK(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[0]/a[1]-b[0]/b[1]);

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                queue.add(new int[]{arr[i], arr[j]});
            }
        }
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(queue.poll());
        }
        return list.get(k-1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a sorted integer array arr) containing (1 and prime numbers),
        // where (all the integers of arr) are (unique). You are also given (an integer k).
        //For every i and j where 0 <= i < j < arr.length, we consider the fraction (arr[i] / arr[j]).
        //* Return (the kth smallest fraction) considered.
        // Return your answer as (an array of integers of size 2),
        // where (answer[0] == arr[i]) and (answer[1] == arr[j]).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= arr.length <= 1000
        //1 <= arr[i] <= 3 * 10^4
        //arr[0] == 1
        //arr[i] is a prime number for i > 0.
        //All the numbers of arr are unique and sorted in strictly increasing order.
        //1 <= k <= arr.length * (arr.length - 1) / 2
        //
        //- Brainstorm
        //Ex:
        //arr = [1,2,3,5], k = 3
        //1/2, 1/3, 1/5, 2/3, 2/5, 3/5
        //=> sorted
        //1/5, 1/3, 1/2, 2/5, 2/3, 3/5
        //
        int[] arr = {1,2,3,5};
        int k = 3;
        System.out.println(kthSmallestPrimeFraction(arr, k));
        System.out.println(kthSmallestPrimeFractionK(arr, k));
    }
}
