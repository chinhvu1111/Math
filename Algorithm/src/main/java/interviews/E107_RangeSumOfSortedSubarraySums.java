package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E107_RangeSumOfSortedSubarraySums {

    public static int rangeSum(int[] nums, int n, int left, int right) {
        int[] prefixSum =new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        List<Integer> newList=new ArrayList<>();

        for(int i=1;i<=n;i++){
            for(int j=0;j+i<=n;j++){
                int k=i+j-1;
                int leftSum=0;
                if(j>0){
                    leftSum=prefixSum[j-1];
                }
                newList.add(prefixSum[k]-leftSum);
            }
        }
        Collections.sort(newList);
        int result=0;

        for(int i=left-1;i<right;i++){
            result=(result+newList.get(i))%1_000_000_007;
        }
        return result;
    }

    public static int rangeSumOptimizeTwoPointer(int[] nums, int n, int left, int right) {
        int[] sumSubArr =new int[n*(n+1)/2];
        int index=0;

        for(int i=0;i<n;i++){
            int sum=0;

            for(int j=i;j<n;j++){
                sum+=nums[j];
                sumSubArr[index++]=sum;
            }
        }
        Arrays.sort(sumSubArr);
        int rs=0;

        for(int i=left-1;i<right;i++){
            rs=(rs+sumSubArr[i])%1_000_000_007;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,4};
//        int left=1;
//        int right=5;
        //Case 1 : Sai Case liên quan đến
        int arr[]=new int[]{1,2,3,4};
        int left=3;
        int right=4;
//        int arr[]=new int[]{1,2,3,4};
//        int left=1;
//        int right=10;
        System.out.println(rangeSum(arr, arr.length, left, right));
        System.out.println(rangeSumOptimizeTwoPointer(arr, arr.length, left, right));
        //Bài này tư duy như sau:
        //Cách 1,
        //1, Brute force
        //1.1, Với cách này ta có thể liệt kê tất cả các chuỗi con ---> Add vào List
        //--> Sau đó sort elements trong đó
        //1,2, Ta có thể dùng array ---> Tối ưu hơn.
        //
        //2, Tối ưu phương pháp brute force:
        //2.1, Để liệt kê hết số lượng sum sub array ta có 2 cách như sau:
        //- Ta dùng length (1 --> n) để liệt kê
        //+ Mỗi length ta sẽ có (n) start index
        //- Ta dùng start index để chia trường hợp (Có n start index ta có thể chia thế này cover all cases)
        //VD: {1,2,3,4}
        //+ index=0 : Có thể kết hợp với các cases (index=2,3,4)
        //+ Ta chỉ cần mỗi (i) --> (j: i --> n-1)
        //==> Mỗi bước ta tạo 1 sum=0 --> Cộng dần để lưu thông tin vào 1 array
        //==> Sau đó sẽ sort.
        //
        //Cách 2: Binary search
        //
    }
}
