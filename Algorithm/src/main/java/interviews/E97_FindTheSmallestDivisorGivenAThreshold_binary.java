package interviews;

public class E97_FindTheSmallestDivisorGivenAThreshold_binary {

    public static int smallestDivisor(int[] nums, int threshold) {
        long sum=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        int low= (int) (sum/threshold);
        int high= (int) sum;

        while (low<high){
            int mid=low +(high-low)/2;

            if(mid!=0&&isValid(mid, nums, threshold)){
                high=mid;
            }else low=mid+1;
        }

        return (int) low;
    }

    public static boolean isValid(long mid, int nums[], int threshold){
        long s=0;

        for(int i=0;i<nums.length;i++){
//            s+=Math.ceil((float)nums[i]/mid);
            s+=(nums[i]+mid-1)/mid;
        }
        return s<=threshold;
    }

    public int smallestDivisorOptimize(int[] A, int threshold) {
        int left = 1, right = (int)1e6;
        while (left < right) {
            int m = (left + right) / 2, sum = 0;
            for (int i : A)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }

    public static int smallestDivisorRefactor(int[] nums, int threshold) {
        int low= 1;
        int high= (int)1e6;

        while (low<high){
            int mid=low +(high-low)/2;

            if(isValidRefactor(mid, nums, threshold)){
                high=mid;
            }else low=mid+1;
        }

        return low;
    }

    public static boolean isValidRefactor(int mid, int nums[], int threshold){
        int s=0;

        for(int x: nums){
            s+=(x+mid-1)/mid;
        }
        return s<=threshold;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,5,9};
//        int threshold=5;
        int arr[]=new int[]{21212,10101,12121};
        int threshold=1000000;
        System.out.println(smallestDivisor(arr, threshold));
        System.out.println(smallestDivisorRefactor(arr, threshold));
        //Bài này tư duy như sau:
        //1, Từ sum --> Ta có thể suy ra min của result
        //+ min=sum/ threshold
        //+ max=sum
        //2, Tối ưu
        //2.1, Để làm tròn ceil khi chia cho 1 số thì :
        //+ x/m --> (x+m-1)/m
        //2.2, Tối ưu range --> high = 1_000_000_009 --> Tối ưu hơn việc dùng SUM
        //+ Long value SUM.
        //2.3, Các phép case từ int --> long (slow)
    }
}
