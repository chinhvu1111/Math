package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E261_RangeFrequencyQueries {

    static class RangeFreqQuery {

        public HashMap<Integer, List<Integer>> valToIndices;

        public RangeFreqQuery(int[] arr) {
            int n=arr.length;
            valToIndices=new HashMap<>();

            for(int i=0;i<n;i++){
                List<Integer> curIndices=valToIndices.getOrDefault(arr[i], new ArrayList<>());
                curIndices.add(i);
                valToIndices.put(arr[i], curIndices);
            }
        }

        public int upperBound(int left, List<Integer> indices){
            int low=0, high=indices.size()-1;
            int rs=-1;
            //2,...,6
            //left = 3
            while(low<=high){
                int mid=low+(high-low)/2;
                if(indices.get(mid)>=left){
                    rs=mid;
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            return rs;
        }
        public int lowerBound(int right, List<Integer> indices){
            int low=0, high=indices.size()-1;
            int rs=-1;
            //2,...,6
            //right = 3
            while(low<=high){
                int mid=low+(high-low)/2;
                if(indices.get(mid)<=right){
                    rs=mid;
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            return rs;
        }

        //Time: O(n)
        public int query(int left, int right, int value) {
            if(!this.valToIndices.containsKey(value)){
                return 0;
            }
            //Time: O(log(n))
            int upperBoundLeft = upperBound(left, this.valToIndices.get(value));
            int lowBoundRight = lowerBound(right, this.valToIndices.get(value));
            // System.out.printf("value: %s, %s %s\n", value, upperBoundLeft, lowBoundRight);
            // if(upperBoundLeft>lowBoundRight){
            //     return 0;
            // }
            if(upperBoundLeft==-1||lowBoundRight==-1){
                return 0;
            }
//            int leftVal = Math.max(upperBoundLeft, 0);
//            int rightVal = Math.max(lowBoundRight, 0);
            return lowBoundRight-upperBoundLeft+1;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a data structure to find the frequency of a given value in a given subarray.
        //- The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
        //- Implement the RangeFreqQuery class:
        //  + RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
        //  + int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
        //- A subarray is a contiguous sequence of elements within an array. arr[left...right]
        // denotes the subarray that contains the elements of nums between indices left and right (inclusive).
        //
        //Example 1:
        //
        //Input
        //["RangeFreqQuery", "query", "query"]
        //[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
        //Output
        //[null, 1, 2]
        //
        //Explanation
        //RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
        //rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
        //rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= arr.length <= 10^5
        //1 <= arr[i], value <= 10^4
        //0 <= left <= right < arr.length
        //At most 10^5 calls will be made to query
        //  + Length <= 10^5 ==> Time: O(n)
        //  + val <= 10^4 ==> Time: O(n*log(n))
        //
        //* Brainstorm:
        //- val: 10^5 times
        //  + Ex: val :[0,2,3,10]
        //- HashMap<Integer, List>
        //- Binary search
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
//        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
//        System.out.println(rangeFreqQuery.query(1, 2, 4)); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
//        System.out.println(rangeFreqQuery.query(0, 11, 33)); // return 2. The value 33 occurs 2 times in the whole array.
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{1,1,1,2,2});
        System.out.println(rangeFreqQuery.query(0,1,2)); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
        System.out.println(rangeFreqQuery.query(0,2,1)); // return 2. The value 33 occurs 2 times in the whole array.
        System.out.println(rangeFreqQuery.query(3,3,2)); // return 2. The value 33 occurs 2 times in the whole array.
        System.out.println(rangeFreqQuery.query(2,2,1)); // return 2. The value 33 occurs 2 times in the whole array.
    }
}
