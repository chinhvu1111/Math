package interviews;

import java.util.HashMap;

public class E61_SubarraySumsDivisibleByK {

    public static int subarraysDivByK(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMapIndex=new HashMap<>();
        HashMap<Integer, Integer> hashMapCount=new HashMap<>();
        int sum=0;
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int currentSum=sum%k;
            boolean isContainsKey=hashMapIndex.containsKey(currentSum);
//            boolean isContainsKeyNegative;
            Integer valueNegative=0;

            if(hashMapCount.containsKey(currentSum-k)){
                valueNegative+=hashMapCount.get(currentSum-k);
            }
            if(hashMapCount.containsKey(currentSum+k)){
                valueNegative+=hashMapCount.get(currentSum+k);
            }
            int valueCount=valueNegative;

            //1, Tối ưu chỗ này vì:
            //+ hashMapIndex.put(currentSum, i) --> đều xuất hiện ở if(contains) và else{}
            //+ valueCount++ --> Bị duplicated ở trong else
            //==> Bỏ if trong else() và bỏ hashMapIndex.put(currentSum, i); trong if()
            //==> Logic lúc này sẽ đúng.
            //1.1, Chỉ có 1 trường hợp duy nhất cần xét riêng chính là (prefixSum[i]%k==0) hiện tại đứng 1 mình.
            if(currentSum==0){
                valueCount++;
//                rs++;
                hashMapIndex.put(currentSum, i);
            }

            if(isContainsKey){
                Integer value=hashMapCount.get(currentSum);

                if(value==null){
                    valueCount++;
//                    rs++;
                    value=1;
                }else{
//                    rs+=value;
//                    if(hashMapIndex.get(currentSum)!=i-1){
//                        value++;
//                    }
                    //Chỗ này phải + value trước khi tăng value lên
                    valueCount+=value;
                    value++;
                }
//                System.out.println(currentSum+" "+value+" "+rs);
                hashMapCount.put(currentSum, value);
                hashMapIndex.put(currentSum, i);
            }else {
                if(currentSum%k==0&&!hashMapIndex.containsKey(currentSum)){
                    valueCount++;
//                    rs++;
                }
                hashMapIndex.put(currentSum, i);
                hashMapCount.put(currentSum, 1);
            }
            System.out.println(nums[i]+" "+currentSum+" "+valueCount);

            rs+=valueCount;
        }
        return rs;
    }

    public static int subarraysDivByKOptimized1(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMapCount=new HashMap<>();
        int sum=0;
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int currentSum=sum%k;
            boolean isContainsKey=hashMapCount.containsKey(currentSum);
//            boolean isContainsKeyNegative;
            Integer valueNegative=0;

            if(hashMapCount.containsKey(currentSum-k)){
                valueNegative+=hashMapCount.get(currentSum-k);
            }
            if(hashMapCount.containsKey(currentSum+k)){
                valueNegative+=hashMapCount.get(currentSum+k);
            }
            int valueCount=valueNegative;

            if(currentSum==0){
                valueCount++;
            }

            if(isContainsKey){
                Integer value=hashMapCount.get(currentSum);

                valueCount+=value;
                value++;
//                System.out.println(currentSum+" "+value+" "+rs);
                hashMapCount.put(currentSum, value);
            }else {
                hashMapCount.put(currentSum, 1);
            }
            System.out.println(nums[i]+" "+currentSum+" "+valueCount);

            rs+=valueCount;
        }
        return rs;
    }

    public static int subarraysDivByKOptimized2(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMapCount=new HashMap<>();
        int sum=0;
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int currentSum=sum%k;
            boolean isContainsKey=hashMapCount.containsKey(currentSum);
//            boolean isContainsKeyNegative;
            Integer valueNegative=0;

            if(hashMapCount.containsKey(currentSum-k)){
                valueNegative+=hashMapCount.get(currentSum-k);
            }
            if(hashMapCount.containsKey(currentSum+k)){
                valueNegative+=hashMapCount.get(currentSum+k);
            }
            int valueCount=valueNegative;

            if(currentSum==0){
                valueCount++;
            }

            if(isContainsKey){
                Integer value=hashMapCount.get(currentSum);

                valueCount+=value;
                value++;
//                System.out.println(currentSum+" "+value+" "+rs);
                hashMapCount.put(currentSum, value);
            }else {
                hashMapCount.put(currentSum, 1);
            }
            System.out.println(nums[i]+" "+currentSum+" "+valueCount);

            rs+=valueCount;
        }
        return rs;
    }

    public static int subarraysDivByKOptimizedOptimized1(int[] nums, int k) {
        int sum = 0;
        int[] arr = new int[k];   // To store mod value of prefix sum
        arr[0] = 1;  // Inititally sum is zero . So , we are incrementing 0th index value.

        for(int num: nums){
            sum += num;
            if(sum < 0){
                arr[((sum%k + k)%k)]++;     // if sum is negative take it's positive modulo value
            }else{
                arr[(sum%k)]++;
            }
        }

        int ans = 0;
        for(int x : arr){
            ans += ((x)*(x-1))/2;     // calculating result from prefix moded values
        }
        return ans;
    }

    public static void main(String[] args) {
        //Case 1:
//        int arr[]=new int[]{4,5,0,-2,-3,1};
        //Case 2 : Quên không check if(sum%k==) rs++
//        int arr[]=new int[]{5};
        //Case 3:
        //Case này là case liên quan đến số âm
        //prefix[0]=-1 và prefix[1]=1 có thể gộp được với nhau.
//        int arr[]=new int[]{-1,2,9};
//        int arr[]=new int[]{-5};
//        int arr[]=new int[]{8,9,7,8,9};
        //#Case này liên quan đến việc số âm
        //VD-1:
        //arr :         -2,4,-5,7,7
        //PrefixSum :   (-2),2,-3,(4),11
        //--> (-2) và (4) có thể gộp thành 1 cặp để trừ đi nhau.
        //VD-2:
        // arr :         -1,2,9
        //PrefixSum :   (-1),(1),10
        //--> (-1) và (1) có thể gộp thành 1 cặp để trừ đi nhau.
        int arr[]=new int[]{-2,4,-5,7,7};
        //Case 3:
        //Case này liên quan đến việc thiếu quan hệ giữa các prefix[i] và prefix[j]
        //+, prefix[i] - prefix[j] =k
        //+, prefix[j] - prefix[i] =k
        //--> Phải có 2 cases như thê này --> Kq mới đúng được.
//        int arr[]=new int[]{7,-5,5,-8,-6,6,-4,7,-8,-7};
        //[8,9,7,8,9]
        //i=0 : {8}
        //i=3 : {8}
        //{9,7} : 16
        //{8,9,7} : 24
        //{9,7,8} : 24
        //{7,8,9} : 24
        //{8,9,7,8} : 32

        //Bài này ta tư duy như sau:
        //##### CÁCH 1 :
        //1, Ở mấy dạng bài rs++ hay nói khác đi rs+=value
        //--> Ta nên lưu riêng ra value + rs
        //--> Log ra thông số trên/ index
        //1.1, Dạng bài này nếu dùng % thường sẽ bị vướng case:
        ///CASE 1:
        // arr={8, 9, 7, 8}, k=8
        //sumPrex={8, 17, 24, 32}
        //sumPrexMod={0, 1, 0, (0)}
        //+ Lúc này tại i==2 --> rs+=1;
        //Do chỉ có 1 case {9,7}
        //+ Lúc này tại i==3 --> rs+=3;
        //Do có 3 kq {8), {9,7,8}, {8,9,7,8}
        //==> ở đây mặc dù getIndex(currentMod=0)=2, 2==i-1
        //-->Ta vẫn lấy rs+=3
        //i==3 (sumMod=0) : Được tính theo i==0 và i==2 (sumMod=4)
        //Giải thích:
        //+ prefixSum[3] - prefixSum[0] : {9,7,8}
        //+ prefixSum[3] - prefixSum[2] : {8}
        //+ prefixSum[3] : {8,9,7,8}
        //CASE 2:
        //arr={4,5,0,-2,-3,1}, k=5
        //sumPrex={4, 9, 9, 7, 4, 5}
        //sumPrexMod={4, 4, (4), 2, 4, 0}
        //+ Tại i==2
        //Có các cases : {0}, (5,0}
        // --> rs+=2
        //i==2 (sumMod=4) : Được tính theo i==0 và i==1 (sumMod=4)
        //Giải thích:
        //+ prefixSum[2] - prefixSum[1] : 0
        //+ prefixSum[2] - prefixSum[0] : 5,0
        //*** Từ CASE 1 và CASE 2:
        //Ta xác định được rằng:
        //+ Ta không cần quan tâm đến nums[i]%k==0
        //+ Ta chỉ cần quan tâm đến currentSum %k==0
        //****************Với những bài dạng kiểu như này cần phải xử lý thật rõ ràng
        //+ Không cần quan tâm đến nums[i] làm gì --> Cho dù nums[i]%k==0 ==> Dùng prefixSum nó cũng bù lại mà thôi.

        //2, Với những dạng bài kiểu như này kinh nghiệm như sau:

        //2.1, Luôn phải xác định rõ ta lưu gì vào hashMap:
        //+ Lưu currentSum
        //+ Lưu currentSumMod.
        //---> Khi xác định rõ rồi thì sẽ không xảy ra trường hợp push(nums[i], value)
        //==> Vì đang sai rule chung.

        //2.2, Dạng bài kiểu này có thể sẽ có số âm nên việc (%k==0)
        //Không phải chỉ xét 2 prefixSum[i]==prefix[j]
        //--> Ta phải xét 2 chiều, 3 trường hợp:
        //+ prefix[i]>=0, prefix[j]>=0
        //--> Chỉ xét 2 prefix[i]==prefix[j]
        //+ <>
        //--> Xét 2 cases:
        //+ prefix[i]-prefix[j]=k
        //+ prefix[i]-prefix[j]=-k
        //Question: Liệu có:
        //+ prefix[i] - prefix[j] = h*k (h>=2) không?
        //---> Không vì ta luôn lấy MOD nếu lệch quá k ==> Nó sẽ % quay trở về <=k.
        //VD: k = 3
        //+ prefixSum : (-2), ..., (4)
        //==> Không có MOD ntn : =+> Chuyển thành : (-2),...,(1) ==> substration =3.

        //2.3, Ngoài ra bài này có hơi hướng dynamic (Khác bài chỉ return true):
        //Tức là số subArray có currentSumMode=a
        //+ dp[currentSum] = dp[currentSum] +1
        //--> Tức là có thêm ( dp[currentSum] + 1 ) arraysSum các continuous array có (currentSum=a)
        //--> Chú ý không cần quan tâm đến nums[i]%k==0, vì prefix nó đã cover được cases đó rồi
        //===> Chỉ chưa cover được case (prefix[i] đứng 1 mình) ==> Cần phải check (currentSum%k==0)
        //+ Cần lưu hashMapCount đến tính số lượng.
        //--> Sau khi làm xong thì thấy dùng hashMapindex chỉ để check index trước đó của currentSumMod đứng ở đâu.

        //##### CÁCH 2 :
        //1, Cách này thiên về các xử lý mod với số Âm (<0)
        //Muốn làm cách này thì phải nhớ công thức:
        //Với sum <0:
        //lúc đó currentSumMod=((sum%k + k)%k);
        //VD: k=7
        //CT: ((sum%k + k)%k)
        //arr :             {-2,4,-5,7,7}
        //prefix:           {-2,2,-3,4,11}
        //currentModNonCT:  {-2,2,-3,4,(4)}
        //currentModCT:     {5,2,(4),4,(4)}
        //--> Ta thấy rule ở đây : 4-7-=-1 --> Đúng cái ta mong muốn
        //==> Chăng qua là ta dùng cty bên trên để tránh phải query hashMap nhiều.
        //2, Nếu ta có dp[currentSum]=4
        //Số lượng array liên tiếp có (sum==currentSum) =4
        //==> currentSum bây giờ ko có tác dụng vì nó là sum_prefix
        //--> arr[] --> Duyệt toán bộ arr tính
        //s+=x*(x-1)/2
        //Giải thích:
        //1,2,3,4 --> có 4 danh sách prefix thỏa mãn --> Số lượng sum = số cặp (prefix đi đôi với nhau_
        //--> CT : count=x*(x-1)/2.

//        System.out.println(subarraysDivByK(arr, 5));
//        System.out.println(subarraysDivByKOptimized1(arr, 5));
//        System.out.println(subarraysDivByK(arr, 9));
//        System.out.println(subarraysDivByKOptimized1(arr, 9));
        System.out.println(subarraysDivByK(arr, 2));
        System.out.println(subarraysDivByKOptimized1(arr, 2));
//        System.out.println(subarraysDivByK(arr, 8));
//        System.out.println(subarraysDivByK(arr, 6));
//        System.out.println(subarraysDivByK(arr, 7));

        System.out.println(subarraysDivByKOptimizedOptimized1(arr, 7));
    }
}
