package interviews;

public class E82_MaximumAverageSubarrayI {

    public static double findMaxAverage(int[] nums, int k) {
        int sum=0;
        int n=nums.length;
        int rs;
        int left=0;

        for(int i=0;i<k&&i<n;i++){
            sum+=nums[i];
        }
        rs=sum/k;
        for(int i=k;i<n;i++){
            sum=sum-nums[left++]+nums[i];
            rs=Math.max(rs, sum);
        }
        return (double) rs/k;
    }

    public double findMaxAverageOptimize(int[] nums, int k) {
        int sum=0;
        for(int i=0;i<k;i++){
            sum+= nums[i];
        }
        int max=sum;
        int p=0,q=k;
        for (int i=0;i<nums.length-k;i++){
            sum+=(nums[q++]-nums[p++]);
            if(sum>max) max=sum;
        }
        return (double)max/k;

    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,12,-5,-6,50,3};
//        int arr[]=new int[]{1,12,-5,-6,50,3};
        int arr[]=new int[]{1,12};
//        int k=4;
//        int k=1;
        int k=2;
        System.out.println(findMaxAverage(arr, k));
        //Bài này tư duy như sau:
        //1, Với dạng bài tính average như thế này:
        //--> Ta có thể tính toán phép chia cuối cùng (
        //+ return sum/k)
        //==> Có cải thiện được 1 chút tốc độ
        //1.1, Nhớ update sum liên tục.
        //2, Tối ưu Data type
        //+ Int --> speeder than Double
        //+ Sum --> Int
    }
}
