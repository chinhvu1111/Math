package contest.test;

import java.util.Arrays;
import java.util.List;

public class MinimizeArrayCost {

    public static long getMinimumCost(List<Integer> arr) {
        long sum=0L;
        int n=arr.size();

        for(int i=1;i<n;i++){
            sum+=Math.pow(arr.get(i)-arr.get(i-1), 2);
        }
        long rs=sum;

        for(int i=1;i<n;i++){
            long curRs= (long) (sum - Math.pow(arr.get(i) - arr.get(i - 1), 2));
            int mid=(arr.get(i)+arr.get(i-1))/2;
            curRs+= Math.pow(arr.get(i)-mid, 2);
            curRs+= Math.pow(arr.get(i-1)-mid, 2);
            rs=Math.min(rs, curRs);
        }
        return rs;
    }

    public static void main(String[] args) {
//        Integer[] nodes= {1,3,5,2,10};
//        Integer[] nodes= {4, 7, 1, 4};
        Integer[] nodes= {4,7,7};
        System.out.println(getMinimumCost(Arrays.asList(nodes)));
    }
}
