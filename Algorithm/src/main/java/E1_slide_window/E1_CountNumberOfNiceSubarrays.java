package E1_slide_window;

import java.util.Deque;
import java.util.LinkedList;

public class E1_CountNumberOfNiceSubarrays {

    //** Đề bài:
    //- Bài này đếm số lượng các subarray sao cho số lượng các chữ số lẻ trong đó = k
    //
    //** Bài này tư duy như sau:
    //1.0, Idea
    //- Các chuỗi không trùng nhau --> không cùng chung (start, end)
    //-
    //VD:
    //2,2,2,1,2,2,1,2,2,2
    //--> Tính prefixSum trước
    //0,0,0,1,1,1,2,2,2,2
    //--> Sau đó tính count lần lượt trên từng prefixSum
    //+ Sum = count[2] * count[0].
    //--> sum = count[i]* count[i-k] +...+
    //VD: k=3
    //1,1,2,1,1
    //1,2,2,3,4
    //- Xét 1 elment thứ i : ta quan tâm đến prefixSum[i-k] trước đó của nó
    // --> Nên ta sẽ count cộng dần + xét đến đâu tính đến đó.
    //
    //1.1, Bài này tư duy như sau:
    //- Ta sẽ tính prefixSum dựa trên n trước
    //- Sau đó sẽ tính count dựa trên prefix sum
    //Với công thức:
    //+ count cộng dần + xét đến đâu tính đến đó.
    //+ Nếu prefix[i]>=k : rs+= count[prefix[i]-k]
    //1.2, Complexity:
    //- Time complexity : O(n)
    //- Space complexity : O(n)
    //
    //Cách 2:
    //2.
    //2.0, Idea
    //+ VD:
    //2,2,2,1,2,2,1,2,2,2
    //+ deque: (2,2,2,1,2,2,1) đã đủ 2 phần tử odd
    //+
    //2.1, slide window
    //** Số subarray có chính xác k chữ số odd = [số subarray nhiều nhất (k) chữ số odd] - [số subarray nhiều nhất (k-1) chữ số odd] .
    //VD:
    //2,2,2,1,2,2,1,2,2,2
    //- Số subarray có nhiều nhất 2 chữ số odd:
    //+ Số subarray =
    //(2,2,2) kết hợp 1 --> 1 có thể chọn kết hợp với (2,2,2),(2,2),(2)
    //--> Số chuỗi cộng lên chính bằng length của queue.
    //
    public static int numberOfSubarrays(int[] nums, int k) {
        int n=nums.length;
        int max=Integer.MIN_VALUE;

        for(Integer num:nums){
            max=Math.max(max, num);
        }
        int[] prefixSum=new int[n];
        int odd=0;

        for(int i=0;i<n;i++){
            if(nums[i]%2==1){
                odd++;
            }
            prefixSum[i]=odd;
        }
        int[] count=new int[n+1];
        count[0]=1;
        int rs=0;

        for(int i=0;i<n;i++){
            if(prefixSum[i]>=k){
                rs+=count[prefixSum[i]-k];
            }
            count[prefixSum[i]]++;
//            System.out.printf("%s %s %s\n",i, nums[i], prefixSum[i]);
        }
        return rs;
    }

    public static int numberOfSubarraysSlideWindow(int[] nums, int k) {
        int numMostK=numberSubarrayHavingMostKOdd(nums, k);
        System.out.println(numMostK);
        int numMostK1=numberSubarrayHavingMostKOdd(nums, k-1);
//        System.out.println(numMostK1);

        return numMostK-numMostK1;
    }

    //2,2,1,2,1,2,(1)
    //+ queue: 2,2,1,2,1,2
    //+ Nếu muốn add thêm 1 --> Thì cần removeFirst đi.
    //+ Sau đó sẽ vẫn rs+= queue.length()
    public static int numberSubarrayHavingMostKOdd(int[] nums, int k){
        if(k==0){
            return 0;
        }
        Deque<Integer> deque=new LinkedList<>();
        int n=nums.length;
        int numberOdd=0;
        int rs=nums[0]%2==0?0:1;

        for(int i=0;i<n;i++){
            if(nums[i]%2==1){
                numberOdd++;
            }
            int v=0;

            if(numberOdd>k){
                if(!deque.isEmpty()){
                    v=1;
                }
                while (!deque.isEmpty()&&deque.removeFirst()%2==0){
                }
                numberOdd--;
            }
//            System.out.println(deque);
            if(numberOdd==k){
                rs+=deque.size()==0?v:deque.size();
            }
            System.out.println(rs);
            deque.add(nums[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] nums=new int[]{2,2,2,1,2,2,1,2,2,2};
//        int k=2;
        int[] nums=new int[]{1,1,2,1,1};
        int k=3;
//        int[] nums=new int[]{1,1,1,1,1};
//        int k=1;
//        int[] nums=new int[]{2,4,6};
//        int k=1;
        System.out.println(numberOfSubarrays(nums, k));
        System.out.println(numberOfSubarraysSlideWindow(nums, k));
        //#Reference:
        //1249. Minimum Remove to Make Valid Parentheses
        //2444. Count Subarrays With Fixed Bounds
    }
}
