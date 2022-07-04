package interviews;

public class E60_FindPivotIndex {

    public static int pivotIndex(int[] nums) {
        int sum=0;
        int n=nums.length;
        int prefixSum[]=new int[n];

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }

        for(int i=0;i<n;i++){
            int left=0;

            if(i-1>=0){
                left=prefixSum[i-1];
            }
            if(sum-nums[i]-left==left){
                return i;
            }
        }
        return -1;
    }

    public static int pivotIndexOptimized(int[] nums) {
        int sum=0;
        int n=nums.length;
//        int prefixSum[]=new int[n];

        for(int i=0;i<n;i++){
            sum+=nums[i];
            nums[i]=sum;
        }

        for(int i=0;i<n;i++){
            int left=0;

            if(i-1>=0){
                left=nums[i-1];
            }
            if(sum-nums[i]==left){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,7,3,6,5,6};
//        int arr[]=new int[]{1};
//        int arr[]=new int[]{1,2};
//        int arr[]=new int[]{1,1};
//        int arr[]=new int[]{2,1,-1};
        System.out.println(pivotIndex(arr));
        //Bài này ta tư duy như sau:
        //1, Bài này nếu tư duy bình thường thì không tối ưu:
        //1,1 Tạo mạng prefixSum để tính:
        //+ left=prefix[i-1];
        //+ return khi if(sum - nums[i] - left==left)
        //==> Tốn chi phí
        //==> Tốn chi phí create new array
        //1.2, Nếu dùng nums[i] biến đổi thay cho luôn prefixSum[i]
        //CT:
        //+ sum- (num1[i]-nums1[i-1] : nums[i] cũ ) - nums1[i-1] == nums1[i-1]
        //
        //Reference:

        //Subarray Sum Equals K
        //Find the Middle Index in Array
        //Number of Ways to Split Array
        //Maximum Sum Score of Array
    }
}
