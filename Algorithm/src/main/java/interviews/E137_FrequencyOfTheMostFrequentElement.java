package interviews;

import java.util.Arrays;
import java.util.stream.Collectors;

public class E137_FrequencyOfTheMostFrequentElement {

    public static int searchSmallestIndexEnoughOperations(int prefixSum[], int nums[],int i, int k){
        int left=0;
        int right=i;
        int rs=-1;
        int countOperationNeede;

        //- Tìm j sao cho (số operation bù vào từ j --> i) <=k
        //==> Nhưng mà số operation phải MAX
        while (left<=right){
            int mid=left+(right-left)/2;
            countOperationNeede=nums[i]*(i-mid+1) - (prefixSum[i+1]-prefixSum[mid]);

            if(countOperationNeede<=k){
                rs=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return rs;
    }

    public static int maxFrequency(int[] nums, int k) {
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int sum=0;
        Arrays.sort(nums);

        //#Đoạn này thiết kế prefix sum sao cho prexixSum[0]=0
        for(int i=1;i<=n;i++){
            sum+=nums[i-1];
            prefixSum[i]=sum;
        }
//        Arrays.stream(prefixSum).forEach(System.out::println);
        int rs=0;

        for(int i=0;i<n;i++){
            int indexStartOperation=searchSmallestIndexEnoughOperations(prefixSum, nums, i, k);

            if(indexStartOperation!=-1){
                rs=Math.max(rs, i-indexStartOperation+1);
            }
//            System.out.printf("%s %s %s,", i, indexStartOperation, nums[i]);
        }
//        System.out.println();

        return rs;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,4,8,13};
        int k=5;
//        int arr[]=new int[]{1,2,4};
//        int k=5;
//        int[] arr=new int[]{3,9,6};
//        int k=2;
        //
        //** Đề bài:
        //- Thêm lần lượt 1 cho các phần tử bất kỳ sao cho (max frequency) của element[i] (element nào đó) của array ==> Là MAX nhất
        //
        //** Tư duy như sau:
        //
        //1, Không biến được thành các phần tử mà không có trong array (VD: != element[i])
        //1.1, Brute force : Thử biến từng số
        //==> Chỉ có thể biến những số <= target (Giá trị muốn biến lúc đó)
        //1.2,
        //- Khi nghĩ đến xét từng số --> Ta sẽ hướng đến việc tìm index của phần tử xa nhất (Tức là phần tử MIN) --> Để có thể thu được số chữ số giống nhau
        //nhiều nhất
        //--> Với tư duy này ta sẽ dùng phương pháp binary search để tìm index của phần tử nhỏ nhất ===> mà bù ít nhất.
        //1.3,
        //- Quy luật tính bù
        //VD: 1(index=0), 2, 6, 6(i=3), 7
        //--> Giả sử biến thành 6
        //- Xét index=0 : Bù (6 * (3-0) - tổng từ ( 0 --> 3) ==> Cái này ta sẽ dùng prefix Sum để xử lý.
        System.out.println(maxFrequency(arr, k));
        //#Reference
        //- Find All Lonely Numbers in the Array
        //- Longest Nice Subarray
    }
}
