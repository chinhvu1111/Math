package E1_Array;

public class E10_CheckifAnArrayIsConsecutive {

    public static boolean isConsecutive(int[] nums) {
        int min=Integer.MAX_VALUE;

        for(int num:nums){
            min=Math.min(num, min);
        }
        int max=min+nums.length-1;
        int count[]=new int[max+1];

        for(int i=0;i<nums.length;i++){
            if(nums[i]>max||count[nums[i]]==-1){
                return false;
            }
            count[nums[i]]=-1;
        }
        return true;
    }

    public static boolean isConsecutiveOptimization(int[] nums) {
        int min=Integer.MAX_VALUE;
        for(int num:nums){
            min=Math.min(min, num);
        }

        int n=nums.length;
        for(int i=0;i<n;i++){
            int index=Math.abs(nums[i])-min;
            if(index>=nums.length){
                return false;
            }else if(nums[index]>=0){
                if(nums[i]==0){
                    nums[i]=Integer.MIN_VALUE;
                }
                nums[index]*=-1;
            }
        }
        for (int num : nums) {
            if (num >= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- An array is consecutive if it contains every number in the range [x, x + n - 1] (inclusive),
        // where x is the minimum number in the array and n is the length of the array.
        //* Return true nếu array bao gồm các số từ (min, min+n-1) (Mỗi số xuất hiện 1 lần)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
        //- Ở đây ta tạo count + sau đó thay đổi từ (count[i]=0 --> -1)
        //
        //1.1, Optimization
        //- Tối ưu space xuống O(1)
        //Ex:
        //nums=[1,3,4,2]
        //+ i=0, nums[i]=1, nums[index=0]=-1 => nums=[-1,3,4,2]
        //+ i=1, nums[i]=3, nums[index=3-1]=-1 => nums=[-1,3,-4,2]
        //+ i=2, nums[i]=-4, nums[index=4-1]=-1 => nums=[-1,3,-4,-2]
        //+ i=3, nums[i]=-2, nums[index=2-1]=-1 => nums=[-1,-3,-4,-2]
        //==> Nếu cái nào mã >=0 ==> return false.
        //
        int[] arr={1,3,4,2};
        System.out.println(isConsecutive(arr));
        System.out.println(isConsecutiveOptimization(arr));
    }
}
