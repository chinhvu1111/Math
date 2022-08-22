package interviews;

public class E109_SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int index=findIndex(nums, target, 0, nums.length-1);

        if(index==-1){
            return -1;
        }
        return index;
    }

    public static int findIndex(int nums[], int value, int low, int high){
        if(low>=high-1){
            if(nums[low]==value){
                return low;
            }
            if(nums[high]==value){
                return high;
            }
            return -1;
        }
        int mid=low + (high-low)/2;
        int left=nums[low];
        int right=nums[high];

        //Nếu đã bị giới hạn bới (low, high) ==> Chỉ được search trong phạm vi đó thôi
        //[4],5,6,(7),0,1,[2]
        if(nums[mid]>right){
            if(value>=left&&value<=nums[mid]){
                high=mid;
            }else if(value<=right||value>=nums[mid]){
                low=mid;
            }else{
                return -1;
            }
        }
        //[4],(5),6,[7],0,1,2
        else if(left<nums[mid]&&nums[mid]<right){
            if(value>nums[mid]){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        //4,5,[6],7,0,(1),[2]
        else if(left>nums[mid]&&nums[mid]<right){
            //Chỗ này nếu so sánh với nums[mid] --> Không có nghĩa gì cả
            //Vì 2 bên của mid > nums[mid]
            if(value>=nums[mid]&&value<=right){
                low=mid;
            }else if(left<=value&&value<=nums[mid]){
                high=mid;
            }
        }
        int rs=findIndex(nums, value, low, high);
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{4,5,6,7,0,1,2};
//        int target=0;
//        int arr[]=new int[]{4,5,6,7,0,1,2};
//        int target=5;
//        int arr[]=new int[]{4,5,6,7,0,1,2};
//        int target=7;
//        int arr[]=new int[]{4,5,6,7};
//        int target=5;
//        int arr[]=new int[]{4,5,6,7};
//        int target=6;
//        int arr[]=new int[]{4,5,6,7,1,2};
//        int target=2;
//        int arr[]=new int[]{4,5,6,7,1,2};
//        int target=3;
        int arr[]=new int[]{5,1,3};
        int target=5;
        System.out.println(search(arr, target));
    }
}
