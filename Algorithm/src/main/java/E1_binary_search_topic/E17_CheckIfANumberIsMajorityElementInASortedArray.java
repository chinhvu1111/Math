package E1_binary_search_topic;

public class E17_CheckIfANumberIsMajorityElementInASortedArray {

    public static boolean isMajorityElement(int[] nums, int target) {
        int n=nums.length;
        int low=0, high=n-1;
        int rightMostIdx=-1;
        int leftMostIdx=-1;

        if(nums[low]==target){
            leftMostIdx=low;
        }
        if(nums[high]==target){
            rightMostIdx=high;
        }
        if(rightMostIdx==-1){
            while(low<=high){
                int mid=low+(high-low)/2;

                //1,2,(2),3
                if(nums[mid]>target){
                    high=mid-1;
                }else if(nums[mid]<target){
                    low=mid+1;
                }else{
                    if(nums[mid]==target){
                        rightMostIdx=mid;
                    }
                    low=mid+1;
                }
            }
        }

        if(leftMostIdx==-1){
            low=0;
            high=n-1;
            while(low<=high){
                int mid=low+(high-low)/2;

                //Ex:
                //1,2,(2),2,3
                //5>3 ==> left
                if(nums[mid]>target){
                    high=mid-1;
                }else if(nums[mid]<target){
                    low=mid+1;
                }else{
                    if(nums[mid]==target){
                        leftMostIdx=mid;
                    }
                    high=mid-1;
                }
            }
        }
        if(rightMostIdx==-1&&leftMostIdx==-1){
            return false;
        }
        System.out.printf("%s %s\n", rightMostIdx, leftMostIdx);
        return rightMostIdx-leftMostIdx+1>(float)(n/2);
        //Ex:
        //1,2,2,2
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an integer array nums sorted in (non-decreasing order) and an integer target, return true if target is (a majority element), or false otherwise.
        //* A majority element in an array nums is an element that appears more than (nums.length / 2 times) in the array.
        //- Check thêm value có chiếm n/2 số lượng element trong array hay không
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= nums.length <= 1000
        //1 <= nums[i], target <= 10^9
        //nums is sorted in non-decreasing order.
        //
        //- Brainstorm
        //- Tìm ở giữa nếu thấy 5 ==> tìm tiếp <> return false.
        //==> Cứ không tìm thấy ==> return false.
        //- Ta cần so sánh nums[high]!=nums[low]
        // + nums[mid] so sánh với nums[0]
        // + nums[mid] so sánh với nums[n-1]
        //
        //Ex:
        //arr=[1,2,2,3]
        //Ex:
        //arr=[1,2,2,2,3]
        //- Ta cần cover case mà các giá trị nó nằm cả 2 bên của mid
        //- Idea là ta sẽ tính số lượng 5 ta có bằng recursion method
        //  + Ta cần tìm search 5 rightmost
        //  + Ta cần tìm search 5 leftmost
        //  ==> Sau đó trừ đi nhau là xong
        //
//        int[] arr={2,5,5};
//        int target=5;
//        int[] arr={2,4,5,5,5,5,5,6,6};
//        int target=5;
//        int[] arr={1,2};
//        int target=2;
        int[] arr={2};
        int target=3;
        System.out.println(isMajorityElement(arr, target));
        //#Reference:
        //1936. Add Minimum Number of Rungs
        //1886. Determine Whether Matrix Can Be Obtained By Rotation
        //532. K-diff Pairs in an Array
    }
}
