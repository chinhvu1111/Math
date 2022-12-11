package interviews;

import java.util.*;

public class E221_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public static int longestSubarray(int[] nums, int limit) {
        int n=nums.length;
        TreeSet<Integer> treeSet=new TreeSet();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int start=0;
        int end=0;
        int min=nums[0];
        int max=nums[0];
        int rs=0;
        treeSet.add(nums[0]);
        mapCount.put(nums[0], 1);
        if(limit != 0){
            rs=1;
        }

        //Nếu
        //- a, b, c nếu max-min > limit -> pop đến khi đạt dược giá trị thì thôi
        for(int i=1;i<n;i++){
            end++;
            treeSet.add(nums[i]);
            if(!mapCount.containsKey(nums[i])){
                mapCount.put(nums[i], 1);
            }else{
                mapCount.put(nums[i],mapCount.get(nums[i])+1);
            }
            min=treeSet.first();
            max=treeSet.last();
            while (max-min>limit&&!treeSet.isEmpty()){
                Integer currentCount=mapCount.get(nums[start]);

                if(currentCount==1){
                    treeSet.remove(nums[start]);
                    mapCount.remove(nums[start]);
                }else{
                    mapCount.put(nums[start], currentCount-1);
                }
                start++;
                min=treeSet.first();
                max=treeSet.last();
            }
//            int countElement=0;
//            Iterator<Integer> iterator= treeSet.iterator();
//
//            while (iterator.hasNext()){
//                int currenNumber=iterator.next();
//                countElement+=mapCount.get(currenNumber);
//            }
            rs=Math.max(rs, end-start+1);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{10,1,2,4,7,2};
//        int limit = 5;
//        int[] arr=new int[]{4,2,2,2,4,4,2,2};
//        int limit = 0;
        int[] arr=new int[]{8};
        int limit = 10;
        System.out.println(longestSubarray(arr, limit));
    }
}
