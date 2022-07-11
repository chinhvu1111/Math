package interviews;

import java.util.*;
import java.util.stream.Collectors;

public class E74_FindKClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int insertIndex=searchElementInsertLinear(arr, x);
        List<Integer> rs=new ArrayList<>();

        if(insertIndex!=-1){
            rs.add(arr[insertIndex]);
        }
        int left=insertIndex-1;
        int right=insertIndex+1;
        int n=arr.length;
//        int currentRs=Integer.MAX_VALUE;
//        int currentValue=Integer.MAX_VALUE;
        int count=1;

        while((left>=0||right<n)&&count<k){
            int valueLeft=left!=-1&&left<n?Math.abs(arr[left]-x):Integer.MAX_VALUE;
            int valueRight=right!=-1&&right<n?Math.abs(arr[right]-x):Integer.MAX_VALUE;

            if(valueLeft!=Integer.MAX_VALUE&&valueLeft==valueRight){
                if(arr[left]<arr[right]){
                    rs.add(arr[left]);
                    left--;
                }else{
                    rs.add(arr[right]);
                    right++;
                }
            }else if(valueLeft>valueRight){
                rs.add(arr[right]);
                right++;
            }else{
                rs.add(arr[left]);
                left--;
            }
            count++;
        }

        Collections.sort(rs);
        return rs;
    }

    public static int searchElementInsertLinear(int arr[], int target){
        int rsIndex=-1;
        int rsValue=Integer.MAX_VALUE;
        int currentValue=Integer.MAX_VALUE;

        for(int i=0;i<arr.length;i++){
            int value=Math.abs(arr[i]-target);

            if(value<rsValue
                    ||(rsValue==Math.abs(arr[i]-target))&&arr[i]<currentValue){
                rsValue=value;
                rsIndex=i;
                currentValue=arr[i];
            }else if(currentValue!=arr[i]){
                break;
            }
        }
        return rsIndex;
    }

    public static List<Integer> findClosestElementsOptimize(int[] arr, int k, int x) {
        List<Integer> rs=new LinkedList<>();
        int low=0;
        int high=arr.length-1;

        while (high-low>=k){
            int distLow=Math.abs(arr[low]-x);
            int distHigh=Math.abs(arr[high]-x);

            if(distHigh>=distLow){
                high--;
            }else {
                low++;
            }
        }
        for(int i=low;i<=high;i++){
            rs.add(arr[i]);
        }
        return rs;
    }

//    public static int searchElementInsert(int arr[], int low, int high, int target){
//        int mid=low + (high-low)/2;
//        int index=-1;
//
//        if(low>=high-1){
//            if(low!=high){
//                if(Math.abs(low-target)<Math.abs(high-target)){
//                    return low;
//                }else{
//                    return high;
//                }
//            }
//            return low;
//        }
//
//        if(arr[mid]>target){
//            index=searchElementInsert(arr, low, mid-1, target);
//        }else{
//            index=searchElementInsert(arr, mid, high, target);
//        }
//        return index;
//    }

    public static List<Integer> findClosestElementsOptimize1(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,4,5};
//        int arr[]=new int[]{1,2};
        int arr[]=new int[]{1,2,5,5,6,6,7,7,8,9};
        //Case 1:
        //Search theo binary (với việc tìm index searchInsert) sẽ bị lỗi case này với việc chọn (1,10)
        //+ Solution : Một là phải đổi phép check condtition <> Chuyển qua linear search dễ hơn.
//        int arr[]=new int[]{1,1,1,10,10,10};
//        int k=4;
//        int x=3;
//        int k=4;
//        int x=-1;
//        int k=1;
//        int x=9;
        int k=7;
        int x=7;
        System.out.println(findClosestElements(arr, k, x));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Ở đây tìm điểm gần nhất của (i) thỏa mãn các điều kiện:
        //+ |a - x| < |b - x|
        //+ |a - x| == |b - x| and a < b
        //1.1, Ở đây ta đã SAI khi áp dụng binary search vi tìm index bị sai ở case:
        //int arr[]=new int[]{1,1,1,10,10,10};
        //--> Ở đây nếu ta search(9) : return 2
        //==> Mặc dù rule ta đang áp dụng là |a-x| và |b-x|
        //+ Search theo binary (với việc tìm index searchInsert) sẽ bị lỗi case này với việc chọn (1,10)
        //+ Solution : Một là phải đổi phép check condtition <> Chuyển qua linear search dễ hơn.
        //
        //1.2, Sau khi search xong thì ta sẽ đi sang 2 phía (left/ right) của index
        //--> Lấy lần lượt các phần tử add vòa rs.
        //Cách 2:
        //1, Thay vì ta tìm index sau đó đi ra 2 phía left, right
        // + sort sau khi add vào list.
        //2, Ta sẽ loại bỏ tất cả các phần tử tệ hơn ở 2 phía (low và high) cho đến khi
        //số lượng phần tử ở giữa (low - high) là k
        //** KINH NGHIỆM TƯ DUY: Phản nghịch tìm k phần tử từ pivot đi ra 2 bên left và right
        //Thay vì cố tìm index + sau đó lấy k phần tử ở left, right
        //----> Loại bỏ các phần tử BAD ==> Nhận được k phần tử tốt nhất.
        //3,
        //if(distHigh>=distLow){
        //                high--;
        //--> Vì ta ưu tiên phần tử less than ==> Xóa high (Xóa đi phần tử lớn hơn).
        System.out.println(findClosestElementsOptimize(arr, k, x));
        //Cách 3:
        //1, Bài này ta đang tìm k phần tử closest:
        //1.1, Tư duy chính ở đây là chuyển thành dạng (slice window):
        //+ Vì dãy số ở đây có tính chất (LIÊN TIẾP NHAU + TĂNG DẦN)
        //VD:
        //A[mid] --> A[mid + k]
        //** Coi x là số cần tìm sao cho các elements gần X nhất:
        //case 1: x - A[mid] < A[mid + k] - x, need to move window go left
        //-------x----A[mid]-----------------A[mid + k]----------
        //
        //case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
        //-------A[mid]----x-----------------A[mid + k]----------
        //
        //case 3: x - A[mid] > A[mid + k] - x, need to move window go right
        //-------A[mid]------------------x---A[mid + k]----------
        //
        //case 4: x - A[mid] > A[mid + k] - x, need to move window go right
        //-------A[mid]---------------------A[mid + k]----x------
        //Question:
        //+ Với các tư duy này --> Làm thế nào xác định được slice window (k elements) ngay từ đầu.
        //==> Không phải slice window ngay từ đầu...
        //Answer:
        //+ Ta sẽ chọn ra n-k phần tử liên tiếp nhau --> để tính mid =left + (righ-left)/2 + k (KHÔNG BỊ QUÁ ARRAY)
        //** KINH NGHIỆM :
        // Tư duy ở đây là kiểm tra mối quan hệ giữa (slice window) và (x)
        //==> Tìm vị trí (mid) sao cho (mid) thỏa mãn điều kiện (x nằm giữa arr[mid] và arr[mid +k]
        //Tổng quát:
        //+ x - A[mid] < A[mid + k] - x :
        //
        //+ x - A[mid] > A[mid + k] - x :--> Dịch slice window sang right.
        //
        System.out.println(findClosestElementsOptimize1(arr, k, x));
        //** Xử lý nốt vấn đề tư duy phần này????

    }
}
