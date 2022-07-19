package interviews;

public class E86_MinimumOperationsToMakeTheArrayIncreasing {

    public static int minOperations(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int count = 0;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (num == nums[i]) {
                count++;
                num++;
            } else if (num > nums[i]) {
                num++;
                count += num - nums[i];
            } else {
                num = nums[i];
            }
        }

        return count;
    }

    public static int minOperationsReWrite(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int count=0;
        int num=nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i]==num){
                count++;
                num++;
//                nums[i]=num+1;
            }if(nums[i]<num){
                num++;
                count+=num-nums[i]+1;
//                nums[i]=num+1;
            }else{
                num=nums[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int nums[]=new int[]{1,5,2,4,1};
        System.out.println(minOperations(nums));
        //Win 98%
        //1.1, Ở cách trước ở chỗ không cần truy cập nums[right] nhiều lần
        //--> Lưu ở valueRight thôi, còn left vẫn phải truy cập theo index.
        //1,2, Ta sẽ luôn lưu 1 giá trị tạm num --> Cầm nó đi so sánh + update trên register thay vì array[i]
        //--> Sẽ nhanh hơn.
    }
}
