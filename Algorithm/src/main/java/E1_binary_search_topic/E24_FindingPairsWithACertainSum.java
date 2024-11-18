package E1_binary_search_topic;

import java.util.HashMap;

public class E24_FindingPairsWithACertainSum {

    static class FindSumPairs {

        public int[] nums1;
        public int[] nums2;
        public HashMap<Integer, Integer> mapCount1=new HashMap<>();
        public HashMap<Integer, Integer> mapCount2=new HashMap<>();
        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1=nums1;
            this.nums2=nums2;
            for (int i = 0; i < nums1.length; i++) {
                mapCount1.put(nums1[i], mapCount1.getOrDefault(nums1[i], 0)+1);
            }
            for (int i = 0; i < nums2.length; i++) {
                mapCount2.put(nums2[i], mapCount2.getOrDefault(nums2[i], 0)+1);
            }
        }

        public void add(int index, int val) {
            if(mapCount2.containsKey(nums2[index])){
                if(mapCount2.get(nums2[index])==1){
                    mapCount2.remove(nums2[index]);
                }else{
                    mapCount2.put(nums2[index], mapCount2.get(nums2[index])-1);
                }
            }
            mapCount2.put(nums2[index]+val, mapCount2.getOrDefault(nums2[index]+val, 0)+1);
            nums2[index]+=val;
        }

        public int count(int tot) {
            int rs=0;
            for (int i = 0; i < nums1.length; i++) {
                if(mapCount2.containsKey(tot-nums1[i])){
                    rs+=mapCount2.get(tot-nums1[i]);
                }
            }
            return rs;
        }
    }
    public static void main(String[] args) {
    }
}
