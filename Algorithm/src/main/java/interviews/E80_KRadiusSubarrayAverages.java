package interviews;

import java.lang.reflect.Array;
import java.util.Arrays;

public class E80_KRadiusSubarrayAverages {

    public static int[] getAverages(int[] nums, int k) {
        int n=nums.length;
        int rs[]=new int[n];
        int low=0;
        int high=2*k;
        long sum=0;
        long prev=0;

        if(k==0){
            return nums;
        }
        if(2*k+1>nums.length){
            Arrays.fill(rs, -1);
            return rs;
        }

        for(int i=0;i<n&&i<=high;i++){
            sum+=nums[i];
            rs[i]=-1;
        }
        if(k<n){
            rs[k]= (int) (sum/(2*k+1));
            prev=sum;
        }

        for(int i=k+1;i+k<n;i++){
            prev=prev-nums[low++]+nums[++high];
            rs[i]= (int) (prev/(2*k+1));
        }
        for(int i=n-k;n-k>=0&&i<n;i++){
            rs[i]=-1;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{7,4,3,9,1,8,5,2,6};
//        int arr[]=new int[]{7};
//        int arr[]=new int[]{40527,53696,10730,66491,62141,83909,78635,18560};
//        int arr[]=new int[]{6643,3914,1918,9122,3503,4072,8633,5893,952,4377,1052,4513,3157,9894,9102,8734,9068,2121,4098,5039,5698,5224,2797,5440,1541,9419,9475,4465,5490,159,829,5701,314};
        int arr[]=new int[]{7,2,3,4,4};
//        int k=3;
//        int k=100;
//        int k=2;
//        int k=5;
        int k=2;
        System.out.println(getAverages(arr, k));
        System.out.println();
        //Current rank : 315,238
        //Bài này ta tư duy như sau:
        //1, Bài này là 1 kiểu slice window điển hình
        //prev là giá trị trước đó --> Ta sẽ tính giá trị sau dựa trên giá trị trước đó
        //2, Các case đặc biệt:
        //2.1, Vì ta tính giá trị trung bình --> khi chia ra + số trước tính theo số sau ==> Số có thể sẽ sai số
        //--> Ta cần phải dùng chính xác đến double
        //--> VD: Case 51 số: Wrong answer
        //2.2, Case overflow int
        //Test case 38: Khi số quá lớn, 40000 số mỗi số =100000 ==? Overflow
        //---> Cần phải để sum=long, prev(long)
        //3, Tối ưu:
        //3.1, Để giảm số vòng lặp thừa ta sẽ chia thành nhiều case đặc biệt để return trực tiếp mà không qua biến đổi
        //+ Case k==0 --> return nums
        //+ Case (2*k+1>n) --> return all -1
        //+ Case bình thường:
        //* Tính riêng 2 đầu =-1 : Để tránh if else trong for ==> Tăng tốc độ
        //* Tính phần ở giữa riểng

    }
}
