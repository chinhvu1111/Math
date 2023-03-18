package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class E17_FindScoreOfAnArrayAfterMarkingAllElements {

    public static long findScore(int[] nums) {
        int n=nums.length;
        boolean[] visited=new boolean[n];
        HashMap<Integer, TreeSet<Integer>> hashIndex=new HashMap<>();

        for(int i=0;i<n;i++){
            TreeSet<Integer> indexTree=hashIndex.get(nums[i]);
            if(indexTree==null){
                indexTree=new TreeSet<>();
            }
            indexTree.add(i);
            hashIndex.put(nums[i], indexTree);
        }
        int[] nums1=new int[n];

        for(int i=0;i<n;i++){
            nums1[i]=nums[i];
        }
        Arrays.sort(nums1);
        long rs=0;

        for(int i=0;i<n;i++){
            TreeSet<Integer> indexs=hashIndex.get(nums1[i]);
            //2,1,5,2 ==> 2 đã được đánh dấu rồi thì loại ra
            while (!indexs.isEmpty()&&visited[indexs.first()]) indexs.pollFirst();
            if(!indexs.isEmpty()){
                int index=indexs.pollFirst();
                visited[index]=true;
                if(index>=1){
                    visited[index-1]=true;
                }
                if(index<n-1){
                    visited[index+1]=true;
                }
                rs+=nums1[i];
                System.out.printf("Index: %s, value %s\n", index, nums1[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{2,1,3,4,5,2};
        int[] arr=new int[]{2,3,5,1,3,2};
        System.out.println(findScore(arr));
    }
}
