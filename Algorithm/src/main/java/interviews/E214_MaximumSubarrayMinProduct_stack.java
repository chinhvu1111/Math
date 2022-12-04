package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E214_MaximumSubarrayMinProduct_stack {

    public static int MOD=1000000007;

    public static int maxSumMinProduct(int[] nums) {
        int n=nums.length;
        long[] prefixSum=new long[n];
        prefixSum[0]=nums[0];

        for(int i=1;i<n;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i];
        }
        int[] left=new int[n];
        int[] right=new int[n];
        Deque<Integer> stack=new LinkedList<>();

        //- left[i] : là chỉ số bên trái của (i) gần nhất mà nhỏ hơn nums[i]
        //- right[i] : là chỉ số bên phải của (i) gần nhất mà nhỏ nums[i]
        for(int i=0;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peekLast()]>=nums[i]){
                stack.removeLast();
            }
            if(!stack.isEmpty()){
                left[i]=stack.peekLast();
            }else{
                left[i]=-1;
            }
            stack.addLast(i);
        }
        stack=new LinkedList<>();
        for(int i=n-1;i>=0;i--){
            while (!stack.isEmpty()&&nums[stack.peekLast()]>=nums[i]){
                stack.removeLast();
            }
            if(!stack.isEmpty()){
                right[i]=stack.peekLast();
            }else{
                right[i]=n;
            }
            stack.addLast(i);
        }
        long result=Integer.MIN_VALUE;
//        println(prefixSum);
//        println(left);
//        println(right);

        for(int i=0;i<n;i++){
            long sum;

            sum=prefixSum[right[i]-1];
            if(left[i]!=-1){
                sum=prefixSum[right[i]-1]-prefixSum[left[i]];
            }
//            if(result/nums[i]<sumRight-sumLeft){
//                result=((sumRight-sumLeft)*nums[i]);
//            }
            result=Math.max(result, sum*nums[i]);
        }
        return (int)result%MOD;
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s,", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr=new int[]{2,3,3,1,2};
        System.out.println(maxSumMinProduct(arr));
        //
        //** Đề bài
        //
        //
        //** Bài này tư duy như sau:
        //0, Chú ý:
        //- Lỗi cơ bản:
        //- (int)result%MOD; ==> SAI
        //+ (int)(result%MOD)
    }
}
