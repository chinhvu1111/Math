package interviews;

public class E71_SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        int rs=search(nums, 0, nums.length-1, target);

        return rs;
    }

    public static int search(int nums[], int low, int high, int target){
        if(low>high){
            return low;
        }

        int mid=low +(high-low)/2;
        int index=-1;
        int currentValue=nums[mid];

        if(currentValue<target){
            index=search(nums, mid+1, high, target);
        }else if(currentValue>target){
            index=search(nums, low, high-1, target);
        }else{
            return mid;
        }
        return index;
    }

    public static int searchLinear(int nums[], int target){
        int index=-1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]<=target){
                index=i;
            }else{
                break;
            }
        }
        if(index!=-1&&nums[index]==target){
            return index;
        }
        return index+1;
    }

    public static int searchLinearOptimize(int nums[], int target){
        int i=0;
        for(i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,5,6};
//        int target=5;
//        int target=2;
        int target=7;
        System.out.println(searchInsert(arr, target));
        //Bài này tư duy như sau:
        //1, Dạng này không nhất thiết phải search theo binary --> Ta hoàn toàn có thể Search theo linear time.
        //2, Chú ý các cách tối ưu như sau:
        //2.1, Thủ thuật tối ưu điều kiện liên quan đến các logic <, >, =
        //Yêu câu: Tìm index có thể insert <=> (i)
        // <=> ( arr[i] <= target )
        //+ if(arr[i]==target) { return (i)}
        //+ if(arr[i]<target) { return (i+1)}
        //---> Bình thường ta sẽ if 2 lần ==> Slow trong loop.
        //Optimize 1:
        //+ For (chỉ check <= ngược lại break)
        //+ ngoài for --> Check if(index+1) return index.
        //---> Chỉ if else đúng 1 lần.
        //Optimize 2:
        //Ta sẽ nhóm >= thày vì <= --> Không cần check gì (index=i)
        //==> Done.
        //** Kinh nghiệm:
        //+ Kết hợp dấu giữa (>) + (=) và (<) + (=).

        //** Reference:
        //12%
        //+ Maximum Average Subarray II
        //+ Get Biggest Three Rhombus Sums in a Grid
        //+ Detonate the Maximum Bombs
        //100%
        //+ UTF-8 Validation
        //+ Unique Morse Code Words
        //+ Max Value of Equation

    }
}
