package contest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class E172_FinalArrayStateAfterKMultiplicationOperationsI {

    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        int n=nums.length;

        for(int i=0;i<n;i++){
            set.add(new int[]{i, nums[i]});
        }
        for(int i=0;i<k;i++){
            int[] curMinE=set.first();
            set.remove(curMinE);
            curMinE[1]=curMinE[1]*multiplier;
            set.add(curMinE);
        }
        Iterator<int[]> iter = set.iterator();

        while (iter.hasNext()){
            int[] curE=iter.next();
            nums[curE[0]]=curE[1];
        }

//        for (int i = 0; i < n; i++) {
//            System.out.printf("%s ", nums[i]);
//        }
//        System.out.println();
        return nums;
    }

    public static void main(String[] args) {
//        int[] nums = {2,1,3,5,6};
//        int k = 5, multiplier = 2;
        int[] nums = {2,1,3,5,6};
        int k = 5, multiplier = 2;
//        int[] nums = {1,2};
//        int k = 3, multiplier = 4;
        getFinalState(nums, k, multiplier);
    }
}
