package interviews;

import java.util.Arrays;

public class E93_MissingNumber {

    public static int missingNumberWrong(int[] nums) {
        int rs=-1;
        boolean isEnoughtN=false;

        for(int i=0;i<nums.length;i++){
            int value=Math.abs(nums[i]);

            if(value!=nums.length&&nums[value]>=0){
                nums[value]*=-1;
            }else if(value==nums.length){
                isEnoughtN=true;
            }
        }
//        if(!isEnoughtZero){
//            return 0;
//        }
        if(!isEnoughtN){
            return nums.length;
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i]>0){
                return i;
            }
        }
        return rs;
    }

    public static int missingNumber(int[] nums) {
        int rs=-1;
        boolean isEnoughtN=false;
        int n=nums.length;

        for(int i=0;i<n;i++){
            nums[i]++;
        }
        for(int i=0;i<nums.length;i++){
            int value=Math.abs(nums[i]);

            if((value-1)==nums.length){
                isEnoughtN=true;
            }else if(nums[value-1]>=0){
                nums[value-1]*=-1;
            }
        }
//        if(!isEnoughtZero){
//            return 0;
//        }
        if(!isEnoughtN){
            return nums.length;
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                return i;
            }
        }
        return rs;
    }

    public int missingNumberSearch(int[] nums) { //binary search
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid= (left + right)/2;
        while(left<right){
            mid = (left + right)/2;
            if(nums[mid]>mid) right = mid;
            else left = mid+1;
        }
        return left;
    }

    public int missingNumberXOR(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    public static int missingNumberOptimize(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;

        return (nums.length * (nums.length + 1) )/ 2 - sum;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{9,6,4,2,3,5,7,0,1};
//        int arr[]=new int[]{3,0,1};
//        int arr[]=new int[]{1, 0};
//        int arr[]=new int[]{0, 2};
//        int arr[]=new int[]{2, 0};
        int arr[]=new int[]{1};
//        System.out.println(missingNumberWrong(arr));
        System.out.println(missingNumber(arr));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Bài này tương tự như các bài trước đó chỉ khó hơn ở 1 vài điểm sau:
        //1.1, Ở đây tồn tại value = 0
        //--> Tức là điều kiện check(nums[i]<0) return i
        //--> Có thể sẽ không còn đúng vì:
        //+ <0 : --> Để xác định 1 số đó đã tồn tại rồi
        //+ >0 : --> Để xác định 1 số đó chưa tồn tại
        //=0 : --> Không xác định được việc gì
        //0*-1=0 ==> Việc ta check =0 (Không kết luận được)
        //1.2, Tồn tại giá trị N == length() (exceed arrays' length())
        //--> Cần phải (xét riêng) ra với N==Length() : Nếu tồn tại thì : isEnoughtN=true
        //
        //2, SOLUTION:
        //Tăng tất cả các giá trị cùa nums[i]=nums[i]+1
        //--> Để có thể giữ tính chất ĐỐI XỨNG SỐ.
        //3, Làm cái dạng này cần phải tư duy rõ ràng ra:
        //3.1, nums[value]*=-1 : Để lưu lại trace.
        //value ở đây là value của từng elements trong nums.
        //3.2, Phần check missing là riêng 1<=x<=n
        //nums[i]>0 --> Ko cần -1 dùng để check trực tiếp đã tồn tại hay chưa
        //
        //Cách 2:
        //1, Tính tổng all sum
        //Tính tổng all = n*(n+1)/2 - sum
        //--> Vì chỉ có 1 số duy nhất nên dùng sum được.
        //Cách 3:
        //1, Ta có thể dùng binrary search
        //--> Để tìm được index cần.
        //Cách 4:
        //1, Dùng phương pháp xor
        //CT:
        //+ a XOR a = 0
        //+ a XOR 0 = a
        //(0--> n) xor (nums[i] (i: 0--> n)
        //==> Ra được số thiếu.
        //
    }
}
