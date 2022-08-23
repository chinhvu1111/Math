package interviews;

public class E109_SearchInRotatedSortedArray_binary {

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
            }else if((left<=value&&value>=nums[mid])||(left>=value&&value<=nums[mid])){
                high=mid;
            }else{
                return -1;
            }
        }
        int rs=findIndex(nums, value, low, high);
        return rs;
    }

    public static int searchOptimize(int[] nums, int target) {
        int low=0, high=nums.length-1;

        //[4],5,(6),7,0,1,[2]
        //[4],5,(6)
        //[6],(4),[5] ==> cái này low=mid+1
        //---> Không cần nums[mid]> nums[low]
        //---> Không cần nums[mid]> nums[high]
        while(low<high){
            int mid=low + (high-low)/2;

            if(nums[mid]>nums[high]){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        int indexSmallest=low;
        low=0;
        high=nums.length-1;

        while (low<high){
            int mid=low + (high-low)/2;

            if(nums[mid]>=target){
                if(mid<=indexSmallest){
                    high=mid;
                }else{
                    low=mid+1;
                }
            }else{
                if(mid<indexSmallest){
                    high=mid-1;
                }else{
                    low=mid;
                }
            }
        }
        return low;
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
//        int arr[]=new int[]{5,1,3};
//        int target=5;
        //Case : Lỗi khi 6 không nằm trong (low, high) ở case cuối cùng
//        int arr[]=new int[]{5,1,3};
//        int target=6;
        //Case : Lỗi khi 8 nằm trong (low, high) ===> Mình bị sai 1 chút ở case cuối cùng
//        int arr[]=new int[]{4,5,6,7,8,1,2,3};
//        int target=8;
        int arr[]=new int[]{4,5,6,7,8,1,2,3};
        int target=8;
        //Case : Lỗi ở predicate cuối cùng
        //if((left<=value&&value>=nums[mid])||(left>=value&&value<=nums[mid])){
        // high=mid;
//        int arr[]=new int[]{5,1,2,3,4};
//        int target=1;
        System.out.println(search(arr, target));
        //Bài này tư duy như sau:
        //1, Ở đây ta dùng custom binary search + break conditions để scan hết các điều kiện
        //*** CHÚ Ý : Khi liệt kê all cases ===> Ta chỉ được phép liệt kê nằm trong (low, high)
        //
        //1.1, Các lưu ý khi break các cases như sau:
        //+ [4],5,6,(7),0,1,[2] : left < midVal > right
        //+ [4],(5),6,[7],0,1,2 : left < midVal < right
        //+ 4,5,[6],7,0,(1),[2] : left > midVal < right
        //Câu hỏi : left > midVal > right có xảy ra không
        //---> Vì là dãy tăng dần ===> Không có case ntn
        //VD: 4,5,[6],7,0,(1),[2]
        //==> Không thể xảy ra được.
        //===> Theo các tư duy này thì ta sẽ chia các cases lần lượt dựa trên từng trường hợp:
        //1.2, Các cases như sau:
        //- [4],5,(6),7,0,1,[2]
        //+ left <= value <= midVal : VD: value=5
        //+ value <= right : VD: value = 1
        //+ value >= midVal : VD: value = 7
        //===> Với 3 cases như thế này ta đã (COVER ALL CASES) có thể
        //
        //- [4],(5),6,[7],0,1,2
        //+ mid <= midVal <= right
        //+ left <= value <= midVal
        //==> Chỉ có duy nhất 1 case
        //
        //- 4,5,[6],7,0,(1),[2]
        //+ left <= midVal ( 0 ... 1)
        //+ value >= left (7 ... 0)
        //+ left >= midVal <= right
        //1.3, Chú ý 1 số trường hợp sẽ return -1 (LUÔN) nếu không có cases nào trong (low --> high) thỏa mãn
        //
        //
        //2, Ta sẽ tối giản bài toán bên trên:
        //
        System.out.println(searchOptimize(arr, target));
    }
}
