package interviews;

public class E271_MoveZeroes_two_pointers {

    public static void moveZeroes(int[] nums) {
        int n=nums.length;
        int index=0;
        int index1=0;

        for(int i=0;i<n;i++){
            if(nums[index1]!=0){
                int temp=nums[index1];
                nums[index1]=nums[index];
                nums[index]=temp;
                index1++;
                index++;
            }else{
                index1++;
            }
        }
//        System.out.println();
    }

    public static void main(String[] args) {
//        int[] nums = {0,1,0,3,12};
        int[] nums = {0,0,0,3,12};
        moveZeroes(nums);
    }
}
