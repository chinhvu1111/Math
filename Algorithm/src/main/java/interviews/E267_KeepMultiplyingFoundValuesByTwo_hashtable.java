package interviews;

import java.util.HashSet;

public class E267_KeepMultiplyingFoundValuesByTwo_hashtable {

    public static int findFinalValue(int[] nums, int original) {
//        int min=Integer.MAX_VALUE;
//        int max=Integer.MIN_VALUE;
        HashSet<Integer> hashSet=new HashSet<>();

        for(int val:nums){
//            min=Math.min(min, val);
//            max=Math.max(max, val);
            hashSet.add(val);
        }
        while (hashSet.contains(original)){
            original=original*2;
        }

        return original;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{5,3,6,1,12};
    }
}
