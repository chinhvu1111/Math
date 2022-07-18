package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class E78_SubarrayProductLessThanK {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        Queue<Integer> integers=new LinkedList<>();
        int n=nums.length;
        int currentProduct=1;
        int rs=0;

        for(int i=0;i<n;i++){
            while (currentProduct*nums[i]>=k&&!integers.isEmpty()){
                int value=integers.poll();
                currentProduct/=value;
            }
            if(nums[i]<k){
                rs+=integers.size()+1;
                integers.add(nums[i]);
                currentProduct=currentProduct*nums[i];
            }
        }
        return rs;
    }

    public static int numSubarrayProductLessThanKOptimize(int[] nums, int k) {
        int left=0;
        int n=nums.length;
        int currentProduct=1;
        int rs=0;

        for(int i=0;i<n;i++){
            currentProduct=currentProduct*nums[i];
            while (currentProduct>=k){
                currentProduct/=nums[left];
                left++;
            }
//            if(nums[i]<k){
//                rs+=(i-left+1);
//            }else{
//                left=i+1;
//            }
            rs+=(i-left+1);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{10,5,2,6};
        int arr[]=new int[]{10,2,5};
//        int arr[]=new int[]{1,2,3};
//        int k=100;
        int k=12;
//        int k=0;

        //Method 2:
//        int arr[]=new int[]{76,8,75,22};
//        int k=18;
        System.out.println(numSubarrayProductLessThanK(arr, k));
        System.out.println(numSubarrayProductLessThanKOptimize(arr, k));
        System.out.println();
        //Bài này tư duy như sau:
        //1, Bài này thuộc dạng continous array --> Slice window
        //+ Ta có thể dùng Queue để thể hiện slice window
        //+ Dùng currentProduct để thể hiện cho giá trị hiện tại (Tích các phần tử trong queue)
        //+ Nếu currentProduct * nums[i} >= k --> Pop() các phần tử ở đầu queue ra.
        //+ Result : Mỗi lần thêm 1 số --> rs += queue.size()+1;
        //2, Tối ưu :
        //+ Thay vì lưu vào queue --> Ta sẽ chỉ lưu 1 left index : Bắt đầu của dẫy continous array
        //+ Nểu currentProduct * nums[i] >=k
        // -->
        //+ currentProduct/=nums[left]
        //+ left++
        //2.1,
        //Sau khi pass while : Vẫn có case numsp[i]>k --> Check (nums[i]>k) : rs+=(i-left+1)
        //Else left=i+1 : tức là nếu Nums[i]>k --> Lấy left =i+1 (Phần tử tiếp theo luôn).
        //2.2,
        //Tối ưu: 98% win
        //while(currentProduct*nums[i]>=k&&!integers.isEmpty()) ==> while(currentProduct>=k)
        //+ currentProduct/=nums[left];
        //+ left++
        //--> Điều kiện bên trên sẽ cover được all cases liên quan đến nums[i]<k (Vì currenProduct * nums[k] --> Giảm đến khi chỉ còn nums[k])
        //--> If(nums[i]<k) --> Không cần check
        //--> left++ (Sẽ luôn được + sau , với việc loop cho đến nums[i] ) ==> Lúc này : left=i+1
    }
}
