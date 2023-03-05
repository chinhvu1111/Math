package contest;

import java.util.Arrays;
import java.util.HashSet;

public class E14_FindTheMaximumNumberOfMarkedIndices {

//    public static int findIndexNearestLessThanValue(int low, int high, int currentValue, int[] nums){
//        System.out.println(low+" "+high);
//        if(low>=high){
//            if(nums[low]<=currentValue){
//                return low;
//            }
//            return -1;
//        }
//        int mid= low + (high-low)/2;
//        int indexNearest=0;
//        if(nums[mid]>currentValue){
//            high=mid-1;
//        }else{
//            indexNearest=mid;
//            low=mid+1;
//        }
//        int nextIndex=findIndexNearestLessThanValue(low, high, currentValue, nums);
//        if(nextIndex!=-1){
//            return nextIndex;
//        }
//        return indexNearest;
//    }

    public static int maxNumOfMarkedIndicesWrong(int[] nums) {
        Arrays.sort(nums);
        println(nums);
        int n=nums.length;
        int rs=0;
        int prevIndex=0;
        HashSet<Integer> distinctNum=new HashSet<>();

        for(int i=0;i<n;i++){
            if(distinctNum.contains(i)){
                continue;
            }
            int j=Math.max(i+2, prevIndex+1);
            for(;j<n;j++){
                if(nums[i]*2<=nums[j]){
                    rs++;
                    prevIndex=j;
                    break;
                }
            }
            System.out.print(i+" "+prevIndex+", ");
            System.out.print(nums[i]+":"+nums[prevIndex]);
            System.out.println();
            distinctNum.add(prevIndex);
        }
        return rs*2;
    }

    public static int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int rs=0;
//        int prevIndex=n/2;

//        for(int i=0;i<n/2;i++){
//            int j=prevIndex;
//            for(;j<n;j++){
//                if(nums[i]*2<=nums[j]){
//                    rs++;
//                    prevIndex=j;
//                    break;
//                }
//            }
//            prevIndex++;
//        }
        int i=0;
        for (int j = n / 2; i < n / 2 && j < n; ++j) {
            i += (2 * nums[i] <= nums[j]) ?1:0;
        }
//        return rs*2;
        return i*2;
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.print(j + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{9,2,5,4};
//        int[] arr=new int[]{42,83,48,10,24,55,9,100,10,17,17,99,51,32,16,98,99,31,28,68,71,14,64,29,15,40};
        int[] arr=new int[]{57,40,57,51,90,51,68,100,24,39,11,85,2,22,67,29,74,82,10,96,14,35,25,76,26,54,29,44,63,49,73,50,95,89,43,62,24,88,88,36,6,16,14,2,42,42,60,25,4,58,23,22,27,26,3,79,64,20,92};
        System.out.println(maxNumOfMarkedIndicesWrong(arr));
        System.out.println(maxNumOfMarkedIndices(arr));
        //** Đề bài:
        //- Mark index I,j sao cho:
        //value_i * 2 <= value_j
        //- I,j không cần quy luật gì đặc biệt.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- nums[i]<=10^9
        //- nums[I]>=1
        //VD: 9 có thể chọn những số từ (0 --> 9/2) (nums[i]>=1)
        //
        //
        //Case 1:
        //3,4,6,12
        //- Trong trường hợp này không nên ghép 12->6
        //Vì 6 cần phải ghép với 3
        //
        //Case 2:
        //2,4,5,9
        //- Trong trường hợp này 4 không được ghép với 2
        //--> 5 --> Sẽ ghép với 2 và 9 --> ghép với 4
        //
        //
        //- Quy luật là gì để ngăn 2 phần tử thoã mãn không connect với nhau?
        //+ Ở đây có 2 cases là (6 -> 12), (2 -> 4) không được connect đến nhau.
        //+ 6 --> 12 : 6 có thể connect với số trước 6.
        //+ 2 --> 4 : 4 có thể connect với số khác sau 4.
        //
        //- Liệu chỉ cần đếm cặp --> Thì có cần phải phân rõ ra không?
        //+ Ở đây nếu mỗi số a*2<= b --> Ta sẽ tính 1 cặp
        //
        //Ex:
        //1,2,4,(5/8)
        //A,b,c,d
        //A --> c
        //==> b --> sau(c)
        //+ Ở đây d có thể là (5/8)
        //==> d có thể chưa chắc đã thoả mãn.
        //
        //Ex:
        //1,2,4,(5/8)
        //A,b,c,d
        //+ Nếu a đã ghép đc với b --> a cũng ghép được với c.
        //==> Nên ta sẽ ưu tiên ghép a với c vì (b<c) ==> b nhỏ nên sau đó có thể ghép được với các phần tử khác sau.
        //
        //Giải quyết case:
        //3,4,6,12
        //+ Tại sao 3 không chọn 12
        //==> Khi 3 chọn 12 thì 3 sẽ cover vào các cases có thể connect với (4)
        //==> Nếu là 3 thì sẽ ưu tiên chọn từ (6 --> 12) Cái nào ok chọn trước.
        //+ Sau đó (4) sẽ đi từ phủ của (3) trở đi.
        //
        //1.1,
        //- Tư duy như bên trên vẫn sai:
        //+ Do nếu ta kết hợp (i) và (i+1) --> Thực tế (i+1) hướng được đến việc kết hợp với các số lớn hơn nữa ==> nên có thể dẫn đến sai.
        //- Do việc kết hợp là giữa 2 số nên ta mong muốn (i --> j)
        //+ i : 0 --> n/2-1
        //+ j : n/2 --> n-1
        //==> Các kết hợp ntn là tối ưu nhất.
        //1.2, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(1)
        //#Reference
        //2575. Find the Divisibility Array of a String
        //484. Find Permutation
        //1861. Rotating the Box
        //462. Minimum Moves to Equal Array Elements II
        //
    }
}
