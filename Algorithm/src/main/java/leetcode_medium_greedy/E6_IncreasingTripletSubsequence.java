package leetcode_medium_greedy;

import java.util.ArrayList;
import java.util.List;

public class E6_IncreasingTripletSubsequence {

    public static boolean increasingTripletDP(int[] nums) {
        int n=nums.length;
        boolean dp[]=new boolean[n];

        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]&&dp[j]){
                    return true;
                }
                if(nums[i]>nums[j]){
                    dp[i]=true;
                }
            }
        }
        return false;
    }

    public static boolean increasingTripletC1(int[] nums) {
        int n=nums.length;
        Integer min=Integer.MAX_VALUE;
        List<Integer> validElement=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(nums[i]>min){
                validElement.add(nums[i]);
            }
            min=Math.min(nums[i], min);
        }
        min=Integer.MAX_VALUE;

        for(int i=0;i<validElement.size();i++){
            if(validElement.get(i)>min){
                return true;
            }
            min=Math.min(validElement.get(i), min);
        }
        return false;
    }

    public static boolean increasingTripletC2(int[] nums) {
        int n=nums.length;
        Integer small=Integer.MAX_VALUE;
        Integer big=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            int currentValue=nums[i];

            if(currentValue<small){
                small=currentValue;
            }else if(big>currentValue&&currentValue>small){
                big=currentValue;
            }else if(big<currentValue) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,1,5,0,4,6};
//        int arr[]=new int[]{20,100,10,12,5,13};
        //C1: Tư duy dynamic programming:
        //dp[i] : Chỉ các số mà ở đó tồn tại nums[i] đứng đằng trước thõa mãn : nums[i] < nums[j].
        //Nếu thỏa mãn nums[i]> num[j] && dp[j] (Tức là đã có số đằng trước <=> nums[j]> nums[k])
        //--> Complexity: O(sqrt(n))
        //C2: Tư duy dạng min.
        // i <j <k.
        //1, Ta cần tìm ra (j) trước --> Add các (j) vào 1 array list.
        //Ta chỉ so sánh (j) và giá trị min từ (j-1) --> 0 ==> Chắc chắn dp[j]=true (Không cần xét đến các giá trị khác)
        //--> Sau đó tìm k dưa trên cắc giá trị (j) có sẵn.
        //C3: Phương pháp : small < nums[i]&& big !=small &&big != init --> return true.
        //Để gộp big!=small&& big!=init --> big=Integer.MAX_VALUE
        //1, Chú ý ở đây không phải tư duy là max (SAI) --> big.
        // Vì cái ta cần là (big) : sẽ là trị max nhất mà trước nó > small
        //--> Như thế ta chỉ cần tìm thấy nums[i] >max --> return true.
//        int arr[]=new int[]{2,4,-2,-3};
//        int arr[]=new int[]{20,100,10,12,5,13};
        //Case 1: Bị sai case max+Integer.MIN_VALUE, min=max
//        int arr[]=new int[]{2,4,-2,-3};
        //Case 2:
        int arr[]=new int[]{1,1,-2,6};
        System.out.println(increasingTripletC2(arr));
    }
}
