package interviews;

import sun.jvm.hotspot.runtime.linux_aarch64.LinuxAARCH64JavaThreadPDAccess;

import java.util.HashMap;

public class E101_ReplaceElementsInAnArray {

    public static int[] arrayChange(int[] nums, int[][] operations) {
        int n=nums.length;
        int m=operations.length;

        HashMap<Integer, Integer> valMapIndex=new HashMap<>();

        for(int i=0;i<n;i++){
            valMapIndex.put(nums[i], i);
        }
        for(int i=0;i<m;i++){
            int key=operations[i][0];
            int value=operations[i][1];
            int currentIndex=valMapIndex.get(key);

            nums[currentIndex]=value;
        }
        return nums;
    }

    public static void main(String[] args) {
        int []nums=new int[]{1,2,4,6};
        int operations[][]=new int[][]{{1,3},{4,7},{6,1}};
    }
}
