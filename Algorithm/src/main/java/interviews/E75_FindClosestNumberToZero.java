package interviews;

public class E75_FindClosestNumberToZero {

    public static int findClosestNumber(int[] nums) {
        int rs = Integer.MAX_VALUE;
        int currentNumner=Integer.MAX_VALUE;

        for(int i=0;i<nums.length;i++){
            int value=Math.abs(nums[i]);

            //Chỗ này thêm để tăng tốc độ
            if(value>rs){
                continue;
            }

            if(value<rs
                    ||(value==rs&&nums[i]>currentNumner)){
                rs=value;
                currentNumner=nums[i];
            }
        }
        return currentNumner;
    }

    public static int findClosestNumberOptimize(int[] nums) {
        int min = -100000;
        int max = 100001;
        for(int num : nums) {
            if(num < 0) min = Math.max(min, num);
            else max = Math.min(max, num);
        }
        if(-min == max) return max;
        if(-min < max) return min;
        else return max;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{-4,-2,1,4,8};
        System.out.println(findClosestNumber(arr));
        System.out.println(findClosestNumberOptimize(arr));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Ở đây ta sẽ lưu 2 varible:
        //+ Giá trị abs
        //+ Giá trị original
        //--> Khi compare lần lượt các value --> Cần phải check 2 đk với (abs(nums[i])==rs) || (nums[i]>currentValue)
        //1.1, Ta có thể tối ưu:
        //if(value>rs){
        //                continue;
        //            }
        //--> Để không qua block if() else bên dưới nữa.
        //Cách 2:
        //+ Ta có thể chia ra 2 loại số âm/ số dương.
        //--> Out of loop :
        //+ check if (-min < max) return min
        //+ check if (-min == max) return max

        //* REFERENCE:
        //+ Find K-th Smallest Pair Distance
    }
}
