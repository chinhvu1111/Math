package interviews;

import java.util.HashMap;

public class E59_SubarraySumEqualsK {

    public static int subarraySumWrong(int[] nums, int k) {
        HashMap<Integer, Integer> sumPre=new HashMap<>();
        int n=nums.length;
        int sum=0;
        int rs=0;
//        sumPre.put(0, 1);

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int preVSum=sum-k;
            boolean isContainsKey=sumPre.containsKey(preVSum);
//            Integer preValue=sumPre.get(k);
//            preValue=preValue==null?0:preValue;

            if(isContainsKey){
//                if(nums[i] == k
////                        && (sumPre.get(preVSum) <= i - 1)
////                        || nums[i - 1] <=0
//                        &&nums[i-1]==nums[i]
//                        &&sum==preVSum
//                ){
//                    rs+=2;
//                }else{
//                    rs++;
//                }
                if(nums[i] == k
                        && (sumPre.get(preVSum) != i - 1)
                ){
                    rs++;
                }
                if(sum!=preVSum){
                    sumPre.put(sum, i);
                }
            }else{
                if(sum==k){
                    rs++;
                }
                sumPre.put(sum, i);
            }
        }
        for(int i=0;i<n;i++){
            if(nums[i]==k){
                rs++;
            }
        }
//        if(!sumPre.containsKey(k)){
//            return 0;
//        }
        return rs;
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> sumCount=new HashMap<>();
        int n=nums.length;
        int sum=0;
        int rs=0;
        sumCount.put(0, 1);

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int preVSum=sum-k;
            boolean isContainsKey=sumCount.containsKey(preVSum);
            int valueCount=0;

            if(isContainsKey){
                int value=sumCount.get(preVSum);

                valueCount+=value;
                //1, Optimizea: chung nên ta sẽ đẩy ra ngoài
//                sumCount.put(sum, sumCount.getOrDefault(sum, 0)+1);
            }
            sumCount.put(sum, sumCount.getOrDefault(sum, 0)+1);
//            System.out.println(sum+" "+nums[i]+" "+valueCount);
            rs+=valueCount;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3};
//        int k=3;
//        int arr[]=new int[]{1,1,1};
//        int k=2;
        //Case 1: Case này rất phổ biến với dạng bày prefix constinious array như thế này
//        int arr[]=new int[]{0,1,0,0};
//        int k=0;
//        int arr[]=new int[]{1,1,2,4};
//        int k=2;
//        int arr[]=new int[]{1};
//        int k=0;
        //# Case: nums[i]<0
        //Case này chỉ có thể sai ở vị trí i==2
        //arr[2]=0
        //Lúc này rs+=2 : Bao gồm chính nó arr[1] và 1 phần tử con (1, -1, 0)
//        int arr[]=new int[]{1, -1, 0};
//        int k=0;
        //Expect : 3
        //# Case
//        int arr[]=new int[]{-1,-1,1};
//        int k=1;
//        int arr[]=new int[]{0, 0};
//        int k=0;

        //Bài này ta tư duy như sau:
        //Tư duy bài này tương tự bài: E61_SubArraySumDivisibleByK

        //1, Các lỗi sai khá nhiều khi làm dạng bài kiểu như thế này:
        //1.1, Đầu tiên nhiệm vụ của ta chính là đếm số sub-array có tổng là k
        //Nên ta cần phải xác định cấu trúc dữ liệu sẽ lưu dạng gì?
        //+ Ở đây ta bị nhầm lẫn giữa việc lưu kết quả sum  và prefixSum[i]
        //** Đề bài nói tìm tổng số sub-array có sum(arr[i])=k
        //Nên kết quả có thể trả ra bằng các cách sau:
        //+ hashMap.get(k) : Lưu ở đây khá là khó và không thể lưu all được
        //+ return rs : Tư duy ở đây là 2 prefix[i]-prefix[j] bất kỳ == k
        //--> rs++
        //1.2, Tư duy liên quan đến index để cover các cases liên quan đến nums[i] <0
        //VD:
        //#CASE 1:
        //k=3
        //arr :         {0,1,(0),0}
        //prefixSum :   {0,1,1,1}
        //--> rs=3
        //+ Với i==2 : rs+=1
        //+ Với i==3 : rs+2
        //Với case này nếu dùng theo kiểu getIndexFirst(prevSum) : Lần xuất hiện đầu tiên của prevSum
        //if(index!=i-1) --> rs+=2;
        //===> Bị sai với i==2 --> Thực tế rs+=1
        //*** --> Không thể dùng index ở đây được.
        //Question: Liệu có thể áp dụng nums[i]!=nums[i-1] <-> rs+=1 không?
        //1.3,
        //#CASE 2 :
        //arr       : {1, -1, 0}
        //prefixSum : {1, 0, 1}
        //--> Case này cũng không thể áp dụng index.
        //Answer : Không thể áp dụng condition: nums[i]!=nums[i-1]
        // --> Vì ở đây -1!=0 --> Mà rs+=2;
        //EXP : Với những bài kiểu như tính tổng số subarry ==> Không thể dùng index, nums[i] được.

        //2, Ở đây ta có tư duy tương tự như dynamic:
        //2.1,
        // Ta sẽ lưu số lượng chuỗi liên tiếp có sum= prefix[i] vào hashMap
        //Nếu currentSum -k --> exists thì tổng số chuỗi có sum= currentSum
        //+ x= hashMap.get(currenSum-k)
        //==> rs+=x
        //+ Số chuỗi có sum =currentSum ===> tăng lên 1 đơn vị
        //int newValue = hashMap.get(currentSum) +1
        //--> Vì prevSum --> exists or not không liên quan đến currentSum
        //--> sumCount.put(sum, sumCount.getOrDefault(sum, 0)+1);
        //Giải thích:
        //+ Nếu currentSum exists --> ++1
        //+ Nếu currentSum not exists --> =1
        //Bài này có khá nhiều cases đặc biệt cần quan tâm:
        //2.2, Ta luôn chú ý đến điều kiện bằng ==k --> valueCount+=1 (Giá trị được + vào rs)

        //3, Tối ưu:
        //+ Thay vì check (sum==k) liên tục
        //--> Quy về check hashMap.contains(sum-k)
        //Khi (sum==k) valueCount++ <=> hashMap.put(0, 1) + + hashMap.contains(sum-k)
        int arr[]=new int[]{1,2,1,2,1};
        int k=3;
//        int arr[]=new int[]{0,0,0,0,0,0,0,0,0,0};
//        int k=0;
        System.out.println(subarraySum(arr, k));
    }
}
