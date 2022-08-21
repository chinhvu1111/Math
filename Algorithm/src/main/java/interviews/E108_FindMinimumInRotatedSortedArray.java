package interviews;

public class E108_FindMinimumInRotatedSortedArray {

    public static int findMinSlow(int[] nums) {
        int rs=Integer.MIN_VALUE;
        int i=0;

        for (i=0;i<nums.length;i++) {
            if (rs < nums[i]) {
                rs = nums[i];
            }else {
                break;
            }
        }
        if(rs==nums[nums.length-1]){
            return nums[0];
        }
        return nums[i];
    }

    public static int findMidBottomToTop(int[] nums){
        int rs=nums[0];
        int i=0;

        for (i=nums.length-1;i>=0;i--) {
            if (rs >= nums[i]) {
                rs=nums[i];
            }else {
                break;
            }
        }
        if(rs==nums[0]){
            return nums[0];
        }
        return nums[i+1];
    }

    public static int findMidBinarySearch(int[] nums){
        int index=findSearchIndex(nums,0, nums.length-1);
        return nums[index];
    }

    public static int findSearchIndex(int nums[], int low, int high){
        if(low>=high){
            return low;
        }
        int mid= low + (high-low)/2;
        int left=nums[low];
        int right=nums[high];
        int midVal=nums[mid];

        if(left<midVal&&midVal>right){
            low=mid+1;
        }else if(left<midVal&&midVal<right){
            high=mid-1;
        }else if(left>midVal&&midVal>right){
            high=mid+1;
        }else if(left>midVal&&midVal<right){
            high=mid;
        }
        else{
            if(right>left){
                return low;
            }else{
                return high;
            }
        }
        int rs=findSearchIndex(nums, low, high);
        return rs;
    }

    public static void main(String[] args){
//        int[] arr =new int[]{3,4,5,1,2};
//        int[] arr =new int[]{11,13,15,17};
//        int[] arr =new int[]{2,1};
        //Case 3: Sai case left > midVal < right
        //Sửa return lại luôn mid ==> Vẫn sai
        int[] arr =new int[]{3,1,2};
        //Case 4: Sai case
        //Sửa right=mid
//        int[] arr =new int[]{5,1,2,3,4};
        System.out.println(findMinSlow(arr));
        System.out.println(findMidBottomToTop(arr));
        System.out.println(findMidBinarySearch(arr));
    }
}
