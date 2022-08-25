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

        while (low<high-1){
            int mid= low + (high-low)/2;

            //Với dạng bài kiểu này, cần phần tích các vị trí mid, target ngay từ đầu --> Rất nhiều cases xảy ra:
            //- 4,5,6,(7)(mid),8,1,[2](target) : ==> Case duy nhất low=mid+1 (Chỉ liệt kê điều kiện của case này thôi)
            //- 4,5,6,7,8,1,2(target),3 (mid)
            //- 4,[5](target),6,(7)(mid),8,1,2

            //- 4,5,6,(7)(mid),8,1,[2](target)
            //+ 7 > target && mid <= indexSmallest
            //target nằm right của mid
            //---> low =mid+1
            //- 4,[5](target),6,(7)(mid),8,1,2
            //---> Case này high = mid-1.
            //- 4,5,6,7,8,1,2(target),3 (mid)
            //+ 3 > target && mid>= indexSmallest
            //---> high=mid-1
            //+ nums[mid] >= target
            //+ nums[mid] >= target
            //===> SAI ==> Tư duy này cuối cùng --> Dẫn đến việc thiếu case ở smallest index.
            //** Đoạn này cần tư duy chắc:
            //nums[mid]>=target :
            //VD; 4,[5],(6),1,2,3
            //VD; 4,5,6,1,[2],(3)
            //--> Đều là high = mid + (-1/0)
            //** ===> Dùng target vẫn sai:
            //VD: 5,[1](target),2(mid),3,4
            //===> high=mid-1
            //===> nhưng target (=1) < (nums[low]=5)
            //Ở đây target >= nums[low] : high=mid-1;
            //+ Chỉ đúng khi:
            //4,[5](target),(6)(mid),1,2,3
            if(nums[mid]>=target){
                if(target==nums[mid]){
                    high=mid;
                } else if(indexSmallest>mid&&target<nums[low]){
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }else{
                //Các cases:
                //1 - 4,5,6,[7](target),8,1(mid),2 : ==> Case duy nhất high=mid-1 (Chỉ liệt kê điều kiện của case này thôi)
                //===> Luôn dùng midVal để so sánh (quyết định bên cho midValue) --> Như thế mới quyết định được (high/low)
                // Dùng (mid>=indexSmallest&&target>=nums[low])
                //===> Bị case 4(low),5(mid),[6](target),7(high)
                //===> mặc dù là (low = mid+1) do (low == smallestIndex) ===> SO SÁNH với HIGH (NGUYÊN LÝ SO SÁNH CÙNG BÊN <=)
                //* Vẫn bị trùng với case 2 ==> mid cần nằm bên (right) ==> (mid_value > low_value)
                //===> Thêm ( target>=nums[low]&&nums[mid]<nums[low] )
                //2 - 4,5,6,7,8,1,(2)(mid),[3] (target) : target < low_value && mid > smallest_index
                //3 - 4,(5)(mid),6,[7](target),8,1,2
                //4 - {4,5(mid),[6](target),7}

                //nums[mid] < target
                //(4),5,6,7,[8],1,2,(3)
                //target=8
                //+ mid chỉ có 2 trường hợp (left/ right) của index small
                //Case 1, VD: 4,5,6,7,8,1,[2](mid),(3)(target) : target > nums[mid]
                //--> low=mid+1
                //Case 2, VD: 4,5,[6](mid),(7)(target),8,1,2,3 : target > nums[mid]
                //--> low=mid+1 (** 1)
                //===> So sánh nums[low] ===> Thế nào đi nữa thì kết quả vẫn là (low=mid+1)
                //VD: Special case:
                //Case 3: VD { 7,1 (smallest index),2 }
                //
                //Ở đây gặp case đặc biệt có 2 element:
                //Case 4: {6,[7],(0)(mid),1,2}
                //==> ta chuyển sang tư duy (low<high-1)
//                if(target==nums[mid]){
//                    low=mid;
//                }else{
//                    low=mid+1;
//                }
                //* THIẾU CASE DẶC BIỆT :
                //- Khi di chuyển (mid, target) <=> (target, mid)
                //VD; 4,5,[6](target),1,(2)(mid),3
                //target > mid --> high=mid-1 (** 2)
                //===> Chốt lại cần so sánh với (left/right) <> Hoặc là ("Không cần")
                // (** 1) + (** 2) : việc chọn không phụ thuộc vào vị trí target (cùng nằm bên trái của smalleastIndex)
                //VẪN SAI:
                //{4,5(mid),[6](target),7}
                //
                // ==> phụ thuộc vào (mid)
                if(target==nums[mid]){
                    low=mid;
                }else if(mid>=indexSmallest&&target>=nums[low]&&nums[mid]<nums[low]&&indexSmallest!=low){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
                //Với trường hợp đứng ở (smallest index) ==> Ta cần phải (So sánh với left và right)
//                else if(target<nums[low]){
//                    low=mid+1;
//                }else{
//                    high=mid-1;
//                }
            }
        }
        if(nums[low]==target){
            return low;
        }
        if(nums[high]==target){
            return high;
        }
        return -1;
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
//        int arr[]=new int[]{4,5,6,7,8,1,2,3};
//        int target=8;
        //Case : Lỗi ở predicate cuối cùng
        //if((left<=value&&value>=nums[mid])||(left>=value&&value<=nums[mid])){
        // high=mid;
//        int arr[]=new int[]{5,1,2,3,4};
//        int target=1;
//        int arr[]=new int[]{4,5,6,7,0,1,2};
//        int target=2;
        int arr[]=new int[]{8,1,2,3,4,5,6,7};
        int target=6;
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
        //2.1, Lỗi sai có thể gây hiểu lầm + debug mãi không ra kết quả
        //---> Liên quan đến so sánh midVal với target:
        //+ Có rất nhiều trường hợp đặc --> So sánh mỗi midVal không thể chia được hết case ==> Ta cần phải kết hợp so sánh với (left, right)
        //
        System.out.println(searchOptimize(arr, target));
    }
}
