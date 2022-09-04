package interviews;

public class E123_MaxConsecutiveOnesIII_slice {
    public static int longestOnes(int[] nums, int k) {
        //Vì bài này xác định chỉ cần flip 0 nên không cần dùng array
        //--> Chỉ cần dùng 1 biến đề lưu count
        int n=nums.length;
        int maxCount=0;
        int start=0;
        int[] count =new int[2];
        int rs=0;

        for(int i=0;i<n;i++){
            maxCount=Math.max(maxCount, ++count[nums[i]]);
//            System.out.printf("%s %s %s\n", i, maxCount, count[nums[i]]);

            if (i-start+1-maxCount>k){
                count[nums[start]]--;
                start++;
            }
            rs=Math.max(rs, i-start+1);
        }

        return rs;
    }

    public static int longestOnesOld(int[] nums, int k) {
        int n=nums.length;
        int[] queueArr=new int[n];
        int front=0,rear=0;
        int rs=0;
        int currentValue=0;

        for(int i=0;i<n;i++){
            currentValue++;

            if(nums[i]==0){
                if(k==0){
                    currentValue=0;
                    continue;
                }
                if(rear-front==k){
                    currentValue=i-queueArr[front++];
                }
                queueArr[rear++]=i;
            }
            rs=Math.max(rs, currentValue);
        }
        return rs;
    }

    public static int longestOnesOptimize(int[] nums, int k){

        int n=nums.length;
        int start=0;
        int rs=0;
        int i=0;

        /*
        Tư duy như sau:
        1,
        1.1, Gặp 0 --> K-- (add 1 element=0 vòa cuối)
        1.2, K<0, start++, Nếu nums[start]==0
        ==> k++ (pop 1 phần tử 0 trước đó đi);
        - nếu k<0 --> (Không tăng start)
        VD: 00000 (k=3)
        - Đến cuối :--> start=2, end=4 (k = 3 - 5 = -2)
        - Chỉ nhận : start=0, end=1, k==0
        VD: 00010 (k=3)
        - Đến cuối :--> start=1, end=4 (k = 3 - 4 = -1)
        - Chỉ nhận : start=1, end=4, k==0
        1.3, Với nums[i]==1 : Ta sẽ không add thêm 0 nữa (Tức là không cần trừ đi k--) --> k lúc này sẽ không cần dịch vị trí đầu.
        ==> Vị trí đầu chỉ dịch khi gặp 0 đằng sau (add 0) + (vị trí đầu nums[start] ==0 ) thì mới dịch.
        1.4, Tư duy hướng theo việc chú ý đến (start và end)
        - Giá trị đâu (start) ta chia thành 2 trường hợp:
        + 0 (Dịch khi quá số lượng ký tự k)
        + 1 (không cần dịch)
        - Giá trị sau (end) ta chia thành 2 trường hợp:
        + 0 (Nếu quá k --> quay ra dịch đầu)
        + 1 (không cần dịch)
         */
        for(i=0;i<n;i++){
            if(nums[i]==0){
                k--;
            }
            if(k<0&&nums[start++]==0){
                k++;
            }
//            rs=Math.max(rs, i-start+1);
        }

        return i-start;
    }

    public static void main(String[] args) {
        int[] arr =new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k=3;
        System.out.println(longestOnes(arr, k));
        System.out.println(longestOnesOld(arr, k));
        //Bài này tư duy như sau:
        //1, Đối với những bài chỉ định character liên tiếp nhau : ===> Không thể làm theo phương pháp count
        //==> Cần làm theo phương pháp stack, hoặc làm như dưới đây.
        //
//        Tư duy như sau:
//        1,
//        1.1, Gặp 0 --> K-- (add 1 element=0 vòa cuối)
//        1.2, K<0, start++, Nếu nums[start]==0
//                ==> k++ (pop 1 phần tử 0 trước đó đi);
//        - nếu k<0 --> (Không tăng start)
//        VD: 00000 (k=3)
//                - Đến cuối :--> start=2, end=4 (k = 3 - 5 = -2)
//                - Chỉ nhận : start=0, end=1, k==0
//        VD: 00010 (k=3)
//                - Đến cuối :--> start=1, end=4 (k = 3 - 4 = -1)
//                - Chỉ nhận : start=1, end=4, k==0
//        1.3, Với nums[i]==1 : Ta sẽ không add thêm 0 nữa (Tức là không cần trừ đi k--) --> k lúc này sẽ không cần dịch vị trí đầu.
//                ==> Vị trí đầu chỉ dịch khi gặp 0 đằng sau (add 0) + (vị trí đầu nums[start] ==0 ) thì mới dịch.
//        1.4, Tư duy hướng theo việc chú ý đến (start và end)
//                - Giá trị đâu (start) ta chia thành 2 trường hợp:
//        + 0 (Dịch khi quá số lượng ký tự k)
//        + 1 (không cần dịch)
//        - Giá trị sau (end) ta chia thành 2 trường hợp:
//        + 0 (Nếu quá k --> quay ra dịch đầu)
//        + 1 (không cần dịch)
        //
        //Các bài liên quan:
        //- Count Number of Nice Subarrays
        //- Replace the Substring for Balanced String
        //- Max Consecutive Ones III
        //- Binary Subarrays With Sum
        //- Subarrays with K Different Integers
        //- Fruit Into Baskets
        //- Shortest Subarray with Sum at Least K
        //- Minimum Size Subarray Sum

        System.out.println(longestOnesOptimize(arr, k));
    }
}
