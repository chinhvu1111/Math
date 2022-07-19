package interviews;

import java.util.Arrays;

public class E85_MinimumIncrementToMakeArrayUnique {

    public static int minIncrementForUnique(int[] nums) {
        if(nums.length==1){
            return 0;
        }

        Arrays.sort(nums);

        int left=0;
        int right=1;
        int rs=0;

        while (right<nums.length){

            if (nums[left]>=nums[right]){
                rs+=nums[left]+1-nums[right];
                nums[right]=nums[left]+1;
            }
            left=right;
            right++;
        }

        return rs;
    }

    public int minIncrementForUniqueOptimize(int[] A) {
        int[] count = new int[100001];
        int result = 0;
        int max = 0;
        for (int a : A) {
            count[a]++;
            max = Math.max(max, a);
        }
        for (int i = 0; i < max; i++) {
            if (count[i] <= 1) {
                continue;
            }
            int diff = count[i] - 1;
            result += diff;
            count[i + 1] += diff;
            count[i] = 1;
        }
        int diff = count[max] - 1;
        result += (1 + diff) * diff / 2;
        return result;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{3,2,1,2,1,7};
//        int arr[]=new int[]{1,2,2};
        System.out.println(minIncrementForUnique(arr));
        //Bài này tư duy như sau:
        //1, Vẫn là tư duy sắp xếp + cộng thêm +1
        //1.1, Ở đây ta sẽ chỉ xét 2 value cạnh nhau là:
        //nums[left] và nums[right]
        //+ If(nums[left] > numss[right]) rs+=nums[left]+1-nums[right]
        //+ next (left, right), left=right, right++
        //2, Tối ưu
        //
    }
}
