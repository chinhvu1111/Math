package interviews;

public class E108_FindMinimumInRotatedSortedArray_binary {

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
            low=mid+1;
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

    public static int findMidBinarySearchOptimize(int[] nums){
        int index=findSearchIndexOptimize(nums,0, nums.length-1);
        return nums[index];
    }

    public static int findSearchIndexOptimize(int nums[], int low, int high){
        if(low>=high-1){
            if(nums[low]>nums[high]){
                return high;
            }
            return low;
        }
        int mid= low + (high-low)/2;
        int left=nums[low];
        int right=nums[high];
        int midVal=nums[mid];

        if(midVal>right){
            low=mid+1;
        }else if(left<midVal&&midVal<right){
            high=mid-1;
        }else if(left>midVal&&midVal<right){
            high=mid;
        }
//        System.out.printf("%s %s\n", low, high);
//        else{
//            if(right>left){
//                return low;
//            }else{
//                return high;
//            }
//        }
        int rs=findSearchIndexOptimize(nums, low, high);
        return rs;
    }

    public static void main(String[] args){
//        int[] arr =new int[]{3,4,5,1,2};
        int[] arr =new int[]{11,13,15,17};
//        int[] arr =new int[]{2,1};
        //Case 3: Sai case left > midVal < right
        //Sửa return lại luôn mid ==> Vẫn sai
//        int[] arr =new int[]{3,1,2};
        //Case 4: Sai case
        //Sửa right=mid
//        int[] arr =new int[]{5,1,2,3,4};
        System.out.println(findMinSlow(arr));
        System.out.println(findMidBottomToTop(arr));
        System.out.println(findMidBinarySearch(arr));
        //Bài này tư duy như sau:
        //Cách 1: Linear
        //1, Cách phân chia trường hợp từ đầu :
        //(left, midVal, high)
        //+ left < midVal > right
        //VD: 3,4,(5),1,2
        //* low = mid +1
        //
        //+ left < midVal < right
        //VD: 3,(4),5 ( Ta đang ở 1 chuỗi tăng dần)
        //* low = mid - 1
        //
        //+ left > midVal > right
        //VD: 5,(4),3
        //* low = mid+1
        //
        //+ left > midVal < right
        //VD: 5,(4),3,1,2 (Không thể xảy ra)
        //---> Nó chỉ không xảy ra khi midVal không phải là kết quả
        //* Nếu nó là kết quả:
        //4,5,6,(1),2,3
        // 4 > 1 < 3
        //
        //2, Ta có thể tìm từ đầu (left --> right)
        //2.1, Khi tìm được phần tử thõa mãn :
        //+ left < x > right
        //--> Khi đó x sẽ là kết quả vì : (Chuỗi này luôn luôn rotate --> Tăng dần được)
        //- Các cases khác nếu chỉ :
        //+ x< right : --> Dịch sang bên trái : high=mid
        //+ x>= left : --> Dịch sang bên phải : low=mid
        //** --> Thực ra cái predicates được thu gọn bằng các predicates bên trên
        //
        //** CONCLUSION:
        //- Có thể có nhiều cách để custom 1 binary search, dựa trên:
        //+ Multiple predicates (Có thể là nhiều cases để chọn move left/right)
        //+ Dựa trên 1 predicates đặc biệt (Không phải chỉ <,>,= thông thường)
        //
        //3,
        //3.1, Sau nhiều mệnh đề if --> sẽ còn else, ta có thể tối ưu bằng cách gộp nó lại với mệnh đề ở đầu method:
        //VD:
        //========= CODE
        //if(low>=high){ ==> Chuyển nó thành : if(low>=high-1){
        //            if(nums[low]>nums[high]){
        //                return high;
        //            }
        //            return low;
        //        }
        //========= CODE
        //----> Gộp nó vào đây
        //
        //3.2, Gộp predicates:
        //
        //if(left<midVal&&midVal>right){
        //            low=mid+1;
        //        }else if(left<midVal&&midVal<right){
        //            high=mid-1;
        //        }else if(left>midVal&&midVal>right){
        //            low=mid+1;
        //        }else if(left>midVal&&midVal<right){
        //            high=mid;
        //        }
        //--->
        //VD: a>x&&b>x || a>x && b<=x (Tất nhiên phải giống assign low= mid+1 ===> Chỉ chuyển thành (a>x)
        //---> Speed up
        //VD: (left<midVal&&midVal>right) + (left>midVal&&midVal>right) ==> midVal>right
        //Sau khi summarize:
        //+ midVal>right : low=mid+1;
        //VD: 3,4,(5),1,[2]
        //--> Có rất nhiều trường hợp là (5)/(4)..., đứng ở trước (1)
        //Vì chuỗi này đang theo dạng (rotate ==> tăng dần)
        //
        //+ left<midVal && midVal<right : mid-1
        //VD: 4,(5),6,1,2,3 : có thể ở vị trí (5) ===> Vì ta cần chọn ra đoạn chứa value min (Case này không có min) ==> SAI.
        //VD: 4,5,6,[1],(2),[3] : có thể ở vị trí (2) ===> ( 1 < 2 < 3)
        //
        //+ left > midVal && midVal < right : mid-1
        //VD: 4,5,[6],1,(2),[3] ==> Có thể như (2) : 6 > 2 < 3
        //
        System.out.println(findMidBinarySearchOptimize(arr));
    }
}
