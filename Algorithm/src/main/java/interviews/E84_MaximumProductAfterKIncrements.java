package interviews;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E84_MaximumProductAfterKIncrements {

    public static int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> integers=new PriorityQueue<>();
        for (int num : nums) {
            integers.add(num);
        }
        for(int i=1;i<=k;i++){
            Integer valuePeek=integers.poll();

            integers.add(valuePeek+1);
        }
        long rs=1;

        while (!integers.isEmpty()){
            rs=(rs*integers.poll())%1_000_000_007;
        }
        return (int) rs;
    }

    public static int maximumProductOptimize(int[] nums, int k) {
        PriorityQueue<Integer> integers=new PriorityQueue<>();
        for (int num : nums) {
            integers.add(num);
        }
        for(int i=1;i<=k;i++){
            Integer valuePeek=integers.poll();
            Integer valueNext=-1;

            if(!integers.isEmpty()){
                valueNext=integers.peek();
            }
            if(valueNext!=-1&&valueNext-valuePeek>1){

                if(valueNext-valuePeek<=k-i+1){
                    i+=valueNext-valuePeek-1;
                    integers.add(valueNext);
                }else if(valueNext-valuePeek>k-i+1){
                    valuePeek+=k-i+1;
                    i=k;
                    integers.add(valuePeek);
                }

            }else {
                valuePeek++;
                integers.add(valuePeek);
            }
        }
        long rs=1;

        while (!integers.isEmpty()){
            rs=(rs*integers.poll())%1_000_000_007;
        }
        return (int) rs;
    }

    public static int maximumProductOptimize1(int[] nums, int k) {
        Arrays.sort(nums);
        int idx = 1;

        while(idx != nums.length && k > 0){
            if(nums[0] < nums[idx]){
                for(int i = 0; i < idx; i++){
                    nums[i]++;
                    k--;
                    if(k == 0) break;
                }
            }
            if(nums[0] == nums[idx])
                idx++;
        }
        idx = 0;
        while(k > 0){
            nums[idx]++;
            idx++;
            k--;
            if(idx == nums.length)
                idx = 0;
        }
        long res = 1;
        for(int num : nums){
            res *= num;
            res %= 1000000007;
        }
        return (int) res;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{6,3,3,2};
//        int k=2;
//        int arr[]=new int[]{24,5,64,53,26,38};
//        int k=54;
        int arr[]=new int[]{21,100};
        int k=58;
        System.out.println(maximumProduct(arr, k));
        System.out.println(maximumProductOptimize(arr, k));
        //Bài này tư duy như sau:
        //1,
        //VD:
        //6*4, có 2 lựa chọn:
        //6+1/ 4+1
        //+ (6+1)*4= 6*4 + 6
        //+ 6*(4+1)= 6*4 + 4
        // (6*4) + 6 > (6*4) + 4
        //==> Như bên trên thì ta ưu tiên + vào value nhỏ hơn (Less than)
        //2, Để luôn tìm được min value --> Sau khi increase giá trị min nhất của Array
        //--> Add elements vào PriorityQueue
        //--> Luôn lấy peek +1 --> Add vào queue lại.
        //3, Tối ưu:
        //Với cách thứ 2:
        //Gồm các steps như sau:
        //3.1, Ta luôn so sánh nums[0] với nums[index]
        //Sau đó ta sẽ cộng lần lượt số vào giữa khoảng [0, index]
        //+ Bắt đầu với : nums[0] và nums[index=1]
        //--> Sau đó với ta sẽ dãn dần index++
        //VD:
        //
        //value: 2,3(indx),5,6
        //index: 0,1,2,3
        //Step 1: nums[0]=2+1 --> = nums[1] : index++;
        //-->
        //value: 3,3,5(indx),6
        //index: 0,1,2,3
        //Step 2:
        // --> Lúc này cần cộng 1 vào giữa (0 --> <indx)
        //Step 3:
        //value: 4,4,5(indx),6
        //index: 0,1,2,3
        // --> Sau khi +1, nums[0] <nums[index]
        //==> Ta tiếp tục +1
        //STEP 4:
        //value: 5,5,5(indx),6
        //index: 0,1,2,3
        //3.2, Đến cuối ta sẽ có 1 dãy giá trị bằng nhau:
        //VD:
        //value: 6,6,6,6 (index)
        //index: 0,1,2,3
        //+ Vì k quá lớn --> (index==nums.length-1)
        //--> index cần quay lại = 0 ==> Cộng 1 từ đầu.
        //
    }
}
