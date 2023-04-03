package interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class E279_4SumII {

    public static int fourSumCountHashset(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n=nums1.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=nums1[i]+nums2[j];
                hashMap.put(sum, hashMap.getOrDefault(sum, 0)+1);
            }
        }
        int rs=0;
//        System.out.println(hashMap.size());

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=nums3[i]+nums4[j];
                Integer value=hashMap.get(-sum);

                if(value!=null){
                    rs+=value;
                }
            }
        }
        return rs;
    }

    /*
    - Có thể mở rộng ra thành 5Sum, 6Sum
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int[][] listArrs=new int[][]{nums1, nums2, nums3, nums4};
        int k=listArrs.length;
        Map<Integer, Integer> hashMapKFirstCount=getHashSum(listArrs,0, k/2);
        Map<Integer, Integer> hashMapKSecond=getHashSum(listArrs,k/2, k);
        int rs=0;

        for(Map.Entry<Integer, Integer> e: hashMapKSecond.entrySet()){
            if(hashMapKFirstCount.containsKey(e.getKey()*-1)){
                rs+=hashMapKFirstCount.get(e.getKey()*-1)*e.getValue();
            }
        }
        return rs;
    }

    public static HashMap<Integer, Integer> getHashSum(int[][] listArrs, int start, int end){
        HashMap<Integer, Integer> currentCount=new HashMap<>();

        for(int i=start;i<end;i++){
            int[] nums=listArrs[i];
            HashMap<Integer, Integer> tmp=new HashMap<>();
//            System.out.println("======");
            for(int u:nums){
                if(!currentCount.isEmpty()){
                    for(Map.Entry<Integer, Integer> e:currentCount.entrySet()){
                        if(tmp.containsKey(u+e.getKey())){
                            tmp.put(u+e.getKey(), tmp.get(u+e.getKey())+e.getValue());
                        }else{
                            tmp.put(u+e.getKey(), e.getValue());
                        }
//                        System.out.println(u+e.getKey());
                    }
                }else{
                    tmp.put(u, tmp.getOrDefault(u, 0)+1);
                }
            }
            currentCount=tmp;
//            System.out.println(tmp);
//            System.out.println("======");
        }
//        System.out.println(currentCount);
        return currentCount;
    }

    private static Map<Integer, Integer> sumCount(int[][] listArrs, int start, int end) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int i = start; i < end; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int a : listArrs[i]) {
                for (int total : cnt.keySet()) {
                    map.put(total + a, map.getOrDefault(total + a, 0) + cnt.get(total));
                }
            }
            cnt = map;
            System.out.println(cnt);
        }
        return cnt;
    }

    public static void main(String[] args) {
//        int[] nums1 = {1,2};
//        int[] nums2 = {-2,-1};
//        int[] nums3 = {-1,2};
//        int[] nums4 = {0,2};
//        int[] nums1 = {-1,-1};
//        int[] nums2 = {-1,1};
//        int[] nums3 = {-1,1};
//        int[] nums4 = {1,-1};
        int[] nums1 = {-1,1,1,1,-1};
        int[] nums2 = {0,-1,-1,0,1};
        int[] nums3 = {-1,-1,1,-1,-1};
        int[] nums4 = {0,1,0,-1,-1};
        System.out.println(fourSumCountHashset(nums1, nums2, nums3, nums4));
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
        //
        //** Đề bài
        //- Tìm số cách sao cho:
        //0 <= i, j, k, l < n
        //nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
        //
        //** Bài này tư duy như sau:
        //1.
        //1.0, Idea
        //Method-1:
        //- HashMap
        //a+b+c+d=0
        //+ Dùng hashmap của 2 elements trước (a+b) với O(n^2)
        //+ Sau đó check tiếp O(n^2) sao cho c+d=-1*sum
        //==> Kiểm tra thu được kết quả : rs+=get(sum)
        //
        //- Bài toán mở rộng:
        //+ Ta sẽ mở rộng thêm với k arrays.
        //1.1, Chú ý cases:
        //{-1=2, 1=3}
        //[0,-1,-1,0,1]
        //Result= 2*2 + 3*2 ==> Mỗi vòng check temp hash có 0 rồi thì + thêm e.value() vào.
        //VD: đang (1:3)[ 1 có 3 giá trị] --> Nối với (-1)
        //==> 1 gặp -1 tổng 2 lần --> Mỗi lần ta sẽ + value (số lần của 1) vào ==> + 3 vào.
        //
        //#Reference:
        //455. Assign Cookies
        //832. Flipping an Image
        //453. Minimum Moves to Equal Array Elements
        //957. Prison Cells After N Days
    }
}
