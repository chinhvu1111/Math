package interviews;

import java.util.ArrayList;
import java.util.List;

public class E22_SquaresOfASortedArray {

    public static int[] sortedSquares(int[] nums) {
        List<Integer> negativeNumbers=new ArrayList<>();
        List<Integer> postitiveNumbers=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                postitiveNumbers.add(nums[i]*nums[i]);
            }
            if(nums[nums.length-i-1]<0){
                negativeNumbers.add(nums[nums.length-i-1]*nums[nums.length-i-1]);
            }
        }
        int iLeft=0;
        int iRight=0;
        int n=postitiveNumbers.size();
        int m=negativeNumbers.size();
        int index=0;

        while (iLeft<n||iRight<m){
            if(iRight<m&&iLeft<n&&postitiveNumbers.get(iLeft)>negativeNumbers.get(iRight)){
                nums[index++]=negativeNumbers.get(iRight++);
            }else if(iLeft<n){
                nums[index++]=postitiveNumbers.get(iLeft++);
            }else{
                nums[index++]=negativeNumbers.get(iRight++);
            }
        }
        return nums;
    }

    public static int[] sortedSquaresOptimize(int[] nums) {
        int[] rs =new int[nums.length];
        int indexPositive=-1;
        int indexNegative=-1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                indexPositive=i;
                break;
            }else{
                indexNegative=i;
            }
        }
        //Positive
        int iLeft=indexPositive;

        if(iLeft==-1){
            iLeft=rs.length;
        }
        int iRight=indexNegative;
//        if(indexPositive>0){
//            iRight=indexPositive-1;
//        }
        int length=nums.length;

        int index=0;

        while (iLeft<length||iRight>=0){
            if(iRight>=0&&iLeft<length&&nums[iLeft]*nums[iLeft]>nums[iRight]*nums[iRight]){
                rs[index++]=nums[iRight]*nums[iRight];
                iRight--;
            }else if(iLeft<length){
                rs[index++]=nums[iLeft]*nums[iLeft];
                iLeft++;
            }else{
                rs[index++]=nums[iRight]*nums[iRight];
                iRight--;
            }
        }
        return rs;
    }

    public int[] sortedSquaresOptimized(int[] nums) {
        int[] res =new int[nums.length]; //Create new Array to return from function
        int last=nums.length-1; //pointer at End
        int start=0; // pointer at start
        int dlast=res.length-1; // pointer at last for resultant array
        while(start<=last)
        {
            if(nums[start]*nums[start]<nums[last]*nums[last])
            {
                res[dlast]=nums[last]*nums[last];
                last--;
                dlast--; }
            else
            {
                res[dlast]=nums[start]*nums[start];
                start++;
                dlast--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{-4,-1,0,3,10};
        //Case 1 : Basic case khi IndexOutofBound
//        int arr[]=new int[]{-1};
        int[] arr =new int[]{1};

        //** Đề bài:
        //- Cho 1 array tăng dần (Cả số âm) --> Trả lại dãy bình phương tăng dần.
        //
        //** Bài này tư duy như sau:
        //
        //Cách 1: Chưa tối ưu vì (ArrayList copy elements + có thể array được tạo ra bị thừa space)
        sortedSquares(arr);
        //1.1, Tư duy ở đây là (array negative (Đảo ngược thứ tự khi ^2) , positive elements (Giữ nguyên thứ tự khi ^2)
        //Lỗi sai:
        //1, while(iLeft<n||iRight<m) : Cẩn thận cần check both indexs khi viết while or
        //2 list có thể có 1 cái có số elements ít hơn --> Có thể gây ra lỗi IndexOutOfBound.
        //
        //Cách 2:
        //- Key : Cách này tìm middle của (negative, positive) elements ở mid of array.
        //
        //Case 1:
        //iRight (Negative) không tìm thấy
        //int arr1[]=new int[]{1};
        //Case 2:
        //iRight (Negative) không tìm thấy
        //int arr1[]=new int[]{-1};
        //Lỗi:
        //2,
        //Có thể bị lỗi liên quan đến:
        //2.1, IndexOutofBound liên quan đến -1 <0
        //while (iLeft<length||iRight>=0) : Đang xét iLeft<length --> nếu không tìm thấy thì gán iLeft=length luôn
        //VÌ if đang chỉ xét (iLeft<length) + else (iLeft < length)
        //** CẦN PHẢI CHECK INDEX "MỌI CHỖ"
        //Ta tư duy như sau:
        //2.2, Vì bài này liên quan đến (negative/ positive) --> Ta chỉ cần xét elements chỉ trên array thôi là được rồi.
        //==> Các vị trí sẽ có 2 loại index cho (+ / -)
        //- Âm (-) là tăng dần --> Bình phương (^2) là giảm dần ==> Ta cần iRight--;
        //- Dương (+) là tăng dần --> Bình phương (^2) là giảm dần ==> Ta cần iLeft++;
        //2.3, Sau đó ta merge 2 array ra 1 new array như bình thường.
        //
        //Cách 3:
        //- Key : Nếu quan sát kỹ hơn thì các số sau khi bình phương ^2 --> có dạng (Max1)...(min)....(Max2)
        //===> hội tụ vào giữa --> Nên ta chỉ cần chạy 2 pointers 2 đầu
        //Bài toán chuyển thành ===> Coi như là merge 2 arrays giảm dần.
        //** Chỉ cần gán index từ end --> start ==> Ra được array (RESULT).
        int[] arr1 =new int[]{-4,-1,0,3,10};
        sortedSquaresOptimize(arr1);
        System.out.println("");
    }
}
