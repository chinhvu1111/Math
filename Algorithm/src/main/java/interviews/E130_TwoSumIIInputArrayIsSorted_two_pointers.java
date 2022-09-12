package interviews;

public class E130_TwoSumIIInputArrayIsSorted_two_pointers {

    public static int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        int[] rs =new int[]{-1,-1};

        while (left<=right){
            if(target > numbers[left]+ numbers[right]){
                left++;
            }else if(target < numbers[left]+ numbers[right]){
                right--;
            }else{
                rs[0]=left+1;
                rs[1]=right+1;
                break;
            }
        }
//        System.out.printf("%s %s\n", rs[0], rs[1]);
        return rs;
    }

    public static int[] twoSumOptimize(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;

        while (left<=right){
            if(target < numbers[left]+ numbers[right]){
                right--;
            }else if(target > numbers[left]+ numbers[right]){
                left++;
            }else{
//                rs[0]=left+1;
//                rs[1]=right+1;
//                break;
                return new int[]{left+1, right+1};
            }
        }
//        System.out.printf("%s %s\n", rs[0], rs[1]);
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,7,11,15};
//        int target=9;
        int arr[]=new int[]{2};
        int target=2;
//        int arr[]=new int[]{2,3,4};
//        int target=6;
        //Bài này tư duy như sau:
        //0, Các cases:
        //0.1, 1,2,5,7,10,15, target=22
        //+ Nếu target > nums[left] + nums[right] : left++
        //+ Nếu target <= nums[left] + nums[right] : right--
        //
        //1, Tối ưu bằng cách:
        //Bỏ đi phần gán array[]
        //arr[0]=left;
        //arr[1]=right;
        //==> Chuyển sang return new int[]{left+1, right+1};
        //==> Tăng tốc độ.
        System.out.println(twoSum(arr, target));
        System.out.println(twoSumOptimize(arr, target));
    }
}
