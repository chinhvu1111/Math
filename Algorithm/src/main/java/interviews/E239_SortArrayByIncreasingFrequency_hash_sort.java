package interviews;

import java.util.*;

public class E239_SortArrayByIncreasingFrequency_hash_sort {

    public static int[] frequencySort(int[] nums) {
        int n=nums.length;
        Map<Integer, Integer> count =new HashMap();
        HashMap<Integer, PriorityQueue<Integer>> countListNums=new HashMap<>();

        for (int num : nums) {
//            count[num]++;
            count.put(num, count.getOrDefault(num, 0)+1);
        }
        for (int num : nums) {
            PriorityQueue<Integer> currenList = countListNums.get(count.get(num));

            if (currenList == null) {
                currenList = new PriorityQueue<>((t1, t2) -> t2-t1);
            }
            currenList.add(num);
            countListNums.put(count.get(num), currenList);
        }
        int[] rs=new int[n];
        int index=0;

        for(int i=1;i<=100;i++){
            if(countListNums.containsKey(i)){
                PriorityQueue<Integer> currentList=countListNums.get(i);

                while (!currentList.isEmpty()){
                    rs[index++]=currentList.poll();
                }
            }
        }
        return rs;
    }

    public static void println(int[] nums){
        int n=nums.length;

        for(int i=0;i<n;i++){
            System.out.printf("%s, ", nums[i]);
        }
        System.out.println();
    }
    public static void main(String[] args) {
//        int[] arr=new int[]{1,1,2,2,2,3};
        int[] arr=new int[]{-1,1,-6,4,5,-6,1,4,1};
        println(frequencySort(arr));
    }
}
