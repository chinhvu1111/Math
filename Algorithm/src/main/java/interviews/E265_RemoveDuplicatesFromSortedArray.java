package interviews;

public class E265_RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        int count=1;
        int prevNum=nums[0];

        for (int i = 1; i < n; i++) {
            if(prevNum!=nums[i]){
                nums[count]=nums[i];
                prevNum=nums[i];
                count++;
            }
        }
        return count+1;
    }

    public static void main(String[] args) {
//        int[] nums=new int[]{1,1,2};
        int[] nums=new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }
}
