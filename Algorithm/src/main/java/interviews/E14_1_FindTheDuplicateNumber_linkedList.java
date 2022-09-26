package interviews;

/*
Đây là bài mở rộng cùng dạng với rùa và thỏ
 */
public class E14_1_FindTheDuplicateNumber_linkedList {

    public static int findDuplicate(int[] nums) {
        //increase
        int left=0, right=nums.length-1;

        while (left<right){
            int mid=left+(right-left)/2;
            int count=0;
            System.out.println(nums[mid]);

            for(int i=mid;i<=right;i++){
                if(nums[i]==nums[mid]){
                    count++;
                }
                if(count>=2){
                    return nums[i];
                }
            }
            right=mid;
        }
        System.out.printf("%s %s,", left, right);
        return -1;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,4,2,2};
//        System.out.println(2^2^2);
        System.out.println(findDuplicate(arr));
    }
}
