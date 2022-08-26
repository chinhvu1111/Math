package interviews;

public class E110_SearchInRotatedSortedArrayII_binary {

    public static boolean search(int[] nums, int target) {
        int low=0, high=0, length=0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]!=nums[high]){
                nums[++high]=nums[i];
            }
        }
        length=high;

        while(low<high){
            int mid=low + (high-low)/2;
//            System.out.printf("%s %s %s\n", low, mid, high);
            //low, small, mid, high
            //low, mid, small, high
            if(nums[mid]>nums[high]){
                low=mid+1;
            }else{
                high=mid;
            }
//            System.out.printf("%s %s %s\n\n", low, mid, high);
        }
        int indexSmallest=low;
        low=0;
        high=length;

        //Target index=13
        while (low < high-1){
            int mid= low + (high-low)/2;
//            System.out.printf("%s %s %s %s\n",nums[mid]>=target, low, mid, high);

            if(nums[mid]>=target){
                if(target==nums[mid]){
                    high=mid;
                } else if(indexSmallest>mid&&target<nums[low]){
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }else{
                if(target==nums[mid]){
                    low=mid;
                }
                //Case 1:
                //1(low), 1,1,1,1,1,1,1,1,1,1,1,1, 2(13), 1(14),1,1,1, 1(high)
                //Case 2:
                //[1,0,1,1,1]
                //0
                else if(mid>=indexSmallest&&target>=nums[low]&&nums[mid]<=nums[low]&&indexSmallest!=low){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
//            System.out.printf("%s %s\n\n", low, high);
        }
        if(nums[low]==target){
            return true;
        }
        if(nums[high]==target){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
//        int target=2;
//        int arr[]=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
//        int target=2;
        int arr[]=new int[]{1,0,1,1,1};
        int target=0;

        System.out.println(search(arr, target));
        //Bài này tư duy như sau:
        //1,
        //2,
        //3, Liên quan đến kỹ năng debug (print):
        //3.1, % % % 2 lần đầu 1 for và kết thúc 1 for
        //3.2, Ngăn cách các lần với nhau bởi dấu : \n\n
        //
        //4,
        //4.1,Bài tập làm thêm slice window:
        //https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
        //4.2, Matrix
        //https://leetcode.com/problems/spiral-matrix-iv/

    }
}
