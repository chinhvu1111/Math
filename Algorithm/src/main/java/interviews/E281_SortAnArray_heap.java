package interviews;

public class E281_SortAnArray_heap {
    public static int[] sortArray(int[] nums) {
        //
        int n=nums.length;

        for(int i=n/2-1;i>=0;i--){
            max_heapify(nums, n, i);
        }
        for(int i=n-1;i>=0;i--){
            int temp=nums[i];
            nums[i]=nums[0];
            nums[0]=temp;
            max_heapify(nums, i, 0);
        }
        return nums;
    }


    public static void max_heapify(int[] nums, int n, int i){
        int low=2*i+1;
        int high=2*i+2;
        int largest=i;

        if(low<n&&nums[low]>nums[i]){
            largest=low;
        }
        if(high<n&&nums[high]>nums[largest]){
            largest=high;
        }
        if(largest!=i){
            int temp=nums[i];
            nums[i]=nums[largest];
            nums[largest]=temp;
            //Cần swap tiếp để có thể tìm lại max value nhất trong current branch
            max_heapify(nums, n, largest);
        }
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.printf("%s, ", j);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 };
        int[] rs=sortArray(arr);
        for (int r : rs) {
            System.out.printf("%s, ", r);
        }
        //** Đề bài:
        //- Sắp xếp array theo chiều tăng dần
        //- Smallest space
        //
        //** Bài này tư duy như sau:
        //1. Tư duy như sau:
        //1.1,
        //- Heapify:
        //+ Process là create data structure từ binary tree --> Sử dụng array.
        //+ Nó được tạo Min-heap và Max-heap.
        //+ Start từ last index của no-leaf node sở hữu index (n/2-1)
        //
        //1.2, Build 1 cái heapify
        //Example-1:
        //{1,2,5,6,2,3,7}
        //                  1
        //             2         5
        //          6     2   3     7
        //- 1 <-> 5
        //- {5,2,1,6,2,3,7}
        //                  5
        //             2         1
        //          6     2   3     7
        //
        //- 5 <-> 7
        //- {5,2,7,6,2,3,1}
        //                  5
        //             2         1
        //          6     2   3     7
        //
        //- 2 <-> 6
        //- {5,6,1,2,2,3,7}
        //                  5
        //             6         1
        //          2     2   3     7
        //
        //- 1 <-> 7
        //- {5,6,1,2,2,3,7}
        //                  5
        //             6         7
        //          2     2   3     1
        //
        //- 1 <-> 7
        //- {5,6,1,2,2,3,7}
        //                  5
        //             6         7
        //          2     2   3     1
        //- Ta thấy rằng nếu swap mà không có recursion thì giá trị của nó
        //sẽ không được còn đúng nữa.
        //
        //Example-2:
        //Heapify 3: First Swap 3 and 17, again swap 3 and 15.
        //
        //                  1
        //             /        \
        //          17           13
        //       /    \         /  \
        //      9      15     5    10
        //     / \    /  \
        //   4   8  3     6
        //
        //Heapify 1: First Swap 1 and 17, again swap 1 and 15, finally swap 1 and 6.
        //- Việc swap 1 <-> 17
        //
        //                  17
        //              /       \
        //          15           13
        //         /    \      /    \
        //       9      6    5      10
        //      / \    /  \
        //    4   8  3     1
        //--> 17,15,13,9,6,5,10,(4,8),3,1
        //--> Ta thấy max heap có tính chất là max node của từng branches.
        //+ max(left)=15
        //+ max(right)=13
        //- Các value liên hệ giữa 2 branches hoàn là không liên quan đến nhau --> VD: 4<8 như ở trên.
        //
        //1.2, Cần sort theo chiều tăng dần
        //- Cần swap (i) chạy từ (n-1 -->0) với j=0
        //+ Mục đích là để swap phần tử nhỏ nhất ở left branch ==> root
        //+ Sau đó heapify cho (new root) + left branch + right branch ==> Để tránh case là
        //            10
        //         8     (5)
        //      4   (6)     1
        //- root chắc chắn là phần tử max value
        //+ (Khi lấy từ phần tử ra --> 5 sẽ không phải là phần tử lớn thứ 2 ==> Ta cần lấy smallest của left branch là 6) ===> LINH TINH
        //- Ta swap phần tử cuối --> Ta muốn lấy array tăng dần + lấy last (Sẽ giảm đều height của binary tree)
        //+ Do root luôn max nhất
        //- Sau đó heapify để tìm MAX nhất ở root.
        //
        //==> Thực hiện heapify lại
        //+ swap(10,6)
        //            6
        //         8      (5)
        //      4    [10]       1
        //- heapify
        //            6
        //         8     (5)
        //      4    10       1
        //
        //            8
        //         6     (5)
        //      4   [10]        1
        //==>
        //             8
        //         6      (5)
        //      4   [10]        1
        //
        //1.3, NOTE:
        //- Để build được heapify ta chỉ cần heapify từ dưới lên cho all non-leaf nodes
        //+ Số node = n/2-1
        //- Chiến lực build tìm max( root, left, right) từ dưới lên
        //+ Ta sẽ thu được max heap : với right là value lớn nhất của all nodes bên right <>.
        //
        //1.4, Complexity:
        //- Time : nLog(n)
        //- Space : log(n) (stack)
        //
        //#Reference:
        //913. Cat and Mouse
        //1122. Relative Sort Array
        //1913. Maximum Product Difference Between Two Pairs
        //1966. Binary Searchable Numbers in an Unsorted Array
    }
}
