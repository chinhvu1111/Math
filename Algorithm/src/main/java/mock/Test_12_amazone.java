package mock;

import java.util.Arrays;

public class Test_12_amazone {

    public static int[] productExceptSelf(int[] nums) {
        int productAll=1;
        int productNonZero=1;
        int n=nums.length;
        int numZero=0;

        for (int num : nums) {
            if (num == 0) {
                numZero++;
            } else {
                productNonZero *= num;
            }
            productAll *= num;
        }
        if (numZero==0) {
            for(int i=0;i<n;i++){
                nums[i]= (productAll/nums[i]);
            }
        }else if (numZero==1) {
            for(int i=0;i<n;i++){
                if(nums[i]==0){
                    nums[i]= productNonZero;
                }else{
                    nums[i]=0;
                }
            }
        }else if(numZero>1){
            Arrays.fill(nums, 0);
        }
        return nums;
    }

    public static int[] productExceptSelfMethod2(int[] nums) {
        int n=nums.length;
        int[] answer=new int[n];
        answer[0]=1;

        for(int i=1;i<n;i++){
            answer[i]=nums[i-1]*answer[i-1];
        }
        int right=1;

        for(int i=n-1;i>=0;i--){
            answer[i]=answer[i]*right;
            right*=nums[i];
        }
        return answer;
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s, ", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums={-1,1,0,-3,3};
        int[] rs=null;
//        rs=productExceptSelf(nums);
        rs=productExceptSelfMethod2(nums);
        println(rs);
        //** Đề bài:
        //- Tính tích tại ví trí (i) = product all digits except gía trị tại (i)
        //
        //** Tư duy
        //1.
        //1.1, Tư duy dựa trên số chữ số 0
        //- Bài này cần chú ý các cases nums[i]==0 nhiều lần.
        //+ Tính số chữ số =0
        //+ number of zero number==1 : Tức là chỉ cần product cho all nums !=0
        //All các vị ==0 trừ --> Đến số num[i]==0 --> Số đó result = product all các giá trị còn lại.
        //+ number of zero number>1 : Tất cả các gía trị xét đến ==0 hết.
        //
        //1.2, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(1)
        //
        //1.3,
        //- Ta sẽ tạo answer array mới
        //{-1,1,0,-3,3}
        //{-1,-1,0,0,0} : nhân từ left to right
        //{ 0,0,0,9,3} : nhân từ right to left
        //--> Làm như thế này để tránh giá trị nums[i]==0.
        //
        //#Reference:
        //239. Sliding Window Maximum
        //265. Paint House II
        //2163. Minimum Difference in Sums After Removal of Elements
    }
}
