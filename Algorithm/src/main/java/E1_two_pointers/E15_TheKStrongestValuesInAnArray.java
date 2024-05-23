package E1_two_pointers;

import java.util.Arrays;

public class E15_TheKStrongestValuesInAnArray {

    public static int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n=arr.length;
        int median=(n%2==1)?arr[n/2]:arr[(n-1)/2];
        int low=0, high=n-1;
        int[] rs=new int[k];
        int i=0;

        while(low<high){
            if(median-arr[low]>arr[high]-median){
                rs[i++]=arr[low++];
            }else{
                rs[i++]=arr[high--];
            }
            if(i==k){
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of integers (arr) and an integer (k).
        //- A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
        //- If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if (arr[i] > arr[j]).
        //* Return a list of the (strongest k values) in the array.
        // return the answer in any arbitrary order.
        //- Median is the (middle) value in (an ordered integer list).
        // More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
        //Ex:
        //For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11]
        // and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
        //For arr = [-7, 22, 17,3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22]
        // and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= arr.length <= 10^5
        //-10^5 <= arr[i] <= 10^5
        //1 <= k <= arr.length
        //
        //- Brainstorm
        //Ex:
        //Input: arr = [1,2,3,4,5], k = 2
        //Output: [5,1]
        //Explanation:
        // Median is 3, the elements of the array sorted by the strongest are [5,1,4,2,3].
        // The strongest 2 elements are [5, 1]. [1, 5] is also accepted answer.
        //Please note that although |5 - 3| == |1 - 3| but 5 is stronger than 1 because 5 > 1.
        //
        //- Làm bình thường bằng two pointers là được.
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*log(n))
        //
        //2. Max heap
        //2.0,
        //- Dùng ct để sort theo | arr[i] - median |
        //==== CODE
        //PriorityQueue<Integer> pq = new PriorityQueue<>(
        //                (a, b) -> Math.abs(a - m) == Math.abs(b - m) ? b - a : Math.abs(b - m) - Math.abs(a - m)
        //        );
        //==== CODE
        //
        //#Reference:
        //2501. Longest Square Streak in an Array
        //1634. Add Two Polynomials Represented as Linked Lists
        //1335. Minimum Difficulty of a Job Schedule
        //
    }
}
