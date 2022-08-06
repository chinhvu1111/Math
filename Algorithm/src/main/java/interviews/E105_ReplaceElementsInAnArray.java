package interviews;

import java.util.HashMap;

public class E105_ReplaceElementsInAnArray {

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

            if(!valMapIndex.containsKey(key)){
                continue;
            }
            int currentIndex=valMapIndex.get(key);

            valMapIndex.remove(key);
            nums[currentIndex]=value;
            valMapIndex.put(value, currentIndex);
        }
        return nums;
    }

    public static int[] arrayChangeOptimize(int[] nums, int[][] operations) {
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
            valMapIndex.put(value, currentIndex);
        }
        return nums;
    }

    public static int[] arrayChangeOptimize2(int[] nums, int[][] operations) {
        HashMap<Integer, Integer> store=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            store.put(nums[i], i);
        }
        for(int i=0;i<operations.length;i++){
            int indx=store.get(operations[i][0]);

            nums[indx]=operations[i][1];
            store.put(operations[i][1], indx);
        }
        return nums;
    }

    public static void main(String[] args) {
//        int []nums=new int[]{1,2,4,6};
//        int operations[][]=new int[][]{{1,3},{4,7},{6,1}};
//        int []nums=new int[]{1,2};
//        int operations[][]=new int[][]{{1,3},{2,1},{3,2}};
        int []nums=new int[]{1,2};
        int operations[][]=new int[][]{{1,3},{2,1},{3,2}};
        //Expected : [2,1]
        //Result : [3,1]
        //Explain :
        //Step 1 : {1,3} : {3,2}
        //Step 3: {2,1} : {3,1}
        //Step 4: {3,2} : {2,1}

        int rs[]=arrayChange(nums, operations);
        int rs1[]=arrayChangeOptimize(nums, operations);
        int rs2[]=arrayChangeOptimize2(nums, operations);

        for(int i=0;i<rs.length;i++){
            System.out.print(rs[i]);
        }
        System.out.println();
        for(int i=0;i<rs.length;i++){
            System.out.print(rs1[i]);
        }
        System.out.println();
        for(int i=0;i<rs2.length;i++){
            System.out.print(rs2[i]);
        }
        System.out.println();
        //Bài này tư duy như sau:
        //1, Ta tư duy như bình thường
        //+ Từ value --> map ra index
        //+ Từ index --> map ra để thay đổi giá trị
        //2, Tối ưu
        //2.1, Tránh tạo các variable như
        //+ int key=operations[i][0];
        // int value=operations[i][1];
        // ---> Có thể làm slow chương trình.
        //===> Bỏ đi.
    }
}
