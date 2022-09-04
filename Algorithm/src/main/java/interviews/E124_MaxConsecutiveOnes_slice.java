package interviews;

public class E124_MaxConsecutiveOnes_slice {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int countConsecutive=0;
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            if(nums[i]==1){
                countConsecutive++;
            }else{
                countConsecutive=0;
            }
            rs=Math.max(rs, countConsecutive);
        }
        return rs;
    }

    public static int findMaxConsecutiveOnesOptimize(int[] nums) {
        int start=-1;
        int n=nums.length;
        int rs=0;
        int i;

        //- Chỉ có cách tối ưu cho việc ít check điều kiện hơn thôi ==> (Giảm phần check max đi)
        //==> Giảm số lần check max đi (Chỉ check max khi cần)
        for(i=0;i<n;i++){
            if(nums[i]==0){
                rs=Math.max(rs, i-start-1);
//                System.out.printf("%s %s\n", start, i);
                start=i;
            }
        }
        rs=Math.max(rs, i-start-1);
        return rs;
    }

    public static void main(String[] args) {
        //Case 1: Với suffix là toàn số 1111 -> i =n-1
        //--> Nếu dùng chỉ if(nums[i]==0) max=.... ==> Bị thiếu mất cases cuối cùng.
//        int[] arr=new int[]{1,1,0,1,1,1};
//        int[] arr=new int[]{1,0,1,1,0,1};
        //Case 2: Nếu prefix toàn là số 1111 ==> Case đầu tiên sẽ bị tính sai khi start=0
        //+ start=0 : result =1 (Sai)
        //--> Start=-1 --> (result = 2) mới đúng.
        int[] arr=new int[]{1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(arr));
        System.out.println(findMaxConsecutiveOnesOptimize(arr));
    }
}
