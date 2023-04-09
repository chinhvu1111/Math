package mock;

public class Test_3_amazone {

    public static int getSmallestValueIndex(int[] nums, int low, int high){
        if(low==high){
            return low;
        }
        int mid=low+(high-low)/2;

//        if(mid==0){
//            return mid;
//        }
//        System.out.printf("%s %s %s\n", low, mid, high);

        if(mid<nums.length-1&&nums[mid]>nums[mid+1]){
            return mid+1;
        }else if(nums[mid]<nums[high]){
            return getSmallestValueIndex(nums, low, mid);
        }else{
            return getSmallestValueIndex(nums, mid+1, high);
        }
    }

    public static int searchIndex(int[] nums, int low, int high, int target){
        if(low>=high-1){
            if(low>=0&&nums[low]==target){
                return low;
            }
            if(high>=0&&nums[high]==target){
                return high;
            }
            return -1;
        }
        int mid=low+(high-low)/2;

        if(nums[mid]>target){
            high=mid-1;
        }else{
            low=mid;
        }
        return searchIndex(nums, low, high, target);
    }

    public static int search(int[] nums, int target) {
        if(nums.length==1){
            return nums[0]==target?0:-1;
        }
        int indexSmallestValue=getSmallestValueIndex(nums, 0, nums.length-1);
//        System.out.println(indexSmallestValue);
        int index;

        if(nums[0]>target||indexSmallestValue==0){
            index=searchIndex(nums, indexSmallestValue, nums.length-1, target);
        }else{
            index=searchIndex(nums, 0, indexSmallestValue-1, target);
        }
        return index;
    }

    public static void main(String[] args) {
//        int[] nums = {4,5,6,7,0,1,2};
//        int target = 0;
//        int[] nums = {1};
//        int target = 0;
//        int[] nums = {1,3};
//        int target = 0;
//        int[] nums = {1,3};
//        int target = 3;
        int[] nums = {5,1,3};
        int target = 5;
//        int[] nums = {3,4,5,6,1,2};
//        int target = 2;
        System.out.println(search(nums, target));
        //** Đề bài:
        //- Array tăng dần + distinct
        //- [0,1,2,(4),5,6,7] : rotated pivot index 3 --> [4,5,6,7,0,1,2]
        //- target --> Tìm index của nó trong nums.
        //- Array sau khi rotate sẽ dạng trends:
        //+ Tăng --> Giảm
        //
        //1.
        //1.0, Idea
        //Có 2 cách tư duy:
        //- Chuyển đổi bài toán --> Chia nhỏ ra
        //- Tìm đìm giữa --> Search 1 bên.
        //
        //1.1,
        //nums[]=[4,5,6,7,0,1,2]
        //+ target=0 ==> Xác định target lớn thứ mấy trong array là được
        //VD:
        // [4,5,6,7,0,1,2]
        // 0,1,2,3,(4),5,6
        //+ Nếu search truyền thống kiểu (low, high) : Có thể sẽ phức tạp
        //+ Chuỗi này vẫn là 1 chuỗi increase.
        //+ Rotate từ right --> left ==> index giảm.
        //+ Để không phức tạp --> Mình sẽ coi (mid-1) sẽ được reset nếu <0
        //VD:
        //[4,5,6,7,0,1,2]
        //+ low=0, high=6
        //+ mid=3, value=7 --> 0 <7 => left
        //--> low=0, high=3 ==> (low=0, high=0)
        //
        //1.2, Bài này tư duy như sau:
        //- Bài này ta vẫn cần dùng điểm rotate tức là điểm value có dạng:
        //3,4,5,6,(0),1,2
        //+ 0 : index = 4.
        //
        //- Ta vẫn cần phải vin vào điều kiện để tìm nó nằm bên phải hay trái:
        //VD:
        //3,4,5,6,0,(1),2
        //VD:
        //Case 1:
        //- tìm 1
        //+ 3>1
        //+ 1 > smallest value --> Nó nằm right của smallest index
        //Case 2:
        //- tìm 4
        //+ 3<4
        //+ 1 < smallest value --> Nó nằm left của smallest index
        //
        //1.3,
        //- Tìm index của phần tử smallest trong array
        //VD:
        //4,5,6,7,0,1,2
        //+ nums[mid] > nums[mid+1] : return mid+1;
        // 4,5,(6),1,3
        //+ nums[mid] < nums[mid+1] && nums[mid] < nums[high] : min nằm ở bên left (low và mid)
        //+ nums[mid] < nums[mid+1] && nums[mid] >= nums[high] : min nằm ở bên right (mid+1, high)
        //- Return khi (low==high) return low;
        //
        //1.4, Complexity:
        //- Time complexity : Log(n)
        //- Space complexity: log(n)
        //#Reference:
        //Search in Rotated Sorted Array II
        //Find Minimum in Rotated Sorted Array
        //34. Find First and Last Position of Element in Sorted Array
        //2137. Pour Water Between Buckets to Make Water Levels Equal
        //
    }
}
