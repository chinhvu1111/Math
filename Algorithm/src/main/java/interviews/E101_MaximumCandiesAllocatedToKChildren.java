package interviews;

public class E101_MaximumCandiesAllocatedToKChildren {

    public static int maximumCandies(int[] candies, long k) {
        int low=1;
        int high=10_000_000;
        int n=candies.length;
        long rs=0;

        while(low<=high){
            int mid= low + (high-low)/2;
            long count=0;

            for(int i=0;i<n;i++){
                count+=candies[i]/mid;

                if(count>k){
                    break;
                }
            }
            //+ count >= k : Quá nhiều số lớn hơn mid, số quá nhỏ --> tăng lên
            //+ count < k : số đó lớn hơn bình thường
            //
            if(count>=k){
                rs=mid;
                low=mid+1;
            }else{
                //Phải để dấu bằng ở đây để tìm max nhất
                high=mid-1;
            }
//            System.out.printf("low: %s high: %s mid: %s rs: %s\n", low, high, mid, rs);
        }
        return (int) rs;
    }

    public static int maximumCandiesOptimize(int[] candies, long k) {
        int low=1;
        int high;
        int n=candies.length;
        long rs=0;
//        int max=0;
        long sum=0;

        for(int i=0;i<n;i++){
//            max=Math.max(candies[i], max);
            sum+=candies[i];
        }
//        high=max;
        high= (int) (sum/k);

        while(low<=high){
            int mid= low + (high-low)/2;
            long count=0;

            for(int i=0;i<n;i++){
                count+=candies[i]/mid;

                if(count>k){
                    break;
                }
            }
            //+ count >= k : Quá nhiều số lớn hơn mid, số quá nhỏ --> tăng lên
            //+ count < k : số đó lớn hơn bình thường
            //
            if(count>=k){
                rs=mid;
                low=mid+1;
            }else{
                //Phải để dấu bằng ở đây để tìm max nhất
                high=mid-1;
            }
//            System.out.printf("low: %s high: %s mid: %s rs: %s\n", low, high, mid, rs);
        }
        return (int) rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{5,8,6};
//        int k=3;
//        int arr[]=new int[]{2,5};
//        int k=11;
        //
        int arr[]=new int[]{4,7,5};
        int k=4;
        System.out.println(maximumCandies(arr, k));
        System.out.println(maximumCandiesOptimize(arr, k));
        //Bài này tư duy như sau:
        //1, Chú ý để đúng dấu = condition
        //2, Tối ưu
        //+ Tối ưu high
        //+ high : max(sum[i]) win 80%
        //+ high : sum/k : win 100%
        //1_000_000_000_000 > 2147483647
    }
}
