package interviews;

import java.util.Arrays;

public class E170_SingleNumberII {

    public static int singleNumber(int[] nums) {

        return -1;
    }

    public static int singleNumberSort(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;

        for(int i=0;i<n;i+=2){
//            System.out.printf("%s %s, ", nums[i], nums[i+2]);
            if(i+2<n&&nums[i]==nums[i+2]){
                i++;
            }else {
                return nums[i];
            }
        }
//        System.out.println(i);
        return -1;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,2,3,2};
        int arr[]=new int[]{0,1,0,1,0,1,99};
//        int arr[]=new int[]{0,1,0,1,0,1,99,200,200,200};
        System.out.println(singleNumber(arr));
//        System.out.println(1<<1);
        System.out.println(singleNumberSort(arr));
        //2,2,3,2
        //
        //
        //1, Có các cách suy nghĩ như sau:
        //1.1, Dùng index 1>>index --> Thay ở đây index lung tung
        //1.2, Dùng nums[i] là số:
        //
        //VD: 2,2,3,2
        //Ta có thể cân dịch không?
        //1<< nums[i] -2^31 <= nums[i] <= 2^31 - 1
        //--> Không thể dịch kiểu này được
        //
        //2, Ta quan tâm đến số lượng chữ số có thể xuất hiện
        //- 3 chữ số
        //- 1 chữ số
        //2.1, Tip trick ở đây là gì?
        //
        //VD: 2,2,3,2
    }
}
