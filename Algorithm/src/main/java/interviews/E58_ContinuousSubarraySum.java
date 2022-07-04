package interviews;

import java.util.HashMap;

public class E58_ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
            sum+=nums[i];
            int numberMultiple=sum/k;
            int currentSum=sum;

//            if((sum%k==0&&!hashMap.isEmpty())||hashMap.containsKey(sum)){
//                return true;
//            }
            if(hashMap.containsKey(sum)&&hashMap.get(sum)!=i-1
                    || (sum%k==0&&hashMap.size()>=1)
//                    || (sum%k==0&&hashMap.size()>1&&nums[i]==0)
            ){
                return true;
            }

            for(int j=1;j<=numberMultiple;j++){
//                int value=currentSum-j*k;
                currentSum-=k;

                if(hashMap.containsKey(currentSum)){
                    return true;
                }
            }
            if(!hashMap.containsKey(sum)){
                hashMap.put(sum, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySumOptimized1(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
            sum+=nums[i];
            int numberMultiple=sum/k;
            int currentSum=sum;
            boolean isContainsSum=hashMap.containsKey(sum);

            if(isContainsSum&&hashMap.get(sum)!=i-1
                    || (sum%k==0&&hashMap.size()>=1)
//                    || (sum%k==0&&hashMap.size()>1&&nums[i]==0)
            ){
                return true;
            }

            for(int j=1;j<=numberMultiple;j++){
                currentSum-=k;

                if(hashMap.containsKey(currentSum)){
                    return true;
                }
            }
            if(!isContainsSum){
                hashMap.put(sum, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySumOptimized2(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int currentSum=sum%k;
            boolean isConstainKey=hashMap.containsKey(currentSum);

            if(isConstainKey&&hashMap.get(currentSum)!=i-1){
                return true;
            }
            if(currentSum%k==0&&hashMap.size()>=1){
                return true;
            }
            if(!isConstainKey){
                hashMap.put(currentSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{23,2,6,4,7};
        //Dạng bài này ta tư duy như sau :
        //Cách 1: Nếu quên tính chất của việc %
        //0,
        //0.1, length=10^5
        //--> Dạng này chỉ có thể xử lý với Complexity (O(n)) hoặc O(n*k)....
        //0.2, 0 <= nums[i] <= 10^9
        //--> Với số lớn như thế này ==> Chỉ có thẻ dùng hashMap stored <-> Không thể dùng arrays
        //0.3, Những bài liên quan đến multiple of k
        //--> Ta có thể sử dụng % (Lấy dư)
        //==> Liên quan đến (a%b==0) ---> Ta có thể dùng phương thức x=x%k
        //==> Để giảm range cho x

        //1, Bài này ban đầu ta đã nghĩ đến việc for 2 lần (n) --> Slow
        //1.1, Ở khi ta có 1 sum rồi thì ta sẽ tính bội của sum hiện tại
        //VD: currentSum=23, k=7
        //0 <= multiply <= 3 (3.2)
        //--> Lúc đó ta sẽ trừ dần cho k
        //1.2, Ở đây ta sẽ áp dụng tư duy prefix_sum
        //--> Lưu tổng tất cả các phần tử elements cho đến vị trí (i)
        //+ Mỗi lần currentSum -= k
        //--> Ta sẽ check currentSum exists hay không? --> nếu tồn tại rồi
        //VD: prefix[i]- prefix[j]== h * k
        //==> Tức là sum( elements[j+1 --> i] ) thỏa mãn return true.

        //2, Tại sao không dùng Arrays --> Lại dùng HashMap
        //--> Vì sum lớn có thể lên dến k*10^5
        //Mà 1 <= k <= 231 - 1
        //--> Nên ta lưu hashMap để giảm không gian nhớ (max= 10^5 spaces).

        //3, Ở đây ta tần nums[i]=nums[i]%k
        //--> Vì muốn giảm số currentSum ==> Giảm (currentSum/k) -- numberMultiple
        //--> Số lần loop sẽ giảm.

        //4, Chú ý với case dạng
        //VD: 5,0,0,0
        //--> sum=5 đã xuất hiện rồi
        //+ Ta cần lưu index của hashMap trước đó để check xem đã tồn tại hay chưa --> Sum(elements)=0
        //+ Ta chỉ lưu index của phần từ xuất hiện đầu tiên thôi --> Đã tồn tại thì không push vào hashMap nữa --> Để đảm bảo index sẽ luôn là firstIndex.

        //4.1, Cần tránh các cases đặc biệt :
//        int nums[]=new int[]{23,2,4,6,6};
        // % : 2, 2, 4, 6, 6.
        //Case 1 :
        //*** Case đặc biệt liên quan đến
        //+ HashMap.containsKey( currentSum) ==true
        //--> Tức là đã included currentSum rồi.
        //+ Không trừ k gì cả --> return true
        int nums[]=new int[]{5,0,0,0};
//        int nums[]=new int[]{0};
        //Case 2: Case này sum =1 = tại nums[1]=0
        //-->
//        int nums[]=new int[]{1,0};
        //Output : true
        //Expected : false
        //Case 3: Case này ngươc với case [0,1]
        //Vì case [0,1]
//        int nums[]=new int[]{2,4,3};
        //Case 4: Case này khi sum=0 và hashMap.size()==1
//        int nums[]=new int[]{0,0};
        System.out.println(checkSubarraySum(nums, 7));
//        System.out.println(checkSubarraySum(nums, 6));

        //Cách 2: Tính chất của những bài % như thế này như sau:
        //1, Ta sẽ chỉ lưu hash của (% số dư)
        //hashMap.put(currentSum%k, i)
        //Nếu bất kỳ 1 sum nào đó tính sau % k --> đã tồn tại
        //--> Tổng các phần tử ở giữa sẽ %k==0 --> return true.
        //2, vì có case:
        //[5,0,0,0] như trên --> Ta sẽ lưu index để check + kiểm tra trùng put(element) to HashMap.
        System.out.println(checkSubarraySumOptimized2(nums, 7));

    }
}
