package interviews;

public class E178_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int sum=0;
        int n=nums.length;
        int rs=Integer.MIN_VALUE;

        if(n>=1){
            sum=nums[0];
            rs=sum;
        }

        for(int i=1;i<n;i++){
            if(sum+nums[i]<=nums[i]){
                sum=nums[i];
            }else if(sum+nums[i]>nums[i]){
                sum+=nums[i];
            }
            rs=Math.max(rs, sum);
        }
        return rs;
    }

    public static int maxSubArrayBruteForce(int[] nums) {
        int n=nums.length;
        int[] prefixSum =new int[n];
        int sum=0;

        //- Method 1:
        //array: [-2, 1, -3, 4]
        //prefix:[-2,-1, -4, 0]
        //sum= prefix[i] - prefix[j] ( range: j+1 --> i
        //
        //- Method 2:
        //array: [-2, 1, -3, 4]
        //prefix:[0(i), -2, -1(j), 4, 0]
        //sum= prefix[i] - prefix[j] ( range: j --> i-1)
        //
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
            rs=Math.max(rs, nums[i]);
        }
        rs=Math.max(rs, sum);

        for(int i=0;i<n;i++){
            int leftSum=subFindMaxSubArrayBruteForce(nums, prefixSum, 0, i);
            int rightSum=subFindMaxSubArrayBruteForce(nums, prefixSum, i, n-1);
            rs=Math.max(rs, Math.max(leftSum, rightSum));
        }

        return rs;
    }

    public static int subFindMaxSubArrayBruteForce(int[] nums, int[] prefixSum, int left, int right){
        int valueLeft=0;
//        System.out.printf("%s %s \n",left, right);

        if(left==right){
            return nums[left];
        }
        if(left>0){
            valueLeft=prefixSum[left-1];
        }
        int currentSum=prefixSum[right]-valueLeft;

        if(left==right-1){
            currentSum=Math.max(currentSum, Math.max(nums[left], nums[right]));
            return currentSum;
        }
        for(int i=left+1;i<right;i++){
            int currentLeftValue=subFindMaxSubArrayBruteForce(nums, prefixSum, left, i);
            int currentRightValue=subFindMaxSubArrayBruteForce(nums, prefixSum, i, right);
            currentSum=Math.max(currentSum, Math.max(currentLeftValue, currentRightValue));
        }

        return currentSum;
    }

    public static int subFindMaxSubArrayDivideAndConquer(int[] nums, int left, int right){
//        if(left>right){
//            return 0;
//        }
        if(left==right){
            return nums[left];
        }
        int mid=left + (right-left)/2;
//        System.out.printf("%s %s \n", left, right);

        //1, Chia thành 2 case:
        //- Có đi qua mid + đi ra cả 2 phía
        //- Không đi qua mid + nằm 1 trong 2 phía
        int valueLeft=subFindMaxSubArrayDivideAndConquer(nums, left, mid);
        int valueRight=subFindMaxSubArrayDivideAndConquer(nums, mid+1, right);
        int valueCross=subFindMaxSubArrayCrossMid(nums, left, right, mid);

//        System.out.printf("%s %s %s \n", valueLeft, valueRight, valueCross);
        return Math.max(valueLeft, Math.max(valueRight, valueCross));
    }
    public static int subFindMaxSubArrayCrossMid(int[] nums, int left, int right, int mid){
        int leftSum=0;
        int maxLeftSum=Integer.MIN_VALUE;

        //2,
        //- Đoạn này phải đúng thứ tự nếu không thì nó sẽ bị sai
        //==> i=mid-1 --> 0 (i--)
        for(int i=mid;i>=left;i--){
            leftSum+=nums[i];
            maxLeftSum=Math.max(maxLeftSum, leftSum);
        }
        int rightSum=0;
        int maxRightSum=Integer.MIN_VALUE;

        //3,
        //- left, right có mid phải bù được đủ cases so với trường hợp CROSS
        for(int i=mid+1;i<=right;i++){
            rightSum+=nums[i];
            maxRightSum=Math.max(maxRightSum, rightSum);
        }
        if(maxLeftSum==Integer.MIN_VALUE){
            maxLeftSum=0;
        }
        if(maxRightSum==Integer.MIN_VALUE){
            maxRightSum=0;
        }

        return maxLeftSum+maxRightSum;
    }

    public static int maxSubArrayDivideAndConquer(int[] nums) {
        return subFindMaxSubArrayDivideAndConquer(nums, 0, nums.length-1);
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        int arr[]=new int[]{5,4,-1,7,8};
//        int arr[]=new int[]{-1};
//        int arr[]=new int[]{-2, 1};
//        int arr[]=new int[]{-1,0,-2};
//        int arr[]=new int[]{-2,-1};
        int arr[]=new int[]{8,-19,5,-4,20};

        System.out.println(maxSubArray(arr));
        System.out.println(maxSubArrayBruteForce(arr));
        System.out.println(maxSubArrayDivideAndConquer(arr));
        //
        //** Đề bài
        //- Trả lại tổng lớn nhất liên tiếp của 1 array
        //- Mảng có số âm ==> Nên không thể reset sum=0
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1,
        //Ở đây nó chỉ xảy ra 2 case:
        //- sum+nums[i]<=nums[i] : sum=nums[i]
        //+ Tức là nếu sum cộng thêm nums[i] --> kết quả <= nums[i] : Nên lấy nums[i]
        //- sum+nums[i]>nums[i] : sum+=nums[i]
        //+ Tức là rs ta đã có thể tìm được MAX sẵn trước đó rồi --> sum + nums[i] > nums[i]
        //==> Việc bắt đầu từ sum > bắt đầu từ (i) nums[i] ==> Ta sẽ update sum (Cover cả trường hợp sum < 0 )
        //
        //1.2, Sum sẽ lấy luôn số đầu tiên nums[0]
        //
        //Cách 2 : Brute force
        //2,
        //2.1, Ta sẽ xét (i) 0 --> n-1
        //Có 2 cách lưu prefixSum:
        //- Method 1:
        //array: [-2, 1, -3, 4]
        //prefix:[-2,-1, -4, 0]
        //sum= prefix[i] - prefix[j] ( range: j+1 --> i
        //
        //# Time complexity: O(N)
        //# Space : O(1)
        //
        //- Method 2:
        //array: [-2, 1, -3, 4]
        //prefix:[0(i), -2, -1(j), 4, 0]
        //sum= prefix[i] - prefix[j] ( range: j --> i-1)
        //
        //- Tức là luôn có currentSum của array từ (i, j) = prefix[j+1] - prefix[i]
        //- Sau đó ta sẽ xét tiếp tục bên (left,right)
        //
        //# Time complextity: N * N/2 * N/4 * N/8 = N^(Log(2) N)
        //# Space : Log(2) N
        //
        //cách 3:
        //3,
        //3.1,
        //- Tư tưởng chính ở đây là phân bài toàn thành nhiều cases khác nhau:
        //Chia thành 2 case:
        //- Có đi qua mid + đi ra cả 2 phía
        //+ (left, right)
        //- Không đi qua mid + nằm (trọn vẹn) 1 trong 2 phía ==> Cần phải cover hết cases.
        //+ (left, mid), (mid+1, right)
        //- Max lúc đó sẽ = Max(leftValue, RightValie, crossValue)
        //
        //3.2, Key chính của tư duy này là việc
        //- Tính sum cho dựa trên vị trí (mid)
        //+ leftSum : end at mid
        //==> i (mid-1 --> left)
        //+ rightSum : start at mid
        //==> i (mid --> right)
        //*** Cần 1 chú ý nữa là Cross ==> Thứ tự tính sum (left/ right) ==> Cần phải đúng so với (mid) (Như đã giải thích bên trên)
        //
        //# Reference:
        //- Best Time to Buy and Sell Stock
        //- Degree of an Array
        //- Maximum Score Of Spliced Array
        //- Maximum Absolute Sum of Any Subarray
        //- Maximum Subarray Sum After One Operation
        //- Substring With Largest Variance
        //- Count Subarrays With Score Less Than K
    }
}
