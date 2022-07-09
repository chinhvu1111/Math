package interviews;

public class E68_BinarySearch {

    public static int searchElementOld(int arr[], int value, int low, int high){
        int mid=(low+high)/2;
        int index=-1;

        if(low>=high-1){
            if(arr[low]==value){
                return low;
            }
            if(arr[high]==value){
                return high;
            }
            return -1;
        }
//        System.out.println(low+" "+high);

        if(value<arr[mid]){
            index=searchElementOld(arr, value, 0, mid-1);
        }else{
            index=searchElementOld(arr, value, mid, high);
        }
        return index;
    }

    public static int searchOld(int[] nums, int target) {
        return searchElementOld(nums, target, 0, nums.length-1);
    }

    public static int searchElement(int arr[], int value, int low, int high){
        int mid=(low+high)/2;
        int index=-1;

        if(low>high){
            return -1;
        }
//        System.out.println(low+" "+high);

        if(value<arr[mid]){
            index=searchElement(arr, value, 0, mid-1);
        }else if(value>arr[mid]){
            index=searchElement(arr, value, mid+1, high);
        }else {
            return mid;
        }
        return index;
    }

    public static int search(int[] nums, int target) {
        return searchElement(nums, target, 0, nums.length-1);
    }

    public static int seachArr(int[] nums, int target){
        int low=0;
        int high=nums.length-1;
        int index=-1;

        while (low<high){
            int mid=(low+high)/2;

            if(target<nums[mid]){
                high=mid-1;
            }else if(target>nums[mid]){
                low=mid+1;
            }else{
                index=mid;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{-1,0,3,5,9,12};
//        int arr[]=new int[]{-1,0,3,5,9,12};
//        System.out.println(search(arr, 9));
        //Bài này tư duy như sau:
        //1,
        // Bài này cần quy định return rule + quy định check conditions
        //VD:
        //Case 1: {1, 2}
        //Case 2: {1}
        //Case 3: {1, 2, 3}
        //1.1, Bài này có 2 cách tư duy cơ bản như sau:
        //+ Tư duy dạng if(low>=high-1){} : Vì ta cần cover case {1,2}
        //--> Vì chạy dạng (low=high-1) + searchElement(0, mid-1) / searchElement(mid, high)
        //--> Có thể xảy ra loop infinite.
        //==> Trong if(low>=high-1) { if(arr[low]==value, arr[high]==value) --> Cái này chỉ run 1 lần không ảnh hưởng đến tốc độ chung (100%)
        //+ Tư duy dạng if(low>high) --> Thì cần đổi lại phần chia range:
        //Format :
        // if(low>high) + searchElement(0, mid-1) + searchElement(mid+1, high) + else { return mid } .
        //2, Với tư duy này ta có thể dùng for loop để áp dụng tư duy tương tự
        //---> Chỉ là khi tìm index + break loop.
        System.out.println(search(arr, 9));
        System.out.println(seachArr(arr, 9));
    }
}
